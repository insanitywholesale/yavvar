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
            if (i == 5 || i == 6 || i == 7) {
                Date dateOfBirth = inHmeromGennisis();
                students[i].setHmeromGennisis(dateOfBirth);
            }
            System.out.println(students[i]);
        }
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
}
