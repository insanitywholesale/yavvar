package athinamhxlog;

import java.util.ArrayList;

public class Administrator extends Person {

    private String adminID;
    //private ArrayList<Person> peopleList;

    private void initPeopleList() {
        //this.peopleList = new ArrayList<Person>();
    }

    public Administrator() {
        initPeopleList();
    }

    public Administrator(String adminID) {//, ArrayList<Person> peopleList) {
        this.adminID = adminID;
        //this.peopleList = peopleList;
    }
}
