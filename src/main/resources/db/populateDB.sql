USE qjgn8nru6v93xc8q;

INSERT INTO skills (name)
VALUES ('sql'),
       ('php'),
       ('c#'),
       ('cpp'),
       ('java');

INSERT INTO account_status (status)
VALUES ('active'),
       ('banned'),
       ('deleted');

INSERT INTO accounts (data, status)
VALUES ('first account', 1),
       ('second account', 1);

INSERT INTO developers (name, account)
VALUES ('Vlad', 1),
       ('Pavel', 2);

INSERT INTO developer_skills
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (2, 5);