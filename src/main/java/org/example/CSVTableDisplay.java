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

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
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

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(padRight(row[i], columnWidths[i] + 2));
            }
            System.out.println();
        }
    }

    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }
}