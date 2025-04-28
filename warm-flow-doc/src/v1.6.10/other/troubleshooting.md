# 常见问题
<!-- @include: ../other/betweengg.md -->


## **1、id精度丢失**
::: tip 方案1：处理长度过长的Long类型

- 只处理长度过长的Long类型，前端页面就不会丢失精度了 (感谢【Colin】提供解决方案)

- 创建一个BigNumberSerializer（用来处理超过JS数据范围的Long类型）

<details>
  <summary><span style="color: orangered;"><span style="color: orangered;">👇 代码详情 👇</span></span></summary>

```java
/**
 * 超出 JS 最大最小值 处理
 */
@JacksonStdImpl
public class BigNumberSerializer extends NumberSerializer {

    /**
     * 根据 JS Number.MAX_SAFE_INTEGER 与 Number.MIN_SAFE_INTEGER 得来
     */
    private static final long MAX_SAFE_INTEGER = 9007199254740991L;
    private static final long MIN_SAFE_INTEGER = -9007199254740991L;

    /**
     * 提供实例
     */
    public static final BigNumberSerializer INSTANCE = new BigNumberSerializer(Number.class);

    public BigNumberSerializer(Class<? extends Number> rawType) {
        super(rawType);
    }

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // 超出范围 序列化位字符串
        if (value.longValue() > MIN_SAFE_INTEGER && value.longValue() < MAX_SAFE_INTEGER) {
            super.serialize(value, gen, provider);
        } else {
            gen.writeString(value.toString());
        }
    }
}
```

</details>

- 添加JacksonConfig配置全局序列化（针对所有属性），一下两种方式任选其一

<details>
  <summary><span style="color: orangered;">👇 代码详情 👇</span></summary>

```java
@Configuration
public class JacksonConfig {
    
    // 方式一  
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            
            ... 其他配置....
     
            JavaTimeModule javaTimeModule = new JavaTimeModule();
          
            //Long,BigInteger  转为 String 防止 js 丢失精度
            javaTimeModule.addSerializer(Long.class, BigNumberSerializer.INSTANCE);
            javaTimeModule.addSerializer(Long.TYPE, BigNumberSerializer.INSTANCE);
            javaTimeModule.addSerializer(BigInteger.class, BigNumberSerializer.INSTANCE);
            
            builder.modules(javaTimeModule);
        };
    }
    
    // 方式二
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        final ObjectMapper objectMapper = builder.build();
    
         ... 其他配置....

        SimpleModule simpleModule = new SimpleModule();
        // Long,BigInteger 转为 String 防止 js 丢失精度
        simpleModule.addSerializer(Long.class, BigNumberSerializer.INSTANCE);
        simpleModule.addSerializer(Long.TYPE, BigNumberSerializer.INSTANCE);
        simpleModule.addSerializer(BigInteger.class, BigNumberSerializer.INSTANCE);
        objectMapper.registerModule(simpleModule);

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
```

</details>

:::


<br>

::: tip 方案2：序列化所有long类型

[如何处理Long类型精度丢失问题 👇点击👇](http://doc.ruoyi.vip/ruoyi/other/faq.html#%E5%A6%82%E4%BD%95%E5%A4%84%E7%90%86long%E7%B1%BB%E5%9E%8B%E7%B2%BE%E5%BA%A6%E4%B8%A2%E5%A4%B1%E9%97%AE%E9%A2%98)
:::


<br>

::: tip 方案3：通过yml配置
通过设置id生成器类型，修改id生成方式

```yml
# warm-flow工作流配置
warm-flow:
  # id生成器类型, 不填默认为orm扩展自带生成器或者warm-flow内置的19位雪花算法, SnowId14:14位，SnowId15:15位， SnowFlake19：19位
  key_type: SnowId19
```
:::



::: tip 方案4：自定义id生成策略
- 参照如下文章，继承填充器接口，修改id生成方式  

[Dromara Warm-Flow工作流引擎数据库主键自增策略实现👇](https://juejin.cn/post/7402110528298074152)
:::



## **2、流程图片中文乱码**
::: tip linux环境：安装中文字体
- 生成的流程图中文乱码或者报错InternalError; java.lang.reflect,InvocationTargetException  

- 由于服务器上缺少中文字体，通过检查fc-list :lang=zh是否包含中文字符集（以下是存在的示例）

```shell
[root@iZbp18ilgi6s1lkbmmfo2jZ zhFonts]# fc-list :lang=zh
/usr/share/fonts/zhFonts/SIMSUN.TTC: 新宋体,NSimSun:style=常规,Regular
/usr/share/fonts/zhFonts/SIMSUN.TTC: 宋体,SimSun:style=常规,Regular
```

[zhFonts.zip](https://gitee.com/min290/file/blob/main/warm-flow-doc/zhFonts.zip)将文件解压至/usr/share/fonts目录下，如果还不行，添加到jre的lib/fonts目录（感谢【我们好像在哪儿见过
】提供的方案）

```shell
[root@iZbp18ilgi6s1lkbmmfo2jZ fonts]# ll
总用量 8
drwxr-xr-x 2 root root 4096 5月  17 00:20 dejavu
drwxr-xr-x 2 root root 4096 5月  17 11:40 zhFonts
```
重启服务
:::


::: tip docker环境：安装中文字体
- 以ruoyi-vue-plus-4.x中dockerfile为例，添加中文字体即可  （感谢【格调@main】提供的方案）

<div><img src="https://foruda.gitee.com/images/1730708137813267165/0ff99854_2218307.png" width="700" /></div>
:::




## **3、initFlow()未加载**

::: tip
- spring开启懒加载后，导致FlowAutoConfig.initFlow()未加载。（由社区【^星^ Q】提供）  

- 删除"lazy-initialization: true",可解决问题，以下是错误示例

```yml
spring:
  main:
    allow-bean-definition-overriding: true
    lazy-initialization: true
    web-application-type: servlet
```
:::


## **4、监听器等对象获取不到**

::: tip 问题出现的原理：

- 业务系统和热加载器不是同一个(热部署插件导致)
- 设置的监听器类的全限定名错误(检查监听器类全限定名)

:::

热部署插件jrebel或者devtools导致的问题，检查是否使用热部署插件，比如spring.devtools，可以把插件关了，或者加上排除配置spring-devtools.properties

```properties
restart.include.flow=/org.dromara.warm.*.jar
```

## **5、类型转换异常**
::: tip 同上
:::


## **6、hh-vue切换mybaits-plus**

::: tip
- 1、根pom.xml，warm-flow-mybatis-sb-starter改为warm-flow-mybatis-plus-sb-starter 

- 2、ruoyi-flow的pom.xml，warm-flow-mybatis-sb-starter改为warm-flow-mybatis-plus-sb-starter  

- 3、ruoyi-common增加依赖

```xml
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.6</version>
        </dependency>
```

- 4、ruoyi-common中排除低版本jsqlparser

```xml {5-8}
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>com.github.jsqlparser</groupId>
                    <artifactId>jsqlparser</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

- 5、MyBatisConfig.java注释掉，新增MybatisPlusConfig

<details>
  <summary><span style="color: orangered;">👇 代码详情 👇</span></summary>

```java
package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis Plus 配置
 *
 * @author ruoyi
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        // 乐观锁插件
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        // 阻断插件
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 分页插件，自动识别数据库类型 https://baomidou.com/guide/interceptor-pagination.html
     */
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置数据库类型为mysql
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        return paginationInnerInterceptor;
    }

    /**
     * 乐观锁插件 https://baomidou.com/guide/interceptor-optimistic-locker.html
     */
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 如果是对全表的删除或更新操作，就会终止该操作 https://baomidou.com/guide/interceptor-block-attack.html
     */
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }
}

```

</details>

- 6、ruoyi-admin的application.yml中配置mybatis改为mybatis-plus
:::


## **7、导入依赖包失败**

::: tip 可尝试切换maven版本 （感谢【一拳打爆常大宝】）
- 如maven3.9.6切换为低版本3.8.2
:::

## **8、 StackOverflowError 错误**
::: tip
- 查看跳转线退回的，是否设置为退回类型，后续有时间再做校验

<div><img src="https://foruda.gitee.com/images/1730877942385830500/baf394aa_2218307.png" width="700"/></div>
:::

## **9、 后端接收流程xml部分丢失**
::: tip
- 一般是xxs过滤导致，排查下接口就行或者对象就行

<div><img src="https://foruda.gitee.com/images/1733466289672727602/b693f617_2218307.png" width="700"/></div>
<div><img src="https://foruda.gitee.com/images/1733466264479226712/de836c18_2218307.png" width="700"/></div>
:::

## **10、 演示项目请假类型为啥使用字典**
::: tip
因为要在一个模块中展示不同的流程案例，所以使用字典，字典映射流程定义
<div><img src="https://foruda.gitee.com/images/1742523937795259637/a55fbbda_2218307.png" width="700"/></div>
:::

## **11、 skipByIns和skip区别**

区别一：业务模块（请假申请）中能直接拿到流程实例id，因为业务表通常冗余了流程实例id，但是一般不会冗余任务id，而待办任务中最方便拿到任务id。
区别一：调用skipByIns时候，这个流程实例对应的点任务不能存在多个，否则不知道办理哪里一个，skip没有这个问题。


InsService流程实例
`skipByInsId(instanceId, flowParams)`：传入流程实例id，流程跳转
`termination(instanceId, flowParams)`：传入流程实例id，终止流程

TaskService待办任务
`skip(taskId, flowParams)`：传入流程任务id，流程跳转
`termination(taskId, flowParams)`：传入流程任务id，终止流程

