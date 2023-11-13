package frontend;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.text.JTextComponent;
import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import backend.database.Schedule;
import backend.database.userProfile;
import frontend.Login;

class Reg extends JFrame implements ActionListener {


    JButton registerButton, backButton;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, confirmPassLabel, logoLabel;
    final JTextField textField1;
    final JPasswordField textField2, textField3;


    public Reg() {
        setTitle("PDM Registration");


        // Username
        ImageIcon userIcon = new ImageIcon("resources/user.png");
        Image userImage = userIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel userImageLabel = new JLabel(new ImageIcon(userImage));
        textField1 = new JTextField(20);


        // Password
        ImageIcon passIcon = new ImageIcon("resources/password1.png");
        Image passImage = passIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel passImageLabel = new JLabel(new ImageIcon(passImage));
        textField2 = new JPasswordField(20);


        // Confirm Password
        ImageIcon confirmPassIcon = new ImageIcon("resources/confirmed.png");
        Image confirmImage = confirmPassIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel confirmPassImageLabel = new JLabel(new ImageIcon(confirmImage));
        textField3 = new JPasswordField(20);


        // Register and Back buttons
        registerButton = new JButton("Register");
        PDMPanels.styleButton(registerButton);


        backButton = new JButton("Login Page");
        PDMPanels.styleButton(backButton);


        // New Panel
        newPanel = new JPanel(new BorderLayout());


        JPanel headingPanel = PDMPanels.GeneralHeader("PDM Registration");


        JLabel welcomeLabel = new JLabel("Parking Made Easy");
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBackground(Color.lightGray);
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        headingPanel.add(welcomeLabel);


        JPanel componentsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);


        // Set placeholders and text color for the text fields
        setPlaceholder(textField1, "Username");
        setPlaceholder(textField2, "Password");
        setPlaceholder(textField3, "Confirm Password");


        gbc.gridx = 0;
        gbc.gridy = 1;
        componentsPanel.add(userImageLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        componentsPanel.add(textField1, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        componentsPanel.add(passImageLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        componentsPanel.add(textField2, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        componentsPanel.add(confirmPassImageLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        componentsPanel.add(textField3, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(registerButton);
        buttonsPanel.add(backButton);
        componentsPanel.add(buttonsPanel, gbc);


        newPanel.add(headingPanel, BorderLayout.NORTH);
        newPanel.add(componentsPanel, BorderLayout.CENTER);


        add(newPanel, BorderLayout.CENTER);


        registerButton.addActionListener(this);
        backButton.addActionListener(this);


        // Create and add the footer using PageLayout
        JPanel footerPanel = PDMPanels.GeneralFooter();
        add(footerPanel, BorderLayout.SOUTH);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        if (ae.getSource() == registerButton) { // Submit button clicked for registration
            String username = textField1.getText();
            String password = new String(textField2.getPassword());




            // Check if the username and password are not empty
            if (!username.isEmpty() && !password.isEmpty()) {
                // Check if the username contains "admin" or "user"
                if (username.toLowerCase().contains("admin") || username.toLowerCase().contains("user")) {
                    // Save the registration data to a file
                    try {
                        String filePath = "src\\backend\\database\\user_data.txt";
                        FileWriter writer = new FileWriter(filePath, true);
                        writer.write(username + "," + password + "\n");
                        writer.close();
                        Login obj = new Login(); // creating object of login class to push the updated data to database
                        obj.pushLogins();;
                        // Show registration successful message
                        JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Close the registration window and open the login window
                        this.dispose(); // Close the registration window
                        Login login = new Login(); // Open the login window
                        login.setVisible(true);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "Error saving registration data.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Login, please add \"admin\" or \"user\" to your username.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == backButton) { // Back button clicked
            // Go back to the login page
            this.dispose(); // Close the registration window
            Login login = new Login(); // Open the login window
            login.setVisible(true);
        }
           /* removeAll();
            dispose();
            createProfileGUI createProfile = new createProfileGUI();
            createProfile.setVisible(true); //Points to create Profile -Bryan*/
        }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reg registration = new Reg();
            registration.setVisible(true);
        });
    }
}
