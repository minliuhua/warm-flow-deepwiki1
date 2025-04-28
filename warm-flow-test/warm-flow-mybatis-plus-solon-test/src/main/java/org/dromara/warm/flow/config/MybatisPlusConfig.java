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

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.apache.ibatis.solon.annotation.Db;
import org.dromara.warm.flow.handler.MpTenantHandler;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    /**
     * MybatisPlus全局配置
     *
     * @param globalConfig 数据源
     */
    @Bean
    public void db1_cfg(@Db("db1") MybatisConfiguration cfg,
                        @Db("db1") GlobalConfig globalConfig) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户插件 必须放到第一位
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor());
        cfg.addInterceptor(interceptor);
    }

    /**
     * 多租户插件
     */
    public TenantLineInnerInterceptor tenantLineInnerInterceptor() {
        return new TenantLineInnerInterceptor(new MpTenantHandler());
    }



}
