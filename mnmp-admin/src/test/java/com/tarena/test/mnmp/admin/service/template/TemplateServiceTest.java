package com.tarena.test.mnmp.admin.service.template;
import com.tarena.mnmp.commons.json.Json;
import com.tarena.mnmp.domain.template.TemplateQuery;
import com.tarena.test.mnmp.admin.sql.app.AppSqlScript;
import java.util.Date;

import com.tarena.mnmp.admin.AdminApplication;
import com.tarena.mnmp.domain.SmsTemplateDO;
import com.tarena.mnmp.domain.app.AppService;
import com.tarena.mnmp.domain.template.TemplateService;
import com.tarena.test.mnmp.admin.service.app.AppServiceTest;
import java.util.List;
import javax.annotation.Resource;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = {AdminApplication.class})
@Ignore
public class TemplateServiceTest {


    Logger logger = LoggerFactory.getLogger(TemplateServiceTest.class);
    @Resource
    private TemplateService templateService;



    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void addSmsTemplate() {
        for (int i = 0 ; i < 100; i++) {
            SmsTemplateDO sms = new SmsTemplateDO();
            sms.setCode("xx-ss-" + i);
            sms.setAppId(1L + i);
            sms.setName("测试模板00" + i);
            sms.setTemplateType(1);
            sms.setContent("模板内容" + i);
            sms.setRemark("备注" + i);
            sms.setAuditStatus(1);
            sms.setAuditResult("结果内容" + i);
            sms.setEnabled(1);
            sms.setUseCount(0);
            sms.setCreateTime(new Date());
            sms.setDeleted(0);
            sms.setCreateUserId(0);
            sms.setCreateUserName("测试");
            sms.setUpdateTime(new Date());
            sms.setNoticeType(1);
            sms.setAppCode("2qqq");
            sms.setClientConfig("{\"accessKeyId\":\"***\",\"accessKeySecret\":\"***\",\"defaultTemplate\":\"文案文案\"}");

            templateService.addSmsTemplate(sms);
        }


    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void closeSmsTemplate () {
        templateService.closeSmsTemplate(2L);
    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void openSmsTemplate() {
        templateService.openSmsTemplate(2L);
    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void queryListPage () {
        TemplateQuery query = new TemplateQuery();
        query.setAppCode("tmtc");
        query.setAuditStatus(1);
        query.setEnabled(1);
        query.setTemplateCode("");
        query.setTemplateName("");
        query.setPageSize(10);
        query.setCurrentPageIndex(1);

        List<SmsTemplateDO> dos = templateService.queryListPage(query);
        Long count = templateService.queryCount(query);
        logger.info("page:{}", dos.size());

    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void queryListByParam() {
        List<SmsTemplateDO> dos = templateService.queryListByParam(null, null, null, null);
        logger.info("size:{}", dos.size());
    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void querySmsTemplateDetail () {
        SmsTemplateDO smsTemplateDO = templateService.querySmsTemplateDetail(1L);
        logger.info("detail:{}", smsTemplateDO.toString());
    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void updateSmsTemplate () {
        SmsTemplateDO sms = new SmsTemplateDO();
        sms.setId(1L);
        sms.setRemark("update test");
        templateService.updateSmsTemplate(sms);
    }

    @Test
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = {AppSqlScript.TRUNCATE_TABLE},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void doAuditSmsTemplate() {
        templateService.doAuditSmsTemplate(1L, -1, "文案不通过");
    }


}