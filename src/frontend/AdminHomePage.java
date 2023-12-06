package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import frontend.SimulationUserInputGUI;
import backend.database.Garage;

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
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 11.png");
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

        JLabel welcomeLabel = new JLabel("                                  Welcome to the Parking Demand Management Dashboard!");
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
        //contentPanel.setBackground(Color.WHITE);

        JButton createSimulationButton = new JButton("Create Simulation");
        createSimulationButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        createSimulationButton.setForeground(Color.BLACK);
        createSimulationButton.setBackground(new Color(50, 119, 222, 255));
        createSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        createSimulationButton.setFocusPainted(false);
        createSimulationButton.setPreferredSize(new Dimension(250, 50));
        createSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton businessFeedbackButton = new JButton("See Feedback");
        businessFeedbackButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        businessFeedbackButton.setForeground(Color.BLACK);
        businessFeedbackButton.setBackground(new Color(114, 145, 98, 250));
        businessFeedbackButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        businessFeedbackButton.setFocusPainted(false);
        businessFeedbackButton.setPreferredSize(new Dimension(250, 50));
        businessFeedbackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logOutB= new JButton("Logout");
        logOutB.setFont(new Font("Monospaced", Font.BOLD, 16));
        logOutB.setForeground(Color.BLACK);
        logOutB.setBackground(new Color(204, 11, 91, 255));
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

        businessFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ArrayList<Garage> garages = new ArrayList<>();
                businessFeedback BusinessFeedback = new businessFeedback("43rd & Elkhorn Ave", 4, garages );
                BusinessFeedback.setVisible(true);
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
        contentPanel.add(businessFeedbackButton);
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
