package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frontend.SimulationUserInputGUI;

public class AdminHomePage extends JFrame{
    public AdminHomePage() {

        setTitle("PDM Business Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(122, 114, 114, 173));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("PDM Business Dashboard");
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headingLabel.setForeground(Color.DARK_GRAY);
        headingLabel.setBackground(Color.lightGray);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headingLabel);

        JLabel welcomeLabel = new JLabel("Welcome to the Parking Demand Management Dashboard!");
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBackground(Color.lightGray);
        welcomeLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        headerPanel.add(welcomeLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 230));
        //contentPanel.setBackground(Color.WHITE);

        JButton createSimulationButton = new JButton("Create Simulation");
        createSimulationButton.setFont(new Font("Roboto", Font.BOLD, 16));
        createSimulationButton.setForeground(Color.BLACK);
        createSimulationButton.setBackground(new Color(231, 103, 103));
        createSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        createSimulationButton.setFocusPainted(false);
        createSimulationButton.setPreferredSize(new Dimension(250, 50));
        createSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        createSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GarageManager simulationStart = new GarageManager();
            }
        });

        contentPanel.add(createSimulationButton);
        add(contentPanel, BorderLayout.CENTER);


        //Create a PDM footer
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Roboto", Font.ITALIC, 15));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(122, 114, 114, 173));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);
            }
        });
    }
}
