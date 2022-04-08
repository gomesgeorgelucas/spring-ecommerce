insert into ec_roles (id_role, name_role) values (1, 'ADMIN');
insert into ec_roles (id_role, name_role) values (2, 'USER');

insert into ec_users(id_user, login_user, name_user, passwd_user, surname_user) values (1,'login','name', 'password', 'surname');
insert into ec_users(id_user, login_user, name_user, passwd_user, surname_user) values (2,'login2','name', 'password2', 'surname');

insert into ec_user_roles (id_role, id_user) VALUES (1,1);
insert into ec_user_roles (id_role, id_user) VALUES (2,2);