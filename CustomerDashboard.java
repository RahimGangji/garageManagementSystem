import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CustomerDashboard extends JFrame implements ActionListener {
    JButton addCustomer, back, viewCustomer;
    JLabel welcomeDashboardTeacher, error;

    CustomerDashboard() {
        setVisible(true);
        setTitle("Product Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        welcomeDashboardTeacher = new JLabel("Welcome To Customer Dashboard");
        welcomeDashboardTeacher.setBounds(180, 0, 600, 200);
        welcomeDashboardTeacher.setFont(new Font("Anton", Font.BOLD, 35));
        welcomeDashboardTeacher.setForeground(Color.black);
        add(welcomeDashboardTeacher);

        addCustomer = new JButton("Add Customer");
        addCustomer.setBounds(40, 150, 250, 50);
        addCustomer.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        addCustomer.setForeground(Color.white);
        addCustomer.setBackground(Color.black);
        addCustomer.setFocusable(false);
        add(addCustomer);

        back = new JButton("Back");
        back.setBounds(300, 150, 250, 50);
        back.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setFocusable(false);
        add(back);

        viewCustomer = new JButton("Customers");
        viewCustomer.setBounds(560, 150, 250, 50);
        viewCustomer.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        viewCustomer.setForeground(Color.white);
        viewCustomer.setBackground(Color.black);
        viewCustomer.setFocusable(false);
        add(viewCustomer);

        addCustomer.addActionListener(this);
        back.addActionListener(this);
        viewCustomer.addActionListener(this);

        error = new JLabel();
        error.setBounds(250, 400, 400, 30); // Set the bounds of the error label
        error.setForeground(Color.RED);
        error.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        add(error);
    }

    private boolean isCustomerAvailable() {
        File file = new File("customers.txt");
        return file.exists() && file.length() > 0;
    }

    private boolean isProductsFileAvailable() {
        File file = new File("products.txt");
        return file.exists() && file.length() > 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addCustomer)) {
            // Handle result button action
            if (isProductsFileAvailable()) {
                new addCustomer();
                dispose();
            } else {
                error.setText("Products file not available or empty.");
            }
        } else if (e.getSource().equals(back)) {
            new Dashboard();
            dispose();
        } else if (e.getSource().equals(viewCustomer)) {
            if (isCustomerAvailable()) {
                new viewCustomer();
                dispose();
            } else {
                error.setText("Customer file not available or empty.");
            }
        }
    }
}
