package ExceptionHandlingQuestions;

public class Q12_GlobalExceptionHandler {

    // Global exception handler class
    public static class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.err.println("Caught exception from thread: " + t.getName());
            System.err.println("Exception message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Set the global exception handler
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());

        // Simulate an uncaught exception in the main thread
        throw new RuntimeException("Uncaught exception in main thread 1");

    }
}

