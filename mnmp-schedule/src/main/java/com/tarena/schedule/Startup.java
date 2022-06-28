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

package com.tarena.schedule;

import com.tarena.dispatcher.assemble.impl.EmailTargetAssembler;
import com.tarena.dispatcher.assemble.impl.SmsTargetAssembler;
import com.tarena.dispatcher.impl.EmailAliNoticeDispatcher;
import com.tarena.dispatcher.impl.SmsAliNoticeDispatcher;
import com.tarena.dispatcher.serialization.SerializationProvider;
import com.tarena.mnmp.api.NoticeTargetEvent;
import com.tarena.mnmp.commons.enums.CycleLevel;
import com.tarena.schedule.utils.NoticeTaskTrigger;
import java.util.ArrayList;
import java.util.List;

public class Startup extends AbstractScheduler {
    public static void main(String[] args) throws Exception {

        EmailTargetAssembler emailTargetAssembler = new EmailTargetAssembler();
        emailTargetAssembler.afterPropertiesSet();
        SmsTargetAssembler smsTargetAssembler = new SmsTargetAssembler();
        smsTargetAssembler.afterPropertiesSet();


        EmailAliNoticeDispatcher emailAliNoticeDispatcher = new EmailAliNoticeDispatcher();
        emailAliNoticeDispatcher.afterPropertiesSet();
        SmsAliNoticeDispatcher smsAliNoticeDispatcher = new SmsAliNoticeDispatcher();
        smsAliNoticeDispatcher.afterPropertiesSet();

        Startup startup = new Startup();
        startup.schedule();
    }

    @Override List<NoticeTaskTrigger> queryTriggers() {
        List<NoticeTaskTrigger> triggers = new ArrayList<>();
        NoticeTaskTrigger trigger = new NoticeTaskTrigger();
        trigger.setCycleLevel(CycleLevel.HOUR.getLevel());
        triggers.add(trigger);
        return triggers;
    }

    @Override boolean stop() {
        return false;
    }

    @Override void finishTask(NoticeTaskTrigger noticeTaskTrigger) {

    }

    @Override void updateNextTriggerTime(NoticeTaskTrigger noticeTaskTrigger) {

    }

    @Override List<List<String>> getTargets(NoticeTaskTrigger noticeTaskTrigger) {
        List<List<String>> targetsList = new ArrayList<>();
        List<String> targets = new ArrayList<>();
        targets.add("135");
        targets.add("136");
        targets.add("137");
        targetsList.add(targets);
        return targetsList;
    }

    @Override <T extends NoticeTargetEvent> void send(List<T> events) {
        SerializationProvider.get().serialize(events);
    }
}
