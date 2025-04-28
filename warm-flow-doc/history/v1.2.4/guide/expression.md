# 条件表达式

## 1、内置表达式
目前内置了大于、大于等、等于、不等于、小于、小于等于、包含、不包含，并且支持扩展

扩展需要实现ExpressionStrategy.java或者继承ExpressionStrategyAbstract.java

并且通过这个方法进行注册ExpressionUtil.setExpression

![输入图片说明](https://foruda.gitee.com/images/1703669588889979582/cbe952be_2218307.png "屏幕截图")

![输入图片说明](https://foruda.gitee.com/images/1703669685489610156/a8e6be49_2218307.png "屏幕截图")

## 2、Spring Expression Language（SpEL）
通过SPI集成SpEL，使用SpEL表达式，引入以下包，前端配置如`@@spel@@|#{@user.eval()}`表达式，即可解析
测试案例`com.warm.flow.sb.test.expression.ExpressionTest`
```xml
<dependency>
    <groupId>io.github.minliuhua</groupId>
    <artifactId>warm-flow-plugin-spel</artifactId>
</dependency>
```

```java
@Component("user")
public class User {

    public boolean eval() {
        return true;
    }
}

@Slf4j
@SpringBootTest
public class ExpressionTest extends FlowBaseTest {

    /**
     *  @@spel@@|#{@user.eval()}
     */
    @Test
    public void testSpel() {
        Map<String, Object> variable = new HashMap<>();
        variable.put("aa", "yes");
        log.info("spel结果:{}", ExpressionUtil.eval("@@spel@@|#{@user.eval()}", null));
    }
}
```

