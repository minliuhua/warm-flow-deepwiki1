package com.ruoyi.system.handle;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.dromara.warm.flow.core.handler.PermissionHandler;
import org.dromara.warm.flow.core.utils.StreamUtils;

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
        // 办理人权限标识，比如用户，角色，部门等, 流程设计时未设置办理人或者ignore为true可不传 [按需传输]
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = sysUser.getRoles();
        List<String> permissionList = StreamUtils.toList(roles, role -> "role:" + role.getRoleId());
        if (sysUser.getUserId() != null) {
            permissionList.add(String.valueOf(sysUser.getUserId()));
        }
        if (sysUser.getDeptId() != null) {
            permissionList.add("dept:" + sysUser.getDeptId());
        }
        return permissionList;
    }

    /**
     * 获取当前办理人
     * @return 当前办理人
     */
    @Override
    public String getHandler() {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        if (sysUser.getUserId() != null) {
            return String.valueOf(sysUser.getUserId());
        }
        return null;
    }

}
