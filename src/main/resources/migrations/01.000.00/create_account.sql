--liquibase formatted sql

--changeset shoshinvova@mail.ru:01.000.00/created_account_table logicalFilePath:path-independent stripComments:true
--rollback drop table if exists account;
create table if not exists account
(
    id              bigint generated always as identity primary key,
    user_id         bigint  not null,
    initial_balance decimal not null check (initial_balance > 0),
    balance         decimal not null check (balance > 0),
    CONSTRAINT fk_account_user FOREIGN KEY (user_id)
        REFERENCES "user" (id)
);

create unique index if not exists account_user_id_unique_ix on account (user_id);