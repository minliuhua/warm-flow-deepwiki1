# 常见问题

## 1、id精度丢失    
此项目目前使用的是雪花算法生成id，可能导致前端页面获取丢失精度（感谢【luoheyu】提供测试意见）    
按照这个把long序列化成字符串，前端页面就不会丢失精度了，获取查看hh-vue项目如何处理
http://doc.ruoyi.vip/ruoyi/other/faq.html#%E5%A6%82%E4%BD%95%E5%A4%84%E7%90%86long%E7%B1%BB%E5%9E%8B%E7%B2%BE%E5%BA%A6%E4%B8%A2%E5%A4%B1%E9%97%AE%E9%A2%98

## 2、流程图片中文乱码
生成的流程图中文乱码或者报错InternalError; java.lang.reflect,InvocationTargetException  
由于服务器上缺少中文字体，通过检查fc-list :lang=zh是否包含中文字符集（以下是存在的示例）
```shell
[root@iZbp18ilgi6s1lkbmmfo2jZ zhFonts]# fc-list :lang=zh
/usr/share/fonts/zhFonts/SIMSUN.TTC: 新宋体,NSimSun:style=常规,Regular
/usr/share/fonts/zhFonts/SIMSUN.TTC: 宋体,SimSun:style=常规,Regular
```
[zhFonts.zip](https://gitee.com/warm_4/warm-flow-doc/blob/main/file/zhFonts.zip)将文件解压至/usr/share/fonts目录下，如果还不行，添加到jre的lib/fonts目录（感谢【我们好像在哪儿见过
】提供的方案）
```shell
[root@iZbp18ilgi6s1lkbmmfo2jZ fonts]# ll
总用量 8
drwxr-xr-x 2 root root 4096 5月  17 00:20 dejavu
drwxr-xr-x 2 root root 4096 5月  17 11:40 zhFonts
```
重启服务

## 3、FlowAutoConfig.initFlow()未加载
spring开启懒加载后，导致FlowAutoConfig.initFlow()未加载。（由社区【^星^ Q】提供）  
删除"lazy-initialization: true",可解决问题，以下是错误示例

```yml
spring:
  main:
    allow-bean-definition-overriding: true
    lazy-initialization: true
    web-application-type: servlet
``` 

## 4、类型转换异常
出现类型转换异常，检查是否使用热部署插件，比如spring.devtools，可以把插件关了，或者加上排除配置spring-devtools.properties
```properties
restart.include.flow=/io.github.minliuhua.*.jar
```

## 5、hh-vue切换mybaits-plus
1、根pom.xml，warm-flow-mybatis-sb-starter改为warm-flow-mybatis-plus-sb-starter  
2、ruoyi-flow的pom.xml，warm-flow-mybatis-sb-starter改为warm-flow-mybatis-plus-sb-starter  
3、ruoyi-common增加依赖
```java
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
```
4、MyBatisConfig.java注释掉，新增MybatisPlusConfig
<details>
  <summary>点击查看代码</summary>

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
public class MybatisPlusConfig
{
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
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
    public PaginationInnerInterceptor paginationInnerInterceptor()
    {
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
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor()
    {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 如果是对全表的删除或更新操作，就会终止该操作 https://baomidou.com/guide/interceptor-block-attack.html
     */
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor()
    {
        return new BlockAttackInnerInterceptor();
    }
}

```

</details>

5、ruoyi-admin的application.yml中配置mybatis改为mybatis-plus  