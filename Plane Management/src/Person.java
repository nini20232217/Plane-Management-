public class Person {      // Creating a class named Person
    private String name;
    private String surname;
    private String email;

    // Adding a Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Adding getters and setters
    public String get_Name() {
        return name;
    }

    public void set_Name(String name) {
        this.name = name;
    }

    public String get_Surname() {
        return surname;
    }

    public void set_Surname(String surname) {
        this.surname = surname;
    }

    public String get_Email() {
        return email;
    }

    public void set_Email(String email) {
        this.email = email;
    }
    public String give_details(){
        return "Name: "+ name + ",Surname: " + surname+ "\n Email address:" + email;
    }
}
