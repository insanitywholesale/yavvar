package athinamhxlog;

import java.util.ArrayList;
import java.util.Collections;

//TODO: switch grade from float to double
public class AthinaMhxLog {

    public static void fillProfList(ArrayList<Person> pl, ArrayList<Professor> prl) {
        for (int i = 0; i < pl.size(); i++) {
            if (pl.get(i) instanceof Professor) {
                Professor p = (Professor) (pl.get(i));
                prl.add(p);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();
        ArrayList<Professor> profs = new ArrayList<Professor>();
        //create some people
        Administrator adm0 = new Administrator("0");
        Professor prof0 = new Professor("0");
        Student stud0 = new Student("0");
        //add people to peoplelist
        people.add(adm0);
        people.add(prof0);
        people.add(stud0);
        //add courses with grades to student's gradingbooklet
        Course crs0 = new Course("1742", "Mhx Log", 6, 7, new ArrayList<>(Collections.singletonList(prof0)));
        Course crs1 = new Course("1941", "ADISE", 6, 9, new ArrayList<>(Collections.singletonList(prof0)));
        stud0.getBooklet().addToBooklet(adm0, crs0, 8);
        stud0.getBooklet().addToBooklet(adm0, crs1, 9);
        //US06
        //login
        ArrayList<Course> cL = prof0.getcourseList(prof0);
        String stid = "0";
        String cid = "1742";
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            boolean isStudent = p instanceof Student;
            if (isStudent) {
                Student s = (Student) (p);
                double oldGrade = s.getBooklet().getGradeByCourseID(cid);
                s.getBooklet().setGradeByCourseID(cid, 8.5);
            }
        }
        //US07
        fillProfList(people, profs);
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            if (p instanceof Student) {
                Student s = (Student) p;
                GradingBooklet gb = s.getBooklet();
                for (int j = 0; j < gb.getChangedList().size(); j++) {
                    if (gb.getChangedList().get(i)) {
                        Course courseList = gb.getCourseList().get(i);
                        double gradeList = gb.getGradeList().get(i);
                        boolean finalByProfList = gb.getFinalByProfList().get(i);
                        boolean changedByProfList = gb.getChangedList().get(i);
                        boolean finalByAdminList = gb.getFinalByProfList().get(i);
                        String sid = s.getStudentID();
                    }
                }
            }
        }
    }

}
