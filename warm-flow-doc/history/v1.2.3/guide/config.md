# 配置文件

```yml
# warm-flow工作流配置
warm-flow:
  # 是否显示banner图，默认是
  banner: true
  # # 填充器，内部有默认实现，如果不满足实际业务，可通过此配置自定义实现
  data-fill-handler-path: com.ruoyi.system.handle.CustomDataFillHandler
  # 全局租户处理器（可通过配置文件注入，也可用@Bean/@Component方式
  tenant_handler_path: com.ruoyi.system.handle.CustomTenantHandler
  # 是否开启逻辑删除（orm框架本身不支持逻辑删除，可通过这种方式开启，比如jpa）
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