package erg5_ask1;

public class statheroTilefono extends Tilefono {
    
    private int kilishStathero;
    private String typosThlefwnou;
    
    public statheroTilefono () {}
    
    public statheroTilefono(String oK, int aT, int lK) {
        super(oK, aT, lK);
        this.typosThlefwnou = "Stathero";
    }

    public int getKilishStathero() {
        return kilishStathero;
    }

    public String getTyposThlefwnou() {
        return typosThlefwnou;
    }

    public void setKilishStathero(int kilishStathero) {
        this.kilishStathero = kilishStathero;
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
