public class Person {
    private String name;
    private String surname;
    private String email;
    public Person(String name, String surname, String email) {
        this.name = name;// "this" is used for refers to the current object in a method or constructor.
        this.surname = surname;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to print information
    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }

    // @Override learned through https://stackoverflow.com/questions/94361/when-do-you-use-javas-override-annotation-and-why
    @Override
    public String toString() {
        return "Name: " + name + "\nSurname: " + surname + "\nEmail: " + email;
    }
}
