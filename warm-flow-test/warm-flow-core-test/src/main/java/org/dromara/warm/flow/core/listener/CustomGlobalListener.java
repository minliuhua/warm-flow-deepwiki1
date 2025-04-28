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
package org.dromara.warm.flow.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局监听器: 整个系统只有一个，任务开始、分派、完成和创建、时期执行
 *
 * @author warm
 * @since 2024/11/17
 */
public class CustomGlobalListener implements GlobalListener {

    private static final Logger log = LoggerFactory.getLogger(CustomGlobalListener.class);

    /**
     * 开始监听器，任务开始办理时执行
     * @param listenerVariable 监听器变量
     */
    public void start(ListenerVariable listenerVariable) {
        log.info("全局开始监听器开始执行......");

        log.info("全局开始监听器执行结束......");

    }

    /**
     * 分派监听器，动态修改代办任务信息
     * @param listenerVariable  监听器变量
     */
    public void assignment(ListenerVariable listenerVariable) {
        log.info("全局分派监听器开始执行......");

        log.info("全局分派监听器执行结束......");
    }

    /**
     * 完成监听器，当前任务完成后执行
     * @param listenerVariable  监听器变量
     */
    public void finish(ListenerVariable listenerVariable) {
        log.info("全局完成监听器开始执行......");

        log.info("全局完成监听器执行结束......");
    }

    /**
     * 创建监听器，任务创建时执行
     * @param listenerVariable  监听器变量
     */
    public void create(ListenerVariable listenerVariable) {
        log.info("全局创建监听器开始执行......");

        log.info("全局创建监听器执行结束......");
    }

}
