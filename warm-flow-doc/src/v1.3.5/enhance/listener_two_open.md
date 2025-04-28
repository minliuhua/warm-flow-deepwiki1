# 监听器表达式扩展
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


## 3. 监听器表达式

### 3.1、监听器表达式接口
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

### 3.2、监听器表达式实现类

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
