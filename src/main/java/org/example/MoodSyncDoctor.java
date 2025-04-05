package org.example;

import java.util.Scanner;

public class MoodSyncDoctor {

    public static void startSession(Scanner scanner) {
        System.out.println("\n👨‍⚕️ MOODSYNC DOCTOR");
        System.out.println("-------------------");

        System.out.println("\nWhy do you want to talk to MoodSync Doctor today?");
        String initialResponse = scanner.nextLine();

        displayMenu(scanner);
    }

    private static void displayMenu(Scanner scanner) {
        System.out.println("\nWhat are you struggling with?");
        System.out.println("1. Anxiety");
        System.out.println("2. Overthinking");
        System.out.println("3. Sadness or Depression");
        System.out.println("4. Sleep Troubles");
        System.out.println("5. Stress");
        System.out.println("6. Loneliness");
        System.out.println("7. Low Self-esteem");
        System.out.println("8. Lack of Motivation");
        System.out.println("9. Burnout");
        System.out.println("10. Relationship Issues");
        System.out.println("11. Something else");

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (Exception e) {
            choice = 11;
            scanner.nextLine(); // Clear buffer
        }

        provideSuggestions(choice, scanner);
    }

    private static void provideSuggestions(int choice, Scanner scanner) {
        System.out.println("\n💭 SUGGESTIONS:");

        switch (choice) {
            case 1 -> handleAnxiety();
            case 2 -> handleOverthinking();
            case 3 -> handleSadness();
            case 4 -> handleSleepIssues();
            case 5 -> handleStress();
            case 6 -> handleLoneliness();
            case 7 -> handleSelfEsteem();
            case 8 -> handleMotivation();
            case 9 -> handleBurnout();
            case 10 -> handleRelationshipIssues();
            default -> handleOtherIssues(scanner);
        }

        System.out.println("\nRemember, consistent self-care and tracking your mood can really help 💖");
        System.out.println("Would you like to continue using MoodSync? (y/n)");

        String response = scanner.nextLine().trim().toLowerCase();
        if (response.startsWith("y")) {
            System.out.println("Awesome! MoodSync Doctor is here anytime you need.");
        } else {
            System.out.println("Take care 🌱 You're doing better than you think.");
        }
    }

    // --- Handler methods ---
    private static void handleAnxiety() {
        System.out.println("• Try the 5-5-5 breathing technique (inhale-hold-exhale)");
        System.out.println("• Limit caffeine and sugar intake");
        System.out.println("• Take a slow walk and observe surroundings");
        System.out.println("• Do grounding: 5 things you see, 4 touch, 3 hear, 2 smell, 1 taste");
        System.out.println("• Listen to calming music or nature sounds");
    }

    private static void handleOverthinking() {
        System.out.println("• Write thoughts in a journal—get them out of your head");
        System.out.println("• Set a timer for a 'worry window'");
        System.out.println("• Redirect thoughts by engaging in an activity");
        System.out.println("• Focus on present moment (mindfulness)");
        System.out.println("• Try thought-stopping techniques: say “stop” and shift");
    }

    private static void handleSadness() {
        System.out.println("• Talk to someone you trust");
        System.out.println("• Do one small thing you used to enjoy");
        System.out.println("• Move your body – gentle stretching or walk");
        System.out.println("• Allow yourself to feel—it’s okay");
        System.out.println("• Get sunlight or fresh air, even briefly");
    }

    private static void handleSleepIssues() {
        System.out.println("• Avoid screens 1 hour before bed");
        System.out.println("• Stick to a consistent sleep schedule");
        System.out.println("• Try progressive muscle relaxation in bed");
        System.out.println("• Avoid caffeine after 2 PM");
        System.out.println("• Keep your room cool, quiet, and dark");
    }

    private static void handleStress() {
        System.out.println("• Break tasks into smaller parts");
        System.out.println("• Take short mindful breaks");
        System.out.println("• Listen to calming music");
        System.out.println("• Identify what's *in* your control");
        System.out.println("• Try box breathing (inhale 4, hold 4, exhale 4, hold 4)");
    }

    private static void handleLoneliness() {
        System.out.println("• Reach out to a friend, even just a message");
        System.out.println("• Join an online or in-person group around your interests");
        System.out.println("• Volunteer or help someone—it can lift your spirit");
        System.out.println("• Write a letter to someone you miss");
        System.out.println("• Spend time in a public space like a library or park");
    }

    private static void handleSelfEsteem() {
        System.out.println("• Write down 3 things you like about yourself");
        System.out.println("• Challenge negative self-talk: Would I say this to a friend?");
        System.out.println("• Try affirmations, even if they feel cheesy");
        System.out.println("• Reflect on things you’ve overcome");
        System.out.println("• Dress in something that makes you feel confident");
    }

    private static void handleMotivation() {
        System.out.println("• Set a tiny goal and accomplish it (e.g., make your bed)");
        System.out.println("• Break bigger goals into smaller steps");
        System.out.println("• Create a playlist that energizes you");
        System.out.println("• Visualize your future self and why it matters");
        System.out.println("• Reward yourself after tasks—even small ones");
    }

    private static void handleBurnout() {
        System.out.println("• Take a day or hour off—recharge guilt-free");
        System.out.println("• Reflect on what’s draining you");
        System.out.println("• Say 'no' more often to protect your energy");
        System.out.println("• Schedule joy: hobbies, nature, quiet time");
        System.out.println("• Reconnect to your 'why' behind what you do");
    }

    private static void handleRelationshipIssues() {
        System.out.println("• Communicate your feelings honestly and calmly");
        System.out.println("• Listen actively—really try to understand their perspective");
        System.out.println("• Set healthy boundaries where needed");
        System.out.println("• Take a break if emotions are too high");
        System.out.println("• Reflect: is the relationship building or draining you?");
    }

    private static void handleOtherIssues(Scanner scanner) {
        System.out.println("Please describe what you're experiencing:");
        String description = scanner.nextLine();

        System.out.println("\nThank you for sharing. General self-care suggestions:");
        System.out.println("• Practice journaling or voice-noting your thoughts");
        System.out.println("• Take a break, physically and mentally");
        System.out.println("• Create something – draw, write, sing");
        System.out.println("• Meditate or simply breathe deeply for 2 minutes");
        System.out.println("• Remember: seeking help is a strength, not a weakness");
    }
}
