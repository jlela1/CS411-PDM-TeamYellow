package frontend;

import backend.database.*;
import backend.database.timeOfArrival;

import backend.database.trendsGarage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class googleMapsGUI  {
    private JFrame mainFrame;
    private JTextField recommendedGarageField;
    private JTextField timeOfArrivalField;
    private JTextField estimatedOccupancyField;
    private JButton homeButton;

    public googleMapsGUI() {
        // Frame setup

        mainFrame = new JFrame("Simulation Page-1");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1500, 1000));
        mainFrame.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 10.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        //mainFrame.setContentPane(backgroundPanel);

        /*
        setTitle("Simulation Page-1");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());*/

        //Header Panel
        JPanel headerPanel = PDMPanels.createUserHeader("Recommended Garage");
        //headerPanel.setPreferredSize(new Dimension(900, 60));
        mainFrame.add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel(new FlowLayout());
        contentPanel.setOpaque(false);
        //contentPanel.setLayout(new GridLayout(1, 10, 10, 5));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 100, 150));
        contentPanel.setBackground(Color.CYAN);





        final JFXPanel fxPanel = new JFXPanel();

        JPanel panel = new JPanel();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanel1 = new GridBagConstraints();
        gbcPanel1.gridx = 0;
        gbcPanel1.gridy = 0;
        gbcPanel1.weighty =0;
        gbcPanel1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbcPanel2 = new GridBagConstraints();
        gbcPanel2.gridx = 0;
        gbcPanel2.gridy = 1;
        gbcPanel2.weighty =20;
        gbcPanel2.fill = GridBagConstraints.BOTH;
        fxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

       // mainPanel.add(contentPanel, gbcPanel1);
        //mainPanel.add(fxPanel, gbcPanel2);
        //mainFrame.add(mainPanel,BorderLayout.CENTER);
        //fxPanel.setLayout(null);
        //fxPanel.setBounds(350, 200, 1200,700);
        mainPanel.add(contentPanel, gbcPanel1);
        mainPanel.add(fxPanel, gbcPanel2);
        mainFrame.getContentPane().add(mainPanel,BorderLayout.CENTER);
        //mainFrame.add(panel);
        //mainFrame.add(fxPanel);



        // should be simulated data, but for now I put something
        String recommendedGarage = "43rd & Elkhorn Ave";
        String estimatedOccupancy = "50%";

        // Recommended Garage
        recommendedGarageField = new JTextField(15);
        recommendedGarageField.setBorder(BorderFactory.createLineBorder(Color.yellow, 4));
        recommendedGarageField.setEditable(false);
        recommendedGarageField.setText(recommendedGarage); // Set the actual value
        JLabel recommendedGarageLabel = new JLabel("Desired Garage:");
        recommendedGarageLabel.setFont(new Font("Monospaced",Font.ITALIC,16));
        contentPanel.add(recommendedGarageLabel);
//        contentPanel.add(recommendedGarageField);
        //contentPanel.add(fxPanel);

        //create garage data
        ArrayList<ArrayList<trendsGarage>> garages = new ArrayList<ArrayList<trendsGarage>>();
        trendsTest.readAndStoreToGraph(garages, 4); //num garages hardcoded temporarily

        // Initialize the garageSelectorComboBox
        JComboBox<String> userSelectionGarage = new JComboBox<String>();

        //userSelectionGarage.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        // Add items to the combo box
        for (ArrayList<trendsGarage> garage : garages) {
            userSelectionGarage.addItem(garage.get(0).getGarage_name());
        }

        contentPanel.add(userSelectionGarage);

        // Time of Arrival
        timeOfArrivalField = new JTextField(15);
        setPlaceholder(timeOfArrivalField, "1245");
        timeOfArrivalField.setBorder(BorderFactory.createLineBorder(Color.green, 4));
        timeOfArrivalField.setEditable(true);
        // Set the actual value
        JLabel timeOfArrivalLabel = new JLabel("Time of Arrival:");
        timeOfArrivalLabel.setFont(new Font("Monospaced",Font.ITALIC,16));
        contentPanel.add(timeOfArrivalLabel);
        contentPanel.add(timeOfArrivalField);

        // Estimated Occupancy
        estimatedOccupancyField = new JTextField(15);
        estimatedOccupancyField.setBorder(BorderFactory.createLineBorder(Color.pink, 4));
        estimatedOccupancyField.setEditable(false);
        estimatedOccupancyField.setText(estimatedOccupancy); //Set the actual value
        JLabel estimatedOccupancyLabel = new JLabel("Estimated Occupancy on Time of Arrival:");
        estimatedOccupancyLabel.setFont(new Font("Monospaced",Font.ITALIC,16));
        contentPanel.add(estimatedOccupancyLabel);
        contentPanel.add(estimatedOccupancyField);

        //mainFrame.add(contentPanel, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);

        //Home Button
        homeButton =  new JButton("Home");
        homeButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to UserDashboard
                mainFrame.dispose(); // Close the current window
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
        buttonPanel.setPreferredSize(new Dimension(70, 30));
        contentPanel.add(homeButton, BorderLayout.SOUTH);

        JButton getDirectionsButton = new JButton("Get Directions");
        getDirectionsButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        getDirectionsButton.setForeground(Color.BLACK);
        getDirectionsButton.setBackground(new Color(23, 11, 204, 163));
        getDirectionsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        getDirectionsButton.setFocusPainted(false);
        getDirectionsButton.setPreferredSize(new Dimension(250, 50));
        getDirectionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(getDirectionsButton);

        JButton getRecommendationButton = new JButton("Get Recommendation");
        getRecommendationButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        getRecommendationButton.setForeground(Color.BLACK);
        getRecommendationButton.setBackground(new Color(23, 11, 204, 163));
        getRecommendationButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        getRecommendationButton.setFocusPainted(false);
        getRecommendationButton.setPreferredSize(new Dimension(250, 50));
        getRecommendationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(getRecommendationButton);


        getRecommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedGarage = (String) userSelectionGarage.getSelectedItem();

                //get time of arrival
                int arrivalTime24HR = Integer.parseInt(timeOfArrivalField.getText());
                int hours = arrivalTime24HR / 100;
                int minutes = arrivalTime24HR % 100;
                int arrivalTime = (hours * 60) + minutes;

                String recommendedGarage = backend.algorithms.recommendationAlgorithm.recommendation(selectedGarage, arrivalTime, 4);

                int selectedGarageNumID = -1;

                for (int i = 0; i < garages.size(); i++) {
                    if (recommendedGarage.equals(garages.get(i).get(0).getGarage_name())) {
                        selectedGarageNumID = i;
                    }

                }

                //get expected occupancy on arrival
                double arrivalOccupancy = garages.get(selectedGarageNumID).get(arrivalTime).getCurrent_capacity();
                double arrivalCapacity = garages.get(selectedGarageNumID).get(arrivalTime).getTotal_capacity();
                double expectedOccupancyPercent = (arrivalOccupancy /arrivalCapacity) * 100.00;
                String expectedOccupancy = (int)expectedOccupancyPercent + "%";
                userSelectionGarage.setSelectedIndex(selectedGarageNumID);
                estimatedOccupancyField.setText(expectedOccupancy);

                switch (selectedGarageNumID) {
                    case 0:
                        //display 443rd and elkhorn route here
                        Platform.runLater(()-> {
                            WebView webView1 = new WebView();
                            fxPanel.setScene(new Scene(webView1));
                            webView1.getEngine().load("https://www.google.com/maps/place/43rd+Street+%26+Elkhorn+Avenue+Garage/@36.8836065,-76.309158,17.75z/data=!4m6!3m5!1s0x89ba9852ea8d5105:0xe605282c33baec84!8m2!3d36.8836248!4d-76.3078488!16s%2Fg%2F11gd689zbg?entry=ttu");

                        });
                        break;
                    case 1:
                        //display constant garage south here
                        Platform.runLater(()-> {
                            WebView webView2 = new WebView();
                            fxPanel.setScene(new Scene(webView2));
                            webView2.getEngine().load("https://www.google.com/maps/place/Constant+Center+South+Garage,+1067+W+43rd+St,+Norfolk,+VA+23508/@36.8827237,-76.3011169,18z/data=!4m6!3m5!1s0x89ba984c96c90c79:0x11bc0a5e1a234d35!8m2!3d36.8824983!4d-76.3009467!16s%2Fg%2F1tgxvf_w?entry=ttu");
                        });
                        break;
                    case 2:
                        //display constant garage north here
                        Platform.runLater(()-> {
                            WebView webView3 = new WebView();
                            fxPanel.setScene(new Scene(webView3));
                            webView3.getEngine().load("https://www.google.com/maps/place/Constant+Center+North+Parking+Garage,+1067+W+43rd+St,+Norfolk,+VA+23529/@36.8855803,-76.3011016,18z/data=!4m6!3m5!1s0x89ba99b3443fdf97:0x77d74bf5702aa998!8m2!3d36.8852306!4d-76.3010372!16s%2Fg%2F1ts_7htk?entry=ttu");

                        });
                        break;
                    case 3:
                        //display 49th and bluestone here
                        Platform.runLater(()-> {
                            WebView webView3 = new WebView();
                            fxPanel.setScene(new Scene(webView3));
                            webView3.getEngine().load("https://www.google.com/maps/place/49th+Street+Stadium+Garage/@36.8881333,-76.3057912,18.25z/data=!4m6!3m5!1s0x89ba99b27bec601f:0x75de86b7374c31a8!8m2!3d36.887864!4d-76.305469!16s%2Fg%2F12hn1l2tl?entry=ttu");

                        });
                        break;

                }

            }
        });


        getDirectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedGarage = (String) userSelectionGarage.getSelectedItem();


                System.out.println("get directions for: " + selectedGarage);

                int selectedGarageNumID = -1;
                String timeOfArrivalID = "";
                String arrivalTime = timeOfArrivalField.getText();

                for (int i = 0; i < garages.size(); i++) {
                    if (selectedGarage.equals(garages.get(i).get(0).getGarage_name())) {
                        selectedGarageNumID = i;
                    }

                }
                timeOfArrival newArrival = new timeOfArrival(arrivalTime);
                System.out.println("Time of arrival: " + arrivalTime);

                switch (selectedGarageNumID) {
                    case 0:
                        //display 443rd and elkhorn route here
                        Platform.runLater(()-> {
                            WebView webView1 = new WebView();
                            fxPanel.setScene(new Scene(webView1));
                            webView1.getEngine().load("https://www.google.com/maps/place/43rd+Street+%26+Elkhorn+Avenue+Garage/@36.8836065,-76.309158,17.75z/data=!4m6!3m5!1s0x89ba9852ea8d5105:0xe605282c33baec84!8m2!3d36.8836248!4d-76.3078488!16s%2Fg%2F11gd689zbg?entry=ttu");

                        });
                        break;
                    case 1:
                        //display constant garage south here
                        Platform.runLater(()-> {
                            WebView webView2 = new WebView();
                            fxPanel.setScene(new Scene(webView2));
                            webView2.getEngine().load("https://www.google.com/maps/place/Constant+Center+South+Garage,+1067+W+43rd+St,+Norfolk,+VA+23508/@36.8827237,-76.3011169,18z/data=!4m6!3m5!1s0x89ba984c96c90c79:0x11bc0a5e1a234d35!8m2!3d36.8824983!4d-76.3009467!16s%2Fg%2F1tgxvf_w?entry=ttu");
                        });
                        break;
                    case 2:
                        //display constant garage north here
                        Platform.runLater(()-> {
                            WebView webView3 = new WebView();
                            fxPanel.setScene(new Scene(webView3));
                            webView3.getEngine().load("https://www.google.com/maps/place/Constant+Center+North+Parking+Garage,+1067+W+43rd+St,+Norfolk,+VA+23529/@36.8855803,-76.3011016,18z/data=!4m6!3m5!1s0x89ba99b3443fdf97:0x77d74bf5702aa998!8m2!3d36.8852306!4d-76.3010372!16s%2Fg%2F1ts_7htk?entry=ttu");

                        });
                        break;
                    case 3:
                        //display 49th and bluestone here
                        Platform.runLater(()-> {
                            WebView webView3 = new WebView();
                            fxPanel.setScene(new Scene(webView3));
                            webView3.getEngine().load("https://www.google.com/maps/place/49th+Street+Stadium+Garage/@36.8881333,-76.3057912,18.25z/data=!4m6!3m5!1s0x89ba99b27bec601f:0x75de86b7374c31a8!8m2!3d36.887864!4d-76.305469!16s%2Fg%2F12hn1l2tl?entry=ttu");

                        });
                        break;

                }

            }
        });

    }
    private void setPlaceholder(JTextComponent component, String placeholder) {
        component.setForeground(Color.GRAY);
        component.setText(placeholder);
        component.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (component.getForeground() == Color.GRAY) {
                    component.setText("");
                    component.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (component.getText().trim().isEmpty()) {
                    component.setText(placeholder);
                    component.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                googleMapsGUI page1 = new googleMapsGUI();
                //page1.setVisible(true);
            }
        });
    }
}
    //googleMapsGUI page1 = new googleMapsGUI();