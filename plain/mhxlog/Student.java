package athinamhxlog;

public class Student extends Person {

    private String studentID;
    private int semester;
    private int ects;
    private float gpa;
    private GradingBooklet gradingBooklet;

    public Student() {
        this.gradingBooklet = new GradingBooklet();
    }

    public Student(String studentID, int semester, int ects, float gpa, GradingBooklet gradingBooklet) {
        this.studentID = studentID;
        this.semester = semester;
        this.ects = ects;
        this.gpa = gpa;
        this.gradingBooklet = gradingBooklet;
    }
}
