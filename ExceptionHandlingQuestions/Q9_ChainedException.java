package ExceptionHandlingQuestions;

import java.sql.SQLException;

public class Q9_ChainedException {

    // Custom exception for the service layer
    public static class ServiceLayerException extends Exception {
        public ServiceLayerException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Custom exception for the repository layer
    public static class RepositoryException extends Exception {
        public RepositoryException(String message, SQLException e) {
            super(message, e);
        }
    }

    // Repository layer
    public static class Repository {
        public void fetchData() throws RepositoryException {
            try {
                // Simulate a database operation that throws SQLException
                throw new java.sql.SQLException("Database error occurred");
            } catch (java.sql.SQLException e) {
                throw new RepositoryException("Failed to fetch data from the repository", e);
            }
        }
    }

    // Service layer
    public static class Service {
        private Repository repository;

        public Service(Repository repository) {
            this.repository = repository;
        }

        public void processData() throws ServiceLayerException {
            try {
                repository.fetchData();
            } catch (RepositoryException e) {
                throw new ServiceLayerException("Service layer error occurred", e);
            }
        }
    }

    // Controller layer
    public static class Controller {
        private Service service;

        public Controller(Service service) {
            this.service = service;
        }

        public void handleRequest() {
            try {
                service.processData();
            } catch (ServiceLayerException e) {
                System.err.println("Controller caught an exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Controller controller = new Controller(service);

        controller.handleRequest();
    }
}

