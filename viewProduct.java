import javax.swing.*;
import java.util.LinkedList;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewProduct extends JFrame {
    private JTextArea productTextArea;
    private LinkedList<Integer> idList;
    private LinkedList<String> nameList;
    private LinkedList<Double> priceList;
    private LinkedList<Integer> quantityList;

    public viewProduct() {
        initializeUI();
        readDataFromFile();
        displayDataInTextArea();
    }

    private void initializeUI() {
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
        back.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductDashboard();
                dispose();
            }
        });
        add(back, BorderLayout.SOUTH);
    }

    private void readDataFromFile() {
        idList = new LinkedList<>();
        nameList = new LinkedList<>();
        priceList = new LinkedList<>();
        quantityList = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+"); // Split by whitespace
                if (data.length == 4) { // Ensure there are four values
                    idList.add(Integer.parseInt(data[0]));
                    nameList.add(data[1]);
                    priceList.add(Double.parseDouble(data[2]));
                    quantityList.add(Integer.parseInt(data[3]));
                } else {
                    // Handle invalid lines in the file
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void displayDataInTextArea() {
        for (int i = 0; i < idList.size(); i++) {
            productTextArea.append("ID: " + idList.get(i) + "\n");
            productTextArea.append("Name: " + nameList.get(i) + "\n");
            productTextArea.append("Price: " + priceList.get(i) + "\n");
            productTextArea.append("Quantity: " + quantityList.get(i) + "\n\n");
        }
    }

}
