/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg4_ask2;

/**
 *
 * @author angle
 */
public class Erg4_Ask2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("enter positive value for x: ");
        int x;
        x = scannerUserInput.getInteger();
        
        switch (x) {
            case 0: System.out.println("x = 0");
                break;
            case 1: System.out.println("x is an odd number");
                break;
            case 2: System.out.println("x is an even number");
                break;
            case 3: System.out.println("x is an odd number");
                break;
            case 4: System.out.println("x is an even number");
                break;
            case 5: System.out.println("x is an odd number");
                break;
            default: System.out.println("x is bigger than 5");
        }
    }
    
}
