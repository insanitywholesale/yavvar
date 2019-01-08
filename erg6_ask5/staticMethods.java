/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg6_ask5;

/**
 *
 * @author angle
 */
public class staticMethods {
    static void findParagontiko2() {
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
    

    static void isProtos2() {
        int n, i;
        boolean protos=true;
        
                do { System.out.print("enter a number between 2 and 100: ");
            n = scannerUserInput.getInteger();
        }
        while ( n < 2 || n > 100 );
        
        for( i=2; (n/2)>i; i++ ){
            if( (n%i) == 0 ){
                protos = false;
            }
        }
    }
    
}
