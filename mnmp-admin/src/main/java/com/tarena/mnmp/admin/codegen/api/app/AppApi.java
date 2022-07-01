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

package com.tarena.mnmp.admin.codegen.api.app;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.tarena.mnmp.app.App;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@Api(
    value = "app",
    tags = "应用管理"
)
@RequestMapping("/app")
public interface AppApi {
    /**
     * 新增应用
     *
     * @param app
     */
    @ApiOperationSupport(order = 1001)
    @ApiOperation(
        value = "新增应用",
        nickname = "addApp",
        notes = ""
    )
    @PostMapping(
        value = {"/add"},
        consumes = {"application/json"}
    )
    void addApp(@ApiParam(value = "新增应用", required = true) @Valid @RequestBody App app);

    /**
     * 编辑应用
     *
     * @param app
     */
    @ApiOperationSupport(order = 1002)
    @ApiOperation(
        value = "编辑应用",
        nickname = "editApp",
        notes = ""
    )
    @PostMapping(
        value = {"/edit"},
        consumes = {"application/json"}
    )
    void editApp(@ApiParam(value = "编辑应用", required = true) @Valid @RequestBody App app);

    /**
     * 关闭应用
     *
     * @param id
     */
    @ApiOperationSupport(order = 1003)
    @ApiOperation(
        value = "关闭应用",
        nickname = "closeApp",
        notes = ""
    )
    @PostMapping({"/close"})
    void closeApp(
        @NotNull @ApiParam(value = "要关闭的应用", required = true) @Valid @RequestParam(value = "id", required = true) Long id);

    /**
     * 开启应用
     *
     * @param id
     */
    @ApiOperationSupport(order = 1004)
    @ApiOperation(
        value = "开启应用",
        nickname = "openApp",
        notes = ""
    )
    @PostMapping({"/open"})
    void openApp(
        @NotNull @ApiParam(value = "要开启的应用id", required = true) @Valid @RequestParam(value = "id", required = true) Long id);

    /**
     * 查看应用详情
     *
     * @param id
     */
    @ApiOperationSupport(order = 1005)
    @ApiOperation(
        value = "查看应用详情",
        nickname = "queryAppDetail",
        notes = "",
        response = App.class
    )
    @GetMapping(
        value = {"/queryDetail"},
        produces = {"application/json"}
    )
    App queryAppDetail(
        @NotNull @ApiParam(value = "应用id", required = true) @Valid @RequestParam(value = "id", required = true) Long id);

    /**
     * 查看应用详情
     */
    @ApiOperationSupport(order = 10065)
    @ApiOperation(
        value = "查询应用管理列表",
        nickname = "queryList",
        notes = "",
        response = App.class,
        responseContainer = "List"
    )
    @GetMapping(
        value = {"/queryList"},
        produces = {"application/json"}
    )
    List<App> queryList();

    /**
     * 查看应用详情
     */
    @ApiOperationSupport(order = 1007)
    @ApiOperation(
        value = "根据用户查询相应的应用管理列表",
        nickname = "queryListByUser",
        notes = "",
        response = App.class,
        responseContainer = "List"
    )
    @GetMapping(
        value = {"/queryListByUser"},
        produces = {"application/json"}
    )
    List<App> queryListByUser();
}
