/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg9_ask2;

/**
 *
 * @author angle
 */
public class Erg9_Ask2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ypallhlos y1 = new Ypallhlos();
        System.out.println("employee number 1");
        System.out.println("enter business serial number: ");
        y1.kodikosEpixeirhshs = scannerUserInput.getInteger();
        System.out.println("enter person serial number: ");
        y1.arithmosMitrwou = scannerUserInput.getInteger();
        System.out.println("enter base income amount: ");
        y1.basikosMisthos = scannerUserInput.getDouble();
        System.out.println("enter overtime hours: ");
        y1.oresYperorias = scannerUserInput.getInteger();
        System.out.println("enter degree type: ");
        y1.eidosPtyxiou = scannerUserInput.getInteger();
        System.out.println(y1);
        y1.showTelikosMisthos();
        
        Ypallhlos y2 = new Ypallhlos();
        System.out.println("employee number 2");
        System.out.println("enter business serial number: ");
        y2.kodikosEpixeirhshs = scannerUserInput.getInteger();
        System.out.println("enter person serial number: ");
        y2.arithmosMitrwou = scannerUserInput.getInteger();
        System.out.println("enter base income amount: ");
        y2.basikosMisthos = scannerUserInput.getDouble();
        System.out.println("enter overtime hours: ");
        y2.oresYperorias = scannerUserInput.getInteger();
        System.out.println("enter degree type: ");
        y2.eidosPtyxiou = scannerUserInput.getInteger();
        System.out.println(y2);
        System.out.println("final income amount: " + y2.returnTelikosMisthos() + "\n");
    }
    
}
