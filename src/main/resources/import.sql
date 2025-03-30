INSERT INTO customers (id, username, password, role) VALUES (1, 'admin', '$2a$12$8NR8V.1Q.6tm0EngEWj07eaiE1IPVzzH88E7uvEIahtRc.yui82UG', 'ADMIN');
INSERT INTO customers (id, username, password, role) VALUES (2, 'orhancesmecik', '$2a$12$ynOSqsJXOaKsFzBEZqvaHu25.yKX8kfV1nkVlse5NyjWEH5bEpHYa', 'USER');
INSERT INTO customers (id, username, password, role) VALUES (3, 'customer1', '$2a$12$zNTWH..ra2zmD8R8JdUYs.W9VQy2Mj8abW4S7WJaZ2sYVSwwXG6uq', 'USER');

INSERT INTO assets (id, customer_id, asset_name, size, usable_size) VALUES (1, 1, 'TRY', 100000, 100000);
INSERT INTO assets (id, customer_id, asset_name, size, usable_size) VALUES (2, 2, 'TRY', 50000, 50000);
INSERT INTO assets (id, customer_id, asset_name, size, usable_size) VALUES (3, 2, 'USD', 10, 10);

INSERT INTO orders (id, username, customer_id, asset_name, order_side, size, price, status, create_date) VALUES (1, 'admin', 2, 'AAPL', 'BUY', 5, 150, 'PENDING', NOW());
INSERT INTO orders (id, username, customer_id, asset_name, order_side, size, price, status, create_date) VALUES (2, 'orhancesmecik', 2, 'USD', 'SELL', 2, 155, 'PENDING', NOW());

