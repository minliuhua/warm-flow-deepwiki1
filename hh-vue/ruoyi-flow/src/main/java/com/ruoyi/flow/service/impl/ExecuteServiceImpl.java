package com.ruoyi.flow.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.flow.vo.WarmFlowInteractiveTypeVo;
import com.ruoyi.flow.mapper.WarmFlowMapper;
import com.ruoyi.flow.service.ExecuteService;
import com.ruoyi.flow.vo.FlowTaskVo;
import org.dromara.warm.flow.core.entity.HisTask;
import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.orm.entity.FlowHisTask;
import org.dromara.warm.flow.orm.entity.FlowTask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 流程执行SERVICEIMPL
 *
 * @author WARM
 * @since 2023/5/29 13:09
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    @Resource
    private WarmFlowMapper flowMapper;

    @Override
    public List<FlowTaskVo> toDoPage(Task task) {
        return flowMapper.toDoPage(task);
    }

    @Override
    public List<FlowHisTask> donePage(HisTask hisTask) {
        return flowMapper.donePage(hisTask);
    }

    @Override
    public List<FlowHisTask> copyPage(FlowTask flowTask) {
        return flowMapper.copyPage(flowTask);
    }

    /**
     * 根据ID反显姓名
     *
     * @param ids 需要反显姓名的用户ID
     * @return {@link List<SysUser>}
     * @author liangli
     * @date 2024/8/21 17:11
     **/
    @Override
    public List<SysUser> idReverseDisplayName(Long[] ids) {
        if (Objects.isNull(ids) || ids.length == 0) {
            return null;
        }
        return flowMapper.idReverseDisplayName(ids);
    }

    /**
     * 查询不包含输入的所有用户
     *
     * @param warmFlowInteractiveTypeVo 输入用户编号集合
     * @return 用户列表
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    @Override
    public List<SysUser> selectNotUserList(WarmFlowInteractiveTypeVo warmFlowInteractiveTypeVo) {
        return flowMapper.selectNotUserIds(warmFlowInteractiveTypeVo);
    }

    /**
     * 查询包含输入的所有用户
     *
     * @param warmFlowInteractiveTypeVo 输入用户编号集合
     * @return 用户列表
     */
    @Override
    public List<SysUser> selectUserList(WarmFlowInteractiveTypeVo warmFlowInteractiveTypeVo) {
        return flowMapper.selectUserIds(warmFlowInteractiveTypeVo);
    }
}
