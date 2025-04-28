package org.dromara.warm.flow.domain;

import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 用户类
 */
@Component("user")
public class User {

    /**
     * spel条件表达：判断大于等4
     * @param flag 待判断的字符串
     * @return boolean
     */
    public boolean eval(String flag) {
        BigDecimal a = new BigDecimal(flag);
        BigDecimal b = new BigDecimal("4");
        return a.compareTo(b) > 0;
    }

    /**
     * spel办理人表达式
     * @param handler 办理人
     * @return String
     */
    public String evalVar(String handler) {
        return handler;
    }

    /**
     * spel办理人表达式
     * @param handler 办理人
     * @return String
     */
    public Long evalVarEntity(Task handler) {
        return handler.getId();
    }

    /**
     * spel监听器表达式
     * @param listenerVariable 监听器变量
     * @return String
     */
    public void notify(ListenerVariable listenerVariable) {
        System.out.println("监听器表达式" + listenerVariable);
    }
}
