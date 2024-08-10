package javaproject;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Create a Random object to generate random numbers
        Random random = new Random();
        
        // Define the range for the random number
        int lowerBound = 1;
        int upperBound = 100;
        
        // Generate a random number between lowerBound and upperBound
        int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        
        int guess = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + lowerBound + " and " + upperBound + ". Try to guess it!");

        // Loop until the user guesses the correct number
        while (!hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            // Read the user's guess
            guess = scanner.nextInt();
            
            // Check if the guess is correct
            if (guess < lowerBound || guess > upperBound) {
                System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ".");
            } else if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                // If the guess is correct
                System.out.println("Congratulations! You've guessed the number correctly.");
                hasGuessedCorrectly = true;
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}
