package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {

    JButton b1, b2;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, pagelabel;
    final JTextField textField1;
    final JPasswordField textField2;

    public Login() {
        pagelabel = new JLabel("PDM Login");
        pagelabel.setFont(new Font("Roboto", Font.BOLD, 24));

        userLabel = new JLabel();
        userLabel.setText("Username:");
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        textField1 = new JTextField(15);

        passLabel = new JLabel();
        passLabel.setText("Password:");
        passLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        textField2 = new JPasswordField(15);

        b1 = new JButton("Submit");
        b1.setFont(new Font("Roboto", Font.BOLD, 16));

        b2 = new JButton("Register");
        b2.setFont(new Font("Roboto", Font.BOLD, 16));

        newPanel = new JPanel(new BorderLayout());

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(Color.LIGHT_GRAY);

        JPanel pdmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pdmLabel = new JLabel("PDM Login");
        pdmLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        pdmLabel.setForeground(Color.DARK_GRAY);
        pdmPanel.setBackground(Color.LIGHT_GRAY);
        pdmPanel.add(pdmLabel);

        headingPanel.add(pdmPanel, BorderLayout.NORTH);

        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new GridLayout(7, 1, 5, 5));

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userPanel.add(userLabel);
        userPanel.add(textField1);

        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passPanel.add(passLabel);
        passPanel.add(textField2);

        componentsPanel.add(userPanel);
        componentsPanel.add(passPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        componentsPanel.add(buttonPanel);

        newPanel.add(headingPanel, BorderLayout.NORTH);
        newPanel.add(componentsPanel, BorderLayout.PAGE_END);

        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        // Create a PDM footer
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Roboto", Font.ITALIC, 15));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(122, 114, 114, 173));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setTitle("PDM Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // Submit button clicked
            String userValue = textField1.getText();
            char[] passValue = textField2.getPassword();

            if (authenticateUser(userValue, new String(passValue))) {
                this.dispose();
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == b2) { // Register button clicked
            this.dispose();
            Reg registration = new Reg();
            registration.setVisible(true);
        }
    }

    private boolean authenticateUser(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
