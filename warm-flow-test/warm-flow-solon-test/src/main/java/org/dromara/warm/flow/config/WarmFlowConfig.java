/*
 *    Copyright 2024-2025, Warm-Flow (290631660@qq.com).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.dromara.warm.flow.config;

import com.zaxxer.hikari.HikariDataSource;
import org.dromara.warm.flow.core.listener.*;
import org.dromara.warm.flow.core.handle.CustomDataFillHandler;
import org.dromara.warm.flow.core.handle.CustomPermissionHandler;
import org.dromara.warm.flow.core.handle.CustomTenantHandler;
import org.dromara.warm.flow.core.handler.DataFillHandler;
import org.dromara.warm.flow.core.handler.PermissionHandler;
import org.dromara.warm.flow.core.handler.TenantHandler;
import org.dromara.warm.flow.core.listener.GlobalListener;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

@Configuration
public class WarmFlowConfig {
    //此下的 db1 与 mybatis.db1 将对应在起来 //可以用 @Db("db1") 注入mapper
    //typed=true，表示默认数据源。@Db 可不带名字注入
    @Bean(value = "db1", typed = true)
    public DataSource db1(@Inject("${demo.db1}") HikariDataSource ds) {
        return ds;
    }

    /**
     * 自定义填充 （可配置文件注入，也可用@Bean/@Component方式）
     */
    @Bean
    public DataFillHandler dataFillHandler() {
        return new CustomDataFillHandler();
    }

    /**
     * 全局租户处理器（可配置文件注入，也可用@Bean/@Component方式）
     */
    @Bean
    public TenantHandler tenantHandler() {
        return new CustomTenantHandler();
    }

    /**
     * 权限校验器 （可配置文件注入，也可用@Bean/@Component方式）
     */
    @Bean
    public PermissionHandler permissionHandler() {
        return new CustomPermissionHandler();
    }

    /**
     * 创建监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public CreateListener createListener() {
        return new CreateListener();
    }

    /**
     * 完成监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public FinishListener finishListener() {
        return new FinishListener();
    }

    /**
     * 开始监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public StartListener startListener() {
        return new StartListener();
    }

    /**
     * 分派监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public AssignmentListener assignmentListener() {
        return new AssignmentListener();
    }

     @Bean
    public GlobalListener globalListener() {
        return new CustomGlobalListener();
    }
}
