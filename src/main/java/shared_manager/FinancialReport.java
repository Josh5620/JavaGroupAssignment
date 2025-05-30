package shared_manager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FinancialReport extends JPanel {

    public FinancialReport(List<List<String>> stockData, List<List<String>> itemsList, String filterDate, Runnable onClose) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Financial Report for " + filterDate, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));
        add(titleLabel, BorderLayout.NORTH);

        // Table Setup
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

        // Bottom Panel for Total + Close
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);

        JLabel totalRevenueLabel = new JLabel("Total Revenue: RM " + String.format("%.2f", grandTotal));
        totalRevenueLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalRevenueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalRevenueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        closeButton.addActionListener(e -> {
            if (onClose != null) onClose.run();
        });

        JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.setBackground(Color.WHITE);
        closePanel.add(closeButton);

        bottomPanel.add(totalRevenueLabel, BorderLayout.CENTER);
        bottomPanel.add(closePanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }


    
    public static void main(String[] args) {
        JDialog dialog = new JDialog();
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
    Arrays.asList("ITM010", "19", "2025-05-08"),
    Arrays.asList("ITM011", "14", "2025-05-09"),
    Arrays.asList("ITM012", "11", "2025-05-11"),
    Arrays.asList("ITM013", "20", "2025-05-10"),
    Arrays.asList("ITM014", "17", "2025-05-07"),
    Arrays.asList("ITM015", "13", "2025-05-07"),
    Arrays.asList("ITM016", "15", "2025-05-10"),
    Arrays.asList("ITM017", "10", "2025-05-11"),
    Arrays.asList("ITM018", "28", "2025-05-08"),
    Arrays.asList("ITM019", "18", "2025-05-10"),
    Arrays.asList("ITM020", "21", "2025-05-11"),
    Arrays.asList("ITM001", "12", "2025-05-09"),
    Arrays.asList("ITM004", "22", "2025-05-10"),
    Arrays.asList("ITM009", "19", "2025-05-08"),
    Arrays.asList("ITM007", "15", "2025-05-10"),
    Arrays.asList("ITM003", "24", "2025-05-11")
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
    Arrays.asList("ITM010", "Flour", "3.10", "SUP001"),
    Arrays.asList("ITM011", "Potatoes", "4.20", "SUP002"),
    Arrays.asList("ITM012", "Apples", "5.00", "SUP003"),
    Arrays.asList("ITM013", "Bananas", "4.40", "SUP003"),
    Arrays.asList("ITM014", "Chicken", "9.90", "SUP001"),
    Arrays.asList("ITM015", "Spinach", "3.60", "SUP004"),
    Arrays.asList("ITM016", "Bread", "3.80", "SUP002"),
    Arrays.asList("ITM017", "Fish", "11.90", "SUP004"),
    Arrays.asList("ITM018", "Eggs", "0.50", "SUP001"),
    Arrays.asList("ITM019", "Garlic", "6.80", "SUP003"),
    Arrays.asList("ITM020", "Lettuce", "3.00", "SUP002")
);
dialog.setTitle("Financial Report Viewer");
dialog.setSize(800, 500);
dialog.setLocationRelativeTo(null);

FinancialReport panel = new FinancialReport(stockData, itemsList, "2025-05-10", dialog::dispose);
dialog.add(panel);
dialog.setModal(true);
dialog.setVisible(true);
    }
}
