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
    private JButton saveGarageButton;
    private JButton startSimulationButton;
    private JButton seeTrendsButton;

    private JComboBox<String> garageSelectorComboBox;

    public SimulationUserInputGUI(ArrayList<Garage> garages, int presetType) {

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 9.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);

        // Create header panel
        JPanel headingPanel = PDMPanels.createHeader("PDM");
        add(headingPanel, BorderLayout.NORTH);
        JLabel simulationLabel = new JLabel("Garage Simulation Settings");
        simulationLabel.setForeground(Color.DARK_GRAY);
        simulationLabel.setBackground(Color.lightGray);
        simulationLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        simulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 7)));
        headingPanel.add(simulationLabel);

        JPanel footerPanel = PDMPanels.createFooter();
        //footerPanel.setOpaque(false);
        backgroundPanel.add(headingPanel, BorderLayout.NORTH);
        backgroundPanel.add(footerPanel, BorderLayout.SOUTH);

        // topPanel contains selectGarageLabel, garageSelectorComboBox, and slidersPanel.
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.add(Box.createRigidArea(new Dimension(getWidth(), getHeight())));
        backgroundPanel.add(topPanel, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(2000, 1000));

        JLabel selectGarageLabel = createLabel("Select Garage:");
        selectGarageLabel.setOpaque(false);

        selectGarageLabel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        topPanel.add(selectGarageLabel, BorderLayout.NORTH);

        // Initialize the garageSelectorComboBox
        garageSelectorComboBox = new JComboBox<String>();
        garageSelectorComboBox.setOpaque(false);

        garageSelectorComboBox.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        // Add items to the combo box
        for (Garage garage : garages) {
            garageSelectorComboBox.addItem(garage.getName());
        }

        // Add combo box to the top of the center panel
        topPanel.add(garageSelectorComboBox, BorderLayout.NORTH);

        //backgroundPanel.add(topPanel, BorderLayout.NORTH);

        JPanel slidersPanel = new JPanel();
        slidersPanel.setOpaque(false);

        topPanel.add(slidersPanel, BorderLayout.NORTH);
        slidersPanel.setPreferredSize(new Dimension(2000, 380));

        slidersPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        vehiclesSlider = createSlider(1, 60, 1, gbc);
        vehiclesValue = createLabelGBC("1", gbc);
        timeToParkSlider = createSlider(1, 60, 1, gbc);
        timeToParkValue = createLabelGBC("1", gbc);
        parkTimeSlider = createSlider(1, 180, 1, gbc);
        parkTimeValue = createLabelGBC("1", gbc);

        JLabel vehiclesEnteringPerMinuteLabel = new JLabel("Vehicles Entering per Minute:");
        vehiclesEnteringPerMinuteLabel.setFont(new Font("Monospaced", Font.BOLD, 18 ));
        JLabel averageTimeToParkLabel = new JLabel("Average Time to Park (minutes):");
        averageTimeToParkLabel.setFont(new Font("Monospaced", Font.BOLD, 18 ));
        JLabel averageTimeParkedLabel = new JLabel("Average Time Parked (minutes):");
        averageTimeParkedLabel.setFont(new Font("Monospaced", Font.BOLD, 18 ));

        gbc.gridx = -1;
        gbc.gridy = 0;
        slidersPanel.add(vehiclesEnteringPerMinuteLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        slidersPanel.add(vehiclesSlider, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        slidersPanel.add(vehiclesValue, gbc);
        gbc.gridx = -1;
        gbc.gridy = 4;
        slidersPanel.add(averageTimeToParkLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        slidersPanel.add(timeToParkSlider, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        slidersPanel.add(timeToParkValue, gbc);
        gbc.gridx = -1;
        gbc.gridy = 8;
        slidersPanel.add(averageTimeParkedLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        slidersPanel.add(parkTimeSlider, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        slidersPanel.add(parkTimeValue, gbc);

        saveGarageButton = createButton("Save Garage", gbc);
        saveGarageButton.setBackground(Color.green);

        gbc.gridx = 0;
        gbc.gridy = 11;
        slidersPanel.add(saveGarageButton, gbc);

        JLabel simulationDurationLabel = new JLabel("Simulation Duration (minutes):");
        simulationDurationLabel.setFont(new Font("Monospaced", Font.BOLD, 18 ));

        gbc.gridx = 0;
        gbc.gridy = 12;
        slidersPanel.add(simulationDurationLabel, gbc);

        durationSlider = createSlider(1, 720, 1, gbc);
        durationValue = createLabelGBC("1", gbc);
        //durationValue.setBackground(Color.lightGray);

        gbc.gridx = 0;
        gbc.gridy = 13;
        slidersPanel.add(durationSlider, gbc);

        gbc.gridx = 1;
        gbc.gridy = 13;
        slidersPanel.add(durationValue, gbc);

        startSimulationButton = createButton("Start Simulation", gbc);
        startSimulationButton.setBackground(Color.ORANGE);

        gbc.gridx = 0;
        gbc.gridy = 14;
        slidersPanel.add(startSimulationButton, gbc);

        addSliderChangeListener(vehiclesSlider, vehiclesValue);
        addSliderChangeListener(timeToParkSlider, timeToParkValue);
        addSliderChangeListener(parkTimeSlider, parkTimeValue);
        addSliderChangeListener(durationSlider, durationValue);

        vehiclesSlider.setOpaque(false);
        timeToParkSlider.setOpaque(false);
        parkTimeSlider.setOpaque(false);
        durationSlider.setOpaque(false);


        saveGarageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Equal to the string of the currently selected garage in the dropdown menu
                String selectedGarage = (String) garageSelectorComboBox.getSelectedItem();

                Garage garageToSave = null;

                for (Garage garage : garages) {
                    assert selectedGarage != null;
                    if (selectedGarage.equals(garage.getName())) {
                        garage.setNumVehiclesEnteringPerMin(Integer.parseInt(vehiclesValue.getText()));
                        garage.setAvgTimeToPark(Integer.parseInt(timeToParkValue.getText()));
                        garage.setAvgParkingDuration(Integer.parseInt(parkTimeValue.getText()));
                        vehiclesSlider.setValue(1);
                        timeToParkSlider.setValue(1);
                        parkTimeSlider.setValue(1);
                        durationSlider.setValue(1);
                    }
                }

            }
        });

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                SimulationGUI simulationGUI = new SimulationGUI(garages, Integer.parseInt(durationValue.getText()), new BorderLayout(), presetType);

                // Make the SimulationGUI visible
                simulationGUI.setVisible(true);
                dispose();

            }
        });

    }

    private JLabel createLabelGBC(String text, GridBagConstraints gbc) {

        JLabel label = new JLabel(text);
        gbc.weightx = 0.0;
        gbc.gridx = 2;
        return label;

    }

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        // Increase font size
        Font labelFont = new Font("Monospaced", Font.BOLD, 18);
        label.setFont(labelFont);

        // Add padding and background color
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return label;

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

    private JButton createButton(String text, GridBagConstraints gbc) {
        JButton button = new JButton(text);
        return button;
    }

    private void addSliderChangeListener(JSlider slider, JLabel label) {
        slider.addChangeListener(e -> label.setText(String.valueOf(slider.getValue())));
    }

}