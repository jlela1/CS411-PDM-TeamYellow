package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import backend.database.Schedule;
import backend.database.userProfile;
import frontend.SimulationUserInputGUI;
import frontend.UserProfileGUI;
import frontend.createProfileGUI;
import frontend.FeedbackSubmissionGUI;

public class UserDashboard extends JFrame{
    public UserDashboard() {

        setTitle("PDM User");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 14.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel header = PDMPanels.createUserHeader("PDM User Dashboard");
        headerPanel.add(header, BorderLayout.NORTH);

        JLabel welcomeLabel = new JLabel("Welcome to the Parking Demand Management User Dashboard!");
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        headerPanel.add(welcomeLabel, BorderLayout.SOUTH);

        //add(header, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 230));
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        JButton recommendationButton = new JButton("Recommendation");
        recommendationButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        recommendationButton.setForeground(Color.BLACK);
        recommendationButton.setBackground(new Color(98, 145, 141, 250));
        recommendationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        recommendationButton.setFocusPainted(false);
        recommendationButton.setPreferredSize(new Dimension(250, 50));
        recommendationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create UserProfile Button
        JButton userProfileButton = new JButton("User Profile");
        userProfileButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        userProfileButton.setForeground(Color.BLACK);
        userProfileButton.setBackground(new Color(50, 119, 222, 255));
        userProfileButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        userProfileButton.setFocusPainted(false);
        userProfileButton.setPreferredSize(new Dimension(250, 50));
        userProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton feedbackButton = new JButton("Feedback");
        feedbackButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        feedbackButton.setForeground(Color.BLACK);
        feedbackButton.setBackground(new Color(255, 193, 7, 255)); // Choose your preferred color
        feedbackButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        feedbackButton.setFocusPainted(false);
        feedbackButton.setPreferredSize(new Dimension(250, 50));
        feedbackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logOutB= new JButton("Logout");
        logOutB.setFont(new Font("Monospaced", Font.BOLD, 16));
        logOutB.setForeground(Color.BLACK);
        logOutB.setBackground(new Color(204, 11, 91, 255));
        logOutB.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        logOutB.setFocusPainted(false);
        logOutB.setPreferredSize(new Dimension(250, 50));
        logOutB.setAlignmentX(Component.CENTER_ALIGNMENT);



        recommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of googleMapsGUI and make it visible
//                googleMapsGUI GoogleMapsGUI = new googleMapsGUI();
//                GoogleMapsGUI.setVisible(true);
            }
        });

        // Add behavior to UserProfile Button: Creates a new instance of UserProfileGUI and sets visible
        userProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creating a test user on userProfileGUI creation. A real user will need to be retrieved.
                removeAll();
                dispose();
                UserProfileGUI userProfileGUI = new UserProfileGUI(new userProfile("12345", "Carson", "Parker", "Fall Semester",
                        "0", "0", "Commuter", "Toyota", "Camry", "2018", new ArrayList<String>(), new Schedule(), new ArrayList<Integer>()));
                userProfileGUI.setVisible(true);
                userProfileGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
                userProfileGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of FeedbackSubmissionGUI and make it visible
                FeedbackSubmissionGUI feedbackSubmissionGUI = new FeedbackSubmissionGUI();
                feedbackSubmissionGUI.setVisible(true);
            }
        });

        logOutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
                log.setVisible(true);
            }
        });



        contentPanel.add(recommendationButton);
        contentPanel.add(userProfileButton);
        contentPanel.add(feedbackButton);
        contentPanel.add(logOutB);

        //Create a PDM footer
        JPanel footer = PDMPanels.createFooter();
        footer.setOpaque(false);
        backgroundPanel.add(footer, BorderLayout.SOUTH);


        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });
    }
}
