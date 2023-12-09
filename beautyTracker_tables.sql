DROP DATABASE IF EXISTS beautytracker;
CREATE DATABASE beautytracker;
USE beautytracker;

DROP TABLE IF EXISTS brand;
CREATE TABLE brand (
    brand_name VARCHAR(30) PRIMARY KEY NOT NULL,
    b_description VARCHAR(512),
    country_of_origin VARCHAR(40),
    founding_year INT,
    email VARCHAR(64) UNIQUE,
    tel VARCHAR(20),
    founder VARCHAR(64)
);

INSERT INTO brand (brand_name, b_description, country_of_origin, founding_year, email, tel, founder)
VALUES
('Brand1', 'Description1', 'Country1', 2000, 'email1@example.com', '1234567890', 'Founder1'),
('Brand2', 'Description2', 'Country2', 2010, 'email2@example.com', '9876543210', 'Founder2');




DROP TABLE IF EXISTS type;
CREATE TABLE type (
    type_name ENUM ('skincare', 'makeup', 'haircare', 'fragrances', 'bath&body', 'tools&brushes') PRIMARY KEY NOT NULL
);

INSERT INTO type (type_name)
VALUES
('skincare'),
('makeup'),
('haircare'),
('fragrances'),
('bath&body'),
('tools&brushes');




DROP TABLE IF EXISTS function_table;
CREATE TABLE function_table (
    f_name VARCHAR(30) PRIMARY KEY NOT NULL,
    f_description VARCHAR(512),
    type_name ENUM ('skincare', 'makeup', 'haircare', 'fragrances', 'bath&body', 'tools&brushes') NOT NULL,
    FOREIGN KEY (type_name) REFERENCES type(type_name) ON UPDATE CASCADE
);

INSERT INTO function_table (f_name, f_description, type_name)
VALUES
('Function1', 'Function1 Description', 'skincare'),
('Function2', 'Function2 Description', 'makeup');




DROP TABLE IF EXISTS concern;
CREATE TABLE concern (
    concern_name VARCHAR(30) PRIMARY KEY NOT NULL,
    c_description VARCHAR(512)
);

INSERT INTO concern (concern_name, c_description)
VALUES
('Concern1', 'Concern1 Description'),
('Concern2', 'Concern2 Description');





DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_name VARCHAR(30) PRIMARY KEY NOT NULL,
    price VARCHAR(512) NOT NULL,
    size VARCHAR(10),
    url VARCHAR(512),
    expiration_date DATE,
    concern_name VARCHAR(30) NOT NULL,
    type_name ENUM ('skincare', 'makeup', 'haircare', 'fragrances', 'bath&body', 'tools&brushes') NOT NULL,
    brand_name VARCHAR(30) NOT NULL,
    FOREIGN KEY (concern_name) REFERENCES concern(concern_name) ON UPDATE CASCADE,
    FOREIGN KEY (type_name) REFERENCES type(type_name) ON UPDATE CASCADE,
    FOREIGN KEY (brand_name) REFERENCES brand(brand_name) ON UPDATE CASCADE
);

INSERT INTO product (product_name, price, size, url, expiration_date, concern_name, type_name, brand_name)
VALUES
('Product1', 29.99, '50', 'http://product1.com', '2023-12-31', 'Concern1', 'skincare', 'Brand1'),
('Product2', 19.99, '10', 'http://product2.com', '2023-11-30', 'Concern2', 'makeup', 'Brand2');





DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient (
    ingredient_name VARCHAR(50),
    is_cruelty_free BOOLEAN,
    is_clean_beauty BOOLEAN,
    is_fragrance_free BOOLEAN,
    product_name VARCHAR(30),
    FOREIGN KEY (product_name) REFERENCES product(product_name) ON DELETE CASCADE
);

INSERT INTO ingredient (ingredient_name, is_cruelty_free, is_clean_beauty, is_fragrance_free, product_name)
VALUES
('Ingredient1', true, true, false, 'Product1'),
('Ingredient2', false, true, true, 'Product2');



DROP TABLE IF EXISTS package;
CREATE TABLE package (
    color VARCHAR(50),
    material VARCHAR(50),
    weight DECIMAL(10, 2) CHECK (weight >= 0),
    refill_available BOOLEAN DEFAULT FALSE,
    sustainable_package BOOLEAN DEFAULT FALSE,
    product_name VARCHAR(30),
    FOREIGN KEY (product_name) REFERENCES product(product_name) ON DELETE CASCADE
);

INSERT INTO package (color, material, weight, refill_available, sustainable_package, product_name)
VALUES
('Red', 'Plastic', 0.5, true, true, 'Product1'),
('Blue', 'Glass', 0.3, false, false, 'Product2');



-- Indexes
CREATE INDEX idx_product_concern ON product (concern_name);
CREATE INDEX idx_product_type ON product (type_name);
CREATE INDEX idx_product_brand ON product (brand_name);
CREATE INDEX idx_ingredient_product ON ingredient (product_name);
CREATE INDEX idx_package_product ON package (product_name);






