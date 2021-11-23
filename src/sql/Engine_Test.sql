create database if not exists test;

create table ne (
    id int,
    sum float,
    brith date,
    title char(20)
)
default
charset = utf8;

create table innodb (
    id int,
    sum float,
    brith date,
    title char(20)
)
ENGINE = INNODB
default
charset = utf8;

create table myisam (
    id int,
    sum float,
    brith date,
    title char(20)
)
ENGINE = MyISAM
default
charset = utf8;

create table memory (
    id int,
    sum float,
    brith date,
    title char(20)
)
ENGINE = MEMORY
default
charset = utf8;