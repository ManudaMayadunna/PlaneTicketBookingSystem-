import java.util.Scanner;

//This Code was made with the help of referring to various YouTube videos and websites
public class w2053018_PlaneManagement {
    private static Scanner input = new Scanner(System.in);/*Used a private class ,so I can use the
        "input" variable name every time I need to get a user input*/

    //Initializing a 2D array for seat plan(Jagged array)
    public static char[][] seatPlan = { //Learned from https://www.geeksforgeeks.org/jagged-array-in-java/
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
    };
    Ticket[] ticketArray = new Ticket[52];
    
    //Entry point of the program
    public static void main(String[] args) {
        w2053018_PlaneManagement planeManagement = new w2053018_PlaneManagement();
    
        int option = -1;//Set the main method to -1
    
        while (option != 0) {
            System.out.println("Welcome to the Plane Management application");
            System.out.println("""
                       ***********************************************
                       *                MENU OPTIONS                 *
                       ***********************************************
                           1) Buy a seat
                           2) Cancel a seat
                           3) Find First available seat
                           4) Show seating plan
                           5) Print tickets information and total sales
                           6) Search tickets
                           0) Quit
                       ***********************************************
                       """);
            System.out.print("Please Select an option: ");
    
            //A simple switch statement to get user inputs in the menu and do what's next
            if (input.hasNextInt()) {
                option = input.nextInt();

                // Take user input for menu option
                switch (option) {
                        case 1:
                            planeManagement.buy_Seat();
                            break;
                        case 2:
                            planeManagement.cancel_seat();
                            break;
                        case 3:
                            planeManagement.find_first_available();
                            break;
                        case 4:
                            planeManagement.show_seating_plan();
                            break;
                        case 5:
                            planeManagement.print_tickets_info();
                            break;
                        case 6:
                            planeManagement.search_ticket();
                            break;
                        case 0:
                            System.out.println("Thank you for using the Plane Management application. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid option.");
                    input.next(); // Clear the invalid input
                }
                System.out.println();
            }
        }

        // Method to calculate price based on row and seat number
        public double calculatePrice(int row, int seatNumber) {
            if ((seatNumber >= 1 && seatNumber <= 5)) {
                return 200.0; // Price for seat numbers 1 to 5 in every row letter is £200
            } else if (seatNumber >= 6 && seatNumber <= 9) {
                return 150.0; // Price for seat numbers 6 to 9 in every row letter is £150
            } else {
                return 180.0; // Default price for other seats is £180
            }
        }

        // Method to get seat number for a given row letter
        public int getSeatNumber(char rowLetter) {
            System.out.print("Enter a seat number: ");
            int seatNumber = input.nextInt();
    
            int row = -1;
            switch (rowLetter) {
                case 'A':
                    row = 0;
                    break;
                case 'B':
                    row = 1;
                    break;
                case 'C':
                    row = 2;
                    break;
                case 'D':
                    row = 3;
                    break;
                default:
                    System.out.println("Invalid Row");
                    return -1; // Return -1 for an invalid row
            }
    
            if ((rowLetter == 'A' || rowLetter == 'D') && (seatNumber > 0 && seatNumber < 15)) {
                if (seatPlan[row].length >= seatNumber) {
                    return seatNumber;
                }
            }
    
            return -1; // Return -1 if the seat number is invalid or unavailable
        }
    
        //Used to buy seats
        public void buy_Seat() {
            // Prompt user for row letter and seat number
            System.out.print("Enter a row letter: ");
            char rowLetter = input.next().toUpperCase().charAt(0);
            System.out.print("Enter a seat number: ");
            int seatNumber = input.nextInt();
    
            int row = -1;
            switch (rowLetter) {
                case 'A':
                    row = 0;
                    break;
                case 'B':
                    row = 1;
                    break;
                case 'C':
                    row = 2;
                    break;
                case 'D':
                    row = 3;
                    break;
                default:
                    System.out.println("Invalid Row");
                    return;
            }

            // Check if the seat is available and within valid range
            if ((rowLetter == 'A' || rowLetter == 'D') && (seatNumber > 0 && seatNumber < 15)) {
                if (seatPlan[row].length >= seatNumber) {
                    if (seatPlan[row][seatNumber - 1] == 'O') {
                        // Gather information about the person
                        System.out.print("Enter person's name: ");
                        String name = input.next();
                        System.out.print("Enter person's surname: ");
                        String surname = input.next();
                        System.out.print("Enter person's email: ");
                        String email = input.next();
    
                        // Create a new Person object
                        Person person = new Person(name, surname, email);
    
                        // Calculate the price based on row and seat number
                        double price = calculatePrice(row, seatNumber);
    
                        // Create a new Ticket
                        Ticket ticket = new Ticket(row, seatNumber, calculatePrice(row,seatNumber), person);
    
                        // Add the ticket to the array of Tickets
                        for (int i = 0; i < ticketArray.length; i++) {
                            if (ticketArray[i] == null) {
                                ticketArray[i] = ticket;
                                break;
                            }
                        }
                        //Save the ticket information
                        ticket.save();
    
                        // Mark seat as occupied
                        seatPlan[row][seatNumber - 1] = 'X';
                        System.out.println("Seat bought successfully!");
                    } else {
                        System.out.println("Seat is already occupied.");
                    }
                } else {
                    System.out.println("Invalid Seat Number");
                }
            } else if ((rowLetter == 'B' || rowLetter == 'C') && (seatNumber > 0 && seatNumber < 13)) {
                if (seatPlan[row].length >= seatNumber) {
                    if (seatPlan[row][seatNumber - 1] == 'O') {
                        // Gather information about the person
                        System.out.print("Enter person's name: ");
                        String name = input.next();
                        System.out.print("Enter person's surname: ");
                        String surname = input.next();
                        System.out.print("Enter person's email: ");
                        String email = input.next();
    
                        // Create a new Person object
                        Person person = new Person(name, surname, email);
    
                        // Calculate the price based on row and seat number
                        double price = calculatePrice(row, seatNumber);
    
                        // Create a new Ticket
                        Ticket ticket = new Ticket(row, seatNumber, calculatePrice(row,seatNumber), person);
    
                        // Add the ticket to the array of Tickets
                        for (int i = 0; i < ticketArray.length; i++) {
                            if (ticketArray[i] == null) {
                                ticketArray[i] = ticket;
                                break;
                            }
                        }

                        //Save the ticket information
                        ticket.save();
    
                        // Mark seat as occupied
                        seatPlan[row][seatNumber - 1] = 'X';
                        System.out.println("Seat bought successfully!");
                    } else {
                        System.out.println("Seat is already occupied.");
                    }
                } else {
                    System.out.println("Invalid Seat Number");
                }
            } else {
                System.out.println("Invalid Row or Seat Number");
            }
            System.out.println();
        }
        public void cancel_seat() {
            System.out.print("Enter a row letter: ");
            char rowLetter = input.next().toUpperCase().charAt(0);
            System.out.print("Enter a seat number: ");
            int seatNumber = input.nextInt();
    
            int row = -1;
            switch (rowLetter) {
                case 'A':
                    row = 0;
                    break;
                case 'B':
                    row = 1;
                    break;
                case 'C':
                    row = 2;
                    break;
                case 'D':
                    row = 3;
                    break;
                default:
                    System.out.println("Invalid Row");
                    return;
            }
    
            boolean foundTicket = false;
    
            for (int i = 0; i < ticketArray.length; i++) {
                if (ticketArray[i] != null && ticketArray[i].getRow() == row && ticketArray[i].getSeat() == seatNumber) {
                    ticketArray[i] = null; // Remove the ticket from the array list of tickets
                    seatPlan[row][seatNumber - 1] = 'O'; // Mark seat as available
                    System.out.println("Ticket canceled successfully!");
                    foundTicket = true;
                    break;
                }
            }
    
            if (!foundTicket) {
                System.out.println("No ticket found for the specified seat.");
            }
    
            System.out.println();
        }
    
        //Get the closest available seat
        public void find_first_available() {
            boolean foundSeat = false;// Flag to track if a seat is found

            // Loop through the seating plan array to find the first available seat
            for (int i = 0; i < seatPlan.length; i++) {
                for (int j = 0; j < seatPlan[i].length; j++) {
                    // Check if the seat is available ('O' indicates an available seat)
                    if (seatPlan[i][j] == 'O') {
                        // Calculate row letter and seat number
                        char rowLetter = (char) ('A' + i);
                        int seatNumber = j + 1;
                        // Print the location of the first available seat
                        System.out.println("The first available seat is: Row " + rowLetter + ", Seat " + seatNumber);
                        foundSeat = true;// Set foundSeat flag to true
                        break;
                    }
                }
                if (foundSeat) {
                    break;
                }
            }

            // If no available seat is found, print a message
            if (!foundSeat) {
                System.out.println("Sorry, no available seats found.");
            }
            System.out.println();
        }

        // Method to display the current seating plan
        public void show_seating_plan() {
            // Loop through the seating plan array and print each seat
            for (int i = 0; i < seatPlan.length; i++) {
                for (int j = 0; j < seatPlan[i].length; j++) {
                    System.out.print(seatPlan[i][j] + " ");// Print the seat status
                }
                System.out.println();// Move to the next row after printing all seats in a row
            }
            System.out.println();
        }
        public void print_tickets_info() {
            double totalSales = 0;
            for (Ticket ticket : ticketArray) {
                if (ticket != null) {
                    System.out.println(ticket.toString());
                    totalSales += ticket.getPrice();
                    System.out.println();
                }
            }
    
            System.out.println("Total Sales: £" + totalSales);
            System.out.println();
        }

        // Method to search for a ticket based on row letter and seat number
        public void search_ticket() {//Should have used linear search but it had multiple errors instead i used Switches and if statements
            System.out.print("Enter a row letter: ");
            char rowLetter = input.next().toUpperCase().charAt(0);
            System.out.print("Enter a seat number: ");
            int seatNumber = input.nextInt();
            System.out.println();
    
            int row = -1;
            // Determine the row index based on the row letter
            switch (rowLetter) {
                case 'A':
                    row = 0;
                    break;
                case 'B':
                    row = 1;
                    break;
                case 'C':
                    row = 2;
                    break;
                case 'D':
                    row = 3;
                    break;
                default:
                    System.out.println("Invalid Row");
                    return;
            }

            // Check if the seat number is within valid range for the given row
            if ((rowLetter == 'A' || rowLetter == 'D') && (seatNumber > 0 && seatNumber < 15)) {
                if (seatPlan[row].length >= seatNumber) {
                    boolean found = false;
                    // Iterate through ticket array to find matching ticket
                    for (Ticket ticket : ticketArray) {
                        if (ticket != null && ticket.getRow() == row && ticket.getSeat() == seatNumber) {
                            // Print ticket information
                            System.out.println(ticket.toString());
                            System.out.println();
                            System.out.println("Person Information:  ");
                            System.out.println(ticket.getPerson().toString());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("No ticket found for the specified seat.");
                    }
                } else {
                    System.out.println("Invalid Seat Number");
                }
            } else if ((rowLetter == 'B' || rowLetter == 'C') && (seatNumber > 0 && seatNumber < 13)) {
                if (seatPlan[row].length >= seatNumber) {
                    boolean found = false;
                    // Iterate through ticket array to find matching ticket
                    for (Ticket ticket : ticketArray) {
                        if (ticket != null && ticket.getRow() == row && ticket.getSeat() == seatNumber) {
                            // Print ticket information
                            System.out.println(ticket.toString());
                            System.out.println();
                            System.out.println("Person Information:  ");
                            System.out.println(ticket.getPerson().toString());
                            found = true;
                            break;
                        }
                    }
                }else {
                System.out.println("Invalid Row or Seat Number");
            }
            System.out.println();
        }
    }
}