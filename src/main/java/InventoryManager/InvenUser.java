package InventoryManager;
import java.util.ArrayList;
import java.util.List;
import UserLogin.User;


public class InvenUser extends User {
    
    static String InvenFilePath = "src/Inventory.txt";
    static List<List<String>> InvenList = new ArrayList<>();
    static List<List<String>> StockAlert = new ArrayList<>();
    
    public InvenUser(){
        InvenList.clear();
        makeBigList(InvenFilePath, InvenList);
    }
    
    public static List<List<String>> getInvenList(){
        return InvenList;
    }
    
    public static List<List<String>> lowitemStock(){ // Returns a array of the stuff that is below <20
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
    
    public static void updateStock(String id, int amount){ 
        for(List<String> item : InvenList){
            for(String itemID : item){
                if(itemID.equals(id)){
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
            String itemName = StockAlert.get(i).get(1); // Gets item name

            alertBuilder.append(itemName);

            if (i < StockAlert.size() - 1) {
                alertBuilder.append(", ");
            }
        }
        sendAlert("SM", username, alertBuilder.toString());
        StockAlert.clear();
    }
    
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