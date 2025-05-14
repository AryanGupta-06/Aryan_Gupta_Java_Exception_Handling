package ExceptionHandlingQuestions;

// Q5_PartialTransactionRollbackOnFailure.java

import java.util.HashMap;
import java.util.Map;

class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws NotEnoughFundsException {
        if (balance < amount) {
            throw new NotEnoughFundsException("Insufficient funds for withdrawal");
        }
        balance -= amount;
    }
}

class NotEnoughFundsException extends Exception {
    public NotEnoughFundsException(String message) {
        super(message);
    }
}

class InvalidBankAccountException extends Exception {
    public InvalidBankAccountException(String message) {
        super(message);
    }
}

class Reserve {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount)
            throws NotEnoughFundsException, InvalidBankAccountException {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new InvalidBankAccountException("Invalid account number");
        }

        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } catch (NotEnoughFundsException e) {
            // Rollback transaction
            fromAccount.deposit(amount);
            throw e;
        }
    }
}

public class Q5_PartialTransactionRollbackOnFailure {
    public static void main(String[] args) {
        Reserve bank = new Reserve();
        Account account1 = new Account("123", 1000);
        Account account2 = new Account("456", 500);

        bank.addAccount(account1);
        bank.addAccount(account2);

        try {
            bank.transferFunds("123", "456", 200);
            System.out.println("Transfer successful");
        } catch (NotEnoughFundsException | InvalidBankAccountException e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }

        System.out.println("Account 123 balance: " + account1.getBalance());
        System.out.println("Account 456 balance: " + account2.getBalance());
    }
}
