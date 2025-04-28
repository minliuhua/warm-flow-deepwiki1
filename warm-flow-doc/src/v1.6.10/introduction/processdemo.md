# 功能演示
<!-- @include: ../other/betweengg.md -->


## 演示地址

::: warning
- admin/admin123

- 演示地址：[http://www.hhzai.top](http://www.hhzai.top)  
- 项目地址：[https://gitee.com/min290/hh-vue.git](https://gitee.com/min290/hh-vue.git)  
:::

## 演示图

<table>
    <tbody>
        <tr>
        <td><img src="https://foruda.gitee.com/images/1736763187046620959/814fd4bf_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1736763199058517932/2b01c913_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1736763731091619270/d74d099d_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1736763757217975424/4d1053a9_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1736763846364044932/e3c2552d_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703641952765512992/dc187080_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1736762724064103953/a3934696_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1736762828094704587/9c0f51c8_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1736762913994459048/2b4b05bc_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1736762989286625820/0cfad72f_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1736763079110050572/6e068531_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1736763097281901410/31cf558d_2218307.png"/></td>
    </tr>
    </tbody>
</table>


## 1、新增定义
::: tip
- 流程编码和流程版本：确定唯一

- 审批表单路径：记录待办任务需要显示的待办信息页面，点击待办时候获取这个路径，动态加载这个页面
:::

<div><img src="https://foruda.gitee.com/images/1742803956071384899/eb563152_2218307.png" width="700"></div>
<div><img src="https://foruda.gitee.com/images/1742804032489030182/89b15652_2218307.png" width="700"></div>


## 2、流程设计
### 2、1节点设置
::: tip 
- 配置节点名称，协作方式（会签、票签和或签），节点权限，是否任意调整，监听器等

- 支持不同节点配置不同得审批表单
:::


<div><img src="https://foruda.gitee.com/images/1734589294761157636/ac74e327_2218307.png" width="700" /></div>
<div><img src="https://foruda.gitee.com/images/1742261152703131620/939d9684_2218307.png" width="700" /></div>
<div><img src="https://foruda.gitee.com/images/1732545153700629064/3183155f_2218307.png" width="700" /></div>

### 2、2跳转线设置
::: tip 
配置跳转名称，跳转类型（通过还是退回）,**退回不能选择通过类型**，调整条件
:::


![](https://foruda.gitee.com/images/1742270239857999165/4c5ce68d_2218307.png)
![](https://foruda.gitee.com/images/1726905626290177483/195615fc_2218307.png)
## 3、开启流程实例
::: tip
hh-vue项目已经准备了七套流程，以及开启流程代码，开启流程会直接执行到开始节点后一个节点
:::

![](/addIns.png)


## 4、提交流程
::: tip
提交流程后，流程流转到代表任务，由流程设计中的对应权限人去办理
:::


![输入图片说明](https://foruda.gitee.com/images/1703668493778770778/d77716b5_2218307.png "屏幕截图")


## 5、办理流程
::: tip
如果是互斥网关则会判断是否满足条件
:::


![输入图片说明](https://foruda.gitee.com/images/1703668882786849328/0b9554ec_2218307.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1703668896500858952/c9dc78e1_2218307.png "屏幕截图")

## 6、驳回流程

![输入图片说明](https://foruda.gitee.com/images/1703669345903195445/4ba131bc_2218307.png "屏幕截图")
