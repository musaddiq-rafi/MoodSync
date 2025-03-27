package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
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
            inputDate(scanner, moodTracker);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void inputDate(Scanner scanner, MoodTracker moodTracker) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        DailyMood dailyMood = new DailyMood(date);

        System.out.println("How are you feeling today?");
        System.out.println("1. " + MoodLevel.EXTREMELY_HAPPY.getDescription());
        System.out.println("2. " + MoodLevel.HAPPY.getDescription());
        System.out.println("3. " + MoodLevel.NEUTRAL.getDescription());
        System.out.println("4. " + MoodLevel.SAD.getDescription());
        System.out.println("5. " + MoodLevel.EXTREMELY_SAD.getDescription());

        int moodChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        MoodLevel moodLevel = switch (moodChoice) {
            case 1 -> MoodLevel.EXTREMELY_HAPPY;
            case 2 -> MoodLevel.HAPPY;
            case 3 -> MoodLevel.NEUTRAL;
            case 4 -> MoodLevel.SAD;
            case 5 -> MoodLevel.EXTREMELY_SAD;
            default -> {
                System.out.println("Invalid choice. Defaulting to NEUTRAL.");
                yield MoodLevel.NEUTRAL;
            }
        };

        dailyMood.setMood(moodLevel);
        displayInnerMenu(scanner, moodTracker, dailyMood);
    }

    private static void displayInnerMenu(Scanner scanner, MoodTracker moodTracker, DailyMood dailyMood) {
        boolean exit = false;
        boolean sleepEntryDone = false;
        boolean productivityEntryDone = false;
        boolean weatherEntryDone = false;
        boolean exerciseEntryDone = false;

        while (!exit) {
            System.out.println("1. Add Sleep Entry" + (sleepEntryDone ? " ✔" : ""));
            System.out.println("2. Add Productivity Entry" + (productivityEntryDone ? " ✔" : ""));
            System.out.println("3. Add Weather Entry" + (weatherEntryDone ? " ✔" : ""));
            System.out.println("4. Add Exercise Entry" + (exerciseEntryDone ? " ✔" : ""));
            System.out.println("5. Add Food Entry");
            System.out.println("6. Add Screen Time Entry");
            System.out.println("7. Add Mood Entry");
            System.out.println("8. Analyze Mood");
            System.out.println("9. Display Graph");
            System.out.println("10. Show Saved Data");
            System.out.println("11. Exit");

            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menuChoice) {
                case 1:
                    if (!sleepEntryDone) {
                        System.out.print("Enter hours of sleep: ");
                        int hours = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Select sleep quality:");
                        System.out.println("1. " + SleepQualities.EXCELLENT.getDescription());
                        System.out.println("2. " + SleepQualities.GOOD.getDescription());
                        System.out.println("3. " + SleepQualities.AVERAGE.getDescription());
                        System.out.println("4. " + SleepQualities.POOR.getDescription());
                        System.out.println("5. " + SleepQualities.TERRIBLE.getDescription());

                        int qualityChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        SleepQualities sleepQuality = switch (qualityChoice) {
                            case 1 -> SleepQualities.EXCELLENT;
                            case 2 -> SleepQualities.GOOD;
                            case 3 -> SleepQualities.AVERAGE;
                            case 4 -> SleepQualities.POOR;
                            case 5 -> SleepQualities.TERRIBLE;
                            default -> {
                                System.out.println("Invalid choice. Defaulting to AVERAGE.");
                                yield SleepQualities.AVERAGE;
                            }
                        };

                        System.out.print("Enter what you feel about the sleep today: ");
                        String sleepMessage = scanner.nextLine();

                        SleepEntry sleepEntry = new SleepEntry();
                        sleepEntry.setHours(hours);
                        sleepEntry.setSleepQuality(sleepQuality);
                        sleepEntry.setSleepMessage(sleepMessage);
                        dailyMood.addEntry(sleepEntry);
                        sleepEntryDone = true;
                    } else {
                        System.out.println("Sleep entry already added.");
                    }
                    break;
                case 2:
                    if (!productivityEntryDone) {
                        System.out.println("Select productivity level:");
                        System.out.println("1. " + ProductivityLevel.EXTREMELY_PRODUCTIVE.getDescription());
                        System.out.println("2. " + ProductivityLevel.PRODUCTIVE.getDescription());
                        System.out.println("3. " + ProductivityLevel.NEUTRAL.getDescription());
                        System.out.println("4. " + ProductivityLevel.UNPRODUCTIVE.getDescription());
                        System.out.println("5. " + ProductivityLevel.EXTREMELY_UNPRODUCTIVE.getDescription());

                        int productivityChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        ProductivityLevel productivityLevel = switch (productivityChoice) {
                            case 1 -> ProductivityLevel.EXTREMELY_PRODUCTIVE;
                            case 2 -> ProductivityLevel.PRODUCTIVE;
                            case 3 -> ProductivityLevel.NEUTRAL;
                            case 4 -> ProductivityLevel.UNPRODUCTIVE;
                            case 5 -> ProductivityLevel.EXTREMELY_UNPRODUCTIVE;
                            default -> {
                                System.out.println("Invalid choice. Defaulting to NEUTRAL.");
                                yield ProductivityLevel.NEUTRAL;
                            }
                        };

                        System.out.print("Enter what productive tasks you have done today: ");
                        String productivityDescription = scanner.nextLine();

                        ProductivityEntry productivityEntry = new ProductivityEntry();
                        productivityEntry.setProductivityLevel(productivityLevel);
                        productivityEntry.setProductivityDescription(productivityDescription);
                        productivityEntryDone = true;
                        dailyMood.addEntry(productivityEntry);
                    } else {
                        System.out.println("Productivity entry already added.");
                    }
                    break;
                case 3:
                    if (!weatherEntryDone) {
                        System.out.println("Select weather level:");
                        System.out.println("1. " + WeatherLevel.SUNNY.getDescription());
                        System.out.println("2. " + WeatherLevel.CLOUDY.getDescription());
                        System.out.println("3. " + WeatherLevel.RAINY.getDescription());
                        System.out.println("4. " + WeatherLevel.STORMY.getDescription());
                        System.out.println("5. " + WeatherLevel.SNOWY.getDescription());

                        int weatherChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        WeatherLevel weatherLevel = switch (weatherChoice) {
                            case 1 -> WeatherLevel.SUNNY;
                            case 2 -> WeatherLevel.CLOUDY;
                            case 3 -> WeatherLevel.RAINY;
                            case 4 -> WeatherLevel.STORMY;
                            case 5 -> WeatherLevel.SNOWY;
                            default -> {
                                System.out.println("Invalid choice. Defaulting to SUNNY.");
                                yield WeatherLevel.SUNNY;
                            }
                        };

                        System.out.print("Enter weather description: ");
                        String weatherDescription = scanner.nextLine();

                        WeatherEntry weatherEntry = new WeatherEntry();
                        weatherEntry.setWeatherLevel(weatherLevel);
                        weatherEntry.setWeatherDescription(weatherDescription);
                        weatherEntryDone = true;
                        dailyMood.addEntry(weatherEntry);
                    } else {
                        System.out.println("Weather entry already added.");
                    }
                    break;
                case 4:
                    if (!exerciseEntryDone) {
                        System.out.println("Select exercise level:");
                        System.out.println("1. " + ExerciseLevel.NONE.getDescription());
                        System.out.println("2. " + ExerciseLevel.LIGHT.getDescription());
                        System.out.println("3. " + ExerciseLevel.MODERATE.getDescription());
                        System.out.println("4. " + ExerciseLevel.INTENSE.getDescription());
                        System.out.println("5. " + ExerciseLevel.EXTREME.getDescription());

                        int exerciseChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        ExerciseLevel exerciseLevel = switch (exerciseChoice) {
                            case 1 -> ExerciseLevel.NONE;
                            case 2 -> ExerciseLevel.LIGHT;
                            case 3 -> ExerciseLevel.MODERATE;
                            case 4 -> ExerciseLevel.INTENSE;
                            case 5 -> ExerciseLevel.EXTREME;
                            default -> {
                                System.out.println("Invalid choice. Defaulting to NONE.");
                                yield ExerciseLevel.NONE;
                            }
                        };

                        System.out.print("Enter exercise description: ");
                        String exerciseDescription = scanner.nextLine();

                        ExerciseEntry exerciseEntry = new ExerciseEntry();
                        exerciseEntry.setExerciseLevel(exerciseLevel);
                        exerciseEntry.setExerciseDescription(exerciseDescription);
                        exerciseEntryDone = true;
                        dailyMood.addEntry(exerciseEntry);
                    } else {
                        System.out.println("Exercise entry already added.");
                    }
                    break;
                case 5:
                    System.out.println("Select food satisfaction level:");
                    System.out.println("1. " + FoodSatisfactionLevel.EXCELLENT.getDescription());
                    System.out.println("2. " + FoodSatisfactionLevel.GOOD.getDescription());
                    System.out.println("3. " + FoodSatisfactionLevel.AVERAGE.getDescription());
                    System.out.println("4. " + FoodSatisfactionLevel.POOR.getDescription());
                    System.out.println("5. " + FoodSatisfactionLevel.TERRIBLE.getDescription());

                    int foodChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    FoodSatisfactionLevel foodSatisfactionLevel = switch (foodChoice) {
                        case 1 -> FoodSatisfactionLevel.EXCELLENT;
                        case 2 -> FoodSatisfactionLevel.GOOD;
                        case 3 -> FoodSatisfactionLevel.AVERAGE;
                        case 4 -> FoodSatisfactionLevel.POOR;
                        case 5 -> FoodSatisfactionLevel.TERRIBLE;
                        default -> {
                            System.out.println("Invalid choice. Defaulting to AVERAGE.");
                            yield FoodSatisfactionLevel.AVERAGE;
                        }
                    };

                    System.out.print("Enter food description: ");
                    String foodDescription = scanner.nextLine();

                    FoodEntry foodEntry = new FoodEntry();
                    foodEntry.setFoodSatisfactionLevel(foodSatisfactionLevel);
                    foodEntry.setFoodDescription(foodDescription);
                    dailyMood.addEntry(foodEntry);
                    break;
                // Other cases...
                case 10:
                    showSavedData();
                    break;
                case 11:
                    try {
                        saveDailyMoodToFile(dailyMood, "daily_mood_data.csv");
                    } catch (IOException e) {
                        System.out.println("Error saving daily mood: " + e.getMessage());
                    }
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void saveDailyMoodToFile(DailyMood dailyMood, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(dailyMood.getDate() + "," + dailyMood.getMood() + "\n");
            for (LogEntry entry : dailyMood.getEntries()) {
                if (entry instanceof SleepEntry) {
                    writer.write("SleepEntry," + ((SleepEntry) entry).toCSV() + "\n");
                } else if (entry instanceof ProductivityEntry) {
                    writer.write("ProductivityEntry," + ((ProductivityEntry) entry).toCSV() + "\n");
                } else if (entry instanceof WeatherEntry) {
                    writer.write("WeatherEntry," + ((WeatherEntry) entry).toCSV() + "\n");
                } else if (entry instanceof ExerciseEntry) {
                    writer.write("ExerciseEntry," + ((ExerciseEntry) entry).toCSV() + "\n");
                } else if (entry instanceof FoodEntry) {
                    writer.write("FoodEntry," + ((FoodEntry) entry).toCSV() + "\n");
                }
                // Add other entry types here...
            }
        }
    }

    private static void showSavedData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("daily_mood_data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading saved data: " + e.getMessage());
        }
    }
}