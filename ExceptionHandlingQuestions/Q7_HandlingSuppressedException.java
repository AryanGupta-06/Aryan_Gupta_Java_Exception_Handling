package ExceptionHandlingQuestions;

// CustomResource.java
class CustomResource implements AutoCloseable {
    private String name;

    public CustomResource(String name) {
        this.name = name;
    }

    @Override
    public void close() throws Exception {
        throw new Exception("Exception from " + name);
    }
}

// Main.java
public class Q7_HandlingSuppressedException {
    public static void main(String[] args) {
        try (CustomResource resource1 = new CustomResource("Resource1");
             CustomResource resource2 = new CustomResource("Resource2")) {
            // Simulate some operations with the resources
            System.out.println("Using resources");
        } catch (Exception e) {
            System.err.println("Primary exception: " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.err.println("Suppressed exception: " + suppressed.getMessage());
            }
        }
    }
}

