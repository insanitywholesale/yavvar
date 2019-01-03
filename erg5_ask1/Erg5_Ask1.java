/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg5_ask1;

/**
 *
 * @author angle
 */
public class Erg5_Ask1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int num;
        
        do { System.out.println("enter a number between 2 and 5: ");
            num = scannerUserInput.getInteger();
        }
        while ( num < 2 || num > 5 );
        System.out.println("the number is " + num);
       
        
    }
    
}
