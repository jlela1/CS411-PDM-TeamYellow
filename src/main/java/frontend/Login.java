package frontend;

import javax.swing.*;
import java.awt.*;

import javax.swing.text.JTextComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {

    JButton b1, b2;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, pageLabel;
    final JTextField textField1;
    final JPasswordField textField2;

    public Login() {

       // Page Title Label
        pageLabel = new JLabel("PDM Login");
        pageLabel.setFont(new Font ("Roboto", Font.BOLD, 24));

        ImageIcon userIcon = new ImageIcon("resources/user.png"); // Provide the path to your image
        Image userImage = userIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Set the size as needed
        JLabel userImageLabel = new JLabel(new ImageIcon(userImage));

        textField1 = new JTextField (20);
        setPlaceholder(textField1, "Username");

        ImageIcon passIcon = new ImageIcon("resources/password1.png"); // Provide the path to your password image
        Image passImage = passIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Set the size as needed
        JLabel passImageLabel = new JLabel(new ImageIcon(passImage));

        textField2 = new JPasswordField(20);
        setPlaceholder(textField2, "Password");

        b1 = new JButton("Submit");
        b1.setFont(new Font("Roboto", Font.BOLD, 16));

        b2 = new JButton("Register");
        b2.setFont(new Font("Roboto", Font.BOLD, 16));

        newPanel = new JPanel(new BorderLayout());

        JPanel headingPanel = PDMPanels.createHeader("PDM Login");

        JLabel welcomeLabel = new JLabel("Parking Made Easy");
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBackground(Color.lightGray);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        headingPanel.add(welcomeLabel);

        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new GridLayout(7, 1, 5, 5));

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userPanel.add(userImageLabel);
        userPanel.add(textField1);

        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passPanel.add(passImageLabel);
        passPanel.add(textField2);

        componentsPanel.add(userPanel);
        componentsPanel.add(passPanel);

        JPanel buttonPanel = new JPanel(); // Create a separate panel for buttons
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        componentsPanel.add(buttonPanel);

        newPanel.add(headingPanel, BorderLayout.NORTH);
        newPanel.add(componentsPanel, BorderLayout.PAGE_END);

        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        // Create and add the footer using PageLayout
        JPanel footerPanel = PDMPanels.createFooter();
        add(footerPanel, BorderLayout.SOUTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("PDM Login");
    }

    private void setPlaceholder(JTextComponent component, String placeholder) {
        component.setForeground(Color.GRAY);
        component.setText(placeholder);
        component.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (component.getForeground() == Color.GRAY) {
                    component.setText("");
                    component.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (component.getText().trim().isEmpty()) {
                    component.setText(placeholder);
                    component.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // Submit button clicked
            String userValue = textField1.getText();
            char[] passValue = textField2.getPassword();

            if (authenticateUser(userValue, new String(passValue))) {
                this.dispose();
                // Replace with your logic to open the appropriate page
                // Example: AdminHomePage adminHomePage = new AdminHomePage();
                // adminHomePage.setVisible(true);
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
            BufferedReader reader = new BufferedReader(new FileReader("src\\main\\java\\backend\\database\\user_data.txt"));
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
