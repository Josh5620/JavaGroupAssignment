package shared_manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FinancialReport extends JPanel {

    public FinancialReport(List<List<String>> stockData, List<List<String>> itemsList, String filterDate) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Financial Report for " + filterDate, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Item ID", "Item Name", "Date", "Quantity Sold", "Unit Price", "Total Revenue"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        double grandTotal = 0.0;

        for (List<String> sale : stockData) {
            String itemID = sale.get(0);
            String date = sale.get(2).trim();

            if (!date.equals(filterDate)) continue;

            int quantitySold = Integer.parseInt(sale.get(1));
            String itemName = "Unknown";
            double unitPrice = 0.0;

            for (List<String> item : itemsList) {
                if (item.get(0).equals(itemID)) {
                    itemName = item.get(1);
                    unitPrice = Double.parseDouble(item.get(2));
                    break;
                }
            }

            double totalRevenue = quantitySold * unitPrice;
            grandTotal += totalRevenue;

            model.addRow(new Object[]{
                itemID,
                itemName,
                date,
                quantitySold,
                String.format("RM %.2f", unitPrice),
                String.format("RM %.2f", totalRevenue)
            });
        }

        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Transaction Breakdown"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);

        JLabel totalRevenueLabel = new JLabel("Total Revenue: RM " + String.format("%.2f", grandTotal));
        totalRevenueLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalRevenueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalRevenueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        closeButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(FinancialReport.this);
            if (window != null) window.dispose();
        });

        JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.setBackground(Color.WHITE);
        closePanel.add(closeButton);

        bottomPanel.add(totalRevenueLabel, BorderLayout.CENTER);
        bottomPanel.add(closePanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void showReport(List<List<String>> stockData, List<List<String>> itemsList, String filterDate) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Financial Report Viewer");
        dialog.setSize(850, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        FinancialReport reportPanel = new FinancialReport(stockData, itemsList, filterDate);
        dialog.add(reportPanel);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        List<List<String>> stockData = Arrays.asList(
            Arrays.asList("ITM001", "20", "2025-05-10"),
            Arrays.asList("ITM002", "15", "2025-05-09"),
            Arrays.asList("ITM003", "10", "2025-05-08"),
            Arrays.asList("ITM004", "25", "2025-05-11"),
            Arrays.asList("ITM005", "30", "2025-05-07"),
            Arrays.asList("ITM006", "12", "2025-05-10"),
            Arrays.asList("ITM007", "18", "2025-05-11"),
            Arrays.asList("ITM008", "22", "2025-05-09"),
            Arrays.asList("ITM009", "16", "2025-05-10"),
            Arrays.asList("ITM010", "19", "2025-05-08")
        );

        List<List<String>> itemsList = Arrays.asList(
            Arrays.asList("ITM001", "Rice", "3.20", "SUP001"),
            Arrays.asList("ITM002", "Onions", "2.50", "SUP002"),
            Arrays.asList("ITM003", "Tomatoes", "3.80", "SUP002"),
            Arrays.asList("ITM004", "Sugar", "4.60", "SUP001"),
            Arrays.asList("ITM005", "Salt", "2.70", "SUP003"),
            Arrays.asList("ITM006", "Milk", "7.20", "SUP002"),
            Arrays.asList("ITM007", "Carrots", "2.30", "SUP003"),
            Arrays.asList("ITM008", "Cabbage", "2.90", "SUP004"),
            Arrays.asList("ITM009", "CookingOil", "5.50", "SUP004"),
            Arrays.asList("ITM010", "Flour", "3.10", "SUP001")
        );

        showReport(stockData, itemsList, "2025-05-10");
    }
}
