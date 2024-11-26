--liquibase formatted sql

--changeset shoshinvova@mail.ru:01.000.00/insert_test_data logicalFilePath:path-independent stripComments:true
--rollback truncate table account; truncate table email_data; truncate table phone_data;  truncate table "user";

insert into "user" (name, date_of_birth, password)
values ('Ivan', '01.05.1993', 'pass_word'),
       ('Petr', '01.05.1996', 'password'),
       ('Sergey', '01.05.1994', 'qwerty123'),
       ('Steven', '01.05.1984', 'abc123456');

insert into account (user_id, balance, initial_balance)
values (1, 1000, 1000),
       (2, 700, 700),
       (3, 900, 900),
       (4, 1500, 1500);

insert into email_data (user_id, email)
values (1, 'Ivan@gmail.com'),
       (2, 'Petr@gmail.com'),
       (3, 'Sergey@gmail.com'),
       (4, 'Steven@gmail.com');

insert into phone_data (user_id, phone)
values (1, '79207865432'),
       (2, '79207865433'),
       (3, '79207865434'),
       (4, '5');
