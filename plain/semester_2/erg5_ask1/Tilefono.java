package erg5_ask1;
/*

*/
public class Tilefono {
    
    private String onomaKatoxou;
    private String typosThlefwnou;
    private int arithmosTilefonou;
    private int leptaKlhshs;
    private static double synolikoKostosKlhsewn;
    
    public Tilefono () {}

    public Tilefono(String oK, int aT, int lK) {
        this.onomaKatoxou = oK;
        this.arithmosTilefonou = aT;
        this.leptaKlhshs = lK;
        synolikoKostosKlhsewn += synolikoKostosKlhsewn;
    }

    public String getOnomaKatoxou() {
        return onomaKatoxou;
    }

    public int getArithmosTilefonou() {
        return arithmosTilefonou;
    }

    public double getSynolikoKostosKlhsewn() {
        return synolikoKostosKlhsewn;
    }

    public int getLeptaKlhshs() {
        return leptaKlhshs;
    }

    public String getTyposThlefwnou() {
        return typosThlefwnou;
    }
    
    public void setOnomaKatoxou(String onomaKatoxou) {
        this.onomaKatoxou = onomaKatoxou;
    }

    public void setArithmosTilefonou(int arithmosTilefonou) {
        this.arithmosTilefonou = arithmosTilefonou;
    }

    public void setSynolikoKostosKlhsewn(double synolikoKostosKlhsewn) {
        this.synolikoKostosKlhsewn = synolikoKostosKlhsewn;
    }

    public void setTyposThlefwnou(String typosThlefwnou) {
        this.typosThlefwnou = typosThlefwnou;
    }

    public void setLeptaKlhshs(int leptaKlhshs) {
        this.leptaKlhshs = leptaKlhshs;
    }
    
    public String typosThlefwnou() {
        return typosThlefwnou;
    }
    
    public double klisiKinhto(int lK){
        double poso = lK * 0.10;
        setSynolikoKostosKlhsewn(getSynolikoKostosKlhsewn() + poso);
        return poso;
    }
    
    public double klisiStathero(int lK){
        double poso = lK * 0.20;
        setSynolikoKostosKlhsewn(getSynolikoKostosKlhsewn() + poso);
        return poso;
    }

    @Override
    public String toString() {
	//elpizw na doulevei giati den to testara
	String s = "typosThlefwnou= " + typosThlefwnou " synolikoKostosKlhsewn= " + synolikoKostosKlhsewn;
	return s;
    }
    
    
}
