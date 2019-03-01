/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg4_ask3;

/**
 *
 * @author angle
 */
public class Erg4_Ask3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int posoEisodhmatos, arithmosAkinhtwn;
        int aforologhtoEisodhma, forologhteoEisodhma;
        int ekptoshApoForo, ekptoshApoEisodhma;
        double forosPouAnalogei, telikoPosoForou;
        
        System.out.print("insert income amount: ");
        posoEisodhmatos = scannerUserInput.getInteger();
        
        System.out.print("insert estate amount: ");
        arithmosAkinhtwn = scannerUserInput.getInteger();
        
        System.out.print("deduction from taxes: ");
        ekptoshApoForo = scannerUserInput.getInteger();
        
        System.out.print("deduction from income: ");
        ekptoshApoEisodhma = scannerUserInput.getInteger();
        
        System.out.println("\n***---===INPUT DATA===---***");
        
        System.out.println("\nincome:" + posoEisodhmatos + "\nestates: " + arithmosAkinhtwn + "\ntax deduction: " + ekptoshApoForo + "\nincome deduction: " + ekptoshApoEisodhma);
        
        switch (arithmosAkinhtwn) {
            case 0: aforologhtoEisodhma = 12000;
                break;
            case 1: aforologhtoEisodhma = 10000;
                break;
            case 2: aforologhtoEisodhma = 8000;
                break;
            case 3: aforologhtoEisodhma = 5000;
                break;
            default: aforologhtoEisodhma = 0;
        }
        
        System.out.println("\n***---===RESULTS===---***\n");
        
        System.out.println("untaxed income:" + aforologhtoEisodhma);
        forologhteoEisodhma = posoEisodhmatos - aforologhtoEisodhma;
        
        forologhteoEisodhma -= ekptoshApoEisodhma;
        System.out.println("taxed income: " + forologhteoEisodhma);
        System.out.println("tax on income: " + (forosPouAnalogei = forologhteoEisodhma * 0.3));
        System.out.println("final tax: " + (telikoPosoForou = forosPouAnalogei - ekptoshApoForo));
        
    }
    
}
