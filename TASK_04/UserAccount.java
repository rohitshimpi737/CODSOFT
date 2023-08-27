import java.util.*;
public class UserAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private List<Transaction> transactions;


  
    public UserAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin != null ? pin : "1234"; 
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }
    public void setPin(String newPin) {
        pin = newPin;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            // Handle insufficient balance
        }
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }



    // Other methods as needed
}
