import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GarageSimulationApp extends JFrame {
    private JSlider capacitySlider;
    private JLabel capacityValue;
    private JSlider garage1CapacitySlider;
    private JLabel garage1CapacityValue;
    private JSlider garage2CapacitySlider;
    private JLabel garage2CapacityValue;
    private JSlider vehiclesSlider;
    private JLabel vehiclesValue;
    private JSlider parkTimeSlider;
    private JLabel parkTimeValue;
    private JSlider durationSlider;
    private JLabel durationValue;
    private JButton startSimulationButton;

    public GarageSimulationApp() {
        setTitle("Garage Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create and initialize components
        capacitySlider = createSlider(1, 100, 1, gbc);
        capacityValue = createLabel("1", gbc);
        garage1CapacitySlider = createSlider(1, 100, 1, gbc);
        garage1CapacityValue = createLabel("1", gbc);
        garage2CapacitySlider = createSlider(1, 100, 1, gbc);
        garage2CapacityValue = createLabel("1", gbc);
        vehiclesSlider = createSlider(1, 60, 1, gbc);
        vehiclesValue = createLabel("1", gbc);
        parkTimeSlider = createSlider(1, 60, 1, gbc);
        parkTimeValue = createLabel("1", gbc);
        durationSlider = createSlider(1, 120, 1, gbc);
        durationValue = createLabel("1", gbc);
        startSimulationButton = createButton("Start Simulation", gbc);

        // Add event listeners
        addSliderChangeListener(capacitySlider, capacityValue);
        addSliderChangeListener(garage1CapacitySlider, garage1CapacityValue);
        addSliderChangeListener(garage2CapacitySlider, garage2CapacityValue);
        addSliderChangeListener(vehiclesSlider, vehiclesValue);
        addSliderChangeListener(parkTimeSlider, parkTimeValue);
        addSliderChangeListener(durationSlider, durationValue);

        startSimulationButton.addActionListener(e -> startSimulation());

        // Place components in the grid
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Football Stadium:"), gbc);
        gbc.gridx = 1;
        add(capacitySlider, gbc);
        gbc.gridx = 2;
        add(capacityValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Garage 1 Capacity:"), gbc);
        gbc.gridx = 1;
        add(garage1CapacitySlider, gbc);
        gbc.gridx = 2;
        add(garage1CapacityValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Garage 2 Capacity:"), gbc);
        gbc.gridx = 1;
        add(garage2CapacitySlider, gbc);
        gbc.gridx = 2;
        add(garage2CapacityValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Vehicles Entering per Minute:"), gbc);
        gbc.gridx = 1;
        add(vehiclesSlider, gbc);
        gbc.gridx = 2;
        add(vehiclesValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Average Park Time (minutes):"), gbc);
        gbc.gridx = 1;
        add(parkTimeSlider, gbc);
        gbc.gridx = 2;
        add(parkTimeValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Simulation Duration (minutes):"), gbc);
        gbc.gridx = 1;
        add(durationSlider, gbc);
        gbc.gridx = 2;
        add(durationValue, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(startSimulationButton, gbc);
    }

    private JSlider createSlider(int min, int max, int value, GridBagConstraints gbc) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMajorTickSpacing((max - min) / 4);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
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

    private void startSimulation() {
        // Simulation logic goes here
        Random random = new Random();
        int newCapacity = random.nextInt(100) + 1;
        int newVehicles = random.nextInt(60) + 1;
        int newParkTime = random.nextInt(60) + 1;
        int newDuration = random.nextInt(120) + 1;
        int newGarage1Capacity = random.nextInt(100) + 1;
        int newGarage2Capacity = random.nextInt(100) + 1;

        animateSlider(capacitySlider, newCapacity);
        animateSlider(garage1CapacitySlider, newGarage1Capacity);
        animateSlider(garage2CapacitySlider, newGarage2Capacity);
        animateSlider(vehiclesSlider, newVehicles);
        animateSlider(parkTimeSlider, newParkTime);
        animateSlider(durationSlider, newDuration);
    }

    private void animateSlider(JSlider slider, int value) {
        int stepDuration = 50;
        int totalSteps = 20;
        int stepValue = (value - slider.getValue()) / totalSteps;

        Timer timer = new Timer(stepDuration, new ActionListener() {
            int currentStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep < totalSteps) {
                    slider.setValue(slider.getValue() + stepValue);
                    currentStep++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GarageSimulationApp app = new GarageSimulationApp();
            app.setVisible(true);
        });
    }
}
