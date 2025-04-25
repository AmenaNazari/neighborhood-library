package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class NeighborhoodLibrary {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedBooks();
        while (true) {
            showHomeScreen();
        }
    }

    private static void seedBooks() {
        books.add(new Book(1, "9780143127741", "To Kill a Mockingbird"));
        books.add(new Book(2, "9780141439600", "Pride and Prejudice"));
        books.add(new Book(3, "9780307277671", "The Kite Runner"));
        // You can add more books if you want to reach 20
    }

    private static void showHomeScreen() {
        System.out.println("\n Welcome to the Neighborhood Library");
        System.out.println("1. Show Available Books");
        System.out.println("2. Show Checked Out Books");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1": showAvailableBooks(); break;
            case "2": showCheckedOutBooks(); break;
            case "3": System.out.println("Goodbye!"); System.exit(0); break;
            default: System.out.println("Invalid option. Try again.");
        }
    }

    private static void showAvailableBooks() {
        System.out.println("\n Available Books:");
        boolean anyAvailable = false;
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println(book.toString());
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No available books.");
            return;
        }

        System.out.print("Enter book ID to check out (or X to return): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("x")) return;

        try {
            int id = Integer.parseInt(input);
            Book book = findBookById(id);
            if (book != null && !book.isCheckedOut()) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                book.checkOut(name);
                System.out.println(" Book checked out to " + name);
            } else {
                System.out.println("Book is not available.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid ID.");
        }
    }

    private static void showCheckedOutBooks() {
        System.out.println("\n Checked Out Books:");
        boolean anyCheckedOut = false;
        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.println(book.toDetailedString());
                anyCheckedOut = true;
            }
        }
        if (!anyCheckedOut) {
            System.out.println("No books are currently checked out.");
            return;
        }

        System.out.print("Enter 'C' to check in a book or 'X' to return: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("x")) return;

        if (input.equalsIgnoreCase("c")) {
            System.out.print("Enter the ID of the book to check in: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                Book book = findBookById(id);
                if (book != null && book.isCheckedOut()) {
                    book.checkIn();
                    System.out.println(" Book checked in.");
                } else {
                    System.out.println(" Invalid ID or book not checked out.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input.");
            }
        }
    }

    private static Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }
}
