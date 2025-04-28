# 条件表达式
> [!IMPORTANT]
> 在执行互斥网关时候，抉择是执行哪个分支，可以通过条件表达式来判断


## 1、内置表达式
- 1、大于: `@@gt@@|flag@@gt@@4`
- 2、大于等于: `@@ge@@|flag@@ge@@4`
- 3、等于: `@@eq@@|flag@@eq@@4`
- 4、不等于： `@@ne@@|flag@@ne@@4`
- 5、小于: `@@lt@@|flag@@lt@@4`
- 6、小于等于: `@@le@@|flag@@le@@4`
- 7、包含: `@@like@@|flag@@like@@4`
- 8、不包含: `@@notNike@@|flag@@notNike@@4`
- 9、SpEL: `@@spel@@|#{@user.eval(#flag)}` 
- 10、自定义表达式

## 2、匹配规则
- 1、常规匹配规则：`@@xxx@@|yyy@@xxx@@zzz`，`xxx`为表达式策略，其中`yyy`为变量，，最后的`zzz`为变量值

- 2、Spring Expression Language（SpEL）: 
前端配置如`#{@user.eval(#flag)}`表达式，入库前要拼接前缀，方便区分表达式类型，最终为`@@spel@@|#{@user.eval(#flag)}`，`#flag`为变量和以下方法入参命名一致，可不设置入参

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
