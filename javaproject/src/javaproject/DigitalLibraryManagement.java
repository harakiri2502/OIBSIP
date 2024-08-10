package javaproject;

import java.util.*;

public class DigitalLibraryManagement {

    // In-memory storage for books and borrowed books
    private static final Map<String, Book> books = new HashMap<>();
    private static final Set<String> borrowedBooks = new HashSet<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Preload some books
        books.put("978-0134685991", new Book("978-0134685991", "Effective Java", "Joshua Bloch"));
        books.put("978-0596009205", new Book("978-0596009205", "Head First Java", "Kathy Sierra and Bert Bates"));

        while (running) {
            System.out.println("\nDigital Library Management System");
            System.out.println("1. View Books");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewBooks();
                    break;

                case 2:
                    addBook(scanner);
                    break;

                case 3:
                    borrowBook(scanner);
                    break;

                case 4:
                    returnBook(scanner);
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nAvailable Books:");
        for (Book book : books.values()) {
            String status = borrowedBooks.contains(book.getIsbn()) ? "Borrowed" : "Available";
            System.out.printf("ISBN: %s, Title: %s, Author: %s, Status: %s%n",
                    book.getIsbn(), book.getTitle(), book.getAuthor(), status);
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        if (books.containsKey(isbn)) {
            System.out.println("A book with this ISBN already exists.");
        } else {
            books.put(isbn, new Book(isbn, title, author));
            System.out.println("Book added successfully.");
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();

        if (books.containsKey(isbn)) {
            if (borrowedBooks.contains(isbn)) {
                System.out.println("This book is already borrowed.");
            } else {
                borrowedBooks.add(isbn);
                System.out.println("Book borrowed successfully.");
            }
        } else {
            System.out.println("No book found with this ISBN.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        if (borrowedBooks.contains(isbn)) {
            borrowedBooks.remove(isbn);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("This book was not borrowed or does not exist.");
        }
    }

    // Book class to store book details
    private static class Book {
        private String isbn;
        private String title;
        private String author;

        public Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
        }

        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
    }
}
