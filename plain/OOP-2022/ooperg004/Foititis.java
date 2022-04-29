package ooperg004;

import java.text.*;
import java.util.*;

public class Foititis {

    private static int AA = 0;
    private String AM;
    private String onomatEpwnymo;
    private Date hmeromGennisis;
    private int etosEisagwgis;

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

    @Override
    public String toString() {
        return (AM + " " + onomatEpwnymo + " ");
    }
}
