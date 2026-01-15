-- 1. Insert Categories first
INSERT INTO category (id, name, description)
VALUES
    (100, 'Electronics', 'Gadgets, computers, and phones'),
    (101, 'Books', 'Paperback, hardback, and ebooks'),
    (102, 'Home & Kitchen', 'Furniture, decor, and cookware');

--

-- 2. Insert Products (Now including the 'available_quantity' value)
INSERT INTO product (id, name, description, price, available_quantity, category_id)
VALUES
    (500, 'Smartphone X', 'Latest model with 5G', 999.99, 50, 100),
    (501, '4K Monitor', '27-inch IPS display', 350.00, 20, 100),
    (502, 'Microservices Patterns', 'Best book for learning architecture', 45.50, 100, 101),
    (503, 'Clean Code', 'A guide to software craftsmanship', 30.00, 150, 101),
    (504, 'Gaming Chair', 'Ergonomic chair for long coding sessions', 199.99, 5, 102);

-- 3. Reset Sequences (Crucial to prevent future errors)
-- Note: Adjust 'category_seq' or 'product_seq' if your DB uses different names (e.g., product_id_seq)
SELECT setval('category_seq', (SELECT MAX(id) FROM category));
SELECT setval('product_seq', (SELECT MAX(id) FROM product));