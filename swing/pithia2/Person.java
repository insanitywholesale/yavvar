package pithia2;

public class Person {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Person(String firstName, String lastName, String username, String password, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public boolean login(String username, String password) {
        return (username == null ? this.username == null : username.equals(this.username)) && (password == null ? this.password == null : password.equals(this.password));
    }

    public String getUsername() {
        return username;
    }
    

}
