-- Users examples
-- password: imagina
insert into users(nif, email, password, name, last_name, number_phone, enabled, status, created_at) values ('21525241D', 'fernando@example.com', '$2a$10$qINz45KQzkLHtzOovvgjn.y1SKh8zfR5Meyh61FHz3bvSlO4uez.O', 'Fernando', 'Alonso García', '123456789', true, 'pendiente', '2021-05-14 08:35:50');
insert into users(nif, email, password, name, last_name, number_phone, enabled, status, created_at) values ('12545421H', 'ned@example.com', '$2a$10$qINz45KQzkLHtzOovvgjn.y1SKh8zfR5Meyh61FHz3bvSlO4uez.O', 'Ned', 'Flandes', '325652147', true, 'pendiente', '2021-05-14 08:35:50');
insert into users(nif, email, password, name, last_name, number_phone, enabled, status, created_at) values ('65215478S', 'juan@example.com', '$2a$10$qINz45KQzkLHtzOovvgjn.y1SKh8zfR5Meyh61FHz3bvSlO4uez.O', 'Juan', 'Fernandez García', '652325471', true, 'validado', '2021-05-14 08:35:50');
insert into users(nif, email, password, name, last_name, number_phone, enabled, status, created_at) values ('96574852L', 'maria@example.com', '$2a$10$qINz45KQzkLHtzOovvgjn.y1SKh8zfR5Meyh61FHz3bvSlO4uez.O', 'María', 'Rivera García', '965856547', true, 'validado', '2021-05-14 08:35:50');

-- Categories examples
insert into categories(name) values ('Ocio');
insert into categories(name) values ('Gasolina');
insert into categories(name) values ('Restauración');
insert into categories(name) values ('Alimentación');
insert into categories(name) values ('Otros');

-- Bank Account examples
insert into bank_accounts(num_account, balance, enabled, created_at) values ('02601732345271851', '0', true, "2020-02-11 01:00:00");
insert into bank_accounts(num_account, balance, enabled, created_at) values ('07941320452229156', '0', true, "2019-02-11 01:00:00");
insert into bank_accounts(num_account, balance, enabled, created_at) values ('30215465212525412', '0', true, "2019-02-11 01:00:00");
insert into bank_accounts(num_account, balance, enabled, created_at) values ('96582454712536985', '0', true, "2019-02-11 01:00:00");



-- Users - Bank Accounts relation
insert into users_bank_accounts(user_id, bank_account_id) values (1, 1);
insert into users_bank_accounts(user_id, bank_account_id) values (1, 2);
insert into users_bank_accounts(user_id, bank_account_id) values (2, 2);
insert into users_bank_accounts(user_id, bank_account_id) values (3, 4);
insert into users_bank_accounts(user_id, bank_account_id) values (3, 1);
insert into users_bank_accounts(user_id, bank_account_id) values (4, 4);

-- Credit Card examples

insert into credit_cards(num_credit_card, placeholder, type, card_provider, cvv, pin, id_user, expiration_date, created_at) values ('5254646244652394', 'Thomas Conley', 'debito', 'MasterCard', '239', '1234', 1, "2022-02-11", '2021-05-14 08:35:50');
insert into credit_cards(num_credit_card, placeholder, type, card_provider, cvv, pin, id_user, expiration_date, created_at) values ('8254645534652344', 'John Connor', 'credito', 'Visa', '122', '1234', 2, "2022-02-11", '2021-05-14 08:35:50');

-- Transaction examples
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values (350, "pago coche", 'ingreso', 1, 2, "2021-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values (-50, 'alquiler garaje', "pago", 1, 2, "2020-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values (500, "nomina", 'domiciliacion', 2, 1, "2021-04-11 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('350', "taller coche", 'gasto', 1, 2, "2021-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('560', 'alquiler garaje', "gasto", 1, 2, "2020-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('5000', "herencia familiar", 'domiciliacion', 2, 1, "2021-04-11 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('150', "pago movil", 'gasto', 1, 2, "2021-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('50', 'alquiler garaje', "gasto", 1, 2, "2020-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('25', "netflix", 'domiciliacion', 2, 1, "2021-04-11 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('350', "pago coche", 'ingreso', 1, 2, "2021-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('50', 'alquiler garaje', "pago", 1, 2, "2020-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('50', "amazon prime", 'domiciliacion', 2, 1, "2021-04-11 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('150', "pago bar", 'gasto', 1, 2, "2021-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('90', 'pago agua', "gasto", 1, 2, "2020-03-27 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('25', "bizum", 'ingreso', 2, 1, "2021-04-11 01:00:00");


insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('1350', "taller coche", 'gasto', 1, 2, "2021-05-01 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('560', 'alquiler garaje', "gasto", 1, 2, "2020-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('5000', "herencia familiar", 'domiciliacion', 2, 1, "2021-04-11 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('5150', "pago luz", 'gasto', 1, 2, "2021-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('505', 'alquiler garaje', "gasto", 1, 2, "2020-02-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('2235', "netflix", 'domiciliacion', 2, 1, "2021-04-05 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('3450', "pago moto", 'ingreso', 1, 2, "2021-05-17 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('5650', 'casa rural', "pago", 1, 2, "2020-02-12 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('5670', "ropa zara", 'pago', 2, 1, "2021-01-21 01:00:00");

insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('678', "pago gas", 'gasto', 1, 2, "2021-02-15 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('770', 'compra mercadona', "gasto", 1, 2, "2020-01-11 01:00:00");
insert into transactions(importe, concepto, tipo_movimiento, id_bank_account, id_credit_card, created_date) values ('234', "bizum", 'ingreso', 2, 1, "2021-04-08 01:00:00");