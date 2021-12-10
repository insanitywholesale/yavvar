package athinamhxlog;

import java.util.ArrayList;

public class Professor extends Person {

    private String professorID;
    private ArrayList<Course> courseList;

    public Professor() {
    }

    public Professor(String professorID) {
        this.professorID = professorID;
    }

}
