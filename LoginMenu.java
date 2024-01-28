
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginMenu extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5;
    JTextField t1, t2;
    JButton b1;

    LoginMenu() {
        setVisible(true);
        setTitle("Admin Panel");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        l1 = new JLabel("Welcome, Please Login");
        l1.setBounds(550, 20, 400, 100);
        l1.setFont(new Font("Josefin Sans", Font.BOLD, 25));
        l1.setForeground(Color.black);
        add(l1);
        // ImageIcon imageIcon = new ImageIcon("rahim.png");
        // l2 = new JLabel(imageIcon);
        // l2.setBounds(0, 50, 400, 400);
        // add(l2);
        t1 = new JTextField();
        t1.setBounds(620, 120, 200, 20);
        add(t1);
        t2 = new JTextField();
        t2.setBounds(620, 180, 200, 20);
        add(t2);
        l3 = new JLabel("Username:");
        l3.setBounds(520, 80, 400, 100);
        l3.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        l3.setForeground(Color.black);
        add(l3);
        l4 = new JLabel("Password:");
        l4.setBounds(520, 140, 400, 100);
        l4.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        l4.setForeground(Color.black);
        add(l4);
        b1 = new JButton("SIGN IN");
        b1.setBounds(650, 240, 150, 50);
        b1.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        b1.setForeground(Color.white);
        b1.setBackground(Color.black);
        b1.setFocusable(false);
        add(b1);
        b1.addActionListener(this);
        l5 = new JLabel();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String id = t1.getText();
            String pass = t2.getText();

            if (t1.getText().equals("admin") && t2.getText().equals("admin")) {
                new Dashboard();

                dispose();
            } else if (id.isEmpty()) {
                // l5 is a JLabel which would be set to "Please enter a valid username" if the
                // id is empty
                l5.setText("Please enter a valid username");
                l5.setBounds(610, 101, 400, 100);
                l5.setFont(new Font("Josefin Sans", Font.BOLD, 15));
                l5.setForeground(Color.red);
                add(l5);
            } else if (pass.isEmpty()) {
                // l5 is a JLabel which would be set to ""Please enter a valid password" if the
                // password is empty
                l5.setText("Please enter a valid password");
                l5.setBounds(610, 160, 400, 100);
                l5.setFont(new Font("Josefin Sans", Font.BOLD, 15));
                l5.setForeground(Color.red);
                add(l5);
            } else {
                // l5 is a JLabel which would be set to "Incorrect Username or Password" if the
                // id or password is incorrect
                l5.setText("Incorrect Username or Password");
                l5.setBounds(600, 260, 400, 100);
                l5.setFont(new Font("Josefin Sans", Font.BOLD, 15));
                l5.setForeground(Color.red);
                add(l5);
                add(l5);

            }
        }
    }
}
