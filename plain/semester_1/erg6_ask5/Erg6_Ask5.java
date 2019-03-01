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
public class Erg6_Ask5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n, npar, i;
        boolean protos=true;
        
        do { System.out.print("enter a number between 0 and 10: ");
            n = scannerUserInput.getInteger();
        }
        while ( n < 0 || n > 10 );
        
        npar = 1;
        
        for ( i=2; i<=n; i++ ) {
            npar = npar*i;
        }
        
        System.out.println("npar's value is " + npar);
        System.out.println("\nthis time with a method\n");
        findParagontiko1();
        System.out.println("\nthis time with a method in a different class\n");
        staticMethods.findParagontiko2();
        
        
        do { System.out.print("enter a number between 2 and 100: ");
            n = scannerUserInput.getInteger();
        }
        while ( n < 2 || n > 100 );
        
         for( i=2; (n/2)>i; i++ ){
            if( (n%i) == 0 ){
                protos = false;
            }
        }
        
        if ( protos==true ) {
            System.out.println("n is a prime number");
        }
        else {
            System.out.println("n is not a prime number");
        }
        
        System.out.println("the value of the variable protos is: " + protos);
        System.out.println("\nthis time with a method\n");
        isProtos1();
        System.out.println("\nthis time with a method in a different class\n");
        staticMethods.isProtos2();
    }
    
    
    static void findParagontiko1() {
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
    
    
    static void isProtos1() {
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
        
        if ( protos==true ) {
            System.out.println("n is a prime number");
        }
        else {
            System.out.println("n is not a prime number");
        }
        
        System.out.println("the value of the variable protos is: " + protos);
    }
}
