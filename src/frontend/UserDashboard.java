package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frontend.SimulationUserInputGUI;

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
                ImageIcon backgroundImage = new ImageIcon ("resources/backgroundsim.jpg");
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

        JLabel welcomeLabel = new JLabel("Welcome to the Parking Demand Management Dashboard!");
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 18));
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
        recommendationButton.setFont(new Font("Roboto", Font.BOLD, 16));
        recommendationButton.setForeground(Color.BLACK);
        recommendationButton.setBackground(new Color(222, 50, 50, 255));
        recommendationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        recommendationButton.setFocusPainted(false);
        recommendationButton.setPreferredSize(new Dimension(250, 50));
        recommendationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        recommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of googleMapsGUI and make it visible
                googleMapsGUI GoogleMapsGUI = new googleMapsGUI();
                GoogleMapsGUI.setVisible(true);
            }
        });

        contentPanel.add(recommendationButton);


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
