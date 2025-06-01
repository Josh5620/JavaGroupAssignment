package shared_model;

import java.awt.*;
import java.awt.print.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class PurchaseOrder extends JPanel {

    private String poID;
    private String date;
    private List<String> itemIDs;
    private List<Integer> quantities;
    private String status;
    private String approvedBy;
    private String resolution;
    private double total;
    private static final List<List<String>> itemList = new ArrayList<>();
    private static final String filePath = "src/Items.txt";

    public PurchaseOrder(List<String> lineParts) {
        this.poID = lineParts.get(0);
        this.date = lineParts.get(1);
        this.itemIDs = Arrays.asList(lineParts.get(2).split("/"));

        this.quantities = new ArrayList<>();
        for (String qty : lineParts.get(3).split("/")) {
            this.quantities.add(Integer.parseInt(qty));
        }

        this.status = lineParts.get(4);
        this.approvedBy = lineParts.get(5);
        this.resolution = lineParts.get(6);

        if (itemList.isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    itemList.add(Arrays.asList(parts));
                }
            } catch (Exception e) {
                System.err.println("Error loading item file: " + e.getMessage());
            }
        }
    }

    public String getPoID() { return poID; }
    public List<String> getItemIDs() { return itemIDs; }
    public List<Integer> getQuantities() { return quantities; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getApprovedBy() { return approvedBy; }
    public String getResolution() { return resolution; }
    public double getTotal() { return total; }
    public String toString() {
    String itemIDsStr = String.join("/", itemIDs);
    String quantitiesStr = quantities.stream()
        .map(String::valueOf)
        .collect(Collectors.joining("/"));

    return String.join("|",
        poID,
        date,
        itemIDsStr,
        quantitiesStr,
        status,
        approvedBy,
        resolution
    );
}

    public void showPO() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Purchase Order: " + poID);
        dialog.setModal(true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(3, 2));
        headerPanel.setBorder(BorderFactory.createTitledBorder("Purchase Order Details"));
        headerPanel.add(new JLabel("POID:"));
        headerPanel.add(new JLabel(poID));
        headerPanel.add(new JLabel("Date:"));
        headerPanel.add(new JLabel(date));
        headerPanel.add(new JLabel("Status:"));
        headerPanel.add(new JLabel(status));

        String[] columns = { "Item ID", "Quantity", "Unit Price", "SupplierID", "Total" };
        Object[][] data = new Object[itemIDs.size()][5];

        for (int i = 0; i < itemIDs.size(); i++) {
            String id = itemIDs.get(i);
            int qty = quantities.get(i);
            String price = "0.00";
            String supplier = "Unknown";

            for (List<String> item : itemList) {
                if (item.get(0).equals(id)) {
                    price = item.get(2);
                    supplier = item.get(3);
                    break;
                }
            }

            double total = qty * Double.parseDouble(price);
            data[i][0] = id;
            data[i][1] = qty;
            data[i][2] = "RM" + String.format("%.2f", Double.parseDouble(price));
            data[i][3] = supplier;
            data[i][4] = "RM" + String.format("%.2f", total);
        }

        JTable table = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(table);

        JPanel approvalPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        approvalPanel.setBorder(BorderFactory.createTitledBorder("Approval Summary"));

        int totalQty = 0;
        double totalPrice = 0.0;
        for (int i = 0; i < itemIDs.size(); i++) {
            int qty = quantities.get(i);
            String id = itemIDs.get(i);
            double price = 0.0;

            for (List<String> item : itemList) {
                if (item.get(0).equals(id)) {
                    price = Double.parseDouble(item.get(2));
                    break;
                }
            }

            totalQty += qty;
            totalPrice += qty * price;
        }

        JLabel totalQtyLabel = new JLabel("Total Quantity: " + totalQty);
        totalQtyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel totalPriceLabel = new JLabel("Total Price: RM" + String.format("%.2f", totalPrice));
        total = totalPrice;
        totalPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        approvalPanel.add(totalQtyLabel);
        approvalPanel.add(totalPriceLabel);

        // === Buttons ===
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        closeButton.addActionListener(e -> dialog.dispose());

        JButton printButton = new JButton("Print");
        printButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        printButton.addActionListener(e -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Preview Purchase Order");

            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                // Dummy page to enable preview popup
                if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
                return Printable.PAGE_EXISTS;
            });

            job.printDialog(); // ← Just show the print preview dialog
        });

        

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);

        // === Add to Dialog ===
        dialog.add(headerPanel, BorderLayout.NORTH);
        dialog.add(tableScroll, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(approvalPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(southPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
