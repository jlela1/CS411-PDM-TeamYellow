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

public class GuestDashboard extends JFrame{
    public GuestDashboard(){
        setTitle("PDM Guest User");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 13.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel header = PDMPanels.createUserHeader("PDM Guest User Dashboard");
        headerPanel.add(header, BorderLayout.NORTH);

        JLabel welcomeLabel = new JLabel("                            Welcome to the Parking Demand Management Guest User Dashboard!");
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

        JButton IMapButton = new JButton("Parking Map");
        IMapButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        IMapButton.setForeground(Color.BLACK);
        IMapButton.setBackground(new Color(98, 145, 141, 250));
        IMapButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        IMapButton.setFocusPainted(false);
        IMapButton.setPreferredSize(new Dimension(250, 50));
        IMapButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton BugButton = new JButton("Report Bug");
        BugButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        BugButton.setForeground(Color.BLACK);
        BugButton.setBackground(new Color(113, 50, 222, 255)); // Choose your preferred color
        BugButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        BugButton.setFocusPainted(false);
        BugButton.setPreferredSize(new Dimension(250, 50));
        BugButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logOutB= new JButton("Logout");
        logOutB.setFont(new Font("Monospaced", Font.BOLD, 16));
        logOutB.setForeground(Color.BLACK);
        logOutB.setBackground(new Color(204, 11, 91, 255));
        logOutB.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        logOutB.setFocusPainted(false);
        logOutB.setPreferredSize(new Dimension(250, 50));
        logOutB.setAlignmentX(Component.CENTER_ALIGNMENT);

        logOutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
                log.setVisible(true);
            }
        });

        BugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of BugReportingGUI and make it visible
                BugReportingGUI Bug = new BugReportingGUI();
                Bug.setVisible(true);
            }
        });

        IMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                googleMapsGUI map = new googleMapsGUI();
                map.setVisible(true);
            }
        });



        contentPanel.add(IMapButton);
        contentPanel.add(BugButton);
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
                GuestDashboard guestDashboard = new GuestDashboard();
                guestDashboard.setVisible(true);
            }
        });
    }

}
