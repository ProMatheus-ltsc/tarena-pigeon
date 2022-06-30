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

package com.tarena.mnmp.admin.codegen.api.template;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.tarena.mnmp.template.SmsTemplate;
import com.tarena.mnmp.template.SmsTemplatePage;
import com.tarena.mnmp.template.WecomTemplate;
import com.tarena.mnmp.template.WecomTemplatePage;
import com.tarena.mnmp.template.WecomTemplatePageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Validated
@Api(
    value = "模板管理",
    tags = "模板管理"
)
@RequestMapping("/template")
public interface TemplateApi {
    @ApiOperationSupport(order = 4001)
    @ApiOperation(
        value = "新增短信模板",
        nickname = "addSmsTemplate",
        notes = "",
        response = String.class
    )
    @PostMapping(
        value = {"/sms/add"},
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    String addSmsTemplate(@ApiParam(value = "新增短信模板", required = true) @Valid @RequestBody SmsTemplate smsTemplate);

    @ApiOperationSupport(order = 4002)
    @ApiOperation(
        value = "关闭使用短信模板",
        nickname = "closeSmsTemplate",
        notes = ""
    )
    @PostMapping({"/template/sms/close"})
    void closeSmsTemplate(
        @NotNull @ApiParam(value = "要关闭的模板id", required = true) @Valid @RequestParam(value = "id", required = true) Long id);

    @ApiOperationSupport(order = 4003)
    @ApiOperation(
        value = "开启使用短信模板",
        nickname = "openSmsTemplate",
        notes = ""
    )
    @PostMapping({"/template/sms/open"})
    void openSmsTemplate(
        @NotNull @ApiParam(value = "要开启的模板id", required = true) @Valid @RequestParam(value = "id", required = true) Long id);

    @ApiOperationSupport(order = 4004)
    @ApiOperation(
        value = "查询短信模板信息（分页）",
        nickname = "queryListByPage",
        notes = "",
        response = SmsTemplatePage.class
    )
    @GetMapping(
        value = {"/template/sms/queryListByPage"},
        produces = {"application/json"}
    )
    SmsTemplatePage queryListByPage(
        @ApiParam("根据模板名称/模板code模糊查询") @Valid @RequestParam(value = "keyword", required = false) String keyword,
        @ApiParam("根据应用code查询") @Valid @RequestParam(value = "appCode", required = false) String appCode,
        @Min(1L) @ApiParam("页数（不传默认第一页）") @Valid @RequestParam(value = "pageNum", required = false) Integer pageNum,
        @Min(0L) @ApiParam("显示条数（不传默认10条）") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @ApiOperationSupport(order = 4005)
    @ApiOperation(
        value = "查询短信模板信息",
        nickname = "queryListByParam",
        notes = "",
        response = SmsTemplate.class,
        responseContainer = "List"
    )
    @GetMapping(
        value = {"/template/sms/queryListByParam"},
        produces = {"application/json"}
    )
    List<SmsTemplate> queryListByParam(
        @ApiParam("根据模板名称/模板code模糊查询") @Valid @RequestParam(value = "keyword", required = false) String keyword,
        @ApiParam("根据应用code查询") @Valid @RequestParam(value = "appCode", required = false) String appCode,
        @ApiParam("根据消息类型查询 1:sms") @Valid @RequestParam(value = "noticeType", required = false) Integer noticeType,
        @ApiParam("根据消息子类型查询 1:sms-短信通知 2:sms-验证码 3:sms-推广短信") @Valid @RequestParam(value = "templateType", required = false) Integer templateType);

    @ApiOperationSupport(order = 4006)
    @ApiOperation(
        value = "查看短信模板详情",
        nickname = "querySmsTemplateDetail",
        notes = "",
        response = SmsTemplate.class
    )
    @GetMapping(
        value = {"/template/sms/queryDetail"},
        produces = {"application/json"}
    )
    SmsTemplate querySmsTemplateDetail(
        @NotNull @ApiParam(value = "模板id", required = true) @Valid @RequestParam(value = "id", required = true) Long id);

    @ApiOperationSupport(order = 4007)
    @ApiOperation(
        value = "修改短信模板",
        nickname = "updateSmsTemplate",
        notes = "",
        response = String.class
    )
    @PostMapping(
        value = {"/template/sms/update"},
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    String updateSmsTemplate(
        @ApiParam(value = "修改短信模板(未通过审核)", required = true) @Valid @RequestBody SmsTemplate templateSms);

    @ApiOperationSupport(order = 4008)
    @ApiOperation(
        value = "短信模板审核",
        nickname = "doAuditSmsTemplate",
        notes = ""
    )
    @PostMapping({"/template/sms/doAudit"})
    void doAuditSmsTemplate(
        @NotNull @ApiParam(value = "要审核的模板id", required = true) @Valid @RequestParam(value = "id", required = true) Long id,
        @NotNull @ApiParam(value = "审核状态 -1未通过 1通过", required = true) @Valid @RequestParam(value = "auditStatus", required = true) Integer auditStatus,
        @ApiParam("审核意见") @Valid @RequestParam(value = "auditResult", required = false) String auditResult);

    @ApiOperationSupport(order = 4101)
    @ApiOperation(
        value = "新增企微模板",
        nickname = "addWecomTemplate",
        notes = "",
        response = String.class
    )
    @PostMapping(
        value = {"/template/wecom"},
        produces = {"application/json"}
    )
    String addWecomTemplate(
        @NotNull @ApiParam(value = "模版名称", required = true) @Valid @RequestParam(value = "name", required = true) String name,
        @NotNull @ApiParam(value = "系统应用id", required = true) @Valid @RequestParam(value = "appId", required = true) Long appId,
        @NotNull @ApiParam(value = "企微应用id", required = true) @Valid @RequestParam(value = "wecomAgentId", required = true) Long wecomAgentId,
        @NotNull @ApiParam(value = "消息模板类型 1:文本 2:图片 3:视频 4:文件 5:文本卡片", required = true) @Valid @RequestParam(value = "templateType", required = true) Integer templateType,
        @ApiParam("卡片跳转链接") @Valid @RequestParam(value = "url", required = false) String url,
        @ApiParam("视频标题/卡片标题") @Valid @RequestParam(value = "title", required = false) String title,
        @ApiParam("文本内容/视频描述/卡片内容") @Valid @RequestParam(value = "description", required = false) String description,
        @ApiParam("按钮文案") @Valid @RequestParam(value = "btntxt", required = false) String btntxt,
        @ApiParam("分享设置：0表示可对外分享，1表示不能分享") @Valid @RequestParam(value = "safe", required = false) Integer safe,
        @ApiParam("附件") @Valid @RequestParam(value = "file", required = false) MultipartFile file);

    @ApiOperationSupport(order = 4102)
    @ApiOperation(
        value = "查询企微模板信息（分页）",
        nickname = "queryWecomListByPage",
        notes = "",
        response = WecomTemplatePage.class
    )
    @PostMapping(
        value = {"/template/wecom/page"},
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    WecomTemplatePage queryWecomListByPage(
        @ApiParam(value = "模版分页查询参数", required = true) @Valid @RequestBody WecomTemplatePageQuery wecomTemplatePageQuery);

    @ApiOperationSupport(order = 4103)
    @ApiOperation(
        value = "查看企微模板详情",
        nickname = "queryWecomTemplateDetail",
        notes = "",
        response = WecomTemplate.class
    )
    @GetMapping(
        value = {"/template/wecom/{templateId}"},
        produces = {"application/json"}
    )
    WecomTemplate queryWecomTemplateDetail(
        @ApiParam(value = "模板id", required = true) @PathVariable("templateId") Long templateId);

    @ApiOperationSupport(order = 4104)
    @ApiOperation(
        value = "查询企微模板信息",
        nickname = "queryWecomTemplatesByParam",
        notes = "",
        response = WecomTemplate.class,
        responseContainer = "List"
    )
    @GetMapping(
        value = {"/template/wecoms"},
        produces = {"application/json"}
    )
    List<WecomTemplate> queryWecomTemplatesByParam(
        @ApiParam("根据模板名称/模板code模糊查询") @Valid @RequestParam(value = "keyword", required = false) String keyword,
        @ApiParam("根据应用id查询") @Valid @RequestParam(value = "appId", required = false) Long appId,
        @ApiParam("根据消息类型查询  3:wecom") @Valid @RequestParam(value = "noticeType", required = false) Integer noticeType,
        @ApiParam("根据消息子类型查询 1:文本 2:图片 3:视频 4:文件 5:文本卡片") @Valid @RequestParam(value = "templateType", required = false) Integer templateType);

    @ApiOperationSupport(order = 4105)
    @ApiOperation(
        value = "修改企微模板",
        nickname = "updateWecomTemplate",
        notes = "",
        response = String.class
    )
    @PutMapping(
        value = {"/template/wecom"},
        produces = {"application/json"}
    )
    String updateWecomTemplate(
        @NotNull @ApiParam(value = "模版id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id,
        @NotNull @ApiParam(value = "模版code", required = true) @Valid @RequestParam(value = "code", required = true) String code,
        @NotNull @ApiParam(value = "模版名称", required = true) @Valid @RequestParam(value = "name", required = true) String name,
        @NotNull @ApiParam(value = "系统应用id", required = true) @Valid @RequestParam(value = "appId", required = true) Long appId,
        @NotNull @ApiParam(value = "企微应用id", required = true) @Valid @RequestParam(value = "wecomAgentId", required = true) Long wecomAgentId,
        @NotNull @ApiParam(value = "消息模板类型 1:文本 2:图片 3:视频 4:文件 5:文本卡片", required = true) @Valid @RequestParam(value = "templateType", required = true) Integer templateType,
        @ApiParam("卡片跳转链接") @Valid @RequestParam(value = "url", required = false) String url,
        @ApiParam("视频标题/卡片标题") @Valid @RequestParam(value = "title", required = false) String title,
        @ApiParam("文本内容/视频描述/卡片内容") @Valid @RequestParam(value = "description", required = false) String description,
        @ApiParam("按钮文案") @Valid @RequestParam(value = "btntxt", required = false) String btntxt,
        @ApiParam("分享设置：0表示可对外分享，1表示不能分享") @Valid @RequestParam(value = "safe", required = false) Integer safe,
        @ApiParam("附件") @Valid @RequestParam(value = "file", required = false) MultipartFile file);

    @ApiOperationSupport(order = 4106)
    @ApiOperation(
        value = "企微模板审核",
        nickname = "updateWecomTemplateAuditStatus",
        notes = ""
    )
    @PutMapping({"/template/wecom/{id}/audit/{auditStatus}"})
    void updateWecomTemplateAuditStatus(@ApiParam(value = "企微模版id", required = true) @PathVariable("id") Long id,
        @ApiParam(value = "审核状态 -1未通过 1通过", required = true) @PathVariable("auditStatus") Integer auditStatus,
        @ApiParam("审核意见") @Valid @RequestParam(value = "auditResult", required = false) String auditResult);

    @ApiOperationSupport(order = 4107)
    @ApiOperation(
        value = "企微模版启用停用",
        nickname = "updateWecomTemplateUseStatus",
        notes = ""
    )
    @PutMapping({"/template/wecom/{id}/useage/{useStatus}"})
    void updateWecomTemplateUseStatus(@ApiParam(value = "模板id", required = true) @PathVariable("id") Long id,
        @ApiParam(value = "使用状态 1启用 0停用", required = true) @PathVariable("useStatus") Integer useStatus);
}
