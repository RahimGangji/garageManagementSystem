import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class addCustomer extends JFrame implements ActionListener {
    JLabel addProductLabel, errorLabel, resultLabel;
    JTextField idField, nameField, quantityField;
    JButton submitButton, backButton;
    LinkedList<String> existingIDs;
    LinkedList<String> existingName;
    LinkedList<String> existingprice;
    LinkedList<String> existingQuantity;
    String Price;

    addCustomer() {
        setVisible(true);
        setTitle("Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        addProductLabel = new JLabel("Add Customer");
        addProductLabel.setBounds(250, 50, 400, 50); // Centered at the top
        addProductLabel.setFont(new Font("Anton", Font.BOLD, 35));
        addProductLabel.setForeground(Color.black);
        add(addProductLabel);

        JLabel idLabel = new JLabel("Item ID:");
        idLabel.setBounds(100, 200, 100, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 200, 200, 30);
        add(idField);

        JLabel nameLabel = new JLabel("customer Name:");
        nameLabel.setBounds(100, 240, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 240, 200, 30);
        add(nameField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(100, 280, 100, 30);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(200, 280, 200, 30);
        add(quantityField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 380, 100, 30);
        submitButton.addActionListener(this);
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.black);
        submitButton.setFocusable(false);
        add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(300, 380, 100, 30);
        backButton.addActionListener(this);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.black);
        backButton.setFocusable(false);
        add(backButton);

        errorLabel = new JLabel("All fields must be filled!");
        errorLabel.setBounds(200, 430, 300, 30);
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        add(errorLabel);

        resultLabel = new JLabel("");
        resultLabel.setBounds(200, 460, 500, 30);
        resultLabel.setForeground(Color.black);
        resultLabel.setVisible(false);
        add(resultLabel);

        existingIDs = new LinkedList<>();
        existingName = new LinkedList<>();
        existingQuantity = new LinkedList<>();
        existingprice = new LinkedList<>();

        loadExistingIDs();
    }

    private boolean isAlphabetic(String str) {
        return str.matches("^[a-zA-Z]+$");
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void loadExistingIDs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(" ");
                if (fields.length > 0) {
                    existingIDs.add(fields[0]);
                    existingName.add(fields[1]);
                    existingQuantity.add(fields[3]);
                    existingprice.add(fields[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void FindData() {
        String inputQuantity = quantityField.getText();

        if (!isNumeric(inputQuantity)) {
            errorLabel.setText("Enter a valid numeric Quantity!");
            errorLabel.setVisible(true);
            return;
        }

        int inputQuantityValue = Integer.parseInt(inputQuantity);
        boolean found = false;

        for (int i = 0; i < existingIDs.size(); i++) {
            if (existingIDs.get(i).equals(idField.getText())) {
                int existingQuantityValue = Integer.parseInt(existingQuantity.get(i));

                if (existingQuantityValue >= inputQuantityValue) {
                    int quantityDifference = existingQuantityValue - inputQuantityValue;
                    existingQuantity.set(i, Integer.toString(quantityDifference));
                    found = true;
                    Price = existingprice.get(i);
                }

            }

        }

        if (!found) {
            errorLabel.setText("Quantity you have entered is not available right now");
            errorLabel.setVisible(true);
        } else {

            writeToProductsFile();
            writeToFile(idField.getText(), nameField.getText(), Price, quantityField.getText());
            System.out.println("Data updated and written to 'products.txt'");
        }
        resultLabel.setVisible(true);
    }

    private void writeToFile(String id, String name, String price, String quantity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true))) {
            // Append the data to the file in the specified format
            int PriceFormat = Integer.parseInt(price);
            int Quantity = Integer.parseInt(quantity);
            writer.write(id + " " + name + " " + PriceFormat * Quantity + " " + quantity);
            writer.newLine(); // Add a new line for the next entry
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeToProductsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (int i = 0; i < existingIDs.size(); i++) {
                String line = existingIDs.get(i) + " " + existingName.get(i) + " " +
                        existingprice.get(i) + " " + existingQuantity.get(i);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitButton)) {
            if (idField.getText().isEmpty() || !isNumeric(idField.getText())
                    || !existingIDs.contains(idField.getText())) {
                errorLabel.setText("Enter a valid ID!");
                errorLabel.setVisible(true);
            } else if (nameField.getText().isEmpty() || !isAlphabetic(nameField.getText())) {
                errorLabel.setText("Enter a valid Name!");
                errorLabel.setVisible(true);
            } else if (quantityField.getText().isEmpty() || !isNumeric(quantityField.getText())) {
                errorLabel.setText("Enter a valid numeric Quantity!");
                errorLabel.setVisible(true);
            } else {
                errorLabel.setVisible(false);
                FindData();
            }
        } else if (e.getSource().equals(backButton)) {
            new CustomerDashboard();
            dispose();
        }
    }

}
