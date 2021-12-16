package pithia2;

import java.util.ArrayList;

public class Professor extends Person {

    private String professorID;
    private ArrayList<Course> courseList;

    public Professor() {
    }

    public Professor(String username, String password) {
        super(username, password);
    }

    public Professor(String professorID) {
        this.professorID = professorID;
    }

    public ArrayList<Course> getcourseList(Professor p) {
        return courseList;
    }

}
