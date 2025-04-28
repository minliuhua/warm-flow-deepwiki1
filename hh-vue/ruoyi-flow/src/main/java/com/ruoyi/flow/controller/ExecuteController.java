package com.ruoyi.flow.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.service.ExecuteService;
import com.ruoyi.flow.service.HhDefService;
import com.ruoyi.flow.vo.FlowTaskVo;
import com.ruoyi.flow.vo.WarmFlowInteractiveTypeVo;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.warm.flow.core.entity.*;
import org.dromara.warm.flow.core.enums.CooperateType;
import org.dromara.warm.flow.core.enums.UserType;
import org.dromara.warm.flow.core.service.*;
import org.dromara.warm.flow.core.utils.StreamUtils;
import org.dromara.warm.flow.orm.entity.FlowHisTask;
import org.dromara.warm.flow.orm.entity.FlowTask;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**load
 * 流程实例Controller
 *
 * @author hh
 * @date 2023-04-18
 */
@Validated
@RestController
@RequestMapping("/flow/execute")
public class ExecuteController extends BaseController {
    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService deptService;

    @Resource
    private ISysRoleService roleService;

    @Resource
    private HisTaskService hisTaskService;

    @Resource
    private TaskService taskService;

    @Resource
    private NodeService nodeService;

    @Resource
    private InsService insService;

    @Resource
    private UserService flowUserservice;

    @Resource
    private ExecuteService executeService;

    @Resource
    private HhDefService hhDefService;

    /**
     * 分页待办任务列表
     */
    @PreAuthorize("@ss.hasPermi('flow:execute:toDoPage')")
    @GetMapping("/toDoPage")
    public TableDataInfo toDoPage(FlowTask flowTask) {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        List<String> permissionList = permissionList(String.valueOf(sysUser.getUserId()), sysUser.getDeptId(), sysUser);
        flowTask.setPermissionList(permissionList);
        startPage();
        List<FlowTaskVo> list = executeService.toDoPage(flowTask);
        List<Long> taskIds = StreamUtils.toList(list, FlowTaskVo::getId);
        List<User> userList = flowUserservice.getByAssociateds(taskIds);
        Map<Long, List<User>> map = StreamUtils.groupByKey(userList, User::getAssociated);
        for (FlowTaskVo taskVo : list) {
            if (StringUtils.isNotNull(taskVo)) {
                List<User> users = map.get(taskVo.getId());
                if (CollectionUtils.isNotEmpty(users)) {
                    for (User user : users) {
                        if (UserType.APPROVAL.getKey().equals(user.getType())) {
                            if (StringUtils.isEmpty(taskVo.getApprover())) {
                                taskVo.setApprover("");
                            }
                            String name = getName(user.getProcessedBy());
                            if (StringUtils.isNotEmpty(name)) taskVo.setApprover(taskVo.getApprover().concat(name).concat(";"));
                        } else if (UserType.TRANSFER.getKey().equals(user.getType())) {
                            if (StringUtils.isEmpty(taskVo.getTransferredBy())) {
                                taskVo.setTransferredBy("");
                            }
                            String name = getName(user.getProcessedBy());
                            if (StringUtils.isNotEmpty(name)) taskVo.setTransferredBy(taskVo.getTransferredBy().concat(name).concat(";"));
                        } else if (UserType.DEPUTE.getKey().equals(user.getType())) {
                            if (StringUtils.isEmpty(taskVo.getDelegate())) {
                                taskVo.setDelegate("");
                            }
                            String name = getName(user.getProcessedBy());
                            if (StringUtils.isNotEmpty(name)) taskVo.setDelegate(taskVo.getDelegate().concat(name).concat(";"));
                        }
                    }
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 分页抄送任务列表
     * author：暗影
     */
    @PreAuthorize("@ss.hasPermi('flow:execute:copyPage')")
    @GetMapping("/copyPage")
    public TableDataInfo copyPage(FlowTask flowTask) {
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        List<String> permissionList = permissionList(String.valueOf(sysUser.getUserId()), sysUser.getDeptId(), sysUser);
        flowTask.setPermissionList(permissionList);
        startPage();
        List<FlowHisTask> list = executeService.copyPage(flowTask);
        return getDataTable(list);
    }
    /**
     * 分页已办任务列表
     */
    @PreAuthorize("@ss.hasPermi('flow:execute:donePage')")
    @GetMapping("/donePage")
    public TableDataInfo donePage(FlowHisTask flowHisTask) {
        startPage();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        flowHisTask.setApprover(sysUser.getUserId().toString());
        List<FlowHisTask> list = executeService.donePage(flowHisTask);
        Map<Long, String> userMap = StreamUtils.toMap(userService.selectUserList(new SysUser())
                , SysUser::getUserId, SysUser::getNickName);
        if (CollectionUtils.isNotEmpty(list)) {
            for (FlowHisTask hisTask : list) {
                if (StringUtils.isNotEmpty(hisTask.getApprover())) {
                    String name = getName(hisTask.getApprover());
                    hisTask.setApprover(name);
                }
                if (StringUtils.isNotEmpty(hisTask.getCollaborator())) {
                    String[] split = hisTask.getCollaborator().split(",");
                    if (split.length > 1) {
                        List<String> names = new ArrayList<>();
                        for (String s : split) {
                            names.add(userMap.get(Long.valueOf(s)));
                        }
                        hisTask.setCollaborator(StringUtils.join(names, ","));
                    }
                }
            }
        }
        return getDataTable(list);
    }

    private String getName(String id) {
        Map<Long, String> userMap = StreamUtils.toMap(userService.selectUserList(new SysUser())
                , SysUser::getUserId, SysUser::getNickName);
        Map<Long, String> deptMap = StreamUtils.toMap(deptService.selectDeptList(new SysDept())
                , SysDept::getDeptId, SysDept::getDeptName);
        Map<Long, String> roleMap = StreamUtils.toMap(roleService.selectRoleAll()
                , SysRole::getRoleId, SysRole::getRoleName);
        if (StringUtils.isNotNull(id)) {
            if (id.contains("user:")) {
                String name = userMap.get(Long.valueOf(id.replace("user:", "")));
                if (StringUtils.isNotEmpty(name)) {
                    return "用户:" + name;
                }
            } else if (id.contains("dept:")) {
                String name = deptMap.get(Long.valueOf(id.replace("dept:", "")));
                if (StringUtils.isNotEmpty(name)) {
                    return "部门:" + name;
                }
            } else if (id.contains("role")) {
                String name = roleMap.get(Long.valueOf(id.replace("role:", "")));
                if (StringUtils.isNotEmpty(name)) {
                    return "角色:" + name;
                }
            } else {
                try {
                    long parseLong = Long.parseLong(id);
                    String name = userMap.get(parseLong);
                    if (StringUtils.isNotEmpty(name)) {
                        return "用户:" + name;
                    }
                } catch (NumberFormatException e) {
                    return id;
                }

            }
        }
        return "";
    }


    /**
     * 查询已办任务历史记录
     */
    @PreAuthorize("@ss.hasPermi('flow:execute:doneList')")
    @GetMapping("/doneList/{instanceId}")
    public R<List<FlowHisTask>> doneList(@PathVariable("instanceId") Long instanceId) {
        List<HisTask> flowHisTasks = hisTaskService.orderById().desc().list(new FlowHisTask().setInstanceId(instanceId));
        Map<Long, String> userMap = StreamUtils.toMap(userService.selectUserList(new SysUser())
                , SysUser::getUserId, SysUser::getNickName);
        List<FlowHisTask> flowHisTaskList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(flowHisTasks)) {
            for (HisTask hisTask : flowHisTasks) {
                if (StringUtils.isNotEmpty(hisTask.getApprover())) {
                    String name = getName(hisTask.getApprover());
                    hisTask.setApprover(name);
                }
                if (StringUtils.isNotEmpty(hisTask.getCollaborator())) {
                    String[] split = hisTask.getCollaborator().split(",");
                    if (split.length > 1) {
                        List<String> names = new ArrayList<>();
                        for (String s : split) {
                            names.add(userMap.get(Long.valueOf(s)));
                        }
                        hisTask.setCollaborator(StringUtils.join(names, ","));
                    }
                }
                FlowHisTask flowHisTask = new FlowHisTask();
                BeanUtils.copyProperties(hisTask, flowHisTask);
                flowHisTaskList.add(flowHisTask);
            }
        }
        return R.ok(flowHisTaskList);
    }

    /**
     * 根据taskId查询代表任务
     *
     * @param taskId
     * @return
     */
    @GetMapping("/getTaskById/{taskId}")
    public R<Task> getTaskById(@PathVariable("taskId") Long taskId) {
        return R.ok(taskService.getById(taskId));
    }

    /**
     * 查询跳转任意节点列表
     */
    @PreAuthorize("@ss.hasPermi('flow:execute:doneList')")
    @GetMapping("/anyNodeList/{instanceId}")
    public R<List<Node>> anyNodeList(@PathVariable("instanceId") Long instanceId) {
        Instance instance = insService.getById(instanceId);
        Node startNode = nodeService.getStartNode(instance.getDefinitionId());
        List<Node> nodeList = nodeService.suffixNodeList(startNode.getId());
        return R.ok(nodeList);
    }

    /**
     * 处理非办理的流程交互类型
     *
     * @param warmFlowInteractiveTypeVo 要转办用户
     * @return 是否成功
     */
    @PostMapping("/interactiveType")
    public R<Boolean> interactiveType(WarmFlowInteractiveTypeVo warmFlowInteractiveTypeVo) {
        return R.ok(hhDefService.interactiveType(warmFlowInteractiveTypeVo));
    }

    /**
     * 交互类型可以选择的用户
     *
     * @param warmFlowInteractiveTypeVo 交互类型请求类
     * @return 是否成功
     */
    @GetMapping("/interactiveTypeSysUser")
    public TableDataInfo interactiveTypeSysUser(WarmFlowInteractiveTypeVo warmFlowInteractiveTypeVo) {
        startPage();
        SysUser currentUser = SecurityUtils.getLoginUser().getUser();
        Long userId = currentUser.getUserId();
        Integer operatorType = warmFlowInteractiveTypeVo.getOperatorType();
        List<SysUser> list;
        Long taskId = warmFlowInteractiveTypeVo.getTaskId();
        List<User> users = flowUserservice.listByAssociatedAndTypes(taskId);
        if (!Objects.equals(CooperateType.REDUCTION_SIGNATURE.getKey(), operatorType)) {
            List<String> userIds = StreamUtils.toList(users, User::getProcessedBy);
            warmFlowInteractiveTypeVo.setUserIds(userIds);
            startPage();
            list = executeService.selectNotUserList(warmFlowInteractiveTypeVo);
        } else {
            List<String> userIds = StreamUtils.toList(users, User::getProcessedBy);
            warmFlowInteractiveTypeVo.setUserIds(userIds);
            startPage();
            list = executeService.selectUserList(warmFlowInteractiveTypeVo);
            list = StreamUtils.filter(list, sysUser -> !Objects.equals(userId, sysUser.getUserId()));
        }
        return getDataTable(list);
    }

    /**
     * 激活流程
     *
     * @param instanceId
     * @return
     */
    @GetMapping("/active/{instanceId}")
    public R<Boolean> active(@PathVariable("instanceId") Long instanceId) {
        return R.ok(insService.active(instanceId));
    }

    /**
     * 挂起流程
     *
     * @param instanceId
     * @return
     */
    @GetMapping("/unActive/{instanceId}")
    public R<Boolean> unActive(@PathVariable("instanceId") Long instanceId) {
        return R.ok(insService.unActive(instanceId));
    }

    /**
     * 获取权限
     *
     * @param userId  用户编号
     * @param deptId  部门编号
     * @param sysUser 登陆用户
     * @return 权限列表
     */
    private List<String> permissionList(String userId, Long deptId, SysUser sysUser) {
        List<SysRole> roles = sysUser.getRoles();
        List<String> permissionList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roles)) {
            permissionList = StreamUtils.toList(roles, role -> "role:" + role.getRoleId());
        }
        permissionList.add(userId);
        if (Objects.nonNull(deptId)) {
            permissionList.add("dept:" + deptId);
        }
        logger.info("当前用户所有权限[{}]", permissionList);
        return permissionList;
    }

    /**
     * 根据ID反显姓名
     *
     * @param ids 需要反显姓名的用户ID
     * @return {@link R< List<SysUser>>}
     * @author liangli
     * @date 2024/8/21 17:08
     **/
    @PreAuthorize("@ss.hasPermi('flow:definition:query')")
    @GetMapping(value = "/idReverseDisplayName/{ids}")
    public R<List<SysUser>> idReverseDisplayName(@PathVariable Long[] ids) {
        return R.ok(executeService.idReverseDisplayName(ids));
    }

}
