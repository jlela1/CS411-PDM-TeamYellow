package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frontend.SimulationUserInputGUI;

public class AdminHomePage extends JFrame{
    public AdminHomePage() {

        setTitle("a Business Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel header = PDMPanels.createHeader("PDM Business Dashboard");
        add(header, BorderLayout.NORTH);

        JLabel welcomeLabel = new JLabel("Welcome to the Parking Demand Management Dashboard!");
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setBackground(Color.lightGray);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        header.add(welcomeLabel);

        add(header, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 230));
        //contentPanel.setBackground(Color.WHITE);

        JButton createSimulationButton = new JButton("Create Simulation");
        createSimulationButton.setFont(new Font("Roboto", Font.BOLD, 16));
        createSimulationButton.setForeground(Color.BLACK);
        createSimulationButton.setBackground(new Color(231, 103, 103, 163));
        createSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        createSimulationButton.setFocusPainted(false);
        createSimulationButton.setPreferredSize(new Dimension(250, 50));
        createSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        createSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontend.GarageManager simulationStart = new frontend.GarageManager();
            }
        });

        contentPanel.add(createSimulationButton);
        add(contentPanel, BorderLayout.CENTER);


        //Create a PDM footer
        JPanel footer = PDMPanels.createFooter();
        add(footer, BorderLayout.SOUTH);


        setLocationRelativeTo(null);
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
