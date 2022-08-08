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

package com.tarena.mnmp.admin.controller.provider;

import com.tarena.mnmp.domain.ProviderDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "供应商控制层出参")
public class ProviderView {

    @ApiModelProperty(
        value = "主键",
        name = "id"
    )
    private Long id;
    @ApiModelProperty(
        value = "供应商名称",
        name = "name"
    )
    private String name;
    @ApiModelProperty(
        value = "供应商编码",
        name = "code"
    )
    private String code;
    @ApiModelProperty(
        value = "业务类型",
        name = "noticeType"
    )
    private Integer noticeType;
    @ApiModelProperty(
        value = "官方网站",
        name = "officialWebsite"
    )
    private String officialWebsite;
    @ApiModelProperty(
        value = "联系人",
        name = "contacts"
    )
    private String contacts;
    @ApiModelProperty(
        value = "联系电话",
        name = "phone"
    )
    private String phone;
    @ApiModelProperty(
        value = "简介",
        name = "remarks"
    )
    private String remarks;


    private String clientConfig;

    @ApiModelProperty(
        value = "审核状态",
        name = "auditStatus",
        notes = "0审核中,1通过,-1未通过"
    )
    private Integer auditStatus;
    @ApiModelProperty(
        value = "开启停用",
        name = "enabled",
        notes = "0停用,1开启"
    )
    private Integer enabled;
    @ApiModelProperty(
        value = "创建时间",
        name = "createTime"
    )
    private Date createTime;
    @ApiModelProperty(
        value = "更新时间",
        name = "updateTime"
    )
    private Date updateTime;

    public static List<ProviderView> convert(List<ProviderDO> source) {
        if (null == source) {
            return new ArrayList<>();
        }
        List<ProviderView> list = new ArrayList<>(source.size());
        for (ProviderDO providerDO : source) {
            ProviderView providerView = new ProviderView();
            BeanUtils.copyProperties(providerDO, providerView);
            list.add(providerView);
        }
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getClientConfig() {
        return clientConfig;
    }

    public void setClientConfig(String clientConfig) {
        this.clientConfig = clientConfig;
    }
}