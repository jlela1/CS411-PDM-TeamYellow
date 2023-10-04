package frontend.Admin;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class trendsGUI extends JFrame  {
    private JComboBox<String> timeSelection;
    private JButton homeButton;
    private JPanel garagePanel;
    private Map<String, Integer> garageCapacities;
    //JLabel headerLabel, footerLabel ;
    //JPanel headerPanel,subPanel;

    public trendsGUI() {
        setTitle("PDM Trends Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeGarageCapacities();
// Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GRAY);
        headerPanel.setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("PDM Trends Page");
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(headingLabel, BorderLayout.NORTH);

        // Time Dropdown
        JPanel timeSection = new JPanel();
        timeSection.setBackground(Color.GRAY);
        timeSelection = new JComboBox<>(new String[]{
                "07:00 AM", "07:10 AM", "07:20 AM", "07:30 AM", "07:40 AM", "07:50 AM",
                "08:00 AM", "08:10 AM", "08:20 AM", "08:30 AM", "08:40 AM", "08:50 AM",
                "09:00 AM", "09:10 AM", "09:20 AM", "09:30 AM", "09:40 AM", "09:50 AM",
                "10:00 AM"
        });
        timeSelection.setFont(new Font("Roboto", Font.PLAIN, 16));
        timeSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGarageCapacities();
            }
        });
        timeSection.add(new JLabel("Select Time Period:"));
        timeSection.add(timeSelection);
        headerPanel.add(timeSection, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Garage Panel
        garagePanel = new JPanel();
        garagePanel.setLayout(new GridLayout(3, 1));

        // Add initial garage information to the panel
        updateGarageCapacities();

        JScrollPane scrollPane = new JScrollPane(garagePanel);
        add(scrollPane, BorderLayout.CENTER);

        // Home Button
        JButton controlsPanel = new JButton();
        homeButton = new JButton(" Go Home Page");
        homeButton.setFont(new Font("Roboto", Font.BOLD, 15));
        controlsPanel.add(homeButton);
        controlsPanel.setFocusPainted(false);
        add(controlsPanel, BorderLayout.LINE_START);

        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Roboto", Font.ITALIC, 15));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(122, 114, 114, 173));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

    }

    private void initializeGarageCapacities() {
        garageCapacities = new HashMap<>();
        garageCapacities.put("  Football Stadium", 44);
        garageCapacities.put("  43rd & Elkhorn", 55);
        garageCapacities.put("  43rd & Bluestone", 49);
    }

    private void updateGarageCapacities() {
        String selectedTime = (String) timeSelection.getSelectedItem();

        // Check if the selectedTime string has a valid format (hh:mm AM/PM)
        if (selectedTime.matches("\\d{2}:\\d{2} [APap][Mm]")) {
            // Extract the hour part from the selected time
            int hour = Integer.parseInt(selectedTime.substring(0, 2));

            // Simulate changing capacities based on the selected time
            int capacityChange = hour >= 7 && hour <= 10 ? hour - 6 : 0;

            // Update garage capacities
            for (Map.Entry<String, Integer> entry : garageCapacities.entrySet()) {
                int updatedCapacity = entry.getValue() + capacityChange;
                entry.setValue(Math.max(updatedCapacity, 0));
            }


            updateGaragePanel();
        } else {
            // Handle invalid input format
            JOptionPane.showMessageDialog(this, "Invalid time format. Please select a valid time.");
        }
    }


    private void updateGaragePanel() {
        garagePanel.removeAll();

        for (Map.Entry<String, Integer> entry : garageCapacities.entrySet()) {
            JPanel garageInfoPanel = new JPanel(new GridLayout(3, 1));
            garageInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel garageNameLabel = new JLabel( entry.getKey());
            JLabel capacityLabel = new JLabel("  Capacity: " + entry.getValue());

            garageInfoPanel.add(garageNameLabel);
            garageInfoPanel.add(capacityLabel);

            garagePanel.add(garageInfoPanel);
        }

        garagePanel.revalidate();
        garagePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            trendsGUI trendsPage = new trendsGUI();
            trendsPage.setVisible(true);
        });
    }
}