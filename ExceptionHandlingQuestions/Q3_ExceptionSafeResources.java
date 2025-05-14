package ExceptionHandlingQuestions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Q3_ExceptionSafeResources {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Using try-with-resources to ensure both resources are closed safely
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copy completed successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
        }
    }
}

