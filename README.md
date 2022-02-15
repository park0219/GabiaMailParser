# GabiaMailParser
가비아 메일 클라이언트 프로그램입니다. 메일이 왔을 때 사용자에게 알려주는 기능과 받은 메일 목록을 보여주는 기능, 템플릿을 등록 및 수정하는 기능, 메일을 발송하는 기능을 가지고 있습니다.

해당 프로그램은 MySQL 데이터베이스를 사용하고 있습니다.\
테이블 생성문
```sql
CREATE DATABASE mail;
CREATE USER 'mail'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON mail.* TO 'mail'@'%';
FLUSH PRIVILEGES;

create table login_info
(
    USER_ID    varchar(30) null comment '사용자 아이디(이메일 주소)',
    LOGIN_DATE datetime    null comment '로그인 날짜'
);

create table mail
(
    MAIL_SEQ          int auto_increment comment '메일 SEQ'
        primary key,
    USER_ID           varchar(30)  null comment '사용자 아이디(이메일 주소)',
    MAIL_TITLE        varchar(500) null comment '메일 제목',
    MAIL_FROM         varchar(100) null comment '보낸 사람 메일 주소',
    MAIL_DATE         datetime     null comment '받은 날짜',
    MAIL_IMPORTANT    char         null comment '중요 메일 체크 여부(Y: 체크, N: 미체크)',
    REGISTRATION_DATE datetime     null comment '등록 날짜'
);

create table template
(
    USER_ID          varchar(30) not null comment '사용자 아이디(이메일 주소)'
        primary key,
    TEMPLATE_CONTENT text        null comment '템플릿 내용',
    LAST_MODIFY      datetime    null comment '최종 수정일'
);
```
