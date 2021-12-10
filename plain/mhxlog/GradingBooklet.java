package athinamhxlog;

import java.util.ArrayList;

public class GradingBooklet {

    private ArrayList<Course> courseList;
    private ArrayList<Float> gradeList;

    public GradingBooklet() {
        this.courseList = new ArrayList<Course>();
    }

    public GradingBooklet(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public GradingBooklet(ArrayList<Course> courseList, ArrayList<Float> gradeList) {
        this.courseList = courseList;
        this.gradeList = gradeList;
    }

    public void addToBooklet(Person person, Course course, float grade) {
        boolean isAdm = person instanceof Administrator;
        boolean isProf = person instanceof Professor;
        if (isAdm || isProf) {
            this.courseList.add(course);
            this.gradeList.add(grade);
        }
    }

    //TODO: add toString
}
