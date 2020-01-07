/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inhereplayer;

/**
 *
 * @author angle
 */
public class Preset {
    int bass;
    int midrange;
    int tremble;
    int balance;
    int volume;

    public Preset(int SlBass, int SlMidrange, int SlTremble, int SlBalance, int SlVolume) {
        this.bass = SlBass;
        this.midrange = SlMidrange;
        this.tremble = SlTremble;
        this.balance = SlBalance;
        this.volume = SlVolume;
    }
}
