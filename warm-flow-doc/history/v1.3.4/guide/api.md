# 核心api

## 1、DefService流程定义接口

### 1.1、新增流程定义表数据，新增后需要通过saveXml接口保存流程节点和流程跳转数据
`checkAndSave(definition)`：校验后新增

### 1.2、新增流程定义，并初始化流程节点和流程跳转数据
`saveAndInitNode(definition)`：校验后新增

### 1.3、导入流程定义、流程节点和流程跳转数据
`importXml(is)`：导入流程定义xml的输入流

### 1.4、保存流程节点和流程跳转数据
`saveXml(def)`： 传入流程定义id、流程定义xml字符串
- id: 流程定义id [必传]
- xmlString: 流程定义xml字符串 [必传]

### 1.5、保存流程节点和流程跳转数据
`saveXml(id, xmlString)`： 传入流程定义id、流程定义xml字符串
- id: 流程定义id
- xmlString: 流程定义xml字符串

### 1.6、导出流程定义
`exportXml(id)`： 导出流程定义(流程定义、流程节点和流程跳转数据)xml的Document对象  

### 1.7、获取流程定义
`xmlString(id)`： 获取流程定义xml(流程定义、流程节点和流程跳转数据)的字符串  

### 1.8、删除
`removeDef(ids)`： 删除流程定义相关数据  

### 1.9、发布
`publish(id)`： 发布流程定义  

### 1.10、取消发布
`unPublish(id)`： 取消发布流程定义  

### 1.11、复制流程
`copyDef(id)`： 复制流程定义   

### 1.12、获取流程图
`flowChart(instanceId)`： 获取流程图的图片流

### 1.13、激活流程
`active(Long id)`： 激活流程

### 1.14、挂起流程
`unActive(Long id)`： 挂起流程：流程定义挂起后，相关的流程实例都无法继续流转

## 2、InsService流程实例接口

### 2.1、开启流程
`start(businessId, flowParams)`：传入业务id，开启流程实例。flowParams包含如下字段：
- flowCode: 流程编码 [必传]
- handler: 办理人唯一标识 [建议传]
- variable: 流程变量 [按需传输]
- ext: 扩展字段，预留给业务系统使用 [按需传输]
- flowStatus: 流程状态，自定义流程状态[按需传输]

### 2.2、流程跳转
`skipByInsId(instanceId, flowParams)`：传入流程实例id，流程跳转。flowParams包含如下字段：
- skipType: 跳转类型(PASS审批通过 REJECT退回) [必传]
- nodeCode: 节点编码,如果指定节点,可任意跳转到对应节点，严禁任意退回选择后置节点 [按需传输]
- permissionFlag: 办理人权限标识，比如用户，角色，部门等, 流程设计时未设置办理人或者ignore为true可不传 [按需传输]
- message: 审批意见 [按需传输]
- handler: 办理人唯一标识 [建议传]
- variable: 流程变量 [按需传输]
- flowStatus: 流程状态，自定义流程状态 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 2.3、终止流程
`termination(instanceId, flowParams)`：传入流程实例id，终止流程。flowParams包含如下字段：
- handler: 办理人唯一标识 [建议传]
- message: 审批意见 [按需传输]
- flowStatus: 流程状态，自定义流程状态 [按需传输]
- permissionFlag: 办理人权限标识，比如用户，角色，部门等，不传不校验权限 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 2.4、删除流程实例
`remove(instanceIds)`：根据实例ids，删除流程

### 2.5、激活实例
`active(Long id)`： 激活实例

### 2.6、挂起实例
`unActive(Long id)`： 挂起实例，流程实例挂起后，该流程实例无法继续流转

### 2.7、根据流程定义id集合，查询流程实例集合
`listByDefIds(defIds)`：根据流程定义id集合，查询流程实例集合

## 3、TaskService待办任务接口

### 3.1、流程跳转
`skip(taskId, flowParams)`：传入流程任务id，流程跳转。flowParams包含如下字段：
- skipType: 跳转类型(PASS审批通过 REJECT退回) [必传]
- nodeCode: 节点编码，如果指定节点,可任意跳转到对应节点，严禁任意退回选择后置节点 [按需传输]
- permissionFlag: 办理人权限标识，比如用户，角色，部门等, 流程设计时未设置办理人或者ignore为true可不传 [按需传输]
- message: 审批意见 [按需传输]
- handler: 办理人唯一标识 [建议传]
- variable: 流程变量 [按需传输]
- flowStatus: 流程状态，自定义流程状态 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 3.2、终止流程
`termination(taskId, flowParams)`：传入流程任务id，终止流程。flowParams包含如下字段：
- message: 审批意见 [按需传输]
- handler: 办理人唯一标识 [建议传]
- flowStatus: 流程状态，自定义流程状态 [按需传输]
- permissionFlag: 办理人权限标识，比如用户，角色，部门等，不传不校验权限 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 3.3、转办
`transfer(taskId, flowParams)`：转办, 默认删除当然办理用户权限，转办后，当前办理不可办理。flowParams包含如下字段：
- handler: 当前办理人唯一标识 [必传]
- permissionFlag: 用户所拥有的权限标识[按需传输，ignore为false，则必传]
- addHandlers: 转办对象 [必传]
- message: 审批意见 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 3.4、委派
`depute(taskId, flowParams)`：委派, 默认删除当然办理用户权限，委派后，当前办理不可办理。flowParams包含如下字段：
- handler: 当前办理人唯一标识 [必传]
- permissionFlag: 用户所拥有的权限标识[按需传输，ignore为false，则必传]
- addHandlers: 委托对象 [必传]
- message: 审批意见 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 3.5、加签
`addSignature(taskId, flowParams)`：加签，增加办理人。flowParams包含如下字段：
- handler: 当前办理人唯一标识 [必传]
- permissionFlag: 用户所拥有的权限标识[按需传输，ignore为false，则必传]
- addHandlers: 加签对象 [必传]
- message: 审批意见 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 3.6、减签
`reductionSignature(taskId, flowParams)`：减签，减少办理人。flowParams包含如下字段：
- handler: 当前办理人唯一标识 [必传]
- permissionFlag: 用户所拥有的权限标识[按需传输，ignore为false，则必传]
- reductionHandlers: 减少办理人 [必传]
- message: 审批意见 [按需传输]
- ignore: 忽略权限校验（比如管理员不校验），默认不忽略 [按需传输]

### 3.7、修改办理人
`updateHandler(taskId, flowParams)`：传入流程任务id，修改办理人
- handler: 办理人唯一标识 [按需传输]
- permissionFlag: 用户所拥有的权限标识 [按需传输，ignore为false，则必传]
- addHandlers: 增加办理人：加签，转办，委托 [按需传输]
- reductionHandlers: 减少办理人：减签，委托 [按需传输]
- message: 审批意见 [按需传输]
- cooperateType: 协作方式(2转办 3委派 6加签 7减签）[按需传输]
- ignore: 转办忽略权限校验（true：忽略，false：不忽略）[按需传输]

## 4、NodeService节点接口
### 4.1、获取下一个节点列表
`getNextNodeList(definitionId, nowNodeCode, anyNodeCode, skipType, variable)`：根据流程定义和当前节点code获取下一节点,如是网关跳过取下一节点,并行网关返回多个节点
- definitionId: 流程定义id [必传]
- nowNodeCode: 当前节点code [必传]
- skipType: 跳转类型（PASS审批通过 REJECT退回） [必传]
- anyNodeCode: anyNodeCode不为空，则可跳转anyNodeCode节点（优先级最高） [按需传输]
- variable: 流程变量,下一个节点是网关需要判断跳转条件,并行网关返回多个节点 [按需传输]

## 5、公共api接口
### 5.1、根据id查询
`getById(id)`：根据id查询
- id: 主键

### 5.2、根据ids主键集合查询
`getByIds(ids)`：根据ids主键集合查询
- ids: 主键集合

### 5.3、分页查询
`getById(entity, page)`：分页查询
- entity: 查询实体
- page: 分页对象，支持设置排序字段

### 5.4、查询列表
`list(entity)`：查询列表
- entity: 查询实体

### 5.5、查询列表，可排序
`list(entity, query)`：查询列表，可排序
- entity: 查询实体
- query: 查询代理层处理，支持设置排序字段

### 5.6、查询一条记录
`getOne(entity)`：查询一条记录
- entity 查询实体

### 5.7、获取总数量
`selectCount(entity)`：获取总数量
- entity: 查询实体

### 5.8、判断是否存在
`exists(entity)`：判断是否存在
- entity: 查询实体

### 5.8、新增
`save(entity)`：新增
- entity: 实体

### 5.9、根据id修改
`updateById(entity)`：根据id修改
- entity: 实体

### 5.10、根据id删除
`removeById(id)`：根据id删除
- id: 实体

### 5.11、根据entity删除
`remove(entity)`：根据entity删除
- entity: 实体

### 5.12、根据ids批量删除
`removeByIds(ids)`：根据ids批量删除
- ids: 实体

### 5.13、批量新增
`saveBatch(list)`：批量新增
- list: 实体集合

### 5.14、批量新增
`saveBatch(list, batchSize)`：批量新增
- list: 需要插入的集合数据
- batchSize: 插入大小

### 5.15、批量更新
`updateBatch(list)`：批量更新
- list: 集合数据

### 5.16、id设置正序排列
`orderById()`：id设置正序排列

### 5.17、创建时间设置正序排列
`orderByCreateTime()`：创建时间设置正序排列

### 5.18、更新时间设置正序排列
`orderByUpdateTime()`：更新时间设置正序排列

### 5.19、设置正序排列
`orderByAsc(orderByField)`：设置正序排列
- orderByField: 排序字段

### 5.20、设置倒序排列
`orderByDesc(orderByField)`：设置倒序排列
- orderByField: 排序字段

### 5.21、用户自定义排序方案
`orderBy(orderByField)`：用户自定义排序方案
- orderByField: 排序字段
