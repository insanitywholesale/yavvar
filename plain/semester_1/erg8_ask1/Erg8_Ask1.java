/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg8_ask1;

/**
 *
 * @author angle
 */
public class Erg8_Ask1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 10; int m = 10;
        int thesimax, thesimin;
        int my2DArray[][] = new int[n][m];
        int avgLine[];
        int avgCol[];
        fillPin(n, m, my2DArray);
        System.out.println("\nshowpin2d");
        showPin2D(n, m, my2DArray);
        avgLine = findMeanLine(n, m, my2DArray);
        avgCol = findMeanCol(n, m, my2DArray);
        System.out.println("\nshowpin1d avgline");
        showPin1D(avgLine);
        System.out.println("\nshowpin1d avgcol");
        showPin1D(avgCol);
        System.out.println("\ndiagonal sum: " + findDSum(n, m, my2DArray));
    }
    
    
    
    static void fillPin(int n, int m, int pin2d[][]) {
        int i, j;
        for ( i=0; i<n; i++ ) {
            for ( j=0; j<m; j++ ) {
                pin2d[i][j] = (int)(9*Math.random());
            }
        }
    }
    
    
    static void showPin2D(int n, int m, int pin2d[][]) {
        int i, j;
        for ( i=0; i<n; i++ ) {
            for ( j=0; j<m; j++ ) {
                System.out.println(pin2d[i][j]);
            }
        }
    }
    
    
    static int[] findMeanLine(int n, int m, int pin2d[][]) {
        int i, j, linesum;
        int pin1d[] = new int[m];
        for ( i=0; i<n; i++ ) {
            linesum = 0;
            for ( j=0; j<m; j++ ) {
                linesum += pin2d[i][j];
            }
            pin1d[i] = linesum/m;
        }
        return pin1d;
    }
    
    
    static void showPin1D(int pin1d[]) {
        int i, n = pin1d.length;
        for (i=0; i <= n-1; i++) {
            System.out.println(pin1d[i]);
        }
    }
    
    
    static int[] findMeanCol(int n, int m, int pin2d[][]) {
        int i, j, colsum;
        int pin1d[] = new int [n];
        for ( j=0; j<m; j++ ) {
            colsum = 0;
            for ( i=0; i<n; i++ ) {
                colsum += pin2d[i][j];
            }
            pin1d[j] = colsum/n;
        }
        return pin1d;
    }
    
    
    static int findDSum(int n, int m, int pin2d[][]){
        int i, j; int diag1=0, diag2=0, diagAdd=0;
        for ( i=0; i<n; i++ ) {
            for( j=0; j<m; j++ ) {
                diag1 += pin2d[i][j];
            }
        }
        for ( i=n; i<n; i++ ) {
            for( j=(m-1); j>=0; j--) {
                diag2 += pin2d[i][j];
            }
        }
        if (pin2d[0].length == pin2d.length) {
            diagAdd = diag1 + diag2;
        }
        else {
            System.out.println("this isn't square!");
        }
        return diagAdd;
    }
    
    
}
