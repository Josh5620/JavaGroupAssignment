package InventoryManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.example.User;

public class InvenUser extends User {
    
    static String filePath1 = "src/Inventory.txt";
    static List<List<String>> InvenList = new ArrayList<>();
    static List<List<String>> StockAlert = new ArrayList<>();
    
    public static void main(String[] args) {
        makeBigList(filePath1, InvenList);
        
        //System.out.print(InvenList);  
        //System.out.print(lowitemStock());
        lowItemAlert(lowitemStock());
        //updateStock("ID001", 5);
        //Test();
    }
    /*
    public static void Test(){
        List<Object> tst = InvenList.get(0);
        Object value = tst.get(0);
        String type = value instanceof String ? "String" :
               value instanceof Integer ? "Integer" :
               value instanceof Double ? "Double" : 
               "Unknown";
        System.out.println("Type: " + type + " | Value: " + value);
        
    }
    */
    
    public static List<List<String>> lowitemStock(){ // Returns a array of the stuff that is below <5 its public so SM can use it too
        for(List<String> item : InvenList){
            for(String qItem : item){
                try{
                    int itemSum = Integer.parseInt(qItem);        
                    if(itemSum < 5 ){
                        StockAlert.add(item);             
                        }} catch(NumberFormatException e) {continue;}     
            }
        }
        return StockAlert;
    }
    
    public static void updateStock(String id, int amount){ // Update stuff iwht th id and amount ltr with UI can make the confirmation a popup box
        for(List<String> item : InvenList){
            for(String itemID : item){
                if(itemID.toString().equals(id)){
                    
                    String name = item.get(0);
                    int baseQuan = Integer.parseInt(item.get(3));
                    System.out.print("Confirm? (Y to confirm) \nOriginal amount: " +
                            name + ": " + baseQuan + "\nAdding: " + amount +
                            "\nNew Amount: " + (baseQuan + amount));
                    
                    if("Y".equals(scanner.nextLine().trim())){
                        item.set(3, (Integer.toString(baseQuan + amount)));
                        System.out.println(item);
                       
                    }                                
                }
            }
            
        }
        
    }
    
    public static void lowItemAlert(List<List<String>> lowItems){
        
        String AlertText = "";
        for( List<String> item : lowItems){
            String smallText = String.format("(%s / %s) Amount Left: %s \n", item.get(0), item.get(2), item.get(3));
            AlertText = (AlertText + smallText);
            smallText = "";
            
            
        }
        
        JOptionPane.showMessageDialog(
                null,
                AlertText,
                "Inventory Alert",
                JOptionPane.WARNING_MESSAGE);
    } 
}
