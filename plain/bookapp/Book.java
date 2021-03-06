package bookapp;

public class Book {

    private String title;
    private String author;
    private String ISBN;
    private String publisher;
    private int pages;
    private int year;
    private double price;

    public Book() {
        //empty constructor
    }

    public Book(String title, String author, String ISBN, String publisher, int pages, int year, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.pages = pages;
        this.year = year;
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{"
                + "\n\ttitle=" + title
                + ",\n\ttuthor=" + author
                + ",\n\tISBN=" + ISBN
                + ",\n\tpublisher=" + publisher
                + ",\n\tpages=" + pages
                + ",\n\tyear=" + year
                + ",\n\tprice=" + price
                + "\n}";
    }

}
