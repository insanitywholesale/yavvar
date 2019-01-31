/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg10_ask6;

/**
 *
 * @author angle
 */
public class staticMethods {
    static int trexonAM = 3301;
    
    static void findMaxSalary(Ypallhlos y1, Ypallhlos y2) {
        if ( y1.gettelikosMisthos() > y2.gettelikosMisthos() ) {
            System.out.println(y1);
        }
        else {
            System.out.println(y2);
        }
    }
    
    static Ypallhlos findMaxSalaryAll(Ypallhlos pinYp[]) {
        int i, maxindex = 0;
        for ( i=1; i<pinYp.length; i++ ) {
            if ( pinYp[i-1].getbasikosMisthos() > pinYp[i].getbasikosMisthos() ) {
                maxindex = i-1;
            }
            else {
                maxindex = i;
            }
        }
        return pinYp[maxindex];
    }
    
    static void setTelikosMisthosAll(Ypallhlos pinYp[]) {
        int i;
        for ( i=0; i<pinYp.length-1; i++ ) {
            pinYp[i].settelikosMisthos();
            System.out.println(pinYp[i].gettelikosMisthos());
        }
    }
    
    
}
