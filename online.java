import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

class ReservationSystem {
    public void makeReservation() {
        System.out.println("Let's make your reservation!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();
        System.out.print("Enter class type: ");
        String classType = sc.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = sc.nextLine();
        System.out.print("Enter source station: ");
        String sourceStation = sc.nextLine();
        System.out.print("Enter destination station: ");
        String destinationStation = sc.nextLine();

        System.out.println("Your reservation details:");
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("Source Station: " + sourceStation);
        System.out.println("Destination Station: " + destinationStation);
        System.out.println("Reservation made successfully! Happy journey!");
    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's cancel your reservation.");
        System.out.print("Enter PNR number to cancel the reservation: ");
        String pnr = scanner.nextLine();
        boolean reservationCancelled = cancelReservationInDatabase(pnr);
        if (reservationCancelled) {
            System.out.println("Reservation with PNR " + pnr + " cancelled successfully.");
        } else {
            System.out.println("Reservation with PNR " + pnr + " not found. Please check the PNR number.");
        }
    }

    private boolean cancelReservationInDatabase(String pnr) {
        return true;
    }
}

public class online {
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User admin = new User("admin", "admin123");

        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.authenticate(username, password)) {
            currentUser = admin;
            System.out.println("Login successful! Welcome, " + username + "!");
            showMenu();
        } else {
            System.out.println("Invalid username or password. Exiting...");
        }
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.makeReservation();
                    break;
                case 2:
                    reservationSystem.cancelReservation();
                    break;
                case 3:
                    System.out.println("Logging out... See you next time!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}