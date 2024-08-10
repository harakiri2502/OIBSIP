package javaproject;

import java.util.Scanner;

public class ATMInterface {

    private static double balance = 0.0;  // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nATM Interface");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Please choose an option (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;

                case 2:
                    depositMoney(scanner);
                    break;

                case 3:
                    withdrawMoney(scanner);
                    break;

                case 4:
                    System.out.println("Exiting... Thank you for using the ATM.");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 4.");
            }
        }

        scanner.close();
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            balance += amount;
            System.out.printf("You have deposited: $%.2f%n", amount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.printf("You have withdrawn: $%.2f%n", amount);
            } else {
                System.out.println("Insufficient funds. Please enter a smaller amount.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
        }
    }
}
