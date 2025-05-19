package InventoryManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import UserLogin.User;


public class InvenUser extends User {
    
    static String InvenFilePath = "src/Inventory.txt";
    static List<List<String>> InvenList = new ArrayList<>();
    static List<List<String>> StockAlert = new ArrayList<>();
    
    public static void main(String[] args) {
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
    
    public InvenUser(){
        makeBigList(InvenFilePath, InvenList);
    }
    
    public static List<List<String>> getInvenList(){
        return InvenList;
    }
    
    public static List<List<String>> lowitemStock(){ // Returns a array of the stuff that is below <5 its public so SM can use it too
        if (!StockAlert.isEmpty()) {
        StockAlert.clear(); // 
        }
        for(List<String> item : InvenList){
            for(String qItem : item){
                try{
                    int itemSum = Integer.parseInt(qItem);        
                    if(itemSum < 20 ){
                        StockAlert.add(item);             
                        }} catch(NumberFormatException e) {continue;}     
            }
        }
        return StockAlert;
    }
    
    public static void updateStock(String id, int amount){ // Update stuff iwht th id and amount ltr with UI can make the confirmation a popup box
        for(List<String> item : InvenList){
            for(String itemID : item){
                if(itemID.equals(id)){
                    int baseQuan = Integer.parseInt(item.get(2));
                    int newAmount = amount;
                    
                    item.set(2, String.valueOf(newAmount));
                    System.out.println("Updated " + item.get(0) + " stock to: " + newAmount);
                    break;
                }
            }
        }
        updateTextFile(InvenList, InvenFilePath);
    }

    public void lowItemAlertSend(String username) {
        lowitemStock();
        StringBuilder alertBuilder = new StringBuilder("Low stock: ");

        for (int i = 0; i < StockAlert.size(); i++) {
            String itemName = StockAlert.get(i).get(1); // get item name

            alertBuilder.append(itemName);

            if (i < StockAlert.size() - 1) {
                alertBuilder.append(", ");
            }
        }

        sendAlert("SM", username, alertBuilder.toString());

        StockAlert.clear();
    }
    
/*
    public static void lowItemAlert(List<List<String>> lowItems){     
        String AlertText = "";
        
        for( List<String> item : lowItems){
            String smallText = String.format("(%s / %s) Amount Left: %s \n", item.get(0), item.get(1), item.get(2));
            AlertText = (AlertText + smallText);
            smallText = "";
            } 
        JOptionPane.showMessageDialog(
                null,
                AlertText,
                "Inventory Alert",
                JOptionPane.WARNING_MESSAGE);
        lowItems.clear();
    } 
*/
    
    public static List botSix(){
        List<List<String>> sortedList = new ArrayList<>(InvenList);
        List<List<String>> bot6List = new ArrayList<>();
        // Sort the list based on the quantity (index 2)
        sortedList.sort((a, b) -> {
            try {
                int qtyA = Integer.parseInt(a.get(2));
                int qtyB = Integer.parseInt(b.get(2));
                return Integer.compare(qtyA, qtyB); 
            } catch (NumberFormatException e) {
                return 0; 
            }
        });
        for (int i = 0; i < Math.min(6, sortedList.size()); i++) {
            bot6List.add(sortedList.get(i));
        }
        return bot6List;
    }
}