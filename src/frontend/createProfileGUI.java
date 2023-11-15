package frontend;

import backend.database.Garage;
import backend.database.Schedule;
import backend.database.userProfile;
import frontend.UserDashboard;
//import backend.database.parkingStructure;
//import javafx.application.Platform;
//import javafx.scene.Scene;
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class createProfileGUI extends JFrame{
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JComboBox<String> permitBox;
    private JComboBox<String> roleBox;
    private JTextField vmText;
    private JTextField vmoText;
    private JTextField vyText;

    public createProfileGUI() {
        // Frame setup
        setTitle("Create Profile");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel headerPanel = PDMPanels.createUserHeader("PDM - Please Create An Account");
        headerPanel.setPreferredSize(new Dimension(250,60));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createUserFooter();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);




        // Header Panel
       // JPanel headerPanel = new JPanel();
       // headerPanel.setOpaque(false);
        //headerPanel.setLayout(new BorderLayout());
        //backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        //headerPanel.setBackground(new Color(113, 100, 217, 242));
        //headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));


        // Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 250));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;


        contentPanel.setOpaque(false);

        //first name
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Your First Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        firstNameText = new JTextField(15);
        firstNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(firstNameText, gbc);

        //Last Name
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Your Last Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        lastNameText = new JTextField(15);
        lastNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(lastNameText, gbc);

        //Role?
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("User Category:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] roleOptions = {"Commuter", "Resident", "Faculty"};
        roleBox = new JComboBox<>(roleOptions);
        roleBox.setFont(new Font("Monospaced", Font.BOLD, 16));
        contentPanel.add(roleBox, gbc);

        //Parking Pass
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Parking Pass Type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        String[] permitOptions = {"Fall 2023", "Spring 2024", "Fall 2024"};
        permitBox = new JComboBox<>(permitOptions);
        permitBox.setFont(new Font("Monospaced", Font.BOLD, 16));
        contentPanel.add(permitBox, gbc);

        //Vehicle make
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(new JLabel("Vehicle Make:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        vmText = new JTextField(15);
        vmText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(vmText, gbc);

        //Vehicle Model
        gbc.gridx = 2;
        gbc.gridy = 4;
        contentPanel.add(new JLabel("Vehicle Model:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        vmoText = new JTextField(15);
        vmoText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(vmoText, gbc);

        //Vehicle Year
        gbc.gridx = 4;
        gbc.gridy = 4;
        contentPanel.add(new JLabel("Vehicle Year:"), gbc);

        gbc.gridx = 5;
        gbc.gridy = 4;
        vyText = new JTextField(6);
        vyText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(vyText, gbc);



        gbc.gridx = 2;
        gbc.gridy = 5;
        JButton saveButton = new JButton("Create An Account");
        saveButton.setBounds(15, 80, 80, 25);
        //saveButton.setBackground(Color.lightGray);
        PDMPanels.styleButton(saveButton);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });
        contentPanel.add(saveButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        JButton cancelButton = new JButton("Cancel");
        //cancelButton.setBounds(20, 80, 40, 40);
        PDMPanels.styleButton(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });

        contentPanel.add(cancelButton, gbc);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

       setContentPane(mainPanel);
       setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createProfileGUI profileGUI = new createProfileGUI();
            profileGUI.setVisible(true);
        });
    }
}