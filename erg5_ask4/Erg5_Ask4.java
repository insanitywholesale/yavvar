/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg5_ask4;

/**
 *
 * @author angle
 */
public class Erg5_Ask4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int xorhtikothta, theseis = 0, roomCount = 0;
        int ypopshfioi; ypopshfioi = 100;
        int epithrhtes;
        
        do {
            System.out.print("enter room capacity: ");
            xorhtikothta = scannerUserInput.getInteger();
            theseis += xorhtikothta;
            roomCount++;
            
            if ( xorhtikothta <= 15 ) {
                epithrhtes = 1;
            }
            else if ( xorhtikothta <=23 ) {
                epithrhtes = 2;
            }
            else {
                epithrhtes = 3;
            }
        }
        while (theseis < ypopshfioi);
        System.out.println("rooms used: " + roomCount + ", supervisors: " + epithrhtes + ", total seats: " + theseis);
    }
    
}
