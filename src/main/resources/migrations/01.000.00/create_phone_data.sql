--liquibase formatted sql

--changeset shoshinvova@mail.ru:01.000.00/created_phone_table logicalFilePath:path-independent stripComments:true
--rollback drop table if exists phone_data;
create table if not exists phone_data
(
    id      bigint generated always as identity primary key,
    user_id bigint      not null,
    phone   varchar(13) not null,
    CONSTRAINT fk_phone_date_user FOREIGN KEY (user_id)
        REFERENCES "user" (id)
);

create unique index if not exists phone_data_phone_unique_ix on email_data (email);