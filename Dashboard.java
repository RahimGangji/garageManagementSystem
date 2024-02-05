import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Dashboard extends JFrame implements ActionListener {
    JButton monthlySale, back, customber, product;
    JLabel welcomeDashboardTeacher, error;

    public Dashboard() {
        setVisible(true);
        setTitle("Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        welcomeDashboardTeacher = new JLabel("Welcome To Admin Dashboard");
        welcomeDashboardTeacher.setBounds(180, 0, 600, 200);
        welcomeDashboardTeacher.setFont(new Font("Anton", Font.BOLD, 35));
        welcomeDashboardTeacher.setForeground(Color.black);
        add(welcomeDashboardTeacher);

        monthlySale = new JButton("Monthly Sale");
        monthlySale.setBounds(40, 150, 250, 50);
        monthlySale.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        monthlySale.setForeground(Color.white);
        monthlySale.setBackground(Color.black);
        monthlySale.setFocusable(false);
        add(monthlySale);

        back = new JButton("Back");
        back.setBounds(300, 150, 250, 50);
        back.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setFocusable(false);
        add(back);

        customber = new JButton("Customer");
        customber.setBounds(560, 150, 250, 50);
        customber.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        customber.setForeground(Color.white);
        customber.setBackground(Color.black);
        customber.setFocusable(false);
        add(customber);

        product = new JButton("Product");
        product.setBounds(40, 250, 250, 50);
        product.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        product.setForeground(Color.white);
        product.setBackground(Color.black);
        product.setFocusable(false);
        add(product);

        monthlySale.addActionListener(this);
        back.addActionListener(this);
        customber.addActionListener(this);
        product.addActionListener(this);

        error = new JLabel();
        error.setBounds(250, 400, 400, 30); // Set the bounds of the error label
        error.setForeground(Color.RED);
        error.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        add(error);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(monthlySale)) {
            // Handle result button action

        } else if (e.getSource().equals(back)) {
            new LoginMenu();
            dispose();
        } else if (e.getSource().equals(customber)) {
            new CustomerDashboard();
            dispose();
        } else if (e.getSource().equals(product)) {
            new ProductDashboard();
            dispose();
        }
    }

}
