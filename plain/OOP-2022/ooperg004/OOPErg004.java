package ooperg004;

import java.text.*;
import java.util.*;

public class OOPErg004 {

    public static void main(String[] args) {
        Foititis students[] = new Foititis[10];
        Foititis f0 = new Foititis("Beep Boop", 13);
        students[0] = f0;
        Foititis f1 = new Foititis("Bleep Bloop", 14);
        students[1] = f1;
        Foititis f2 = new Foititis("Vroom Vzoom", 15);
        students[2] = f2;
        Foititis f3 = new Foititis("Foo Bar", 16);
        students[3] = f3;
        Foititis f4 = new Foititis("Bar Baz", 12);
        students[4] = f4;
        Foititis f5 = new Foititis("Re Fresh", 19);
        students[5] = f5;
        Foititis f6 = new Foititis("Ping Pong", 18);
        students[6] = f6;
        Foititis f7 = new Foititis("Healthz Status200", 12);
        students[7] = f7;
        Foititis f8 = new Foititis("Enas Foititis", 17);
        students[8] = f8;
        Foititis f9 = new Foititis("Mia Foitititria", 17);
        students[9] = f9;

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }

        System.out.println("=== Εισαγωγή ημερ/νιών γέννησης φοιτητών ===");

        for (int i = 0; i < students.length; i++) {
            /*if (i == 5 || i == 6 || i == 7) {
                Date dateOfBirth = inHmeromGennisis();
                students[i].setHmeromGennisis(dateOfBirth);
            }*/
            System.out.println(students[i]);
        }

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
                    anazitisiMeEpwnymo(students);
                    break;
                case 2:
                    anazitisiMeHmeromGennisis(students);
                    break;
                case 3:
                    anazitisiMeAM(students);
                    break;
                default:
                    System.out.println("επελεξε αριθμο απο 0 εως και 2");
                    break;
            }
        } while (!exit);

    }

    public static void printMainMenu() {
        System.out.print("1) Αναζήτηση με Επώνυμο\n"
                + "2) Αναζήτηση με Ημερ/νία γέννησης\n"
                + "3) Αναζήτηση με ΑΜ (δυαδική)\n"
                + "0) Εξοδος");
    }

    public static Date inHmeromGennisis() {
        Scanner scan = new Scanner(System.in);
        System.out.println("εισαγετε ημερ/νια σε μορφη ΗΗ/ΜΜ/ΕΕΕE: ");
        String dob = scan.nextLine();
        return convertStrToDate(dob);
    }

    public static Date convertStrToDate(String hmeromStr) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date hmerom = null;
        try {
            hmerom = df.parse(hmeromStr);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return hmerom;
    }

    public static int anazitisiMeEpwnymo(Foititis[] students) {
        Scanner scan = new Scanner(System.in);
        System.out.println("εισαγετε επωνυμο για αναζητηση: ");
        String lastName = scan.nextLine();
        Foititis[] results = lastnameSearch(lastName, students);
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
        if (results.length <= 0) {
            return -1;
        } else {
            return results.length;
        }
    }

    public static Foititis[] lastnameSearch(String lastName, Foititis[] students) {
        ArrayList<Foititis> results = new ArrayList<Foititis>();
        for (int i = 0; i < students.length; i++) {
            if (students[i].getEpwnymo().equals(lastName)) {
                results.add(students[i]);
            }
        }
        return (Foititis[]) results.toArray();
    }

    public static int anazitisiMeHmeromGennisis(Foititis[] students) {
        Scanner scan = new Scanner(System.in);
        System.out.println("εισαγετε ημερ/νία γέννησης για αναζητηση: ");
        String dateStr = scan.nextLine();
        Date date = convertStrToDate(dateStr);
        Foititis[] results = dateSearch(date, students);
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
        if (results.length <= 0) {
            return -1;
        } else {
            return results.length;
        }
    }

    public static Foititis[] dateSearch(Date date, Foititis[] students) {
        ArrayList<Foititis> results = new ArrayList<Foititis>();
        for (int i = 0; i < students.length; i++) {
            if (students[i].getHmeromGennisis() == date) {
                results.add(students[i]);
            }
        }
        return (Foititis[]) results.toArray();
    }

    public static void anazitisiMeAM(Foititis[] students) {
        Scanner scan = new Scanner(System.in);
        System.out.println("εισαγετε αριθμο μητρωου για αναζητηση: ");
        String AM = scan.nextLine();
        Arrays.sort(students);
        int idx = binSearch(students, AM, 0, students.length);
        System.out.println(students[idx]);
    }

    public static int binSearch(Foititis[] arr, String key, int l, int r) {
        int mid;
        int pos = -1;
        long k = Long.parseLong(key);

        int arrmid;

        while (pos == -1 && l <= r) {
            mid = (l + r) / 2;
            arrmid = Integer.parseInt(arr[mid].getAM());
            if (k < arrmid) {
                r = mid - 1;
            } else if (k > arrmid) {
                l = mid + 1;
            } else {
                pos = mid;
            }
        }
        return pos;
    }
}
