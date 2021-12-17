package pithia2;

import java.util.ArrayList;

public class Professor extends Person {

    private String professorID;
    private ArrayList<Course> courseList;

    public Professor() {
    }

    public Professor(String username, String password) {
        super(username, password);
        this.courseList = new ArrayList<Course>();
    }

    public Professor(String professorID) {
        this.professorID = professorID;
        this.courseList = new ArrayList<Course>();
    }

    public ArrayList<Course> getcourseList(Professor p) {
        return courseList;
    }

    public String[] getcourseListStrings(Professor p) {
        String[] s = new String[courseList.size()];
        for (int i=0; i<courseList.size();i++) {
            s[i] = courseList.get(i).courseToString();
        }
        return s;
    }

    public void addCourse(Course c) {
        courseList.add(c);
    }

}
