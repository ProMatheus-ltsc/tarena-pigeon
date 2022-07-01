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

import com.tarena.mnmp.admin.codegen.api.task.TaskApi;
import com.tarena.mnmp.task.Task;
import com.tarena.mnmp.task.TaskPage;
import com.tarena.mnmp.task.TaskQuery;
import com.tarena.mnmp.task.TaskStatistics;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TaskController implements TaskApi {
    @Override public void addTask(String name, Long appId, Integer noticeType, Integer templateType, Long templateId,
        Integer taskType, String remark, MultipartFile file, Integer auditStatus, Long signId, Long triggerTime,
        Long triggerEndTime, Integer cycleLevel, Integer cycleNum) {

    }

    @Override public void doAudit(Long id, Integer auditStatus, String auditResult) {

    }

    @Override public TaskPage queryListByPage(TaskQuery taskQuery) {
        return null;
    }

    @Override public Task queryTaskDetail(Long id) {
        return null;
    }

    @Override public TaskStatistics queryTaskStatistics(Long id) {
        return null;
    }

    @Override public void stopTask(Long id) {

    }

    @Override
    public void updateTask(Long id, String name, Long appId, Integer noticeType, Integer templateType, Long templateId,
        Integer taskType, String remark, Integer auditStatus, Long signId, Long triggerTime, Long triggerEndTime,
        Integer cycleLevel, Integer cycleNum, MultipartFile file) {

    }
}
