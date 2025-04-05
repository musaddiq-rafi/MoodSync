package org.example;

import org.example.model.user.UserManager;
import org.example.service.auth.Authenticator;
import org.example.service.mood.MoodTracker;
import org.example.service.repository.DataManager;
import org.example.ui.UserInteraction;

import java.util.Scanner;

public class Main {

    public static void printHeader() {
        // ANSI escape codes for colors
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String PURPLE = "\u001B[35m";
        final String YELLOW = "\u001B[33m";
        final String GREEN = "\u001B[32m";
        final String BLUE = "\u001B[34m";

        System.out.println(CYAN +
                " ███╗   ███╗ ██████╗  ██████╗ ██████╗     ███████╗██╗   ██╗███╗   ██╗ ██████╗ \n" +
                " ████╗ ████║██╔═══██╗██╔═══██╗██╔══██╗    ██╔════╝╚██╗ ██╔╝████╗  ██║██╔════╝ \n" +
                " ██╔████╔██║██║   ██║██║   ██║██║  ██║    ███████╗ ╚████╔╝ ██╔██╗ ██║██║      \n" +
                " ██║╚██╔╝██║██║   ██║██║   ██║██║  ██║    ╚════██║  ╚██╔╝  ██║╚██╗██║██║      \n" +
                " ██║ ╚═╝ ██║╚██████╔╝╚██████╔╝██████╔╝    ███████║   ██║   ██║ ╚████║╚██████╗ \n" +
                " ╚═╝     ╚═╝ ╚═════╝  ╚═════╝ ╚═════╝     ╚══════╝   ╚═╝   ╚═╝  ╚═══╝ ╚═════╝ \n" +
                RESET);

        System.out.println(YELLOW + "✨ Welcome to " + PURPLE + "MoodSync" + YELLOW + " ✨");
        System.out.println(GREEN + "💚 Savor the Favor of Your Mental Health 💚" + RESET);
        System.out.println(BLUE + "🌞 Mood | 😴 Sleep | 🍽️ Food | 🏃 Exercise | ☀️ Weather | 💻 Screen Time 🌙" + RESET);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        Authenticator authenticator = new Authenticator(userManager);
        MoodTracker moodTracker = new MoodTracker();
        DataManager dataManager = new DataManager();

        printHeader();

        System.out.println("1️⃣  Register");
        System.out.println("2️⃣  Login");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            if (UserInteraction.register(scanner, authenticator)) {
                System.out.println("✅ Registration successful! Please log in.");
                UserInteraction.login(scanner, authenticator, moodTracker, dataManager);
            } else {
                System.out.println("⚠️  Username already exists.");
            }
        } else if (choice == 2) {
            UserInteraction.login(scanner, authenticator, moodTracker, dataManager);
        } else {
            System.out.println("❌ Invalid choice.");
        }

        scanner.close();
    }
}
