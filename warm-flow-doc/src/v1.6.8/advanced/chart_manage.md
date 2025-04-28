# 流程图管理
<!-- @include: ../other/betweengg.md -->

## 1、追加文字

### 1.1、保存需要追加的文字
以下是在全局`分派监听器`中追加文字示例，也可以在流程或者节点监听器。

```java  {15-19}
/**
 * 分派监听器，动态修改代办任务信息
 * @param listenerVariable  监听器变量
 */
public void assignment(ListenerVariable listenerVariable) {
    log.info("全局分派监听器开始执行......");

    String defJsonStr = listenerVariable.getInstance().getDefJson();
    if (StringUtils.isNotBlank(defJsonStr)) {
        DefJson defJson = FlowEngine.jsonConvert.strToBean(defJsonStr, DefJson.class);
        for (NodeJson nodeJson : defJson.getNodeList()) {
            if (nodeJson.getNodeCode().equals(listenerVariable.getNode().getNodeCode())) {
                Long userId = Long.valueOf(listenerVariable.getFlowParams().getHandler());
                SysUser sysUser = userService.selectUserById(userId);
                if (sysUser != null && StringUtils.isNotEmpty(sysUser.getNickName())) {
                    nodeJson.getExtMap().put("办理人", sysUser.getNickName());
                }
                nodeJson.getExtMap().put("办理时间", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd")));
            }
        }
        listenerVariable.getInstance().setDefJson(FlowEngine.jsonConvert.objToStr(defJson));
    }

    log.info("全局分派监听器执行结束......");
}
```

### 1.2、追加文字

```java  {15-19}
    /**
 * 查询流程图
 *
 * @param instanceId
 * @return
 */
@GetMapping("/flowChart/{instanceId}")
public R<String> flowChart(@PathVariable("instanceId") Long instanceId) {
    return R.ok(chartService.chartIns(instanceId, (flowChartChain) -> {
        List<FlowChart> flowChartList = flowChartChain.getFlowChartList();
        flowChartList.forEach(flowChart -> {
            if (flowChart instanceof BetweenChart) {
                BetweenChart betweenChart = (BetweenChart) flowChart;
                Map<String, Object> extMap = betweenChart.getNodeJson().getExtMap();
                // 给节点顶部增加文字说明
                // betweenChart.topText("办理时间: 2025-02-08 12:12:12", Color.red);
                if (MapUtil.isNotEmpty(extMap)) {
                    for(Map.Entry<String, Object> entry : extMap.entrySet()){
                        // 给节点中追加文字
                        betweenChart.addText(entry.getKey() + ":", Color.red);
                        betweenChart.addText((String) entry.getValue(), Color.red);
                    }
                }
            }
        });
    }));
}
```

### 1.3、效果图
<div><img src="https://foruda.gitee.com/images/1740390432531294424/ce6d2ff7_2218307.png" width="800"></div>


## 2、自定义流程图节点颜色

### 2.1、配置文件
```yaml
# warm-flow工作流配置
warm-flow:
  ...
  ##流程状态对应的三元色
  chart-status-color:
    ## 未办理
    - 157,255,0
    ## 待办理
    - 0,0,0
    ## 已办理
    - 255,200,0
```

### 2.2、效果图
<div><img src="https://foruda.gitee.com/images/1740390600987335612/33073d72_2218307.png" width="800"></div>


## 3、流程图渲染规则
- 目标节点，待办
- 开启流程:
    - 初始化流程定义json
    - 其他的：未办
- 通过:
    - 途径节点和跳转线：已办，
- 退回:
    - 途径节点和跳转线：未办，
    - 目标节点之后的所有结点和跳转线：未办
- 目标节点是结束节点:
    - 目标节点和起始节点：已办
    - 所有待办：未办
- 如果办理途径存在并行网关，则需要判断是否需要生成新任务
    - 获取目标节点途径最近的并行网关集合
    - 根据目标节点递归往上找最近的并行网关，或者直到不是网关为止
    - 查询每个并行网关的所有的前置路线，记录总数量和已办理数量，如果已办数量=总数量-1，说明可以生成新任务
    - 如果前置节点是非并行网关，总数量+1，已办数量按实际情况记录
    - 如果前置节点是并行网关，递归往上找，直至找到非并行网关，记录总数量和已办数量。
