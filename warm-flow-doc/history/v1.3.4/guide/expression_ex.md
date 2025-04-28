# 表达式扩展
> [!IMPORTANT]
> 当内置的**条件、办理人变量和监听器表达式**不满足业务需求时，可进行扩展

## 1、表达式公共接口
> [!IMPORTANT]
> `ExpressionStrategy`接口，是表达式策略类接口，抽离公共方法
> 
> 1、`getType()`: 表达式策略类型，也是表达式的前缀
> 2、`isIntercept()`: 执行表达式之前，是否截取表达式前缀，然后在进行执行，默认不截取
> 3、`eval()`: 执行表达式
> 4、`setExpression()`: 设置新增的表达式，方便扩展

```java
/**
 * 表达式策略类接口
 *
 * @author warm
 */
public interface ExpressionStrategy<T> {

    /**
     * 获取策略类型
     *
     * @return 类型
     */
    String getType();

    /**
     * 是否截取表达式前缀，然后在进行执行，默认不截取
     * 如果截取，比如@@spel@@|#{@user.evalVar()} , 截取@@spel@@|，后再执行#{@user.evalVar()}解析
     *
     * @return 类型
     */
    default Boolean isIntercept() {
        return false;
    }

    /**
     * 执行表达式
     *
     * @param expression 表达式
     * @param variable   流程变量
     * @return 执行结果
     */
    T eval(String expression, Map<String, Object> variable);


    /**
     * 设置表达式
     * @param expressionStrategy 表达式
     */
    void setExpression(ExpressionStrategy<T> expressionStrategy);

}
```

## 2、注册表达式实现类
- 通过这个方法进行注册ExpressionUtil.setExpression

```java
ExpressionUtil.setExpression(new ExpressionStrategyEq());
```


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

## 4. 监听器表达式

### 4.1、监听器表达式接口
```java

/**
 * 监听器表达式策略接口
 *
 * @author warm
 */
public interface ListenerStrategy extends ExpressionStrategy<Boolean> {

    /**
     * 监听器表达式策略实现类集合
     */
    List<ExpressionStrategy<Boolean>> expressionStrategyList = new ArrayList<>();

    default void setExpression(ExpressionStrategy<Boolean> expressionStrategy) {
        expressionStrategyList.add(expressionStrategy);
    }

}
```

### 4.2、监听器表达式实现类

```java
/**
 * spel监听器表达式 #{@user.eval()}
 *
 * @author warm
 */
public class ListenerStrategySpel implements ListenerStrategy {

    @Override
    public String getType() {
        return "#";
    }

    @Override
    public Boolean eval(String expression, Map<String, Object> variable) {
        SpelHelper.parseExpression(expression, variable);
        // 恒返回true，说明匹配上监听器表达式，扩展策略也一定要返回true
        return true;
    }
}
```

## 5、条件表达式

- 扩展需要实现`ConditionStrategy`接口或者继承`ConditionStrategyAbstract`抽象类


### 5.1、条件表达式接口
```java
/**
 * 条件表达式接口
 *
 * @author warm
 */
public interface ConditionStrategy extends ExpressionStrategy<Boolean> {

    /**
     * 条件表达式策略实现类map
     */
    Map<String, ExpressionStrategy<Boolean>> map = new HashMap<>();

    default void setExpression(ExpressionStrategy<Boolean> expressionStrategy) {
        map.put(expressionStrategy.getType(), expressionStrategy);
    }

    static Map<String, ExpressionStrategy<Boolean>> getExpressionMap() {
        return map;
    }
    
    default Boolean isIntercept() {
        return true;
    }
}
```

### 5.2、条件表达式抽象类

```java
/**
 * 条件表达式抽象类，复用部分代码
 *
 * @author warm
 */
public abstract class ConditionStrategyAbstract implements ConditionStrategy {


    /**
     * 执行表达式前置方法 合法性校验
     *
     * @param split    表达式后缀：如flag@@eq@@4
     * @param variable 流程变量
     */
    public void preEval(String[] split, Map<String, Object> variable) {
        AssertUtil.isEmpty(variable, ExceptionCons.NULL_CONDITIONVALUE);
        Object o = variable.get(split[0].trim());
        AssertUtil.isNull(o, ExceptionCons.NULL_CONDITIONVALUE);
    }

    /**
     * 执行表达式
     *
     * @param expression 表达式
     * @param variable   流程变量
     * @return 执行结果
     */
    @Override
    public Boolean eval(String expression, Map<String, Object> variable) {
        String[] split = expression.split(FlowCons.splitAt);
        preEval(split, variable);
        String variableValue = String.valueOf(variable.get(split[0].trim()));
        return afterEval(split, variableValue);
    }

    /**
     * 执行表达式后置方法
     *
     * @param split 如flag@@eq@@4
     * @param value 流程变量值
     * @return 执行结果
     */
    public abstract Boolean afterEval(String[] split, String value);

}
```

### 5.3、条件表达式实现类
```java
/**
 * 条件表达式等于 @@eq@@|flag@@eq@@4
 *
 * @author warm
 */
public class ConditionStrategyEq extends ConditionStrategyAbstract {

    @Override
    public String getType() {
        return FlowCons.splitAt + "eq" + FlowCons.splitAt;
    }

    @Override
    public Boolean afterEval(String[] split, String value) {
        if (MathUtil.isNumeric(split[2].trim())) {
            return MathUtil.determineSize(value, split[2].trim()) == 0;
        } else {
            return value.equals(split[2].trim());
        }
    }

}
```
