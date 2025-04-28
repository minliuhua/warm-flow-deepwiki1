# 自定义流程状态

## 1、开启流程

```java
    public void insertTestLeave(TestLeave testLeave, Integer flowStatus)
    {
        String id = IdUtils.nextIdStr();
        testLeave.setId(id);
        LoginUser user = SecurityUtils.getLoginUser();
        FlowParams flowParams = FlowParams.build().flowCode(getFlowType(testLeave))
                .handler(user.getUser().getUserId().toString());
        
        // 自定义流程状态扩展
        if (Objects.nonNull(flowStatus)) {
            flowParams.setFlowStatus(flowStatus);
        }

        Instance instance = insService.start(id, flowParams);
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