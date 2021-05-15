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
insert into bank_accounts(num_account, balance, enabled, created_date) values ('02601732345271851', '500', true, "2020-02-11 01:00:00");
insert into bank_accounts(num_account, balance, enabled, created_date) values ('07941320452229156', '1500', true, "2019-02-11 01:00:00");
insert into bank_accounts(num_account, balance, enabled, created_date) values ('30215465212525412', '15000', true, "2019-02-11 01:00:00");
insert into bank_accounts(num_account, balance, enabled, created_date) values ('96582454712536985', '20000', true, "2019-02-11 01:00:00");



-- Users - Bank Accounts relation
insert into users_bank_accounts(user_id, bank_account_id) values (1, 1);
insert into users_bank_accounts(user_id, bank_account_id) values (1, 2);
insert into users_bank_accounts(user_id, bank_account_id) values (2, 2);
insert into users_bank_accounts(user_id, bank_account_id) values (3, 4);
insert into users_bank_accounts(user_id, bank_account_id) values (3, 1);
insert into users_bank_accounts(user_id, bank_account_id) values (4, 4);

-- Credit Card examples

insert into credit_cards(id, num_credit_card, placeholder, type, card_provider, cvv, pin, id_user, expiration_date) values (1, '5254646244652394', 'Thomas Conley', 'debit', 'MasterCard', '239', '1234', 1, "2041-02-11 01:00:00");
insert into credit_cards(id, num_credit_card, placeholder, type, card_provider, cvv, pin, id_user, expiration_date) values (2, '8254645534652344', 'John Connor', 'debit', 'Visa', '122', '1234', 2, "2031-07-11 01:00:00");

-- Transaction examples
insert into transactions(id, importe, concepto, tipo_movimiento, id_bank_account, created_date) values (1, '350', "pago coche", 'ingreso', 1, "2021-02-11 01:00:00");
insert into transactions(id, importe, concepto, tipo_movimiento, id_bank_account, created_date) values (3, '50', 'alquiler garaje', "pago", 1, "2020-02-11 01:00:00");
insert into transactions(id, importe, concepto, tipo_movimiento, id_bank_account, created_date) values (2, '500', "nomina", 'domiciliacion', 2, "2021-04-11 01:00:00");


