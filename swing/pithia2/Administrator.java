package pithia2;

public class Administrator extends Person {

    private String adminID;

    private void initPeopleList() {
    }

    public Administrator() {
        initPeopleList();
    }

    public Administrator(String username, String password) {
        super(username, password);
    }

    public Administrator(String adminID) {
        this.adminID = adminID;
    }
}
