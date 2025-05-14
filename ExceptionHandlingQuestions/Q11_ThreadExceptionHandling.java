package ExceptionHandlingQuestions;

public class Q11_ThreadExceptionHandling {

    // Runnable task that throws a RuntimeException
    public static class Task implements Runnable {
        @Override
        public void run() {
            throw new RuntimeException("Exception from child thread");
        }
    }

    public static void main(String[] args) {
        // Create a new thread with the Task
        Thread childThread = new Thread(new Task(), "child-thread");

        // Set the UncaughtExceptionHandler for the child thread
        childThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println("Caught exception from thread: " + t.getName());
                System.err.println("Exception message: " + e.getMessage());
            }
        });

        // Start the child thread
        childThread.start();

        // Wait for the child thread to finish
        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished execution");
    }
}

