INSERT INTO customer (id, identification) VALUES (1, 'John');
INSERT INTO customer (id, identification) VALUES (2, 'Rene');
INSERT INTO balance (id, customer_identification, balance_mark, balance) VALUES (1, 'John', 'LAST', 999);
INSERT INTO balance (id, customer_identification, balance_mark, balance) VALUES (2, 'John', NULL, 90);
INSERT INTO balance (id, customer_identification, balance_mark, balance) VALUES (3, 'John', NULL, 45);
INSERT INTO balance (id, customer_identification, balance_mark, balance) VALUES (4, 'Rene', 'LAST', 290);