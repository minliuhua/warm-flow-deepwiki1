# 条件表达式

## 1、内置表达式
- 1、大于
- 2、大于等于
- 3、等于
- 4、不等于
- 5、小于
- 6、小于等于
- 7、包含
- 8、不包含
- 9、SpEL
- 10、自定义表达式


## 2、Spring Expression Language（SpEL）
- 前端配置如`#{@user.eval(#flag)}`表达式，入库前要拼接前缀，方便区分表达式类型，最终为`@@spel@@|#{@user.eval(#flag)}` 
- `#flag`为变量和以下方法入参命名一致，可不设置入参

<img src="https://foruda.gitee.com/images/1727163098727096928/c29d9af5_2218307.png" width="700">

```java
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
}

/**
 * 新增OA 请假申请
 *
 * @param testLeave OA 请假申请
 * @return 结果
 */
@Override
public int insertTestLeave(TestLeave testLeave, String flowStatus)
{
    FlowParams flowParams = FlowParams.build().flowCode(getFlowType(testLeave));
    // 流程变量
    Map<String, Object> variable = new HashMap<>();
    variable.put("flag", String.valueOf(testLeave.getDay()));
    flowParams.variable(variable);

    Instance instance = insService.start(id, flowParams);
    return instance != null? 1 : 0;
}
```

## 3、自定义表达式

**1、扩展需要实现`ExpressionStrategy`接口或者继承`ExpressionStrategyAbstract`抽象类**  
**2、并且通过这个方法进行注册ExpressionUtil.setExpression**

<table>
    <tbody>
        <tr>
        <td><img src="https://foruda.gitee.com/images/1703669588889979582/cbe952be_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703669685489610156/a8e6be49_2218307.png"/></td>
    </tr>
    </tbody>
</table>
