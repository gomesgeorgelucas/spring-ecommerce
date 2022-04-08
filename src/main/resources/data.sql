insert into ec_roles (id_role, name_role) values (1, 'ADMIN');
insert into ec_roles (id_role, name_role) values (2, 'USER');

insert into ec_authorities(id_authority, name_authority) values (1, 'USER:READ');
insert into ec_authorities(id_authority, name_authority) values (2, 'USER:WRITE');
insert into ec_authorities(id_authority, name_authority) values (3, 'PRODUCT:READ');
insert into ec_authorities(id_authority, name_authority) values (4, 'PRODUCT:WRITE');
insert into ec_authorities(id_authority, name_authority) values (5, 'ORDER:READ');
insert into ec_authorities(id_authority, name_authority) values (6, 'ORDER:WRITE');

insert into ec_role_authorities(id_role, id_authority) values (1, 1);
insert into ec_role_authorities(id_role, id_authority) values (1, 2);
insert into ec_role_authorities(id_role, id_authority) values (1, 3);
insert into ec_role_authorities(id_role, id_authority) values (1, 4);
insert into ec_role_authorities(id_role, id_authority) values (1, 5);
insert into ec_role_authorities(id_role, id_authority) values (1, 6);

insert into ec_role_authorities(id_role, id_authority) values (2, 1);
insert into ec_role_authorities(id_role, id_authority) values (2, 2);
insert into ec_role_authorities(id_role, id_authority) values (2, 3);
insert into ec_role_authorities(id_role, id_authority) values (2, 5);
insert into ec_role_authorities(id_role, id_authority) values (2, 6);