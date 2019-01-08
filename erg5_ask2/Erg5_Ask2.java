/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg5_ask2;

/**
 *
 * @author angle
 */
public class Erg5_Ask2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num, timhDynamhs, ektheths;
        
        do { System.out.print("enter a number between 2 and 5: ");
            num = scannerUserInput.getInteger();
        }
        while ( num < 2 || num > 5 );
        System.out.println("the number is " + num + ".");
       
        System.out.println("the powers of " + num + " are:");
        
        ektheths = 1;
        timhDynamhs = (int)(Math.pow(num, ektheths));
        
         while (timhDynamhs <= 1000) {
            System.out.println(timhDynamhs);
            ektheths += 1;
            timhDynamhs = (int)(Math.pow(num, ektheths));
            
        }
    }
    
}
