# MoodSync - Mental Health Companion Application

## Overview
**MoodSync** is a comprehensive mental health companion application designed to help users track and analyze their daily moods and activities. The application allows users to log various aspects of their daily life, such as sleep quality, productivity, weather, exercise, food satisfaction, and screen time. It provides insights and suggestions based on the logged data to help users improve their mental well-being.

## Features

- **User Authentication**: Secure registration and login system.
- **Mood Tracking**: Log daily moods and various activities.
- **Data Analysis**: Analyze logged data to provide insights and suggestions.
- **Visualization**: Display logged data in a tabular format.
- **MoodSync Doctor**: Provides suggestions and tips for mental health issues.

## Clean Code Practices
The application follows several clean code practices to ensure maintainability and readability:

- **Meaningful Names**: Variables, methods, and classes have descriptive names that convey their purpose.
- **Single Responsibility Principle**: Each class and method has a single responsibility, making the code easier to understand and modify.
- **Consistent Formatting**: The code is consistently formatted with proper indentation and spacing.
- **Commenting and Documentation**: Important sections of the code are documented with comments to explain their functionality.

## File and folder structure

The file and folder structure is organized to promote modularity and separation of concerns:

```
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

## Object-Oriented Programming (OOP) Principles
The application is designed following fundamental OOP principles:

- **Encapsulation**: Data and methods are encapsulated within classes, providing a clear interface and hiding implementation details.
- **Inheritance**: Common functionality is abstracted into base classes, and specific behaviors are implemented in derived classes.
- **Polymorphism**: Interfaces and abstract classes are used to define common behaviors, allowing different implementations to be used interchangeably.
- **Abstraction**: Complex logic is abstracted into simpler interfaces and classes, making the code easier to understand and extend.

## Refactoring
The codebase has been refactored to improve its structure and readability:

- **Modularization**: The code is organized into packages based on functionality, such as auth, mood, repository, and ui.
- **Method Extraction**: Large methods are broken down into smaller, more manageable methods.
- **Code Duplication**: Duplicate code is identified and refactored into reusable methods or classes.

## Functionality

The application provides the following functionality:

- **User Registration and Login**: Users can register and log in to the application securely.
- **Daily Mood Logging**: Users can log their daily mood and various activities, such as sleep, productivity, weather, exercise, food, and screen time.
- **Data Analysis**: The application analyzes the logged data and provides insights and suggestions to help users improve their mental well-being.
- **Data Visualization**: Logged data is displayed in a tabular format for easy viewing.
- **MoodSync Doctor**: Provides suggestions and tips for various mental health issues based on user input.

## OOP Fundamentals
This application follows the fundamental knowledge of Object-Oriented Programming 2 (OOP2) course by:

- **Applying OOP Principles**: The application is designed using core OOP principles such as encapsulation, inheritance, polymorphism, and abstraction.
- **Design Patterns**: Common design patterns, such as Singleton and Factory, are used to solve recurring design problems.
- **Refactoring Techniques**: The codebase is continuously refactored to improve its structure, readability, and maintainability.
- **Clean Code Practices**: The code follows clean code practices to ensure it is easy to read, understand, and modify.

## Code Structure Overview

### Main.java
- **Single Responsibility Principle (SRP)**: The Main class is responsible only for starting the application and handling the initial user interaction.
- **Meaningful Names**: The method `printHeader` and the variables within it have descriptive names that convey their purpose.

### DailyMood.java
- **Encapsulation**: The class encapsulates the data related to a daily mood entry, providing getter and setter methods to access and modify the data.
- **Single Responsibility Principle (SRP)**: The class is responsible for managing the mood entries for a single day.
- **Consistent Formatting**: The code is consistently formatted with proper indentation and spacing.

### Authenticator.java
- **Single Responsibility Principle (SRP)**: The Authenticator class is responsible for handling user authentication.
- **Encapsulation**: The class encapsulates the authentication logic, providing methods to register and login users.

### UserManager.java
- **Single Responsibility Principle (SRP)**: The UserManager class is responsible for managing user data.
- **Encapsulation**: The class encapsulates the user data and provides methods to register and authenticate users.

### EntryHandler.java
- **Single Responsibility Principle (SRP)**: The EntryHandler class is responsible for handling user input for different types of entries.
- **Meaningful Names**: Methods like `addSleepEntry`, `addProductivityEntry`, etc., have descriptive names that convey their purpose.
- **Consistent Formatting**: The code is consistently formatted with proper indentation and spacing.

### DataManager.java
- **Single Responsibility Principle (SRP)**: The DataManager class is responsible for managing the data storage and retrieval.
- **Encapsulation**: The class encapsulates the logic for saving and loading data from a file.
- **Method Extraction**: Large methods are broken down into smaller, more manageable methods, such as `saveDailyMoodToFile` and `loadAllDailyMoods`.

### MoodAnalyzer.java
- **Single Responsibility Principle (SRP)**: The MoodAnalyzer class is responsible for analyzing mood data.
- **Meaningful Names**: Methods like `analyzeEntries` have descriptive names that convey their purpose.
- **Consistent Formatting**: The code is consistently formatted with proper indentation and spacing.

### CSVTableDisplay.java
- **Single Responsibility Principle (SRP)**: The CSVTableDisplay class is responsible for displaying data in a tabular format.
- **Encapsulation**: The class encapsulates the logic for reading and displaying CSV data.
- **Method Extraction**: Large methods are broken down into smaller, more manageable methods, such as `displayTable` and `displayDailyMoodData`.

### MoodSyncDoctor.java
- **Single Responsibility Principle (SRP)**: The MoodSyncDoctor class is responsible for providing suggestions based on user input.
- **Meaningful Names**: Methods like `startSession` and `provideSuggestions` have descriptive names that convey their purpose.
- **Consistent Formatting**: The code is consistently formatted with proper indentation and spacing.

### LogEntry.java
- **Encapsulation**: The class encapsulates the common properties of all log entries, providing getter and setter methods to access and modify the data.
- **Inheritance**: The class serves as a base class for other specific entry types like `SleepEntry`, `ProductivityEntry`, etc.

### Entry Types (SleepEntry.java, ProductivityEntry.java, ScreenTimeEntry.java, etc.)
- **Inheritance**: These classes inherit from `LogEntry` and add specific properties related to their respective entry types.
- **Encapsulation**: The classes encapsulate the data related to each specific entry type, providing getter and setter methods to access and modify the data.

### MoodLevel, ExerciseLevel, FoodSatisfactionLevel, etc. (Enums)
- **Encapsulation**: Each enum encapsulates the different levels and their descriptions.
- **Meaningful Names**: The enum constants and their methods have descriptive names that convey their purpose.

## Conclusion
By adhering to clean code practices and OOP principles, **MoodSync** provides a robust, maintainable, and scalable solution for tracking and improving mental health. The application's modular design and continuous refactoring ensure that it is adaptable to future improvements and features.
