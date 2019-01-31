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
public class Erg10_Ask6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i, n=5, oY, eP;
        double bM;
        
        Ypallhlos pinYp[] = new Ypallhlos [n];
        for ( i=0; i<pinYp.length; i++ ) {
            oY = (int)(Math.random() * 40);
            eP = (int)(Math.random() * 5);
            bM = (int)(20000 + Math.random() * 30001);
            pinYp[i] = new Ypallhlos(oY, eP, bM);
            //System.out.println(pinYp[i]);
        }
        System.out.println(staticMethods.findMaxSalaryAll(pinYp));
        staticMethods.setTelikosMisthosAll(pinYp);
    }
    
}
