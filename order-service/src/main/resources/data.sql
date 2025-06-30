-- Insert Orders
INSERT INTO orders (id, order_stauts, total_price) VALUES (1, 'PENDING', 500.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (2, 'CONFIRMED', 1500.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (3, 'CANCELLED', 0.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (4, 'PENDING', 700.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (5, 'CONFIRMED', 2200.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (6, 'PENDING', 950.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (7, 'CONFIRMED', 1300.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (8, 'CANCELLED', 0.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (9, 'PENDING', 1850.00);
INSERT INTO orders (id, order_stauts, total_price) VALUES (10, 'CONFIRMED', 2750.00);

-- Insert OrderItems for Order 1
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (1, 101, 2, 1);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (2, 102, 1, 1);

-- Insert OrderItems for Order 2
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (3, 103, 3, 2);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (4, 104, 2, 2);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (5, 105, 1, 2);

-- Insert OrderItems for Order 3 (Cancelled - no items)

-- Insert OrderItems for Order 4
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (6, 106, 1, 4);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (7, 107, 4, 4);

-- Insert OrderItems for Order 5
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (8, 108, 2, 5);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (9, 109, 3, 5);

-- Insert OrderItems for Order 6
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (10, 110, 1, 6);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (11, 111, 2, 6);

-- Insert OrderItems for Order 7
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (12, 112, 5, 7);

-- Insert OrderItems for Order 8 (Cancelled - no items)

-- Insert OrderItems for Order 9
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (13, 113, 2, 9);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (14, 114, 3, 9);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (15, 115, 1, 9);

-- Insert OrderItems for Order 10
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (16, 116, 4, 10);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (17, 117, 2, 10);
INSERT INTO order_item (id, product_id, quanity, order_id) VALUES (18, 118, 1, 10);
