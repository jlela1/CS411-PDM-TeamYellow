package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

class Reg extends JFrame implements ActionListener {

    JButton b3, b4;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, confirmPassLabel, pagelabel;
    final JTextField textField1;
    final JPasswordField textField2, textField3;

    Reg() {
        /* Username */
        userLabel = new JLabel();
        userLabel.setText("Username");
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        /* Username length */
        textField1 = new JTextField(15);

        /* Password */
        passLabel = new JLabel();
        passLabel.setText("Password");
        passLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        /* Password length */
        textField2 = new JPasswordField(15);

        confirmPassLabel = new JLabel();
        confirmPassLabel.setText("Confirm Password");
        confirmPassLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        textField3 = new JPasswordField(15);

        /* Submit Button */
        b3 = new JButton("Submit");
        b3.setFont(new Font("Roboto", Font.BOLD, 16));

        b4 = new JButton("Back to Login Page");
        b4.setFont(new Font("Roboto", Font.BOLD, 16));

        /* New Panel */
        newPanel = new JPanel(new BorderLayout());

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(Color.LIGHT_GRAY);

        JPanel pdmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pdmLabel = new JLabel("PDM Registration");
        pdmLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        pdmLabel.setForeground(Color.DARK_GRAY);
        pdmPanel.setBackground(Color.LIGHT_GRAY);
        pdmPanel.add(pdmLabel);

        JPanel madeEasyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        madeEasyLabel = new JLabel("Parking Made Easy");
        madeEasyLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        madeEasyLabel.setForeground(Color.DARK_GRAY);
        madeEasyPanel.setBackground(Color.lightGray);
        madeEasyPanel.add(madeEasyLabel);

        headingPanel.add(pdmPanel, BorderLayout.NORTH);
        headingPanel.add(madeEasyPanel, BorderLayout.CENTER);

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

        JPanel buttonPanel = new JPanel(); // Create a separate panel for buttons
        buttonPanel.add(b3);
        buttonPanel.add(b4);

        componentsPanel.add(buttonPanel);

        newPanel.add(headingPanel, BorderLayout.NORTH);
        newPanel.add(componentsPanel, BorderLayout.PAGE_END);

        add(newPanel, BorderLayout.CENTER);

        b3.addActionListener(this);
        b4.addActionListener(this);

        // PDM Footer
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Roboto", Font.ITALIC, 15));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(122, 114, 114, 173));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b3) { // Submit button clicked for registration
            String username = textField1.getText();
            String password = new String(textField2.getPassword());

            // Check if the username and password are not empty
            if (!username.isEmpty() && !password.isEmpty()) {
                // Save the registration data to a file
                try {
                    FileWriter writer = new FileWriter("user_data.txt", true);
                    writer.write(username + "," + password + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error saving registration data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == b4) { // Back button clicked
            // Go back to the login page
            this.dispose(); // Close the registration window
            Login login = new Login(); // Open the login window
            login.setVisible(true);
        }
    }
}
