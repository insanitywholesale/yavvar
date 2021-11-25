--TODO: swith IDs from SERIAL to IDENTITY
--TODO: make shipping 0 for order over 50
--TODO: increase shipping for heavier stuff
--TODO: figure out optargs to functions
--TODO: figure out how to do discounts
--TODO: figure out ordering
--TODO: add users
--TODO: if order has shipped, add tracking number

--STORED PROCEDURES

CREATE OR REPLACE FUNCTION create_tables() RETURNS void AS $$

    CREATE TABLE if not exists Users (
        UserID SERIAL PRIMARY KEY NOT NULL,
        UserNickName VARCHAR NOT NULL,
        UserEmail VARCHAR NOT NULL,
        UserPassword VARCHAR NOT NULL,
        UserFirstName VARCHAR NOT NULL,
        UserLastName VARCHAR NOT NULL,
        UserCity VARCHAR NOT NULL,
        UserZipcode VARCHAR NOT NULL,
        UserCountry VARCHAR NOT NULL,
        UserPhone VARCHAR NOT NULL,
        UserAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        UserUpdated TIMESTAMP NOT NULL DEFAULT NOW(),
        UserIsAdmin BOOLEAN NOT NULL DEFAULT FALSE,
        UserBecameAdmin TIMESTAMP
    );

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

    CREATE TABLE if not exists Orders (
        OrderID SERIAL PRIMARY KEY NOT NULL,
        OrderUserID SERIAL REFERENCES Users(UserID) NOT NULL,
        OrderCost FLOAT NOT NULL,
        OrderShippingCost FLOAT NOT NULL DEFAULT 5.0,
        OrderTax FLOAT NOT NULL DEFAULT 24.0,
        OrderTotalCost FLOAT NOT NULL,
        OrderProcessed BOOLEAN NOT NULL DEFAULT FALSE,
        OrderCity VARCHAR NOT NULL,
        OrderAddress VARCHAR NOT NULL,
        OrderZipcode VARCHAR NOT NULL,
        OrderCountry VARCHAR NOT NULL,
        OrderPhone VARCHAR NOT NULL,
        OrderEmail VARCHAR NOT NULL,
        OrderHasShipped BOOLEAN NOT NULL DEFAULT FALSE,
        OrderHasArrived BOOLEAN NOT NULL DEFAULT FALSE,
        OrderTrackingNumber VARCHAR,
        OrderAdded TIMESTAMP NOT NULL DEFAULT NOW(),
        OrderUpdated TIMESTAMP NOT NULL DEFAULT NOW(),
        OrderFinalized TIMESTAMP NOT NULL DEFAULT NOW(),
        OrderFinal BOOLEAN NOT NULL DEFAULT FALSE
    );

    --TODO: define primary key
    CREATE TABLE if not exists OrderedProducts (
        ProductID SERIAL NOT NULL,
        OrderID SERIAL NOT NULL,
        ProductID SERIAL NOT NULL,
        ProductQuantity INTEGER NOT NULL DEFAULT 1,
        ProductTotalCost FLOAT NOT NULL,
        Constraint Ordered_OrderID FOREIGN KEY(OrderID) REFERENCES Orders on delete cascade on update cascade,
        Constraint Ordered_ProductID FOREIGN KEY(ProductID) REFERENCES Products on delete cascade on update cascade
    );

$$ LANGUAGE SQL;

SELECT create_tables();

--                                     (userid   city     addr     zip      country  phone    email  )
CREATE OR REPLACE FUNCTION create_order(integer, varchar, varchar, varchar, varchar, varchar, varchar) RETURNS INTEGER AS $$
    INSERT INTO Orders (
        OrderUserID,
        OrderCity,
        OrderAddress,
        OrderZipcode,
        OrderCountry,
        OrderPhone,
        OrderEmail
    ) VALUES ($1, $2, $3, $4, $5, $6, $7);
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
    SET OrderFinal = TRUE
    WHERE OrderID == $1;
$$ LANGUAGE SQL;

--                                  (orderid)
CREATE OR REPLACE FUNCTION get_order(integer) RETURNS SETOF Orders AS $$
    SELECT * FROM Orders o WHERE o.OrderID == $1;
$$ LANGUAGE SQL;

--                                 (nick     email    passwd   fname    lname    city     zip      country  phone  )
CREATE OR REPLACE FUNCTION add_user(varchar, varchar, varchar, varchar, varchar, varchar, varchar, varchar, varchar) RETURNS void AS $$
    INSERT INTO Users (
        UserNickName,
        UserEmail,
        UserPassword,
        UserFirstName,
        UserLastName,
        UserCity,
        UserZipcode,
        UserCountry,
        UserPhone
    ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9);
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
