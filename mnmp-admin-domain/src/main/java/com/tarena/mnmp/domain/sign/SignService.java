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

package com.tarena.mnmp.domain.sign;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    @Autowired
    private SignDao signDao;

    public void addSign(SignSaveParam signSaveParam) {
        SignDO signDO = new SignDO();
        BeanUtils.copyProperties(signSaveParam, signDO);
        signDao.save(signDO);
    }

    public void closeSign(Long id) {
        signDao.disable(id);
    }

    public void editSign(SignSaveParam signSaveParam) {
        SignDO signDO = new SignDO();
        BeanUtils.copyProperties(signSaveParam, signDO);
        signDao.modify(signDO);
    }

    public void openSign(Long id) {
        signDao.enable(id);
    }

    public SignDO querySignDetail(Long id) {
        return signDao.findById(id);
    }

    public List<SignDO> querySignList(SignQuery signQuery) {
        return signDao.querySigns(signQuery);
    }

    public void auditSign(Long id, Integer status) {
        SignDO signDO = new SignDO();
        signDO.setId(id);
        signDO.setAuditStatus(signDO.getAuditStatus());
        signDao.modify(signDO);
    }
}