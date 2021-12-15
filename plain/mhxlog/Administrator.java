package athinamhxlog;

import java.util.ArrayList;

public class Administrator extends Person {

    private String adminID;

    private void initPeopleList() {
    }

    public Administrator() {
        initPeopleList();
    }

    public Administrator(String adminID) {
        this.adminID = adminID;
    }
}
