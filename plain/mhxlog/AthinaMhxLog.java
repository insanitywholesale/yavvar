package athinamhxlog;

import java.util.ArrayList;
import java.util.Collections;

public class AthinaMhxLog {

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();
        Administrator adm0 = new Administrator(0);
        Professor prof0 = new Professor("0");
        Student stud0 = new Student("0");
        Course crs0 = new Course("1742", "Mhx Log", 6, 7, new ArrayList<>(Collections.singletonList(prof0)));
        Course crs1 = new Course("1941", "ADISE", 6, 9, new ArrayList<>(Collections.singletonList(prof0)));
        stud0.getBooklet().addToBooklet(adm0, crs0, 8);
        stud0.getBooklet().addToBooklet(adm0, crs1, 9);
        people.add(adm0);
        people.add(prof0);
        people.add(stud0);
    }

}
