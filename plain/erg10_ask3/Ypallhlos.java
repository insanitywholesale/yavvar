/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg10_ask3;

/**
 *
 * @author angle
 */
public class Ypallhlos {
    private int arithmosMitrwou, oresYperorias, eidosPtyxiou;
    static int kodikosEpixeirhshs = 33010;
    private double basikosMisthos;
    
    Ypallhlos() {
    //intentionally left blank
    }
    
    Ypallhlos(int aM, int oY, int eP, double bM) {
        arithmosMitrwou = aM;
        oresYperorias = oY;
        eidosPtyxiou = eP;
        basikosMisthos = bM;
    }
    
    //get   
    int getkodikosEpixeirhshs() { 
        return kodikosEpixeirhshs;
    }
    int getarithmosMitrwou() {
        return arithmosMitrwou;
    } 
    int getoresYperorias() {
        return oresYperorias;
    }
    int geteidosPtyxiou() {
        return eidosPtyxiou;
    }
    double getbasikosMisthos() {
        return basikosMisthos;
    }
    
    //set

    void setarithmosMitrwou(int aM) {
        arithmosMitrwou = aM;
    }
    void setoresYperorias(int oY) {
        oresYperorias = oY;
    
    }
    void seteidosPtyxiou(int eP) {
        eidosPtyxiou = eP;
    }
    void setbasikosMisthos(double bM) {
        basikosMisthos = bM;
    }
    void setkodikosEpixeirhshs(int kE) {
        kodikosEpixeirhshs = kE;
    }
    
    //methods
    void showTelikosMisthos() {
        int timhYperorias = oresYperorias * 20;
        int epidomaPtyxiou = 0;
        double telikosMisthos;
        
        switch (epidomaPtyxiou) {
            case 1: epidomaPtyxiou = 300;
                break;
            case 2: epidomaPtyxiou = 150;
                break;
            case 3: epidomaPtyxiou = 100;
                break;
            case 4: epidomaPtyxiou = 50;
                break;
            default: epidomaPtyxiou = 0;
        }
        
        System.out.println("final income amount: " + (telikosMisthos = basikosMisthos + timhYperorias + epidomaPtyxiou) + "\n");
    }
    
    double returnTelikosMisthos() {
        int timhYperorias = oresYperorias * 20;
        int epidomaPtyxiou = 0;
        double telikosMisthos;
        
        switch (epidomaPtyxiou) {
            case 1: epidomaPtyxiou = 300;
                break;
            case 2: epidomaPtyxiou = 150;
                break;
            case 3: epidomaPtyxiou = 100;
                break;
            case 4: epidomaPtyxiou = 50;
                break;
            default: epidomaPtyxiou = 0;
        }
        
        return telikosMisthos = basikosMisthos + timhYperorias + epidomaPtyxiou;
    }
    
    @Override //the following method overrides the default behaviour
    public String toString() {
        String s = "\nbusiness serial number: " + kodikosEpixeirhshs + "\nperson serial number: " + arithmosMitrwou + "\nbase income amount: ";
        s += basikosMisthos + "\novertime hours: " + oresYperorias + "\ndegree type: " + eidosPtyxiou;
        return s;
    }
}
