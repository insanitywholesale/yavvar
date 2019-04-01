package erg5_ask1;

public class kinhtoTilefono extends Tilefono {
    
    private int klishKinhto;
    private String typosThlefwnou;
    
    public kinhtoTilefono () {}
    
    public kinhtoTilefono(String oK, int aT, int lK) {
        super(oK, aT, lK);
        this.typosThlefwnou = "Kinhto";
    }
    
    public int getKlishKinhto() {
        return klishKinhto;
    }

    public String getTyposThlefwnou() {
        return typosThlefwnou;
    }

    public void setKlishKinhto(int klishKinhto) {
        this.klishKinhto = klishKinhto;
    }

    public void setTyposThlefwnou(String typosThlefwnou) {
        this.typosThlefwnou = typosThlefwnou;
    }
    
    @Override
     public double klisiKinhto(int lK){
        double poso = lK * 0.10;
        setSynolikoKostosKlhsewn(getSynolikoKostosKlhsewn() + poso);
        return poso;
    }
    
    @Override
    public double klisiStathero(int lK){
        double poso = lK * 0.20;
        setSynolikoKostosKlhsewn(getSynolikoKostosKlhsewn() + poso);
        return poso;
    }
    
    @Override
    public String typosThlefwnou() {
        return typosThlefwnou;
    }
    
}
