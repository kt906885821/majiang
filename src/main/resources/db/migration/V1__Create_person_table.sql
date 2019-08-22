create table USER
(
    ID           int auto_increment,
    ACCESS_ID    VARCHAR(100) not null,
    NAME         VARCHAR(20)  not null,
    TOKEN        CHAR(36)     not null,
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
