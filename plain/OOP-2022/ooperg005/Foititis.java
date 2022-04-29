package ooperg005;

import java.text.*;
import java.util.*;

public class Foititis {

    private static int AA = 0;
    private String AM;
    private String onomatEpwnymo;
    private Date hmeromGennisis;
    private int etosEisagwgis;
    private String thlefwno;
    private float mesosOros;

    public Foititis() {
    }

    public Foititis(String onomatEpwnymo, int etosEisagwgis) {
        this.onomatEpwnymo = onomatEpwnymo;
        this.etosEisagwgis = etosEisagwgis;
        AA++;
        DecimalFormat mf = new DecimalFormat("0000");
        this.AM = (etosEisagwgis + mf.format(AA));
    }

    public Foititis(String onomatEpwnymo, Date hmeromGennisis, int etosEisagwgis, String thlefwno, float mesosOros) {
        this.onomatEpwnymo = onomatEpwnymo;
        this.hmeromGennisis = hmeromGennisis;
        this.etosEisagwgis = etosEisagwgis;
        this.thlefwno = thlefwno;
        this.mesosOros = mesosOros;
        AA++;
        DecimalFormat mf = new DecimalFormat("0000");
        this.AM = (etosEisagwgis + mf.format(AA));
    }

    public void setHmeromGennisis(Date hmeromGennisis) {
        this.hmeromGennisis = hmeromGennisis;
    }

    public String getOnomatEpwnymo() {
        return onomatEpwnymo;
    }

    public String getEpwnymo() {
        return onomatEpwnymo.split("\\s+")[1];
    }

    public Date getHmeromGennisis() {
        return hmeromGennisis;
    }

    public String getAM() {
        return AM;
    }

    public String getThlefwno() {
        return thlefwno;
    }

    public float getMesosOros() {
        return mesosOros;
    }

    @Override
    public String toString() {
        return (AM + " " + onomatEpwnymo + " " + hmeromGennisis + " ");
    }
}
