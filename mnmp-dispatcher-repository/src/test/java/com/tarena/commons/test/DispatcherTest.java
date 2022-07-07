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

package com.tarena.commons.test;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.tarena.dispatcher.assemble.impl.EmailTargetAssembler;
import com.tarena.dispatcher.assemble.impl.SmsTargetAssembler;
import com.tarena.dispatcher.assemble.impl.TargetAssemblerRegistry;
import com.tarena.dispatcher.event.SmsNoticeEvent;
import com.tarena.dispatcher.impl.DispatcherRegistry;
import com.tarena.dispatcher.impl.EmailAliNoticeDispatcher;
import com.tarena.dispatcher.impl.SmsAliNoticeDispatcher;
import com.tarena.dispatcher.respository.TargetLogRepository;
import com.tarena.mnmp.api.NoticeDTO;
import com.tarena.mnmp.api.TargetDTO;
import com.tarena.mnmp.commons.utils.DollarPlaceholderReplacer;
import com.tarena.mnmp.enums.NoticeType;
import com.tarena.mnmp.monitor.Monitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class DispatcherTest {

    private static SmsNoticeEvent assemble() throws Exception {

        DollarPlaceholderReplacer dollarPlaceholderReplacer = new DollarPlaceholderReplacer();
        EmailTargetAssembler emailTargetAssembler = new EmailTargetAssembler();
        emailTargetAssembler.afterPropertiesSet();
        emailTargetAssembler.setDollarPlaceholderReplacer(dollarPlaceholderReplacer);
        SmsTargetAssembler smsTargetAssembler = new SmsTargetAssembler();
        smsTargetAssembler.afterPropertiesSet();
        smsTargetAssembler.setDollarPlaceholderReplacer(dollarPlaceholderReplacer);

        NoticeDTO notice = new NoticeDTO();
        notice.setNoticeType(NoticeType.SMS);
        notice.setSignName("阿里云测试短信");
        notice.setTemplateCode("SMS_154950909");
        notice.setTemplateContent("1656");
        List<String> strings = Arrays.asList("18510273063");
        Map<String, Object> params = new HashMap<>();
        params.put("code", "1656");
        List<TargetDTO> targetDTOS = new ArrayList<>();
        for (String string : strings) {
            TargetDTO targetDTO = new TargetDTO(string, params);
            targetDTOS.add(targetDTO);
        }
        notice.setTargets(targetDTOS);
        SmsNoticeEvent targetList = TargetAssemblerRegistry.getInstance().assemble(notice, 1);
        return targetList;
    }

    @Test
    public void assembler() throws Exception {
        assemble();
    }

    @Test
    public void test() throws Exception {
        TargetLogRepository targetLogRepository = new TargetLogRepositoryMock();
        Client client = new Client(new Config());
        Monitor monitor = new MonitorMock();
        EmailAliNoticeDispatcher emailAliNoticeDispatcher = new EmailAliNoticeDispatcher();
        emailAliNoticeDispatcher.afterPropertiesSet();
        emailAliNoticeDispatcher.setTargetLogRepository(targetLogRepository);
        emailAliNoticeDispatcher.setMonitor(monitor);


        SmsAliNoticeDispatcher smsAliNoticeDispatcher = new AliSmsDispatcherMock();
        smsAliNoticeDispatcher.afterPropertiesSet();
        smsAliNoticeDispatcher.setTargetLogRepository(targetLogRepository);
        smsAliNoticeDispatcher.setMonitor(monitor);
        smsAliNoticeDispatcher.setAliSmsClient(client);
        SmsNoticeEvent baseNoticeTargets = assemble();
        DispatcherRegistry.getInstance().dispatcher(baseNoticeTargets);
    }
}