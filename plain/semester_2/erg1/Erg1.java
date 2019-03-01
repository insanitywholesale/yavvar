package erg1;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.*;

public class Erg1 {

    private static Foititis [] pinFoitites;
    
    public static void main(String[] args) {
        int i;
        
        pinFoitites = new Foititis[10];
        pinFoitites[0] = new Foititis(17, "Πολυκάρπου Κλέων", strToDate("10/10/1999"));
        pinFoitites[1] = new Foititis(17, "Γεωργίου Αριστέα", strToDate("2/5/1998"));
        pinFoitites[2] = new Foititis(17, "Τσουκαλάς Ανδρόνικος", strToDate(null));
        pinFoitites[3] = new Foititis(17, "Χυτήρης Οδυσσέας", strToDate("26/2/1998"));
        pinFoitites[4] = new Foititis(17, "Πετρίδου Δανάη", strToDate(null));
        
        for(i=0;i<pinFoitites.length;i++){
            System.out.println(pinFoitites[i]);
        }
       
        for(i=0;i<pinFoitites.length;i++){
            if((pinFoitites[i].getHmerom() == null)  && (pinFoitites[i] != null)) {
                pinFoitites[i].setHmerom(inputHmerom());
            }
        }
    }

    private static Date strToDate(String hmeromStr){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date hmerom = null;
        try {
            hmerom = df.parse(hmeromStr);
        }
        catch (Exception ex ){
            System.out.println(ex);
        }
        return hmerom;
    }
    
    private static Date inputHmerom() {
        System.out.println("enter date of birth for ");
        return strToDate(scannerUserInput.getString());
    }


}

