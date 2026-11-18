package matressapp;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class SalesForm extends JFrame {

    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;

    private JButton btnProcess;
    private JButton btnClear;

    // Constructor
    public SalesForm() {

        setTitle("Mattress Sales Application");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Customer Name
        JLabel lblCustomer = new JLabel("Customer Name:");
        lblCustomer.setBounds(40, 40, 130, 25);
        panel.add(lblCustomer);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(180, 40, 230, 25);
        panel.add(txtCustomerName);

        // Product Selection
        JLabel lblProduct = new JLabel("Select Product:");
        lblProduct.setBounds(40, 85, 130, 25);
        panel.add(lblProduct);

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        cmbProduct = new JComboBox<>(products);
        cmbProduct.setBounds(180, 85, 230, 25);
        panel.add(cmbProduct);

        // Product Price
        JLabel lblPrice = new JLabel("Enter Price:");
        lblPrice.setBounds(40, 130, 130, 25);
        panel.add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(180, 130, 230, 25);
        panel.add(txtPrice);

        // Process Button
        btnProcess = new JButton("PROCESS");
        btnProcess.setBounds(100, 190, 120, 35);
        panel.add(btnProcess);

        // Clear Button
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(250, 190, 120, 35);
        panel.add(btnClear);

        // Process button action
        btnProcess.addActionListener(e -> processSale());

        // Clear button action
        btnClear.addActionListener(e -> clearForm());

        // Create Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);

        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        add(panel);

    }

    // Process Sale Method
    private void processSale() {

        String customerName = txtCustomerName.getText().trim();

        String product = (String) cmbProduct.getSelectedItem();

        String priceText = txtPrice.getText().trim();

        // Validate customer name
        if (customerName.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter the customer name."
            );

            return;
        }

        // Validate product price
        double price;

        try {

            price = Double.parseDouble(priceText);

            if (!Double.isFinite(price) || price <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price greater than zero."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric price."
            );

            return;
        }

        // Create calculation object
        ProductCalculations calculation =
                new ProductCalculations(price);

        // Calculate VAT, Commission and Total
        double vat = calculation.GetVat();

        double commission = calculation.GetCommission();

        double total = calculation.GetTotal();

        // Display sales information
        String message =
                "Customer Name: " + customerName +
                "\nProduct: " + product +
                "\nVAT: R " + vat +
                "\nCOMMISSION: R " + commission +
                "\nTOTAL: R " + total;

        JOptionPane.showMessageDialog(
            this,
            message,
            "Sales Information",
            JOptionPane.INFORMATION_MESSAGE
        );

        // Save information to data.txt
        saveToFile(customerName, product, vat, commission, total);

    }

    // Save information to file
    private void saveToFile(
            String customerName,
            String product,
            double vat,
            double commission,
            double total) {

        try (FileWriter writer = new FileWriter("data.txt", true)) {

            writer.write("DATA LOG\n");
            writer.write("******************************************\n");

            writer.write("Customer Name: " + customerName + "\n");

            writer.write("Product: " + product + "\n");

            writer.write("VAT: R " + vat + "\n");

            writer.write("COMMISSION: R " + commission + "\n");

            writer.write("TOTAL: R " + total + "\n");

            writer.write("******************************************\n\n");

            JOptionPane.showMessageDialog(
                this,
                "Sales information saved successfully!"
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                this,
                "Error saving information: " + e.getMessage()
            );

        }

    }

    // Clear Form Method
    private void clearForm() {

        txtCustomerName.setText("");

        txtPrice.setText("");

        cmbProduct.setSelectedIndex(0);

    }

}