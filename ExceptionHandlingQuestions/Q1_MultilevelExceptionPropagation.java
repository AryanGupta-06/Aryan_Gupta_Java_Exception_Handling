package ExceptionHandlingQuestions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

// Custom exception class
class DataProcessingException extends Exception {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class Q1_MultilevelExceptionPropagation {
    private static final Logger logger = Logger.getLogger(Q1_MultilevelExceptionPropagation.class.getName());

    // Method to read data from a file
    public static void readData() throws IOException {
        // Simulating reading from a file
        Files.readAllLines(Paths.get("data.txt"));
    }

    // Method to process data
    public static void processData() throws DataProcessingException {
        try {
            readData();
        } catch (IOException e) {
            // Catching IOException and throwing a custom DataProcessingException
            throw new DataProcessingException("Error processing data", e);
        }
    }

    // Main method
    public static void main(String[] args) {
        try {
            processData();
        } catch (DataProcessingException e) {
            // Catching DataProcessingException and logging it
            logger.log(Level.SEVERE, "Exception caught in main: ", e);
        }
    }
}

