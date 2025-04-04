package org.example;

import java.util.Scanner;

public class UserInteraction {
    public static boolean register(Scanner scanner, Authenticator authenticator) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return authenticator.register(username, password);
    }

    public static void login(Scanner scanner, Authenticator authenticator, MoodTracker moodTracker, DataManager dataManager) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authenticator.login(username, password)) {
            System.out.println("Login successful!");
            MoodInteraction.inputDate(scanner, moodTracker, dataManager);
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
