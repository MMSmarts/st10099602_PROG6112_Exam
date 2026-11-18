package com.prog6112.q2;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** Swing form for collecting mattress sales and saving their calculations. */
public class ProductApp extends JFrame {
    private final JTextField customerNameField = new JTextField(20);
    private final JComboBox<String> productBox = new JComboBox<>(new String[] {
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final JTextField priceField = new JTextField(20);

    /** Build the inputs, Process button and required menu items. */
    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 280);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> dispose());
        fileMenu.add(exitItem);

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

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addField(form, gbc, 0, "Customer Name:", customerNameField);
        addField(form, gbc, 1, "Selected Product:", productBox);
        addField(form, gbc, 2, "Enter Price:", priceField);
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton processButton = new JButton("PROCESS");
        processButton.addActionListener(e -> processSale());
        form.add(processButton, gbc);
        add(form, BorderLayout.CENTER);
    }

    /** Position one label and input in a row of the form. */
    private void addField(JPanel panel, GridBagConstraints gbc, int row,
                          String label, java.awt.Component input) {
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(input, gbc);
    }

    /** Validate the form, calculate sale values and write the report to data.txt. */
    private void processSale() {
        String customerName = customerNameField.getText().trim();
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer name.");
            return;
        }
        double price;
        // Reject missing, non-numeric, negative and non-finite prices.
        try {
            price = Double.parseDouble(priceField.getText().trim());
            if (!Double.isFinite(price) || price < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid product price.");
            return;
        }

        ProductCalculations calculation = new ProductCalculations(price);
        String result = "Customer Name: " + customerName
                + "\nProduct: " + productBox.getSelectedItem()
                + "\nVAT: R " + calculation.GetVat()
                + "\nCOMMISSION: R " + calculation.GetCommission()
                + "\nTOTAL: R " + calculation.GetTotal();
        String message = result.replace("\n", System.lineSeparator());

        // Display the calculated report and save it to data.txt.
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
        String log = "DATA LOG" + System.lineSeparator()
                + "*********************************************" + System.lineSeparator()
                + message + System.lineSeparator()
                + "*********************************************" + System.lineSeparator();
        // Handle file errors so the application stays open if saving fails.
        try {
            Files.writeString(Path.of("data.txt"), log, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save data.txt: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Reset both text boxes and select the first product again. */
    private void clearForm() {
        customerNameField.setText("");
        priceField.setText("");
        productBox.setSelectedIndex(0);
        customerNameField.requestFocusInWindow();
    }

    /** Start the user interface on Swing’s event-dispatch thread. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
