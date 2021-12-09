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

    public void AddToBooklet(Course course, float grade) {
        this.courseList.add(course);
        this.gradeList.add(grade);
    }
}
