--liquibase formatted sql

--changeset shoshinvova@mail.ru:01.000.00/created_user_table logicalFilePath:path-independent stripComments:true
--rollback drop table if exists user;
create table if not exists "user"
(
    id            bigint generated always as identity primary key,
    name          varchar(500) not null,
    date_of_birth timestamp,
    password      varchar(500) not null check (length(password) >= 8 AND length(password) <= 500)
);

create index if not exists user_date_of_birth_ix on "user" (date_of_birth);
create index if not exists user_name_gin_trgm_idx on "user" using gin (name);