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


    public createProfileGUI() {
        // Frame setup
        setTitle("Create Profile");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("resources/color.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };



        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        //headerPanel.setBackground(new Color(113, 100, 217, 242));
        //headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Create Profile");
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headingLabel.setForeground(Color.DARK_GRAY);
        headingLabel.setBackground(Color.lightGray);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headingLabel, BorderLayout.NORTH);


        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

        //JPanel mainPanel = new JPanel();
        //mainPanel.setOpaque(false);
        //mainPanel.setLayout(new GridLayout(4, 1));
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        contentPanel.add(namePanel);

        JPanel permitPanel = new JPanel();
        permitPanel.setOpaque(false);
        contentPanel.add(permitPanel);

        JPanel rolePanel = new JPanel();
        rolePanel.setOpaque(false);
        contentPanel.add(rolePanel);

        JPanel savePanel = new JPanel();
        savePanel.setOpaque(false);
        contentPanel.add(savePanel);


        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Roboto",Font.BOLD,16));
        nameLabel.setForeground(Color.white);
        namePanel.add(nameLabel);

        JTextField firstNameText = new JTextField(9);
        firstNameText.setPreferredSize(new Dimension(50,50));
        firstNameText.setFont(new Font("Roboto",Font.PLAIN,16));
        namePanel.add(firstNameText);

        JTextField lastNameText = new JTextField(9);
        lastNameText.setPreferredSize(new Dimension(50,50));
        lastNameText.setFont(new Font("Roboto",Font.PLAIN,16));
        namePanel.add(lastNameText);

        JLabel permitLabel = new JLabel("Permit:");
        permitLabel.setFont(new Font("Roboto",Font.BOLD,16));
        permitLabel.setForeground(Color.white);
        permitPanel.add(permitLabel);

       /* JTextField permitText = new JTextField(20);
        permitText.setPreferredSize(new Dimension(250,50));
       permitPanel.add(permitText); */
        String[] semesterOptions ={"Fall 2023", "Spring 2024", "Fall 2024"};
        JComboBox<String> permitBox = new JComboBox<>(semesterOptions);
        permitBox.setPreferredSize(new Dimension(250,50));
        permitBox.setFont(new Font("Roboto",Font.PLAIN,16));
        permitPanel.add(permitBox);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Roboto",Font.BOLD,16));
        roleLabel.setForeground(Color.white);
        rolePanel.add(roleLabel);

      /*  JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(100,-50,165,25);
        JMenu menu = new JMenu("Permit Type");
        JMenuItem menuItem1 = new JMenuItem("Commuter");

        menu.add(menuItem1);
        menuBar.add(menu);
        contentPanel.add(menuBar);*/

        String[] options ={"Commuter", "Resident", "Faculty"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(250,50));
        comboBox.setFont(new Font("Roboto",Font.PLAIN,16));
        rolePanel.add(comboBox);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(15, 80, 80, 25);
        saveButton.setBackground(Color.lightGray);
        savePanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(15, 80, 80, 25);
        cancelButton.setBackground(Color.lightGray);
        savePanel.add(cancelButton);

        this.setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });

        //contentPanel.setLayout(new GridLayout(10,1));
       // contentPanel.setLayout(new GridLayout(5, 1));
       // SpringLayout layout = new SpringLayout();
        //contentPanel.setLayout(layout);

/*
        layout.putConstraint(SpringLayout.EAST, namePanel, 80, SpringLayout.EAST, savePanel);
        layout.putConstraint(SpringLayout.EAST, permitPanel, 65, SpringLayout.EAST, savePanel);
        layout.putConstraint(SpringLayout.EAST, rolePanel, 65, SpringLayout.EAST, savePanel);
        layout.putConstraint(SpringLayout.WEST, savePanel, 120, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, savePanel, 120, SpringLayout.NORTH, rolePanel);
        layout.putConstraint(SpringLayout.NORTH, rolePanel, 120, SpringLayout.NORTH, permitPanel);
        layout.putConstraint(SpringLayout.NORTH, permitPanel, 120, SpringLayout.NORTH, namePanel);

        contentPanel.add(namePanel);
        contentPanel.add(permitPanel);
        contentPanel.add(rolePanel);
        contentPanel.add(savePanel);
*/
        backgroundPanel.add(contentPanel, BorderLayout.SOUTH);

       setContentPane(backgroundPanel);
       setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createProfileGUI profileGUI = new createProfileGUI();
            profileGUI.setVisible(true);
        });
    }
}