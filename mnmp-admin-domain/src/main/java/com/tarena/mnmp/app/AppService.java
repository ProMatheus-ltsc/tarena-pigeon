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

package com.tarena.mnmp.app;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Autowired
    private AppDao appDao;

    public void addApp(AppAddParam appAddParam) {
        AppDO appDO = new AppDO();
        BeanUtils.copyProperties(appAddParam, appDO);
        appDao.save(appDO);
    }

    public void editApp(AppEditParam appEditParam) {
        AppDO appDO = new AppDO();
        BeanUtils.copyProperties(appEditParam, appDO);
        appDao.modify(appDO);
    }

    public void closeApp(Long id) {
        appDao.disable(id);
    }

    public void openApp(Long id) {
        appDao.enable(id);
    }

    public AppDTO queryAppDetail(Long id) {
        AppDO appDO = appDao.findById(id);
        AppDTO appDTO = new AppDTO();
        BeanUtils.copyProperties(appDO, appDTO);
        return appDTO;
    }

    public List<AppDTO> queryList() {
        List<AppDO> appDOs = appDao.queryAllApps();
        List<AppDTO> appDTOs = new ArrayList<>();
        for (AppDO appDO : appDOs) {
            AppDTO appDTO = new AppDTO();
            BeanUtils.copyProperties(appDO, appDTO);
            appDTOs.add(appDTO);
        }
        return appDTOs;
    }

}
