package ooperg001;

public class OOPErg001 {

    public static void main(String[] args) {
        Foititis f1 = new Foititis("20001", 2020, "Πολυκαρπου Κλεων");
        System.out.println(f1.getAM() + " " + f1.getOnomatEpwnymo());

        Foititis f2 = new Foititis("20002", 1999, "Γεωργιου Αριστεα");
        System.out.println(f2.getAM() + " " + f2.getOnomatEpwnymo());

        Foititis f3 = new Foititis("", 2017, "");
        f3.setOnomatEpwnymo("Κουρατζινος Αγγελος");
        f3.setAM("174949");

        System.out.println(f3);

    }
}
