CREATE TABLE `flow_definition`
(
    `id`              bigint          NOT NULL COMMENT '主键id',
    `flow_code`       varchar(40)     NOT NULL COMMENT '流程编码',
    `flow_name`       varchar(100)    NOT NULL COMMENT '流程名称',
    `category`        varchar(100)             DEFAULT NULL COMMENT '流程类别',
    `version`         varchar(20)     NOT NULL COMMENT '流程版本',
    `is_publish`      tinyint(1)      NOT NULL DEFAULT '0' COMMENT '是否发布（0未发布 1已发布 9失效）',
    `form_custom`     char(1)                  DEFAULT 'N' COMMENT '审批表单是否自定义（Y是 N否）',
    `form_path`       varchar(100)             DEFAULT NULL COMMENT '审批表单路径',
    `activity_status` tinyint(1)      NOT NULL DEFAULT '1' COMMENT '流程激活状态（0挂起 1激活）',
    `listener_type`   varchar(100)             DEFAULT NULL COMMENT '监听器类型',
    `listener_path`   varchar(400)             DEFAULT NULL COMMENT '监听器路径',
    `ext`             varchar(500)             DEFAULT NULL COMMENT '业务详情 存业务表对象json字符串',
    `create_time`     datetime                 DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                 DEFAULT NULL COMMENT '更新时间',
    `del_flag`        char(1)                  DEFAULT '0' COMMENT '删除标志',
    `tenant_id`       varchar(40)              DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='流程定义表';

CREATE TABLE `flow_node`
(
    `id`              bigint        NOT NULL COMMENT '主键id',
    `node_type`       tinyint(1)      NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
    `definition_id`   bigint          NOT NULL COMMENT '流程定义id',
    `node_code`       varchar(100)    NOT NULL COMMENT '流程节点编码',
    `node_name`       varchar(100)  DEFAULT NULL COMMENT '流程节点名称',
    `permission_flag` varchar(200)  DEFAULT NULL COMMENT '权限标识（权限类型:权限标识，可以多个，用逗号隔开)',
    `node_ratio`      decimal(6, 3) DEFAULT NULL COMMENT '流程签署比例值',
    `coordinate`      varchar(100)  DEFAULT NULL COMMENT '坐标',
    `any_node_skip`   varchar(100)  DEFAULT NULL COMMENT '任意结点跳转',
    `listener_type`   varchar(100)  DEFAULT NULL COMMENT '监听器类型',
    `listener_path`   varchar(400)  DEFAULT NULL COMMENT '监听器路径',
    `handler_type`    varchar(100)  DEFAULT NULL COMMENT '处理器类型',
    `handler_path`    varchar(400)  DEFAULT NULL COMMENT '处理器路径',
    `form_custom`     char(1)       DEFAULT 'N' COMMENT '审批表单是否自定义（Y是 N否）',
    `form_path`       varchar(100)  DEFAULT NULL COMMENT '审批表单路径',
    `version`         varchar(20)     NOT NULL COMMENT '版本',
    `create_time`     datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime      DEFAULT NULL COMMENT '更新时间',
    `ext`             text          COMMENT '扩展属性',
    `del_flag`        char(1)       DEFAULT '0' COMMENT '删除标志',
    `tenant_id`       varchar(40)   DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='流程节点表';

CREATE TABLE `flow_skip`
(
    `id`             bigint       NOT NULL COMMENT '主键id',
    `definition_id`  bigint          NOT NULL COMMENT '流程定义id',
    `now_node_code`  varchar(100)    NOT NULL COMMENT '当前流程节点的编码',
    `now_node_type`  tinyint(1)   DEFAULT NULL COMMENT '当前节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
    `next_node_code` varchar(100)    NOT NULL COMMENT '下一个流程节点的编码',
    `next_node_type` tinyint(1)   DEFAULT NULL COMMENT '下一个节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
    `skip_name`      varchar(100) DEFAULT NULL COMMENT '跳转名称',
    `skip_type`      varchar(40)  DEFAULT NULL COMMENT '跳转类型（PASS审批通过 REJECT退回）',
    `skip_condition` varchar(200) DEFAULT NULL COMMENT '跳转条件',
    `coordinate`     varchar(100) DEFAULT NULL COMMENT '坐标',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    `del_flag`       char(1)      DEFAULT '0' COMMENT '删除标志',
    `tenant_id`      varchar(40)  DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='节点跳转关联表';

CREATE TABLE `flow_instance`
(
    `id`              bigint      NOT NULL COMMENT '主键id',
    `definition_id`   bigint      NOT NULL COMMENT '对应flow_definition表的id',
    `business_id`     varchar(40) NOT NULL COMMENT '业务id',
    `node_type`       tinyint(1)  NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
    `node_code`       varchar(40) NOT NULL COMMENT '流程节点编码',
    `node_name`       varchar(100)         DEFAULT NULL COMMENT '流程节点名称',
    `variable`        text COMMENT '任务变量',
    `flow_status`     varchar(20) NOT NULL COMMENT '流程状态（0待提交 1审批中 2审批通过 4终止 5作废 6撤销 8已完成 9已退回 10失效 11拿回）',
    `activity_status` tinyint(1)  NOT NULL DEFAULT '1' COMMENT '流程激活状态（0挂起 1激活）',
    `def_json`        text COMMENT '流程定义json',
    `create_by`       varchar(64)          DEFAULT '' COMMENT '创建者',
    `create_time`     datetime             DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime             DEFAULT NULL COMMENT '更新时间',
    `ext`             varchar(500)         DEFAULT NULL COMMENT '扩展字段，预留给业务系统使用',
    `del_flag`        char(1)              DEFAULT '0' COMMENT '删除标志',
    `tenant_id`       varchar(40)          DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='流程实例表';

CREATE TABLE `flow_task`
(
    `id`            bigint       NOT NULL COMMENT '主键id',
    `definition_id` bigint       NOT NULL COMMENT '对应flow_definition表的id',
    `instance_id`   bigint       NOT NULL COMMENT '对应flow_instance表的id',
    `node_code`     varchar(100) NOT NULL COMMENT '节点编码',
    `node_name`     varchar(100) DEFAULT NULL COMMENT '节点名称',
    `node_type`     tinyint(1)   NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
    `flow_status`     varchar(20) NOT NULL COMMENT '流程状态（0待提交 1审批中 2审批通过 4终止 5作废 6撤销 8已完成 9已退回 10失效 11拿回）',
    `form_custom`   char(1)      DEFAULT 'N' COMMENT '审批表单是否自定义（Y是 N否）',
    `form_path`     varchar(100) DEFAULT NULL COMMENT '审批表单路径',
    `create_time`   datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime     DEFAULT NULL COMMENT '更新时间',
    `del_flag`      char(1)      DEFAULT '0' COMMENT '删除标志',
    `tenant_id`     varchar(40)  DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='待办任务表';

CREATE TABLE `flow_his_task`
(
    `id`               bigint(20)          NOT NULL COMMENT '主键id',
    `definition_id`    bigint(20)          NOT NULL COMMENT '对应flow_definition表的id',
    `instance_id`      bigint(20)          NOT NULL COMMENT '对应flow_instance表的id',
    `task_id`          bigint(20)          NOT NULL COMMENT '对应flow_task表的id',
    `node_code`        varchar(100)                 DEFAULT NULL COMMENT '开始节点编码',
    `node_name`        varchar(100)                 DEFAULT NULL COMMENT '开始节点名称',
    `node_type`        tinyint(1)                   DEFAULT NULL COMMENT '开始节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
    `target_node_code` varchar(200)                 DEFAULT NULL COMMENT '目标节点编码',
    `target_node_name` varchar(200)                 DEFAULT NULL COMMENT '结束节点名称',
    `approver`         varchar(40)                  DEFAULT NULL COMMENT '审批者',
    `cooperate_type`   tinyint(1)          NOT NULL DEFAULT '0' COMMENT '协作方式(1审批 2转办 3委派 4会签 5票签 6加签 7减签)',
    `collaborator`     varchar(40)                  DEFAULT NULL COMMENT '协作人',
    `skip_type`        varchar(10)         NOT NULL COMMENT '流转类型（PASS通过 REJECT退回 NONE无动作）',
    `flow_status`      varchar(20)         NOT NULL COMMENT '流程状态（0待提交 1审批中 2审批通过 4终止 5作废 6撤销 8已完成 9已退回 10失效 11拿回）',
    `form_custom`      char(1)                      DEFAULT 'N' COMMENT '审批表单是否自定义（Y是 N否）',
    `form_path`        varchar(100)                 DEFAULT NULL COMMENT '审批表单路径',
    `message`          varchar(500)                 DEFAULT NULL COMMENT '审批意见',
    `variable`         TEXT                         DEFAULT NULL COMMENT '任务变量',
    `ext`              varchar(500)                 DEFAULT NULL COMMENT '业务详情 存业务表对象json字符串',
    `create_time`      datetime                     DEFAULT NULL COMMENT '任务开始时间',
    `update_time`      datetime                     DEFAULT NULL COMMENT '审批完成时间',
    `del_flag`         char(1)                      DEFAULT '0' COMMENT '删除标志',
    `tenant_id`        varchar(40)                  DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='历史任务记录表';


CREATE TABLE `flow_user`
(
    `id`           bigint      NOT NULL COMMENT '主键id',
    `type`         char(1)         NOT NULL COMMENT '人员类型（1待办任务的审批人权限 2待办任务的转办人权限 3待办任务的委托人权限）',
    `processed_by` varchar(80) DEFAULT NULL COMMENT '权限人',
    `associated`   bigint          NOT NULL COMMENT '任务表id',
    `create_time`  datetime    DEFAULT NULL COMMENT '创建时间',
    `create_by`    varchar(80) DEFAULT NULL COMMENT '创建人',
    `update_time`  datetime    DEFAULT NULL COMMENT '更新时间',
    `del_flag`     char(1)     DEFAULT '0' COMMENT '删除标志',
    `tenant_id`    varchar(40) DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `user_processed_type` (`processed_by`, `type`),
    KEY `user_associated` (`associated`) USING BTREE
) ENGINE = InnoDB COMMENT ='流程用户表';



-- ----------------------------
-- Table structure for test_leave
-- ----------------------------
CREATE TABLE `test_leave` (
                              `id` bigint unsigned NOT NULL COMMENT '主键',
                              `type` tinyint NOT NULL COMMENT '请假类型',
                              `reason` varchar(500)  NOT NULL COMMENT '请假原因',
                              `start_time` datetime NOT NULL COMMENT '开始时间',
                              `end_time` datetime NOT NULL COMMENT '结束时间',
                              `day` tinyint DEFAULT NULL COMMENT '请假天数',
                              `instance_id` bigint DEFAULT NULL COMMENT '流程实例的id',
                              `node_code` varchar(100)  DEFAULT NULL COMMENT '节点编码',
                              `node_name` varchar(100)  DEFAULT NULL COMMENT '流程节点名称',
                              `node_type`       tinyint(1) NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
                              `flow_status` varchar(20) DEFAULT NULL COMMENT '流程状态（0待提交 1审批中 2 审批通过 3自动通过 4终止 5作废 6撤销 7取回  8已完成 9已退回 10失效）',
                              `create_by` varchar(64)  DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_by` varchar(64)  DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `del_flag` char(1)  DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='OA 请假申请表';

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1061, '测试菜单', 0, 60, 'test', NULL, NULL, 1, 0, 'M', '0', '0', '', 'example', 'admin', '2023-03-17 18:15:12', 'admin', '2023-10-26 17:21:45', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1092, 'OA 请假申请', 1061, 1, 'leave', 'system/leave/index', NULL, 1, 0, 'C', '0', '0', 'system:leave:list', '#', 'admin', '2023-04-02 18:14:21', 'admin', '2024-03-25 09:30:57', 'OA 请假申请菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1094, 'OA 请假申请新增', 1092, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:leave:add', '#', 'admin', '2023-04-02 18:14:21', 'admin', '2024-03-25 09:31:07', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1095, 'OA 请假申请提交', 1092, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:leave:submit', '#', 'admin', '2023-04-02 18:14:21', 'admin', '2024-03-25 09:34:42', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1096, 'OA 请假申请修改', 1092, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:leave:edit', '#', 'admin', '2023-04-02 18:14:21', 'admin', '2024-03-25 09:31:15', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1097, 'OA 请假申请删除', 1092, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:leave:remove', '#', 'admin', '2023-04-02 18:14:21', 'admin', '2024-03-25 09:31:18', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1098, 'OA 请假申请导出', 1092, 6, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:leave:export', '#', 'admin', '2023-04-02 18:14:21', 'admin', '2024-03-25 09:31:23', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2000, 'OA 请假申请查询', 1092, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:leave:query', '#', 'admin', '2024-03-25 09:33:03', 'admin', '2024-03-25 09:33:16', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2001, 'OA 请假申请办理', 1092, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:execute:handle', '#', 'admin', '2024-03-25 09:35:12', '', NULL, '');

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1106, '流程管理', 0, 50, 'flow', NULL, NULL, 1, 0, 'M', '0', '0', '', 'cascader', 'admin', '2023-04-11 11:02:28', 'admin', '2023-10-26 17:21:32', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1107, '流程定义', 1106, 1, 'definition', 'flow/definition/index', NULL, 1, 0, 'C', '0', '0', 'flow:definition:list', 'online', 'admin', '2023-04-11 13:06:38', 'admin', '2023-04-13 13:00:05', '流程定义菜单');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1109, '流程定义新增', 1107, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'flow:definition:add', '#', 'admin', '2023-04-11 13:06:38', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1111, '流程定义修改', 1107, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'flow:definition:edit', '#', 'admin', '2023-04-11 13:06:38', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1112, '流程定义删除', 1107, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'flow:definition:remove', '#', 'admin', '2023-04-11 13:06:38', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1124, '导入流程定义', 1107, 11, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:definition:importDefinition', '#', 'admin', '2023-06-04 23:52:33', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1108, '流程定义查询', 1107, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'flow:definition:query', '#', 'admin', '2023-04-11 13:06:38', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1110, '流程设计', 1107, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'flow:definition:design', '#', 'admin', '2023-04-11 13:06:38', 'admin', '2023-04-14 12:01:20', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1114, '跳转规则配置', 1107, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:definition:skip', '#', 'admin', '2023-04-13 01:11:24', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1115, '查看流程设计', 1107, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:definition:queryDesign', '#', 'admin', '2023-04-14 12:02:37', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1116, '发布', 1107, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:definition:publish', '#', 'admin', '2023-04-14 17:03:57', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1117, '取消发布', 1107, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:definition:unPublish', '#', 'admin', '2023-04-14 23:05:27', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1118, '待办任务', 1106, 2, 'todo', 'flow/task/todo/index', NULL, 1, 0, 'C', '0', '0', 'flow:execute:toDoPage', 'guide', 'admin', '2023-04-17 17:21:21', 'admin', '2023-06-11 00:33:39', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1122, '已办任务', 1106, 3, '1', 'flow/task/done/index', NULL, 1, 0, 'C', '0', '0', 'flow:execute:donePage', 'druid', 'admin', '2023-05-06 13:01:37', 'admin', '2023-06-02 08:59:50', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1123, '已办任务历史记录', 1122, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:execute:doneList', '#', 'admin', '2023-06-02 13:12:11', '', NULL, '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1119, '办理', 1118, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'flow:execute:handle', '#', 'admin', '2023-04-22 16:03:38', 'admin', '2023-06-02 08:59:46', '');
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2006, '抄送任务', 1106, 4, 'notice', 'flow/notice/index', NULL, 1, 0, 'C', '0', '0', 'flow:execute:copyPage', 'email', 'admin', '2024-06-27 09:18:06', '', NULL, '');


INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (101, '节点类型', 'node_type', '0', 'admin', '2024-03-18 15:08:19', '', NULL, '节点类型');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (100, '是否发布', 'is_publish', '0', 'admin', '2024-03-18 15:07:04', 'admin', '2024-03-18 15:07:11', '是否发布');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (15, '流程状态', 'flow_status', '0', 'admin', '2023-05-07 00:47:03', 'admin', '2023-10-27 16:50:23', '流程状态');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, '请假类型', 'leave_type', '0', 'admin', '2023-04-01 23:32:00', 'admin', '2023-04-11 11:07:24', '请假类型列表');
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (102, '协作类型', 'cooperate_type', '0', 'admin', '2024-06-03 22:38:27', '', NULL, '协作类型');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (103, 0, '开始节点', '0', 'node_type', '0', 'default', 'N', '0', 'admin', '2024-03-18 15:08:43', '', NULL, '开始节点');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (104, 1, '中间节点', '1', 'node_type', '1', 'default', 'N', '0', 'admin', '2024-03-18 15:08:51', '', NULL, '中间节点');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (105, 2, '结束节点', '2', 'node_type', '2', 'default', 'N', '0', 'admin', '2024-03-18 15:09:04', '', NULL, '结束节点');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (106, 3, '互斥网关', '3', 'node_type', '3', 'default', 'N', '0', 'admin', '2024-03-18 15:09:12', '', NULL, '互斥网关');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (107, 4, '并行网关', '4', 'node_type', '4', 'default', 'N', '0', 'admin', '2024-03-18 15:09:24', '', NULL, '并行网关');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (41, 0, '待提交', '0', 'flow_status', NULL, 'info', 'N', '0', 'admin', '2023-05-07 00:48:15', 'admin', '2023-05-07 00:50:24', '待提交');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (42, 1, '审批中', '1', 'flow_status', NULL, 'primary', 'N', '0', 'admin', '2023-05-07 00:49:27', 'admin', '2023-05-07 00:50:33', '审批中');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (45, 2, '审批通过', '2', 'flow_status', NULL, 'primary', 'N', '0', 'admin', '2023-09-04 00:29:04', 'admin', '2023-09-18 14:53:17', '审批通过');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (108, 3, '自动完成', '3', 'flow_status', NULL, 'primary', 'N', '0', 'admin', '2023-09-04 00:29:04', 'admin', '2023-09-18 14:53:17', '自动完成');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (136, 4, '终止', '4', 'flow_status', NULL, 'info', 'N', '0', 'admin', '2025-04-18 14:35:56', 'admin', '2025-04-18 14:36:03', '终止');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (134, 6, '撤销', '6', 'flow_status', NULL, 'danger', 'N', '0', 'admin', '2025-04-16 12:01:38', '', NULL, '撤销');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (43, 8, '已完成', '8', 'flow_status', NULL, 'success', 'N', '0', 'admin', '2023-05-07 00:49:59', 'admin', '2023-09-04 00:28:38', '已完成');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (44, 9, '退回', '9', 'flow_status', NULL, 'warning', 'N', '0', 'admin', '2023-05-07 00:50:56', '', NULL, '退回');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (50, 10, '失效', '10', 'flow_status', NULL, 'info', 'N', '0', 'admin', '2023-09-20 11:24:32', 'admin', '2023-09-20 11:25:51', '失效');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (135, 11, '拿回', '11', 'flow_status', NULL, 'warning', 'N', '0', 'admin', '2025-04-17 12:10:30', '', NULL, '拿回');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (30, 0, '串行-简单', '0', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2023-04-01 23:32:40', 'admin', '2024-06-06 16:31:11', 'leaveFlow-serial1');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (31, 1, '串行-通过互斥', '1', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2023-04-01 23:33:14', 'admin', '2024-06-06 16:31:20', 'leaveFlow-serial2');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (46, 2, '并行-汇聚', '2', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2023-09-13 00:17:44', 'admin', '2024-06-06 16:31:51', 'leaveFlow-parallel1');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (51, 4, '串行-退回互斥', '4', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2023-10-20 10:11:57', 'admin', '2024-06-06 16:31:43', 'leaveFlow-serial3');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (109, 5, '会签', '5', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2024-06-06 16:22:21', 'admin', '2024-06-06 16:32:09', 'leaveFlow-meet-sign');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (110, 6, '票签', '6', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2024-06-06 16:22:31', 'admin', '2024-06-06 16:32:15', 'leaveFlow-vote-sign');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (118, 7, '串行-复杂互斥', '7', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2024-08-07 13:08:52', 'admin', '2024-08-12 15:22:52', 'leaveFlow-serial4');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (119, 8, '并行-复杂', '8', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2024-08-12 15:23:18', '', NULL, 'leaveFlow-parallel3');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (123, 9, '办理人表达式', '9', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2024-08-07 13:08:52', 'admin', '2024-08-12 15:22:52', 'leaveFlow-serial5');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (128, 10, '监听器', '10', 'leave_type', NULL, 'default', 'N', '0', 'admin', '2024-11-25 13:49:51', '', NULL, 'leaveFlow-serial6');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (131, 11, '网关直连', '11', 'leave_type', '', 'default', 'N', '0', 'admin', '2025-01-09 00:36:01', 'admin', '2025-01-09 00:36:35', 'leaveFlow-direct');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (100, 0, '未发布', '0', 'is_publish', '0', 'default', 'N', '0', 'admin', '2024-03-18 15:07:27', '', NULL, '未发布');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (101, 1, '已发布', '1', 'is_publish', '1', 'default', 'N', '0', 'admin', '2024-03-18 15:07:37', '', NULL, '已发布');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (102, 9, '已失效', '9', 'is_publish', '9', 'default', 'N', '0', 'admin', '2024-03-18 15:07:48', '', NULL, '已失效');

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (111, 1, '无', '1', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:39:09', '', NULL, '审批');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (112, 2, '转办', '2', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:39:17', '', NULL, '转办');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (113, 3, '委派', '3', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:39:29', '', NULL, '委派');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (114, 4, '会签', '4', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:39:41', '', NULL, '会签');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (115, 5, '票签', '5', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:39:52', '', NULL, '票签');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (116, 6, '加签', '6', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:40:12', '', NULL, '加签');
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (117, 7, '减签', '7', 'cooperate_type', NULL, 'primary', 'N', '0', 'admin', '2024-06-03 22:40:24', '', NULL, '减签');

INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (100, '领导', 'leader', 3, '1', 1, 1, '0', '0', 'admin', '2024-04-01 10:51:30', '', NULL, NULL);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (101, '员工', 'yuangong', 4, '1', 1, 1, '0', '0', 'admin', '2024-04-01 10:52:06', '', NULL, NULL);
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (100, NULL, 'leader', '领导', '00', '', '', '0', '', '$2a$10$l7l.cF7SKlC.D5EhO02tBeMgjNHWjwj3T9.C8ylxz2b9g85pJqltG', '0', '0', '127.0.0.1', '2024-04-01 10:55:39', 'admin', '2024-04-01 10:54:23', '', '2024-04-01 10:55:38', NULL);
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (101, NULL, 'yuangong', '员工', '00', '', '', '0', '', '$2a$10$2IQ5nrTqnlG/NW2hzGr.r.gryFBhoIuV0O6PbAUdOpeBFikvCY9Qe', '0', '0', '127.0.0.1', '2024-04-01 10:56:25', 'admin', '2024-04-01 10:54:35', '', '2024-04-01 10:56:25', NULL);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (100, 100);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (101, 101);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1061);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1092);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1094);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1095);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1096);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1097);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1098);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1106);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1107);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1108);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1109);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1110);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1111);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1112);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1114);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 1124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 2000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (100, 2001);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1061);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1092);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1094);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1095);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1096);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1097);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1098);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1106);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1107);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1108);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1109);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1110);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1111);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1112);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1114);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1115);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1116);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1117);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1118);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1119);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1122);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1123);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 1124);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 2000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (101, 2001);

