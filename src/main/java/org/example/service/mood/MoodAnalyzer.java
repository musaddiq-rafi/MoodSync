package org.example.service.mood;

import org.example.service.repository.DataManager;
import org.example.model.mood.DailyMood;
import org.example.model.mood.LogEntry;
import org.example.model.mood.entry.*;
import org.example.model.mood.level.MoodLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class MoodAnalyzer {

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String PURPLE = "\u001B[35m";

    public static void analyzeMood(DailyMood currentMood, DataManager dataManager) {
        String date = currentMood.getDate();
        analyzeSpecificDate(currentMood, date, dataManager);
    }

    public static void analyzeSpecificDate(DailyMood mood, String date, DataManager dataManager) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            String dayName = parsedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

            // Header for mood analysis with color
            System.out.println(GREEN + "╔══════════════════════════════════════════╗");
            System.out.println("║     " + CYAN + "MOOD ANALYSIS: " + date + " (" + dayName + ")" + GREEN + "  ║" + RESET);
            System.out.println(GREEN + "╚══════════════════════════════════════════╝" + RESET);

            // Current mood status with color
            System.out.println("\n" + PURPLE + "📊 MOOD STATUS: " + RESET + mood.getMood().getDescription());

            // Analyze all entry types
            analyzeEntries(mood);

            // Provide personalized recommendations
            provideRecommendations(mood);

        } catch (DateTimeParseException e) {
            System.out.println(RED + "Error: Invalid date format. Please use the format YYYY-MM-DD." + RESET);
        } catch (Exception e) {
            System.out.println(RED + "Error analyzing mood for date: " + date + RESET);
            e.printStackTrace();
        }
    }

    private static void analyzeEntries(DailyMood mood) {
        System.out.println("\n" + CYAN + "📝 DAILY ENTRIES ANALYSIS:" + RESET);

        // Sleep analysis
        if (mood.isHasSleepEntry()) {
            SleepEntry entry = findEntry(mood, SleepEntry.class);
            assert entry != null;
            System.out.println(GREEN + "🛌 Sleep: " + RESET + entry.getSleepQuality().getDescription());
            System.out.println(" \uD83D\uDCDD  " + YELLOW + "What you wrote about your sleep that day : " + entry.getSleepMessage() + RESET);
            System.out.println(" \uD83E\uDD13☝\uFE0F Remember, good sleep is the key to maintain good mood ");
            System.out.println(CYAN + "ANALYZED INSIGHT:" + RESET);

            switch (entry.getSleepQuality()) {
                case EXCELLENT -> {
                    System.out.println("   🌟 Great sleep! Keep your routine consistent.");
                    System.out.println("   Consistent good sleep can improve your overall mood and energy levels.");
                    System.out.println("   Keep maintaining a regular sleep schedule.");
                }
                case GOOD -> {
                    System.out.println("   😊 Good sleep boosts your mood. Stay consistent.");
                    System.out.println("   Try to maintain a regular sleep schedule to continue feeling good.");
                    System.out.println("   Consider keeping a sleep diary to track patterns.");
                }
                case AVERAGE -> {
                    System.out.println("   😐 Consider reducing screen time before bed.");
                    System.out.println("   Average sleep might be a sign to improve your bedtime routine.");
                    System.out.println("   Try to create a relaxing pre-sleep routine.");
                }
                case POOR -> {
                    System.out.println("   😟 Try relaxing activities before sleep like reading.");
                    System.out.println("   Poor sleep can affect your mood. Consider evaluating your sleep environment.");
                    System.out.println("   Avoid caffeine and heavy meals before bedtime.");
                }
                case TERRIBLE -> {
                    System.out.println("   ⚠️ Poor sleep may be affecting your mood. Reflect on your nighttime habits.");
                    System.out.println("   Consistently terrible sleep might require consulting a sleep specialist.");
                    System.out.println("   Consider seeking professional help if sleep issues persist.");
                }
            }
        } else {
            System.out.println("🛌 Sleep: No data");
        }

        // Exercise analysis
        if (mood.isHasExerciseEntry()) {
            ExerciseEntry entry = findEntry(mood, ExerciseEntry.class);
            assert entry != null;
            System.out.println(GREEN + "🏃 Exercise: " + RESET + entry.getExerciseLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  " + YELLOW + "What you wrote about your exercise that day : " + entry.getExerciseDescription() + RESET);
            System.out.println(" \uD83E\uDD13☝\uFE0F Remember, exercise is a great way to boost your mood ");

            System.out.println(CYAN + "ANALYZED INSIGHT:" + RESET);
            switch (entry.getExerciseLevel()) {
                case NONE -> {
                    System.out.println("   🧘 Consider light stretching or a walk to get started.");
                    System.out.println("   Starting with small exercises can gradually build a healthy habit.");
                    System.out.println("   Try to incorporate some form of movement into your day.");

                }
                case LIGHT -> {
                    System.out.println("   👍 Light exercise is a good foundation. Keep it up!");
                    System.out.println("   Light exercises can help maintain your physical and mental health.");
                    System.out.println("   Try to gradually increase the intensity of your workouts.");
                }
                case MODERATE -> {
                    System.out.println("   💪 You're maintaining a healthy level of activity.");
                    System.out.println("   Moderate exercise is great for overall well-being. Keep going!");
                    System.out.println("   Consider mixing in different types of workouts for variety.");
                }
                case INTENSE -> {
                    System.out.println("   🔥 Impressive! Don’t forget to rest and hydrate.");
                    System.out.println("   Intense exercise requires proper rest and nutrition to avoid burnout.");
                    System.out.println("   Ensure you're balancing intense workouts with recovery days.");
                }
                case EXTREME -> {
                    System.out.println("   🚀 Extreme training—make sure you're not overexerting.");
                    System.out.println("   Extreme exercise can be beneficial but ensure you're not overdoing it.");
                    System.out.println("   Listen to your body and consider consulting a trainer for guidance.");
                }
            }
        } else {
            System.out.println("🏃 Exercise: No data");
        }

        // Food analysis
        if (mood.isHasFoodEntry()) {
            FoodEntry entry = findEntry(mood, FoodEntry.class);
            assert entry != null;
            System.out.println(GREEN + "🍽️ Food: " + RESET + entry.getFoodSatisfactionLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  " + YELLOW + "What you wrote about your food that day : " + entry.getFoodDescription() + RESET);
            System.out.println(" \uD83E\uDD13☝\uFE0F Remember, food is fuel for your body and mind ");

            System.out.println(CYAN + "ANALYZED INSIGHT:" + RESET);
            switch (entry.getFoodSatisfactionLevel()) {
                case EXCELLENT -> {
                    System.out.println("   🥗 You're fueling your body well. Keep choosing nourishing meals.");
                    System.out.println("   Excellent food choices contribute to better health and mood.");
                    System.out.println("   Consider sharing your favorite recipes with friends.");
                }
                case GOOD -> {
                    System.out.println("   😊 Good meals contribute to positive energy and mood.");
                    System.out.println("   Maintaining good eating habits can enhance your overall well-being.");
                    System.out.println("   Try to include a variety of foods for balanced nutrition.");
                }
                case AVERAGE -> {
                    System.out.println("   🍱 Consider adding more variety or nutrients.");
                    System.out.println("   Average food satisfaction might indicate a need for more balanced meals.");
                    System.out.println("   Experiment with new recipes to make meals more enjoyable.");
                }
                case POOR -> {
                    System.out.println("   😞 Unfulfilling meals may be affecting your energy levels.");
                    System.out.println("   Poor food choices can lead to low energy and mood swings.");
                    System.out.println("   Try to plan meals ahead of time to ensure better choices.");
                }
                case TERRIBLE -> {
                    System.out.println("   🚫 Try to prioritize a balanced meal, even if you're busy.");
                    System.out.println("   Terrible food satisfaction can significantly impact your health.");
                    System.out.println("   Consider meal prepping or seeking help from a nutritionist.");
                }
            }
        } else {
            System.out.println("🍽️ Food: No data");
        }


        // Weather analysis
        if (mood.isHasWeatherEntry()) {
            SleepEntry.WeatherEntry entry = findEntry(mood, SleepEntry.WeatherEntry.class);
            assert entry != null;
            System.out.println(GREEN + "☁️ Weather: " + RESET + entry.getWeatherLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  " + YELLOW + "What you wrote about your weather that day : " + entry.getWeatherDescription() + RESET);
            System.out.println(" \uD83E\uDD13☝\uFE0F Remember, weather can affect your mood and energy levels ");

            System.out.println(CYAN + "ANALYZED INSIGHT:" + RESET);
            switch (entry.getWeatherLevel()) {
                case SUNNY -> {
                    System.out.println("   ☀️ Great day for outdoor activities!");
                    System.out.println("   Sunny weather can boost your mood and energy levels.");
                    System.out.println("   Consider spending time outside to soak up the sun.");
                }
                case CLOUDY -> {
                    System.out.println("   🌥️ Cloudy days can feel slow—try brightening your space.");
                    System.out.println("   Cloudy weather might make you feel sluggish. Keep your environment bright.");
                    System.out.println("   Consider engaging in indoor activities that uplift your mood.");
                }
                case RAINY -> {
                    System.out.println("   🌧️ Rainy mood? Maybe enjoy something cozy indoors.");
                    System.out.println("   Rainy days can be perfect for indoor relaxation and self-care.");
                    System.out.println("   Consider reading a book or watching a movie to lift your spirits.");
                }
                case STORMY -> {
                    System.out.println("   ⛈️ Stormy weather—stay safe and practice self-care.");
                    System.out.println("   Stormy weather can be stressful. Focus on indoor activities.");
                    System.out.println("   Consider using this time for self-reflection or relaxation.");
                }
                case SNOWY -> {
                    System.out.println("   ❄️ Cold and snowy—warm drinks and comfort go a long way.");
                    System.out.println("   Snowy weather can be cozy. Enjoy warm drinks and indoor activities.");
                    System.out.println("   Consider bundling up and enjoying a walk in the snow.");
                }
            }
        } else {
            System.out.println("☁️ Weather: No data");
        }

        // Productivity analysis
        if (mood.isHasProductivityEntry()) {
            ProductivityEntry entry = findEntry(mood, ProductivityEntry.class);
            assert entry != null;
            System.out.println(GREEN + "📈 Productivity: " + RESET + entry.getProductivityLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  " + YELLOW + "What you wrote about your productivity that day : " + entry.getProductivityDescription() + RESET);
            System.out.println(" \uD83E\uDD13☝\uFE0F Remember, productivity affects your mood ");

            System.out.println(CYAN + "ANALYZED INSIGHT:" + RESET);
            switch (entry.getProductivityLevel()) {
                case EXTREMELY_PRODUCTIVE -> {
                    System.out.println("   🚀 You crushed it today! Celebrate your wins.");
                    System.out.println("   High productivity can lead to a great sense of accomplishment.");
                    System.out.println("   Consider sharing your achievements with others.");
                }
                case PRODUCTIVE -> {
                    System.out.println("   💪 Solid day! Keep building that momentum.");
                    System.out.println("   Maintaining productivity can help achieve your goals.");
                    System.out.println("   Consider setting new goals to keep the momentum going.");
                }
                case NEUTRAL -> {
                    System.out.println("   😐 Maybe a small goal tomorrow can spark motivation.");
                    System.out.println("   Neutral productivity might need a small push for better results.");
                    System.out.println("   Consider breaking tasks into smaller, manageable parts.");
                }
                case UNPRODUCTIVE -> {
                    System.out.println("   😓 Everyone has off-days. Try a small win tomorrow.");
                    System.out.println("   Unproductive days are normal. Focus on small achievements.");
                    System.out.println("   Consider reflecting on what might have caused the unproductivity.");
                }
                case EXTREMELY_UNPRODUCTIVE -> {
                    System.out.println("   😴 Reflect on what slowed you down. Rest is productive too.");
                    System.out.println("   Extremely unproductive days might need rest and reflection.");
                    System.out.println("   Consider taking a break and returning with a fresh perspective.");
                }
            }
        } else {
            System.out.println("📈 Productivity: No data");
        }

        // Screen time analysis
        if (mood.isHasScreenTimeEntry()) {
            ScreenTimeEntry entry = findEntry(mood, ScreenTimeEntry.class);
            assert entry != null;
            System.out.println(GREEN + "📱 Screen Time: " + RESET + entry.getScreenTimeLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  " + YELLOW + "What you wrote about your screen time that day : " + entry.getScreenTimeDescription() + RESET);

            switch (entry.getScreenTimeLevel()) {
                case NONE -> System.out.println("   👏 Great job staying off screens!");
                case LOW -> System.out.println("   👍 Balanced screen use is good for mental clarity.");
                case MODERATE -> System.out.println("   ⚖️ Keep an eye on usage, especially before bed.");
                case HIGH -> System.out.println("   📉 Consider taking screen breaks during the day.");
                case EXCESSIVE -> System.out.println("   ⚠️ High screen time can affect mood and sleep. Try unplugging a bit.");
            }
        } else {
            System.out.println("📱 Screen Time: No data");
        }
    }

    private static void provideRecommendations(DailyMood mood) {
        System.out.println("\n" + GREEN + "╔══════════════════════════════════════════╗");
        System.out.println("        💡" + CYAN + "OVERALL MOOD RECOMMENDATIONS:" + RESET);
        System.out.println("╚══════════════════════════════════════════╝");

        MoodLevel currentMood = mood.getMood();
        System.out.println("\n" + CYAN + "Your overall mood is: " + RESET + currentMood.getDescription());

        // Providing recommendations based on mood level
        switch (currentMood) {
            case EXTREMELY_HAPPY:
                System.out.println("• " + GREEN + "Celebrate your success and spread positivity!" + RESET);
                System.out.println("• " + GREEN + "Keep doing what’s working for you!" + RESET);
                System.out.println("• " + GREEN + "Take time to reflect and express gratitude" + RESET);
                break;

            case HAPPY:
                System.out.println("• " + GREEN + "Great job! Keep up the good work!" + RESET);
                System.out.println("• " + GREEN + "Share your positive energy with others" + RESET);
                System.out.println("• " + GREEN + "Consider maintaining your routine and try to improve further" + RESET);
                break;

            case NEUTRAL:
                System.out.println("• " + YELLOW + "Try adding a new activity to your routine today" + RESET);
                System.out.println("• " + YELLOW + "Connect with a friend or family member" + RESET);
                System.out.println("• " + YELLOW + "Consider limiting screen time before bed" + RESET);
                break;

            case SAD:
                System.out.println("• " + RED + "Consider talking to someone about how you're feeling" + RESET);
                System.out.println("• " + RED + "Try a short walk outside to clear your mind" + RESET);
                System.out.println("• " + RED + "Practice mindfulness or deep breathing for 5 minutes" + RESET);
                break;

            case EXTREMELY_SAD:
                System.out.println("• " + RED + "It might be helpful to reach out to a mental health professional" + RESET);
                System.out.println("• " + RED + "Take some time for self-care, perhaps a walk or a break" + RESET);
                System.out.println("• " + RED + "Focus on small, manageable steps to improve your mood" + RESET);
                break;

            default:
                System.out.println("• " + YELLOW + "Consider evaluating what can improve your day" + RESET);
                break;
        }
    }


    @SuppressWarnings("unchecked")
    private static <T extends LogEntry> T findEntry(DailyMood mood, Class<T> type) {
        for (LogEntry entry : mood.getEntries()) {
            if (type.isInstance(entry)) {
                return (T) entry;
            }
        }
        return null;
    }
}
