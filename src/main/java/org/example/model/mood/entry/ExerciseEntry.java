package org.example.model.mood.entry;

import org.example.model.mood.level.ExerciseLevel;
import org.example.model.mood.LogEntry;

public class ExerciseEntry extends LogEntry {
    private ExerciseLevel exerciseLevel;
    private String exerciseDescription;

    public ExerciseLevel getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(ExerciseLevel exerciseLevel) {
        this.exerciseLevel = exerciseLevel;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String toCSV() {
        return exerciseLevel + ";" + exerciseDescription;
    }

    public static ExerciseEntry fromCSV(String csv) {
        String[] parts = csv.split(";", 2);
        ExerciseEntry entry = new ExerciseEntry();
        entry.setExerciseLevel(ExerciseLevel.valueOf(parts[0]));
        entry.setExerciseDescription(parts[1]);
        return entry;
    }
}