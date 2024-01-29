import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class AddProduct extends JFrame implements ActionListener {
    JLabel addProductLabel, errorLabel;
    JTextField idField, nameField, priceField,quantityField;
    JButton submitButton, backButton;
    LinkedList<String> existingIDs;

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabetic(String str) {
        return str.matches("^[a-zA-Z]+$");
    }

    AddProduct() {
        setVisible(true);
        setTitle("Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        addProductLabel = new JLabel("Add Product");
        addProductLabel.setBounds(250, 50, 400, 50); // Centered at the top
        addProductLabel.setFont(new Font("Anton", Font.BOLD, 35));
        addProductLabel.setForeground(Color.black);
        add(addProductLabel);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 200, 100, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 200, 200, 30);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 240, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 240, 200, 30);
        add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(100, 280, 100, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(200, 280, 200, 30);
        add(priceField);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(100, 320, 100, 30);
        add(quantityLabel);
        quantityField= new JTextField();
        quantityField.setBounds(200, 320, 200, 30);
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
        existingIDs = new LinkedList<>();
        loadExistingIDs();
    }

    private void loadExistingIDs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into fields (assuming space-separated format)
                String[] fields = line.split(" ");
                if (fields.length > 0) {
                    existingIDs.add(fields[0]); // Add the ID to the linked list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions here
        if (e.getSource().equals(backButton)) {
            // Handle result button action
            new ProductDashboard();
            dispose();

        } else if (e.getSource().equals(submitButton)) {
            if (idField.getText().isEmpty()) {
                errorLabel.setText("Your ID Field is Empty!");
                errorLabel.setVisible(true);
            } else if (!isNumeric(idField.getText())) {
                errorLabel.setText("Enter a valid numeric ID!");
                errorLabel.setVisible(true);
            } else if (existingIDs.contains(idField.getText())) {
                errorLabel.setText("ID already taken!");
                errorLabel.setVisible(true);
            } else if (nameField.getText().isEmpty()) {
                errorLabel.setText(" Your Name Field is Empty! ");
                errorLabel.setVisible(true);
            } else if (!isAlphabetic(nameField.getText())) {
                errorLabel.setText("Enter a valid alphabetic Name!");
                errorLabel.setVisible(true);
            } else if (priceField.getText().isEmpty()) {
                errorLabel.setText("Your Price Field is Empty!");
                errorLabel.setVisible(true);
            } else if (!isNumeric(priceField.getText())) {
                errorLabel.setText("Enter a valid numeric Price!");
                errorLabel.setVisible(true);
            } else if(!isNumeric(quantityField.getText())){
                errorLabel.setText("Enter a valid numeric Quantity! ");
                errorLabel.setVisible(true);
            }
             else {
                // If all conditions are met, write data to the file
                writeToFile(idField.getText(), nameField.getText(), priceField.getText(),quantityField.getText());
                existingIDs.add(idField.getText());
                // Optionally, you can clear the fields or perform any other action
                idField.setText("");
                nameField.setText("");
                priceField.setText("");
                quantityField.setText("");
                // Hide the error label
                errorLabel.setVisible(false);

                System.out.println(existingIDs);
            }
        }
    }

    private void writeToFile(String id, String name, String price,String quantity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt", true))) {
            // Append the data to the file in the specified format
            writer.write(id + " " + name + " " + price + " " +quantity);
            writer.newLine(); // Add a new line for the next entry
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
