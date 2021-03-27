package bookapp;

public class MyUtils {

    public static void binSearch(Book bookArray[], int search) {
        int low = 0, high = 9, mid = -1;
        int x = search;
        while (low <= high) {
            mid = (low + high) / 2;
            if (x < bookArray[mid].getYear()) {
                high = mid - 1;
            } else if (x > bookArray[mid].getYear()) {
                low = mid + 1;
            }
        }
        if (mid == -1) {
            System.out.println("den yparxei tetoio vivlio");
        }
        System.out.println(bookArray[mid]);
    }

    public static void binSearch(Book bookArray[], String search) {
        int low = 0, high = 9, mid = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (bookArray[mid].getISBN().compareTo(search) < 0) {
                low = mid + 1;
            } else if (bookArray[mid].getISBN().compareTo(search) > 0) {
                high = mid - 1;
            }
        }
        if (mid == -1) {
            System.out.println("den yparxei tetoio vivlio");
        }
        System.out.println(bookArray[mid]);
    }

    public static void seqSearch(Book bookArray[], String search) {
        int found = 0;
        for (int i = 0; i < 9; i++) {
            if (bookArray[i].getISBN().equals(search)) {
                System.out.println(bookArray[i]);
            }
        }
        if (found == 0) {
            System.out.println("den yparxoyn tetoia vivlia");
        }
    }

    public static void seqSearch(Book bookArray[], int search) {
        int found = 0;
        for (int i = 0; i < 9; i++) {
            if (bookArray[i].getYear() == search) {
                System.out.println(bookArray[i]);
            }
        }
        if (found == 0) {
            System.out.println("den yparxoyn tetoia vivlia");
        }
    }

    public static void valueSearch(Book bookArray[]) {
        System.out.println("eisagete thn katwtath timh:");
        double lowPrice = scannerUserInput.getDouble();
        System.out.println("eisagete thn anwtath timh:");
        double highPrice = scannerUserInput.getDouble();
        int found = 0;
        for (int i = 0; i < 10; i++) {
            if (bookArray[i] != null && (bookArray[i].getPrice() > lowPrice && bookArray[i].getPrice() < highPrice)) {
                System.out.println(bookArray[i]);
                found++;
            }
        }
        if (found == 0) {
            System.out.println("den yparxoyn tetoia vivlia");
        }
    }

}
