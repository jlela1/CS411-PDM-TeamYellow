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

        JPanel header = PDMPanels.createHeader("PDM Business Dashboard");
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
        //contentPanel.setBackground(Color.WHITE);

        JButton createSimulationButton = new JButton("Create Simulation");
        createSimulationButton.setFont(new Font("Roboto", Font.BOLD, 16));
        createSimulationButton.setForeground(Color.BLACK);
        createSimulationButton.setBackground(new Color(23, 11, 204, 163));
        createSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        createSimulationButton.setFocusPainted(false);
        createSimulationButton.setPreferredSize(new Dimension(250, 50));
        createSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logOutB= new JButton("Logout");
        logOutB.setFont(new Font("Roboto", Font.BOLD, 16));
        logOutB.setForeground(Color.BLACK);
        logOutB.setBackground(new Color(241, 49, 49, 163));
        logOutB.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        logOutB.setFocusPainted(false);
        logOutB.setPreferredSize(new Dimension(250, 50));
        logOutB.setAlignmentX(Component.CENTER_ALIGNMENT);



        createSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SimulationOptions simulationOptions = new SimulationOptions();
                simulationOptions.setVisible(true);
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

        contentPanel.add(createSimulationButton);
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
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);
            }
        });
    }
}
