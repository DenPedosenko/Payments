# UserTypes inserts
INSERT INTO user_types(name_en, name_ru)
VALUES ('User', 'Пользователь');
INSERT INTO user_types(name_en, name_ru)
VALUES ('Admin', 'Администратор');

#Account_status inserts
INSERT INTO account_status(name_en, name_ru)
VALUES ('Active', 'Активный');
INSERT INTO Account_status(name_en, name_ru)
VALUES ('Blocked', 'Заблокированный');

#Account inserts
INSERT INTO accounts(name_en, name_ru, user_id, account_status_id)
VALUES ('Payment', 'Платежный', 2, 1);
INSERT INTO accounts(name_en, name_ru, user_id, account_status_id)
VALUES ('Bonus', 'Бонусний', 2, 1);
INSERT INTO accounts(name_en, name_ru, user_id, account_status_id)
VALUES ('Payment', 'Платежный', 3, 1);
INSERT INTO accounts(name_en, name_ru, user_id, account_status_id)
VALUES ('Bonus', 'Бонусний', 3, 1);
INSERT INTO accounts(name_en, name_ru, user_id, account_status_id)
VALUES ('Payment', 'Платежный', 4, 2);
INSERT INTO accounts(name_en, name_ru, user_id, account_status_id)
VALUES ('Bonus', 'Бонусний', 4, 2);

#Card inserts
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 1);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 2);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 3);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 4);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 5);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 6);

# UserStatuses inserts
INSERT INTO user_status(name_en, name_ru)
VALUES ('Active', 'Активный');
INSERT INTO user_status(name_en, name_ru)
VALUES ('Blocked', 'Заблокированный');

# Users inserts
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Gendalf', 'Gray', 'the_gratest_mag@example.com', '7777', 2, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Aragorn', 'Elessar', 'elessar@example.com', '1111', 2, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Frodo', 'Bagins', 'mister_frodo@example.com', '1111', 1, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Sauron', 'Maia', 'the_dark_lord@example.com', '6666', 1, 2);

# PaymentTypes inserts
INSERT INTO payment_type(name_en, name_ru)
VALUES ('Communal services', 'Комунальні послуги');
INSERT INTO payment_type(name_en, name_ru)
VALUES ('Replenishment of mobile account', 'Поповнення мобільного рахунку');

# Payment_status inserts
INSERT INTO payment_status(name_en, name_ru)
VALUES ('Prepeared', 'Підготовлений');
INSERT INTO payment_status(name_en, name_ru)
VALUES ('Sent', 'Відправлений');

# Payment inserts
INSERT INTO payments(user_id, account_id, creating_date,  payment_status_id, payment_type_id)
VALUES (2, 1,  '2020-10-9', 1, 1);
INSERT INTO payments(user_id, account_id, creating_date,  payment_status_id, payment_type_id)
VALUES (3, 2,  '2020-10-15', 2, 1);