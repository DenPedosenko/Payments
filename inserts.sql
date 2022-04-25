# UserTypes inserts
INSERT INTO user_types(name_en, name_ua)
VALUES ('User', 'Пользователь');
INSERT INTO user_types(name_en, name_ua)
VALUES ('Admin', 'Администратор');

# UserStatuses inserts
INSERT INTO user_statuses(name_en, name_ua)
VALUES ('Active', 'Активный');
INSERT INTO user_statuses(name_en, name_ua)
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

#Account_status inserts
INSERT INTO account_statuses(name_en, name_ua)
VALUES ('Active', 'Активный');
INSERT INTO Account_statuses(name_en, name_ua)
VALUES ('Blocked', 'Заблокированный');

#Account inserts
INSERT INTO accounts(name_en, name_ua, user_id, account_status_id, balance)
VALUES ('Payment', 'Платежный', 3, 1, 100);
INSERT INTO accounts(name_en, name_ua, user_id, account_status_id, balance)
VALUES ('Bonus', 'Бонусний', 3, 1, 100);
INSERT INTO accounts(name_en, name_ua, user_id, account_status_id, balance)
VALUES ('Payment', 'Платежный', 4, 2, 100);
INSERT INTO accounts(name_en, name_ua, user_id, account_status_id, balance)
VALUES ('Bonus', 'Бонусний', 4, 2, 100);

#Card inserts
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 1);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 2);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 3);
INSERT INTO cards(card_number, exp_date, cvv, account_id)
VALUES ('1111111111111111', '12/12', '205', 4);

# PaymentTypes inserts
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Public utilities', 'Комунальні послуги');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Mobile communications', 'Поповнення мобільного');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Help for Ukrainians', 'Допомога українцям');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Internet', 'Інтернет');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Television', 'Телебачення');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Distribution', 'Дистрибуція');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Security service', 'Охорона');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Kindergartens', 'Дитячі садки');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Transport', 'Транспорт');
INSERT INTO payment_types(name_en, name_ua)
VALUES ('Games', 'Ігри');

# Payment_status inserts
INSERT INTO payment_statuses(name_en, name_ua)
VALUES ('Prepeared', 'Підготовлений');
INSERT INTO payment_statuses(name_en, name_ua)
VALUES ('Sent', 'Відправлений');

# Payment inserts
INSERT INTO payments(user_id, account_id, creating_date,  payment_status_id, payment_type_id, amount)
VALUES (3, 1,  '2020-10-9', 1, 1, 250.00);
INSERT INTO payments(user_id, account_id, creating_date,  payment_status_id, payment_type_id, amount)
VALUES (3, 1,  '2020-10-9 12:00:00', 1, 1, 250.00);
INSERT INTO payments(user_id, account_id, creating_date,  payment_status_id, payment_type_id, amount)
VALUES (3, 2,  '2020-10-15', 2, 1, 100.00);

# Request_status inserts
INSERT INTO request_statuses(name_en, name_ua)
VALUES ('Active', 'Активний');
INSERT INTO request_statuses(name_en, name_ua)
VALUES ('Closed', 'Закритий');

# Requests inserts
INSERT INTO requests(user_id, account_id, creating_date,  request_status_id)
VALUES (4, 4,  '2020-10-9', 1);