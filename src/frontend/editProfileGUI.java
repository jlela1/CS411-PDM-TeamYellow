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


    public editProfileGUI(userProfile userProfile) {
        // Frame setup
        setTitle("Edit Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 35.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel header = PDMPanels.createUserHeader("PDM - Edit Your Profile");
        //header.setOpaque(false);
        //headerPanel.setPreferredSize(new Dimension(250,60));
        headerPanel.add(header, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createUserFooter();
        footerPanel.setOpaque(false);
        backgroundPanel.add(footerPanel, BorderLayout.SOUTH);


        // Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        //contentPanel.setBorder(BorderFactory.createEmptyBorder(180, 70, 150, 50));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 2, 10, 2);
        gbc.anchor = GridBagConstraints.WEST;


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
        String[] roleOptions = {"Commuter", "Resident", "Faculty", "Visitor"};
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

        firstNameText.setText(userProfile.getUserFirstName());
        lastNameText.setText(userProfile.getUserLastName());
        roleBox.setSelectedItem(userProfile.getUserRole());
        permitBox.setSelectedItem(userProfile.getPermitType());
        //vmText.setText(userProfile.getVehicleMake());
        //vmoText.setText(userProfile.getVehicleModel());
        //vyText.setText(userProfile.getVehicleYear());

        //Save Button

        gbc.gridx = 2;
        gbc.gridy = 5;
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBounds(15, 80, 80, 25);
        PDMPanels.styleButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userProfile.setUserFirstName(firstNameText.getText());
                userProfile.setUserLastName(lastNameText.getText());
                userProfile.setUserRole((String) roleBox.getSelectedItem());
                userProfile.setPermitType((String) permitBox.getSelectedItem());
                //userProfile.setVehicleMake(vmText.getText());
                //userProfile.setVehicleModel(vmoText.getText());
                //userProfile.setVehicleYear(vyText.getText());

                dispose();
                UserProfileGUI userProfileGUI = new UserProfileGUI(userProfile);
                userProfileGUI.setVisible(true);
                //userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
        contentPanel.add(saveButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        JButton cancelButton = new JButton("Cancel");
        //cancelButton.setBounds(15, 80, 80, 25);
        PDMPanels.styleButton(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserProfileGUI userProfileGUI = new UserProfileGUI(userProfile);
                userProfileGUI.setVisible(true);
                //userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        contentPanel.add(cancelButton, gbc);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            userProfile initialProfile = new userProfile("12345", "Carson", "Parker", "Fall Semester", "0", "0", "Commuter", new Schedule(), new ArrayList<Integer>());
            editProfileGUI editProfile = new editProfileGUI(initialProfile);
            editProfile.setVisible(true);
        });
    }
}


