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

DROP TABLE IF EXISTS type;
CREATE TABLE type (
        type_name VARCHAR(30) PRIMARY KEY NOT NULL
);

INSERT INTO type (type_name) VALUES ('skincare'), ('makeup'), ('haircare'), ('fragrances'), ('bath&body'), ('tools&brushes');



DROP TABLE IF EXISTS function_table;
CREATE TABLE function_table (
    f_name VARCHAR(30) PRIMARY KEY NOT NULL,
    f_description VARCHAR(512),
    type_name VARCHAR(30) NOT NULL,
    FOREIGN KEY (type_name) REFERENCES type(type_name) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS concern;
CREATE TABLE concern (
    concern_name VARCHAR(30) PRIMARY KEY NOT NULL,
    c_description VARCHAR(512)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_name VARCHAR(30) PRIMARY KEY NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    size VARCHAR(10),
    url VARCHAR(512),
    expiration_date DATE,
    concern_name VARCHAR(30) NOT NULL,
    type_name VARCHAR(30) NOT NULL,
    brand_name VARCHAR(30) NOT NULL,
    FOREIGN KEY (concern_name) REFERENCES concern(concern_name) ON UPDATE RESTRICT,
    FOREIGN KEY (type_name) REFERENCES type(type_name) ON UPDATE RESTRICT,
    FOREIGN KEY (brand_name) REFERENCES brand(brand_name) ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient (
    ingredient_name VARCHAR(50),
    is_cruelty_free BOOLEAN,
    is_clean_beauty BOOLEAN,
    is_fragrance_free BOOLEAN,
    product_name VARCHAR(30),
    FOREIGN KEY (product_name) REFERENCES product(product_name) ON DELETE RESTRICT
);

DROP TABLE IF EXISTS package;
CREATE TABLE package (
    color VARCHAR(50),
    material VARCHAR(50),
    weight DECIMAL(10, 2) CHECK (weight >= 0),
    refill_available BOOLEAN DEFAULT FALSE,
    sustainable_package BOOLEAN DEFAULT FALSE,
    product_name VARCHAR(30),
    FOREIGN KEY (product_name) REFERENCES product(product_name) ON DELETE RESTRICT
);

-- Indexes
CREATE INDEX idx_product_concern ON product (concern_name);
CREATE INDEX idx_product_type ON product (type_name);
CREATE INDEX idx_product_brand ON product (brand_name);
CREATE INDEX idx_ingredient_product ON ingredient (product_name);
CREATE INDEX idx_package_product ON package (product_name);
