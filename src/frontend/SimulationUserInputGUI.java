package frontend;
import backend.database.Garage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SimulationUserInputGUI extends JFrame {

    private JSlider vehiclesSlider;
    private JLabel vehiclesValue;
    private JSlider parkTimeSlider;
    private JSlider timeToParkSlider;
    private JLabel timeToParkValue;
    private JLabel parkTimeValue;
    private JSlider durationSlider;
    private JLabel durationValue;
    private JButton startSimulationButton;
    private JButton seeTrendsButton;

    private JComboBox<String> garageSelector;

    public SimulationUserInputGUI(ArrayList<Garage> garages) {

        setTitle("PDM Garage Simulation"); // Updated the title
        setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximize the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create and initialize components
        String[] garageNames = new String[garages.size()];
        for (int i = 0; i < garages.size(); i++) {
            garageNames[i] = garages.get(i).getName();
        }
        garageSelector = new JComboBox<>(garageNames);

        JLabel selectGarageLabel = new JLabel("Select garage");
        selectGarageLabel.setFont(new Font("Arial", Font.BOLD, 26));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        add(selectGarageLabel, gbc);

        gbc.gridx = -2;
        gbc.gridy++;
        add(garageSelector, gbc);

        vehiclesSlider = createSlider(1, 60, 1, gbc);
        vehiclesValue = createLabel("1", gbc);
        timeToParkSlider = createSlider(1, 60, 1, gbc);
        timeToParkValue = createLabel("1", gbc);
        parkTimeSlider = createSlider(1, 180, 1, gbc);
        parkTimeValue = createLabel("1", gbc);
        durationSlider = createSlider(1, 720, 1, gbc);
        durationValue = createLabel("1", gbc);
        startSimulationButton = createButton("Start Simulation", gbc);
        seeTrendsButton = createButton("See Trends", gbc);
        seeTrendsButton.setVisible(false);


        JLabel vehiclesEnteringPerMinuteLabel = new JLabel("Vehicles Entering per Minute:");
        JLabel averageTimeToParkLabel = new JLabel("Average Time to Park (minutes):");
        JLabel averageTimeParkedLabel = new JLabel("Average Time Parked (minutes):");
        JLabel simulationDurationLabel = new JLabel("Simulation Duration (minutes):");

        addSliderChangeListener(vehiclesSlider, vehiclesValue);
        addSliderChangeListener(timeToParkSlider, timeToParkValue);
        addSliderChangeListener(parkTimeSlider, parkTimeValue);
        addSliderChangeListener(durationSlider, durationValue);

        // Place components in the grid
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridx = 2;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridx = 2;

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridx = 2;

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(vehiclesEnteringPerMinuteLabel, gbc);
        gbc.gridx = 1;
        add(vehiclesSlider, gbc);
        gbc.gridx = 2;
        add(vehiclesValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(averageTimeToParkLabel, gbc);
        gbc.gridx = 1;
        add(timeToParkSlider, gbc);
        gbc.gridx = 2;
        add(timeToParkValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(averageTimeParkedLabel, gbc);
        gbc.gridx = 1;
        add(parkTimeSlider, gbc);
        gbc.gridx = 2;
        add(parkTimeValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        add(simulationDurationLabel, gbc);
        gbc.gridx = 1;
        add(durationSlider, gbc);
        gbc.gridx = 2;
        add(durationValue, gbc);


        gbc.gridx = 0;
        gbc.gridy = -10;
        add(startSimulationButton, gbc);

        gbc.gridy = 12;
        add(seeTrendsButton, gbc);

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedGarageName = (String) garageSelector.getSelectedItem();
                Garage selectedGarage = null;

                // Declare selectedGarageName outside the if block
                for (Garage garage : garages) {
                    if (garage.getName().equals(selectedGarageName)) {
                        selectedGarage = garage;
                        break;
                    }
                }

                if (selectedGarage == null) {
                    // Handle the case where no garage is selected
                    JOptionPane.showMessageDialog(SimulationUserInputGUI.this, "Please select a garage.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Get the values from the sliders and other components
                int vehiclesPerMinute = vehiclesSlider.getValue();
                int avgTimeToPark = timeToParkSlider.getValue();
                int avgParkTime = parkTimeSlider.getValue();
                int simulationDuration = durationSlider.getValue();

                getContentPane().remove(vehiclesSlider);
                getContentPane().remove(timeToParkSlider);
                getContentPane().remove(parkTimeSlider);
                getContentPane().remove(durationSlider);
                getContentPane().remove(startSimulationButton);
                getContentPane().remove(vehiclesValue);
                getContentPane().remove(durationValue);
                getContentPane().remove(parkTimeValue);
                getContentPane().remove(timeToParkValue);
                getContentPane().remove(vehiclesEnteringPerMinuteLabel);
                getContentPane().remove(averageTimeToParkLabel);
                getContentPane().remove(averageTimeParkedLabel);
                getContentPane().remove(simulationDurationLabel);

                // Create an instance of SimulationGUI
                SimulationGUI simulationGUI = new SimulationGUI(garages, vehiclesPerMinute,
                        avgTimeToPark, avgParkTime, simulationDuration, new BorderLayout());

                // Make the SimulationGUI visible
                simulationGUI.setVisible(true);

            }
        });

    }

    private JSlider createSlider(int min, int max, int value, GridBagConstraints gbc) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMajorTickSpacing((max - min) / 4);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        gbc.weightx = 1.0;
        gbc.gridx = 1;
        return slider;
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

    private void addSliderChangeListener(JSlider slider, JLabel label) {
        slider.addChangeListener(e -> label.setText(String.valueOf(slider.getValue())));
    }

}
