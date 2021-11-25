--TODO: swith IDs from SERIAL to IDENTITY
--TODO: function to make shipping 0 over 50 EUR and increase up to 20 for heavy stuff
--TODO: figure out weight classes
--TODO: figure out optargs to functions
--TODO: figure out how to do discounts
--TODO: add users

CREATE OR REPLACE FUNCTION create_tables() RETURNS void AS $$
    CREATE TABLE if not exists Categories (
        CategoryID SERIAL PRIMARY KEY NOT NULL,
        CategoryName VARCHAR NOT NULL,
        CategoryDescription VARCHAR NOT NULL DEFAULT '',
        CategoryAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        CategoryUpdated TIMESTAMP NOT NULL DEFAULT NOW()
    );

    CREATE TABLE if not exists Manufacturers (
        ManufacturerID SERIAL PRIMARY KEY NOT NULL,
        ManufacturerName VARCHAR NOT NULL,
        ManufacturerEmail VARCHAR NOT NULL,
        ManufacturerAddress VARCHAR NOT NULL,
        ManufacturerPhone VARCHAR NOT NULL,
        ManufacturerAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        ManufacturerUpdated TIMESTAMP NOT NULL DEFAULT NOW()
    );

    CREATE TABLE if not exists Orders (
        OrderID SERIAL PRIMARY KEY NOT NULL,
        OrderCost FLOAT NOT NULL,
        OrderShippingCost FLOAT NOT NULL DEFAULT 5.0,
        OrderTax FLOAT NOT NULL DEFAULT 24.0,
        OrderTotalCost FLOAT NOT NULL,
        OrderProcessed BOOLEAN NOT NULL DEFAULT FALSE,
        OrderCity VARCHAR NOT NULL,
        OrderShippingAddress VARCHAR NOT NULL,
        OrderShippingAddress2 VARCHAR,
        OrderZipcode VARCHAR NOT NULL,
        OrderCountry VARCHAR NOT NULL,
        OrderPhone VARCHAR NOT NULL,
        OrderPhone2 VARCHAR,
        OrderEmail VARCHAR NOT NULL,
        OrderDate TIMESTAMP NOT NULL,
        OrderHasShipped BOOLEAN NOT NULL DEFAULT FALSE, --TODO: if hasshipped, add tracking number
        OrderHasArrived BOOLEAN NOT NULL DEFAULT FALSE,
        OrderTrackingNumber VARCHAR,
        OrderAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        OrderUpdated TIMESTAMP NOT NULL DEFAULT NOW()
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
        ProductInStock BOOLEAN NOT NULL DEFAULT FALSE,
        ProductAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        ProductUpdated TIMESTAMP NOT NULL DEFAULT NOW()
    );
$$ LANGUAGE SQL;

SELECT create_tables();

CREATE OR REPLACE FUNCTION assign_weight_class() RETURNS void AS $$
    UPDATE Products
    SET ProductWeightClass = 5
    WHERE ProductWeight >= 20.0;

    UPDATE Products
    SET ProductWeightClass = 4
    WHERE ProductWeight < 20.0;

    UPDATE Products
    SET ProductWeightClass = 3
    WHERE ProductWeight < 10.5;

    UPDATE Products
    SET ProductWeightClass = 2
    WHERE ProductWeight < 5.5;

    UPDATE Products
    SET ProductWeightClass = 1
    WHERE ProductWeight < 1.1;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_shipped_orders() RETURNS SETOF Orders AS $$
    SELECT * FROM Orders o WHERE o.OrderHasShipped = TRUE AND o.OrderHasArrived = FALSE;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_unshipped_orders() RETURNS SETOF Orders AS $$
    SELECT * FROM Orders o WHERE o.OrderHasShipped = FALSE AND o.OrderHasArrived = FALSE;
$$ LANGUAGE SQL;

--TODO: see if this workie
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
