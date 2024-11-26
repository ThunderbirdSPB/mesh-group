--liquibase formatted sql

--changeset shoshinvova@mail.ru:01.000.00/created_email_data_table logicalFilePath:path-independent stripComments:true
--rollback drop table if exists email_data;
create table if not exists email_data
(
    id      bigint generated always as identity primary key,
    user_id bigint       not null,
    email   varchar(200) not null,
    CONSTRAINT fk_email_data_user FOREIGN KEY (user_id)
        REFERENCES "user" (id)
);

create unique index if not exists email_data_email_unique_ix on email_data (email);