// shared_manager/POManager.java
package shared_manager;

import java.util.*;

public class POManager {
    static String filePath = "src/PurchaseOrders.txt";
    static List<List<String>> poList = new ArrayList<>();

    public POManager() {
        makeBigList(filePath, poList);
    }

    /** raw rows, each size==7 as in the txt file */
    public static List<List<String>> getPOList() {
        return poList;
    }

    /**
     * Add a new PO.
     * record must be List of 7 strings:
     * [0]=poID,
     * [1]=date,
     * [2]=itemIDs slash-separated,
     * [3]=qtys slash-separated,
     * [4]=status,
     * [5]=pmID,
     * [6]=resolution
     */
    public static boolean addPO(List<String> record) {
        if (record.size() != 7) return false;
        poList.add(record);
        updateTextFile(poList, filePath);
        return true;
    }

    /**
     * Edit status and/or resolution for a given PO
     */
    public static boolean editPO(String poID, String newStatus, String newResolution) {
        for (List<String> row : poList) {
            if (row.get(0).equalsIgnoreCase(poID)) {
                row.set(4, newStatus);
                row.set(6, newResolution);
                updateTextFile(poList, filePath);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a PO by ID (if ever needed)
     */
    public static boolean deletePO(String poID) {
        for (int i = 0; i < poList.size(); i++) {
            if (poList.get(i).get(0).equalsIgnoreCase(poID)) {
                poList.remove(i);
                updateTextFile(poList, filePath);
                return true;
            }
        }
        return false;
    }

    // hook in your User.makeBigList & User.updateTextFile
    private void makeBigList(String filepath, List<List<String>> mainList) {
        // copy–paste User.makeBigList here
    }
    private static void updateTextFile(List<List<String>> arrayList, String FilePathString) {
        // copy–paste User.updateTextFile here
    }
}
