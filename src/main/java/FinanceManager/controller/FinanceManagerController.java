/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.controller;
import FinanceManager.dao.PurchaseRequisitionDAO;
import FinanceManager.dao.PurchaseRequisitionDAO;
import FinanceManager.model.PurchaseRequisition;
import FinanceManager.model.PurchaseRequisition;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author sumingfei
 */
public class FinanceManagerController {
    
    public static List<PurchaseRequisition> getAllRequisitions() throws IOException {
        return PurchaseRequisitionDAO.loadAll();
    }

    public static void approveRequisition(String prId) throws IOException {
        List<PurchaseRequisition> all = PurchaseRequisitionDAO.loadAll();
        for (PurchaseRequisition pr : all) {
            if (pr.getPrId().equals(prId)) {
                pr.setStatus("APPROVED");
                PurchaseRequisitionDAO.update(pr);
                return;
            }
        }
        throw new IllegalArgumentException("PR not found: " + prId);
    }
    
    public static void createRequisition(String desc, int qty) throws IOException {
    // 1) Generate unique PR id
    // 2) Write a new line to your PR data file:
    //    id + "|" + LocalDate.now() + "|" + desc + "|" + qty + "|PENDING"
    // 3) (Optional) Return or log success
}
    
}
