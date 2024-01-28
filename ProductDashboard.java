import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDashboard extends JFrame implements ActionListener {
    JButton addProduct, back, viewProduct;
    JLabel welcomeDashboardTeacher, error;

    public ProductDashboard() {
        setVisible(true);
        setTitle("Product Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        welcomeDashboardTeacher = new JLabel("Welcome To Product Dashboard");
        welcomeDashboardTeacher.setBounds(180, 0, 600, 200);
        welcomeDashboardTeacher.setFont(new Font("Anton", Font.BOLD, 35));
        welcomeDashboardTeacher.setForeground(Color.black);
        add(welcomeDashboardTeacher);

        addProduct = new JButton("Add Product");
        addProduct.setBounds(40, 150, 250, 50);
        addProduct.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        addProduct.setForeground(Color.white);
        addProduct.setBackground(Color.black);
        addProduct.setFocusable(false);
        add(addProduct);

        back = new JButton("Back");
        back.setBounds(300, 150, 250, 50);
        back.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setFocusable(false);
        add(back);

        viewProduct = new JButton("View Product");
        viewProduct.setBounds(560, 150, 250, 50);
        viewProduct.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        viewProduct.setForeground(Color.white);
        viewProduct.setBackground(Color.black);
        viewProduct.setFocusable(false);
        add(viewProduct);

        addProduct.addActionListener(this);
        back.addActionListener(this);
        viewProduct.addActionListener(this);

        error = new JLabel();
        error.setBounds(250, 400, 400, 30); // Set the bounds of the error label
        error.setForeground(Color.RED);
        error.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        add(error);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addProduct)) {
            // Handle result button action

        } else if (e.getSource().equals(back)) {
            new Dashboard();
            dispose();
        } else if (e.getSource().equals(viewProduct)) {
            // new OopPaperSet();
            dispose();
        }
    }

}
