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
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class createProfileGUI extends JFrame{
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JComboBox<String> roleBox;
    private JTextField vmText;
    private JTextField vmoText;
    private JTextField vyText;

    // Remains false until the user enters something in the firstName box. Gets set to false if the firstName
    // box is made empty. This ensures that the tip text "First" is not returned as the firstName.
    boolean firstNameTextWasChanged = false;

    // Remains false until the user enters something in the firstName box. Gets set to false if the firstName
    // box is made empty. This ensures that the tip text "First" is not returned as the firstName.
    boolean lastNameTextWasChanged = false;

    public createProfileGUI() {

        // Frame setup
        setTitle("Create Profile");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 35.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        mainPanel.setLayout(new BorderLayout());

        setContentPane(mainPanel);

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
        contentPanel.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        firstNameText = new JTextField(15);
        // Set initial text color to gray, text will be removed and color set to black when text box selected.
        firstNameText.setForeground(new Color(150, 150, 150));
        firstNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(firstNameText, gbc);

        // When the user clicks the firstName box, remove the tip text and set color to black.
        firstNameText.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                firstNameTextWasChanged = true;
                firstNameText.setText("");
                firstNameText.setForeground(new Color(0, 0, 0));
            }

            // If the user clicks off of the firstName box without entering anything, replace the tip text. Else, do nothing.
            public void focusLost(FocusEvent e) {
                if (firstNameText.getText().isEmpty()) {
                    firstNameTextWasChanged = false;
                    firstNameText.setText("First");
                    firstNameText.setForeground(new Color(150, 150, 150));
                }
            }
        });

        //Last Name
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        lastNameText = new JTextField("Last", 15);
        // Set initial text color to gray, text will be removed and color set to black when text box selected.
        lastNameText.setForeground(new Color(150, 150, 150));
        lastNameText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        contentPanel.add(lastNameText, gbc);

        // When the user clicks the lastName box, remove the tip text and set color to black.
        lastNameText.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                lastNameTextWasChanged = true;
                lastNameText.setText("");
                lastNameText.setForeground(new Color(0, 0, 0));
            }

            // If the user clicks off of the lastName box without entering anything, replace the tip text. Else, do nothing.
            public void focusLost(FocusEvent e) {
                if (lastNameText.getText().isEmpty()) {
                    lastNameTextWasChanged = false;
                    lastNameText.setText("Last");
                    lastNameText.setForeground(new Color(150, 150, 150));
                }
            }
        });

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

        gbc.gridx = 3;
        gbc.gridy = 5;
        JButton saveButton = new JButton("Create Account");
        saveButton.setBounds(15, 80, 80, 25);
        //saveButton.setBackground(Color.lightGray);
        PDMPanels.styleButton(saveButton);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkIfNameFieldsChanged(firstNameText, lastNameText, firstNameTextWasChanged, lastNameTextWasChanged);

                removeAll();
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });
        contentPanel.add(saveButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        JButton cancelButton = new JButton("Cancel");
        //cancelButton.setBounds(20, 80, 40, 40);
        PDMPanels.styleButton(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkIfNameFieldsChanged(firstNameText, lastNameText, firstNameTextWasChanged, lastNameTextWasChanged);

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

        // Set focus on a non-input element initially so that text boxes do not auto select.
        Runnable setInitialFocusRun = new Runnable() {
            public void run() {
                try {
                    headerPanel.requestFocusInWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        SwingUtilities.invokeLater(setInitialFocusRun);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createProfileGUI profileGUI = new createProfileGUI();
            profileGUI.setVisible(true);
        });
    }

    // Call this function before saving name data or closing GUI.
    // Checks if the name fields have been altered and sets them to empty string if not.
    // This prevents the tip text "First" and "Last" from being returned.
    public void checkIfNameFieldsChanged(JTextField firstName, JTextField lastName, Boolean firstNameTextWasChanged, Boolean lastNameTextWasChanged) {

        if (!firstNameTextWasChanged) {
            firstName.setText("");
        }

        if (!lastNameTextWasChanged) {
            lastName.setText("");
        }

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