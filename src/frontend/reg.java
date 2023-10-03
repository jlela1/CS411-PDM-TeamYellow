package frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

class Reg extends JFrame implements ActionListener {

    JButton b3, b4;
    JPanel newPanel;
    JLabel userLabel, passLabel, passlabel2, pagelabel;
    final JTextField textField1, textField2, textField3;


    Reg() {

  /* /*
        pagelabel = new JLabel();
        pagelabel.setText("Account Registration");

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

        passlabel2 = new JLabel();
        passlabel2.setText("Confirm Password");
        textField3 = new JPasswordField(10);

        /* /*Submit Button* /// */
        b3 = new JButton("Submit");

        b4 = new JButton("Back");


        /* /*New Panel* /// */
        newPanel = new JPanel();

        //newPanel.ass(pageLabel);//

        newPanel.add(userLabel);
        newPanel.add(textField1);

        newPanel.add(passLabel);
        newPanel.add(textField2);

        newPanel.add(passlabel2);
        newPanel.add(textField3);

        newPanel.add(b3);
        newPanel.add(b4);
        add(newPanel, BorderLayout.CENTER);
        b3.addActionListener(this);
        b4.addActionListener(this);

        setTitle("Account Registration");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();

        if (userValue.equals("test@gmail.com") && passValue.equals("test")) {
            NewPage page = new NewPage();
            page.setVisible(true);
            JLabel wel_label = new JLabel("Welcome: " + userValue);
            page.getContentPane().add(wel_label);

        } else {

            System.out.println("Please enter valid username and password");
        }

    }
}

    class RegDemo{
        public static void main (String arg[]){

            try {
            Reg form = new Reg();
            form.setSize(400,400);
            form.setVisible(true);
            }

            catch(Exception e) {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }