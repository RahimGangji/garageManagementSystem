import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class montlySale extends JFrame {
    private LinkedList<String> idList = new LinkedList<>();
    private LinkedList<String> customerList = new LinkedList<>();
    JLabel addProductLabel, customerField, productField;

    montlySale() {
        setVisible(true);
        setTitle("Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        addProductLabel = new JLabel("Stocks Update");
        addProductLabel.setBounds(250, 50, 400, 50); // Centered at the top
        addProductLabel.setFont(new Font("Anton", Font.BOLD, 35));
        addProductLabel.setForeground(Color.black);
        add(addProductLabel);
        JLabel idLabel = new JLabel("Total Customers:");
        idLabel.setBounds(100, 200, 100, 30);
        add(idLabel);
        customerField = new JLabel();
        customerField.setBounds(200, 200, 200, 30);
        add(customerField);
        JLabel nameLabel = new JLabel("Total Products:");
        nameLabel.setBounds(100, 240, 100, 30);
        add(nameLabel);
        productField = new JLabel();
        productField.setBounds(200, 240, 200, 30);
        add(productField);
        if (isCustomerAvailable()) {

            try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(" ");
                    if (fields.length > 0) {
                        idList.add(fields[0]);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            int idListSize = idList.size();
            customerField.setText(idListSize + "");

        } else {
            customerField.setText("Not Available");
        }

        if (isProductsFileAvailable()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(" ");
                    if (fields.length > 0) {
                        customerList.add(fields[0]);

                    }
                }
                int productListSize=customerList.size();
                productField.setText(productListSize+"");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            productField.setText("Not Found");
        }
    }

    private boolean isCustomerAvailable() {
        File file = new File("customers.txt");
        return file.exists() && file.length() > 0;
    }

    private boolean isProductsFileAvailable() {
        File file = new File("products.txt");
        return file.exists() && file.length() > 0;
    }
}
