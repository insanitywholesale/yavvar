/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg7_ask1;

/**
 *
 * @author angle
 */
public class Erg7_Ask1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 5, index = (int)((n-2)*Math.random()), thesimax, thesimin;
        int pin[] = new int[n];
        fillPin(pin);
        showPin(pin);
        thesimax = findThesiMax(pin);
        thesimin = findThesiMin(pin);
        System.out.println("thesimax = " + thesimax + " " + "thesimin = " + thesimin);
        System.out.println("index: " + index);
        pin = swapPin(index, pin);
        showPin(pin);
    }
    
    static void fillPin(int pin[]) {
        int i, n = pin.length;
        for (i=0; i <= n-1; i++) {
            pin[i] = (int)(10*Math.random() + 1);
        }
    }
    
    static void showPin(int pin[]) {
        int i, n = pin.length;
        for (i=0; i <= n-1; i++) {
            System.out.println(pin[i]);
        }
    }
    
    static int findThesiMax(int pin[]) {
        int n = pin.length, i, max, thesimax;
        max = pin[1];
        thesimax = 0;
        for ( i=1; i<=(n-1); i++ ) {
            if ( pin[i]>max ) {
                max = pin[i];
                thesimax = i;
            }
        }
        return thesimax;
    }

    static int findThesiMin(int pin[]) {
        int n = pin.length, i, min, thesimin;
        min = pin[1];
        thesimin = 1;
        for ( i=1; i<=(n-1); i++ ) {
            if ( pin[i]<min ) {
                min = pin[i];
                thesimin = i;
            }
        }
        return thesimin;
    }
    
    static int[] swapPin(int index, int pin[]) {
        int temp;
        temp = pin[index];
        pin[index] = pin[index+1];
        pin[index+1] = temp;
        return pin;

    }
}

