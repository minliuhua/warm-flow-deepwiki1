# 设计器集成
::: tip
- 为了方便业务系统快速引入设计器，不需要搬运并且适配等工作  
- 可以按照本文中介绍的，使用设计器，并快速接入业务系统  
- 设计原理采取不分离的方式，把设计器打包的jar包中,以接口和静态资源的方式引入
:::

## 1. 引入依赖
::: code-tabs#shell

@tab:active springboot

```xml
<dependency>
  <groupId>org.dromara.warm</groupId>
  <artifactId>warm-flow-plugin-ui-sb-web</artifactId>
  <version>版本号</version>
</dependency>
```

@tab solon

```xml
<dependency>
  <groupId>org.dromara.warm</groupId>
  <artifactId>warm-flow-plugin-ui-solon-web</artifactId>
  <version>版本号</version>
</dependency>
```

:::

## 2. 后端放行部分路径
> 这个路径需要放行，否则无法访问，`/warm-flow-ui/**`, `/warm-flow/**`

::: code-tabs#shell

@tab:active spring-security

```java
@Bean
protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
{
    return httpSecurity
            .......
            // 注解标记允许匿名访问的url
            .authorizeHttpRequests((requests) -> {
                // 后端请求，静态资源，可匿名访问
                requests.antMatchers("/warm-flow-ui/**", "/warm-flow/**").permitAll()
                        // 除上面外的所有请求全部需要鉴权认证
                        .anyRequest().authenticated();
            })
            ......
            .build();
}
```

@tab sa-token

```java
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                // 以上是sa-token案例，下面才是需要排除的地址
                .excludePathPatterns("/warm-flow-ui/**", "/warm-flow/**");
    }
}
```

@tab shiro

```java
@Configuration
public class ShiroConfig {

    /**
     * Shiro过滤器配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        CustomShiroFilterFactoryBean shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
        ......
        // 后端请求，静态资源，可匿名访问
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/warm-flow-ui/**", "anon");
        filterChainDefinitionMap.put("/warm-flow/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
```

:::

## 3. 前端引入设计器
::: tip
**1、设计器页面入口是访问后端地址(前后端不分离)：`ip:port/warm-flow-ui/index.html?id=${definitionId}&disabled=${disabled}`**

:::

::: code-tabs#shell

@tab:active vue2

```vue
首先传入设计器需要的流程定义definitionId和是否可编辑disabled参数
本实例采用iframe方式嵌入设计器

<template>
  <div :style="'height:' + height">
    <iframe :src="url" style="width: 100%; height: 100%"/>
  </div>
</template>
<script>

  export default {
    name: "WarmFlow",
    data() {
      return {
        height: document.documentElement.clientHeight - 94.5 + "px;",
        url: ""
      };
    },
    mounted() {
      // process.env.VUE_APP_BASE_API: 前端地址的前缀如dev-api
      // definitionId: 为需要查询的流程定义id
      // disabled: 为是否可编辑, 例如：查看的时候不可编辑，不可保存
      this.url = process.env.VUE_APP_BASE_API + `/warm-flow-ui/index.html?id=${definitionId}&disabled=${disabled}`;
      this.iframeLoaded();
    },
    methods: {
      // iframe监听组件内设计器保存事件
      iframeLoaded() {
        window.onmessage = (event) => {
          console.log(event);
          switch (event.data.method) {
            case "close":
              this.close();
              break;
          }
        }
      },
      close() {
        const obj = {path: "/flow/definition"};
        this.$tab.closeOpenPage(obj);
      }
    }
  };
</script>
```

@tab vue3

```vue
<template>
  <div class="container" ref="container">
    <iframe ref="iframe" :src="iframeUrl" frameborder="0" width="100%" height="100%"></iframe>
  </div>
</template>

<script setup name="WarmFlow">
const { proxy } = getCurrentInstance();
import { onMounted } from 'vue';

// import.meta.env.VITE_APP_BASE_API:  前端地址的前缀如dev-api
// definitionId: 为需要查询的流程定义id
// disabled: 为是否可编辑, 例如：查看的时候不可编辑，不可保存
const iframeUrl = ref(import.meta.env.VITE_APP_BASE_API + `/warm-flow-ui/index.html?id=${definitionId}&disabled=${disabled}`);

const iframeLoaded = () => {
  // iframe监听组件内设计器保存事件
  window.onmessage = (event) => {
    switch (event.data.method) {
      case "close":
        close();
        break;
    }
  }
};

/** 关闭按钮 */
function close() {
  const obj = { path: "/flow/definition" };
  proxy.$tab.closeOpenPage(obj);
}

onMounted(() => {
  iframeLoaded();
});
</script>

<style scoped>
.container {
  width: 100%;
  height: calc(100vh - 84px);
}
</style>

```

@tab 前后端不分离

```java
可以直接访问后端接口加载页面，如：`ip:port/warm-flow-ui/index.html?id=${definitionId}&disabled=${disabled}`

@Controller
@RequestMapping("/warm-flow")
public class WarmFlowController
{
    @GetMapping()
    public String index(String definitionId, Boolean disabled)
    {
        return redirect("/warm-flow-ui/index.html?id=" + definitionId + "&disabled=" + disabled);
    }
}

或者前端直接访问后端接口，如：`/warm-flow-ui/index.html?id=1839683148936663047&disabled=false`
/*打开新的页签，加载设计器*/
function detail(dictId) {
  var url = prefix + '/detail/' + dictId;
  $.modal.openTab("字典数据", "/warm-flow-ui/index.html?id=1839683148936663047&disabled=false");
}

```

@tab React

```shell
待完善
```

:::

## 4. 设计器办理人权限数据接入
> 给任务节点设置哪些权限的人可以办理，实现接口提供给设计器

### 4.1 办理人权限选择弹框页面

<img src="https://foruda.gitee.com/images/1729094468685997422/d38c7e79_2218307.png">
<br>

### 4.2 实现接口获取以上页面办理人权限数据

#### 4.2.1 HandlerSelectService接口
```java
/**
 * 流程设计器-获取办理人权限设置列表接口
 *
 * @author warm
 */
public interface HandlerSelectService {

  /**
   * 获取办理人权限设置列表tabs页签, 如：用户、角色和部门等
   * @return tabs页签
   */
  List<String> getHandlerType();

  /**
   * 获取办理人权限设置列表结果，如：用户列表、角色列表、部门列表等
   * @param query 查询参数
   * @return 结果
   */
  List<HandlerSelectVo> getHandlerSelect(HandlerQuery query);
}

```
<br>

#### 4.2.2 HandlerSelectServiceImpl实现类

```java
/**
 * 流程设计器-获取办理人权限设置列表接口实现类
 *
 * @author warm
 */
@Component
public class HandlerSelectServiceImpl implements HandlerSelectService {
  @Autowired
  private SysUserMapper userMapper;

  @Autowired
  private SysRoleMapper roleMapper;

  @Autowired
  private SysDeptMapper deptMapper;

  /**
   * 获取办理人权限设置列表tabs页签，如：用户、角色和部门等，可以返回其中一种或者多种，按业务需求决定
   * @return tabs页签
   */
  @Override
  public List<String> getHandlerType() {
    return Arrays.asList("用户", "角色", "部门");
  }

  /**
   * 获取用户列表、角色列表、部门列表等，可以返回其中一种或者多种，按业务需求决定
   * @param query 查询参数
   * @return 结果
   */
  @Override
  public HandlerSelectVo getHandlerSelect(HandlerQuery query) {

    if ("角色".equals(query.getHandlerType())) {
      return getRole(query);
    }

    if ("部门".equals(query.getHandlerType())) {
      return getDept(query);
    }

    if ("用户".equals(query.getHandlerType())) {
      return getUser(query);
    }

    return new HandlerSelectVo();
  }

  /**
   * 获取角色列表
   *
   * @param query 查询条件
   * @return HandlerSelectVo
   */
  private HandlerSelectVo getRole(HandlerQuery query) {
        ......
    // 查询角色列表
    List<SysRole> roleList = roleMapper.selectRoleList(sysRole);
    long total = new PageInfo<>(roleList).getTotal();

    // 业务系统数据，转成组件内部能够显示的数据, total是业务数据总数，用于分页显示
    HandlerFunDto<SysRole> handlerFunDto = new HandlerFunDto<>(roleList, total)
            // 以下设置获取内置变量的Function
            .setStorageId(role -> "role:" + role.getRoleId()) // 前面拼接role:  是为了防止用户、角色的主键重复
            .setHandlerCode(SysRole::getRoleKey) // 权限编码
            .setHandlerName(SysRole::getRoleName) // 权限名称
            .setCreateTime(role -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, role.getCreateTime()));

    return getHandlerSelectVo(handlerFunDto);
  }

  /**
   * 获取用户列表
   *
   * @param query 查询条件
   * @return HandlerSelectVo
   */
  private HandlerSelectVo getDept(HandlerQuery query) {
        ......
    // 查询部门列表
    List<SysDept> deptList = deptMapper.selectDeptList(sysDept);
    long total = new PageInfo<>(deptList).getTotal();

    // 业务系统数据，转成组件内部能够显示的数据, total是业务数据总数，用于分页显示
    HandlerFunDto<SysDept> handlerFunDto = new HandlerFunDto<>(deptList, total)
            .setStorageId(dept -> "dept:" + dept.getDeptId()) // 前面拼接dept:  是为了防止用户、部门的主键重复
            .setHandlerName(SysDept::getDeptName) // 权限名称
            .setCreateTime(dept -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, dept.getCreateTime()));

    return getHandlerSelectVo(handlerFunDto);

  }

  /**
   * 获取用户列表, 同时构建左侧部门树状结构
   *
   * @param query 查询条件
   * @return HandlerSelectVo
   */
  private HandlerSelectVo getUser(HandlerQuery query) {
        ......
    // 查询用户列表
    List<SysUser> userList = userMapper.selectUserList(sysUser);
    long total = new PageInfo<>(userList).getTotal();
    // 查询部门列表，构建树状结构
    List<SysDept> deptList = deptMapper.selectDeptList(new SysDept());

    // 业务系统数据，转成组件内部能够显示的数据, total是业务数据总数，用于分页显示
    HandlerFunDto<SysUser> handlerFunDto = new HandlerFunDto<>(userList, total)
            .setStorageId(user -> user.getUserId().toString())
            .setHandlerCode(SysUser::getUserName) // 权限编码
            .setHandlerName(SysUser::getNickName) // 权限名称
            .setCreateTime(user -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, user.getCreateTime()))
            .setGroupName(user -> user.getDept() != null ? user.getDept().getDeptName() : "");

    // 业务系统机构，转成组件内部左侧树列表能够显示的数据
    TreeFunDto<SysDept> treeFunDto = new TreeFunDto<>(deptList)
            .setId(dept -> dept.getDeptId().toString()) // 左侧树ID
            .setName(SysDept::getDeptName) // 左侧树名称
            .setParentId(dept -> dept.getParentId().toString()); // 左侧树父级ID

    return getHandlerSelectVo(handlerFunDto, treeFunDto);
  }
}
```

## 5. 共享后端权限(如token)
- 后端放行路径`/warm-flow-ui/**,/warm-flow/**`，改为只放行一个`/warm-flow-ui/**`
- 在前端加载设计器页面路径后面，追加&Authorization=${token}，token是业务系统的token，可追加多个token
- yml中配置`warm-flow.token-name=Authorization`,每次请求会把token, set到header的`Authorization`上，多个token用逗号分隔
- 请注意：请求中的token的名称或者key，要和后端yml中配置一致
```yml
# warm-flow工作流配置
warm-flow:
  ......
  ## 如果需要工作流共享业务系统权限，默认Authorization，如果有多个token，用逗号分隔
  token-name: Authorization
  ......
```
