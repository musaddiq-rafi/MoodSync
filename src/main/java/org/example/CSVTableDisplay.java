package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVTableDisplay {
    public void displayTable(String filename) {
        List<String[]> rows = new ArrayList<>();
        int[] columnWidths = null;
        String date = "";
        String mood = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read header line (Date and Mood)
            if ((line = reader.readLine()) != null) {
                String[] headerParts = line.split(";");
                if (headerParts.length >= 3) {
                    date = headerParts[1].trim();
                    mood = headerParts[2].trim();
                }
            }

            // Read the rest of the entries (Type;Feel;Note)
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                rows.add(columns);

                if (columnWidths == null) {
                    columnWidths = new int[columns.length];
                } else if (columns.length > columnWidths.length) {
                    int[] newColumnWidths = new int[columns.length];
                    System.arraycopy(columnWidths, 0, newColumnWidths, 0, columnWidths.length);
                    columnWidths = newColumnWidths;
                }

                for (int i = 0; i < columns.length; i++) {
                    columnWidths[i] = Math.max(columnWidths[i], columns[i].length());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        if (rows.isEmpty()) {
            System.out.println("No data found in CSV file.");
            return;
        }

        // Print mood header
        System.out.println("=".repeat(50));
        System.out.printf(" Mood Log - Date: %s | Mood: %s%n", date, mood);
        System.out.println("=".repeat(50));

        // Print column headers
        String[] headers = {"Type", "Feel", "Note"};
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = Math.max(columnWidths[i], headers[i].length());
        }

        printSeparator(columnWidths);
        for (int i = 0; i < headers.length; i++) {
            System.out.print("| " + padRight(headers[i], columnWidths[i]) + " ");
        }
        System.out.println("|");
        printSeparator(columnWidths);

        // Print data rows
        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                System.out.print("| " + padRight(row[i], columnWidths[i]) + " ");
            }
            System.out.println("|");
        }
        printSeparator(columnWidths);
    }

    private void printSeparator(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");
    }

    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }
}