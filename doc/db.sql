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


