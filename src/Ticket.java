import java.io.FileWriter; // For file handling
import java.io.IOException; // For exception handling

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row; // "this" is used for refers to the current object in a method or constructor.
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    // Setters
    public void setRow(int row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print information of a Ticket
    public void printInfo() {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: $" + price);

        if (person != null) {
            System.out.println("Person Information:");
            System.out.println("Name: " + person.getName());
            System.out.println("Surname: " + person.getSurname());
            System.out.println("Email: " + person.getEmail());
        } else {
            System.out.println("No person information available.");
        }
    }
        // Method to save ticket information to a file
        public void save() { // Learned from Lecture slides and https://www.w3schools.com/java/java_files.asp
            String fileName = rowToSeatFileName(row, seat) + ".txt";
            try {
                FileWriter writer = new FileWriter(fileName);
                writer.write("Ticket Information:\n");
                writer.write("Row: " + row + "\n");
                writer.write("Seat: " + seat + "\n");
                writer.write("Price: $" + price + "\n");
                if (person != null) {
                    writer.write(person.toString() + "\n");
                }
                writer.close();
                System.out.println("Ticket information saved to " + fileName);
            } catch (IOException e) {
                System.out.println("An error occurred while saving the ticket information.");
                e.printStackTrace();
            }
        }

    // Helper method to generate file name from row and seat
    private String rowToSeatFileName(int row, int seat) {
        char rowChar = (char) ('A' + row);
        return rowChar + String.valueOf(seat);
    }

    // @Override learned through https://stackoverflow.com/questions/94361/when-do-you-use-javas-override-annotation-and-why
    @Override //Used to indicate that a method in a subclass is overriding a method of its superclass
    public String toString() {
        String ticketInfo = "Ticket Information:\n" +
                "Row: " + row + "\n" +
                "Seat: " + seat + "\n" +
                "Price: $" + price;
            return ticketInfo;
        }
    }
