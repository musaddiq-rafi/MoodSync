package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Authenticator authenticator = new Authenticator(userManager);
        MoodTracker moodTracker = new MoodTracker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Mood Tracking App");
        System.out.println("1. Register");
        System.out.println("2. Login");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (authenticator.register(username, password)) {
                System.out.println("Registration successful! Please log in.");
                login(scanner, authenticator, moodTracker);
            } else {
                System.out.println("Username already exists.");
            }
        } else if (choice == 2) {
            login(scanner, authenticator, moodTracker);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    private static void login(Scanner scanner, Authenticator authenticator, MoodTracker moodTracker) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (authenticator.login(username, password)) {
            System.out.println("Login successful!");
            displayInnerMenu(scanner, moodTracker);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void displayInnerMenu(Scanner scanner, MoodTracker moodTracker) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add Sleep Entry");
            System.out.println("2. Add Productivity Entry");
            System.out.println("3. Add Weather Entry");
            System.out.println("4. Add Exercise Entry");
            System.out.println("5. Add Food Entry");
            System.out.println("6. Add Screen Time Entry");
            System.out.println("7. Add Mood Entry");
            System.out.println("8. Analyze Mood");
            System.out.println("9. Display Graph");
            System.out.println("10. Exit");

            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menuChoice) {
                case 1:
                    System.out.print("Enter hours of sleep: ");
                    int hours = scanner.nextInt();
                    SleepEntry sleepEntry = new SleepEntry();
                    sleepEntry.setHours(hours);
                    moodTracker.addEntry(sleepEntry);
                    break;
//                case 2:
//                    System.out.print("Enter tasks completed: ");
//                    int tasksCompleted = scanner.nextInt();
//                    ProductivityEntry productivityEntry = new ProductivityEntry();
//                    productivityEntry.setTasksCompleted(tasksCompleted);
//                    moodTracker.addEntry(productivityEntry);
//                    break;
//                case 3:
//                    System.out.print("Enter weather condition (DULL, GLOOMY, SUNNY, RAINY, CLOUDY, WINDY, SNOWY): ");
//                    String weatherCondition = scanner.nextLine();
//                    WeatherEntry weatherEntry = new WeatherEntry();
//                    weatherEntry.setWeatherCondition(WeatherCondition.valueOf(weatherCondition.toUpperCase()));
//                    moodTracker.addEntry(weatherEntry);
//                    break;
//                case 4:
//                    System.out.print("Enter minutes of exercise: ");
//                    int minutes = scanner.nextInt();
//                    ExerciseEntry exerciseEntry = new ExerciseEntry();
//                    exerciseEntry.setMinutes(minutes);
//                    moodTracker.addEntry(exerciseEntry);
//                    break;
//                case 5:
//                    System.out.print("Enter food type: ");
//                    String foodType = scanner.nextLine();
//                    FoodEntry foodEntry = new FoodEntry();
//                    foodEntry.setFoodType(foodType);
//                    moodTracker.addEntry(foodEntry);
//                    break;
//                case 6:
//                    System.out.print("Enter screen time in minutes: ");
//                    int screenTimeMinutes = scanner.nextInt();
//                    ScreenTimeEntry screenTimeEntry = new ScreenTimeEntry();
//                    screenTimeEntry.setMinutes(screenTimeMinutes);
//                    moodTracker.addEntry(screenTimeEntry);
//                    break;
//                case 7:
//                    System.out.print("Enter mood level (HAPPY, NEUTRAL, SAD, EXTREMELY_HAPPY): ");
//                    String moodLevel = scanner.nextLine();
//                    MoodEntry moodEntry = new MoodEntry();
//                    moodEntry.setMoodLevel(MoodLevel.valueOf(moodLevel.toUpperCase()));
//                    moodTracker.addEntry(moodEntry);
//                    break;
//                case 8:
//                    moodTracker.analyzeMood();
//                    break;
//                case 9:
//                    Graph graph = new Graph();
//                    graph.displayGraph();
//                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}