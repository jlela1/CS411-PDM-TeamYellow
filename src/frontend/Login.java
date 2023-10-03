package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
 class Login extends JFrame implements ActionListener {

    JButton b1, b2;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, pagelabel;
    final JTextField textField1, textField2;

    Login() {

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
        b1.setFont(new Font("Roboto", Font.PLAIN, 16));

        b2 = new JButton("Register");
        b2.setFont(new Font("Roboto", Font.PLAIN, 16));

        /* New Panel */
        newPanel = new JPanel(new BorderLayout());


        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(Color.LIGHT_GRAY);

        JPanel pdmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pdmLabel = new JLabel("PDM");
        pdmLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        pdmPanel.setBackground(Color.LIGHT_GRAY);
        pdmLabel.setForeground(Color.DARK_GRAY);
        pdmPanel.add(pdmLabel);

        JPanel madeEasyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        madeEasyLabel = new JLabel("Parking Made Easy");
        madeEasyLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        madeEasyLabel.setForeground(Color.GRAY);
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
         String userValue = textField1.getText();
         String passValue = textField2.getText();

         if (userValue.equals("test@gmail.com") && passValue.equals("test")) {
             NewPage page = new NewPage();
             page.setVisible(true);
             JLabel wel_label = new JLabel("Welcome: "+userValue);
             wel_label.setFont(new Font("Roboto", Font.PLAIN, 16));
             page.getContentPane().add(wel_label, BorderLayout.CENTER);

         }

         else {

             JOptionPane.showMessageDialog(this,"Please enter valid username and password");
         }
    }


 }


class LoginDemo {
    public static void main (String arg[]){
        try {
            Login form = new Login();
            //form.setSize(400,400);
            form.setVisible(true);
            //form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
            //form.setLocationRelativeTo(null); // Center the window on the screen
        }

        catch(Exception e) {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}