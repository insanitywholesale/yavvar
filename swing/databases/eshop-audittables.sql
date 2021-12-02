--create audit/log tables and triggers

CREATE OR REPLACE FUNCTION create_audit_tables() RETURNS void AS $$
    CREATE TABLE if not exists UserAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        UserID INTEGER NOT NULL,
        UserNickName VARCHAR NOT NULL,
        UserEmail VARCHAR NOT NULL,
        UserPassword VARCHAR NOT NULL,
        UserFirstName VARCHAR NOT NULL,
        UserLastName VARCHAR NOT NULL,
        UserAddressID INTEGER,
        UserIsAdmin BOOLEAN NOT NULL
    );
    CREATE TABLE if not exists CategoryAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        CategoryID INTEGER NOT NULL,
        CategoryName VARCHAR NOT NULL,
        CategoryDescription VARCHAR NOT NULL
    );
    CREATE TABLE if not exists ManufacturerAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        ManufacturerID INTEGER NOT NULL,
        ManufacturerAddressID INTEGER
    );
    CREATE TABLE if not exists ProductAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        ProductID INTEGER NOT NULL,
        ProductTitle VARCHAR NOT NULL,
        ProductPrice FLOAT NOT NULL,
        ProductCategoryID INTEGER,
        ProductManufacturerID INTEGER,
        ProductDescription VARCHAR NOT NULL,
        ProductVersion FLOAT NOT NULL,
        ProductWeight FLOAT,
        ProductWeightClass SMALLINT,
        ProductOnSale BOOLEAN NOT NULL
    );
    CREATE TABLE if not exists OrderAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        OrderID INTEGER NOT NULL,
        OrderUserID INTEGER NOT NULL,
        OrderCost FLOAT NOT NULL,
        OrderShippingCost FLOAT NOT NULL,
        OrderTax FLOAT NOT NULL,
        OrderTotalCost FLOAT NOT NULL,
        OrderBillingAddressID INTEGER,
        OrderShippingAddressID INTEGER,
        OrderIsFinal BOOLEAN NOT NULL,
        OrderHasShipped BOOLEAN NOT NULL,
        OrderHasArrived BOOLEAN NOT NULL,
        OrderTrackingNumber VARCHAR
    );
    CREATE TABLE if not exists AddressAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        AddressID INTEGER NOT NULL,
        AddressCountry VARCHAR NOT NULL,
        AddressCity VARCHAR NOT NULL,
        AddressZipcode VARCHAR NOT NULL,
        Address1 VARCHAR NOT NULL,
        Address2 VARCHAR,
        AddressPhone VARCHAR NOT NULL,
        AddressPhone2 VARCHAR
    );

    CREATE TABLE if not exists OrderedProductsAudit (
        OperationTimestamp TIMESTAMP PRIMARY KEY NOT NULL,
        OperationType VARCHAR(1) NOT NULL,
        OperatorID VARCHAR NOT NULL,
        OrderedProductID INTEGER NOT NULL,
        OrderID INTEGER NOT NULL,
        ProductID INTEGER NOT NULL,
        ProductQuantity INTEGER NOT NULL,
        ProductTotalCost FLOAT NOT NULL
    );
$$ LANGUAGE SQL;