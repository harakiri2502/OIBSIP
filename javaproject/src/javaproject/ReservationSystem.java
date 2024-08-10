package javaproject;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationSystem {

    private Map<Integer, String> reservations;
    private int nextId;

    public ReservationSystem() {
        reservations = new HashMap<>();
        nextId = 1;
    }

    public int makeReservation(String name) {
        int id = nextId++;
        reservations.put(id, name);
        System.out.println("Reservation made. Your ID is: " + id);
        return id;
    }

    public void cancelReservation(int id) {
        if (reservations.remove(id) != null) {
            System.out.println("Reservation canceled.");
        } else {
            System.out.println("No reservation found with ID: " + id);
        }
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations.");
        } else {
            for (Map.Entry<Integer, String> entry : reservations.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem system = new ReservationSystem();
        
        while (true) {
            System.out.println("\nReservation System Menu:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    system.makeReservation(name);
                    break;

                case 2:
                    System.out.print("Enter your reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    system.cancelReservation(id);
                    break;

                case 3:
                    system.viewReservations();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
