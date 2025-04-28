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

import org.dromara.warm.flow.core.constant.FlowCons;
import org.dromara.warm.flow.core.entity.Instance;
import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.core.utils.CollUtil;
import org.dromara.warm.flow.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 流程分派监听器
 */
public class DefAssignmentListener implements Listener {

    private static final Logger log = LoggerFactory.getLogger(DefAssignmentListener.class);

    @Override
    public void notify(ListenerVariable variable) {
        log.info("流程分派监听器开始执行......");
        List<Task> tasks = variable.getNextTasks();
        Instance instance = variable.getInstance();
        if (CollUtil.isNotEmpty(tasks)) {
            for (Task task : tasks) {
                List<String> permissionList = task.getPermissionList();
                // 如果设置了发起人审批，则需要动态替换权限标识
                for (int i = 0; i < permissionList.size(); i++) {
                    String permission = permissionList.get(i);
                    if (StringUtils.isNotEmpty(permission) && permission.contains(FlowCons.WARMFLOWINITIATOR)) {
                        permissionList.set(i, permission.replace(FlowCons.WARMFLOWINITIATOR, instance.getCreateBy()));
                    }
                }
            }
        }
        log.info("流程分派监听器执行结束......");
    }
}
