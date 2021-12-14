package athinamhxlog;

import java.util.ArrayList;

public class GradingBooklet {

    private ArrayList<Course> courseList;
    private ArrayList<Double> gradeList;
    private ArrayList<Boolean> finalByProfList;
    private ArrayList<Boolean> changedByProfList;
    private ArrayList<Boolean> finalByAdminList;

    public GradingBooklet() {
        this.courseList = new ArrayList<Course>();
    }

    public GradingBooklet(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public GradingBooklet(ArrayList<Course> courseList, ArrayList<Double> gradeList) {
        this.courseList = courseList;
        this.gradeList = gradeList;
    }

    public void addToBooklet(Person person, Course course, double grade) {
        boolean isAdm = person instanceof Administrator;
        boolean isProf = person instanceof Professor;
        if (isAdm || isProf) {
            this.courseList.add(course);
            this.gradeList.add(grade);
        }
    }

    public Course getCourseByCourseID(String courseID) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(courseID)) {
                return courseList.get(i);
            }
        }
        return null;
    }

    public double getGradeByCourseID(String courseID) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(courseID)) {
                return gradeList.get(i);
            }
        }
        return -1;
    }

    public void setGradeByCourseID(String courseID, double grade) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(courseID)) {
                gradeList.set(i, grade);
            }
        }
    }

    //TODO: add toString
}
