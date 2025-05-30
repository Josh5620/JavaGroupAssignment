// shared_manager/PRManager.java
package shared_manager;

import java.util.*;

public class PRManager {
    static String filePath = "src/PurchaseRequisitions.txt";
    static List<List<String>> prList = new ArrayList<>();

    public PRManager() {
        makeBigList(filePath, prList);
    }

    /** full raw data: each row has 7 fields exactly as in the txt */
    public static List<List<String>> getPRList() {
        return prList;
    }

    /**
     * Add a new PR.
     * record must be a List of 7 strings:
     * [0]=PRID,
     * [1]=itemIDs slash-separated,
     * [2]=qtys slash-separated,
     * [3]=date,
     * [4]=supplierID,
     * [5]=smID,
     * [6]=status
     */
    public static boolean addPR(List<String> record) {
        if (record.size() != 7) return false;
        prList.add(record);
        updateTextFile(prList, filePath);
        return true;
    }

    /**
     * Change the status (e.g. to "Approved" or "Rejected")
     */
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

    /**
     * If you ever needed to delete:
     */
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

    // ─── helper methods from your User class ───────────────────
    private void makeBigList(String filepath, List<List<String>> mainList) {
        // copy–paste your User.makeBigList here
    }
    private static void updateTextFile(List<List<String>> arrayList, String FilePathString) {
        // copy–paste User.updateTextFile here
    }
}
