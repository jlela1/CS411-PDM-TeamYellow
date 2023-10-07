package frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationUserInputGUI extends JFrame {

    private JSlider capacitySlider;
    private JLabel capacityValue;
    private JSlider garage1CapacitySlider;
    private JLabel garage1CapacityValue;
    private JSlider garage2CapacitySlider;
    private JLabel garage2CapacityValue;
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

    public SimulationUserInputGUI() {

        setTitle("PDM Garage Simulation"); // Updated the title
        setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximize the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create and initialize components
        capacitySlider = createSlider(1, 500, 1, gbc);
        capacityValue = createLabel("1", gbc);
        garage1CapacitySlider = createSlider(1, 500, 1, gbc);
        garage1CapacityValue = createLabel("1", gbc);
        garage2CapacitySlider = createSlider(1, 500, 1, gbc);
        garage2CapacityValue = createLabel("1", gbc);
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

        JLabel footballStadiumCapacityLabel = new JLabel("Football Stadium Capacity:");
        JLabel garage1CapacityLabel = new JLabel("43rd & Elkhorn Capacity:");
        JLabel garage2CapacityLabel = new JLabel("43rd & Bluestone Capacity:");
        JLabel vehiclesEnteringPerMinuteLabel = new JLabel("Vehicles Entering per Minute:");
        JLabel averageTimeToParkLabel = new JLabel("Average Time to Park (minutes):");
        JLabel averageTimeParkedLabel = new JLabel("Average Time Parked (minutes):");
        JLabel simulationDurationLabel = new JLabel("Simulation Duration (minutes):");

        // Add event listeners
        addSliderChangeListener(capacitySlider, capacityValue);
        addSliderChangeListener(garage1CapacitySlider, garage1CapacityValue);
        addSliderChangeListener(garage2CapacitySlider, garage2CapacityValue);
        addSliderChangeListener(vehiclesSlider, vehiclesValue);
        addSliderChangeListener(timeToParkSlider, timeToParkValue);
        addSliderChangeListener(parkTimeSlider, parkTimeValue);
        addSliderChangeListener(durationSlider, durationValue);

        // Place components in the grid
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(footballStadiumCapacityLabel, gbc);
        gbc.gridx = 1;
        add(capacitySlider, gbc);
        gbc.gridx = 2;
        add(capacityValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(garage1CapacityLabel, gbc);
        gbc.gridx = 1;
        add(garage1CapacitySlider, gbc);
        gbc.gridx = 2;
        add(garage1CapacityValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(garage2CapacityLabel, gbc);
        gbc.gridx = 1;
        add(garage2CapacitySlider, gbc);
        gbc.gridx = 2;
        add(garage2CapacityValue, gbc);

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
        gbc.gridy = 6;
        add(simulationDurationLabel, gbc);
        gbc.gridx = 1;
        add(durationSlider, gbc);
        gbc.gridx = 2;
        add(durationValue, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(startSimulationButton, gbc);

        gbc.gridy = 12;
        add(seeTrendsButton, gbc);

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get the values from the sliders and other components
                int capacity = capacitySlider.getValue();
                int garage1Capacity = garage1CapacitySlider.getValue();
                int garage2Capacity = garage2CapacitySlider.getValue();
                int vehiclesPerMinute = vehiclesSlider.getValue();
                int avgTimeToPark = timeToParkSlider.getValue();
                int avgParkTime = parkTimeSlider.getValue();
                int simulationDuration = durationSlider.getValue();

                getContentPane().remove(capacitySlider);
                getContentPane().remove(garage1CapacitySlider);
                getContentPane().remove(garage2CapacitySlider);
                getContentPane().remove(vehiclesSlider);
                getContentPane().remove(timeToParkSlider);
                getContentPane().remove(parkTimeSlider);
                getContentPane().remove(durationSlider);
                getContentPane().remove(startSimulationButton);
                getContentPane().remove(capacityValue);
                getContentPane().remove(garage1CapacityValue);
                getContentPane().remove(garage2CapacityValue);
                getContentPane().remove(vehiclesValue);
                getContentPane().remove(durationValue);
                getContentPane().remove(parkTimeValue);
                getContentPane().remove(timeToParkValue);
                getContentPane().remove(footballStadiumCapacityLabel);
                getContentPane().remove(garage1CapacityLabel);
                getContentPane().remove(garage2CapacityLabel);
                getContentPane().remove(vehiclesEnteringPerMinuteLabel);
                getContentPane().remove(averageTimeToParkLabel);
                getContentPane().remove(averageTimeParkedLabel);
                getContentPane().remove(simulationDurationLabel);

                // Create an instance of SimulationGUI
                SimulationGUI simulationGUI = new SimulationGUI(capacity, garage1Capacity, garage2Capacity, vehiclesPerMinute,
                        avgTimeToPark, avgParkTime, simulationDuration, gbc);

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
