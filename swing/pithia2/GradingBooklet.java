package pithia2;

import java.util.ArrayList;

public class GradingBooklet {

    private ArrayList<Course> courseList;
    private ArrayList<Double> gradeList;
    private ArrayList<Boolean> finalByProfList;
    private ArrayList<Boolean> changedByProfList;
    private ArrayList<Boolean> finalByAdminList;

    public GradingBooklet() {
        this.courseList = new ArrayList<Course>();
        this.gradeList = new ArrayList<Double>();
        this.finalByProfList = new ArrayList<Boolean>();
        this.changedByProfList = new ArrayList<Boolean>();
        this.finalByAdminList = new ArrayList<Boolean>();
    }

    public GradingBooklet(ArrayList<Course> courseList) {
        this.courseList = courseList;
        this.finalByProfList = new ArrayList<Boolean>();
        this.changedByProfList = new ArrayList<Boolean>();
        this.finalByAdminList = new ArrayList<Boolean>();
    }

    public GradingBooklet(ArrayList<Course> courseList, ArrayList<Double> gradeList) {
        this.courseList = courseList;
        this.gradeList = gradeList;
        this.finalByProfList = new ArrayList<Boolean>();
        this.changedByProfList = new ArrayList<Boolean>();
        this.finalByAdminList = new ArrayList<Boolean>();
    }

    public void addToBooklet(Person person, Course course, double grade) {
        boolean isAdm = person instanceof Administrator;
        boolean isProf = person instanceof Professor;
        if (isAdm || isProf) {
            this.courseList.add(course);
            this.gradeList.add(grade);
            this.finalByProfList.add(false);
            this.changedByProfList.add(false);
            this.finalByAdminList.add(false);
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
                finalByProfList.set(i, true);
                changedByProfList.set(i, true);
            }
        }
    }
    public void setFinalByAdminByCourseID(Administrator a, String courseID) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(courseID)) {
                finalByAdminList.set(i, true);
            }
        }
    }

    public ArrayList<Boolean> getChangedList() {
        return changedByProfList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Double> getGradeList() {
        return gradeList;
    }

    public ArrayList<Boolean> getFinalByProfList() {
        return finalByProfList;
    }

    public ArrayList<Boolean> getFinalByAdminList() {
        return finalByAdminList;
    }

    public String[] getCoursesAndGrades() {
        String[] s = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            s[i] = courseList.get(i).courseToString() + " " + gradeList.get(i);
        }
        return s;
    }
    
    public void printGradingBooklet() {
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i).courseToString() + " " + gradeList.get(i) + " " + finalByProfList.get(i) + " " + changedByProfList.get(i) + " " + finalByAdminList.get(i));
        }
    }

}
