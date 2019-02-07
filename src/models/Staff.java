package models;

public class Staff extends User{

    //ATTRIBUTES
    private String position;
    private double salary;
    
    //CONSTRUCTORS
    public Staff(){
        super();
        this.position = "null";
        this.salary = 0.00;
    }
    public Staff(String position, double salary, String username, String password, String forename, String surname){
        super(username, password, forename, surname);
        this.position = position;
        this.salary = salary;
    }
    
    //GETTERS AND SETTERS
    //POSITION
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    //SALARY
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    //METHODS AND FUNCTIONS
    //DISPLAY GREETING FOR CUSTOMER HOME
    public String displayGreeting(){
        return String.format("Welcome %s %s, You are logged in as Staff", getForename(), getSurname());
    }
}
