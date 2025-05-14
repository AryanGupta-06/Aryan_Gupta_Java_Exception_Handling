package ExceptionHandlingQuestions;

// Base custom exception
class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

// Subclass for insufficient funds
class InsufficientFundsException extends BankingException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Subclass for invalid account
class InvalidAccountException extends BankingException {
    public InvalidAccountException(String message) {
        super(message);
    }
}

// Bank class with transferFunds method
class Bank {
    private double balance;

    public Bank(double balance) {
        this.balance = balance;
    }

    public void transferFunds(double amount, String accountNumber) throws InsufficientFundsException, InvalidAccountException {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new InvalidAccountException("Invalid account number.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for the transfer.");
        }
        balance -= amount;
        System.out.println("Transfer successful. New balance: " + balance);
    }
}

// Main class to demonstrate exception handling
public class Q2_CustomExceptionWithHierarchy extends Exception {
    public static void main(String[] args) {
        Bank bank = new Bank(1000.0);

        // Demonstrate catching exceptions separately
        try {
            bank.transferFunds(1500.0, "12345");
        } catch (InsufficientFundsException e) {
            System.err.println("Caught InsufficientFundsException: " + e.getMessage());
        } catch (InvalidAccountException e) {
            System.err.println("Caught InvalidAccountException: " + e.getMessage());
        }

        // Demonstrate catching exceptions together
        try {
            bank.transferFunds(500.0, "");
        } catch (BankingException e) {
            System.err.println("Caught BankingException: " + e.getMessage());
        }
    }
}

