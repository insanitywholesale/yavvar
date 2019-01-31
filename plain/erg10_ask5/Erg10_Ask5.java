/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg10_ask5;

/**
 *
 * @author angle
 */
public class Erg10_Ask5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int aM, oY, eP;
        double bM;
        
        //with constructor
        System.out.println("employee number 1");
        System.out.println("enter base income amount: ");
        bM = scannerUserInput.getDouble();
        System.out.println("enter overtime hours: ");
        oY = scannerUserInput.getInteger();
        System.out.println("enter degree type: ");
        eP = scannerUserInput.getInteger();
        Ypallhlos y1 = new Ypallhlos(oY, eP, bM);
        System.out.println(y1);
        y1.settelikosMisthos();
        System.out.println("final income amount:" + y1.gettelikosMisthos() + "\n");
        
        //with set methods
        System.out.println("employee number 2");
        Ypallhlos y2 = new Ypallhlos();
        System.out.println("enter base income amount: ");
        bM = scannerUserInput.getDouble();
        y2.setbasikosMisthos(bM);
        System.out.println("enter overtime hours: ");
        oY = scannerUserInput.getInteger();
        y2.setoresYperorias(oY);
        System.out.println("enter degree type: ");
        eP = scannerUserInput.getInteger();
        y2.seteidosPtyxiou(eP);
        System.out.println(y2);
        y2.settelikosMisthos();
        System.out.println("final income amount: " + y2.gettelikosMisthos() + "\n");
    }
    
}
