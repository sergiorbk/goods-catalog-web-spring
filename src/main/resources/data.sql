-- Вставка даних у таблицю "users"
INSERT INTO users (username, password, role) VALUES
('admin', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'ADMIN'),
('user1', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user2', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user3', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user4', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user5', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user6', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user7', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user8', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user9', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER'),
('user10', '$2a$12$CsgRSpO2EZ9Nf/UMqXJDduAQSVIcECBVW1bpSYlRfuiO8m30m1vNa', 'USER');

-- Вставка даних у таблицю "categories"
INSERT INTO categories (name, description, parent_id) VALUES
('Electronics', 'All electronic items', NULL),
('Smartphones', 'Smartphones and accessories', 1),
('Laptops', 'Laptops and accessories', 1),
('Home Appliances', 'Various home appliances', NULL),
('Kitchen Appliances', 'Appliances for kitchen', 4),
('Televisions', 'All types of televisions', 1),
('Accessories', 'Various electronic accessories', 1),
('Cameras', 'Digital and film cameras', NULL),
('Drones', 'Drones and accessories', 8),
('Wearables', 'Smartwatches and fitness bands', 1);

-- Вставка даних у таблицю "products"
INSERT INTO products (name, description, price, category_id) VALUES
('iPhone 14', 'Latest iPhone model with enhanced features', 999.99, 2),
('Samsung Galaxy S23', 'New Samsung flagship smartphone', 899.99, 2),
('Google Pixel 7', 'Smartphone with excellent camera', 799.99, 2),
('MacBook Pro', 'Apple laptop with M1 chip', 1499.99, 3),
('Dell XPS 13', 'High-performance Windows laptop', 1299.99, 3),
('HP Spectre x360', 'Convertible laptop with great battery life', 1199.99, 3),
('Sony 55" 4K TV', 'Smart TV with vibrant display', 699.99, 6),
('LG OLED TV', 'Ultra HD OLED TV', 1999.99, 6),
('Nikon D3500', 'Entry-level digital SLR camera', 499.99, 8),
('Canon EOS R', 'Full-frame mirrorless camera', 1399.99, 8),
('DJI Mavic Air 2', 'Compact drone with 4K video', 799.99, 9),
('Apple Watch Series 8', 'Smartwatch with health tracking features', 399.99, 10),
('Fitbit Charge 5', 'Fitness tracker with GPS', 179.99, 10),
('JBL Flip 5', 'Portable Bluetooth speaker', 99.99, 7),
('Samsung Galaxy Buds', 'Wireless earbuds with great sound', 149.99, 7),
('Razer Gaming Mouse', 'High precision gaming mouse', 59.99, 7),
('Logitech Webcam', 'Full HD webcam for streaming', 79.99, 7),
('Bose Noise Cancelling Headphones', 'Over-ear headphones with noise cancellation', 299.99, 7),
('Sony WH-1000XM4', 'Wireless noise cancelling headphones', 349.99, 7),
('Kindle Paperwhite', 'E-reader with built-in light', 139.99, 1),
('Nest Hub', 'Smart display with Google Assistant', 99.99, 1);

-- Вставка даних у таблицю "product_images"
INSERT INTO product_images (product_id, image_url) VALUES
(1, 'https://example.com/iphone14-front.jpg'),
(1, 'https://example.com/iphone14-back.jpg'),
(2, 'https://example.com/galaxy-s23-front.jpg'),
(3, 'https://example.com/pixel7.jpg'),
(4, 'https://example.com/macbookpro.jpg'),
(5, 'https://example.com/dell-xps-13.jpg'),
(6, 'https://example.com/hp-spectre.jpg'),
(7, 'https://example.com/sony-tv.jpg'),
(8, 'https://example.com/lg-oled-tv.jpg'),
(9, 'https://example.com/nikon-d3500.jpg'),
(10, 'https://example.com/canon-eos-r.jpg'),
(11, 'https://example.com/dji-mavic-air-2.jpg'),
(12, 'https://example.com/apple-watch-series-8.jpg'),
(13, 'https://example.com/fitbit-charge-5.jpg'),
(14, 'https://example.com/jbl-flip-5.jpg'),
(15, 'https://example.com/samsung-galaxy-buds.jpg'),
(16, 'https://example.com/razer-gaming-mouse.jpg'),
(17, 'https://example.com/logitech-webcam.jpg'),
(18, 'https://example.com/bose-headphones.jpg'),
(19, 'https://example.com/sony-wh-1000xm4.jpg'),
(20, 'https://example.com/kindle-paperwhite.jpg'),
(21, 'https://example.com/nest-hub.jpg');