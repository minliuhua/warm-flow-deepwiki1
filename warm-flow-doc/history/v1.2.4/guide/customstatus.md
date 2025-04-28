# 自定义流程状态

## 1、开启流程

```java
    /**
     * 新增OA 请假申请
     *
     * @param testLeave OA 请假申请
     * @return 结果
     */
    @Override
    public int insertTestLeave(TestLeave testLeave, Integer flowStatus)
    {
        String id = IdUtils.nextIdStr();
        testLeave.setId(id);
        LoginUser user = SecurityUtils.getLoginUser();
        FlowParams flowParams = FlowParams.build().flowCode(getFlowType(testLeave))
                .handler(user.getUser().getUserId().toString());
        // 流程变量
        Map<String, Object> variable = new HashMap<>();
        variable.put("testLeave", testLeave);
        variable.put("flag", String.valueOf(testLeave.getDay()));
        flowParams.variable(variable);
        // 自定义流程状态扩展
        if (Objects.nonNull(flowStatus)) {
            flowParams.setFlowStatus(flowStatus);
        }

        Instance instance = insService.start(id, flowParams);
        testLeave.setInstanceId(instance.getId());
        testLeave.setNodeCode(instance.getNodeCode());
        testLeave.setNodeName(instance.getNodeName());
        testLeave.setNodeType(instance.getNodeType());
        testLeave.setFlowStatus(instance.getFlowStatus());
        testLeave.setCreateTime(DateUtils.getNowDate());
        // 新增抄送人方法
        //TODO 发送通知
        if (StringUtils.isNotNull(testLeave.getAdditionalHandler())) {
            List<User> users = FlowFactory.userService().structureUser(instance.getId()
                    , testLeave.getAdditionalHandler(), "4");
            FlowFactory.userService().saveBatch(users);
        }


        return testLeaveMapper.insertTestLeave(testLeave);
    }
```

## 2、流程跳转

```java
        // 自定义流程状态扩展
        if (Objects.nonNull(flowStatus)) {
            flowParams.setFlowStatus(flowStatus);
        }
        Instance instance = insService.skipByInsId(testLeave.getInstanceId(), flowParams);
```

```java
        // 自定义流程状态扩展
        if (Objects.nonNull(flowStatus)) {
            flowParams.setFlowStatus(flowStatus);
        }
        Instance instance = taskService.skip(taskId, flowParams);
```

## 3、其他请查阅核心api