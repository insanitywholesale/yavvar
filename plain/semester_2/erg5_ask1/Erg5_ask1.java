package erg5_ask1;

public class Erg5_ask1 {

    public static void main(String[] args) {
        int n = 5;
        Tilefono pin[] = new Tilefono[n];
        pin[0] = new kinhtoTilefono("aaa", 111, 10);
        pin[1] = new kinhtoTilefono("bbb", 222, 20);
        pin[2] = new statheroTilefono("ccc", 333, 30);
        pin[3] = new kinhtoTilefono("ddd", 444, 40);
        pin[4] = new statheroTilefono("eee", 555, 50);
        
        call(pin[3], pin[2]);
        call(pin[0], pin[2]);
        call(pin[1], pin[4]);
        call(pin[3], pin[1]);
        
        for(int i=0;i<pin.length;i++)
            System.out.println(pin[i]);
        
    }
    
    public static void call(Tilefono thlefwno1, Tilefono thlefwno2){
        String type1 = thlefwno1.typosThlefwnou();
        String type2 = thlefwno2.typosThlefwnou();
        
        if("Stathero".equals(type2)) {
            thlefwno1.klisiStathero(thlefwno1.getLeptaKlhshs());
        }
        else {
            thlefwno1.klisiKinhto(thlefwno1.getLeptaKlhshs());
        }
    
    }
}
