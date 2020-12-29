
-- 创建数据库

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP DATABASE IF EXISTS `demo`;
CREATE DATABASE `demo` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_0900_ai_ci;

-- 选择数据库

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(10) NOT NULL,
 `value` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT "",
 `creator` int NOT NULL,
 `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci  comment '配置表';

create table user_info(
id int unsigned auto_increment,
username varchar(255) not null comment'用户名',
password_md5 varchar(255) not null comment'密码',
slat varchar(20) not null comment'盐',
 `creator` int NOT NULL comment '创建者',
 `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP  comment '创建时间',
  `update_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  comment '修改时间',
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci   comment'用户表';

-- 角色表
create table role_info(
id int unsigned auto_increment,
role_name varchar(255) not null comment'角色名称',
remark varchar(255) default null comment'备注',
 `creator` int NOT NULL comment '创建者',
 `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP  comment '创建时间',
  `update_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  comment '修改时间',
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci   comment'角色表';

-- 权限表
create table permission(
id int unsigned auto_increment,
p_name varchar(255) not null comment'权限名称',
p_id int comment'父类ID',
 `creator` int NOT NULL comment '创建者',
 `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP  comment '创建时间',
  `update_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  comment '修改时间',
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci   comment'权限表';

-- 用户角色表
create table user_role(
id int unsigned auto_increment,
user_id int not null comment'用户ID',
role_id int not null comment'角色ID',
 `creator` int NOT NULL comment '创建者',
 `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP  comment '创建时间',
  `update_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  comment '修改时间',
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci   comment'用户角色表';

-- 角色权限表
create table role_permission(
id int unsigned auto_increment,
role_id int not null comment'角色ID',
permission_id int not null comment'权限ID',
 `creator` int NOT NULL comment '创建者',
 `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP  comment '创建时间',
  `update_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  comment '修改时间',
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci  comment'角色权限表';