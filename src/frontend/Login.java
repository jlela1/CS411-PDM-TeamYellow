package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
 class Login extends JFrame implements ActionListener {

    JButton b1, b2;
    JPanel newPanel;
    JLabel userLabel, passLabel, pagelabel;
    final JTextField textField1, textField2;

    Login() {

  /* /*
        pagelabel = new JLabel();
        pagelabel.setText("PDM Login");

   */

        /* /*Username* /// */
        userLabel = new JLabel();
        userLabel.setText("Username");

        /* /*Username length* /// */
        textField1 = new JTextField(10);

        /* /*Password* /// */
        passLabel = new JLabel();
        passLabel.setText("Password");

        /* /*Password length* /// */
        textField2 = new JPasswordField(10);

        /* /*Submit Button* /// */
        b1 = new JButton("Submit");

        b2 = new JButton("Register");

        /* /*New Panel* /// */
        newPanel = new JPanel();

       //newPanel.ass(pageLabel);//

        newPanel.add(userLabel);
        newPanel.add(textField1);

        newPanel.add(passLabel);
        newPanel.add(textField2);

        newPanel.add(b1);
        newPanel.add(b2);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);
        b2.addActionListener(this);

        setTitle("PDM Login");
    }

    public void actionPerformed(ActionEvent ae) {
         String userValue = textField1.getText();
         String passValue = textField2.getText();

         if (userValue.equals("test@gmail.com") && passValue.equals("test")) {
             NewPage page = new NewPage();
             page.setVisible(true);
             JLabel wel_label = new JLabel("Welcome: "+userValue);
             page.getContentPane().add(wel_label);

         }

         else {

             System.out.println("Please enter valid username and password");
         }
    }

 }









class LoginDemo {
    public static void main (String arg[]){
        try {
            Login form = new Login();
            form.setSize(400,400);
            form.setVisible(true);
        }

        catch(Exception e) {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}