package InventoryManager;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockReport extends JPanel {
    private List<List<String>> inventory;
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JLabel titleLabel;
    private JScrollPane scrollPane;
    
    // Constructor
    public StockReport() {
        this.inventory = new ArrayList<>();
        initializeComponents();
        setupLayout();
    }
    
    // Constructor with inventory data
    public StockReport(List<List<String>> inventory) {
        this.inventory = inventory;
        initializeComponents();
        setupLayout();
        populateTable();
    }
    
    // Initialize components
    private void initializeComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 400));
        
        // Title label
        titleLabel = new JLabel("Stock Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create table model with column names
        String[] columnNames = {"ID", "Name", "Quantity", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        stockTable = new JTable(tableModel);
        stockTable.setRowHeight(25);
        stockTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        stockTable.setFont(new Font("Arial", Font.BOLD, 12));
        
        stockTable.setDefaultRenderer(Object.class, new StockCellRenderer());
        
        scrollPane = new JScrollPane(stockTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inventory Items"));
    }
    
    // Setup layout
    private void setupLayout() {
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add legend panel
        JPanel legendPanel = createLegendPanel();
        add(legendPanel, BorderLayout.SOUTH);
    }
    
    // Create legend panel showing color meanings
    private JPanel createLegendPanel() {
        JPanel legendPanel = new JPanel(new FlowLayout());
        legendPanel.setBorder(BorderFactory.createTitledBorder("Stock Level Legend"));
        
        // Red label for critical stock
        JLabel criticalLabel = new JLabel("Critical (<20)");
        criticalLabel.setOpaque(true);
        criticalLabel.setBackground(Color.RED);
        criticalLabel.setForeground(Color.WHITE);
        criticalLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Yellow label for low stock
        JLabel lowLabel = new JLabel("Low (20-49)");
        lowLabel.setOpaque(true);
        lowLabel.setBackground(Color.YELLOW);
        lowLabel.setForeground(Color.BLACK);
        lowLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Green label for good stock
        JLabel goodLabel = new JLabel("Good (50+)");
        goodLabel.setOpaque(true);
        goodLabel.setBackground(Color.GREEN);
        goodLabel.setForeground(Color.WHITE);
        goodLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        legendPanel.add(criticalLabel);
        legendPanel.add(Box.createHorizontalStrut(10));
        legendPanel.add(lowLabel);
        legendPanel.add(Box.createHorizontalStrut(10));
        legendPanel.add(goodLabel);
        
        return legendPanel;
    }
    
    // Populate table with inventory data
    private void populateTable() {
        tableModel.setRowCount(0); // Clear existing rows
        
        if (inventory != null) {
            for (List<String> item : inventory) {
                if (item.size() >= 3) {
                    String id = item.get(0);
                    String name = item.get(1);
                    int quantity = Integer.parseInt(item.get(2));
                    String status = getStockStatus(quantity);
                    
                    tableModel.addRow(new Object[]{id, name, quantity, status});
                }
            }
        }
    }
    
    // Determine stock status based on quantity
    private String getStockStatus(int quantity) {
        if (quantity < 20) {
            return "Critical";
        } else if (quantity < 50) {
            return "Low";
        } else {
            return "Good";
        }
    }
    
    // Get color based on quantity
    private Color getStockColor(int quantity) {
        if (quantity < 20) {
            return Color.RED;
        } else if (quantity < 50) {
            return Color.YELLOW;
        } else {
            return Color.GREEN;
        }
    }
    
    // Custom cell renderer for color coding rows
    private class StockCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component component = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            
            // Set bold font for all cells
            component.setFont(new Font("Arial", Font.BOLD, 12));
            
            if (!isSelected) {
                // Get quantity from the table model
                int quantity = (Integer) table.getValueAt(row, 2);
                Color backgroundColor = getStockColor(quantity);
                
                component.setBackground(backgroundColor);
                component.setForeground(Color.BLACK);

            }
            
            return component;
        }
    }
    
    // Main method to create and display the stock report popup
    public void createStockReportPopup() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Stock Report");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(this);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }
    
    // Static method to create popup with inventory data
    public static void showStockReport(List<List<String>> inventory) {
        StockReport stockReport = new StockReport(inventory);
        stockReport.createStockReportPopup();
    }
    
    // Getters and Setters
    public List<List<String>> getInventory() {
        return inventory;
    }
    
    public void setInventory(List<List<String>> inventory) {
        this.inventory = inventory;
        populateTable();
        repaint();
    }
    
    public JTable getStockTable() {
        return stockTable;
    }
    
    public void setStockTable(JTable stockTable) {
        this.stockTable = stockTable;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public JLabel getTitleLabel() {
        return titleLabel;
    }
    
    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }
    
    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }
    
    // Method to add single item to inventory
    public void addInventoryItem(String id, String name, String quantity) {
        List<String> newItem = Arrays.asList(id, name, quantity);
        inventory.add(newItem);
        populateTable();
    }
    
    // Method to remove item by ID
    public void removeInventoryItem(String id) {
        inventory.removeIf(item -> item.get(0).equals(id));
        populateTable();
    }
    
    // Method to get inventory count by status
    public int getItemCountByStatus(String status) {
        int count = 0;
        for (List<String> item : inventory) {
            int quantity = Integer.parseInt(item.get(2));
            if (getStockStatus(quantity).equals(status)) {
                count++;
            }
        }
        return count;
    }
}