# 核心api

## 1、DefService流程定义接口

### 1.1、导入流程定义
`importXml(is)`：导入流程定义xml的输入流is，保存流程定义数据，返回流程定义对象

### 1.2、保存流程定义
`saveXml(def))`： 传入流程定义id、流程定义xml字符串，保存流程定义数据  

### 1.3、导出流程定义
`exportXml(id)`： 导出流程定义xml的Document对象  

### 1.4、获取流程定义
`xmlString(id)`： 获取流程定义xml的字符串  

### 1.5、删除
`removeDef(ids)`： 删除流程定义相关数据  

### 1.6、发布
`publish(id)`： 发布流程定义  

### 1.7、取消发布
`unPublish(id)`： 取消发布流程定义  

### 1.8、复制流程
`copyDef(id)`： 复制流程定义   

### 1.9、获取流程图
`flowChart(instanceId)`： 获取流程图的图片流

### 1.10、激活流程
`active(Long id)`： 激活流程

### 1.11、挂起流程
`unActive(Long id)`： 挂起流程：流程定义挂起后，相关的流程实例都无法继续流转

## 2、InsService流程实例接口

### 2.1、开启流程
`start(businessId, flowParams)`：传入业务id，开启流程实例。flowParams包含如下字段：
- flowCode:流程编码 [必传]
- handler:办理人唯一标识 [建议传]
- variable:流程变量 [按需传输]
- ext:扩展字段，预留给业务系统使用 [按需传输]
- flowStatus:流程状态，自定义流程状态[按需传输]

### 2.2、流程跳转
`skipByInsId(instanceId, flowParams)`：传入流程实例id，流程跳转。flowParams包含如下字段：
- skipType:跳转类型(PASS审批通过 REJECT退回) [必传]
- nodeCode:节点编码 [如果指定跳转节点,必传]
- permissionFlag:办理人权限标识 [按需传输]
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]
- variable:流程变量 [按需传输]
- flowStatus:流程状态，自定义流程状态[按需传输]

### 2.3、终止流程
`termination(instanceId, flowParams)`：传入流程实例id，终止流程。flowParams包含如下字段：
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]
- flowStatus:流程状态，自定义流程状态[按需传输]

### 2.4、删除流程实例
`remove(instanceIds)`：根据实例ids，删除流程

### 2.5、激活实例
`active(Long id)`： 激活实例

### 2.6、挂起实例
`unActive(Long id)`： 挂起实例，流程实例挂起后，该流程实例无法继续流转

## 3、TaskService待办任务接口

### 3.1、流程跳转
`skip(taskId, flowParams)`：传入流程实例id，流程跳转。flowParams包含如下字段：
- skipType:跳转类型(PASS审批通过 REJECT退回) [必传]
- nodeCode:节点编码 [如果指定跳转节点,必传]
- permissionFlag:办理人权限标识 [按需传输]
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]
- variable:流程变量 [按需传输]
- flowStatus:流程状态，自定义流程状态[按需传输]

### 3.2、终止流程
`termination(taskId, flowParams)`：传入流程任务id，终止流程。flowParams包含如下字段：
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]
- flowStatus:流程状态，自定义流程状态[按需传输]

### 3.3、转办
`transfer(taskId, curUser, permissionFlag, addHandlers, message)`：转办, 默认删除当然办理用户权限，转办后，当前办理不可办理
- taskId 修改的任务id [必传]
- curUser 当前办理人唯一标识 [必传]
- permissionFlag 用户权限标识集合 [必传]
- addHandlers 增加办理人：加签，转办，委托 [必传]
- message 审批意见 [按需传输]

### 3.4、委派
`depute(taskId, curUser, permissionFlag, addHandlers, message)`：委派, 默认删除当然办理用户权限，转办后，当前办理不可办理
- taskId 修改的任务id [必传]
- curUser 当前办理人唯一标识 [必传]
- permissionFlag 用户权限标识集合 [必传]
- addHandlers 增加办理人：加签，转办，委托 [必传]
- message 审批意见 [按需传输]

### 3.5、加签
`addSignature(taskId, curUser, permissionFlag, addHandlers, message)`：加签，增加办理人
- taskId 修改的任务id [必传]
- curUser 当前办理人唯一标识 [必传]
- permissionFlag 用户权限标识集合 [必传]
- addHandlers 增加办理人：加签，转办，委托 [必传]
- message 审批意见 [按需传输]

### 3.6、减签
`reductionSignature(taskId, curUser, permissionFlag, addHandlers, message)`：减签，减少办理人
- taskId 修改的任务id [必传]
- curUser 当前办理人唯一标识 [必传]
- permissionFlag 用户权限标识集合 [必传]
- addHandlers 增加办理人：加签，转办，委托 [必传]
- message 审批意见 [按需传输]

### 3.7、修改办理人
`updateHandler(modifyHandler)`：修改办理人
- curUser:办理人唯一标识 [按需传输]
- ignore: 转办忽略权限校验（true：忽略，false：不忽略）[按需传输]
- permissionFlag: 用户所拥有的权限标识 [按需传输，ignore为false，则必传]
- addHandlers: 增加办理人：加签，转办，委托 [按需传输]
- reductionHandlers: 减少办理人：减签，委托 [按需传输]
- message: 审批意见 [按需传输]
- cooperateType: 协作方式(1审批 2转办 3委派 4会签 5票签 6加签 7减签）[按需传输]

