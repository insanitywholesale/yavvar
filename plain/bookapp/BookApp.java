package bookapp;

import java.time.LocalDate;

public class BookApp {

    public static void main(String[] args) {
        Book bookArray[] = new Book[10];
        boolean end = false;
        while (!end) {
            listOptions();
            switch (getUserInt(1, 5)) {
                case 1:
                    //check full (if last is not null, it's not full)
                    if (bookArray[9] != null) {
                        //find first non-null spot
                        for (int i = 0; i < 10; i++) {
                            if (bookArray[i] != null) {
                                bookArray[i] = bookMenu();
                            }
                        }
                    }
                    break;
                case 2:
                    //TODO: implement nested menu with while
                    int searchField = searchField();
                    int searchMethod;
                    if (searchField == 1) {
                        String isbn = enterISBN();
                        searchMethod = searchMethod();
                        if (searchMethod == 1) {
                            MyUtils.seqSearch(bookArray, isbn);
                        } else {
                            MyUtils.binSearch(bookArray, isbn);
                        }
                    } else if (searchField == 2) {
                        int year = enterYear();
                        searchMethod = searchMethod();
                        if (searchMethod == 1) {
                            MyUtils.seqSearch(bookArray, year);
                        } else {
                            MyUtils.binSearch(bookArray, year);
                        }
                    } else {
                        break;
                    }
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
