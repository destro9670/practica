create sequence roles_id_seq
    increment 1
    start 3
    no cycle;

create table roles
(
    id   bigint primary key default nextval('roles_id_seq'),
    name varchar(100) not null
);

create sequence users_id_seq
    increment 1
    start 3
    no cycle;

create table users
(
    id       bigint primary key           default nextval('users_id_seq'),
    login    varchar(40),
    password varchar(255),
    enabled  bool                         default false,
    role_id  bigint references roles (id) default 2
);

create unique index login_idx on users (login);

create sequence marks_id_seq
    increment 1
    start 1
    no cycle;

create table marks
(
    id      bigint primary key default nextval('marks_id_seq'),
    count   int not null,
    user_id bigint references users (id),
    receipt timestamp          default current_timestamp
);

create sequence themes_id_seq
    increment 1
    start 1
    no cycle;

create table themes
(
    id      bigint primary key default nextval('themes_id_seq'),
    name    varchar(255)
);

create sequence questions_id_seq
    increment 1
    start 1
    no cycle;

create table questions
(
    id      bigint primary key default nextval('questions_id_seq'),
    name    text,
    theme_id bigint references themes(id)
);

create sequence answers_id_seq
    increment 1
    start 1
    no cycle;

create table answers
(
    id      bigint primary key default nextval('answers_id_seq'),
    name    text,
    is_correct bool,
    question_id bigint references questions(id)
);

-- insert data

insert into roles (id, name)
values (1, 'ROLE_ADMIN');
insert into roles (id, name)
values (2, 'ROLE_USER');

insert into users (id, login, password, enabled, role_id)
values (1, 'admin', '$2a$10$0jbZNZRJjTwYf4ytFJx4wuTg2bp//7uxvYPTJUsF0PYtxSX1y91R.', true, 1);
insert into users (id, login, password, enabled, role_id)
values (2, 'test', '$2a$10$Uq3QHTfxJPPbVJ3TCJ8TR.eK/mw2UwI.ocdjZJoSCsNIsKjITKAdW', true, 2);

