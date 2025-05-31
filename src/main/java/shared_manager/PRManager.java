package shared_manager;

import UserLogin.User;
import shared_model.PurchaseRequisition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PRManager extends User {
    private static final String FILE_PATH = "src/PurchaseRequisitions.txt";
    private static final List<List<String>> prList = new ArrayList<>();

    public PRManager() {
        makeBigList(FILE_PATH, prList);
    }

    // For IM/FM compatibility
    public static List<List<String>> getPRList() {
        return prList;
    }

    // Adds a new PR (PurchaseRequisition object) — used by SM
    public static boolean addPR(PurchaseRequisition pr) {
        List<String> record = Arrays.asList(pr.toDataLine().split("\\|"));
        if (record.size() != 7) return false;
        prList.add(record);
        updateTextFile(prList, FILE_PATH);
        return true;
    }

    // Deletes a PR by ID — used by SM/PM
    public static boolean deletePR(String prID) {
        for (int i = 0; i < prList.size(); i++) {
            List<String> row = prList.get(i);
            if (row.get(0).equalsIgnoreCase(prID)) {
                prList.remove(i);
                updateTextFile(prList, FILE_PATH);
                return true;
            }
        }
        return false;
    }

    // Converts List<List<String>> to List<PurchaseRequisition> — used by SM/PM
    public static List<PurchaseRequisition> loadAllPRs() {
        List<PurchaseRequisition> result = new ArrayList<>();
        for (List<String> row : prList) {
            try {
                String line = String.join("|", row);
                result.add(PurchaseRequisition.parse(line));
            } catch (Exception e) {
                System.err.println("Skipping invalid PR: " + row);
            }
        }
        return result;
    }

    // Generates next PR ID like PR001, PR002...
    public static String generateNextPRID() {
        String lastID = "PR000";
        for (List<String> row : prList) {
            String id = row.get(0);
            if (id.startsWith("PR")) lastID = id;
        }
        int next = Integer.parseInt(lastID.substring(2)) + 1;
        return String.format("PR%03d", next);
    }
}
