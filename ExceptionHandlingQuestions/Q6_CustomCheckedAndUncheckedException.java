package ExceptionHandlingQuestions;

// FileProcessor.java
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// FileValidationException.java
class FileValidationException extends Exception {
    public FileValidationException(String message) {
        super(message);
    }
}

// CriticalSystemException.java
class CriticalSystemException extends RuntimeException {
    public CriticalSystemException(String message) {
        super(message);
    }
}

public class Q6_CustomCheckedAndUncheckedException {
    public void validateFile(String filePath) throws FileValidationException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new FileValidationException("Invalid file: " + filePath);
        }
    }

    public void processFile(String filePath) {
        try {
            validateFile(filePath);
            // Simulate file processing
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            System.out.println("File content: " + content);
        } catch (FileValidationException e) {
            System.err.println("File validation failed: " + e.getMessage());
        } catch (IOException e) {
            throw new CriticalSystemException("Critical system error while reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Q6_CustomCheckedAndUncheckedException processor = new Q6_CustomCheckedAndUncheckedException();
        String validFilePath = "input.txt";
        String invalidFilePath = "output.txt";

        // Process a valid file
        processor.processFile(validFilePath);

        // Process an invalid file
        processor.processFile(invalidFilePath);
    }
}

