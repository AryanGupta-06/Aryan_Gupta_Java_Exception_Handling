package ExceptionHandlingQuestions;

import java.io.IOException;

// Custom exception class
class CustomException extends Exception {
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Class containing methods for exception handling
class ExceptionHandlingExample {
    public static void riskyMethod() throws IOException {
        // Simulating an exception
        throw new IOException("Original IOException occurred");
    }

    public static void handleException() throws CustomException {
        try {
            riskyMethod();
        } catch (IOException e) {
            // Catching IOException and re-throwing it wrapped in CustomException
            throw new CustomException("CustomException: Error in riskyMethod", e);
        }
    }
}

// Main class to demonstrate exception handling
public class Q4_RethrowingException {
    public static void main(String[] args) {
        try {
            ExceptionHandlingExample.handleException();
        } catch (CustomException e) {
            // Handling the re-thrown CustomException
            System.err.println("Caught CustomException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

