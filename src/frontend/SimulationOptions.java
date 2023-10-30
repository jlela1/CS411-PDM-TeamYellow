package frontend;

import backend.database.Garage;
import backend.database.numVehEnteringRate;
import backend.database.trendsGarage;

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
import java.awt.Insets;
import java.util.ArrayList;

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

        JPanel componentsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 30, 30, 30);


        //Custom Sim Button
        JButton CustomSimulationButton = new JButton("Custom Simulation");
        CustomSimulationButton.setFont(new Font("Roboto", Font.BOLD, 16));
        CustomSimulationButton.setForeground(Color.BLACK);
        CustomSimulationButton.setBackground(new Color(98, 139, 145, 250));
        CustomSimulationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        CustomSimulationButton.setFocusPainted(false);
        CustomSimulationButton.setPreferredSize(new Dimension(250, 50));
        CustomSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        //simulation preset title
        JLabel simTitle = new JLabel("        Simulation Presets");
        simTitle.setFont(new Font("Roboto", Font.PLAIN, 20));
        simTitle.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        simTitle.setSize(250, 50);
        simTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        simTitle.setAlignmentY(JLabel.CENTER_ALIGNMENT);


        //Normal Day Button
        JButton NormalDayButton = new JButton("Normal Day at ODU");
        NormalDayButton.setFont(new Font("Roboto", Font.BOLD, 16));
        NormalDayButton.setForeground(Color.BLACK);
        NormalDayButton.setBackground(new Color(98, 139, 145, 250));
        NormalDayButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        NormalDayButton.setFocusPainted(false);
        NormalDayButton.setPreferredSize(new Dimension(250, 50));
        NormalDayButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Fire in Elkhorn Garage
        JButton ElkFireButton = new JButton("Fire in Elkhorn Garage");
        ElkFireButton.setFont(new Font("Roboto", Font.BOLD, 16));
        ElkFireButton.setForeground(Color.BLACK);
        ElkFireButton.setBackground(new Color(98, 139, 145, 250));
        ElkFireButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        ElkFireButton.setFocusPainted(false);
        ElkFireButton.setPreferredSize(new Dimension(250, 50));
        ElkFireButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Football Game Button
        JButton FootballButton = new JButton("Football Game");
        FootballButton.setFont(new Font("Roboto", Font.BOLD, 16));
        FootballButton.setForeground(Color.BLACK);
        FootballButton.setBackground(new Color(98, 139, 145, 250));
        FootballButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        FootballButton.setFocusPainted(false);
        FootballButton.setPreferredSize(new Dimension(250, 50));
        FootballButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton HomeButton = new JButton("Home");
        HomeButton.setFont(new Font("Roboto", Font.BOLD, 16));
        HomeButton.setForeground(Color.BLACK);
        HomeButton.setBackground(new Color(114, 145, 98, 250));
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
                GarageManager simulationStart = new GarageManager();
            }
        });

        NormalDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //create a simulation for a normal ODU day:

                //create garages:

                ArrayList<Garage> garages = new ArrayList<Garage>();

                int time = 720; //time for the simulation: 12 hours

                Garage garage1 = new Garage("43rd & Elkhorn Ave", 655);
                garage1.setGarageID(0);
                garage1.setAvgParkingDuration(180);
                garage1.setNumVehiclesEnteringPerMin(1); //starts at 1 car per min at 7am
                garage1.setAvgTimeToPark(10);
                //set variable vehicles per minute
                garage1.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
                garage1.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
                garage1.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
                garage1.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
                garage1.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
                garage1.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

                Garage garage2 = new Garage("Constant Center South", 1535);
                garage2.setGarageID(1);
                garage2.setAvgParkingDuration(180);
                garage2.setNumVehiclesEnteringPerMin(1);
                garage2.setAvgTimeToPark(10);
                //set variable vehicles per minute
                garage2.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
                garage2.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
                garage2.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
                garage2.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
                garage2.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
                garage2.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

                Garage garage3 = new Garage("Constant Center North", 1045);
                garage3.setGarageID(2);
                garage3.setAvgParkingDuration(180);
                garage3.setNumVehiclesEnteringPerMin(1);
                garage3.setAvgTimeToPark(10);
                //set variable vehicles per minute
                garage3.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
                garage3.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
                garage3.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
                garage3.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
                garage3.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
                garage3.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

                Garage garage4 = new Garage("49th Street Stadium", 745);
                garage4.setGarageID(3);
                garage4.setAvgParkingDuration(180);
                garage4.setNumVehiclesEnteringPerMin(1);
                garage4.setAvgTimeToPark(10);
                //set variable vehicles per minute
                garage4.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
                garage4.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
                garage4.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
                garage4.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
                garage4.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
                garage4.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

                garages.add(0, garage1);
                garages.add(1, garage2);
                garages.add(2, garage3);
                garages.add(3, garage4);

                dispose();
                // Create an instance of SimulationGUI
                SimulationGUI simulationGUI = new SimulationGUI(garages, time, new BorderLayout());

                // Make the SimulationGUI visible
                simulationGUI.setVisible(true);

            }
        });

        ElkFireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        FootballButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
