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

    private JLabel garage6CapacityLabel;
    private JProgressBar garage6OccupancyBar;
    private JLabel garage7CapacityLabel;
    private JProgressBar garage7OccupancyBar;
    private JLabel garage8CapacityLabel;
    private JProgressBar garage8OccupancyBar;
    private JLabel garage9CapacityLabel;
    private JProgressBar garage9OccupancyBar;
    private JLabel garage10CapacityLabel;
    private JProgressBar garage10OccupancyBar;
    private JButton seeTrendsButton;
    private JButton pauseButton;
    private JButton nextMinuteButton;
    private JLabel notificationLabel;

    private ArrayList<Garage> garages;

    // Initialize GUI for active portion of simulation
    public SimulationGUI(ArrayList<Garage> garagesList, int simulationDuration, BorderLayout borderLayout, int presetType) {

        garages = garagesList; //initialize value for private var, enable access on all functions in this class

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set JFrame to full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 1.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        // Create header panel
        JPanel headingPanel = PDMPanels.createHeader("PDM");
        add(headingPanel, BorderLayout.NORTH);
        JLabel simulationLabel = new JLabel("Garage Simulation");
        simulationLabel.setForeground(Color.DARK_GRAY);
        simulationLabel.setBackground(Color.lightGray);
        simulationLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        simulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headingPanel.add(simulationLabel);

        backgroundPanel.add(headingPanel, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createFooter();
        add(footerPanel, BorderLayout.SOUTH);

        // Create and add main center panel that will contain time, notifications, and simulation labels
        JPanel centerPanel = new JPanel();
        centerPanel.add(Box.createRigidArea(new Dimension(getWidth(), getHeight())));
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(2000, 1000));

        centerPanel.setOpaque(false);

        // Create a panel for the time label to be placed at the top of the center panel
        JPanel timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(1500, 50));

        // Create time label
        int time = 420;
        timeLabel = createLabel("Time: " + convertMinutesToAMPM(time));
        timeLabel.setOpaque(false);
        timePanel.add(timeLabel, BorderLayout.CENTER);
        timePanel.setOpaque(false);

        // Create a panel for the notification label to be placed under the time label in the center panel
        JPanel notificationPanel = new JPanel();
        notificationPanel.setPreferredSize(new Dimension(1500, 100));
        notificationPanel.setOpaque(false);

        // Create notification label
        notificationLabel = new JLabel("Notification:", SwingConstants.CENTER);
        Font labelFont = new Font("Monospaced", Font.BOLD, 18);
        notificationLabel.setFont(labelFont);
        notificationLabel.setOpaque(true);
        notificationLabel.setBackground(Color.LIGHT_GRAY);
        notificationLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        notificationLabel.setPreferredSize(new Dimension(1000, 50));
        notificationPanel.add(notificationLabel, BorderLayout.CENTER);


        // Create garages panel
        JPanel garagesPanel = new JPanel();
        garagesPanel.setPreferredSize(new Dimension(1800, 900));
        garagesPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        garagesPanel.setOpaque(false);

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
                garage1ImageLabel.setOpaque(false);
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
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
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
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
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
                garage5ImageLabel.setOpaque(false);
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                break;

            case 6:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                garage5Panel = new JPanel();
                garage5OccupancyBar = new JProgressBar();
                garage5OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setStringPainted(true);
                garage5Panel.setPreferredSize(new Dimension(300, 300));
                garage5ImageLabel = new JLabel();
                garage5ImageLabel.setIcon(garageImage);
                garage5ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage5ImageLabel.setOpaque(false);
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                JPanel garage6Panel = new JPanel();
                garage6OccupancyBar = new JProgressBar();
                garage6OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setStringPainted(true);
                garage6Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage6ImageLabel = new JLabel();
                garage6ImageLabel.setIcon(garageImage);
                garage6ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage6ImageLabel.setOpaque(false);
                garage6CapacityLabel = createLabel(garages.get(5).getName());
                garage6Panel.add(garage6CapacityLabel, BorderLayout.NORTH);
                garage6Panel.add(garage6ImageLabel, BorderLayout.CENTER);
                garage6Panel.add(garage6OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage6Panel, BorderLayout.CENTER);

                break;

            case 7:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                garage5Panel = new JPanel();
                garage5OccupancyBar = new JProgressBar();
                garage5OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setStringPainted(true);
                garage5Panel.setPreferredSize(new Dimension(300, 300));
                garage5ImageLabel = new JLabel();
                garage5ImageLabel.setIcon(garageImage);
                garage5ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage5ImageLabel.setOpaque(false);
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                garage6Panel = new JPanel();
                garage6OccupancyBar = new JProgressBar();
                garage6OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setStringPainted(true);
                garage6Panel.setPreferredSize(new Dimension(300, 300));
                garage6ImageLabel = new JLabel();
                garage6ImageLabel.setIcon(garageImage);
                garage6ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage6ImageLabel.setOpaque(false);
                garage6CapacityLabel = createLabel(garages.get(5).getName());
                garage6Panel.add(garage6CapacityLabel, BorderLayout.NORTH);
                garage6Panel.add(garage6ImageLabel, BorderLayout.CENTER);
                garage6Panel.add(garage6OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage6Panel, BorderLayout.CENTER);

                JPanel garage7Panel = new JPanel();
                garage7OccupancyBar = new JProgressBar();
                garage7OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setStringPainted(true);
                garage7Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage7ImageLabel = new JLabel();
                garage7ImageLabel.setIcon(garageImage);
                garage7ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage7ImageLabel.setOpaque(false);
                garage7CapacityLabel = createLabel(garages.get(6).getName());
                garage7Panel.add(garage7CapacityLabel, BorderLayout.NORTH);
                garage7Panel.add(garage7ImageLabel, BorderLayout.CENTER);
                garage7Panel.add(garage7OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage7Panel, BorderLayout.CENTER);

                break;

            case 8:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                garage5Panel = new JPanel();
                garage5OccupancyBar = new JProgressBar();
                garage5OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setStringPainted(true);
                garage5Panel.setPreferredSize(new Dimension(300, 300));
                garage5ImageLabel = new JLabel();
                garage5ImageLabel.setIcon(garageImage);
                garage5ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage5ImageLabel.setOpaque(false);
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                garage6Panel = new JPanel();
                garage6OccupancyBar = new JProgressBar();
                garage6OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setStringPainted(true);
                garage6Panel.setPreferredSize(new Dimension(300, 300));
                garage6ImageLabel = new JLabel();
                garage6ImageLabel.setIcon(garageImage);
                garage6ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage6ImageLabel.setOpaque(false);
                garage6CapacityLabel = createLabel(garages.get(5).getName());
                garage6Panel.add(garage6CapacityLabel, BorderLayout.NORTH);
                garage6Panel.add(garage6ImageLabel, BorderLayout.CENTER);
                garage6Panel.add(garage6OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage6Panel, BorderLayout.CENTER);

                garage7Panel = new JPanel();
                garage7OccupancyBar = new JProgressBar();
                garage7OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setStringPainted(true);
                garage7Panel.setPreferredSize(new Dimension(300, 300));
                garage7ImageLabel = new JLabel();
                garage7ImageLabel.setIcon(garageImage);
                garage7ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage7ImageLabel.setOpaque(false);
                garage7CapacityLabel = createLabel(garages.get(6).getName());
                garage7Panel.add(garage7CapacityLabel, BorderLayout.NORTH);
                garage7Panel.add(garage7ImageLabel, BorderLayout.CENTER);
                garage7Panel.add(garage7OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage7Panel, BorderLayout.CENTER);

                JPanel garage8Panel = new JPanel();
                garage8OccupancyBar = new JProgressBar();
                garage8OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage8OccupancyBar.setString(garages.get(7).getOccupancy() + "/" + garages.get(7).getMaxCapacity());
                garage8OccupancyBar.setStringPainted(true);
                garage8Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage8ImageLabel = new JLabel();
                garage8ImageLabel.setIcon(garageImage);
                garage8ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage8ImageLabel.setOpaque(false);
                garage8CapacityLabel = createLabel(garages.get(7).getName());
                garage8Panel.add(garage8CapacityLabel, BorderLayout.NORTH);
                garage8Panel.add(garage8ImageLabel, BorderLayout.CENTER);
                garage8Panel.add(garage8OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage8Panel, BorderLayout.CENTER);

                break;

            case 9:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                garage5Panel = new JPanel();
                garage5OccupancyBar = new JProgressBar();
                garage5OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setStringPainted(true);
                garage5Panel.setPreferredSize(new Dimension(300, 300));
                garage5ImageLabel = new JLabel();
                garage5ImageLabel.setIcon(garageImage);
                garage5ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage5ImageLabel.setOpaque(false);
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                garage6Panel = new JPanel();
                garage6OccupancyBar = new JProgressBar();
                garage6OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setStringPainted(true);
                garage6Panel.setPreferredSize(new Dimension(300, 300));
                garage6ImageLabel = new JLabel();
                garage6ImageLabel.setIcon(garageImage);
                garage6ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage6ImageLabel.setOpaque(false);
                garage6CapacityLabel = createLabel(garages.get(5).getName());
                garage6Panel.add(garage6CapacityLabel, BorderLayout.NORTH);
                garage6Panel.add(garage6ImageLabel, BorderLayout.CENTER);
                garage6Panel.add(garage6OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage6Panel, BorderLayout.CENTER);

                garage7Panel = new JPanel();
                garage7OccupancyBar = new JProgressBar();
                garage7OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setStringPainted(true);
                garage7Panel.setPreferredSize(new Dimension(300, 300));
                garage7ImageLabel = new JLabel();
                garage7ImageLabel.setIcon(garageImage);
                garage7ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage7ImageLabel.setOpaque(false);
                garage7CapacityLabel = createLabel(garages.get(6).getName());
                garage7Panel.add(garage7CapacityLabel, BorderLayout.NORTH);
                garage7Panel.add(garage7ImageLabel, BorderLayout.CENTER);
                garage7Panel.add(garage7OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage7Panel, BorderLayout.CENTER);

                garage8Panel = new JPanel();
                garage8OccupancyBar = new JProgressBar();
                garage8OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage8OccupancyBar.setString(garages.get(7).getOccupancy() + "/" + garages.get(7).getMaxCapacity());
                garage8OccupancyBar.setStringPainted(true);
                garage8Panel.setPreferredSize(new Dimension(300, 300));
                garage8ImageLabel = new JLabel();
                garage8ImageLabel.setIcon(garageImage);
                garage8ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage8ImageLabel.setOpaque(false);
                garage8CapacityLabel = createLabel(garages.get(7).getName());
                garage8Panel.add(garage8CapacityLabel, BorderLayout.NORTH);
                garage8Panel.add(garage8ImageLabel, BorderLayout.CENTER);
                garage8Panel.add(garage8OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage8Panel, BorderLayout.CENTER);

                JPanel garage9Panel = new JPanel();
                garage9OccupancyBar = new JProgressBar();
                garage9OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage9OccupancyBar.setString(garages.get(8).getOccupancy() + "/" + garages.get(8).getMaxCapacity());
                garage9OccupancyBar.setStringPainted(true);
                garage9Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage9ImageLabel = new JLabel();
                garage9ImageLabel.setIcon(garageImage);
                garage9ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage9ImageLabel.setOpaque(false);
                garage9CapacityLabel = createLabel(garages.get(8).getName());
                garage9Panel.add(garage9CapacityLabel, BorderLayout.NORTH);
                garage9Panel.add(garage9ImageLabel, BorderLayout.CENTER);
                garage9Panel.add(garage9OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage9Panel, BorderLayout.CENTER);

                break;

            case 10:

                garage1Panel = new JPanel();
                garage1OccupancyBar = new JProgressBar();
                garage1OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage1OccupancyBar.setString(garages.get(0).getOccupancy() + "/" + garages.get(0).getMaxCapacity());
                garage1OccupancyBar.setStringPainted(true);
                garage1Panel.setPreferredSize(new Dimension(300, 300));
                garage1ImageLabel = new JLabel();
                garage1ImageLabel.setIcon(garageImage);
                garage1ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage1ImageLabel.setOpaque(false);
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
                garage2ImageLabel.setOpaque(false);
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
                garage3ImageLabel.setOpaque(false);
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
                garage4ImageLabel.setOpaque(false);
                garage4CapacityLabel = createLabel(garages.get(3).getName());
                garage4Panel.add(garage4CapacityLabel, BorderLayout.NORTH);
                garage4Panel.add(garage4ImageLabel, BorderLayout.CENTER);
                garage4Panel.add(garage4OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage4Panel, BorderLayout.CENTER);

                garage5Panel = new JPanel();
                garage5OccupancyBar = new JProgressBar();
                garage5OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage5OccupancyBar.setString(garages.get(4).getOccupancy() + "/" + garages.get(4).getMaxCapacity());
                garage5OccupancyBar.setStringPainted(true);
                garage5Panel.setPreferredSize(new Dimension(300, 300));
                garage5ImageLabel = new JLabel();
                garage5ImageLabel.setIcon(garageImage);
                garage5ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage5ImageLabel.setOpaque(false);
                garage5CapacityLabel = createLabel(garages.get(4).getName());
                garage5Panel.add(garage5CapacityLabel, BorderLayout.NORTH);
                garage5Panel.add(garage5ImageLabel, BorderLayout.CENTER);
                garage5Panel.add(garage5OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage5Panel, BorderLayout.CENTER);

                garage6Panel = new JPanel();
                garage6OccupancyBar = new JProgressBar();
                garage6OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setStringPainted(true);
                garage6Panel.setPreferredSize(new Dimension(300, 300));
                garage6ImageLabel = new JLabel();
                garage6ImageLabel.setIcon(garageImage);
                garage6ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage6ImageLabel.setOpaque(false);
                garage6CapacityLabel = createLabel(garages.get(5).getName());
                garage6Panel.add(garage6CapacityLabel, BorderLayout.NORTH);
                garage6Panel.add(garage6ImageLabel, BorderLayout.CENTER);
                garage6Panel.add(garage6OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage6Panel, BorderLayout.CENTER);

                garage7Panel = new JPanel();
                garage7OccupancyBar = new JProgressBar();
                garage7OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setStringPainted(true);
                garage7Panel.setPreferredSize(new Dimension(300, 300));
                garage7ImageLabel = new JLabel();
                garage7ImageLabel.setIcon(garageImage);
                garage7ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage7ImageLabel.setOpaque(false);
                garage7CapacityLabel = createLabel(garages.get(6).getName());
                garage7Panel.add(garage7CapacityLabel, BorderLayout.NORTH);
                garage7Panel.add(garage7ImageLabel, BorderLayout.CENTER);
                garage7Panel.add(garage7OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage7Panel, BorderLayout.CENTER);

                garage8Panel = new JPanel();
                garage8OccupancyBar = new JProgressBar();
                garage8OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage8OccupancyBar.setString(garages.get(7).getOccupancy() + "/" + garages.get(7).getMaxCapacity());
                garage8OccupancyBar.setStringPainted(true);
                garage8Panel.setPreferredSize(new Dimension(300, 300));
                garage8ImageLabel = new JLabel();
                garage8ImageLabel.setIcon(garageImage);
                garage8ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage8ImageLabel.setOpaque(false);
                garage8CapacityLabel = createLabel(garages.get(7).getName());
                garage8Panel.add(garage8CapacityLabel, BorderLayout.NORTH);
                garage8Panel.add(garage8ImageLabel, BorderLayout.CENTER);
                garage8Panel.add(garage8OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage8Panel, BorderLayout.CENTER);

                garage9Panel = new JPanel();
                garage9OccupancyBar = new JProgressBar();
                garage9OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage9OccupancyBar.setString(garages.get(8).getOccupancy() + "/" + garages.get(8).getMaxCapacity());
                garage9OccupancyBar.setStringPainted(true);
                garage9Panel.setPreferredSize(new Dimension(300, 300));
                garage9ImageLabel = new JLabel();
                garage9ImageLabel.setIcon(garageImage);
                garage9ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage9ImageLabel.setOpaque(false);
                garage9CapacityLabel = createLabel(garages.get(8).getName());
                garage9Panel.add(garage9CapacityLabel, BorderLayout.NORTH);
                garage9Panel.add(garage9ImageLabel, BorderLayout.CENTER);
                garage9Panel.add(garage9OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage9Panel, BorderLayout.CENTER);

                JPanel garage10Panel = new JPanel();
                garage10OccupancyBar = new JProgressBar();
                garage10OccupancyBar.setPreferredSize(new Dimension(250, 50));
                garage10OccupancyBar.setString(garages.get(9).getOccupancy() + "/" + garages.get(9).getMaxCapacity());
                garage10OccupancyBar.setStringPainted(true);
                garage10Panel.setPreferredSize(new Dimension(300, 300));
                JLabel garage10ImageLabel = new JLabel();
                garage10ImageLabel.setIcon(garageImage);
                garage10ImageLabel.setPreferredSize(new Dimension(274, 184));
                garage10ImageLabel.setOpaque(false);
                garage10CapacityLabel = createLabel(garages.get(9).getName());
                garage10Panel.add(garage10CapacityLabel, BorderLayout.NORTH);
                garage10Panel.add(garage10ImageLabel, BorderLayout.CENTER);
                garage10Panel.add(garage10OccupancyBar, BorderLayout.EAST);
                garagesPanel.add(garage10Panel, BorderLayout.CENTER);

                break;

        }

        seeTrendsButton = new JButton("See Trends");
        seeTrendsButton.setPreferredSize(new Dimension(1500, 50));
        seeTrendsButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        garagesPanel.add(seeTrendsButton, BorderLayout.SOUTH);

        pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(750, 50));
        pauseButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        garagesPanel.add(pauseButton, BorderLayout.SOUTH);

        nextMinuteButton = new JButton("Next Minute");
        nextMinuteButton.setPreferredSize(new Dimension(750, 50));
        nextMinuteButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        garagesPanel.add(nextMinuteButton, BorderLayout.SOUTH);

        // Repaint the frame to update the changes
        getContentPane().validate();
        getContentPane().repaint();

        seeTrendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    dispose();
                    trendsGUI trendsPage = new trendsGUI(garages.get(0).getName(), garages.size(), garages);
                    trendsPage.setVisible(true);
                });
            }
        });

        GarageSimulation garageSimulation = new GarageSimulation(this, garages, simulationDuration, time, presetType);

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (garageSimulation.getPaused()) {
                        garageSimulation.unpauseSimulation();
                    } else {
                        garageSimulation.pauseSimulation();
                    }
                });
            }
        });

        nextMinuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    garageSimulation.nextMinute();
                });
            }
        });

    }

    // Called by simulation driver code at the end of each simulation tick
    public void updateSimLabels(ArrayList<Garage> garageList, int time, String notification) {

        garages = garageList; //update local private var with new info

        timeLabel.setText("Time: " + convertMinutesToAMPM(time));

        notificationLabel.setText(notification);
        notificationLabel.setOpaque(false);

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
            case 6:
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
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setValue(getOccupancyPercentage(garages.get(5)));
                break;
            case 7:
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
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setValue(getOccupancyPercentage(garages.get(5)));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setValue(getOccupancyPercentage(garages.get(6)));
                break;
            case 8:
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
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setValue(getOccupancyPercentage(garages.get(5)));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setValue(getOccupancyPercentage(garages.get(6)));
                garage8OccupancyBar.setString(garages.get(7).getOccupancy() + "/" + garages.get(7).getMaxCapacity());
                garage8OccupancyBar.setValue(getOccupancyPercentage(garages.get(7)));
                break;
            case 9:
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
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setValue(getOccupancyPercentage(garages.get(5)));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setValue(getOccupancyPercentage(garages.get(6)));
                garage8OccupancyBar.setString(garages.get(7).getOccupancy() + "/" + garages.get(7).getMaxCapacity());
                garage8OccupancyBar.setValue(getOccupancyPercentage(garages.get(7)));
                garage9OccupancyBar.setString(garages.get(8).getOccupancy() + "/" + garages.get(8).getMaxCapacity());
                garage9OccupancyBar.setValue(getOccupancyPercentage(garages.get(8)));
                break;
            case 10:
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
                garage6OccupancyBar.setString(garages.get(5).getOccupancy() + "/" + garages.get(5).getMaxCapacity());
                garage6OccupancyBar.setValue(getOccupancyPercentage(garages.get(5)));
                garage7OccupancyBar.setString(garages.get(6).getOccupancy() + "/" + garages.get(6).getMaxCapacity());
                garage7OccupancyBar.setValue(getOccupancyPercentage(garages.get(6)));
                garage8OccupancyBar.setString(garages.get(7).getOccupancy() + "/" + garages.get(7).getMaxCapacity());
                garage8OccupancyBar.setValue(getOccupancyPercentage(garages.get(7)));
                garage9OccupancyBar.setString(garages.get(8).getOccupancy() + "/" + garages.get(8).getMaxCapacity());
                garage9OccupancyBar.setValue(getOccupancyPercentage(garages.get(8)));
                garage10OccupancyBar.setString(garages.get(9).getOccupancy() + "/" + garages.get(9).getMaxCapacity());
                garage10OccupancyBar.setValue(getOccupancyPercentage(garages.get(9)));
                break;
        }
    }

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        // Increase font size
        Font labelFont = new Font("Monospaced", Font.BOLD, 18);
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

