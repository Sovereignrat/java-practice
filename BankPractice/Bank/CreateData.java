package Bank;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CreateData {
    public static void main(String[] args) {
        // This class would contain methods to create and save BankAccount data
        // For example, writing to a file or database
        List<BankAccount> accounts = List.of(
            new BankAccount("100001", "Alice Smith", 500.0),
            new BankAccount("100002", "Bob Johnson", 1500.0),
            new BankAccount("100003", "Charlie Brown", 250.0)
        );
        // Code to save accounts would go here
        String filename = "BankData.bin";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            for (BankAccount account : accounts) {
                oos.writeObject(account);
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the account data.");
            e.printStackTrace();
        }
    }
    
}
