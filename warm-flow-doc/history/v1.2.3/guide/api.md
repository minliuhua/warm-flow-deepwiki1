# 核心接口

## DefService流程定义接口

`importXml(is)`：导入流程定义xml的输入流is，保存流程定义数据，返回流程定义对象

`saveXml(def))`： 传入流程定义id、流程定义xml字符串，保存流程定义数据  

`exportXml(id)`： 导出流程定义xml的Document对象  

`xmlString(id)`： 获取流程定义xml的字符串  

`removeDef(ids)`： 删除流程定义相关数据  

`publish(id)`： 发布流程定义  

`unPublish(id)`： 取消发布流程定义  

`copyDef(id)`： 复制流程定义   

`flowChart(instanceId)`： 获取流程图的图片流

## InsService流程实例接口

`start(businessId, flowParams)`：传入业务id，开启流程实例。flowParams包含如下字段：
- flowCode:流程编码 [必传]
- handler:办理人唯一标识 [建议传]
- variable:流程变量 [按需传输]
- ext:扩展字段，预留给业务系统使用 [按需传输]

`skipByInsId(instanceId, flowParams)`：传入流程实例id，流程跳转。flowParams包含如下字段：
- skipType:跳转类型(PASS审批通过 REJECT退回) [必传]
- nodeCode:节点编码 [如果指定跳转节点,必传]
- permissionFlag:办理人权限标识 [按需传输]
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]
- variable:流程变量 [按需传输]

`termination(instanceId, flowParams)`：传入流程实例id，终止流程。flowParams包含如下字段：
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]

`remove(instanceIds)`：根据实例ids，删除流程

## TaskService待办任务接口

`skip(taskId, flowParams)`：传入流程实例id，流程跳转。flowParams包含如下字段：
- skipType:跳转类型(PASS审批通过 REJECT退回) [必传]
- nodeCode:节点编码 [如果指定跳转节点,必传]
- permissionFlag:办理人权限标识 [按需传输]
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]
- variable:流程变量 [按需传输]

`termination(taskId, flowParams)`：传入流程任务id，终止流程。flowParams包含如下字段：
- message:审批意见 [按需传输]
- handler:办理人唯一标识 [建议传]

`transfer(taskId, curUser, permissionFlag, addHandlers, message)`：转办, 默认删除当然办理用户权限，转办后，当前办理不可办理
- taskId 修改的任务id
- curUser 当前办理人唯一标识
- permissionFlag 用户权限标识集合
- addHandlers 增加办理人：加签，转办，委托
- message 审批意见

`depute(taskId, curUser, permissionFlag, addHandlers, message)`：委派, 默认删除当然办理用户权限，转办后，当前办理不可办理
- taskId 修改的任务id
- curUser 当前办理人唯一标识
- permissionFlag 用户权限标识集合
- addHandlers 增加办理人：加签，转办，委托
- message 审批意见

`addSignature(taskId, curUser, permissionFlag, addHandlers, message)`：加签，增加办理人
- taskId 修改的任务id
- curUser 当前办理人唯一标识
- permissionFlag 用户权限标识集合
- addHandlers 增加办理人：加签，转办，委托
- message 审批意见

`reductionSignature(taskId, curUser, permissionFlag, addHandlers, message)`：减签，减少办理人
- taskId 修改的任务id
- curUser 当前办理人唯一标识
- permissionFlag 用户权限标识集合
- addHandlers 增加办理人：加签，转办，委托
- message 审批意见