package InventoryManager;
import java.util.ArrayList;
import java.util.List;
import org.example.User;

public class InvenUser extends User {
    
    static String filePath1 = "src/Inventory.txt";
    static List<List<String>> InvenList = new ArrayList<>();
    
    public static void main(String[] args) {
        makeBigList(filePath1, InvenList);
        System.out.print(InvenList);
        
    }
}
