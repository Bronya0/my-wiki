#电子管理书表
drop table if exists `ebook`;
create table `ebook`
(
    `id`           bigint      not null comment 'id',
    `name`         varchar(50) not null comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description`  varchar(200) comment '描述',
    `cover`        varchar(200) comment '封面',
    `doc_count`    int comment '文档数',
    `view_count`   int comment '阅读数',
    `vote_count`   int comment '点赞数',
    primary key (`id`)
)   engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id,name,description) values (1,'SpringBoot教程','一个约定大于配置的方便好用的框架');
insert into `ebook` (id,name,description) values (2,'vue教程','前后端分离时代流行的前端框架');
insert into `ebook` (id,name,description) values (3,'python教程','大众化而应用广泛的编程语言');
insert into `ebook` (id,name,description) values (4,'mysql教程','最流行的开源数据库管理系统');
insert into `ebook` (id,name,description) values (5,'IDEA教程','Java开发的首选集成开发环境');
insert into `ebook` (id,name,description) values (6,'go教程','天生并发');
insert into `ebook` (id,name,description) values (7,'语文教程','中国人不能不学中文');
insert into `ebook` (id,name,description) values (8,'数学教程','中国人不能不学数学');
insert into `ebook` (id,name,description) values (9,'英语教程','中国人也得学英语');
insert into `ebook` (id,name,description) values (10,'物理教程','中国人也得学物理');
insert into `ebook` (id,name,description) values (11,'化学教程','中国人也得学化学');
insert into `ebook` (id,name,description) values (12,'生物教程','中国人也得学生物');
insert into `ebook` (id,name,description) values (13,'政治教程','中国人也得学政治');
insert into `ebook` (id,name,description) values (14,'美术教程','中国人也得学美术');
insert into `ebook` (id,name,description) values (15,'建模教程','中国人也得学建模');
insert into `ebook` (id,name,description) values (16,'特效教程','中国人也得学特效');
insert into `ebook` (id,name,description) values (17,'文案教程','中国人也得学文案');
insert into `ebook` (id,name,description) values (18,'策划教程','中国人也得学策划');
insert into `ebook` (id,name,description) values (19,'测试教程','中国人也得学测试');
insert into `ebook` (id,name,description) values (20,'开发教程','中国人也得学开发');
insert into `ebook` (id,name,description) values (21,'竞技教程','中国人也得学竞技');
insert into `ebook` (id,name,description) values (22,'演绎教程','中国人也得学演绎');
insert into `ebook` (id,name,description) values (23,'ppt教程','中国人也得学ppt');
insert into `ebook` (id,name,description) values (24,'word教程','中国人也得学word');
insert into `ebook` (id,name,description) values (25,'office教程','中国人也得学office');
insert into `ebook` (id,name,description) values (26,'wps教程','中国人也得学wps');
insert into `ebook` (id,name,description) values (27,'建站教程','中国人也得学建站');

#分类表
drop table if exists `category`;
create table `category`(
    `id` bigint not null comment 'id',
    `parent` bigint not null comment '父id',
    `name` varchar(50) not null comment '名字',
    `sort` int comment '顺序',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='分类';

insert into `category` (id, parent, name, sort) VALUES (100,000,'编程语言',100);
insert into `category` (id, parent, name, sort) VALUES (101,100,'java',101);
insert into `category` (id, parent, name, sort) VALUES (102,100,'golang',102);

insert into `category` (id, parent, name, sort) VALUES (200,000,'开发工具',200);
insert into `category` (id, parent, name, sort) VALUES (201,200,'IDEA',201);
insert into `category` (id, parent, name, sort) VALUES (202,200,'eclipse',202);

insert into `category` (id, parent, name, sort) VALUES (300,000,'数据库',300);
insert into `category` (id, parent, name, sort) VALUES (301,300,'redis',301);
insert into `category` (id, parent, name, sort) VALUES (302,300,'mysql',302);

insert into `category` (id, parent, name, sort) VALUES (400,000,'框架',400);
insert into `category` (id, parent, name, sort) VALUES (401,400,'mybatis',401);
insert into `category` (id, parent, name, sort) VALUES (402,400,'spring',402);

#电子书内容表设计
drop table if exists `doc`;
create table `doc`(
    `id` bigint not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    `view_count` int default 0 comment '阅读数',
    `vote_count` int default 0 comment '点赞数',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES (1,1,0,'文档1',1,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES (2,1,1,'文档1.1',1,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES (3,1,0,'文档2',2,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES (4,1,3,'文档2.1',1,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES (5,1,3,'文档2.2',2,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES (6,1,5,'文档2.2.1',1,0,0);

#文档内容表
drop table if exists `content`;
create table `content`(
    `id` bigint not null comment '文档id',
    `content` mediumtext not null comment '内容',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '文档内容';

#用户表
drop table if exists `user`;
create table `user`(
    `id` bigint not null comment '用户id',
    `login_name` varchar(50) not null comment '登录名',
    `name` varchar(50) not null comment '昵称',
    `password` char(32) not null comment '密码',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
)engine=innodb default charset=utf8mb4 comment '用户';

insert into `user` (id, login_name, name, password) VALUES (1,'tangssst','一号','123456')