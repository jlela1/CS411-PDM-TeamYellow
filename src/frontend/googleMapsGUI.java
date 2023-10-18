package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class googleMapsGUI extends JFrame {
    private JTextField recommendedGarageField;
    private JTextField timeOfArrivalField;
    private JTextField estimatedOccupancyField;
    private JButton homeButton;

    public googleMapsGUI() {
        // Frame setup
        setTitle("Simulation Page-1");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Header Panel
        JPanel headerPanel = PDMPanels.createHeader("Recommended Garage");
        headerPanel.setPreferredSize(new Dimension(900, 60));
        add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();

        // should be simulated data, but for now I put something
        String recommendedGarage = "43rd & Elkhorn Ave";
        String timeOfArrival = "12:45 PM";
        String estimatedOccupancy = "50%";

        // Recommended Garage
        recommendedGarageField = new JTextField(15);
        recommendedGarageField.setBorder(BorderFactory.createLineBorder(Color.yellow, 4));
        recommendedGarageField.setEditable(false);
        recommendedGarageField.setText(recommendedGarage); // Set the actual value
        JLabel recommendedGarageLabel = new JLabel("Recommended Garage to Park:");
        recommendedGarageLabel.setFont(new Font("Roboto",Font.ITALIC,16));
        contentPanel.add(recommendedGarageLabel);
        contentPanel.add(recommendedGarageField);

        // Time of Arrival
        timeOfArrivalField = new JTextField(15);
        timeOfArrivalField.setBorder(BorderFactory.createLineBorder(Color.green, 4));
        timeOfArrivalField.setEditable(false);
        timeOfArrivalField.setText(timeOfArrival); // Set the actual value
        JLabel timeOfArrivalLabel = new JLabel("Time of Arrival:");
        timeOfArrivalLabel.setFont(new Font("Roboto",Font.ITALIC,16));
        contentPanel.add(timeOfArrivalLabel);
        contentPanel.add(timeOfArrivalField);

        // Estimated Occupancy
        estimatedOccupancyField = new JTextField(15);
        estimatedOccupancyField.setBorder(BorderFactory.createLineBorder(Color.pink, 4));
        estimatedOccupancyField.setEditable(false);
        estimatedOccupancyField.setText(estimatedOccupancy); //Set the actual value
        JLabel estimatedOccupancyLabel = new JLabel("Estimated Occupancy on Time of Arrival:");
        estimatedOccupancyLabel.setFont(new Font("Roboto",Font.ITALIC,16));
        contentPanel.add(estimatedOccupancyLabel);
        contentPanel.add(estimatedOccupancyField);

        add(contentPanel, BorderLayout.CENTER);

        //Home Button
        homeButton =  new JButton("Home");
        homeButton.setFont(new Font("Roboto", Font.BOLD, 18));
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to AdminHomePage
                dispose(); // Close the current window
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
        buttonPanel.setPreferredSize(new Dimension(70, 30));
        contentPanel.add(homeButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            googleMapsGUI page1 = new googleMapsGUI();
            page1.setVisible(true);
        });
    }
}
