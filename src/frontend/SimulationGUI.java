package frontend;

import backend.database.Garage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimulationGUI extends JFrame {

    private JLabel timeLabel;
    private JLabel garage1CapacityLabel;
    private JProgressBar garage1OccupancyBar;
    private JLabel garage2CapacityLabel;
    private JProgressBar garage2OccupancyBar;
    private JLabel garage3CapacityLabel;
    private JProgressBar garage3OccupancyBar;
    private JLabel garage4CapacityLabel;
    private JProgressBar garage4OccupancyBar;
    private JLabel garage5CapacityLabel;
    private JProgressBar garage5OccupancyBar;
    private JButton seeTrendsButton;

    private ArrayList<Garage> garages;

    // Initialize GUI for active portion of simulation
    public SimulationGUI(ArrayList<Garage> garagesList, int simulationDuration, BorderLayout borderLayout) {

        garages = garagesList; //initialize value for private var, enable access on all functions in this class

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Create header panel
        JPanel headingPanel = PDMPanels.createHeader("PDM");
        add(headingPanel, BorderLayout.NORTH);
        JLabel simulationLabel = new JLabel("Garage Simulation");
        simulationLabel.setForeground(Color.DARK_GRAY);
        simulationLabel.setBackground(Color.lightGray);
        simulationLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        simulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headingPanel.add(simulationLabel);

        JPanel footerPanel = PDMPanels.createFooter();
        add(footerPanel, BorderLayout.SOUTH);

        // Create and add main center panel that will contain time, notifications, and simulation labels
        JPanel centerPanel = new JPanel();
        centerPanel.add(Box.createRigidArea(new Dimension(getWidth(), getHeight())));
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(2000, 1000));

        // Create a panel for the time label to be placed at the top of the center panel
        JPanel timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(1500, 50));

        // Create time label
        int time = 420;
        timeLabel = createLabel("Time: " + convertMinutesToAMPM(time));
        timePanel.add(timeLabel, BorderLayout.CENTER);

        // Create a panel for the notification label to be placed under the time label in the center panel
        JPanel notificationPanel = new JPanel();
        notificationPanel.setPreferredSize(new Dimension(1500, 100));

        // Create notification label
        JLabel notificationLabel = new JLabel("Notification:", SwingConstants.CENTER);
        Font labelFont = new Font("Roboto", Font.BOLD, 18);
        notificationLabel.setFont(labelFont);
        notificationLabel.setOpaque(true);
        notificationLabel.setBackground(Color.LIGHT_GRAY);
        notificationLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        notificationLabel.setPreferredSize(new Dimension(1000, 50));
        notificationPanel.add(notificationLabel, BorderLayout.CENTER);

        // Create garages panel
        JPanel garagesPanel = new JPanel();
        garagesPanel.setPreferredSize(new Dimension(1800, 500));
        garagesPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        centerPanel.add(timePanel, BorderLayout.NORTH);
        centerPanel.add(notificationPanel, BorderLayout.CENTER);
        centerPanel.add(garagesPanel, BorderLayout.SOUTH);

        ImageIcon garageImage = new ImageIcon("resources/garage_clipart.png");

        switch (garages.size()) //create labels based on num of garages (up to 5)
        {
            case 1:

                JPanel garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1CapacityLabel = createLabel(garages.get(0).getName());
                garage1Panel.add(garage1CapacityLabel, BorderLayout.NORTH);
                garage1Panel.add(garage1ImageLabel, BorderLayout.CENTER);
                garage1Panel.add(garage1OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage1Panel, BorderLayout.CENTER);

                break;

            case 2:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1CapacityLabel = createLabel(garages.get(0).getName());
                garage1Panel.add(garage1CapacityLabel, BorderLayout.NORTH);
                garage1Panel.add(garage1ImageLabel, BorderLayout.CENTER);
                garage1Panel.add(garage1OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage1Panel, BorderLayout.CENTER);

                JPanel garage2Panel = new JPanel();
                garage2OccupancyBar = new JProgressBar();
                garage2OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setStringPainted(true);
                garage2Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage2ImageLabel = new JLabel();
                garage2ImageLabel.setIcon(garageImage);
                garage2ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage2CapacityLabel = createLabel(garages.get(1).getName());
                garage2Panel.add(garage2CapacityLabel, BorderLayout.NORTH);
                garage2Panel.add(garage2ImageLabel, BorderLayout.CENTER);
                garage2Panel.add(garage2OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage2Panel, BorderLayout.CENTER);

                break;

            case 3:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1CapacityLabel = createLabel(garages.get(0).getName());
                garage1Panel.add(garage1CapacityLabel, BorderLayout.NORTH);
                garage1Panel.add(garage1ImageLabel, BorderLayout.CENTER);
                garage1Panel.add(garage1OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage1Panel, BorderLayout.CENTER);

                garage2Panel = new JPanel();
                garage2OccupancyBar = new JProgressBar();
                garage2OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setStringPainted(true);
                garage2Panel.setPreferredSize(new Dimension(300, 300));
                garage2ImageLabel = new JLabel();
                garage2ImageLabel.setIcon(garageImage);
                garage2ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage2CapacityLabel = createLabel(garages.get(1).getName());
                garage2Panel.add(garage2CapacityLabel, BorderLayout.NORTH);
                garage2Panel.add(garage2ImageLabel, BorderLayout.CENTER);
                garage2Panel.add(garage2OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage2Panel, BorderLayout.CENTER);

                JPanel garage3Panel = new JPanel();
                garage3OccupancyBar = new JProgressBar();
                garage3OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage3OccupancyBar.setString(garages.get(2).getOccupancy() + "/" + garages.get(2).getMaxCapacity());
                garage3OccupancyBar.setStringPainted(true);
                garage3Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage3ImageLabel = new JLabel();
                garage3ImageLabel.setIcon(garageImage);
                garage3ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage3CapacityLabel = createLabel(garages.get(2).getName());
                garage3Panel.add(garage3CapacityLabel, BorderLayout.NORTH);
                garage3Panel.add(garage3ImageLabel, BorderLayout.CENTER);
                garage3Panel.add(garage3OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage3Panel, BorderLayout.CENTER);

                break;

            case 4:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1CapacityLabel = createLabel(garages.get(0).getName());
                garage1Panel.add(garage1CapacityLabel, BorderLayout.NORTH);
                garage1Panel.add(garage1ImageLabel, BorderLayout.CENTER);
                garage1Panel.add(garage1OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage1Panel, BorderLayout.CENTER);

                garage2Panel = new JPanel();
                garage2OccupancyBar = new JProgressBar();
                garage2OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setStringPainted(true);
                garage2Panel.setPreferredSize(new Dimension(300, 300));
                garage2ImageLabel = new JLabel();
                garage2ImageLabel.setIcon(garageImage);
                garage2ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage2CapacityLabel = createLabel(garages.get(1).getName());
                garage2Panel.add(garage2CapacityLabel, BorderLayout.NORTH);
                garage2Panel.add(garage2ImageLabel, BorderLayout.CENTER);
                garage2Panel.add(garage2OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage2Panel, BorderLayout.CENTER);

                garage3Panel = new JPanel();
                garage3OccupancyBar = new JProgressBar();
                garage3OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage3OccupancyBar.setString(garages.get(2).getOccupancy() + "/" + garages.get(2).getMaxCapacity());
                garage3OccupancyBar.setStringPainted(true);
                garage3Panel.setPreferredSize(new Dimension(300, 300));
                garage3ImageLabel = new JLabel();
                garage3ImageLabel.setIcon(garageImage);
                garage3ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage3CapacityLabel = createLabel(garages.get(2).getName());
                garage3Panel.add(garage3CapacityLabel, BorderLayout.NORTH);
                garage3Panel.add(garage3ImageLabel, BorderLayout.CENTER);
                garage3Panel.add(garage3OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage3Panel, BorderLayout.CENTER);

                JPanel garage4Panel = new JPanel();
                garage4OccupancyBar = new JProgressBar();
                garage4OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage4OccupancyBar.setString(garages.get(3).getOccupancy() + "/" + garages.get(3).getMaxCapacity());
                garage4OccupancyBar.setStringPainted(true);
                garage4Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage4ImageLabel = new JLabel();
                garage4ImageLabel.setIcon(garageImage);
                garage4ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                break;

            case 5:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1CapacityLabel = createLabel(garages.get(0).getName());
                garage1Panel.add(garage1CapacityLabel, BorderLayout.NORTH);
                garage1Panel.add(garage1ImageLabel, BorderLayout.CENTER);
                garage1Panel.add(garage1OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage1Panel, BorderLayout.CENTER);

                garage2Panel = new JPanel();
                garage2OccupancyBar = new JProgressBar();
                garage2OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setStringPainted(true);
                garage2Panel.setPreferredSize(new Dimension(300, 300));
                garage2ImageLabel = new JLabel();
                garage2ImageLabel.setIcon(garageImage);
                garage2ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage2CapacityLabel = createLabel(garages.get(1).getName());
                garage2Panel.add(garage2CapacityLabel, BorderLayout.NORTH);
                garage2Panel.add(garage2ImageLabel, BorderLayout.CENTER);
                garage2Panel.add(garage2OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage2Panel, BorderLayout.CENTER);

                garage3Panel = new JPanel();
                garage3OccupancyBar = new JProgressBar();
                garage3OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage3OccupancyBar.setString(garages.get(2).getOccupancy() + "/" + garages.get(2).getMaxCapacity());
                garage3OccupancyBar.setStringPainted(true);
                garage3Panel.setPreferredSize(new Dimension(300, 300));
                garage3ImageLabel = new JLabel();
                garage3ImageLabel.setIcon(garageImage);
                garage3ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage3CapacityLabel = createLabel(garages.get(2).getName());
                garage3Panel.add(garage3CapacityLabel, BorderLayout.NORTH);
                garage3Panel.add(garage3ImageLabel, BorderLayout.CENTER);
                garage3Panel.add(garage3OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage3Panel, BorderLayout.CENTER);

                garage4Panel = new JPanel();
                garage4OccupancyBar = new JProgressBar();
                garage4OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage4OccupancyBar.setString(garages.get(3).getOccupancy() + "/" + garages.get(3).getMaxCapacity());
                garage4OccupancyBar.setStringPainted(true);
                garage4Panel.setPreferredSize(new Dimension(300, 300));
                garage4ImageLabel = new JLabel();
                garage4ImageLabel.setIcon(garageImage);
                garage4ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                JPanel garage5Panel = new JPanel();
                garage5OccupancyBar = new JProgressBar();
                garage5OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setStringPainted(true);
                garage5Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage5ImageLabel = new JLabel();
                garage5ImageLabel.setIcon(garageImage);
                garage5ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                break;

        }

        seeTrendsButton = new JButton("See Trends");
        seeTrendsButton.setPreferredSize(new Dimension(1500, 50));
        seeTrendsButton.setFont(new Font("Roboto", Font.BOLD, 16));
        garagesPanel.add(seeTrendsButton, BorderLayout.SOUTH);

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

        GarageSimulation garageSimulation = new GarageSimulation(this, garages, simulationDuration, time);

    }

    // Called by simulation driver code at the end of each simulation tick
    public void updateSimLabels(ArrayList<Garage> garageList, int time) {

        garages = garageList; //update local private var with new info

        timeLabel.setText("Time: " + convertMinutesToAMPM(time));

        switch (garages.size()) //create labels based on num of garages (up to 5)
        {
            case 1:
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setValue(getOccupancyPercentage(garages.get(0)));
                break;
            case 2:
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setValue(getOccupancyPercentage(garages.get(0)));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setValue(getOccupancyPercentage(garages.get(1)));
                break;
            case 3:
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setValue(getOccupancyPercentage(garages.get(0)));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setValue(getOccupancyPercentage(garages.get(1)));
                garage3OccupancyBar.setString(garages.get(2).getOccupancy() + "/" + garages.get(2).getMaxCapacity());
                garage3OccupancyBar.setValue(getOccupancyPercentage(garages.get(2)));
                break;
            case 4:
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setValue(getOccupancyPercentage(garages.get(0)));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setValue(getOccupancyPercentage(garages.get(1)));
                garage3OccupancyBar.setString(garages.get(2).getOccupancy() + "/" + garages.get(2).getMaxCapacity());
                garage3OccupancyBar.setValue(getOccupancyPercentage(garages.get(2)));
                garage4OccupancyBar.setString(garages.get(3).getOccupancy() + "/" + garages.get(3).getMaxCapacity());
                garage4OccupancyBar.setValue(getOccupancyPercentage(garages.get(3)));
                break;
            case 5:
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setValue(getOccupancyPercentage(garages.get(0)));
                garage2OccupancyBar.setString(garages.get(1).getOccupancy() + "/" + garages.get(1).getMaxCapacity());
                garage2OccupancyBar.setValue(getOccupancyPercentage(garages.get(1)));
                garage3OccupancyBar.setString(garages.get(2).getOccupancy() + "/" + garages.get(2).getMaxCapacity());
                garage3OccupancyBar.setValue(getOccupancyPercentage(garages.get(2)));
                garage4OccupancyBar.setString(garages.get(3).getOccupancy() + "/" + garages.get(3).getMaxCapacity());
                garage4OccupancyBar.setValue(getOccupancyPercentage(garages.get(3)));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setValue(getOccupancyPercentage(garages.get(4)));
                break;
        }
    }

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        // Increase font size
        Font labelFont = new Font("Roboto", Font.BOLD, 18);
        label.setFont(labelFont);

        // Add padding and background color
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return label;

    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);
        return button;

    }

    // Takes a garage object and returns its current occupancy as a percentage
    private static int getOccupancyPercentage(Garage garage) {

        int maxCapacity = garage.getMaxCapacity();
        int occupancy = garage.getOccupancy();

        double doublePercentage = ((double) occupancy / maxCapacity) * 100;
        int percentage = (int)Math.round(doublePercentage);

        return percentage;

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

