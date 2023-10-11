package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.text.JTextComponent;
import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;

class Reg extends JFrame implements ActionListener {

    JButton registerButton, backButton;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, confirmPassLabel, logoLabel;
    final JTextField textField1;
    final JPasswordField textField2, textField3;


    public Reg() {

        setTitle("PDM Registration");

        //ImageIcon oduLogo = new ImageIcon("CS411-PDM-TeamYellow/resources/ODULogo.png");
        //Image Logo = oduLogo.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        //JLabel logoLabel = new JLabel(new ImageIcon(Logo));

        //Username
        ImageIcon userIcon = new ImageIcon("CS411-PDM-TeamYellow/resources/user.png");
        Image userImage = userIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel userImageLabel = new JLabel(new ImageIcon(userImage));
        textField1 = new JTextField(20);

        //Password
        ImageIcon passIcon = new ImageIcon("CS411-PDM-TeamYellow/resources/password1.png");
        Image passImage = passIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel passImageLabel = new JLabel(new ImageIcon(passImage));
        textField2 = new JPasswordField(20);

        //Confirm Password
        ImageIcon confirmPassIcon = new ImageIcon("CS411-PDM-TeamYellow/resources/confirmed.png");
        Image confirmImage = confirmPassIcon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT);
        JLabel confirmPassLabel = new JLabel(new ImageIcon(confirmImage));
        //confirmPassLabel = new JLabel("Confirm Password:");
       // confirmPassLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        textField3 = new JPasswordField(20);

        // Register and Back buttons

        registerButton = new JButton("Register");
        PDMPanels.styleButton(registerButton);

        backButton = new JButton("Login Page");
        PDMPanels.styleButton(backButton);


        //New Panel
        newPanel = new JPanel(new BorderLayout());

        JPanel headingPanel = PDMPanels.createHeader("PDM Registration");

        JLabel welcomeLabel = new JLabel("Parking Made Easy");
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBackground(Color.lightGray);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        headingPanel.add(welcomeLabel);

        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new GridLayout(4, 1, 3,3 ));

        //Set placeholders and text color for the text fields
        setPlaceholder(textField1, "Username");
        setPlaceholder(textField2, "Password");
        setPlaceholder(textField3, "Password");

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userPanel.add(userImageLabel);
        userPanel.add(textField1);

        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passPanel.add(passImageLabel);
        passPanel.add(textField2);

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmPanel.add(confirmPassLabel);
        confirmPanel.add(textField3);

        componentsPanel.add(userPanel);
        componentsPanel.add(passPanel);
        componentsPanel.add(confirmPanel);

        JPanel buttonPanel = new JPanel(); // Create a separate panel for buttons
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        componentsPanel.add(buttonPanel);

        //JPanel logoPanel = new JPanel();
        //logoPanel.setLayout(new BorderLayout ());
        //logoPanel.add(logoLabel);


        newPanel.add(headingPanel, BorderLayout.NORTH);
        //newPanel.add(logoPanel, BorderLayout.PAGE_END);
        newPanel.add(componentsPanel, BorderLayout.CENTER);

        add(newPanel, BorderLayout.CENTER);

        registerButton.addActionListener(this);
        backButton.addActionListener(this);

        // Create and add the footer using PageLayout
        JPanel footerPanel = PDMPanels.createFooter();
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
        } else if (ae.getSource() == backButton) { // Back button clicked
            // Go back to the login page
            this.dispose(); // Close the registration window
            Login login = new Login(); // Open the login window
            login.setVisible(true);
        }
    }
}
