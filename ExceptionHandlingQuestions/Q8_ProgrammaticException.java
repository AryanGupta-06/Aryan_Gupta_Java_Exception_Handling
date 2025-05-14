package ExceptionHandlingQuestions;

import java.util.Random;

// ServiceUnavailableException.java
class ServiceUnavailableException extends Exception {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}

class FlakyService {
    private Random random = new Random();

    public String fetchData() throws ServiceUnavailableException {
        if (random.nextBoolean()) {
            throw new ServiceUnavailableException("Service is temporarily unavailable");
        }
        return "Data from service";
    }
}

// ServiceClient.java
public class Q8_ProgrammaticException {
    private static final int MAX_RETRIES = 3;

    public String fetchDataWithRetry() throws ServiceUnavailableException {
        FlakyService service = new FlakyService();
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                return service.fetchData();
            } catch (ServiceUnavailableException e) {
                attempts++;
                if (attempts == MAX_RETRIES) {
                    throw new ServiceUnavailableException("Failed to fetch data after " + MAX_RETRIES + " attempts");
                }
                System.out.println("Retrying... (" + attempts + ")");
            }
        }
        return null; // This line will never be reached
    }

    public static void main(String[] args) {
        Q8_ProgrammaticException client = new Q8_ProgrammaticException();
        try {
            String data = client.fetchDataWithRetry();
            System.out.println("Fetched data: " + data);
        } catch (ServiceUnavailableException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

