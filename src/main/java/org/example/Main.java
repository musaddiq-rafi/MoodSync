package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        Authenticator authenticator = new Authenticator(userManager);
        MoodTracker moodTracker = new MoodTracker();
        DataManager dataManager = new DataManager();

        System.out.println("Welcome to the Mood Tracking App");
        System.out.println("1. Register");
        System.out.println("2. Login");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            if (UserInteraction.register(scanner, authenticator)) {
                System.out.println("Registration successful! Please log in.");
                UserInteraction.login(scanner, authenticator, moodTracker, dataManager);
            } else {
                System.out.println("Username already exists.");
            }
        } else if (choice == 2) {
            UserInteraction.login(scanner, authenticator, moodTracker, dataManager);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
