/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
import java.io.*;
import java.util.*;

public class PRManager {
    private final String FILE_PATH = "C:\\Users\\dhoom\\Downloads\\PurchaseRequisitions.txt";

    // Load All PRs
    public List<PR> loadPRs() throws IOException {
        List<PR> prs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                prs.add(new PR(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
            }
        }
        br.close();
        return prs;
    }

    // Generate Next PRID
    public String generateNextPRID() throws IOException {
        List<PR> prs = loadPRs();
        int maxNum = 0;
        for (PR p : prs) {
            String id = p.getPrid();
            if (id.startsWith("PR")) {
                try {
                    int num = Integer.parseInt(id.substring(2));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("PR%03d", maxNum + 1);
    }

    // Add PR
    public void addPR(PR pr) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(pr.toString());
        bw.newLine();
        bw.close();
    }

    // Edit PR
    public void editPR(PR updatedPR) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(updatedPR.getPrid())) {
                lines.add(updatedPR.toString());
            } else {
                lines.add(line);
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    // Delete PR
    public void deletePR(String prid) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (!parts[0].equals(prid)) {
                lines.add(line);
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }
}


