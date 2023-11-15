package frontend;

import backend.database.Garage;
import backend.database.Schedule;
import backend.database.userProfile;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;

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

        JPanel header = PDMPanels.createUserHeader("PDM - Edit Profile");
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
        contentPanel.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        firstNameText = new JTextField(15);
        firstNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(firstNameText, gbc);

        //Last Name
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        lastNameText = new JTextField(15);
        lastNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(lastNameText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JCheckBox commuterCheckbox = new JCheckBox("Commuter");
        commuterCheckbox.setFont(new Font("Monospaced",Font.BOLD,16));
        commuterCheckbox.setOpaque(false);
        contentPanel.add(commuterCheckbox, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        JCheckBox residentCheckbox = new JCheckBox("Resident");
        residentCheckbox.setFont(new Font("Monospaced",Font.BOLD,16));
        residentCheckbox.setOpaque(false);
        contentPanel.add(residentCheckbox, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        JCheckBox facultyCheckbox = new JCheckBox("Faculty");
        facultyCheckbox.setFont(new Font("Monospaced",Font.BOLD,16));
        facultyCheckbox.setOpaque(false);
        contentPanel.add(facultyCheckbox, gbc);

        commuterCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                // If checkbox set to true deselect other checkboxes
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    residentCheckbox.setSelected(false);
                    facultyCheckbox.setSelected(false);
                }

            }
        });
        residentCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                // If checkbox set to true deselect other checkboxes
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    commuterCheckbox.setSelected(false);
                    facultyCheckbox.setSelected(false);
                }

            }
        });
        facultyCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                // If checkbox set to true deselect other checkboxes
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    commuterCheckbox.setSelected(false);
                    residentCheckbox.setSelected(false);
                }

            }
        });

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

        gbc.gridx = 6;
        gbc.gridy = 4;
        JLabel licensePlateLabel = new JLabel("License Plate #: ");
        licensePlateLabel.setFont(new Font("Monospaced",Font.BOLD,16));
        contentPanel.add(licensePlateLabel, gbc);

        gbc.gridx = 7;
        gbc.gridy = 4;
        JTextField licensePlateText = new JTextField(generateLicensePlateNumber(), 9);
        licensePlateText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(licensePlateText, gbc);

        firstNameText.setText(userProfile.getUserFirstName());
        lastNameText.setText(userProfile.getUserLastName());
        vmText.setText(userProfile.getVehicleMake());
        vmoText.setText(userProfile.getVehicleModel());
        vyText.setText(userProfile.getVehicleYear());

        // Check the checkbox with user role
        String role = userProfile.getUserRole();
        if (role.equals("Commuter")) {
            commuterCheckbox.setSelected(true);
            residentCheckbox.setSelected(false);
            facultyCheckbox.setSelected(false);
        } else if (role.equals("Resident")) {
            commuterCheckbox.setSelected(false);
            residentCheckbox.setSelected(true);
            facultyCheckbox.setSelected(false);
        } else if (role.equals("Faculty")) {
            commuterCheckbox.setSelected(false);
            residentCheckbox.setSelected(false);
            facultyCheckbox.setSelected(true);
        }

        //vmText.setText(userProfile.getVehicleMake());
        //vmoText.setText(userProfile.getVehicleModel());
        //vyText.setText(userProfile.getVehicleYear());

        //Save Button

        gbc.gridx = 3;
        gbc.gridy = 5;
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBounds(15, 80, 80, 25);
        PDMPanels.styleButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userProfile.setUserFirstName(firstNameText.getText());
                userProfile.setUserLastName(lastNameText.getText());
                userProfile.setVehicleMake(vmText.getText());
                userProfile.setVehicleModel(vmoText.getText());
                userProfile.setVehicleYear(vyText.getText());

                if (commuterCheckbox.isSelected()) {
                    userProfile.setUserRole((String) "Commuter");
                } else if (residentCheckbox.isSelected()) {
                    userProfile.setUserRole((String) "Resident");
                } else if (facultyCheckbox.isSelected()) {
                    userProfile.setUserRole((String) "Faculty");
                }

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

        gbc.gridx = 3;
        gbc.gridy = 6;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(15, 80, 80, 25);
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
            userProfile initialProfile = new userProfile("12345", "Carson", "Parker", "Fall Semester",
                    "0", "0", "Commuter", "Toyota", "Camry", "2018", new Schedule(), new ArrayList<Integer>());
            editProfileGUI editProfile = new editProfileGUI(initialProfile);
            editProfile.setVisible(true);
        });
    }

    // Returns a random license plate number as a String. Format: Letter_Letter_Letter-Num_Num_Num_Num
    public String generateLicensePlateNumber() {

        StringBuilder lpn = new StringBuilder();

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // Generate 3 random letters appending each to the lpn
        for (int i = 0; i < 3; i++) {

            Random rand = new Random();

            int upperBound = 26;

            int randomInt = rand.nextInt(upperBound);

            String selectedLetter = alphabet[randomInt];

            lpn.append(selectedLetter);

        }

        // Add hyphen after letters
        lpn.append('-');

        // Generate 4 random numbers (0-9) appending each to the lpn
        for (int i = 0; i < 4; i++) {

            Random rand = new Random();

            int upperBound = 10;

            int randomInt = rand.nextInt(upperBound);

            String selectedNum = numbers[randomInt];

            lpn.append(selectedNum);

        }

        return lpn.toString();

    }
}


