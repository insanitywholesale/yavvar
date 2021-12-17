package pithia2;

public class Student extends Person {

    private String studentID;
    private int semester;
    private int ects;
    private float gpa;
    private GradingBooklet gradingBooklet;

    public Student() {
        this.gradingBooklet = new GradingBooklet();
    }

    public Student(String studentID) {
        this.studentID = studentID;
        this.gradingBooklet = new GradingBooklet();
    }

    public Student(String username, String password) {
        super(username, password);
        this.studentID = username + "12345";
        this.gradingBooklet = new GradingBooklet();
    }
    
    public Student(String studentID, int semester, int ects, float gpa, GradingBooklet gradingBooklet) {
        this.studentID = studentID;
        this.semester = semester;
        this.ects = ects;
        this.gpa = gpa;
        this.gradingBooklet = gradingBooklet;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getSemester() {
        return semester;
    }

    public int getEcts() {
        return ects;
    }

    public float getGpa() {
        return gpa;
    }

    public GradingBooklet getGradingBooklet() {
        return gradingBooklet;
    }

    public GradingBooklet getBooklet() {
        return gradingBooklet;
    }

}
