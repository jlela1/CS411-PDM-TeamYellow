package frontend;

import backend.database.Garage;
import backend.database.Presets.Fire;
import backend.database.Presets.Football;
import backend.database.Presets.NormalDay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimulationOptions extends JFrame {

    public SimulationOptions() {

        setTitle("PDM Simulation Options");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 6.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        // Header Panel
        JPanel header = PDMPanels.createHeader("PDM Simulation Options");



        header.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        add(header, BorderLayout.NORTH);
        backgroundPanel.add(header, BorderLayout.NORTH);

        JPanel componentsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        componentsPanel.setOpaque(false);
        backgroundPanel.add(componentsPanel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 30, 30, 30);


        //Custom Sim Button
        JButton CustomSimulationButton = new JButton("Custom Simulation");
        CustomSimulationButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        CustomSimulationButton.setForeground(Color.BLACK);
        CustomSimulationButton.setBackground(new Color(164, 196, 201, 250));
        CustomSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        CustomSimulationButton.setFocusPainted(false);
        CustomSimulationButton.setPreferredSize(new Dimension(250, 50));
        CustomSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        //simulation preset title
        JLabel simTitle = new JLabel("Simulation Presets");
        simTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
        simTitle.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        simTitle.setSize(250, 50);
        simTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        simTitle.setAlignmentY(JLabel.CENTER_ALIGNMENT);


        //Normal Day Button
        JButton NormalDayButton = new JButton("Normal Day at ODU");
        NormalDayButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        NormalDayButton.setForeground(Color.BLACK);
        NormalDayButton.setBackground(new Color(164, 196, 201, 250));
        NormalDayButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        NormalDayButton.setFocusPainted(false);
        NormalDayButton.setPreferredSize(new Dimension(250, 50));
        NormalDayButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Fire in Elkhorn Garage
        JButton ElkFireButton = new JButton("Garage Closure");
        ElkFireButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        ElkFireButton.setForeground(Color.BLACK);
        ElkFireButton.setBackground(new Color(164, 196, 201, 250));
        ElkFireButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        ElkFireButton.setFocusPainted(false);
        ElkFireButton.setPreferredSize(new Dimension(250, 50));
        ElkFireButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Football Game Button
        JButton FootballButton = new JButton("Football Game");
        FootballButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        FootballButton.setForeground(Color.BLACK);
        FootballButton.setBackground(new Color(164, 196, 201, 250));
        FootballButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        FootballButton.setFocusPainted(false);
        FootballButton.setPreferredSize(new Dimension(250, 50));
        FootballButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton HomeButton = new JButton("Home");
        HomeButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        HomeButton.setForeground(Color.BLACK);
        HomeButton.setBackground(new Color(178, 225, 153, 250));
        HomeButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        HomeButton.setFocusPainted(false);
        HomeButton.setPreferredSize(new Dimension(250, 50));
        HomeButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        gbc.gridx = 1;
        gbc.gridy = 1;
        componentsPanel.add(CustomSimulationButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        componentsPanel.add(simTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        componentsPanel.add(NormalDayButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        componentsPanel.add(ElkFireButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        componentsPanel.add(FootballButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        componentsPanel.add(HomeButton, gbc);

        add(componentsPanel, BorderLayout.CENTER);


        //Create a PDM footer
        JPanel footer = PDMPanels.createFooter();
        add(footer, BorderLayout.SOUTH);

        setLocationRelativeTo(null);


        CustomSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                ArrayList<Garage> newGarageList = new ArrayList<>();

                GarageManager simulationStart = new GarageManager(newGarageList, 0);
            }
        });

        NormalDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new NormalDay();

            }
        });

        ElkFireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new Fire();
            }
        });

        FootballButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new Football();
            }
        });

        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimulationOptions simOptions = new SimulationOptions();
                simOptions.setVisible(true);
            }
        });
    }


}
