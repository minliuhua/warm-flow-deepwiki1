# 配置文件
## 1、spring
### 1.1、yaml配置方式
```yml
# warm-flow工作流配置
warm-flow:
  # 是否开启工作流，默认true
  enabled: true
  # 是否显示banner图，默认是
  banner: true
  # 是否开启设计器ui，默认true
  ui: true
  # id生成器类型, 不填默认为orm扩展自带生成器或者warm-flow内置的19位雪花算法, SnowId14:14位，SnowId15:15位， SnowFlake19：19位
  key_type: SnowId19
  # 填充器，内部有默认实现，如果不满足实际业务，可通过此配置自定义实现
  data-fill-handler-path: com.ruoyi.system.handle.CustomDataFillHandler
  # 全局租户处理器，有多租户需要，可以配置自定义实现
  tenant_handler_path: com.ruoyi.system.handle.CustomTenantHandler
  # 是否开启逻辑删除（orm框架本身不支持逻辑删除，可通过这种方式开启）
  logic_delete: true
  # 逻辑删除字段值（开启后默认为2）
  logic_delete_value: 2
  # 逻辑未删除字段（开启后默认为0）
  logic_not_delete_value: 0
  # 当使用JPA时指定JpaPersistenceProvider
  jpa_persistence_provider: org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider
  # 内部已实现自动获取，失效时使用此配置（在使用mybatis扩展包时, 由于各数据库sql语句存在差异, 通过此配置兼容，默认为mysql）
  data_source_type: mysql
```

### 1.2、bean配置方式
```java
@Configuration
public class WarmFlowConfig {

    /**
     * 自定义填充 （可配置文件注入，也可用@Bean/@Component方式）
     */
    @Bean
    public DataFillHandler dataFillHandler() {
        return new CustomDataFillHandler();
    }

    /**
     * 全局租户处理器（可通过配置文件注入，也可用@Bean/@Component方式
     */
    @Bean
    public TenantHandler tenantHandler() {
        return new CustomTenantHandler();
    }
}

/**
 * 全局租户处理器（可通过配置文件注入，也可用@Bean/@Component方式
 *
 * @author warm
 */
public class CustomTenantHandler implements TenantHandler {


    @Override
    public String getTenantId() {
        return "000000";
    }
}

```

## 2、solon
### 2.1、yaml配置方式
```yaml
# warm-flow工作流配置
warm-flow:
  # 是否显示banner图，默认是
  banner: true
  # # 填充器，内部有默认实现，如果不满足实际业务，可通过此配置自定义实现
  #  data-fill-handler-path: org.dromara.warm.flow.core.test.handle.CustomDataFillHandler
  # 全局租户处理器（可通过配置文件注入，也可用@Bean/@Component方式
  #  tenant_handler_path: org.dromara.warm.flow.core.test.handle.CustomTenantHandler
  # 是否开启逻辑删除（orm框架本身不支持逻辑删除，可通过这种方式开启，比如jpa）
  logic_delete: false
  # 逻辑删除字段值（开启后默认为2）
  logic_delete_value: 2
  # 逻辑未删除字段（开启后默认为0）
  logic_not_delete_value: 0

# warm-flow-jpa 的hibernate jpa配置 此处属于固定配置
warm-flow-jpa:
# jpa 与 数据源绑定，此处填写数据源名称
  datasource: db1
# hibernate 配置配置
  properties:
    hibernate:
      hbm2ddl:
        auto: none
      show_sql: true
      format_sql: true
      dialect: org.hibernate.dialect.MySQL8Dialect
      connection:
        isolaction: 4 # 事务隔离级别 4 可重复度

```

### 2.1、bean配置方式
```java
@Configuration
public class WarmFlowConfig {

    /**
     * 自定义填充 （可配置文件注入，也可用@Bean/@Component方式）
     */
    @Bean
    public DataFillHandler dataFillHandler() {
        return new CustomDataFillHandler();
    }

    /**
     * 全局租户处理器（可通过配置文件注入，也可用@Bean/@Component方式
     */
    @Bean
    public TenantHandler tenantHandler() {
        return new CustomTenantHandler();
    }
}
```
