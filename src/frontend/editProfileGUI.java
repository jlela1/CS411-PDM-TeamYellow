package frontend;

import backend.database.Garage;
//import backend.database.parkingStructure;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class editProfileGUI extends JFrame{


    public editProfileGUI() {
        // Frame setup
        setTitle("Edit Profile");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());


        JPanel header = PDMPanels.createHeader("Edit Profile");
        add(header, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();

        JPanel namePanel = new JPanel();
        JPanel permitPanel = new JPanel();
        JPanel rolePanel = new JPanel();
        JPanel savePanel = new JPanel();


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Roboto",Font.ITALIC,16));
        namePanel.add(nameLabel);



        JTextField nameText = new JTextField(20);
       nameText.setPreferredSize(new Dimension(250,50));
        nameText.setFont(new Font("Roboto",Font.PLAIN,16));
        namePanel.add(nameText);


        JLabel permitLabel = new JLabel("Year:");
       permitLabel.setFont(new Font("Roboto",Font.ITALIC,16));
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
        roleLabel.setFont(new Font("Roboto",Font.ITALIC,16));
        rolePanel.add(roleLabel);



      /*  JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(100,-50,165,25);
        JMenu menu = new JMenu("Permit Type");
        JMenuItem menuItem1 = new JMenuItem("Commuter");

        menu.add(menuItem1);
        menuBar.add(menu);
        contentPanel.add(menuBar);*/

        String[] options ={"Commuter", "Resident", "faculty"};
        JComboBox<String> comboBox = new JComboBox<>(options);
       comboBox.setPreferredSize(new Dimension(250,50));
        comboBox.setFont(new Font("Roboto",Font.PLAIN,16));
        rolePanel.add(comboBox);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(10, 80, 80, 25);
        savePanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(100, 80, 80, 25);
        savePanel.add(cancelButton);

        this.setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String permitType = (String) permitBox.getSelectedItem();
                String roleType = (String) comboBox.getSelectedItem();
                System.out.println("Name: " +  name + ", Permit Type: " + permitType + ", Role: "
                + roleType);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //UserProfile userProfile = new UserProfile();
                //userProfile.setVisible(true);
            }
        });
        //contentPanel.setLayout(new GridLayout(4,1));
       SpringLayout layout = new SpringLayout();
       contentPanel.setLayout(layout);

        contentPanel.add(namePanel);
        contentPanel.add(permitPanel);
        contentPanel.add(rolePanel);
        contentPanel.add(savePanel);


        layout.putConstraint(SpringLayout.EAST, namePanel, 80, SpringLayout.EAST, savePanel);
        layout.putConstraint(SpringLayout.EAST, permitPanel, 65, SpringLayout.EAST, savePanel);
        layout.putConstraint(SpringLayout.EAST, rolePanel, 65, SpringLayout.EAST, savePanel);
        layout.putConstraint(SpringLayout.WEST, savePanel, 120, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, savePanel, 120, SpringLayout.NORTH, rolePanel);
        layout.putConstraint(SpringLayout.NORTH, rolePanel, 120, SpringLayout.NORTH, permitPanel);
        layout.putConstraint(SpringLayout.NORTH, permitPanel, 120, SpringLayout.NORTH, namePanel);

        //layout.putConstraint(SpringLayout.WEST, namePanel, 10, SpringLayout.WEST, permitPanel);

        add(contentPanel);
        setSize(400,500);
        setVisible(true);





    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            editProfileGUI editProfile = new editProfileGUI();
            editProfile.setVisible(true);
        });
    }
}


