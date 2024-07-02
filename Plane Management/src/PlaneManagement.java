// I declare that my work contains no examples of misconduct, such as plagiarism, or collusion
// Any code taken from other sources is referenced within my code solution.
// student id - 20232217(IIT)      20534895(UOW)
// date - 18/03/2024

import java.util.Scanner;

public class PlaneManagement {
    private static final Ticket[] tickets = new Ticket[52];
    private static int Num_of_tickets_sold = 0;

    private static final int[][] seats;   // Declaration of the seats
    // Static to initialize the seats array
    static {
        seats = new int[4][];
        seats[0] = new int[14];  // Row A - 14 seats
        seats[1] = new int[12];  // Row B - 12 seats
        seats[2] = new int[12];  // Row C - 12 seats
        seats[3] = new int[14];  // Row D - 14 seats
    }

    public static void main(String[] args) {
        // Display the welcome message
        System.out.println("'Welcome to the Plane Management Application'");
        Scanner choice = new Scanner(System.in);
        int user_option;   // Declaring user menu option variable
        // Displaying the menu options
        do {
            System.out.println("***********************************************");
            System.out.println("*                    MENU                     *");
            System.out.println("***********************************************");
            System.out.println("""
                          1) Buy a seat\s
                          2) Cancel a seat\s
                          3) Find first available seat\s
                          4) Show seating plan\s
                          5) Print ticket information and total sales\s
                          6) Search ticket\s
                          0) Quit\s
                    """);
            System.out.println("***********************************************");

            System.out.print("Please select an option: ");
            user_option = choice.nextInt();  // Getting the user for the menu option

            switch (user_option) {
                case 1:
                    buy_seat(seats);
                    break;
                case 2:
                    cancel_seat(seats);
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan(seats);
                    break;
                case 5:
                    print_tickets_info(Num_of_tickets_sold);
                    break;
                case 6:
                    search_ticket(Num_of_tickets_sold);
                    break;
                case 0:
                    quit_programme();
                    break;
                default:
                    System.out.println("Invalid Option. Please try again");
                    break;
            }
        } while (user_option != 0); //Continue till the user chooses the quit option
    }

    // ------------------------ Creating a method for user option 1 --------------------------------------------------------
    public static void buy_seat(int[][] seats) {
        Scanner seat_choice = new Scanner(System.in);

        char seat_row;   // Declaring the seat row variable the user wants to buy
        int seat_number;  // Declaring the seat number variable the user wants to buy

        do {
            System.out.println("Enter your choice for seat row (A - D): ");
            seat_row = seat_choice.next().toUpperCase().charAt(0);

            if (seat_row < 'A' || seat_row > 'D') {
                System.out.println("Please enter a valid row letter.");
            }
        } while (seat_row < 'A' || seat_row > 'D');

        if (seat_row == 'A' || seat_row == 'D') {
            do {
                System.out.println("Enter your choice for a seat (1 - 14):  ");
                seat_number = seat_choice.nextInt();   // Asking the user to enter the seat number
                if (seat_number < 1 || seat_number > 14) {   // Checking if the entered seat number is in range
                    System.out.println("Please enter a valid seat number.");
                } else if (seats[seat_row - 'A'][seat_number - 1] != 0) {   // Checking if the chosen seat is already booked
                    System.out.println("Seat" + " " + seat_number + "in row " + seat_row + " is already booked.Please try again");
                    seat_number = -1;
                }
            } while (seat_number < 1 || seat_number > 14);

        } else {
            do {
                System.out.println("Enter your choice for a seat (1 - 12):  ");
                seat_number = seat_choice.nextInt();    // Asking the user to enter the seat number
                if (seat_number < 1 || seat_number > 12) {   // Checking if the entered seat number is in range
                    System.out.println("Please enter a valid seat number.");
                } else if (seats[seat_row - 'A'][seat_number - 1] != 0) {   // Checking if the chosen seat is already booked
                    seat_number = -1;
                }
            } while (seat_number < 1 || seat_number > 12);
        }

        Scanner detail = new Scanner(System.in);
        // Asking the user for personal information
        System.out.print("Enter your first name: ");
        String name = detail.next();
        System.out.print("Enter your surname: ");
        String surname = detail.next();
        System.out.print("Enter your email address: ");
        String email = detail.next();

        Person user = new Person(name, surname, email);

        seats[seat_row - 'A'][seat_number - 1] = 1;  // Marking the seat as booked
        System.out.println("Your seat in Row " + seat_row + " seat " + seat_number + " is successfully bought!!");

        if (Num_of_tickets_sold <= 52) {
            var ticket = new Ticket(seat_row, seat_number, user);
            tickets[Num_of_tickets_sold++] = ticket;
            ticket.save();
        } else System.out.println("Sorry, all the tickets are sold out");
    }

    // ----------------------------- Creating a method for user option 2 ---------------------------------------------------
    public static void cancel_seat(int[][] seats) {
        Scanner seat_choice = new Scanner(System.in);
        char seat_row;    // Declaring the seat row variable the user wants to cancel
        int seat_number;  // Declaring the seat number variable the user wants to cancel

        do {
            System.out.println("Enter your choice of seat row for cancellation (A - D):   ");
            seat_row = seat_choice.next().toUpperCase().charAt(0);
        } while (seat_row < 'A' || seat_row > 'D');

        int index_seat_row = seat_row - 'A';

        if (seat_row == 'A' || seat_row == 'D') {
            do {
                System.out.println("Enter your seat number for cancellation (1 - 14):  ");
                seat_number = seat_choice.nextInt();
                if (seat_number < 1 || seat_number > 14) {
                    System.out.println("Please enter a valid seat number.");
                }
            } while (seat_number < 1 || seat_number > 14);
        } else {
            do {
                System.out.println("Enter your seat number for cancellation (1 - 12):  ");
                seat_number = seat_choice.nextInt();
                if (seat_number < 1 || seat_number > 12) {
                    System.out.println("Please enter a valid seat number.");
                }
            } while (seat_number < 1 || seat_number > 12);
        }
        if (seats[index_seat_row][seat_number - 1] == 0) {
            System.out.println("No need to cancel.The seat is already available");
        } else {
            seats[index_seat_row][seat_number - 1] = 0;
            System.out.println("Seat cancelled successfully");

            // Removing the ticket from the array of tickets
            for (int i = 0; i < Num_of_tickets_sold; i++) {
                if (tickets[i].get_row() == seat_row && tickets[i].get_seat() == seat_number) {
                    for (int j = i; j < Num_of_tickets_sold - 1; j++) {
                        tickets[j] = tickets[j + 1];
                    }
                    Num_of_tickets_sold--;       // Decreasing the number of tickets sold
                    break;
                }
            }
        }
    }

// ----------------------------- Creating a method for user option 3 ---------------------------------------------------

    public static void find_first_available() {
        for (int row = 0; row < seats.length; row++) {    // Loops through each row
            for (int seat = 0; seat < seats[row].length; seat++) {  // Loops through each seat
                if (seats[row][seat] == 0) {   // Checking if the current seat is available
                    char rowLetter = (char) ('A' + row);  // Calculates the row letter with an available seat
                    System.out.println("The first available seat is: Row-" + rowLetter + " " + "Seat- " + (seat + 1));
                    break;
                }
            }
        }
    }

// --------------------------------- Creating a method for user option 4 -----------------------------------------------

    public static void show_seating_plan(int[][] seats) {
        System.out.println("Seating Plan:");
        System.out.println("  1 2 3 4 5 6 7 8 9 10 11 12 13 14");

        char[] rowLetters = {'A', 'B', 'C', 'D'};  // Initializing an array that contains the row letters

        for (int seat_row = 0; seat_row < 4; seat_row++) {
            System.out.print(rowLetters[seat_row] + ":");
            for (int seat = 0; seat < seats[seat_row].length; seat++) {
                if (seat > 9)
                    System.out.print(" "); // to add space to matching the seat numbering
                if (seats[seat_row][seat] == 0) // Checking if the current seat is available
                    System.out.print("O ");  // Prints O if the seat is available
                else System.out.print("X ");  // Prints X if the seat is already booked
            }
            System.out.println();
        }
    }

// ------------------------------ Creating a method for user option 5 --------------------------------------------------
    // Printing the information of all sold tickets along with total sales

    public static void print_tickets_info(int Num_of_tickets_sold) {

        double TotalSales = 0.0;  // Initializing a variable to count total of sales

        System.out.println("Ticket Information: ");
        for (int i = 0; i < Num_of_tickets_sold; i++) {  // Iterates through each sold ticket in the array
            Ticket ticket = tickets[i];
            System.out.println("Ticket " + (i + 1) + ":");
            ticket.Print_Ticket_Info(); // Calling the method that is used in the class Ticket
            TotalSales += ticket.get_price();  // Retrieves the get_price method that is used in the class Ticket
        }
        System.out.println("Total Sales: " + TotalSales);
    }

    // -------------------------- Creating a method for user option 6 ------------------------------------------------------
    public static void search_ticket(int Num_of_tickets_sold) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the row letter(A - D): ");
        char rowLetter = input.next().toUpperCase().charAt(0);

        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter.");
            return;
        }

        int seatNumber;
        if (rowLetter == 'A' || rowLetter == 'D') {
            System.out.println("Enter the seat number(1-14): ");
            seatNumber = input.nextInt();
            if (seatNumber < 1 || seatNumber > 14) {
                System.out.println("Invalid seat number.");
                return;
            }
        } else {
            System.out.println("Enter the seat number(1 - 12): ");
            seatNumber = input.nextInt();
            if (seatNumber < 1 || seatNumber > 12) {
                System.out.println("Invalid seat number.");
                return;
            }
        }

        boolean ticket_found = false;
        for (int i = 0; i < Num_of_tickets_sold; i++){
            Ticket ticket = tickets[i];
            if (ticket.get_row() == rowLetter && ticket.get_seat() == seatNumber){
                ticket.Print_Ticket_Info();
                ticket_found = true;
                break;
            }
        }
        if (!ticket_found){
            System.out.println("This seat is available");
        }
    }
    // -------------------------------- Creating a method for user option 0 ------------------------------------------------
    public static void quit_programme(){
        System.out.println("Thank you for using our application.");
        System.exit(0);
    }
}

// References
// 1 - Java arrays - https://www.w3schools.com/java/java_arrays.asp
// 2 - Java method parameters - https://www.w3schools.com/java/java_methods_param.asp
// 3 - Java recursion - https://www.w3schools.com/java/java_recursion.asp
// 4 - Java Modifiers - https://www.geeksforgeeks.org/access-modifiers-java/ , https://www.programiz.com/java-programming/access-modifiers
// 5 - Java file handling - https://www.w3schools.com/java/java_files.asp
// 6 - Java encapsulation - https://www.w3schools.com/java/java_encapsulation.asp