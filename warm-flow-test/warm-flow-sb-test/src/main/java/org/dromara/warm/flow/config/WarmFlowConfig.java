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

import org.dromara.warm.flow.core.listener.*;
import org.dromara.warm.flow.core.handle.CustomDataFillHandler;
import org.dromara.warm.flow.core.handle.CustomPermissionHandler;
import org.dromara.warm.flow.core.handle.CustomTenantHandler;
import org.dromara.warm.flow.core.handler.DataFillHandler;
import org.dromara.warm.flow.core.handler.PermissionHandler;
import org.dromara.warm.flow.core.handler.TenantHandler;
import org.dromara.warm.flow.core.listener.GlobalListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * warm-flow配置文件
 *
 * @author warm
 */
@Configuration
public class WarmFlowConfig {

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
     * 节点创建监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public CreateListener createListener() {
        return new CreateListener();
    }

    /**
     * 节点完成监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public FinishListener finishListener() {
        return new FinishListener();
    }

    /**
     * 节点开始监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public StartListener startListener() {
        return new StartListener();
    }

    /**
     * 节点分派监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public AssignmentListener assignmentListener() {
        return new AssignmentListener();
    }

    /**
     * 流程创建监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public DefCreateListener defCreateListener() {
        return new DefCreateListener();
    }

    /**
     * 流程完成监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public DefFinishListener defFinishListener() {
        return new DefFinishListener();
    }

    /**
     * 流程开始监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public DefStartListener defStartListener() {
        return new DefStartListener();
    }

    /**
     * 流程分派监听器，可用@Bean/@Component方式注入
     */
    @Bean
    public DefAssignmentListener defAssignmentListener() {
        return new DefAssignmentListener();
    }

    @Bean
    public GlobalListener globalListener() {
        return new CustomGlobalListener();
    }
}
