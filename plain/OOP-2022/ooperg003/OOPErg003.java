package ooperg003;

import java.util.*;

public class OOPErg003 {

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            printMainMenu();
            choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    int factorialInput = factorialPrompt();
                    int factorialResult = factorial(factorialInput);
                    System.out.println("ΠΑΡΑΓΟΝΤΙΚΟ ΑΠΟΤΕΛΕΣΜΑ: " + factorialResult + "\n");
                    break;
                case 2:
                    int fibonacciInput = fibonacciPrompt();
                    int fibonacciResult = fibonacci(fibonacciInput);
                    System.out.println("Fibonacci ΑΠΟΤΕΛΕΣΜΑ: " + fibonacciResult + "\n");
                    break;
                case 3:
                    String palindromeInput = palindromePrompt();
                    boolean palindromeResult = palindrome(palindromeInput);
                    System.out.println("ΠΑΛΙΝΔΡΟΜΟ ΑΠΟΤΕΛΕΣΜΑ: " + palindromeResult + "\n");
                    break;
                case 4:
                    int primeInput = primePrompt();
                    boolean primeResult = prime(primeInput, 2);
                    System.out.println("ΠΡΩΤΟΣ ΑΠΟΤΕΛΕΣΜΑ: " + primeResult + "\n");
                    break;
                case 5:
                    int hanoiInput = hanoiPrompt();
                    System.out.println("ΠΥΡΓΟΙ ΤΟΥ ΑΝΟΙ ΑΠΟΤΕΛΕΣΜΑ: ");
                    hanoi(hanoiInput);
                    System.out.println();
                    break;
                default:
                    break;
            }
        } while (!exit);
    }

    public static void printMainMenu() {
        System.out.print("  ΜΕΝΟΥ\n"
                + "1=Παραγοντικό,\n"
                + "2=Ακολουθία Fibonacci,\n"
                + "3=Παλίνδρομο,\n"
                + "4=Πρώτοι αριθμοί,\n"
                + "5=Πύργοι του Ανόϊ,\n"
                + "0=Εξοδος\n"
                + "  ΔΩΣΕ ΑΡΙΘΜΟ: ");
    }

    public static int giveInt() {
        int in = -1;
        Scanner scan = new Scanner(System.in);
        in = Integer.parseInt(scan.nextLine());
        return in;
    }

    public static int giveIntInRange(int rangeLow, int rangeHigh) {
        int in = -1;
        Scanner scan = new Scanner(System.in);
        do {
            if (in != -1) {
                System.out.println("Δωσε αριθμο απο " + rangeLow + " εως και " + rangeHigh); //TODO: see if it works as expected
            }
            in = Integer.parseInt(scan.nextLine());
        } while (in > rangeHigh || in < rangeLow);
        return in;
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int factorialPrompt() {
        System.out.println("Δωσε αριθμο για ευρεση παραγοντικου: ");
        return giveIntInRange(0, Integer.MAX_VALUE);
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    public static int fibonacciPrompt() {
        System.out.println("Δωσε αριθμο για ευρεση σειρας fibonacci: ");
        return giveIntInRange(0, Integer.MAX_VALUE);
    }

    public static boolean palindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return palindrome(s.substring(1, s.length() - 1));
        }
        return false;
    }

    public static String palindromePrompt() {
        System.out.println("Δωσε αριθμο η αλφαριθμητικο για ευρεση παλινδρομου: ");
        return new Scanner(System.in).nextLine();
    }

    public static boolean prime(int n, int div) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % div == 0) {
            return false;
        }
        if (div * div > n) {
            return true;
        }
        return prime(n, div + 1);
    }

    public static int primePrompt() {
        System.out.println("Δωσε αριθμο για ευρεση πρωτου: ");
        return giveIntInRange(0, Integer.MAX_VALUE);
    }

    public static void hanoiHelper(int n, int curPole, int tempPole, int endPole) {
        if (n == 0) {
            return;
        }
        hanoiHelper(n - 1, curPole, endPole, tempPole);
        System.out.println(curPole + " -> " + endPole);
        hanoiHelper(n - 1, tempPole, curPole, endPole);
    }

    public static void hanoi(int n) {
        hanoiHelper(n, 1, 2, 3);
    }

    public static int hanoiPrompt() {
        System.out.println("Δωσε αριθμο δισκων: ");
        return giveIntInRange(0, 64);
    }
}
