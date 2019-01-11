/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg9_ask1;

/**
 *
 * @author angle
 */
public class Erg9_Ask1 {

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
        showTelikosMisthos(y1);
        
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
        System.out.println("final income amount: " + returnTelikosMisthos(y2));
        
    }
    
    
    static void showTelikosMisthos(Ypallhlos y) {
        int timhYperorias = y.oresYperorias * 20;
        int epidomaPtyxiou = 0;
        double telikosMisthos;
        
        switch (epidomaPtyxiou) {
            case 1: epidomaPtyxiou = 300;
                break;
            case 2: epidomaPtyxiou = 150;
                break;
            case 3: epidomaPtyxiou = 100;
                break;
            case 4: epidomaPtyxiou = 50;
                break;
            default: epidomaPtyxiou = 0;
        }
        
        System.out.println("final income amount: " + (telikosMisthos = y.basikosMisthos + timhYperorias + epidomaPtyxiou));
    }
    
    
    static double returnTelikosMisthos(Ypallhlos y) {
        int timhYperorias = y.oresYperorias * 20;
        int epidomaPtyxiou = 0;
        double telikosMisthos;
        
        switch (epidomaPtyxiou) {
            case 1: epidomaPtyxiou = 300;
                break;
            case 2: epidomaPtyxiou = 150;
                break;
            case 3: epidomaPtyxiou = 100;
                break;
            case 4: epidomaPtyxiou = 50;
                break;
            default: epidomaPtyxiou = 0;
        }
        
        return telikosMisthos = y.basikosMisthos + timhYperorias + epidomaPtyxiou;
    }
}
