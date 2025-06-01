package shared_model;
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
    
    public StockReport() {
        this.inventory = new ArrayList<>();
        initializeComponents();
        setupLayout();
    }
    
    public StockReport(List<List<String>> inventory) {
        this.inventory = inventory;
        initializeComponents();
        setupLayout();
        populateTable();
    }
    
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
    
    private void setupLayout() {
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add legend panel
        JPanel legendPanel = createLegendPanel();
        add(legendPanel, BorderLayout.SOUTH);
    }
    
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
    
    private String getStockStatus(int quantity) {
        if (quantity < 20) {
            return "Critical";
        } else if (quantity < 50) {
            return "Low";
        } else {
            return "Good";
        }
    }
    
    private Color getStockColor(int quantity) {
        if (quantity < 20) {
            return Color.RED;
        } else if (quantity < 50) {
            return Color.YELLOW;
        } else {
            return Color.GREEN;
        }
    }
    
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
      
}