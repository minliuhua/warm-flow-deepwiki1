package org.dromara.warm.flow.core.handle;


import org.dromara.warm.flow.core.handler.PermissionHandler;

import java.util.Arrays;
import java.util.List;

/**
 * 办理人权限处理器（可通过配置文件注入，也可用@Bean/@Component方式）
 *
 * @author shadow
 */
public class CustomPermissionHandler implements PermissionHandler {

    /**
     * 获取当前操作用户所有权限
     */
    @Override
    public List<String> permissions() {
        return Arrays.asList("role:1", "role:2");
    }

    /**
     * 获取当前办理人
     * @return 当前办理人
     */
    @Override
    public String getHandler() {
        return "1";
    }

}
