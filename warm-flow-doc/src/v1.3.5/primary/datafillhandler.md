# 数据填充器
> [!IMPORTANT]
> 1、如果觉得内置的id、创建时间和更新时间自动生成规则，不符合业务要求，可通过填充器覆盖

## 1、自定义填充器，并继承DataFillHandler
```java
public class CustomDataFillHandler implements DataFillHandler {

    @Override
    public void idFill(Object object) {
        RootEntity entity = (RootEntity) object;
        if (ObjectUtil.isNotNull(entity)) {
            if (Objects.isNull(entity.getId())) {
                entity.setId(IdUtils.nextId());
            }
        }
    }

    @Override
    public void insertFill(Object object) {
        RootEntity entity = (RootEntity) object;
        if (ObjectUtil.isNotNull(entity)) {
            Date date = ObjectUtil.isNotNull(entity.getCreateTime())? entity.getCreateTime() : new Date();
            entity.setCreateTime(date);
            entity.setUpdateTime(date);
        }
    }

    @Override
    public void updateFill(Object object) {
        RootEntity entity = (RootEntity) object;
        if (ObjectUtil.isNotNull(entity)) {
            entity.setUpdateTime(ObjectUtil.isNotNull(entity.getUpdateTime()) ? entity.getCreateTime() : new Date());
        }
    }
}

```

## 2、注入bean
### 2.1、通过@Component方式注入

### 2.2、yaml配置方式
```yml
# warm-flow工作流配置
warm-flow:
  # 填充器，内部有默认实现，如果不满足实际业务，可通过此配置自定义实现
  data-fill-handler-path: com.ruoyi.system.handle.CustomDataFillHandler
```

### 2.3、@Configuration+@Bean配置方式
```java
@Configuration
public class WarmFlowConfig {

    @Bean
    public DataFillHandler dataFillHandler() {
        return new CustomDataFillHandler();
    }
}
```
