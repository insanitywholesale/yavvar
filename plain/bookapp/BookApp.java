package bookapp;

import java.time.LocalDate;

public class BookApp {

    //global var bad
    public static Book bookArray[] = new Book[10];
    public static int sorting = 0; //0 = unsorted, 1 = sorted by isbn, 2 = sorted by year

    //possible improvement 1: check if book with the same isbn exists
    //possible improvement 2: ability to delete book
    public static void main(String[] args) {
        System.out.println("ta arxeia java yparxoyn kai sto github: https://github.com/insanitywholesale/yavvar/tree/master/plain/bookapp");
        System.out.println("####### PROSOXH #######");
        System.out.println("den douleuei h klhsh ths taksinomhshs, thelei doyleia akoma");
        System.out.println("oi algorithmoi yparxoun sthn klash MyUtils alla den xeirizontai thn yparksh null pediwn oute taksinomhsh me ola ta pedia");
        System.out.println("####### PROSOXH #######");
        System.out.println("");
        System.out.println("");

        bookArray[0] = new Book("a", "me", "9605122839", "mypub1", 123, 1999, 13.12);
        bookArray[1] = new Book("b", "me", "9780110002224", "mypub2", 789, 1789, 01.65);
        bookArray[2] = new Book("pro linux system admin", "dennis/james/peter", "9781484220078", "A1press", 1008, 2017, 74.82);
        boolean end = false;
        while (!end) {
            listOptions();
            switch (getUserInt(1, 6)) {
                case 1:
                    //find first non-null spot
                    int insertIndex = -1;
                    for (int i = 0; i < 10; i++) {
                        if (bookArray[i] != null) {
                            insertIndex = i;
                        }
                    }
                    if (insertIndex == -1) {
                        System.out.println("den mporei na ginei eisagwgh vivliou epeidh o xwros apothikeyshs gemise");
                        break;
                    }
                    Book newBook = bookMenu();
                    if (newBook == null) {
                        break;
                    } else {
                        bookArray[insertIndex] = newBook;
                        break;
                    }
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
                    boolean endSortField = false;
                    boolean endSortMethod = false;
                    int sortField = 0;
                    int sortMethod;
                    while (!endSortField) {
                        sortField = sortField();
                        sorting = sortField;
                        endSortField = true;
                    }
                    while (!endSortMethod) {
                        sortMethod = sortMethod();
                        switch (sortMethod) {
                            case 1:
                                bookArray = MyUtils.bubbleSort(bookArray, sorting);
                                endSortMethod = true;
                                break;
                            case 2:
                                bookArray = MyUtils.insertionSort(bookArray, sorting);
                                endSortMethod = true;
                                break;
                            case 3:
                                bookArray = MyUtils.selectionSort(bookArray, sorting);
                                endSortMethod = true;
                                break;
                            case 4:
                                bookArray = MyUtils.quickSort(bookArray, sorting);
                                endSortMethod = true;
                                break;
                            case 5:
                                bookArray = MyUtils.mergeSort(bookArray, sorting);
                                endSortMethod = true;
                                break;
                            default:
                                endSortMethod = true;
                                break;
                        }
                    }
                    break;
                case 5:
                    for (int i = 0; i < 9; i++) {
                        if (bookArray[i] != null) {
                            System.out.println(bookArray[i]);
                        }
                    }
                    break;
                case 6:
                    end = true;
                    break;
                default:
                    System.out.println("idk");
                    break;
            }
        }//end main while loop
    }//end main

    public static int sortMethod() {
        System.out.println("Epilogh methodou taksinomhshs:");
        System.out.println("  1. bubblesort");
        System.out.println("  2. insertionsort");
        System.out.println("  3. selectionsort");
        System.out.println("  4. quicksort");
        System.out.println("  5. mergesort");
        System.out.println("  6. Epistrofh sthn epilogh pediou taksinomhshs");
        return getUserInt(1, 6);
    }

    public static int sortField() {
        System.out.println("Epilogh pediou taksinomhshs:");
        System.out.println("  1. Onoma syggrafea");
        System.out.println("  2. ISBN");
        System.out.println("  3. Etos kykloforias");
        System.out.println("  4. Timh vivliou");
        System.out.println("  5. Epistrofh sthn arxikh lista epilogwn");
        return getUserInt(1, 5);
    }

    public static String enterISBN() {
        String isbn = "";
        while (!correctISBN(isbn)) {
            System.out.println("Dwste ISBN:");
            isbn = scannerUserInput.getString();
        }
        return isbn;
    }

    public static String enterISBN(boolean canCancel) {
        String isbn = "";
        while (!correctISBN(isbn)) {
            if (canCancel) {
                System.out.println("ISBN (0 gia akyrwsh kataxwrishs):");
            }
            isbn = scannerUserInput.getString();
            if (isbn.equals("0")) {
                break;
            }
        }
        return isbn;
    }

    public static int enterYear() {
        int year = LocalDate.now().getYear() + 1;
        while (year > LocalDate.now().getYear()) {
            System.out.println("Dwste etos:");
            year = scannerUserInput.getInteger();
        }
        return year;
    }

    public static int enterYear(boolean prompt) {
        int year = LocalDate.now().getYear() + 1;
        while (year > LocalDate.now().getYear()) {
            if (prompt) {
                System.out.println("Dwste etos:");
            }
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
        // ISBN in this case requires special handling for cancelling input
        String isbn = enterISBN(true);
        if (isbn.equals("0")) {
            return null;
        }
        System.out.println("ekdotikos oikos:");
        String publisher = scannerUserInput.getString();
        System.out.println("arithmos selidwn:");
        int pages = scannerUserInput.getInteger();
        System.out.println("etos ekdoshs:");
        int year = enterYear(false);
        System.out.println("timh:");
        double price = scannerUserInput.getDouble();
        return new Book(title, author, isbn, publisher, pages, year, price);
    }

    public static void listOptions() {
        System.out.println("Epiloges:");
        System.out.println("  1. Eisagwgh stoixeiwn vivliou");
        System.out.println("  2. Anazhthsh vivliou");
        System.out.println("  3. Emfanish stoixeiwn vivliwn metaksy kapoiwn timwn");
        System.out.println("  4. Taksinomhsh vivliwn");
        System.out.println("  5. Emfanish stoixeiwn olwn twn vivliwn");
        System.out.println("  6. Telos");
    }

    public static boolean correctISBN(String ISBN) {
        ISBN = ISBN.replaceAll("[^0-9]", "");
        if (ISBN.length() != 13 && ISBN.length() != 10) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            if ((bookArray[i] != null) && (bookArray[i].getISBN().equals(ISBN))) {
                System.out.println("Yparxei hdh vivlio me ayto to ISBN");
                return false;
            }
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
