insert into ec_roles (id_role, name_role) values (1, 'ADMIN');
insert into ec_roles (id_role, name_role) values (2, 'USER');

insert into ec_users(id_user, login_user, name_user, passwd_user, surname_user) values (1,'login','name', 'password', 'surname');
insert into ec_users(id_user, login_user, name_user, passwd_user, surname_user) values (2,'login2','name', 'password2', 'surname');

insert into ec_user_roles (id_role, id_user) VALUES (1,1);
insert into ec_user_roles (id_role, id_user) VALUES (2,2);

insert into ec_product_categories (id_product_category, name_product_category) values (1, 'eletronicos');
insert into ec_product_categories (id_product_category, name_product_category) values (2, 'casa');
insert into ec_product_categories (id_product_category, name_product_category) values (3, 'banho');
insert into ec_product_categories (id_product_category, name_product_category) values (4, 'pet');
insert into ec_product_categories (id_product_category, name_product_category) values (5, 'computacao');

insert into ec_products (id_product, description_product, name_product, quantity_product, unit_price_product, id_product_creator_user)
values (1, 'computador de mesa','computador1',10,1000.00,1);
insert into ec_products (id_product, description_product, name_product, quantity_product, unit_price_product, id_product_creator_user)
values (2, 'computador de mesa','computador2',5,2000.00,1);
insert into ec_products (id_product, description_product, name_product, quantity_product, unit_price_product, id_product_creator_user)
values (3, 'toalha de mesa','toalha1',10,10.00,1);
insert into ec_products (id_product, description_product, name_product, quantity_product, unit_price_product, id_product_creator_user)
values (4, 'lencol de solteiro','lencol1',3,100.00,1);
insert into ec_products (id_product, description_product, name_product, quantity_product, unit_price_product, id_product_creator_user)
values (5, 'coleira media','coleira1',10,20.00,1);
insert into ec_products (id_product, description_product, name_product, quantity_product, unit_price_product, id_product_creator_user)
values (6, 'relogio de pulso','relogio1',7,18.00,1);

insert into ec_category_products (id_product, id_product_category)
values (1,1);
insert into ec_category_products (id_product, id_product_category)
values (1,5);
insert into ec_category_products (id_product, id_product_category)
values (2,5);
insert into ec_category_products (id_product, id_product_category)
values (2,1);
insert into ec_category_products (id_product, id_product_category)
values (3,2);
insert into ec_category_products (id_product, id_product_category)
values (4,2);
insert into ec_category_products (id_product, id_product_category)
values (5,4);
insert into ec_category_products (id_product, id_product_category)
values (6,1);