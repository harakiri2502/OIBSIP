package javaproject;

import java.util.*;

public class OnlineExamPortal {

    // In-memory storage for users and exam data
    private static final Map<String, User> users = new HashMap<>();
    private static User loggedInUser = null;
    
    public static void main(String[] args) {
        // Preload some users (username: password)
        users.put("student1", new User("student1", "password123", "John Doe", "student1@example.com"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nOnline Exam Portal");
            if (loggedInUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("1. Update Profile and Password");
                System.out.println("2. Take Exam");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        updateProfile(scanner);
                        break;
                    case 2:
                        takeExam(scanner);
                        break;
                    case 3:
                        loggedInUser = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }

        scanner.close();
        System.out.println("Exiting the portal.");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void updateProfile(Scanner scanner) {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to update your profile.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            loggedInUser.setName(name);
        }

        System.out.print("Enter new email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            loggedInUser.setEmail(email);
        }

        System.out.print("Enter new password (leave blank to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.trim().isEmpty()) {
            loggedInUser.setPassword(newPassword);
        }

        System.out.println("Profile updated successfully.");
    }

    private static void takeExam(Scanner scanner) {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to take the exam.");
            return;
        }

        // Sample questions and options
        List<Question> questions = Arrays.asList(
            new Question("What is the capital of France?", "A. Paris", "B. London", "C. Rome", "D. Berlin", 'A'),
            new Question("What is 2 + 2?", "A. 3", "B. 4", "C. 5", "D. 6", 'B')
        );

        int score = 0;

        for (Question q : questions) {
            System.out.println(q.getQuestion());
            System.out.println(q.getOptionA());
            System.out.println(q.getOptionB());
            System.out.println(q.getOptionC());
            System.out.println(q.getOptionD());

            System.out.print("Enter your answer (A, B, C, D): ");
            char answer = scanner.nextLine().toUpperCase().charAt(0);

            if (answer == q.getCorrectAnswer()) {
                score++;
            }
        }

        System.out.println("Exam finished. Your score: " + score + "/" + questions.size());
    }

    // User class to store user information
    private static class User {
        private String username;
        private String password;
        private String name;
        private String email;

        public User(String username, String password, String name, String email) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    // Question class to store questions and options
    private static class Question {
        private String question;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private char correctAnswer;

        public Question(String question, String optionA, String optionB, String optionC, String optionD, char correctAnswer) {
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() { return question; }
        public String getOptionA() { return optionA; }
        public String getOptionB() { return optionB; }
        public String getOptionC() { return optionC; }
        public String getOptionD() { return optionD; }
        public char getCorrectAnswer() { return correctAnswer; }
    }
}
