package athinamhxlog;

import java.util.ArrayList;

public class Course {

    private String courseID;
    private String courseName;
    private int courseECTS;
    private int courseSemester;
    private ArrayList<Professor> courseProfessorList;

    public Course() {
        this.courseProfessorList = new ArrayList<Professor>();
    }

    public Course(String courseID, String courseName, int courseECTS, int courseSemester, ArrayList<Professor> courseProfessorList) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseECTS = courseECTS;
        this.courseSemester = courseSemester;
        this.courseProfessorList = courseProfessorList;
    }

}
