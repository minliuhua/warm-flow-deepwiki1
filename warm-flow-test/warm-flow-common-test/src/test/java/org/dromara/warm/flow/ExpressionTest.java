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
package org.dromara.warm.flow;


import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.FlowBaseTest;
import org.dromara.warm.flow.core.FlowEngine;
import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.dromara.warm.flow.core.utils.ExpressionUtil;
import org.dromara.warm.plugin.modes.sb.helper.SpelHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


/**
 * 表达式测试
 *
 * @author warm
 */
@Slf4j
@SpringBootTest
public class ExpressionTest extends FlowBaseTest {

    /**
     * spel测试
     */
    @Test
    public void testSpel() {

        Map<String, Object> map = new HashMap<>();
        map.put("listenerVariable", new ListenerVariable());
        log.info("SpelHelper结果:{}", SpelHelper.parseExpression("#{@user.notify(#listenerVariable)}", map));
    }

    /**
     * 条件表达式测试
     */
    @Test
    public void testCondition() {
        Map<String, Object> variable = new HashMap<>();
        variable.put("flag", "5");

        log.info("default条件表达式结果:{}", ExpressionUtil.evalCondition
                ("default@@${(flag == 5 && flag > 4) && flag > 5}", variable));

        log.info("spel条件表达式结果:{}", ExpressionUtil.evalCondition("spel@@#{@user.eval(#flag)}", variable));

        log.info("eq条件表达式结果:{}", ExpressionUtil.evalCondition("eq@@flag|4", variable));

    }


    /**
     * 监听器表达式测试
     */
    @Test
    public void testListener() {
        Map<String, Object> variable3 = new HashMap<>();
        variable3.put("listenerVariable", new ListenerVariable());
        ExpressionUtil.evalListener("#{@user.notify(#listenerVariable)}", variable3);
    }

     /**
     * 办理人表达式测试
     */
    @Test
    public void testVariable() {
        Map<String, Object> variable1 = new HashMap<>();
        variable1.put("handler", "101");
        log.info("spel办理人表达式结果:{}", ExpressionUtil.evalVariable("#{@user.evalVar(#handler)}", variable1));

        Map<String, Object> variable2 = new HashMap<>();
        variable1.put("handler", FlowEngine.newTask().setId(1L));
        log.info("spel办理人表达式结果:{}", ExpressionUtil.evalVariable("#{@user.evalVar(#handler)}", variable2));

        List<Task> addTasks = new ArrayList<>();
        addTasks.add(FlowEngine.newTask().setPermissionList(Arrays.asList("${handler1}"
                , "#{@user.evalVar(#handler2)}", "${handler3}", "#{@user.evalVar(#handler4)}"
                , "#{@user.evalVarEntity(#handler5)}", "role:1", "1")));
        Map<String, Object> variable = new HashMap<>();
        variable.put("handler1", Arrays.asList(4, "5", 100L));
        variable.put("handler2", 12L);
        variable.put("handler3", new Object[]{9, "10", 102L});
        variable.put("handler4", "15");
        Task task = FlowEngine.newTask();
        variable.put("handler5", task.setId(55L));

        ExpressionUtil.evalVariable(addTasks, variable);
        addTasks.forEach(p -> p.getPermissionList().forEach(System.out::println));
    }
}
