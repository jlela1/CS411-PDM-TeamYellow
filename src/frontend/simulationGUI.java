package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class simulationGUI {
    private int time = 0;
    private JPanel panel;
    public static JLabel timeLabel;
    public static JLabel struct1Label;
    public static JLabel struct2Label;
    public static JLabel notificationLabel;

    public simulationGUI(int time, String struct1, String struct2) {

        JFrame frame = new JFrame();

        timeLabel = new JLabel("Time: 1200");
        struct1Label = new JLabel("Structure 1: 0/100");
        struct2Label = new JLabel("Structure 2: 0/100");
        notificationLabel = new JLabel("Notification:");


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(timeLabel);
        panel.add(struct1Label);
        panel.add(struct2Label);
        panel.add(notificationLabel);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PARKING SIMULATION");
        frame.pack();
        frame.setVisible(true);
    }

    public static void updateTimeGUI(int time) {
        timeLabel.setText("Time: " + time);
    }

    public static void updateStruct1GUI(int capacity, int occupancy) {
        struct1Label.setText("Structure 1: " + occupancy + "/" + capacity);
    }

    public static void updateStruct2GUI(int capacity, int occupancy) {
        struct2Label.setText("Structure 2: " + occupancy + "/" + capacity);
    }

    public static void updateNotificationGUI(String notification) {
        notificationLabel.setText(notification);
    }

}
