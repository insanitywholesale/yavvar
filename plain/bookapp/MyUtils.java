package bookapp;

public class MyUtils {

    public static Book[] bubbleSort(Book bookArray[], int sortBy) {
        int len = 3;
        if (sortBy == 2) {
            //sort year
        } else {
            Book tmpBook;
            for (int i = 0; i < len; i++) {
                for (int j = 1; j < (len - i); j++) {
                    int isbnCompare = bookArray[j - 1].getISBN().compareTo(bookArray[j].getISBN());
                    if (isbnCompare > 0) {
                        tmpBook = bookArray[j - 1];
                        bookArray[j - 1] = bookArray[j];
                        bookArray[j] = tmpBook;
                    }
                }
            }
        }
        return bookArray;
    }

    public static Book[] insertionSort(Book bookArray[], int sortBy) {
        int len = bookArray.length;
        if (sortBy == 2) {
            for (int i = 1; i < len; i++) {
                Book tmpBook = bookArray[i];
                int j = i - 1;
                while ((j >= 0) && (tmpBook.getYear() <= bookArray[j].getYear())) {
                    bookArray[j + 1] = bookArray[j];
                    j--;
                }
                bookArray[j + 1] = tmpBook;
            }
        } else {
            //sort isbn
        }
        return bookArray;
    }

    public static Book[] selectionSort(Book bookArray[], int sortBy) {
        if (bookArray == null) {
            return null;
        }
        int len = bookArray.length;
        if (len <= 1) {
            return bookArray;
        }
        if (sortBy == 2) {
            for (int i = 0; i < len - 1; i++) {
                int min = i;
                for (int j = i + 1; j < len; j++) {
                    if (bookArray[j].getYear() < bookArray[min].getYear()) {
                        min = j;
                    }
                }
                Book tmpBook = bookArray[min];
                bookArray[min] = bookArray[i];
                bookArray[i] = tmpBook;
            }
        } else {
            //sort isbn
        }
        return bookArray;
    }

    public static int partition(Book bookArray[], int low, int high, int sortBy) {
        Book pivot = bookArray[high];
        int i = (low - 1);
        if (sortBy == 2) {
            for (int j = low; j < high; j++) {
                if (bookArray[j].getYear() <= pivot.getYear()) {
                    i++;
                    Book tmpBook = bookArray[i];
                    bookArray[i] = bookArray[j];
                    bookArray[j] = tmpBook;
                }
            }
        } else {
            //sort isbn
        }
        Book temp = bookArray[i + 1];
        bookArray[i + 1] = bookArray[high];
        bookArray[high] = temp;
        return i + 1;
    }

    public static Book[] myQuickSort(Book bookArray[], int low, int high, int sortBy) {
        if (low < high) {
            int pi = partition(bookArray, low, high, sortBy);
            myQuickSort(bookArray, low, pi - 1, sortBy);
            myQuickSort(bookArray, pi + 1, high, sortBy);
        }
        return bookArray;
    }

    public static Book[] quickSort(Book bookArray[], int sortBy) {
        if (bookArray == null) {
            return null;
        }
        int len = bookArray.length;
        if (len <= 1) {
            return bookArray;
        }
        int low = 0;
        int high = len - 1;
        return myQuickSort(bookArray, low, high, sortBy);
    }

    public static Book[] mergeSort(Book bookArray[], int sortBy) {
        if (bookArray == null) {
            return null;
        }
        int len = bookArray.length;
        if (len <= 1) {
            return bookArray;
        }
        int left = 0;
        int right = len - 1;
        int mid = (left + right) / 2;

        Book[] leftBookArray = new Book[mid];
        for (int i = 0; i < mid; i++) {
            leftBookArray[i] = bookArray[i];
        }
        Book[] rightBookArray = new Book[len - mid];
        for (int i = mid; i < len; i++) {
            rightBookArray[i - mid] = bookArray[i];
        }
        leftBookArray = mergeSort(leftBookArray, sortBy);
        rightBookArray = mergeSort(rightBookArray, sortBy);

        int i = 0;
        int j = 0;
        int k = 0;
        if (sortBy == 2) {
            while (i < leftBookArray.length && j < rightBookArray.length) {
                if (leftBookArray[i].getYear() < rightBookArray[j].getYear()) {
                    bookArray[k] = leftBookArray[i];
                    i++;
                } else {
                    bookArray[k] = rightBookArray[j];
                    j++;
                }
                k++;
            }
        } else {
            //sort isbn
        }
        while (i < leftBookArray.length) {
            bookArray[k] = leftBookArray[i];
            i++;
            k++;
        }
        while (j < rightBookArray.length) {
            bookArray[k] = rightBookArray[j];
            j++;
            k++;
        }
        return bookArray;
    }

    public static void binSearch(Book bookArray[], int search) {
        int low = 0, high = bookArray.length - 1, mid = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (search < bookArray[mid].getYear()) {
                high = mid - 1;
            } else if (search > bookArray[mid].getYear()) {
                low = mid + 1;
            }
        }
        if (mid == -1) {
            System.out.println("den yparxei tetoio vivlio");
        }
        System.out.println(bookArray[mid]);
    }

    public static void binSearch(Book bookArray[], String search) {
        int low = 0, high = bookArray.length - 1, mid = -1;
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
        int len = bookArray.length - 1;
        int found = 0;
        for (int i = 0; i < len; i++) {
            if ((bookArray[i] != null) && (bookArray[i].getISBN().equals(search))) {
                System.out.println(bookArray[i]);
                found++;
            }
        }
        if (found == 0) {
            System.out.println("den yparxoyn tetoia vivlia");
        }
    }

    public static void seqSearch(Book bookArray[], int search) {
        int len = bookArray.length - 1;
        int found = 0;
        for (int i = 0; i < len; i++) {
            if ((bookArray[i] != null) && (bookArray[i].getYear() == search)) {
                System.out.println(bookArray[i]);
                found++;
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
