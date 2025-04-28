# 办理人表达式扩展
::: tip
- 当内置的**办理人变量**不满足业务需求时，可进行扩展

:::

<!-- @include: ./expression_open.md -->

## 3、办理人表达式

- 扩展需要实现`VariableStrategy`接口, 实现`getType和eval`方法

### 3.1、办理人表达式接口
```java
/**
 * 办理人表达式策略
 *
 * @author warm
 */
public interface VariableStrategy extends ExpressionStrategy<List<String>> {

    /**
     * 办理人表达式策略实现类集合
     */
    List<ExpressionStrategy<List<String>>> expressionStrategyList = new ArrayList<>();

    default void setExpression(ExpressionStrategy<List<String>> expressionStrategy) {
        expressionStrategyList.add(expressionStrategy);
    }
}
```

### 3.2、办理人表达式实现类

```java
/**
 * 默认办理人表达式策略： ${flag}
 *
 * @author warm
 */
public class DefaultVariableStrategy implements VariableStrategy {

    @Override
    public String getType() {
        return "$";
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
