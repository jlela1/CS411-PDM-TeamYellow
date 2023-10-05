package frontend.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomePage extends JFrame{
    public AdminHomePage() {
        setTitle("PDM Business Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 0, 0, 128));
        headerPanel.setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("PDM Business Dashboard");
        headingLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(headingLabel, BorderLayout.NORTH);

        JLabel welcomeLabel = new JLabel("Welcome to the Parking Demand Management Dashboard");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Create a panel for the main content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JButton createSimulationButton = new JButton("Go to Simulation Options");
        createSimulationButton.setFont(new Font("Verdana", Font.BOLD, 16));
        createSimulationButton.setForeground(Color.WHITE);
        createSimulationButton.setBackground(new Color(0, 0, 100));
        createSimulationButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        createSimulationButton.setPreferredSize(new Dimension(300, 50));


        createSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click action here, e.g., navigate to the testing page
                // You can use a new JFrame for the testing page
            }
        });

        mainPanel.add(createSimulationButton);
        add(mainPanel, BorderLayout.CENTER);

        //Create a PDM footer
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 0, 0, 128));
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

