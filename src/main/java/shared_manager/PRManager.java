package shared_manager;
import UserLogin.User;

import java.util.*;

public class PRManager extends User{
    private static String filePath = "src/PurchaseRequisitions.txt";
    private static List<List<String>> prList = new ArrayList<>();

    public PRManager() {
        makeBigList(filePath, prList);
    }

    public static List<List<String>> getPRList() {
        return prList;
    }

    public static boolean addPR(List<String> record) {
        if (record.size() != 6) return false;
        prList.add(record);
        updateTextFile(prList, filePath);
        return true;
    }

    public static boolean editStatus(String prID, String newStatus) {
        for (List<String> row : prList) {
            if (row.get(0).equalsIgnoreCase(prID)) {
                row.set(6, newStatus);
                updateTextFile(prList, filePath);
                return true;
            }
        }
        return false;
    }

    public static boolean deletePR(String prID) {
        for (int i = 0; i < prList.size(); i++) {
            if (prList.get(i).get(0).equalsIgnoreCase(prID)) {
                prList.remove(i);
                updateTextFile(prList, filePath);
                return true;
            }
        }
        return false;
    }
}
