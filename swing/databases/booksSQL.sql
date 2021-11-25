--TODO: trigger for Authors BooksWritten
--TODO: check if ISBN is valid on insert and update

CREATE FUNCTION get_books() RETURNS void AS $$
    SELECT ISBN, Title, Edition, Pages, Category FROM Books;
$$ LANGUAGE SQL;

CREATE FUNCTION get_book(varchar) RETURNS void AS $$
    SELECT ISBN, Title, Edition, Pages, Category FROM Books WHERE ISBN = $1;
$$ LANGUAGE SQL;

CREATE FUNCTION add_book(varchar, varchar, integer, integer, varchar) RETURNS void AS $$
    INSERT INTO Books(ISBN, Title, Edition, Pages, Category) VALUES ($1, $2, $3, $4, $5);
$$ LANGUAGE SQL;

CREATE FUNCTION delete_book(varchar) RETURNS void AS $$
    DELETE FROM Books WHERE ISBN = $1;
$$ LANGUAGE SQL;

CREATE FUNCTION destroy_tables() RETURNS void AS $$
    DROP TABLE Rates;
    DROP TABLE Publishes;
    DROP TABLE Writes;
    DROP TABLE Ratings;
    DROP TABLE Publishers;
    DROP TABLE Authors;
    DROP TABLE Books;
$$ LANGUAGE SQL;

CREATE FUNCTION create_tables() RETURNS void AS $$
    CREATE TABLE if not exists Books (
        ISBN VARCHAR(13) PRIMARY KEY NOT NULL,
        Title VARCHAR(100) NOT NULL,
        Edition INTEGER NOT NULL,
        Pages INTEGER NOT NULL,
        Category VARCHAR(20) NOT NULL,
    );

    CREATE TABLE if not exists Authors (
        AuthorID SERIAL PRIMARY KEY NOT NULL,
        FirstName VARCHAR(30) NOT NULL,
        MiddleName VARCHAR(30),
        LastName VARCHAR(30) NOT NULL,
        YearBorn INTEGER NOT NULL,
        YearDied INTEGER,
        BooksWritten INTEGER, --TODO: increase with every book written
        Constraint Author_Age check (YearDied > YearBorn)
    );

    CREATE TABLE if not exists Publishers (
        PublisherID SERIAL PRIMARY KEY NOT NULL,
        PublisherName VARCHAR(30) NOT NULL,
        YearStarted INTEGER NOT NULL,
        YearEnded INTEGER,
        BooksPublished INTEGER, --TODO: increase with every book published
        Constraint Publisher_Age check (YearStarted > YearEnded)
    );

    CREATE TABLE if not exists Ratings (
        RatingID SERIAL PRIMARY KEY NOT NULL,
        Rating INTEGER NOT NULL,
        Constraint Valid_Rating check (Rating >= 1 AND Rating <= 5)
    );

    CREATE TABLE if not exists Writes (
        ISBN VARCHAR(13) NOT NULL,
        AuthorID SERIAL NOT NULL,
        Constraint Write_ISBN FOREIGN KEY(ISBN) REFERENCES Books on delete cascade on update cascade,
        Constraint Write_AuthorID FOREIGN KEY(AuthorID) REFERENCES Authors on delete cascade on update cascade
    );

    CREATE TABLE if not exists Publishes (
        ISBN VARCHAR(13) NOT NULL,
        AuthorID SERIAL NOT NULL,
        Publish_Date DATE NOT NULL,
        Publish_Timestamp TIMESTAMP NOT NULL,
        Constraint Publish_ISBN FOREIGN KEY(ISBN) REFERENCES Books on delete cascade on update cascade,
        Constraint Publish_AuthorID FOREIGN KEY(AuthorID) REFERENCES Authors on delete cascade on update cascade
    );

    CREATE TABLE if not exists Rates (
        ISBN VARCHAR(13) NOT NULL,
        RatingID SERIAL NOT NULL,
        Rate_Date DATE NOT NULL,
        Rate_Timestamp TIMESTAMP NOT NULL,
        Constraint Rate_ISBN FOREIGN KEY(ISBN) REFERENCES Books on delete cascade on update cascade,
        Constraint Rate_RatingID FOREIGN KEY(RatingID) REFERENCES Ratings on delete cascade on update cascade
    );
$$ LANGUAGE SQL;