/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tarena.mnmp.admin.controller.task;

import com.alibaba.excel.util.StringUtils;
import com.tarena.mnmp.admin.codegen.api.task.TaskApi;
import com.tarena.mnmp.commons.pager.PagerResult;
import com.tarena.mnmp.commons.utils.DateUtils;
import com.tarena.mnmp.commons.utils.ExcelUtils;
import com.tarena.mnmp.domain.TaskDO;
import com.tarena.mnmp.domain.task.TaskQuery;
import com.tarena.mnmp.domain.task.TaskService;
import com.tarena.mnmp.domain.task.TaskStatistics;
import com.tarena.mnmp.enums.TaskStatus;
import com.tarena.mnmp.protocol.BusinessException;
import com.tarena.mnmp.protocol.Result;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TaskController implements TaskApi {




    @Autowired
    private TaskService taskService;

    @Value("${excel.path}")
    private String excelPath;

    @Override public void getExcel(String path, HttpServletResponse response) throws BusinessException {
        File file;
        if (StringUtils.isBlank(path)) {
            // 输出默认的
            try {
                file = ResourceUtils.getFile("classpath:target.xlsx");
            } catch (FileNotFoundException e) {
                throw new BusinessException("201", "读取文件异常，请稍后再试");
            }
        } else {
            String filePath = excelPath + path;
            file = new File(filePath);
        }

        if (!file.exists() || file.length() == 0) {
            throw new BusinessException("202", "文件不存在！！！");
        }

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            throw new BusinessException("201", "读取文件异常，请稍后再试");
        }

    }

    @Override public Result<String> uploadFile(MultipartFile file) throws IOException, BusinessException {
        if (null == file) {
            throw new BusinessException("201", "上传文件不能为空");
        }
        if (!ExcelUtils.checkExcel(file.getOriginalFilename())) {
            throw new BusinessException("202", "上传文件不是excel格式");
        }
        String name = UUID.randomUUID() + "." + ExcelUtils.suffixName(file.getOriginalFilename());
        StringBuilder newPath = new StringBuilder();
        newPath.append(excelPath).append(DateUtils.dateStr(new Date(), "yyyy/MM/dd/"));
        File f = new File(newPath.toString());
        if (!f.exists() && !f.mkdirs()) {
            throw new BusinessException("203", "目录创建失败，请检查权限");
        }
        newPath.append(name);
        Streams.copy(file.getInputStream(), Files.newOutputStream(Paths.get(newPath.toString())), true);
        return new Result<>(newPath.toString());
    }

    @Transactional
    @Override public void addTask(TaskParam taskParam) {
        TaskDO bo = new TaskDO();
        BeanUtils.copyProperties(taskParam, bo);
        taskService.addTask(bo, taskParam.getFilePath());
    }

    @Override public void doAudit(Long id, Integer auditStatus, String auditResult) {
        taskService.doAudit(id, auditStatus, auditResult);
    }

    @Override public PagerResult<TaskView> queryListByPage(TaskQuery taskQuery) {
        PagerResult<TaskDO> res = taskService.queryListByPage(taskQuery);
        List<TaskView> list = new ArrayList<>();
        res.getList().forEach(task -> {
            TaskView tv = new TaskView();
            BeanUtils.copyProperties(task, tv);
            list.add(tv);
        });
        PagerResult<TaskView> result = new PagerResult<>();
        result.setList(list);
        result.setPageSize(taskQuery.getPageSize());
        result.setRecordCount(res.getRecordCount());
        result.setCurrentPageIndex(taskQuery.getCurrentPageIndex());
        return result;
    }

    @Override public TaskView queryTaskDetail(Long id) {
        TaskDO task = taskService.detailById(id);
        TaskView tv = new TaskView();
        BeanUtils.copyProperties(task, tv);
        return tv;
    }

    @Override public TaskStatistics queryTaskStatistics(Long id) {
        return null;
    }

    @Override public void stopTask(Long id) {
        taskService.action(id, TaskStatus.TASK_STOP.status());
    }

    @Override public void startTask(Long id) {
        taskService.action(id, TaskStatus.TASK_NO_OPEN.status());
    }

    @Override public void modify(TaskParam taskParam) {
        TaskDO task = new TaskDO();
        BeanUtils.copyProperties(taskParam, task);
        taskService.updateTask(task);
    }
}
