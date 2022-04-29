package ooperg004;

import java.text.*;
import java.util.*;

public class Foititis implements Comparable<Foititis> {

    private static int AA = 0;
    private String AM;
    private String onomatEpwnymo;
    private Date hmeromGennisis;
    private int etosEisagwgis;

    @Override
    public int compareTo(Foititis other) {
        return Integer.compare(Integer.parseInt(this.AM), Integer.parseInt(other.AM));
    }

    public Foititis() {
    }

    public Foititis(String onomatEpwnymo, int etosEisagwgis) {
        this.onomatEpwnymo = onomatEpwnymo;
        this.etosEisagwgis = etosEisagwgis;
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

    @Override
    public String toString() {
        return (AM + " " + onomatEpwnymo + " " + hmeromGennisis + " ");
    }
}
