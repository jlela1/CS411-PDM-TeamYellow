package frontend;

import backend.database.Garage;
import backend.database.userProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class UserProfileGUI extends JFrame {


    private JComboBox<String> dayComboBox;

    // Contains components 1-9 for setting the hour portion of class start time
    private JComboBox<String> startTimeHourComboBox;

    // Contains components 00, 05, 10, ..., 55 for setting the minute portion of class start time
    private JComboBox<String> startTimeMinuteComboBox;
    private JComboBox<String> amPMComboBox;

    // Constructor takes the userProfile object to be updated based on ComboBox selections
    public UserProfileGUI(userProfile user) {

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 32.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        // Create a main panel element to contain the non-header and footer elements of the page.
        JPanel headerPanel = PDMPanels.createUserHeader("PDM - User Profile");
        //headerPanel.setOpaque(false);
        //headerPanel.setLayout(new BorderLayout());

        //header.setOpaque(false);
        headerPanel.setPreferredSize(new Dimension(250,60));
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);


        //JPanel footerPanel = PDMPanels.createUserFooter();
        //footerPanel.setOpaque(false);
        //backgroundPanel.add(footerPanel, BorderLayout.SOUTH);

        /*
        JLabel simulationLabel = new JLabel("Your Account");
        simulationLabel.setOpaque(false);
        simulationLabel.setForeground(Color.DARK_GRAY);
        simulationLabel.setBackground(Color.lightGray);
        simulationLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        simulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(simulationLabel);

        */

        //String userName = user.getUserFirstName() + " " + user.getUserLastName();
        JLabel nameLabel = createLabel("Your Name: " + user.getUserFirstName() + "   Your Last Name: " + user.getUserLastName());
        nameLabel.setOpaque(false);
        nameLabel.setFont(new Font("Monospaced", Font.ITALIC, 28));
        nameLabel.setForeground(Color.darkGray);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.TOP);
        JLabel userRoleLabel = createLabel("Parking Pass Type: " + user.getUserRole());
        userRoleLabel.setOpaque(false);
        userRoleLabel.setFont(new Font("Monospaced", Font.ITALIC, 28));
        userRoleLabel.setForeground(Color.darkGray);
        userRoleLabel.setHorizontalAlignment(JLabel.CENTER);
        userRoleLabel.setVerticalAlignment(JLabel.CENTER);
        //JLabel permitTypeLabel = createLabel("Permit Type: " + user.getPermitType());
        //permitTypeLabel.setOpaque(false);
        //permitTypeLabel.setFont(new Font("Monospaced", Font.ITALIC, 28));
        //permitTypeLabel.setForeground(Color.darkGray);
        //permitTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        //permitTypeLabel.setVerticalAlignment(JLabel.BOTTOM);

        /*

        JLabel scheduleLabel = createLabel("Change Schedule:");
        scheduleLabel.setOpaque(false);
        scheduleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));

        // Create an array of strings containing days of the week and populate the dayComboBox with each entry.
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<String>();
        for (String day : days) {
            dayComboBox.addItem(day);
        }

        // Create an array of strings containing possible hours and populate the startTimeHourComboBox with each entry
        String[] hours = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        startTimeHourComboBox = new JComboBox<String>();
        for (String hour : hours) {
            startTimeHourComboBox.addItem(hour);
        }

        // Create an array of strings containing possible minutes and populate the startTimeMinuteComboBox with each entry
        String[] minutes = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
        startTimeMinuteComboBox = new JComboBox<String>();
        for (String minute : minutes) {
            startTimeMinuteComboBox.addItem(minute);
        }

        amPMComboBox = new JComboBox<String>();
        amPMComboBox.addItem("AM");
        amPMComboBox.addItem("PM");

        // Set the time value in the combo boxes to the value saved for Monday
        String[] mondayTimeValues = minutesToTimeString(user.getDailyStartTime(0));
        startTimeHourComboBox.setSelectedItem(mondayTimeValues[0]);
        startTimeMinuteComboBox.setSelectedItem(mondayTimeValues[1]);
        amPMComboBox.setSelectedItem(mondayTimeValues[2]);
*/

        // Create a top panel element containing the user's name, role, and permit type labels.
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);
        //topPanel.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints topPanelGBC = new GridBagConstraints();
        backgroundPanel.add(topPanel, BorderLayout.CENTER);
        //topPanel.setLayout(new GridBagLayout());

        topPanel.setPreferredSize(new Dimension(1800, 300));
        topPanelGBC.gridx = 0;
        topPanelGBC.gridy = 0;
        topPanel.add(nameLabel, topPanelGBC);
        //topPanelGBC.gridy = 1;
        //topPanel.add(permitTypeLabel, topPanelGBC);
        topPanelGBC.gridy = 2;
        topPanel.add(userRoleLabel, topPanelGBC);
        backgroundPanel.add(topPanel, BorderLayout.CENTER);
        /*
        // Create labels for combo boxes
        JLabel dayComboBoxLabel = new JLabel("Day:");
        dayComboBoxLabel.setFont(new Font("Monospaced", Font.PLAIN, 22));
        JLabel timeComboBoxLabel = new JLabel("Class Start Time:");
        timeComboBoxLabel.setFont(new Font("Monospaced", Font.PLAIN, 22));

        // Create a middle panel element containing the schedule label and day/time selection combo boxes
        JPanel middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        middlePanel.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints middlePanelGBC = new GridBagConstraints();
        middlePanel.setLayout(new GridBagLayout());
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        middlePanel.setPreferredSize(new Dimension(2000, 100));
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 0;
        middlePanel.add(scheduleLabel, middlePanelGBC);
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 1;
        middlePanel.add(dayComboBoxLabel, middlePanelGBC);
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 2;
        middlePanel.add(dayComboBox, middlePanelGBC);
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 3;
        middlePanel.add(timeComboBoxLabel, middlePanelGBC);
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 4;
        middlePanel.add(startTimeHourComboBox, middlePanelGBC);
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 5;
        middlePanel.add(startTimeMinuteComboBox, middlePanelGBC);
        middlePanelGBC.gridx = 0;
        middlePanelGBC.gridy = 6;
        middlePanel.add(amPMComboBox, middlePanelGBC);
        */
        // Create a bottom panel element containing the dashboard button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        //bottomPanel.setBackground(new Color(113, 100, 217, 242));
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(200, 70));

        // Create button to open an edit profile GUI instance
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setBackground(Color.PINK);
        bottomPanel.add(editProfileButton, BorderLayout.CENTER);

        // Create button to return to user dashboard and add it to the bottom of the page
        JButton dashboardButton = new JButton("User Dashboard");
        dashboardButton.setBackground(Color.PINK);
        bottomPanel.add(dashboardButton, BorderLayout.SOUTH);



        /*
        // Update user's schedule information when dayComboBox selection is changed
        dayComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                updateTimeComboBoxes(user);
            }
        });

        // Update user's schedule information when startTimeHourComboBox selection is changed
        startTimeHourComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                updateUserSchedule(user);
            }
        });

        // Update user's schedule information when startTimeMinuteComboBox selection is changed
        startTimeMinuteComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                updateUserSchedule(user);
            }
        });

        // Update user's schedule information when amPMComboBox selection is changed
        amPMComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                updateUserSchedule(user);
            }
        });
        */


        // Cleanup and open new editProfile when edit profile button is pressed
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the latest user profile from the database
                userProfile latestUser = userProfile.getLatestUserProfile();
                if(latestUser == null ){
                    latestUser = user;
                }
                removeAll();
                dispose();
                editProfileGUI editProfile = new editProfileGUI(latestUser); //gets latest user

                editProfile.setVisible(true);
            }
        });

        // Cleanup and open new UserDashboard when dashboard button is pressed
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                dispose();
                UserDashboard newDashboard = new UserDashboard();
                newDashboard.setVisible(true);
            }
        });



        setVisible(true);

    }
    /*
    // Takes a user profile and 4 strings representing the changed day and time. Updates the given user's schedule members to reflect the selection.
    private void updateUserSchedule(userProfile user) {
        // Get the latest user profile from the database
        userProfile latestUser = userProfile.getLatestUserProfile();

        // Check if latestUser is not null before proceeding
        if (latestUser != null) {
            userProfile latestUserCopy = latestUser.clone(); // Create a copy of the latest user

            String day = (String) dayComboBox.getSelectedItem();

            int dayIndex = switch (day) {
                case "Monday" -> 0;
                case "Tuesday" -> 1;
                case "Wednesday" -> 2;
                case "Thursday" -> 3;
                case "Friday" -> 4;
                case "Saturday" -> 5;
                case "Sunday" -> 6;
                default -> 0;
            };

            String hour = (String) startTimeHourComboBox.getSelectedItem();
            String minute = (String) startTimeMinuteComboBox.getSelectedItem();
            String amPM = (String) amPMComboBox.getSelectedItem();
            String timeStr = hour + "-" + minute + "-" + amPM;
            int minuteTime = timeStringToMinutes(timeStr);

            // Set the daily start time only for the selected day
            latestUser.setDailyStartTime(dayIndex, minuteTime);

            // Get the day of the week as a string
            String selectedDayOfWeek = userProfile.getDayOfWeekFromIndex(dayIndex);

            // Insert or update the schedule for the selected day
            userProfile.insertOrUpdateDailyStartTimes(latestUser.getVehicleId(), latestUser.getDailyStartTimes(), selectedDayOfWeek);
        }
    }

    // Updates the time combo boxes to reflect the time saved in the user's profile when the day combo box is changed
    private void updateTimeComboBoxes(userProfile user) {

        String day = (String) dayComboBox.getSelectedItem();

        int dayIndex = switch (day) {
            case "Monday" -> 0;
            case "Tuesday" -> 1;
            case "Wednesday" -> 2;
            case "Thursday" -> 3;
            case "Friday" -> 4;
            case "Saturday" -> 5;
            case "Sunday" -> 6;
            default -> 0;
        };

        // Set the values in the combo boxes to those saved for the selected day
        String[] timeValues = minutesToTimeString(user.getDailyStartTime(dayIndex));
        System.out.println(timeValues[0]);
        System.out.println(timeValues[1]);
        System.out.println(timeValues[2]);
        startTimeHourComboBox.setSelectedItem(timeValues[0]);
        startTimeMinuteComboBox.setSelectedItem(timeValues[1]);
        amPMComboBox.setSelectedItem(timeValues[2]);

    }

    // Converts a timeStr in the format "hour-MM-AMPM" into total minutes for storage in userProfile. Ex. 7-15-AM == 435.
    public static int timeStringToMinutes(String timeStr) {

        String[] components = timeStr.split("-");

        String hourStr = components[0];
        String minuteStr = components[1];
        String amPmStr = components[2];

        // Convert the hour and minute to integers
        int hour = Integer.parseInt(hourStr);
        int minute = Integer.parseInt(minuteStr);

        // Convert to 24-hour format if it's PM
        if (amPmStr.equalsIgnoreCase("PM")) {
            hour = (hour + 12) % 24;
        }

        // Calculate the total minutes
        int totalMinutes = hour * 60 + minute;

        return totalMinutes;
    }

    // Returns an array of Strings of length 3 containing the hour, minute, and AMPM value based on the minutes provided.
    public static String[] minutesToTimeString(int totalMinutes) {
        if (totalMinutes < 0 || totalMinutes > 1440) {
            throw new IllegalArgumentException("Total minutes must be between 0 and 1440");
        }

        // Calculate the hour and minute components
        int hour = totalMinutes / 60;
        int minute = totalMinutes % 60;

        // Determine AM or PM
        String amPm;
        if (hour >= 12) {
            if (hour > 12) {
                hour -= 12;
            }
            amPm = "PM";
        } else {
            if (hour == 0) {
                hour = 12;
            }
            amPm = "AM";
        }

        String[] returnArray = new String[3];
        returnArray[0] = Integer.toString(hour);
        returnArray[1] = Integer.toString(minute);
        returnArray[2] = amPm;

        // Cover special cases for formatting
        if (returnArray[1].equals("0")) {
            returnArray[1] = "00";
        } else if (returnArray[1].equals("5")) {
            returnArray[1] = "05";
        }

        return returnArray;

    }
    */


    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        // Increase font size
        Font labelFont = new Font("Monospaced", Font.BOLD, 32);
        label.setFont(labelFont);

        // Add padding and background color
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return label;

    }

}