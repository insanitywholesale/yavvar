package bookapp;

import java.time.LocalDate;

public class BookApp {

    public static void main(String[] args) {
        Book bookArray[] = new Book[10];
        bookArray[0] = new Book("a", "me", "9605122839", "mypub1", 123, 1999, 13.12);
        bookArray[1] = new Book("b", "me", "9780110002224", "mypub2", 789, 1789, 01.65);
        boolean end = false;
        while (!end) {
            listOptions();
            switch (getUserInt(1, 5)) {
                case 1:
                    //find first non-null spot
                    boolean inserted = false;
                    for (int i = 0; i < 10; i++) {
                        if (bookArray[i] != null) {
                            bookArray[i] = bookMenu();
                            inserted = true;
                        }
                    }
                    if (!inserted) {
                        System.out.println("den mporei na ginei eisagwgh vivliou epeidh o xwros apothikeyshs gemise");
                    }
                    break;
                case 2:
                    boolean endField = false;
                    boolean endMethod = false;
                    int searchField;
                    String isbn = "";
                    int year = LocalDate.now().getYear() + 2;
                    int searchMethod;
                    while (!endField) {
                        searchField = searchField();
                        switch (searchField) {
                            case 1:
                                isbn = enterISBN();
                                break;
                            case 2:
                                year = enterYear();
                                break;
                            default:
                                endField = true;
                                break;
                        }
                        while (!endMethod && (searchField == 1 || searchField == 2)) {
                            searchMethod = searchMethod();
                            switch (searchMethod) {
                                case 1:
                                    if (searchField == 1) {
                                        MyUtils.seqSearch(bookArray, isbn);
                                    } else {
                                        MyUtils.seqSearch(bookArray, year);
                                    }
                                    endMethod = true;
                                    endField = true;
                                    break;
                                case 2:
                                    if (searchField == 1) {
                                        MyUtils.binSearch(bookArray, isbn);
                                    } else {
                                        MyUtils.binSearch(bookArray, year);
                                    }
                                    endMethod = true;
                                    endField = true;
                                    break;
                                default:
                                    endMethod = true;
                                    break;
                            }
                        }
                        endMethod = false;
                    }
                    endField = false;
                    break;
                case 3:
                    MyUtils.valueSearch(bookArray);
                    break;
                case 4:
                    for (int i = 0; i < 9; i++) {
                        if (bookArray[i] != null) {
                            System.out.println(bookArray[i]);
                        }
                    }
                    break;
                case 5:
                    end = true;
                    break;
                default:
                    System.out.println("idk");
                    break;
            }
        }//end main while loop
    }//end main

    public static String enterISBN() {
        String isbn = "";
        while (!correctISBN(isbn)) {
            System.out.println("Dwste ISBN:");
            isbn = scannerUserInput.getString();
        }
        return isbn;
    }

    public static int enterYear() {
        int year = LocalDate.now().getYear() + 5;
        while (year > LocalDate.now().getYear()) {
            System.out.println("Dwste etos:");
            year = scannerUserInput.getInteger();
        }
        return year;
    }

    public static int searchMethod() {
        System.out.println("Anazhthsh me methodo:");
        System.out.println("  1. Seriakh");
        System.out.println("  2. Dyadikh");
        System.out.println("  3. Epistrofh sthn epilogh pediou anazhthtshs");
        return getUserInt(1, 3);
    }

    public static int searchField() {
        System.out.println("Anazhthsh me pedio:");
        System.out.println("  1. ISBN");
        System.out.println("  2. Etos kykloforias");
        System.out.println("  3. Epistrofh sthn arxikh lista epilogwn");
        return getUserInt(1, 3);
    }

    //get an int within specified range range
    public static int getUserInt(int low, int high) {
        int userInt = 0;
        while (userInt < low || userInt > high) {
            System.out.println("Dwste epilogh (" + low + "-" + high + "):");
            userInt = scannerUserInput.getInteger();
        }
        return userInt;
    }

    public static Book bookMenu() {
        System.out.println("titlos:");
        String title = scannerUserInput.getString();
        System.out.println("syggrafeas:");
        String author = scannerUserInput.getString();
        System.out.println("ISBN:");
        String isbn = scannerUserInput.getString();
        while (!correctISBN(isbn)) {
            isbn = scannerUserInput.getString();
        }
        System.out.println("ekdotikos oikos:");
        String publisher = scannerUserInput.getString();
        System.out.println("arithmos selidwn:");
        int pages = scannerUserInput.getInteger();
        System.out.println("etos ekdoshs:");
        int year = scannerUserInput.getInteger();
        System.out.println("timh:");
        double price = scannerUserInput.getDouble();
        return new Book(title, author, isbn, publisher, pages, year, price);
    }

    public static void listOptions() {
        System.out.println("Epiloges:");
        System.out.println("  1. Eisagwgh stoixeiwn vivliou");
        System.out.println("  2. Anazhthsh vivliou");
        System.out.println("  3. Emfanish stoixeiwn vivliwn metaksy kapoiwn timwn");
        System.out.println("  4. Emfanish stoixeiwn olwn twn vivliwn");
        System.out.println("  5. Telos");
    }

    public static boolean correctISBN(String ISBN) {
        if (ISBN.length() != 13 || ISBN.length() != 10) {
            return false;
        }

        if (ISBN.length() == 10) {
            int sum = 0;
            for (int i = 0; i < ISBN.length(); i++) {
                sum += Character.getNumericValue(ISBN.charAt(i)) * (10 - i);
            }
            return sum % 11 == 0;
        } else {
            int sum = 0;
            for (int i = 0; i < ISBN.length(); i++) {
                sum += Character.getNumericValue(ISBN.charAt(i)) * (i % 2 == 0 ? 1 : 3);
            }
            return sum % 10 == 0;
        }
    }

}
