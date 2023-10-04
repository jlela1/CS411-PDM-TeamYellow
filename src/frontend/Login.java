package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 public class Login extends JFrame implements ActionListener {

    JButton b1, b2;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, pagelabel;
    final JTextField textField1;
    final JPasswordField textField2;

    public Login() {

       // Page Title Label
        pagelabel = new JLabel("PDM Login");
        pagelabel.setFont(new Font ("Roboto", Font.BOLD, 24));
/*
        // PDM Heading
        pdmLabel = new JLabel("PDM");
        pdmLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        pdmLabel.setForeground(Color.DARK_GRAY);
        pdmLabel.setHorizontalAlignment(JLabel.CENTER);

        // Sub Heading
        madeEasyLabel = new JLabel("Parking Made Easy");
        madeEasyLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        madeEasyLabel.setForeground(Color.DARK_GRAY);
        madeEasyLabel.setHorizontalAlignment(JLabel.CENTER);

*/
        /* /*Username* /// */
        userLabel = new JLabel();
        userLabel.setText("Username:");
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        /* /*Username length* /// */
        textField1 = new JTextField (15);


        /* /*Password* /// */
        passLabel = new JLabel();
        passLabel.setText("Password:");
        passLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        /* /*Password length* /// */
        textField2 = new JPasswordField(15);


        /* /*Submit Button* /// */
        b1 = new JButton("Submit");
        b1.setFont(new Font("Roboto", Font.BOLD, 16));

        b2 = new JButton("Register");
        b2.setFont(new Font("Roboto", Font.BOLD, 16));

        /* New Panel */
        newPanel = new JPanel(new BorderLayout());


        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(Color.LIGHT_GRAY);

        JPanel pdmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pdmLabel = new JLabel("PDM Login");
        pdmLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        pdmPanel.setBackground(Color.LIGHT_GRAY);
        pdmLabel.setForeground(Color.DARK_GRAY);
        pdmPanel.add(pdmLabel);

        JPanel madeEasyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        madeEasyLabel = new JLabel("Parking Made Easy");
        madeEasyLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
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
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        componentsPanel.add(buttonPanel);

        newPanel.add(headingPanel, BorderLayout.NORTH);
        newPanel.add(componentsPanel, BorderLayout.PAGE_END);

        add(newPanel, BorderLayout.CENTER);


        b1.addActionListener(this);
        b2.addActionListener(this);

        //Create a PDM footer
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Roboto", Font.ITALIC, 15));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(122, 114, 114, 173));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setTitle("PDM Login");

    }

     public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == b1) { // Submit button clicked
             // Check the login logic here
             String userValue = textField1.getText();
             char[] passValue = textField2.getPassword();

             // Replace this logic with your actual login logic
             if (userValue.equals("test@gmail.com") && new String(passValue).equals("test")) {
                 // Successful login, perform action here (e.g., open a new page)
                 this.dispose();
                 AdminHomePage adminHomePage = new AdminHomePage();
                 adminHomePage.setVisible(true);
             } else {
                 JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
             }
         } else if (ae.getSource() == b2) { // Register button clicked
             // Open the registration page
             this.dispose(); // Close the login window
             Reg registration = new Reg(); // Open the registration window
             registration.setVisible(true);
         }
     }
 }