package models;

public class User {

    //ATTRIBUTES
    private String username;
    private String password;
    private String forename;
    private String surname;
 
    //CONSTRUCTORS
    public User(){
        this.username = "null";
        this.password = "null";
        this.forename = "null";
        this.surname = "null";
    }
    public User(String username, String password, String forename, String surname) {
        this.username = username;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
    }
    
    //GETTERS AND SETTERS
    //USERNAME
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    //PASSWORD
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //FORENAME
    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }

    //SURNAME
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
