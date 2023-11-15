package frontend;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    JPanel newPanel;
    JLabel pdmLabel, madeEasyLabel, userLabel, passLabel, pageLabel;
    final JTextField textField1;
    final JPasswordField textField2;

    public Login() {
        // Page Title Label
        pageLabel = new JLabel("PDM Login");
        pageLabel.setFont(new Font("Monospaced", Font.BOLD, 24));


        ImageIcon userIcon = new ImageIcon("resources/user.png");
        Image userImage = userIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel userImageLabel = new JLabel(new ImageIcon(userImage));


        textField1 = new JTextField(20);
        setPlaceholder(textField1, "Username");

        ImageIcon passIcon = new ImageIcon("resources/password1.png");
        Image passImage = passIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        JLabel passImageLabel = new JLabel(new ImageIcon(passImage));

        textField2 = new JPasswordField(20);
        setPlaceholder(textField2, "Password");

        b1 = new JButton("Submit");
        b1.setFont(new Font("Monospaced", Font.BOLD, 16));

        b2 = new JButton("Register");
        b2.setFont(new Font("Monospaced", Font.BOLD, 16));

        b3 = new JButton("Continue as Guest");
        b3.setFont(new Font("Monospaced", Font.BOLD, 16));

        newPanel = new JPanel(new BorderLayout());

        JPanel headingPanel = PDMPanels.GeneralHeader("PDM Login");


        JLabel welcomeLabel = new JLabel("Parking Made Easy");
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBackground(Color.lightGray);
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 20)));
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



        JPanel buttonPanel = new JPanel();
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);

        componentsPanel.add(buttonPanel);

        newPanel.add(headingPanel, BorderLayout.NORTH);
        newPanel.add(componentsPanel, BorderLayout.PAGE_END);

        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Create and add the footer using PageLayout
        JPanel footerPanel = PDMPanels.GeneralFooter();
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
        if (ae.getSource() == b1) {
            String userValue = textField1.getText();
            char[] passValue = textField2.getPassword();

            if (authenticateUser(userValue, new String(passValue))) {
                this.dispose();

                // Check if the username contains "Admin"
                if (userValue.toLowerCase().contains("admin")) {
                    AdminHomePage adminHomePage = new AdminHomePage();
                    adminHomePage.setVisible(true);
                } else if (userValue.toLowerCase().contains("user")) {
                    createProfileGUI createProfile = new createProfileGUI();
                    createProfile.setVisible(true);
                } else {
                    // Handle other user roles or scenarios
                    // You can add more checks or create different dashboards for different roles.
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }


        } else if (ae.getSource() == b2) {
            this.dispose();
            Reg registration = new Reg();
            registration.setVisible(true);
        }
        else if (ae.getSource()==b3) {
            this.dispose();
            GuestDashboard guest = new GuestDashboard();
                    guest.setVisible(true);
        }
    }

    private boolean authenticateUser(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/backend/database/user_data.txt"));
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

    public static void parseTextFile(){
        String inputFileName = "src/backend/database/user_data.txt";
        String outputFileName = "src/backend/database/userDataToPush.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String firstPart = parts[0];
                    String secondPart = parts[1];
                    // Enclose both parts with single quotes and handle shorter lines.
                    String modifiedLine = "'" + firstPart + "','" + secondPart + "'";
                    if (line.length() > firstPart.length() + secondPart.length() + 1) {
                        modifiedLine += line.substring(firstPart.length() + secondPart.length() + 1);
                    }
                    writer.write(modifiedLine + "\n");
                }
            }

            reader.close();
            writer.close();

            System.out.println("Parsing completed. Modified data written to " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pushLogins(){
        parseTextFile();
        String connectionString = "jdbc:sqlserver://10.20.30.1;encrypt=true;trustServerCertificate=true;database=Logins;";
    /*replace the 192.168.0.170 with your IPv4 address. You can get this by opening a terminal and running "ipconfig"
    scroll down until you see the "Wireless LAN adapter Wi-Fi:". Use the IPv4 address from there. You will also need to
    enable SQL Server Authentication and enable the sa account https://www.fosstechnix.com/how-to-enable-sa-account-in-sql-server/
    make sure the sa password is "admin"
    /*/
        String user = "sa";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
            System.out.println("Connection Established");
            try (BufferedReader br = new BufferedReader(new FileReader("src/backend/database/userDataToPush.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    StringBuilder sql2 = new StringBuilder("INSERT INTO Logins ");
                    sql2.append("(username, ");
                    sql2.append("password)");
                    sql2.append("VALUES(");
                    sql2.append(line);
                    int result = line.lastIndexOf(",");
                    //System.out.println("result is: " + result + " last character is: " + line.charAt(result) + " and length is: " + line.length());
                    if (line.length() == result + 1) {
                        sql2.append("'none')");
                    } else {
                        sql2.append(")");
                    }
                    System.out.println(sql2.toString());

                    Statement stmt2 = connection.createStatement();
                    stmt2.executeUpdate(sql2.toString());
                }
                System.out.println("Uploaded to DB successfully.");

            }
        } catch (SQLException | IOException e) {
            System.out.println("Error connecting to the db");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
