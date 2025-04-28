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
package com.ruoyi.system.listener;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import org.dromara.warm.flow.core.FlowEngine;
import org.dromara.warm.flow.core.dto.DefJson;
import org.dromara.warm.flow.core.dto.NodeJson;
import org.dromara.warm.flow.core.listener.GlobalListener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局监听器: 整个系统只有一个，任务开始、分派、完成和创建时期执行
 *
 * @author warm
 * @since 2024/11/17
 */
@Component
public class CustomGlobalListener implements GlobalListener {

    private static final Logger log = LoggerFactory.getLogger(CustomGlobalListener.class);

    @Resource
    private ISysUserService userService;

    /**
     * 开始监听器，任务开始办理时执行
     * @param listenerVariable 监听器变量
     */
    @Override
    public void start(ListenerVariable listenerVariable) {
        log.info("全局开始监听器开始执行......");

        log.info("全局开始监听器执行结束......");

    }

    /**
     * 分派监听器，动态修改代办任务信息
     * @param listenerVariable  监听器变量
     */
    @Override
    public void assignment(ListenerVariable listenerVariable) {
        log.info("全局分派监听器开始执行......");

        String defJsonStr = listenerVariable.getInstance().getDefJson();
        if (StringUtils.isNotBlank(defJsonStr)) {
            DefJson defJson = FlowEngine.jsonConvert.strToBean(defJsonStr, DefJson.class);
            for (NodeJson nodeJson : defJson.getNodeList()) {
                if (nodeJson.getNodeCode().equals(listenerVariable.getNode().getNodeCode())) {
                    Long userId = Long.valueOf(listenerVariable.getFlowParams().getHandler());
                    SysUser sysUser = userService.selectUserById(userId);
                    if (sysUser != null && StringUtils.isNotEmpty(sysUser.getNickName())) {
                        nodeJson.getExtMap().put("办理人", sysUser.getNickName());
                    }
                    nodeJson.getExtMap().put("办理时间", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd")));
                }
            }
            listenerVariable.getInstance().setDefJson(FlowEngine.jsonConvert.objToStr(defJson));
        }

        log.info("全局分派监听器执行结束......");
    }

    /**
     * 完成监听器，当前任务完成后执行
     * @param listenerVariable  监听器变量
     */
    @Override
    public void finish(ListenerVariable listenerVariable) {
        log.info("全局完成监听器开始执行......");

        log.info("全局完成监听器执行结束......");
    }

    /**
     * 创建监听器，任务创建时执行
     * @param listenerVariable  监听器变量
     */
    @Override
    public void create(ListenerVariable listenerVariable) {
        log.info("全局创建监听器开始执行......");

        log.info("全局创建监听器执行结束......");
    }

}
