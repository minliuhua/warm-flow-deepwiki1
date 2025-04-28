# 办理人变量设置

## 1、内置表达式
- 1、默认办理人变量策略: `@@default@@|${handler1}`
- 2、spel办理人变量策略: `@@spel@@|#{@user.evalVar(#handler2)}`
- 3、@@xxx@@: 标识不同策略的前缀

## 2、变量替换时机
- 1、上一个节点任务办理时，传入变量
- 2、下一个节点任务生成时即可获取替换  

> 比如B-->C, C任务设置办理人变量为`@@default@@|${handler1}`，B任务办理时传入变量`handler1=100`，则C节点办理人变量为100


## 3、默认办理人变量策略

### 前端页面设置变量
- 比如：`@@default@@|${handler1}`
- `@@default@@|${handler1}`中@@default@@表示默认办理人变量策略，handler1是需要被流程变量中替换的标识


<img src="https://foruda.gitee.com/images/1727164067302855332/04f4b2ca_2218307.png"  width="700">



### 后端代码设置变量
```java

// 流程变量
Map<String, Object> variable = new HashMap<>();
variable.put("handler1", "100");
flowParams.variable(variable);

Instance instance = insService.skipByInsId(testLeave.getInstanceId(), flowParams);
```

## 4、spel办理人变量策略

### 前端页面设置变量
- 比如：`@@spel@@|#{@user.evalVar(#handler2)}`
- `#{@user.evalVar(#handler2)}`是spel表达式，`#handler2`是方法入参变量，可以不设置



<img src="https://foruda.gitee.com/images/1727164084637385718/6b68c042_2218307.png"  width="700">



### 后端代码设置变量
```java
/**
 * 用户类
 */
@Component("user")
public class User {

    /**
     * spel办理人表达式
     * @param handler2 办理人
     * @return String
     */
    public String evalVar(String handler2) {
        return handler2;
    }
}

// 流程变量
Map<String, Object> variable = new HashMap<>();
variable.put("handler2", "101");
flowParams.variable(variable);

Instance instance = insService.skipByInsId(testLeave.getInstanceId(), flowParams);
```

## 5、扩展

- 扩展需要实现`VariableStrategy`接口, 实现`getType和eval`方法
- 并且通过这个方法进行注册VariableUtil.setVariable

```java
/**
 * 办理人表达式策略
 *
 * @author warm
 */
public interface VariableStrategy {

    /**
     * 获取变量替换类型
     * @return 变量替换类型
     */
    String getType();

    /**
     * 执行表达式
     * @param expression 表达式
     * @param variable 流程变量
     * @return 执行结果
     */
    String eval(String expression, Map<String, Object> variable);
}



/**
 * 默认办理人表达式策略： @@default@@|${flag}
 *
 * @author warm
 */
public class DefaultVariableStrategy implements VariableStrategy {

    @Override
    public String getType() {
        return FlowCons.splitAt + "default" + FlowCons.splitAt;
    }

    @Override
    public String eval(String expression, Map<String, Object> variable) {
        if (StringUtils.isEmpty(expression) || MapUtil.isEmpty(variable)) {
            return null;
        }
        String result = expression.replace("${", "").replace("}", "");
        Object o = variable.get(result);
        if (ObjectUtil.isNotNull(o)) {
            String variableStr = (String) o;
            if (StringUtils.isEmpty(variableStr)) {
                return null;
            }
            return variableStr;
        }
        return null;
    }
}
```
