package frontend;

import backend.database.Garage;
import backend.database.numVehEnteringRate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vehicleRateGUI {
    private JFrame mainFrame;
    private JTextField timeField;
    private JTextField rateField;
    private DefaultListModel<String> rateListModel;
    private JList<String> rateList;
    private JButton doneButton;
    private JComboBox<String> garageSelectorComboBox;
    public vehicleRateGUI(ArrayList<Garage> garages, int presetType) {

        mainFrame = new JFrame("Adjust Vehicle Rate");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1500, 1000));
        mainFrame.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 11.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        mainFrame.setContentPane(backgroundPanel);


        // Create a header panel
        JPanel headerPanel = PDMPanels.createHeader("Adjust Vehicle Rate");


        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridLayout(5, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));


        garageSelectorComboBox = new JComboBox<String>();

        garageSelectorComboBox.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        JLabel selectorLabel = new JLabel("Garage:");
        selectorLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        garageSelectorComboBox.setFont(new Font("Monospaced", Font.PLAIN, 16));

        // Add items to the combo box
        for (Garage garage : garages) {
            garageSelectorComboBox.addItem(garage.getName());
        }

        // Add combo box to the top of the center panel


        timeField = new JTextField(15);
        timeField.setBackground(Color.LIGHT_GRAY);
        JLabel nameLabel = new JLabel("Time in Minutes:");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        timeField.setFont(new Font("Monospaced", Font.PLAIN, 16));

        rateField = new JTextField(15);
        rateField.setBackground(Color.LIGHT_GRAY);
        JLabel capacityLabel = new JLabel("New Vehicle Rate:");
        capacityLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        rateField.setFont(new Font("Monospaced", Font.PLAIN, 16));

        inputPanel.add(selectorLabel);
        inputPanel.add(garageSelectorComboBox);
        inputPanel.add(nameLabel);
        inputPanel.add(timeField);
        inputPanel.add(capacityLabel);
        inputPanel.add(rateField);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 70 ));

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        saveButton.setBackground(Color.YELLOW);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        deleteButton.setBackground(Color.red);

        JButton viewButton = new JButton("View Garages");
        viewButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        viewButton.setBackground(Color.PINK);

        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        doneButton = new JButton("Done");
        doneButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        doneButton.setBackground(Color.green);
        buttonPanel.add(doneButton); // Add the Done button

        JPanel listPanel = new JPanel();
        listPanel.setOpaque(false);
        listPanel.setLayout(new BorderLayout());
        listPanel.setPreferredSize(new Dimension(350, 100));


        rateListModel = new DefaultListModel<>();
        rateList = new JList<>(rateListModel);
        rateList.setFont(new Font("Monospaced", Font.BOLD, 16));
        rateList.setForeground(Color.BLACK);
        rateList.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(rateList);
        scrollPane.setOpaque(false);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        updateGarageList(garages.get(0));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedGarageString = (String) garageSelectorComboBox.getSelectedItem(); //get garage currently selected
                int selectedGarageIndex = -1;

                for(Garage garage : garages) {
                    if(selectedGarageString.equals(garage.getName())) {
                        selectedGarageIndex = garage.getGarageID();
                    }
                }

                String timeText = timeField.getText().trim();
                String rateText = rateField.getText().trim();

                if (timeText.isEmpty() || rateText.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Please fill in both Time and Rate.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int time = Integer.parseInt(timeText);
                int rate = Integer.parseInt(rateText);
                numVehEnteringRate newRate = new numVehEnteringRate(time, rate); //create new rate object

                //store new rate
                garages.get(selectedGarageIndex).variableNumVehPerMin.add(newRate);

                updateGarageList(garages.get(selectedGarageIndex));
                timeField.setText("");
                rateField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = rateList.getSelectedIndex(); //get item currently selected in the list

                String selectedGarageString = (String) garageSelectorComboBox.getSelectedItem(); //get current selected garage
                int selectedGarageIndex = -1;

                for(Garage garage : garages) {
                    if(selectedGarageString.equals(garage.getName())) {
                        selectedGarageIndex = garage.getGarageID();
                    }
                }

                if (selectedIndex >= 0) {
                    garages.remove(selectedIndex);
                    updateGarageList(garages.get(selectedGarageIndex));
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedGarageString = (String) garageSelectorComboBox.getSelectedItem(); //get currently selected garage
                int selectedGarageIndex = -1;

                for(Garage garage : garages) {
                    if(selectedGarageString.equals(garage.getName())) {
                        selectedGarageIndex = garage.getGarageID();
                    }
                }

                showRateList(garages.get(selectedGarageIndex));
            }
        });

        // ActionListener for the Done button
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //spawn new simUserInput with garages
                SimulationUserInputGUI simulation = new SimulationUserInputGUI(garages, presetType);
                simulation.setVisible(true);
            }
        });
        garageSelectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGarageString = (String) garageSelectorComboBox.getSelectedItem(); //get currently selected garage
                int selectedGarageIndex = -1;

                for(Garage garage : garages) {
                    if(selectedGarageString.equals(garage.getName())) {
                        selectedGarageIndex = garage.getGarageID();
                    }
                }

                updateGarageList(garages.get(selectedGarageIndex));
            }
        });


        JPanel footerPanel = PDMPanels.createFooter();
        mainFrame.add(footerPanel, BorderLayout.SOUTH);

        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(inputPanel, BorderLayout.WEST);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.add(listPanel, BorderLayout.EAST);

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    private void updateGarageList(Garage garage) {

        rateListModel.clear();
        for(int i = 0; i < garage.variableNumVehPerMin.size(); i++) {
            rateListModel.addElement("Time: " + garage.variableNumVehPerMin.get(i).getTime() + ", Rate: " + garage.variableNumVehPerMin.get(i).getRate());
        }


    }

    private void showRateList(Garage garage) {
        updateGarageList(garage);

        JFrame rateListFrame = new JFrame("Rate List");
        rateListFrame.setLayout(new BorderLayout());
        rateListFrame.setPreferredSize(new Dimension(500, 400));

        JPanel headingPanel = PDMPanels.createHeader("Saved Rates for Simulation");

        JList<String> viewGarageList = new JList<>(rateListModel);
        viewGarageList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(viewGarageList);

        rateListFrame.add(scrollPane, BorderLayout.CENTER);

        rateListFrame.add(headingPanel, BorderLayout.NORTH);
        rateListFrame.pack();
        rateListFrame.setVisible(true);
        rateListFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        //test data

        ArrayList<Garage> garages = new ArrayList<Garage>();

        Garage garage1 = new Garage("43rd & Elkhorn Ave", 655);
        garage1.setGarageID(0);
        garage1.setAvgParkingDuration(180);
        garage1.setNumVehiclesEnteringPerMin(1); //starts at 1 car per min at 7am
        garage1.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage1.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage1.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage1.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage1.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage1.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage1.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        Garage garage2 = new Garage("Constant Center South", 1535);
        garage2.setGarageID(1);
        garage2.setAvgParkingDuration(180);
        garage2.setNumVehiclesEnteringPerMin(1);
        garage2.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage2.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage2.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage2.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage2.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage2.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage2.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        Garage garage3 = new Garage("Constant Center North", 1045);
        garage3.setGarageID(2);
        garage3.setAvgParkingDuration(180);
        garage3.setNumVehiclesEnteringPerMin(1);
        garage3.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage3.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage3.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage3.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage3.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage3.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage3.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        Garage garage4 = new Garage("49th Street Stadium", 745);
        garage4.setGarageID(3);
        garage4.setAvgParkingDuration(180);
        garage4.setNumVehiclesEnteringPerMin(1);
        garage4.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage4.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 5));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage4.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 7)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage4.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 5)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage4.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 3)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage4.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 2)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage4.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        garages.add(0, garage1);
        garages.add(1, garage2);
        garages.add(2, garage3);
        garages.add(3, garage4);

        vehicleRateGUI newGUI = new vehicleRateGUI(garages, 1);

    }


}
