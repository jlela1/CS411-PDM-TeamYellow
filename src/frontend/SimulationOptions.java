package frontend;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimulationOptions extends JFrame {

    public SimulationOptions() {

        setTitle("PDM Simulation Options");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel header = PDMPanels.createHeader("PDM Simulation Options");
        add(header, BorderLayout.NORTH);

        header.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        add(header, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 40));
        contentPanel.setBackground(Color.WHITE);

        //Custom Sim Button
        JButton CustomSimulationButton = new JButton("Custom Simulation");
        CustomSimulationButton.setFont(new Font("Roboto", Font.BOLD, 16));
        CustomSimulationButton.setForeground(Color.BLACK);
        CustomSimulationButton.setBackground(new Color(98, 139, 145, 250));
        CustomSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        CustomSimulationButton.setFocusPainted(false);
        CustomSimulationButton.setPreferredSize(new Dimension(250, 50));
        CustomSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        CustomSimulationButton.setAlignmentY(Component.CENTER_ALIGNMENT);


        //simulation preset title
        JLabel simTitle = new JLabel("Simulation Presets");
        simTitle.setFont(new Font("Roboto", Font.PLAIN, 20));
        simTitle.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        simTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        simTitle.setAlignmentY(Component.CENTER_ALIGNMENT);


        //Normal Day Button
        JButton NormalDayButton = new JButton("Normal Day at ODU");
        NormalDayButton.setFont(new Font("Roboto", Font.BOLD, 16));
        NormalDayButton.setForeground(Color.BLACK);
        NormalDayButton.setBackground(new Color(98, 139, 145, 250));
        NormalDayButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        NormalDayButton.setFocusPainted(false);
        NormalDayButton.setPreferredSize(new Dimension(250, 50));
        NormalDayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        NormalDayButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Fire in Elkhorn Garage
        JButton ElkFireButton = new JButton("Fire in Elkhorn Garage");
        ElkFireButton.setFont(new Font("Roboto", Font.BOLD, 16));
        ElkFireButton.setForeground(Color.BLACK);
        ElkFireButton.setBackground(new Color(98, 139, 145, 250));
        ElkFireButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        ElkFireButton.setFocusPainted(false);
        ElkFireButton.setPreferredSize(new Dimension(250, 50));
        ElkFireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ElkFireButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Football Game Button
        JButton FootballButton = new JButton("Football Game");
        FootballButton.setFont(new Font("Roboto", Font.BOLD, 16));
        FootballButton.setForeground(Color.BLACK);
        FootballButton.setBackground(new Color(98, 139, 145, 250));
        FootballButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        FootballButton.setFocusPainted(false);
        FootballButton.setPreferredSize(new Dimension(250, 50));
        FootballButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        FootballButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton HomeButton = new JButton("Home");
        HomeButton.setFont(new Font("Roboto", Font.BOLD, 16));
        HomeButton.setForeground(Color.BLACK);
        HomeButton.setBackground(new Color(114, 145, 98, 250));
        HomeButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        HomeButton.setFocusPainted(false);
        HomeButton.setPreferredSize(new Dimension(250, 50));
        HomeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        HomeButton.setAlignmentY(Component.CENTER_ALIGNMENT);


        contentPanel.add(CustomSimulationButton);
        contentPanel.add(simTitle);
        contentPanel.add(NormalDayButton);
        contentPanel.add(ElkFireButton);
        contentPanel.add(FootballButton);
        contentPanel.add(HomeButton);
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
                SimulationOptions simOptions = new SimulationOptions();
                simOptions.setVisible(true);
            }
        });
    }

}
