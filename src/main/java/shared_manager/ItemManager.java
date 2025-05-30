package shared_manager;
import UserLogin.User;
import java.util.*;

public class ItemManager extends User{
    static String filePath = "src/Items.txt";
    static List<List<String>> itemList = new ArrayList<>();

    public ItemManager() {
        makeBigList(filePath, itemList);
    }

    public static List<List<String>> getItemList() {
        return itemList;
    }

    /** record = [itemID, itemName, price, supplierID] */
    public static boolean addItem(List<String> record) {
        for (List<String> row : itemList) {
            if (row.get(0).equalsIgnoreCase(record.get(0))) {
                return false; // duplicate ID
            }
        }
        itemList.add(record);
        updateTextFile(itemList, filePath);
        return true;
    }

    /** Replace entire row matching itemID */
    public static boolean editItem(String itemID, List<String> newValues) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).get(0).equalsIgnoreCase(itemID)) {
                itemList.set(i, newValues);
                updateTextFile(itemList, filePath);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteItem(String itemID) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).get(0).equalsIgnoreCase(itemID)) {
                itemList.remove(i);
                updateTextFile(itemList, filePath);
                return true;
            }
        }
        return false;
    }
}
