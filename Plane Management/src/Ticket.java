import java.io.FileWriter;
import java.io.IOException;    // Import the IOException class to handle errors

public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(char row, int seat, Person person){
        this.row = row;
        this.seat = seat;
        this.price = calculate_ticket_price();
        this.person = person;
    }
    public char get_row(){
        return row;
    }
    public void set_row(char row){
        this.row = row;
    }
    public int get_seat(){
        return seat;
    }
    public void set_seat(int seat){
        this.seat = seat;
    }
    public double get_price(){
        return price;
    }
    public void set_price(double price){
        this.price = price;
    }

    public Person get_person() {
        return person;
    }
    public void set_person(Person person){
        this.person = person;
    }

    public double calculate_ticket_price(){
        double price;
        if (seat <6){
            price = 200.0;
        } else if (seat <10) {
            price = 150.0;
        }else {
            price = 180.0;
        }
        return price;
    }
    public void Print_Ticket_Info(){
        System.out.println("Row: "+ row);
        System.out.println("Seat "+ seat);
        System.out.println("Price "+ price);
        System.out.println("Personal Details: "+ person.toString());
    }

    public void save() {
        String File_Name = row + "" + seat+ ".txt";
        try(FileWriter writer = new FileWriter(File_Name)){
            writer.write("Ticket information: \n");
            writer.write("Row: "+row + ",Seat: "+ seat + "\n");
            writer.write("Name: "+ person.get_Name()+ " " + person.get_Surname()+"\n");
            writer.write("Email: "+ person.get_Email()+"\n");
            System.out.println("Ticket information saved to file: "+ File_Name);
        }catch (IOException e){
            System.out.println("An error has occurred while saving the ticket information to a file: "+ File_Name);
        }
    }
}

