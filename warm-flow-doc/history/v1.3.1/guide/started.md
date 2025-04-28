# 快速开始

> [!IMPORTANT] 
> **在开始之前，我们假定您已经:**
> 
> 1、熟悉 Java 环境配置及其开发
> 2、熟悉 关系型 数据库，比如 MySQL
> 3、熟悉 Spring Boot或者Solon 及相关框架
> 4、熟悉 Java 构建工具，比如 Maven

## 1、导入sql，按需求执行

- 首次导入，先创建数据库，找到对应数据库的全量脚本[warm-flow-all.sql](https://gitee.com/dromara/warm-flow/tree/master/sql/mysql)，执行  
- 如果版本更新，找到对应数据库的更新版本，比如xx-upgrade，[warm-flow_x.x.x.sql](https://gitee.com/dromara/warm-flow/tree/master/sql/mysql/v1-upgrade)，执行

<table>
    <tbody>
        <tr>
        <td><img src="https://foruda.gitee.com/images/1724349579810152906/15af22df_2218307.png" width="500px" height="180px"/></td>
        <td><img src="https://foruda.gitee.com/images/1724349629546024487/f32625d9_2218307.png" width="500px" height="180px"/></td>
    </tr>
    </tbody>
</table>

## 2、官网流程定义案例xml

[官网流程定义案例xml](https://gitee.com/dromara/warm-flow-test/tree/master/warm-flow-core-test/src/main/resources)


## 3、maven依赖
### 3.1、mybatis
**springboot2项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-sb-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**springboot3项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-sb3-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**solon项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-solon-plugin</artifactId>
      <version>1.3.1</version>
</dependency>
```

### 3.2、mybatis-plus
**springboot2项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-plus-sb-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**springboot3项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-plus-sb3-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**solon项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-plus-solon-plugin</artifactId>
      <version>1.3.1</version>
</dependency>
```

### 3.3、jpa
**warm-flow工作流配置**
```yml
# warm-flow工作流配置
warm-flow:
  # 当使用JPA时指定JpaPersistenceProvider
  jpa_persistence_provider: org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider
```

**springboot2项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-jpa-sb-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**springboot3项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-jpa-sb3-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**solon项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-plus-solon-plugin</artifactId>
      <version>1.3.1</version>
</dependency>
```

### 3.4、mybatis-flex
**springboot2项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-flex-sb-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**springboot3项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-flex-sb3-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**solon项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-mybatis-flex-solon-plugin</artifactId>
      <version>1.3.1</version>
</dependency>
```

### 3.5、easy-query
**springboot项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
      <artifactId>warm-flow-easy-query-sb-starter</artifactId>
      <version>1.3.1</version>
</dependency>
```

**solon项目**

```xml
<dependency>
      <groupId>org.dromara</groupId>
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
      <version>1.3.1</version>
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

## 6、设计器引入
> 通过jar包引入：[文档地址](./designerIntroduced.md)

## 7、代码示例

> 详细案例测试代码[warm-flow-test](https://gitee.com/dromara/warm-flow-test)项目中，warm-flow-xxx-test模块的测类

**以下为简短案例：**

### 7.1、部署流程

```java
public void deployFlow() throws Exception {
        String path = "/Users/minliuhua/Desktop/mdata/file/IdeaProjects/min/hh-vue/hh-admin/src/main/resources/leaveFlow-serial.xml";
        System.out.println("已部署流程的id：" + defService.importXml(new FileInputStream(path)).getId());
    }
```

### 7.2、发布流程

```java
public void publish() throws Exception {
        defService.publish(1212437969554771968L);
    }
```

### 7.3、开启流程

```java
public void startFlow() {
        System.out.println("已开启的流程实例id：" + insService.start("1", getUser()).getId());
    }
```

### 7.4、流程流转

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

## 8、初学者推荐学习路线

### 8.1、推荐集成案例hh-vue
[项目地址](../../common/projectexample.md)

### 8.2、推荐学习视频
[新手教学视频，社区同学录制](https://www.bilibili.com/video/BV1Ci42117pK/)
