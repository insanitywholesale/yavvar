/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg10_ask1;

/**
 *
 * @author angle
 */
public class Erg10_Ask1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int kE, aM, oY, eP;
        double bM;
        
        System.out.println("employee number 1");
        System.out.println("enter business serial number: ");
        kE = scannerUserInput.getInteger();
        System.out.println("enter person serial number: ");
        aM = scannerUserInput.getInteger();
        System.out.println("enter base income amount: ");
        bM = scannerUserInput.getDouble();
        System.out.println("enter overtime hours: ");
        oY = scannerUserInput.getInteger();
        System.out.println("enter degree type: ");
        eP = scannerUserInput.getInteger();
        Ypallhlos y1 = new Ypallhlos(kE, aM, oY, eP, bM);
        System.out.println(y1);
        y1.showTelikosMisthos();
        
        System.out.println("employee number 2");
        System.out.println("enter business serial number: ");
        kE = scannerUserInput.getInteger();
        System.out.println("enter person serial number: ");
        aM = scannerUserInput.getInteger();
        System.out.println("enter base income amount: ");
        bM = scannerUserInput.getDouble();
        System.out.println("enter overtime hours: ");
        oY = scannerUserInput.getInteger();
        System.out.println("enter degree type: ");
        eP = scannerUserInput.getInteger();
        Ypallhlos y2 = new Ypallhlos(kE, aM, oY, eP, bM);
        System.out.println(y2);
        System.out.println("final income amount: " + y2.returnTelikosMisthos() + "\n");
    }
    
}
