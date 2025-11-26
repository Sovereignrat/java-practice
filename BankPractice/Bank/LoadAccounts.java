package Bank;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadAccounts {
    public static void main(String[] args) {
        // This class would contain methods to load an existing BankAccount from storage
        // For example, reading from a file or database
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
        // Display loaded accounts
        for (BankAccount account : accounts) {
            account.displayAccountInfo();
        }
    }    
}
