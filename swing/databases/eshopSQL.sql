--TODO: swith IDs from SERIAL to IDENTITY
--TODO: make shipping 0 for order over 100 EUR
--TODO: increase shipping for heavier stuff
--TODO: figure out optargs to functions
--TODO: figure out how to do discounts
--TODO: if order has shipped, add tracking number

--STORED PROCEDURES

CREATE OR REPLACE FUNCTION create_tables() RETURNS void AS $$

    CREATE TABLE if not exists Addresses (
        AddressID SERIAL PRIMARY KEY NOT NULL,
        AddressCountry VARCHAR NOT NULL,
        AddressCity VARCHAR NOT NULL,
        AddressZipcode VARCHAR NOT NULL,
        Address1 VARCHAR NOT NULL,
        Address2 VARCHAR,
        AddressPhone VARCHAR NOT NULL,
        AddressPhone2 VARCHAR
    );

    CREATE TABLE if not exists Users (
        UserID SERIAL PRIMARY KEY NOT NULL,
        UserNickName VARCHAR NOT NULL,
        UserEmail VARCHAR NOT NULL,
        UserPassword VARCHAR NOT NULL,
        UserFirstName VARCHAR NOT NULL,
        UserLastName VARCHAR NOT NULL,
        UserAddressID SERIAL REFERENCES Addresses(AddressID) NOT NULL,
        UserAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        UserUpdated TIMESTAMP NOT NULL DEFAULT NOW(),
        UserIsAdmin BOOLEAN NOT NULL DEFAULT FALSE,
        UserBecameAdmin TIMESTAMP
    );

    CREATE TABLE if not exists Categories (
        CategoryID SERIAL PRIMARY KEY NOT NULL,
        CategoryName VARCHAR NOT NULL,
        CategoryDescription VARCHAR NOT NULL DEFAULT ''
    );

    CREATE TABLE if not exists Manufacturers (
        ManufacturerID SERIAL PRIMARY KEY NOT NULL,
        ManufacturerName VARCHAR NOT NULL,
        ManufacturerEmail VARCHAR NOT NULL,
        ManufacturerAddress VARCHAR NOT NULL,
        ManufacturerPhone VARCHAR NOT NULL
    );

    CREATE TABLE if not exists Products (
        --ProductID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
        ProductID SERIAL PRIMARY KEY NOT NULL,
        ProductTitle VARCHAR NOT NULL,
        ProductPrice FLOAT NOT NULL,
        ProductCategoryID SERIAL REFERENCES Categories(CategoryID),
        ProductManufacturerID SERIAL REFERENCES Manufacturers(ManufacturerID),
        ProductDescription VARCHAR NOT NULL DEFAULT '',
        ProductVersion FLOAT NOT NULL DEFAULT 1.0,
        ProductWeight FLOAT,
        ProductWeightClass SMALLINT,
        ProductOnSale BOOLEAN NOT NULL DEFAULT FALSE,
        ProductInStock BOOLEAN NOT NULL DEFAULT FALSE
    );

    CREATE TABLE if not exists Orders (
        OrderID SERIAL PRIMARY KEY NOT NULL,
        OrderUserID SERIAL REFERENCES Users(UserID) NOT NULL,
        OrderCost FLOAT NOT NULL DEFAULT 0.0,
        OrderShippingCost FLOAT NOT NULL DEFAULT 5.0,
        OrderTax FLOAT NOT NULL DEFAULT 24.0,
        OrderTotalCost FLOAT NOT NULL DEFAULT 0.0,
        OrderBillingAddressID SERIAL REFERENCES Addresses(AddressID),
        OrderShippingAddressID SERIAL REFERENCES Addresses(AddressID),
        OrderIsFinal BOOLEN NOT NULL DEFAULT FALSE,
        OrderHasShipped BOOLEAN NOT NULL DEFAULT FALSE,
        OrderHasArrived BOOLEAN NOT NULL DEFAULT FALSE,
        OrderTrackingNumber VARCHAR
    );

    CREATE TABLE if not exists OrderedProducts (
        OrderedProductID SERIAL PRIMARY KEY NOT NULL,
        OrderID SERIAL NOT NULL,
        ProductID SERIAL NOT NULL,
        ProductQuantity INTEGER NOT NULL DEFAULT 1,
        ProductTotalCost FLOAT NOT NULL,
        Constraint Ordered_OrderID FOREIGN KEY(OrderID) REFERENCES Orders on delete cascade on update cascade,
        Constraint Ordered_ProductID FOREIGN KEY(ProductID) REFERENCES Products on delete cascade on update cascade
    );

$$ LANGUAGE SQL;

SELECT create_tables();

CREATE OR REPLACE FUNCTION create_audit_tables() RETURNS void AS $$
    CREATE TABLE if not exists UserAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        UserID SERIAL NOT NULL
    );
    CREATE TABLE if not exists CategoryAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        CategoryID SERIAL NOT NULL
    );
    CREATE TABLE if not exists ManufacturerAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        ManufacturerID SERIAL NOT NULL
    );
    CREATE TABLE if not exists ProductAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        ProductID SERIAL NOT NULL
    );
    CREATE TABLE if not exists OrderAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        OrderID SERIAL NOT NULL
    );
    CREATE TABLE if not exists AddressAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        AddressID SERIAL NOT NULL
    );
/*
    CREATE TABLE if not exists OrderedProductsAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        --TODO: fill in after figuring out primary key for OrderedProducts
    );
*/
$$ LANGUAGE SQL;

SELECT create_audit_tables();

CREATE OR REPLACE FUNCTION process_order_audit() RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO OrderAudit SELECT 'D', now(), user, OLD.*;
            RETURN OLD;
        ELSEIF (TG_OP = 'UPDATE') THEN
            INSERT INTO OrderAudit SELECT 'U', now(), user, NEW.*;
            RETURN NEW;
        ELSEIF (TG_OP = 'INSERT') THEN
            INSERT INTO OrderAudit SELECT 'I', now(), user, NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER order_audit AFTER INSERT OR UPDATE OR DELETE ON Orders FOR EACH ROW EXECUTE PROCEDURE process_order_audit();

CREATE OR REPLACE FUNCTION process_user_audit() RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO UserAudit SELECT 'D', now(), user, OLD.*;
            RETURN OLD;
        ELSEIF (TG_OP = 'UPDATE') THEN
            INSERT INTO UserAudit SELECT 'U', now(), user, NEW.*;
            RETURN NEW;
        ELSEIF (TG_OP = 'INSERT') THEN
            INSERT INTO UserAudit SELECT 'I', now(), user, NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER user_audit AFTER INSERT OR UPDATE OR DELETE ON Users FOR EACH ROW EXECUTE PROCEDURE process_user_audit();

CREATE OR REPLACE FUNCTION process_address_audit() RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO AddressAudit SELECT 'D', now(), user, OLD.*;
            RETURN OLD;
        ELSEIF (TG_OP = 'UPDATE') THEN
            INSERT INTO AddressAudit SELECT 'U', now(), user, NEW.*;
            RETURN NEW;
        ELSEIF (TG_OP = 'INSERT') THEN
            INSERT INTO AddressAudit SELECT 'I', now(), user, NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER address_audit AFTER INSERT OR UPDATE OR DELETE ON Addresses FOR EACH ROW EXECUTE PROCEDURE process_address_audit();

CREATE OR REPLACE FUNCTION process_category_audit() RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO CategoryAudit SELECT 'D', now(), user, OLD.*;
            RETURN OLD;
        ELSEIF (TG_OP = 'UPDATE') THEN
            INSERT INTO CategoryAudit SELECT 'U', now(), user, NEW.*;
            RETURN NEW;
        ELSEIF (TG_OP = 'INSERT') THEN
            INSERT INTO CategoryAudit SELECT 'I', now(), user, NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER category_audit AFTER INSERT OR UPDATE OR DELETE ON Categories FOR EACH ROW EXECUTE PROCEDURE process_category_audit();

CREATE OR REPLACE FUNCTION process_manufacturer_audit() RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO ManufacturerAudit SELECT 'D', now(), user, OLD.*;
            RETURN OLD;
        ELSEIF (TG_OP = 'UPDATE') THEN
            INSERT INTO ManufacturerAudit SELECT 'U', now(), user, NEW.*;
            RETURN NEW;
        ELSEIF (TG_OP = 'INSERT') THEN
            INSERT INTO ManufacturerAudit SELECT 'I', now(), user, NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER manufacturer_audit AFTER INSERT OR UPDATE OR DELETE ON Manufacturers FOR EACH ROW EXECUTE PROCEDURE process_manufacturers_audit();

CREATE OR REPLACE FUNCTION process_product_audit() RETURNS TRIGGER AS $$
    BEGIN
        IF (TG_OP = 'DELETE') THEN
            INSERT INTO ProductAudit SELECT 'D', now(), user, OLD.*;
            RETURN OLD;
        ELSEIF (TG_OP = 'UPDATE') THEN
            INSERT INTO ProductAudit SELECT 'U', now(), user, NEW.*;
            RETURN NEW;
        ELSEIF (TG_OP = 'INSERT') THEN
            INSERT INTO ProductAudit SELECT 'I', now(), user, NEW.*;
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER product_audit AFTER INSERT OR UPDATE OR DELETE ON Products FOR EACH ROW EXECUTE PROCEDURE process_product_audit();

--                                     (userid )
CREATE OR REPLACE FUNCTION create_order(integer) RETURNS INTEGER AS $$
    INSERT INTO Orders (
        OrderUserID
    ) VALUES ($1) RETURNING OrderID;
$$ LANGUAGE SQL;

--                                          (orderid  baddrid  saddrid)
CREATE OR REPLACE FUNCTION set_order_address(integer, integer, integer) RETURNS void AS $$
    UPDATE Orders
    SET OrderBillingAddressID = $2, OrderShippingAddressID = $3
    WHERE OrderID == $1;
$$ LANGUAGE SQL;

--                                          (orderid  prodid   prodqty)
CREATE OR REPLACE FUNCTION add_item_to_order(integer, integer, integer) RETURNS void AS $$
    INSERT INTO OrderedProducts (
        OrderID,
        ProductID,
        ProductQuantity
    ) VALUES ($1, $2, $3);
$$ LANGUAGE SQL;

--                                       (orderid)
CREATE OR REPLACE FUNCTION finalize_order(integer) RETURNS void AS $$
    UPDATE Orders
    SET OrderIsFinal = TRUE
    WHERE OrderID = $1 AND OrderBillingAddressID IS NOT NULL AND OrderShippingAddressID IS NOT NULL;
$$ LANGUAGE SQL;

--                                 (nick     email    passwd   fname    lname  )
CREATE OR REPLACE FUNCTION add_user(varchar, varchar, varchar, varchar, varchar) RETURNS void AS $$
    INSERT INTO Users (
        UserNickName,
        UserEmail,
        UserPassword,
        UserFirstName,
        UserLastName
    ) VALUES ($1, $2, $3, $4, $5);
$$ LANGUAGE SQL;

--                                        (userid )
CREATE OR REPLACE FUNCTION make_user_admin(integer) RETURNS void AS $$
    UPDATE Users
    SET UserIsAdmin = TRUE, UserBecameAdmin = NOW()
    WHERE UserID == $1;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION assign_weight_class() RETURNS void AS $$
    UPDATE Products
    SET ProductWeightClass = 5, ProductUpdated = NOW()
    WHERE ProductWeight >= 20.0;

    UPDATE Products
    SET ProductWeightClass = 4, ProductUpdated = NOW()
    WHERE ProductWeight < 20.0;

    UPDATE Products
    SET ProductWeightClass = 3, ProductUpdated = NOW()
    WHERE ProductWeight < 10.5;

    UPDATE Products
    SET ProductWeightClass = 2, ProductUpdated = NOW()
    WHERE ProductWeight < 5.5;

    UPDATE Products
    SET ProductWeightClass = 1, ProductUpdated = NOW()
    WHERE ProductWeight < 1.1;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_shipped_orders() RETURNS SETOF Orders AS $$
    SELECT * FROM Orders o WHERE o.OrderHasShipped = TRUE AND o.OrderHasArrived = FALSE;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_unshipped_orders() RETURNS SETOF Orders AS $$
    SELECT * FROM Orders o WHERE o.OrderHasShipped = FALSE AND o.OrderHasArrived = FALSE;
$$ LANGUAGE SQL;

--                                    (title    price  catID    manufID  desc     vers   weight sale     stock  )
CREATE OR REPLACE FUNCTION add_product(varchar, float, integer, integer, varchar, float, float, boolean, boolean) RETURNS void AS $$
    INSERT INTO Products (
        ProductTitle,
        ProductPrice,
        ProductCategoryID,
        ProductManufacturerID,
        ProductDescription,
        ProductVersion,
        ProductWeight,
        ProductOnSale,
        ProductInStock
    ) VALUES($1, $2, $3, $4, $5, $6, $7, $8, $9);
$$ LANGUAGE SQL;

--                                                      (title    price  manufID  desc     vers   weight sale     stock  )
CREATE OR REPLACE FUNCTION add_product_with_manufacturer(varchar, float, integer, varchar, float, float, boolean, boolean) RETURNS void AS $$
    INSERT INTO Products (
        ProductTitle,
        ProductPrice,
        ProductManufacturerID,
        ProductDescription,
        ProductVersion,
        ProductWeight,
        ProductOnSale,
        ProductInStock
    ) VALUES($1, $2, $3, $4, $5, $6, $7, $8);
$$ LANGUAGE SQL;

--                                                  (title    price  catID    desc     vers   weight sale     stock  )
CREATE OR REPLACE FUNCTION add_product_with_category(varchar, float, integer, varchar, float, float, boolean, boolean) RETURNS void AS $$
    INSERT INTO Products (
        ProductTitle,
        ProductPrice,
        ProductCategoryID,
        ProductDescription,
        ProductVersion,
        ProductWeight,
        ProductOnSale,
        ProductInStock
    ) VALUES($1, $2, $3, $4, $5, $6, $7, $8);
$$ LANGUAGE SQL;

--                                                 (prodid )
CREATE OR REPLACE FUNCTION get_product(integer) RETURNS Products AS $$
    SELECT * FROM Products p WHERE p.ProductID = $1;
$$ LANGUAGE SQL;

--                                                 (prodid )
CREATE OR REPLACE FUNCTION get_product_stock_status(integer) RETURNS BOOLEAN AS $$
    SELECT ProductInStock FROM Products p WHERE p.ProductID = $1;
$$ LANGUAGE SQL;

--                                     (name     desc   )
CREATE OR REPLACE FUNCTION add_category(varchar, varchar) RETURNS void AS $$
    INSERT INTO Categories (
        CategoryName,
        CategoryDescription
    ) VALUES ($1, $2);
$$ LANGUAGE SQL;

--                                         (name     email    address  phone  )
CREATE OR REPLACE FUNCTION add_manufacturer(varchar, varchar, varchar, varchar) RETURNS void AS $$
    INSERT INTO Manufacturers (
        ManufacturerName,
        ManufacturerEmail,
        ManufacturerAddress,
        ManufacturerPhone
    ) VALUES ($1, $2, $3, $4);
$$ LANGUAGE SQL;
