use beautytracker;

-- view collection

DELIMITER //

CREATE PROCEDURE DisplayProductCollection()
BEGIN
    SELECT p.product_name, p.price, p.size, p.url, p.expiration_date, 
           c.concern_name, t.type_name, b.brand_name 
    FROM product p
    LEFT JOIN concern c ON p.concern_name = c.concern_name 
    LEFT JOIN type t ON p.type_name = t.type_name 
    LEFT JOIN brand b ON p.brand_name = b.brand_name 
    ORDER BY p.product_name;
END //

DELIMITER ;

-- add product 
DELIMITER //

CREATE PROCEDURE addNewProduct(
    IN p_productName VARCHAR(255),
    IN p_price VARCHAR(512),
    IN p_size VARCHAR(512),
    IN p_url VARCHAR(512),
    IN p_expirationDate DATE,
    IN p_concernName VARCHAR(30),
    IN p_typeName VARCHAR(30),
    IN p_brandName VARCHAR(30)
)
BEGIN
    INSERT INTO product (product_name, price, size, url, expiration_date, concern_name, type_name, brand_name)
    VALUES (p_productName, p_price, p_size, p_url, p_expirationDate, p_concernName, p_typeName, p_brandName);
END //

DELIMITER ;

-- add ingredients
DELIMITER //

CREATE PROCEDURE AddIngredientDetails(
    IN p_ingredient_name VARCHAR(50), 
    IN p_is_cruelty_free BOOLEAN, 
    IN p_is_clean_beauty BOOLEAN, 
    IN p_is_fragrance_free BOOLEAN, 
    IN p_product_name VARCHAR(30)
)
BEGIN
    INSERT INTO ingredient (ingredient_name, is_cruelty_free, is_clean_beauty, is_fragrance_free, product_name) 
    VALUES (p_ingredient_name, p_is_cruelty_free, p_is_clean_beauty, p_is_fragrance_free, p_product_name);
END //

DELIMITER ;

-- add package 
DELIMITER //

CREATE PROCEDURE AddPackageDetails(
    IN p_productName VARCHAR(255),
    IN p_color VARCHAR(255),
    IN p_material VARCHAR(255),
    IN p_weight DECIMAL(10,2),
    IN p_refillAvailable BOOLEAN,
    IN p_sustainablePackage BOOLEAN
)
BEGIN
    INSERT INTO package (product_name, color, material, weight, refill_available, sustainable_package)
    VALUES (p_productName, p_color, p_material, p_weight, p_refillAvailable, p_sustainablePackage);
END //

DELIMITER ;



-- add brand
DELIMITER //

CREATE PROCEDURE AddOrUpdateBrand(
    IN p_brand_name VARCHAR(30),
    IN p_description VARCHAR(512),
    IN p_country VARCHAR(40),
    IN p_founding_year INT,
    IN p_email VARCHAR(64),
    IN p_tel VARCHAR(20),
    IN p_founder VARCHAR(64)
)
BEGIN
    IF EXISTS (SELECT * FROM brand WHERE brand_name = p_brand_name) THEN
        UPDATE brand
        SET b_description = p_description,
            country_of_origin = p_country,
            founding_year = p_founding_year,
            email = p_email,
            tel = p_tel,
            founder = p_founder
        WHERE brand_name = p_brand_name;
    ELSE
        INSERT INTO brand (brand_name, b_description, country_of_origin, founding_year, email, tel, founder)
        VALUES (p_brand_name, p_description, p_country, p_founding_year, p_email, p_tel, p_founder);
    END IF;
END //

DELIMITER ;


-- edit brand

DELIMITER //

CREATE PROCEDURE UpdateBrandDetails(
    IN p_brandName VARCHAR(255),
    IN p_field VARCHAR(255),
    IN p_value VARCHAR(255)
)
BEGIN
    CASE
        WHEN p_field = 'b_description' THEN
            UPDATE brand SET b_description = p_value WHERE brand_name = p_brandName;
        WHEN p_field = 'country_of_origin' THEN
            UPDATE brand SET country_of_origin = p_value WHERE brand_name = p_brandName;
        -- Add similar cases for other fields
        -- ...
    END CASE;
END //

DELIMITER ;


-- edit concern

DELIMITER $$

CREATE PROCEDURE EditConcern(IN concernName VARCHAR(30), IN newDescription VARCHAR(512), IN userConfirmation VARCHAR(3))
BEGIN
    IF newDescription = '' THEN
        IF LOWER(userConfirmation) = 'yes' THEN
            UPDATE concern SET c_description = NULL WHERE concern_name = concernName;
            SELECT 'Description deleted.' AS Result;
        ELSE
            SELECT 'Description update canceled.' AS Result;
        END IF;
    ELSE
        UPDATE concern SET c_description = newDescription WHERE concern_name = concernName;
        IF ROW_COUNT() > 0 THEN
            SELECT CONCAT('Concern "', concernName, '" has been updated.') AS Result;
        ELSE
            SELECT 'Concern not found or could not be updated.' AS Result;
        END IF;
    END IF;
END$$

DELIMITER ;


-- add concern details

DELIMITER //

CREATE PROCEDURE AddOrUpdateConcern(IN concernName VARCHAR(30), IN description VARCHAR(512))
BEGIN
    IF description = '' THEN
        SET description = NULL;
    END IF;

    INSERT INTO concern (concern_name, c_description) 
    VALUES (concernName, description)
    ON DUPLICATE KEY UPDATE c_description = VALUES(c_description);
END //

DELIMITER ;

-- add type

DELIMITER //

CREATE PROCEDURE addOrUpdateType(IN typeName VARCHAR(30), IN typeDescription VARCHAR(512))
BEGIN
    IF EXISTS (SELECT * FROM type WHERE type_name = typeName) THEN
        UPDATE type SET description = typeDescription WHERE type_name = typeName;
    ELSE
        INSERT INTO type (type_name, description) VALUES (typeName, typeDescription);
    END IF;
END //

DELIMITER ;

-- select product 
DELIMITER $$

CREATE PROCEDURE selectProduct(IN prodName VARCHAR(30))
BEGIN
    SELECT product_name, price, size, url, expiration_date, concern_name, type_name, brand_name 
    FROM product 
    WHERE product_name = prodName;
END$$

DELIMITER ;

-- filter keywords

DELIMITER //

CREATE PROCEDURE filterProductsByKeyword(IN searchKeyword VARCHAR(255))
BEGIN
    SELECT DISTINCT product.product_name, product.price, product.size, product.url, product.expiration_date,
                    concern.concern_name, type.type_name, brand.brand_name, function_table.f_name, brand.country_of_origin
    FROM product
    LEFT JOIN concern ON product.concern_name = concern.concern_name
    LEFT JOIN type ON product.type_name = type.type_name
    LEFT JOIN brand ON product.brand_name = brand.brand_name
    LEFT JOIN function_table ON product.type_name = function_table.type_name
    LEFT JOIN ingredient ON product.product_name = ingredient.product_name
    LEFT JOIN package ON product.product_name = package.product_name
    WHERE product.product_name LIKE searchKeyword OR type.type_name LIKE searchKeyword OR brand.brand_name LIKE searchKeyword
          OR concern.concern_name LIKE searchKeyword OR ingredient.ingredient_name LIKE searchKeyword
          OR package.color LIKE searchKeyword OR package.material LIKE searchKeyword
          OR function_table.f_name LIKE searchKeyword OR brand.country_of_origin LIKE searchKeyword;
END //

DELIMITER ;

-- view selected product 

DELIMITER //

CREATE PROCEDURE ViewSelectedProduct(IN productName VARCHAR(30))
BEGIN
    SELECT p.product_name, p.price, p.size, p.url, p.expiration_date, 
           c.concern_name, t.type_name, b.brand_name 
    FROM product p
    LEFT JOIN concern c ON p.concern_name = c.concern_name 
    LEFT JOIN type t ON p.type_name = t.type_name 
    LEFT JOIN brand b ON p.brand_name = b.brand_name 
    WHERE p.product_name = productName;
END //

DELIMITER ;

-- delete product 

DELIMITER //

CREATE PROCEDURE DeleteProduct(IN productName VARCHAR(30))
BEGIN
    DECLARE rowsAffected INT;

    DELETE FROM product WHERE product_name = productName;

    SET rowsAffected = ROW_COUNT();
    IF rowsAffected > 0 THEN
        SELECT 'Product has been deleted.' AS message;
    ELSE
        SELECT 'Product not found or could not be deleted.' AS message;
    END IF;
END //

DELIMITER ;


-- edit product 
DELIMITER //

CREATE PROCEDURE editProduct(IN productName VARCHAR(30), IN fieldToUpdate VARCHAR(30), IN newValue VARCHAR(512))
BEGIN
    IF NOT EXISTS (SELECT * FROM product WHERE product_name = productName) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Product does not exist.';
    ELSE
        -- Verify if the chosen field is valid
        IF fieldToUpdate IN ('price', 'size', 'url', 'expiration_date', 'concern_name', 'type_name', 'brand_name') THEN
            -- Prepare the dynamic SQL query
            SET @query = CONCAT('UPDATE product SET ', fieldToUpdate, ' = ? WHERE product_name = ?');
            PREPARE stmt FROM @query;

            -- Set the appropriate value type based on the field
			CASE fieldToUpdate
				WHEN 'expiration_date' THEN
					IF newValue = '' THEN
						SET @newValue = NULL;
					ELSE
						SET @newValue = STR_TO_DATE(newValue, '%Y-%m-%d');
					END IF;
				ELSE
					SET @newValue = newValue;
			END CASE;

            -- Execute the query
            SET @productName = productName;
            EXECUTE stmt USING @newValue, @productName;
            DEALLOCATE PREPARE stmt;
        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid field selected.';
        END IF;
    END IF;
END//

DELIMITER ;

-- update type

DELIMITER //

CREATE PROCEDURE updateType(IN typeName VARCHAR(30), IN newTypeName VARCHAR(30))
BEGIN
    IF EXISTS (SELECT * FROM type WHERE type_name = typeName) THEN
        UPDATE type SET type_name = newTypeName WHERE type_name = typeName;
        SELECT 'Type updated successfully.' AS message;
    ELSE
        SELECT 'Type not found.' AS message;
    END IF;
END //

DELIMITER ;

-- edit function 
DELIMITER //

CREATE PROCEDURE EditFunction(
    IN oldFunctionName VARCHAR(30),
    IN newFunctionName VARCHAR(30),
    IN newDescription VARCHAR(512),
    IN deleteDescription BOOLEAN
)
BEGIN
    IF deleteDescription THEN
        UPDATE function_table
        SET f_name = newFunctionName, f_description = NULL
        WHERE f_name = oldFunctionName;
    ELSE
        UPDATE function_table
        SET f_name = newFunctionName, f_description = newDescription
        WHERE f_name = oldFunctionName;
    END IF;
END //

DELIMITER ;

-- edit ingredient 
DELIMITER //

CREATE PROCEDURE edit_ingredient(
    IN ingredient_name VARCHAR(50), 
    IN update_field VARCHAR(30), 
    IN new_value VARCHAR(50)
)
BEGIN
    DECLARE field_type VARCHAR(10);

    -- Determine the type of field (either 'string' or 'boolean')
    CASE update_field
        WHEN 'ingredient_name' THEN SET field_type = 'string';
        WHEN 'is_cruelty_free' THEN SET field_type = 'boolean';
        WHEN 'is_clean_beauty' THEN SET field_type = 'boolean';
        WHEN 'is_fragrance_free' THEN SET field_type = 'boolean';
        ELSE SET field_type = 'invalid';
    END CASE;

    -- If field type is invalid, exit the procedure
		IF field_type = 'invalid' THEN
			SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'An invalid field has been selected.';
		END IF;


    -- Update logic
    SET @query = CONCAT('UPDATE ingredient SET ', update_field, ' = ? WHERE ingredient_name = ?');
    PREPARE stmt FROM @query;

    -- Assign values to user-defined variables
    SET @new_value_param = new_value;
    SET @ingredient_name_param = ingredient_name;

    -- Set value based on field type
    IF field_type = 'boolean' THEN
        SET @new_value_bool = (new_value = 'true');
        EXECUTE stmt USING @new_value_bool, @ingredient_name_param;
    ELSE
        EXECUTE stmt USING @new_value_param, @ingredient_name_param;
    END IF;

    DEALLOCATE PREPARE stmt;
END //

DELIMITER ;

-- edit package 
DELIMITER $$

CREATE PROCEDURE update_package(IN p_product_name VARCHAR(30), IN p_field VARCHAR(20), IN p_value VARCHAR(50))
BEGIN
    CASE
        WHEN p_field = 'color' OR p_field = 'material' THEN
            SET @query = CONCAT('UPDATE package SET ', p_field, ' = ? WHERE product_name = ?');
            PREPARE stmt FROM @query;
            SET @value = p_value, @productName = p_product_name;
            EXECUTE stmt USING @value, @productName;
            DEALLOCATE PREPARE stmt;
        WHEN p_field = 'weight' THEN
            SET @query = 'UPDATE package SET weight = ? WHERE product_name = ?';
            PREPARE stmt FROM @query;
            SET @weight = CAST(p_value AS DECIMAL(10, 2)), @productName = p_product_name;
            EXECUTE stmt USING @weight, @productName;
            DEALLOCATE PREPARE stmt;
        WHEN p_field = 'refill_available' OR p_field = 'sustainable_package' THEN
            SET @query = CONCAT('UPDATE package SET ', p_field, ' = ? WHERE product_name = ?');
            PREPARE stmt FROM @query;
            SET @boolValue = (p_value = 'yes'), @productName = p_product_name;
            EXECUTE stmt USING @boolValue, @productName;
            DEALLOCATE PREPARE stmt;
    END CASE;
END$$

DELIMITER ;
