package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationGUI extends JFrame {

    private GridBagConstraints gbc;
    private JLabel timeLabel;
    private JLabel capacityLabel;
    private JLabel garage1CapacityLabel;
    private JLabel garage2CapacityLabel;
    private JButton seeTrendsButton;

    // Initialize GUI for active portion of simulation
    public SimulationGUI(int capacityValue, int garage1CapacityValue, int garage2CapacityValue, int vehiclesPerMinute,
                         int avgTimeToPark, int avgParkTime, int simulationDuration, GridBagConstraints gbcReturn) {

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gbc = gbcReturn;

        setLayout(new GridBagLayout());

        int time = 420;

        timeLabel = createLabel("Time: " + convertMinutesToAMPM(time), gbc);
        capacityLabel = createLabel("Football Stadium Capacity: " + "0/" + Integer.toString(capacityValue), gbc);
        garage1CapacityLabel = createLabel("43rd & Elkhorn Capacity:  " + "0/" + Integer.toString(garage1CapacityValue), gbc);
        garage2CapacityLabel = createLabel("43rd & Bluestone Capacity: " + "0/" + Integer.toString(garage2CapacityValue), gbc);

        seeTrendsButton = createButton("See Trends", gbc);
        seeTrendsButton.setVisible(true);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(timeLabel, gbc);
        gbc.gridy = 8;
        add(capacityLabel, gbc);
        gbc.gridy = 9;
        add(garage1CapacityLabel, gbc);
        gbc.gridy = 10;
        add(garage2CapacityLabel, gbc);
        gbc.gridy = 12;
        add(seeTrendsButton, gbc);

        // Repaint the frame to update the changes
        getContentPane().validate();
        getContentPane().repaint();

        seeTrendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    trendsGUI trendsPage = new trendsGUI();
                    trendsPage.setVisible(true);
                });
            }
        });

        GarageSimulation garageSimulation = new GarageSimulation(this, capacityValue, garage1CapacityValue, garage2CapacityValue, vehiclesPerMinute,
                avgTimeToPark, avgParkTime, simulationDuration, time);

    }

    // Called by simulation driver code at the end of each simulation tick
    public void updateSimLabels(int footballStadiumCapacity, int garage1Capacity, int garage2Capacity, int footballStadiumOccupancy,
                                int garage1Occupancy, int garage2Occupancy, int time) {

        timeLabel.setText("Time: " + convertMinutesToAMPM(time));
        capacityLabel.setText("Football Stadium Capacity: " + footballStadiumOccupancy + "/" + footballStadiumCapacity);
        garage1CapacityLabel.setText("43rd & Elkhorn Capacity: " + garage1Occupancy + "/" + garage1Capacity);
        garage2CapacityLabel.setText("43rd & Bluestone Capacity: " + garage2Occupancy + "/" + garage2Capacity);

    }

    private JLabel createLabel(String text, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        gbc.weightx = 0.0;
        gbc.gridx = 2;
        return label;
    }

    private JButton createButton(String text, GridBagConstraints gbc) {
        JButton button = new JButton(text);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy++;
        return button;
    }

    public static String convertMinutesToAMPM(int minutes) {
        if (minutes < 0 || minutes >= 24 * 60) {
            throw new IllegalArgumentException("Invalid input: minutes must be between 0 and 1439.");
        }

        // Convert minutes to hours and minutes
        int hours = minutes / 60;
        int mins = minutes % 60;

        // Determine AM or PM
        String amPm;
        if (hours < 12) {
            amPm = "AM";
            if (hours == 0) {
                hours = 12;
            }
        } else {
            amPm = "PM";
            if (hours > 12) {
                hours -= 12;
            }
        }

        // Format the time as HH:MM AM/PM
        String formattedTime = String.format("%02d:%02d %s", hours, mins, amPm);

        return formattedTime;
    }

}
