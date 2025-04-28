# 快速开始

> [!IMPORTANT] 
> **在开始之前，我们假定您已经:**
> 
> 熟悉 Java 环境配置及其开发
> 熟悉 关系型 数据库，比如 MySQL
> 熟悉 Spring Boot或者Solon 及相关框架
> 熟悉 Java 构建工具，比如 Maven

## 1、导入sql，按需求执行
```shell
如果第一次导入，请先创建数据库，找到组件中的sql目录，找到对应数据库的全量脚本warm-flow-all.sql，执行  
如果版本更新，找到对应数据库的更新版本，比如xx-upgrade，warm-flow_x.x.x.sql，执行
```
## 2、官网流程定义案例xml
```shell
地址：warm-flow\warm-flow-test\warm-flow-core-test\src\main\resources\leaveFlow-xxx.xml
```


## 3、maven依赖
### 3.1、mybatis
springboot项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-sb-starter</artifactId>
      <version>1.2.4</version>
</dependency>
```

solon项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-solon-plugin</artifactId>
      <version>1.2.4</version>
</dependency>
```

### 3.2、mybatis-plus
springboot项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-plus-sb-starter</artifactId>
      <version>1.2.4</version>
</dependency>
```

solon项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-plus-solon-plugin</artifactId>
      <version>1.2.4</version>
</dependency>
```

### 3.3、jpa
warm-flow工作流配置
```yml
# warm-flow工作流配置
warm-flow:
  # 当使用JPA时指定JpaPersistenceProvider
  jpa_persistence_provider: org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider
```

springboot项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-jpa-sb-starter</artifactId>
      <version>1.2.4</version>
</dependency>
```

solon项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-plus-solon-plugin</artifactId>
      <version>1.2.4</version>
</dependency>
```

### 3.4、mybatis-flex
springboot项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-flex-sb-starter</artifactId>
      <version>1.2.4</version>
</dependency>
```

solon项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-mybatis-flex-solon-plugin</artifactId>
      <version>1.2.4</version>
</dependency>
```

### 3.5、easy-query
springboot项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-easy-query-sb-starter</artifactId>
      <version>1.2.4</version>
</dependency>
```

solon项目

```xml
<dependency>
      <groupId>io.github.minliuhua</groupId>
      <artifactId>warm-flow-easy-query-solon-plugin</artifactId>
      <exclusions>
          <exclusion>
              <groupId>org.noear</groupId>
              <artifactId>solon.data</artifactId>
          </exclusion>
          <exclusion>
              <groupId>org.noear</groupId>
              <artifactId>solon.logging</artifactId>
          </exclusion>
      </exclusions>
      <version>1.2.4</version>
</dependency>
```

## 4、支持数据库类型

* [x] mysql
* [x] oracle
* [x] postgresql
* [ ] 达梦
* [ ] 人大金仓
* [ ] GaussDB
* [ ] oceanbase
* [ ] sqlserver
* [ ] ......


## 5、支持orm框架类型
* [x] mybatis
* [x] mybatis-plus
* [x] jpa
* [x] easy-query
* [x] mybatis-flex
* [ ] ......



> **有想扩展其他orm框架和数据库的可加qq群联系群主**

## 6、代码示例

> 测试代码在warm-flow中warm-flow-test目录下，warm-flow-xxx-test模块的测类


## 7、部署流程

```java
public void deployFlow() throws Exception {
        String path = "/Users/minliuhua/Desktop/mdata/file/IdeaProjects/min/hh-vue/hh-admin/src/main/resources/leaveFlow-serial.xml";
        System.out.println("已部署流程的id：" + defService.importXml(new FileInputStream(path)).getId());
    }
```

## 8、发布流程

```java
public void publish() throws Exception {
        defService.publish(1212437969554771968L);
    }
```

## 9、开启流程

```java
public void startFlow() {
        System.out.println("已开启的流程实例id：" + insService.start("1", getUser()).getId());
    }
```

## 10、流程流转

```java
public void skipFlow() throws Exception {
        // 通过实例id流转
        Instance instance = insService.skipByInsId(1219286332141080576L, getUser().skipType(SkipType.PASS.getKey())
                .permissionFlag(Arrays.asList("role:1", "role:2")));
        System.out.println("流转后流程实例：" + instance.toString());

//        // 通过任务id流转
//        Instance instance = insService.skip(1219286332145274880L, getUser().skipType(SkipType.PASS.getKey())
//                .permissionFlag(Arrays.asList("role:1", "role:2")));
//        System.out.println("流转后流程实例：" + instance.toString());
    }

 public void skipAnyNode() throws Exception {
        // 跳转到指定节点
        Instance instance = insService.skip(1219286332145274880L, getUser().skipType(SkipType.PASS.getKey())
                .permissionFlag(Arrays.asList("role:1", "role:2")).nodeCode("4"));
        System.out.println("流转后流程实例：" + instance.toString());
    }
```
