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
package org.dromara.warm.flow.core;


import org.dromara.warm.flow.core.dto.FlowParams;
import org.dromara.warm.flow.core.entity.Definition;
import org.dromara.warm.flow.core.entity.Instance;
import org.dromara.warm.flow.core.entity.Node;
import org.dromara.warm.flow.core.enums.SkipType;
import org.dromara.warm.flow.core.service.DefService;
import org.dromara.warm.flow.core.service.InsService;
import org.dromara.warm.flow.core.service.NodeService;
import org.dromara.warm.flow.core.service.TaskService;
import org.dromara.warm.flow.core.utils.page.Page;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class FlowBaseTest {

    public String getFlowCode() {
        return "serial55";
    }

    public Long getInsId() {
        return 196379784727543808L;
    }

    public Long getTaskId() {
        return 1266424585176354816L;
    }

    public String getBusinessId() {
        return "3";
    }

    public FlowParams getUser() {
        return FlowParams.build().flowCode(getFlowCode())
                .handler("1")
                .skipType(SkipType.PASS.getKey())
                .permissionFlag(Arrays.asList("role:1", "role:2"));
    }

    /**
     * 部署流程
     */
    public void deployFlow(DefService defService) throws Exception {
        URL resource = getClass().getClassLoader().getResource("leaveFlow-serial-内部测试用.json");
        if (resource == null) {
            throw new RuntimeException("无法找到资源文件: leaveFlow-serial-内部测试用.json");
        }
        // 解码路径中的中文字符
        String path = URLDecoder.decode(resource.getPath(), StandardCharsets.UTF_8.toString());
        System.out.println("已部署流程的id：" + defService.importIs(new FileInputStream(path)).getId());

    }

    public Long getTestDefId(DefService defService) {
        ArrayList<String> flowCodeList = new ArrayList<>();
        flowCodeList.add(getFlowCode());
        return defService.queryByCodeList(flowCodeList).stream().findFirst().map(Definition::getId).orElse(0L);
    }

    /**
     * 发布流程
     */
    public void publish(DefService defService) {

        defService.publish(getTestDefId(defService));
    }

    /**
     * 激活流程
     */
    public void active(DefService defService) {
        defService.active(getTestDefId(defService));
    }

    /**
     * 挂起流程
     */
    public void unActive(DefService defService){
        defService.unActive(getTestDefId(defService));
    }

    /**
     * 取消流程
     */
    public void unPublish(DefService defService) {
        defService.unPublish(getTestDefId(defService));
    }

    /**
     * 获取流程定义
     */
    public void queryDesign(DefService defService) {
        System.out.println("获取流程定义：" + defService.queryDesign(getTestDefId(defService)));
    }

    /**
     * 删除流程定义
     */
    public void removeDef(DefService defService) {
        defService.removeDef(Collections.singletonList(getTestDefId(defService)));
    }

    /**
     * 开启流程
     */
    public void startFlow(InsService insService, TaskService taskService) {
        Instance instance = insService.start(getBusinessId(), getUser());
        System.out.println("已开启的流程实例id：" + instance.getId());
        taskService.list(FlowEngine.newTask().setInstanceId(instance.getId()))
                .forEach(task -> System.out.println("流转后任务id实例：" + task.getId()));
    }

    /**
     * 删除流程实例
     */
    public void removeIns(InsService insService) {
        insService.remove(Collections.singletonList(2L));
    }

    /**
     * 激活流程实例
     */
    public void activeIns(InsService insService) {
        insService.active(getInsId());
    }

    /**
     * 挂起流程实例
     */
    public void unActiveIns(InsService insService) {
        insService.unActive(getInsId());
    }

    /**
     * 办理
     */
    public void skipFlow(InsService insService, TaskService taskService) {
        // 通过实例id流转
        FlowParams flowParams = getUser().skipType(SkipType.PASS.getKey())
                .permissionFlag(Arrays.asList("role:1", "role:2"));
        Map<String, Object> variable = new HashMap<>();
        variable.put("testLeave", new HashMap<>());
        variable.put("flag", "1");
        flowParams.variable(variable);
        Instance instance = taskService.skipByInsId(getInsId(), flowParams);
        System.out.println("流转后流程实例：" + instance.toString());

//        // 通过任务id流转
//        Instance instance = taskService.skip(getTaskId(), getUser().skipType(SkipType.PASS.getKey())
//                .permissionFlag(Arrays.asList("role:1", "role:2")));
        System.out.println("流转后流程实例：" + instance.toString());
        taskService.list(FlowEngine.newTask().setInstanceId(instance.getId()))
                .forEach(task -> System.out.println("流转后任务id实例：" + task.getId()));
    }

    /**
     * 终止流程实例
     */
    public void termination(TaskService taskService) {

        FlowParams flowParams = new FlowParams();
        flowParams.message("终止流程").handler("1");
        taskService.termination(1260200517360029696L, flowParams);
    }

    /**
     * 跳转到指定节点 跳转到结束节点
     */
    public void skipAnyNode(TaskService taskService) {
        Instance instance = taskService.skip(1260200765054652416L, getUser().skipType(SkipType.PASS.getKey())
                .permissionFlag(Arrays.asList("role:1", "role:2")).nodeCode("5"));
        System.out.println("流转后流程实例：" + instance.toString());
    }

    /**
     * 分页
     */
    public void page(DefService defService){
        Definition flowDefinition = FlowEngine.newDef();
        Page<Definition> page = Page.pageOf(1, 10);
        page = defService.orderByCreateTime().desc().page(flowDefinition, page);
        List<Definition> list = page.getList();
        list.forEach(System.out::println);
    }

    /**
     * 转办
     */
    public void transfer(TaskService taskService) {

         taskService.transfer(getTaskId(), new FlowParams()
                         .handler("1")
                         .permissionFlag(Arrays.asList("role:1", "role:2", "user:1"))
                         .addHandlers(Arrays.asList("1","2"))
                         .message("转办"));
    }

    /**
     * 委派
     */
    public void depute(TaskService taskService){
        taskService.transfer(getTaskId(), new FlowParams()
                        .handler("1")
                        .permissionFlag(Arrays.asList("role:1", "role:2", "user:1"))
                        .addHandlers(Arrays.asList("1","2"))
                        .message("委派"));
    }

    /**
     * 加签
     */
    public void addSignature(TaskService taskService){
        taskService.addSignature(getTaskId(), new FlowParams()
                        .handler("1")
                        .permissionFlag(Arrays.asList("role:1", "role:2", "user:1"))
                        .addHandlers(Arrays.asList("1","2"))
                        .message("加签"));
    }

    /**
     * 减签
     */
    public void reductionSignature(TaskService taskService){
        taskService.reductionSignature(getTaskId(), new FlowParams()
                        .handler("1")
                        .permissionFlag(Arrays.asList("role:1", "role:2", "user:1"))
                        .reductionHandlers(Arrays.asList("1","2"))
                        .message("减签"));
    }

    /**
     * 获取下面的节点
     */
    public void getNextNodeList(NodeService nodeService) {
        // Long definitionId, String nowNodeCode, String nextNodeCode, String skipType, Map<String, Object> variable
        Map<String, Object> variable = new HashMap<>();
        variable.put("abc", "abc");
        List<Node> nextNodeList = nodeService.getNextNodeList(1277309492450693120L, "4", "", "", null);
        System.out.print("下面的节点：");
        nextNodeList.stream().map(Node::getNodeName).forEach(System.out::println);
    }

    /**
     * 获取前置和后置的节点
     */
    public void previousNodeList(NodeService nodeService) {
        List<Node> previousNodeList = nodeService.previousNodeList(1328315500865916942L);
        System.out.print("所有的前置节点：");
        previousNodeList.stream().map(Node::getNodeName).forEach(System.out::println);
        List<Node> suffixNodeList = nodeService.suffixNodeList(1328315500865916942L);
        System.out.print("所有的后置节点：");
        suffixNodeList.stream().map(Node::getNodeName).forEach(System.out::println);
    }

}
