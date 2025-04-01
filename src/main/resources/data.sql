delete from users_roles;
delete from users;
delete from roles;

insert into users(id, email,name,password) values (1,'mike@mail.ru','Mike','{noop}1');
insert into users(id, email,name,password) values (2, 'admin@mail.ru','Admin','{noop}1');

insert into roles(name) values ('ROLE_USER');
insert into roles(name) values ('ROLE_ADMIN');

insert into users_roles(user_id, role_id) values(1,'ROLE_USER');
insert into users_roles(user_id, role_id) values(2,'ROLE_ADMIN');