package frontend;

import backend.database.Garage;
import backend.database.parkingStructure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        JPanel headerPanel = PDMPanels.createUserHeader("Recommended Garage");
        //headerPanel.setPreferredSize(new Dimension(900, 60));
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
        JLabel recommendedGarageLabel = new JLabel("Desired Garage:");
        recommendedGarageLabel.setFont(new Font("Roboto",Font.ITALIC,16));
        contentPanel.add(recommendedGarageLabel);
//        contentPanel.add(recommendedGarageField);

        //create garage data
        ArrayList<ArrayList<parkingStructure>> garages = new ArrayList<ArrayList<parkingStructure>>();
        trendsTest.readAndStoreToGraph(garages, 4); //num garages hardcoded temporarily

        // Initialize the garageSelectorComboBox
        JComboBox<String> userSelectionGarage = new JComboBox<String>();

        //userSelectionGarage.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        // Add items to the combo box
        for (ArrayList<parkingStructure> garage : garages) {
            userSelectionGarage.addItem(garage.get(0).getGarage_name());
        }

        contentPanel.add(userSelectionGarage);

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
                // Navigate to UserDashboard
                dispose(); // Close the current window
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
        buttonPanel.setPreferredSize(new Dimension(70, 30));
        contentPanel.add(homeButton, BorderLayout.SOUTH);

        JButton getDirectionsButton = new JButton("Get Directions");
        getDirectionsButton.setFont(new Font("Roboto", Font.BOLD, 16));
        getDirectionsButton.setForeground(Color.BLACK);
        getDirectionsButton.setBackground(new Color(23, 11, 204, 163));
        getDirectionsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        getDirectionsButton.setFocusPainted(false);
        getDirectionsButton.setPreferredSize(new Dimension(250, 50));
        getDirectionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(getDirectionsButton);

        getDirectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedGarage = (String) userSelectionGarage.getSelectedItem();

                System.out.println("get directions for: " + selectedGarage);

                int selectedGarageNumID = -1;

                for (int i = 0; i < garages.size(); i++) {
                    if (selectedGarage.equals(garages.get(i).get(0).getGarage_name())) {
                        selectedGarageNumID = i;
                    }
                }

                switch(selectedGarageNumID) {
                    case 0:
                        //display 443rd and elkhorn route here

                        break;
                    case 1:
                        //display constant garage south here

                        break;
                    case 2:
                        //display constant garage north here

                        break;
                    case 3:
                        //display 49th and bluestone here

                        break;
                    case 4:

                        break;
                }

            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            googleMapsGUI page1 = new googleMapsGUI();
            page1.setVisible(true);
        });
    }
}
