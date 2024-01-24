
# create metadata

create database if not exists words_remember;

use words_remember;

create table t_japan (
    id int primary key auto_increment,
    jp_write varchar(255) comment '日语书写',
    jp_read varchar(255) not null comment '日语读音',
    cn_mean varchar(255) not null comment '中文含义',
    word_type varchar(32) comment '单词类型',
    index idx_mean (`cn_mean`)
);

create table t_english (
    id int primary key auto_increment,
    eng_word varchar(255) not null comment '英文单词',
    word_type varchar(32) comment '单词类型',
    cn_mean varchar(255) comment '中文含义',
    index idx_mean (`cn_mean`)
);

create table t_english_sentence (
    id int primary key auto_increment,
    eng_word varchar(255) not null unique comment '英文单词',
    eng_sentence text not null
);
