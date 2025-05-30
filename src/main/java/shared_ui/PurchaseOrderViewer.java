package shared_ui;

import shared_manager.ItemManager;
import shared_manager.POManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderViewer {
    // load items once for lookups
    static List<List<String>> itemsList = new ArrayList<>();
    static {
        new ItemManager();                 // calls makeBigList internally
        itemsList = ItemManager.getItemList();
    }

    /**
     * Call this with one raw PO row from POManager.getPOList()
     * record layout:
     * 0=poID
     * 1=date
     * 2=itemIDs slash-separated
     * 3=quantities slash-separated
     * 4=status
     * 5=pmID
     * 6=resolution
     */
    public static void showPO(List<String> po) {
        // extract fields
        String poID    = po.get(0);
        String date    = po.get(1);
        String items   = po.get(2);
        String qtys    = po.get(3);
        String status  = po.get(4);

        // split into arrays
        String[] itemArr = items.split("/");
        String[] qtyArr  = qtys.split("/");

        // build table data
        String[] cols = { "Item ID", "Item Name", "Order Qty" };
        Object[][] data = new Object[itemArr.length][3];
        for (int i = 0; i < itemArr.length; i++) {
            String id = itemArr[i];
            String name = findItemName(id);
            String q    = (i < qtyArr.length ? qtyArr[i] : "");
            data[i][0] = id;
            data[i][1] = name;
            data[i][2] = q;
        }

        // set up dialog
        JDialog dlg = new JDialog();
        dlg.setTitle("Purchase Order: " + poID);
        dlg.setModal(true);
        dlg.setSize(600, 400);
        dlg.setLocationRelativeTo(null);
        dlg.setLayout(new BorderLayout());

        // header
        JPanel header = new JPanel(new GridLayout(3,2));
        header.setBorder(BorderFactory.createTitledBorder("PO Details"));
        header.add(new JLabel("PO ID:"));     header.add(new JLabel(poID));
        header.add(new JLabel("Date:"));      header.add(new JLabel(date));
        header.add(new JLabel("Status:"));    header.add(new JLabel(status));

        // table
        JTable tbl = new JTable(data, cols);
        JScrollPane scroll = new JScrollPane(tbl);

        // close button
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dlg.dispose());
        JPanel pnlBtn = new JPanel();
        pnlBtn.add(btnClose);

        // assemble
        dlg.add(header, BorderLayout.NORTH);
        dlg.add(scroll, BorderLayout.CENTER);
        dlg.add(pnlBtn, BorderLayout.SOUTH);
        dlg.setVisible(true);
    }

    /** look up itemName in your raw Items.txt loaded by ItemManager */
    private static String findItemName(String itemID) {
        for (List<String> row : itemsList) {
            if (row.get(0).equalsIgnoreCase(itemID)) {
                // row = [itemID, itemName, stockLevel]
                return row.get(1);
            }
        }
        return "Unknown";
    }
}