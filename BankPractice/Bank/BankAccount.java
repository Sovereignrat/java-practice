package Bank;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        super();
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    
    public BankAccount() {
        super();
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public BankAccount loadAccounts(String accountNumber) {
        String filename = "BankData.bin";
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o;

            while(true) {
                try {
                    o = ois.readObject();
                    if (o instanceof BankAccount) {
                        accounts.add((BankAccount)o);
                    } else {
                        System.err.println("Invalid object in file.");
                    }
                } catch(EOFException ex) {
                    break; // End of file reached
                }
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading the account data.");
            e.printStackTrace();
        }
        for (BankAccount account : accounts) {
            if (account.accountNumber.equals(accountNumber)) {
                return account;
            }
        }
        return null; // Account not found
    }
}
