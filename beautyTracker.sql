CREATE TABLE brand (
    brand_name VARCHAR(30) PRIMARY KEY,
    b_description VARCHAR(512),
    country_of_origin VARCHAR(40),
    founding_year INT(4),
    email VARCHAR(64),
    tel INT(20),
    founder VARCHAR(64)
);


CREATE TABLE type (
    type_name ENUM ('skincare', 'makeup', 'haircare', 'fragrances', 'bath&body', 'tools&brushes') PRIMARY KEY
);


CREATE TABLE function_table (
    f_name VARCHAR(30) PRIMARY KEY,
    f_description VARCHAR(512),
    
    -- Foreign key
    type_name ENUM ('skincare', 'makeup', 'haircare', 'fragrances', 'bath&body', 'tools&brushes'),
    FOREIGN KEY (type_name) REFERENCES type(type_name)
);


CREATE TABLE concern (
    concern_name VARCHAR(30) PRIMARY KEY,
    c_description VARCHAR(512)
);



CREATE TABLE product (
    product_name VARCHAR(30) PRIMARY KEY,
    price DECIMAL(10, 2),
    size INT,
    url VARCHAR(512),
    expiration_date DATE,  -- Assuming expiration date is a date field

    -- Foreign keys
    concern_name VARCHAR(30),
    type_name ENUM ('skincare', 'makeup', 'haircare', 'fragrances', 'bath&body', 'tools&brushes'),
    brand_name VARCHAR(30),

    FOREIGN KEY (concern_name) REFERENCES concern(concern_name),
    FOREIGN KEY (type_name) REFERENCES type(type_name),
    FOREIGN KEY (brand_name) REFERENCES brand(brand_name)
);


CREATE TABLE ingredient (
    ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
    ingredient_name VARCHAR(50) NOT NULL,
    is_cruelty_free BOOLEAN,
    is_clean_beauty BOOLEAN,
    is_fragrance_free BOOLEAN,
    
    product_name VARCHAR(30),
    FOREIGN KEY (product_name) REFERENCES product(product_name)
);


DROP TABLE IF EXISTS package;
CREATE TABLE package (
    product_name VARCHAR(30) PRIMARY KEY,
    color VARCHAR(50),
    material VARCHAR(50),
    weight DECIMAL(10, 2),
    refill_available BOOLEAN,
    sustainable_package BOOLEAN,
    FOREIGN KEY (product_name) REFERENCES product(product_name)
);





CREATE INDEX idx_product_concern ON product (concern_name);
CREATE INDEX idx_product_type ON product (type_name);
CREATE INDEX idx_product_brand ON product (brand_name);

CREATE INDEX idx_ingredient_product ON ingredient (product_name);

CREATE INDEX idx_package_product ON package (product_name);











