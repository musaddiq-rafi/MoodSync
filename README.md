# MoodSync – Log. Learn. Heal.
### Happiness tracker and mental health analyzer



---

## Overview

**MoodSync** is a comprehensive mental health companion application designed to help you track and analyze your daily mood and activities. Log various aspects of your day—including sleep quality, productivity, weather, exercise, food satisfaction, and screen time—and receive personalized insights and suggestions to enhance your well-being.

---

## ✨ Features


- 📊 **Mood Tracking**: Daily logs of your mood and activities.
- 📝 **Activity Logging**: Record sleep, productivity, weather, exercise, food satisfaction, and screen time.
- 📂 **File System**: Data is stored in a structured file system for easy access and management.
- 🔍 **Data Analysis**: Detailed insights with actionable suggestions.
- 📈 **Visualization**: Display and explore data in an easy-to-read table.
- 🩺 **MoodSync Doctor**: Expert suggestions and mental health tips.


---

## 💻 Clean Code Practices

To ensure maintainability and readability, the application follows best clean code practices:

- **Meaningful Names**: Variables, methods, and classes are clearly named.
- **Single Responsibility Principle (SRP)**: Every component does one thing and does it well.
- **Consistent Formatting**: Uniform code style with proper indentation.
- **Commenting and Documentation**: Detailed comments explain important sections.

---

## 📂 File and Folder Structure

```plaintext
src/main/java/org/example/
│
├── model/                     # Domain objects
│   ├── user/
│   │   ├── User.java          # User entity (new)
│   │   └── UserManager.java
│   │
│   ├── mood/
│   │   ├── DailyMood.java     
│   │   ├── LogEntry.java      # Base class
│   │   │
│   │   ├── entry/             # Entry type implementations
│   │   │   ├── SleepEntry.java
│   │   │   ├── ProductivityEntry.java
│   │   │   ├── WeatherEntry.java
│   │   │   ├── ExerciseEntry.java
│   │   │   ├── FoodEntry.java
│   │   │   └── ScreenTimeEntry.java
│   │   │
│   │   ├── level/             # Entry level enums
│   │       ├── MoodLevel.java
│   │       ├── SleepQualities.java
│   │       ├── ProductivityLevel.java
│   │       ├── WeatherLevel.java
│   │       ├── ExerciseLevel.java
│   │       ├── FoodSatisfactionLevel.java
│   │       └── ScreenTimeLevel.java
│   │
├── service/                   # Business logic
│   ├── auth/
│   │   └── Authenticator.java
│   │
│   ├── mood/
│   │   ├── MoodTracker.java
│   │   ├── MoodAnalyzer.java
│   │   └── MoodSyncDoctor.java
│   │
├── repository/                # Data access
│   └── DataManager.java
│
├── ui/                        # User interaction
│   ├── UserInteraction.java
│   ├── MoodInteraction.java
│   ├── EntryHandler.java
│   └── visualization/
│       └── CSVTableDisplay.java
│
└── Main.java                  # Application entry point
```

---

## 🏗️ Object-Oriented Programming (OOP) Principles

The system is designed with OOP fundamentals in mind:

- **Encapsulation**: Data and behavior are contained within classes.
- **Inheritance**: Common functionality is shared across classes.
- **Polymorphism**: Different implementations are interchangeable through common interfaces.
- **Abstraction**: Complex logic is simplified via clear, concise interfaces.

---

## 🔄 Refactoring & SOLID Principles

### Single Responsibility Principle (SRP)
- **UserManager**: Manages user registration and authentication.
- **Authenticator**: Executes the authentication logic.
- **DailyMood**: Handles mood data logging.
- **MoodAnalyzer**: Evaluates mood data to provide insights.
- **MoodSyncDoctor**: Delivers expert mental health suggestions.
- **LogEntry & Subclasses**: Represent various mood-related entries.
- **DataManager**: Manages data storage and retrieval.

### Open/Closed Principle (OCP)
- **Extensibility**: New entry types (e.g., SleepEntry, FoodEntry) can be added without modifying existing code.
- **Scalability**: Enum classes allow for seamless addition of new levels.

### Liskov Substitution Principle (LSP)
- Subclasses, such as SleepEntry and ExerciseEntry, fulfill their parent class contracts and can be used interchangeably where a LogEntry is expected.

### Interface Segregation Principle (ISP)
- Each class focuses on its specific functionality, avoiding unnecessary method implementations.

### Dependency Inversion Principle (DIP)
- High-level modules (e.g., **Authenticator**, **MoodAnalyzer**) depend on abstractions rather than low-level modules.

---

## 🚀 Functionality

- **User Registration and Login**: Secure account setup and authentication.
- **Daily Mood Logging**: Seamlessly log your mood and day-to-day activities.
- **Data Analysis**: Receive personalized insights to help you thrive.
- **Data Visualization**: Explore your data with clear, concise tables.
- **MoodSync Doctor**: Get tailored suggestions to boost your mental health.

---

## 📚 OOP Fundamentals & Design Patterns

Inspired by the OOP2 course, the application implements:

- **Core OOP Principles**: Encapsulation, Inheritance, Polymorphism, and Abstraction.
- **Design Patterns**: Utilizes patterns like Singleton and Factory for robust design.
- **Refactoring Techniques**: Continuous improvement for cleaner, more maintainable code.
- **Clean Code Practices**: Consistently readable, well-documented, and modular code.

---

## ✅ Conclusion

By adhering to clean code practices and OOP principles, **MoodSync** delivers a robust, maintainable, and scalable solution for tracking and improving your mental health. Its modular design and continuous refactoring ensure that the application is ready for future improvements and features.

---


