package frontend;
import backend.database.Garage;
import backend.database.numVehEnteringRate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class vehicleRateGUI extends JFrame {

    private JSlider timeSlider;
    private JLabel timeValue;
    private JSlider rateSlider;
    private JLabel rateValue;
    private JButton saveRateButton;
    private JButton startSimulationButton;
    private JButton seeTrendsButton;

    private JComboBox<String> garageSelectorComboBox;

    public vehicleRateGUI(ArrayList<Garage> garages) {

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Create header panel
        JPanel headingPanel = PDMPanels.createHeader("PDM");
        add(headingPanel, BorderLayout.NORTH);
        JLabel simulationLabel = new JLabel("Adjust Vehicle Rate");
        simulationLabel.setForeground(Color.DARK_GRAY);
        simulationLabel.setBackground(Color.lightGray);
        simulationLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        simulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headingPanel.add(simulationLabel);

        JPanel footerPanel = PDMPanels.createFooter();
        add(footerPanel, BorderLayout.SOUTH);

        // topPanel contains selectGarageLabel, garageSelectorComboBox, and slidersPanel.
        JPanel topPanel = new JPanel();
        topPanel.add(Box.createRigidArea(new Dimension(getWidth(), getHeight())));
        add(topPanel, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(2000, 1000));

        JLabel selectGarageLabel = createLabel("Select Garage:");

        selectGarageLabel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        topPanel.add(selectGarageLabel, BorderLayout.NORTH);

        // Initialize the garageSelectorComboBox
        garageSelectorComboBox = new JComboBox<String>();

        garageSelectorComboBox.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        // Add items to the combo box
        for (Garage garage : garages) {
            garageSelectorComboBox.addItem(garage.getName());
        }

        // Add combo box to the top of the center panel
        topPanel.add(garageSelectorComboBox, BorderLayout.NORTH);

        JPanel slidersPanel = new JPanel();

        topPanel.add(slidersPanel, BorderLayout.NORTH);
        slidersPanel.setPreferredSize(new Dimension(2000, 200));

        slidersPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        timeSlider = createSlider(1, 60, 1, gbc);
        timeValue = createLabelGBC("1", gbc);
        rateSlider = createSlider(1, 60, 1, gbc);
        rateValue = createLabelGBC("1", gbc);

        JLabel timeLabel = new JLabel("Time in Minutes:");
        JLabel rateLabel = new JLabel("New Vehicle Entering Rate");


        gbc.gridx = -1;
        gbc.gridy = 0;
        slidersPanel.add(timeLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        slidersPanel.add(timeSlider, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        slidersPanel.add(timeValue, gbc);
        gbc.gridx = -1;
        gbc.gridy = 4;
        slidersPanel.add(rateLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        slidersPanel.add(rateSlider, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        slidersPanel.add(rateValue, gbc);
        gbc.gridx = -1;
        gbc.gridy = 8;


        saveRateButton = createButton("Save Rate", gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        slidersPanel.add(saveRateButton, gbc);

        //JLabel simulationDurationLabel = new JLabel("Simulation Duration (minutes):");

        //gbc.gridx = 0;
       // gbc.gridy = 12;
        //slidersPanel.add(simulationDurationLabel, gbc);



        startSimulationButton = createButton("Start Simulation", gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        slidersPanel.add(startSimulationButton, gbc);

        addSliderChangeListener(timeSlider, timeValue);
        addSliderChangeListener(rateSlider, rateValue);

        saveRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Equal to the string of the currently selected garage in the dropdown menu
                String selectedGarage = (String) garageSelectorComboBox.getSelectedItem();


                int time = Integer.parseInt(timeValue.getText());
                int rate = Integer.parseInt(rateValue.getText());
                numVehEnteringRate newRate = new numVehEnteringRate(time, rate);
                //newRate.setTime(time);
                //newRate.setRate(rate);



                for (Garage garage : garages ) {
                    assert selectedGarage != null;
                    if (selectedGarage.equals(garage.getName())) {
                        newRate.setTime(Integer.parseInt(timeValue.getText()));
                        newRate.setRate(Integer.parseInt(rateValue.getText()));
                        timeSlider.setValue(1);
                        rateSlider.setValue(1);
                    }
                }

            }
        });

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getContentPane().remove(timeSlider);
                getContentPane().remove(rateSlider);
                getContentPane().remove(startSimulationButton);
                getContentPane().remove(timeValue);
                getContentPane().remove(rateValue);
                getContentPane().remove(timeLabel);
                getContentPane().remove(rateLabel);
                //getContentPane().remove(simulationDurationLabel);

                // Create an instance of SimulationUserInput GUI
                SimulationUserInputGUI simulation = new SimulationUserInputGUI(garages);
                simulation.setVisible(true);

                // Create an instance of SimulationGUI
                //SimulationGUI simulationGUI = new SimulationGUI(garages, Integer.parseInt(durationValue.getText()), new BorderLayout(), 0);

                // Make the SimulationGUI visible
                //simulationGUI.setVisible(true);

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