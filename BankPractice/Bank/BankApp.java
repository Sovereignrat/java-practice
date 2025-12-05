// File: BankApp.java
// Programmer: Edgar Fernandez
// Date: 11/24/2025
// Description: main method to test the Bank class.
//
package Bank;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

public class BankApp {
    public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);

    System.out.println("Welcome to the Bank Application!");
    System.out.println("This application allows you to manage your bank accounts.");
    System.out.println("You can create accounts, deposit, withdraw, and view account information.");
    System.out.println("Let's get started!");
    System.out.println("Do you have an existing account? (yes/no)");
    String response = myObj.nextLine();

    // Handle user response
    if (response.equalsIgnoreCase("yes")) {
        System.out.println("Please enter your account number:");
        String accountNumber = myObj.nextLine();
        try {
            File file = new File("BankData.bin");
            if (!file.exists()) {
                System.out.println("No accounts found. Please create a new account.");
                myObj.close();
                return;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while accessing the account data.");
            e.printStackTrace();
            myObj.close();
            return;
        }
        BankAccount account = new BankAccount();
        // TODO: Implement loading account from file based on accountNumber
        // For now, using a placeholder account
        if (accountNumber == null || accountNumber.isEmpty()) {
            System.out.println("Account not found. Please check your account number or create a new account.");
            myObj.close();
            return;
        }
        // Here you would typically look up the account in a database or file
        System.out.println("Account found! You can now manage your account.");
        Boolean validChoice = true;
        int choice = myObj.nextInt();
        while(choice !=4 && validChoice) {
            System.out.println("What would you like to do?");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Account Info");
            System.out.println("4. Exit");
            if (choice == 1) {
                System.out.println("Enter deposit amount:");
                double amount = myObj.nextDouble();
                // Perform deposit operation here
                System.out.println("Deposited: $" + amount);
            } else if (choice == 2) {
               System.out.println("Enter withdrawal amount:");
               double amount = myObj.nextDouble();
               // Perform withdrawal operation here
               System.out.println("Withdrew: $" + amount);
            } else if (choice == 3) {
                // Display account information here
                System.out.println("Displaying account information...");
            } else if (choice == 4) {
                System.out.println("Exiting. Thank you for using our Bank Application!");   
            } else {
                System.out.println("Invalid choice. exiting"); 
                validChoice = false;
            }
            
        }
        
        
    } else {
        System.out.println("Let's create a new account.");
        System.out.println("Enter your name:");
        String name = myObj.nextLine();
        System.out.println("Enter initial deposit amount:");
        double initialDeposit = myObj.nextDouble();
        Random rand = new Random();
        int accountNumber = rand.nextInt(1000000); // Generate random account number
        List<BankAccount> accounts = List.of(
            new BankAccount(String.valueOf(accountNumber), name, initialDeposit)
        );
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BankData.bin"));
            for (BankAccount account : accounts) {
                oos.writeObject(account);
            }
            oos.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred while saving the account data.");
            e.printStackTrace();
        }
        // Here you would typically save the new account to a database or file
        System.out.println("Account created successfully! Your account number is: " + accountNumber);
    }
    myObj.close();
    System.out.println("Thank you for using our Bank Application!");
    }
}