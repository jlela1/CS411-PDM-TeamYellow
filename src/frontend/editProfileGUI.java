package frontend;

import backend.database.Garage;
import backend.database.Schedule;
import backend.database.userProfile;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class editProfileGUI extends JFrame{

    private JTextField firstNameText;
    private JTextField lastNameText;
    private JComboBox<String> permitBox;
    private JComboBox<String> roleBox;
    private JTextField vmText;
    private JTextField vmoText;
    private JTextField vyText;


    public editProfileGUI() {
        // Frame setup
        setTitle("Edit Profile");
        setLayout(new BorderLayout());


        JPanel headerPanel = PDMPanels.createUserHeader("PDM - Edit Your Profile");
        add(headerPanel, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createUserFooter();
        add(footerPanel, BorderLayout.SOUTH);


        // Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;

        //first name
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Your First Name: "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        firstNameText = new JTextField(15);
        firstNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(firstNameText, gbc);

        //Last Name
        gbc.gridx = 3;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Your Last Name:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        lastNameText = new JTextField(15);
        lastNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(lastNameText, gbc);

        //Parking Pass
        gbc.gridx = 2;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Parking Pass Type:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        String[] permitOptions = {"Option 1", "Option 2", "Option 3"};
        permitBox = new JComboBox<>(permitOptions);
        permitBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(permitBox, gbc);

        //Role?
        gbc.gridx = 2;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("................: "), gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        String[] roleOptions = {"Role 1", "Role 2", "Role 3"};
        roleBox = new JComboBox<>(roleOptions);
        roleBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(roleBox, gbc);

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

        //Save Button

        gbc.gridx = 2;
        gbc.gridy = 5;
        JButton saveButton = new JButton("Save Your Changes");
        //saveButton.setBounds(20, 80,30 , 40);
        PDMPanels.styleButton(saveButton);
        contentPanel.add(saveButton, gbc);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String permitType = (String) permitBox.getSelectedItem();
                String role = (String) roleBox.getSelectedItem();
                String vehicleMake = vmText.getText();
                String vehicleModel = vmoText.getText();
                String vehicleYear = vyText.getText();
                removeAll();
                UserProfileGUI userProfileGUI = new UserProfileGUI(new userProfile("12345", "Carson", "Parker", "Fall Semester", "0", "0", "Commuter", new Schedule(), new ArrayList<Integer>()));
                userProfileGUI.setVisible(true);
                //userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });


        gbc.gridx = 3;
        gbc.gridy = 5;
        JButton cancelButton = new JButton("Cancel Your Changes");
        //cancelButton.setBounds(20, 80, 40, 40);
        PDMPanels.styleButton(cancelButton);
        contentPanel.add(cancelButton, gbc);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                UserProfileGUI userProfileGUI = new UserProfileGUI(new userProfile("12345", "Carson", "Parker", "Fall Semester", "0", "0", "Commuter", new Schedule(), new ArrayList<Integer>()));
                userProfileGUI.setVisible(true);
                //userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //userProfile profile = new userProfile("Sample", "John", "Doe", "Option 1", "Role 1", "Toyota", "Camry", "2022");
            editProfileGUI editProfile = new editProfileGUI();
            editProfile.setVisible(true);
        });
    }
}



        /*
        this.setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String Vm = vmText.getText();
                String Vmo = vmoText.getText();
                String Vy = vyText.getText();
                String permitType = (String) permitBox.getSelectedItem();
                String roleType = (String) comboBox.getSelectedItem();
                removeAll();
                UserProfileGUI userProfileGUI = new UserProfileGUI(new userProfile("12345", firstName, lastName, permitType, "0", "0", roleType, new Schedule(), new ArrayList<Integer>()));
                userProfileGUI.setVisible(true);
                userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                UserProfileGUI userProfileGUI = new UserProfileGUI(new userProfile("12345", "Carson", "Parker", "Fall Semester", "0", "0", "Commuter", new Schedule(), new ArrayList<Integer>()));
                userProfileGUI.setVisible(true);
                userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        */

