/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg9_ask2;

/**
 *
 * @author angle
 */
public class Ypallhlos {
    int kodikosEpixeirhshs, arithmosMitrwou, oresYperorias, eidosPtyxiou;
    double basikosMisthos;
    
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
}
