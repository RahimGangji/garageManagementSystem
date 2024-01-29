import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class viewProduct extends JFrame {
    private JTextArea productTextArea;

    viewProduct() {
        setVisible(true);
        setTitle("Product Dashboard");
        setSize(900, 500);
        setLayout(new BorderLayout());

        // JLabel at the top
        JLabel welcomeLabel = new JLabel("Welcome to Product List");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // JTextArea in the center with JScrollPane
        productTextArea = new JTextArea();
        productTextArea.setEditable(false);
        productTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(productTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Back button at the bottom
        JButton back = new JButton("Back");
        back.setBounds(300, 150, 250, 50);
        back.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setFocusable(false);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button action (e.g., go back to the previous screen)
                dispose(); // Close the current window
                // You can open another window or perform any other action here
            }
        });
        add(back, BorderLayout.SOUTH);

        readAndDisplayProducts();
    }

    private void readAndDisplayProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into fields (assuming space-separated format)
                String[] fields = line.split(" ");
                if (fields.length >= 3) {
                    // Append product information to the JTextArea
                    productTextArea.append(
                            "ID: " + fields[0] +
                                    " | Name: " + fields[1] +
                                    " | Price: " + fields[2] +
                                    " | Quantity: " + (fields.length > 3 ? fields[3] : "N/A") + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
