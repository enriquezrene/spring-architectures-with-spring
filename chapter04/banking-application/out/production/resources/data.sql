INSERT INTO customer (id_customer, identification, full_name) VALUES (1, 'ABC111', 'John Smith');
INSERT INTO customer (id_customer, identification, full_name) VALUES (2, 'ABC112', 'Rene Enriquez');
INSERT INTO balance (id_balance, id_customer, balance_mark, balance) VALUES (101, 1, 'LAST', 999);
INSERT INTO balance (id_balance, id_customer, balance_mark, balance) VALUES (102, 1, NULL, 90);
INSERT INTO balance (id_balance, id_customer, balance_mark, balance) VALUES (103, 1, NULL, 45);
INSERT INTO balance (id_balance, id_customer, balance_mark, balance) VALUES (104, 2, 'LAST', 290);