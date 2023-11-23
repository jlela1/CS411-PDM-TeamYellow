package frontend;

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
        gbc.anchor = GridBagConstraints.CENTER;


        //first name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        firstNameText = new JTextField(15);
        firstNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(firstNameText, gbc);

        //Last Name
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        contentPanel.add(lastNameLabel, gbc);

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
        JLabel vehicleMakeLabel = new JLabel("Vehicle Make:");
        vehicleMakeLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        contentPanel.add(vehicleMakeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        vmText = new JTextField(15);
        vmText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(vmText, gbc);

        //Vehicle Model
        gbc.gridx = 2;
        gbc.gridy = 4;
        JLabel vehicleModelLabel = new JLabel("Vehicle Model:");
        vehicleModelLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        contentPanel.add(vehicleModelLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        vmoText = new JTextField(15);
        vmoText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(vmoText, gbc);

        //Vehicle Year
        gbc.gridx = 4;
        gbc.gridy = 4;
        JLabel vehicleYearLabel = new JLabel("Vehicle Year:");
        vehicleYearLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        contentPanel.add(vehicleYearLabel, gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel addClassLabel = new JLabel("Add a Class: ");
        addClassLabel.setFont(new Font("Monospaced",Font.BOLD,16));
        addClassLabel.setOpaque(false);
        contentPanel.add(addClassLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JTextField classNameText = new JTextField(15);
        classNameText.setFont(new Font("Monospaced",Font.PLAIN,16));
        contentPanel.add(classNameText, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, -30, 0, 0);
        // Create an array of strings containing days of the week and populate the dayComboBox with each entry.
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox<String> dayComboBox = new JComboBox<String>();
        for (String day : days) {
            dayComboBox.addItem(day);
        }
        contentPanel.add(dayComboBox, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, -190, 0, 0);
        // Create an array of strings containing possible hours and populate the startTimeHourComboBox with each entry
        String[] hours = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        JComboBox<String> startTimeHourComboBox = new JComboBox<String>();
        for (String hour : hours) {
            startTimeHourComboBox.addItem(hour);
        }
        contentPanel.add(startTimeHourComboBox, gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, -391, 0, 0);
        // Create an array of strings containing possible minutes and populate the startTimeMinuteComboBox with each entry
        String[] minutes = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
        JComboBox<String> startTimeMinuteComboBox = new JComboBox<String>();
        for (String minute : minutes) {
            startTimeMinuteComboBox.addItem(minute);
        }
        contentPanel.add(startTimeMinuteComboBox, gbc);

        startTimeHourComboBox.setSelectedItem("7");

        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, -500, 0, 0);
        JComboBox<String> amPMComboBox = new JComboBox<String>();
        amPMComboBox.addItem("AM");
        amPMComboBox.addItem("PM");
        contentPanel.add(amPMComboBox, gbc);

        gbc.gridx = 6;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, -550, 0, 0);
        JButton addClassButton = new JButton("Add Class");
        addClassButton.setBounds(15, 80, 80, 25);
        PDMPanels.styleButton(addClassButton);
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Do nothing if the class name text field is empty
                if (!classNameText.getText().isEmpty()) {

                    String className = classNameText.getText();
                    String day = (String) dayComboBox.getSelectedItem();
                    String hour = (String) startTimeHourComboBox.getSelectedItem();
                    String minute = (String) startTimeMinuteComboBox.getSelectedItem();
                    String amPM = (String) amPMComboBox.getSelectedItem();
                    String classToSave = className + " - " + day + " " + hour + ':' + minute + " " + amPM;

                    classNameText.setText("");

                    userProfile.addSavedClass(classToSave);

                }

            }
        });
        contentPanel.add(addClassButton, gbc);

        gbc.gridx = 7;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, -550, 0, 0);
        JButton viewClassesButton = new JButton("View Classes");
        addClassButton.setBounds(15, 80, 80, 25);
        PDMPanels.styleButton(viewClassesButton);
        viewClassesButton.addActionListener(new ActionListener() {
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

                ViewClassesGUI viewClassesGUI = new ViewClassesGUI(userProfile);

                viewClassesGUI.setVisible(true);

            }
        });
        contentPanel.add(viewClassesButton, gbc);

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
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 0, 0);
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
                userProfile latest_user = backend.database.userProfile.getLatestUserProfile();
                latest_user.setUserFirstName(userProfile.getUserFirstName());
                latest_user.setUserLastName(userProfile.getUserLastName());
                latest_user.setUserRole(userProfile.getUserRole());
                latest_user.setPermitType(userProfile.getPermitType());
                latest_user.setVehicleMake(userProfile.getVehicleMake());
                latest_user.setVehicleModel(userProfile.getVehicleModel());
                latest_user.setVehicleYear(userProfile.getVehicleYear());
                latest_user.updateProfileInDatabase(latest_user);
                dispose();
                UserProfileGUI userProfileGUI = new UserProfileGUI(userProfile);
                userProfileGUI.setVisible(true);
                //userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
        contentPanel.add(saveButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 8;
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
                    "0", "0", "Commuter", "Toyota", "Camry", "2018", new ArrayList<String>(), new Schedule(), new ArrayList<Integer>());
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


