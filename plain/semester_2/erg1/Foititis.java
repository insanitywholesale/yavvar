package erg1;

import java.util.Date;
import java.text.*;

public class Foititis {
    private static int auxwnArithmos = 0;
    private String AM;
    private String onomatEpwnymo;
    private Date hmeromGennisis;

    int getauxwnArithmos() {return auxwnArithmos;}
    String getAM() {return AM;}
    String getonomatEpwnymo() {return onomatEpwnymo;}
    Date getHmerom() {return hmeromGennisis;}

    void setHmerom(Date hg) {hmeromGennisis = hg;} 

    public Foititis(int etos, String onomatEpwnymo, Date hmeromGennisis) {
        auxwnArithmos+=1;
        // Δημιουργία αριθμού 3 ψηφίων
        DecimalFormat myFormatter = new DecimalFormat("000");
        String tempArithm = myFormatter.format(auxwnArithmos);
        this.AM= (etos + tempArithm); 
        this.onomatEpwnymo = onomatEpwnymo;
        this.hmeromGennisis = hmeromGennisis;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(this.AM + " ");
        sb.append(this.onomatEpwnymo + " ");
        if(this.hmeromGennisis != null)
            sb.append(dateToStr(this.hmeromGennisis));
        return sb.toString();
    }

    private String dateToStr(Date hmeromGennisis) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String str = df.format(this.hmeromGennisis);
        return str;
    }
}
