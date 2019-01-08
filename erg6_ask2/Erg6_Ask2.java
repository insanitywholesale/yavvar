/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg6_ask2;

/**
 *
 * @author angle
 */
public class Erg6_Ask2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n, npar, i;
        
        do { System.out.print("enter a number between 0 and 10: ");
            n = scannerUserInput.getInteger();
        }
        while ( n < 0 || n > 10 );
        
        npar = 1;
        
        for ( i=2; i<=n; i++ ) {
            npar = npar*i;
        }
        
        System.out.println("npar's value is " + npar);
    }
    
}
