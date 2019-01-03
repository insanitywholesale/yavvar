/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg4_ask1;

/**
 *
 * @author angle
 */
public class Erg4_Ask1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int month;
        String monthString;
        System.out.print("enter integer for month");
        month = scannerUserInput.getInteger();
        
        if (month == 1)
            monthString = "January";
        else if (month == 2)
            monthString = "February";
        else if (month == 3)
            monthString = "March";
        else if (month == 4)
            monthString = "April";
        else if (month == 5)
            monthString = "May";
        else if (month == 6)
            monthString = "June";
        else if (month == 7)
            monthString = "July";
        else if (month == 8)
            monthString = "August";
        else if (month == 9)
            monthString = "September";
        else if (month == 10)
            monthString = "October";
        else if (month == 11)
            monthString = "November";
        else if (month == 12)
            monthString = "December";
        else monthString = "Invalid month";
        
        System.out.println("Month: " + monthString);
        
    }
    
}
