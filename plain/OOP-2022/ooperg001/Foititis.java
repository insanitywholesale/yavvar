package ooperg001;

public class Foititis {

    private String AM;
    private String onomatEpwnymo;
    private int etosEisagwgis;

    public Foititis(String AM, int etosEisagwgis, String onomatEpwnymo) {
        this.AM = AM;
        this.onomatEpwnymo = onomatEpwnymo;
    }

    public Foititis() {
    }

    public String getAM() {
        return AM;
    }

    public String getOnomatEpwnymo() {
        return onomatEpwnymo;
    }

    public int getEtosEisagwgis() {
        return etosEisagwgis;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public void setOnomatEpwnymo(String onomatEpwnymo) {
        this.onomatEpwnymo = onomatEpwnymo;
    }

    public void setEtosEisagwgis(int etosEisagwgis) {
        this.etosEisagwgis = etosEisagwgis;
    }

    @Override
    public String toString() {
        return "Foititis{" + "\n  AM=" + AM + "\n  onomatEpwnymo=" + onomatEpwnymo + "\n  etosEisagwgis=" + etosEisagwgis + "\n}";
    }
    

}
