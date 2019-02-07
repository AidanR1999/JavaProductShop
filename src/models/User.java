package models;

public class User {

    //Attributes
    private String username;
    private String password;
    private String forename;
    private String surname;
 
    //Constructors
    //empty
    public User(){
        this.username = "null";
        this.password = "null";
        this.forename = "null";
        this.surname = "null";
    }
    //all attributes
    public User(String username, String password, String forename, String surname) {
        this.username = username;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
    }
    
    //Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
