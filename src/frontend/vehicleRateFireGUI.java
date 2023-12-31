package frontend;

import backend.database.Garage;
import backend.database.closeGarage;
import backend.database.numVehEnteringRate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vehicleRateFireGUI extends JFrame{
    private JFrame mainFrame;
    private JTextField timeField;
    private JTextField rateField;
    private JTextField fireField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private DefaultListModel<String> rateListModel;
    private JList<String> rateList;
    private JButton doneButton;
    private JComboBox<String> garageSelectorComboBox;
    private JComboBox<String> garageSelectorFireComboBox;
    public vehicleRateFireGUI(ArrayList<Garage> garages, int presetType) {

        mainFrame = new JFrame("Adjust Vehicle Rate (Closure)");
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
        mainFrame.setContentPane(backgroundPanel);


        // Create a header panel
        JPanel headerPanel = PDMPanels.createHeader("Adjust Vehicle Rate (Closure)");


        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridLayout(8, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 150));


        garageSelectorComboBox = new JComboBox<String>();

        garageSelectorComboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JLabel selectorLabel = new JLabel("Garage:");
        selectorLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        garageSelectorComboBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
        garageSelectorComboBox.setOpaque(false);

        // Add items to the combo box
        for (Garage garage : garages) {
            garageSelectorComboBox.addItem(garage.getName());
        }

        garageSelectorFireComboBox = new JComboBox<String>();

        garageSelectorFireComboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JLabel selectorFireLabel = new JLabel("Garage:");
        selectorFireLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        garageSelectorFireComboBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
        garageSelectorFireComboBox.setOpaque(false);

        // Add items to the combo box
        for (Garage garage : garages) {
            garageSelectorFireComboBox.addItem(garage.getName());
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


        JLabel fireLabel = new JLabel("Closure:");
        fireLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        JLabel fireLabel2 = new JLabel("");
        fireLabel2.setFont(new Font("Monospaced", Font.BOLD, 18));

        startTimeField = new JTextField(15);
        startTimeField.setBackground(Color.LIGHT_GRAY);
        JLabel startTimeLabel = new JLabel("Start Time:");
        startTimeLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        startTimeField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        startTimeField.setText(Integer.toString(garages.get(0).closeGarageList.get(0).getTime()));

        endTimeField = new JTextField(15);
        endTimeField.setBackground(Color.LIGHT_GRAY);
        JLabel endTimeLabel = new JLabel("End Time:");
        endTimeLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        endTimeField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        endTimeField.setText(Integer.toString(garages.get(0).closeGarageList.get(1).getTime()));

        inputPanel.add(selectorLabel);
        inputPanel.add(garageSelectorComboBox);
        inputPanel.add(nameLabel);
        inputPanel.add(timeField);
        inputPanel.add(capacityLabel);
        inputPanel.add(rateField);
        inputPanel.add(fireLabel);
        inputPanel.add(fireLabel2);
        inputPanel.add(selectorFireLabel);
        inputPanel.add(garageSelectorFireComboBox);
        inputPanel.add(startTimeLabel);
        inputPanel.add(startTimeField);
        inputPanel.add(endTimeLabel);
        inputPanel.add(endTimeField);


        JPanel buttonPanel =  new JPanel();
        buttonPanel.setOpaque(false);
        GridBagConstraints gbcPanel1 = new GridBagConstraints();
        gbcPanel1.gridx = 0;
        gbcPanel1.gridy = 1;
        gbcPanel1.weighty =0;
        gbcPanel1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbcPanel2 = new GridBagConstraints();
        gbcPanel2.gridx = 0;
        gbcPanel2.gridy = 2;
        gbcPanel2.weighty =0;
        gbcPanel2.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbcPanel3 = new GridBagConstraints();
        gbcPanel3.gridx = 0;
        gbcPanel3.gridy = 3;
        gbcPanel3.weighty =1;
        gbcPanel3.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbcPanel4 = new GridBagConstraints();
        gbcPanel4.gridx = 0;
        gbcPanel4.gridy = 4;
        gbcPanel4.weighty =0;
        gbcPanel4.fill = GridBagConstraints.BOTH;
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(350, 250, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        Dimension maxButtonSize = new Dimension(250, 60);
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        saveButton.setBackground(Color.YELLOW);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        saveButton.setMaximumSize(maxButtonSize);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        deleteButton.setBackground(Color.red);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        deleteButton.setMaximumSize(maxButtonSize);

        JButton viewButton = new JButton("View Garages");
        viewButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        viewButton.setBackground(Color.PINK);
        viewButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        viewButton.setMaximumSize(maxButtonSize);

        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
        buttonPanel.add(viewButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));

        doneButton = new JButton("Done");
        doneButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        doneButton.setBackground(Color.green);
        doneButton.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
        buttonPanel.add(doneButton); // Add the Done button
        buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
        doneButton.setMaximumSize(maxButtonSize);

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

                String selectedGarageString = (String) garageSelectorComboBox.getSelectedItem();
                int selectedGarageIndex = -1;

                for(Garage garage : garages) {
                    if (selectedGarageString.equals(garage.getName())) {
                        selectedGarageIndex = garage.getGarageID();
                    }

                }

                String timeText = timeField.getText().trim();
                String rateText = rateField.getText().trim();
                String startTimeText = startTimeField.getText().trim();
                String endTimeText = endTimeField.getText().trim();

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
                //save the fire settings
                String selectedFireGarageString = (String) garageSelectorFireComboBox.getSelectedItem(); //get currently selected garage
                int startFireTime = Integer.parseInt(startTimeField.getText());
                int endFireTime = Integer.parseInt(endTimeField.getText());
                int selectedFireGarageIndex = -1;

                for (Garage garage : garages) {
                    if(selectedFireGarageString.equals(garage.getName())) {
                        selectedFireGarageIndex = garage.getGarageID();
                    }
                }

                if(selectedFireGarageIndex == 0 && startFireTime == 480 && endFireTime == 720) { //if the fire settings are unchanged
                    //spawn new simUserInput with garages
                    SimulationUserInputGUI simulation = new SimulationUserInputGUI(garages, presetType);
                    simulation.setVisible(true);
                } else { //if the fire settings have been changed

                    //delete old fire settings from elkhorn
                    garages.get(0).closeGarageList.remove(1);
                    garages.get(0).closeGarageList.remove(0);

                    //add new fire settings
                    closeGarage startCloseGarage = new closeGarage(startFireTime, true);
                    closeGarage endCloseGarage = new closeGarage(endFireTime, false);

                    garages.get(selectedFireGarageIndex).closeGarageList.add(startCloseGarage);
                    garages.get(selectedFireGarageIndex).closeGarageList.add(endCloseGarage);

                    //spawn new simUserInput with garages
                    SimulationUserInputGUI simulation = new SimulationUserInputGUI(garages, presetType);
                    simulation.setVisible(true);
                }

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
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                vehicleRateFireGUI newGUI = new vehicleRateFireGUI(garages, 1);
                newGUI.setVisible(true);
            }
        });

        //vehicleRateFireGUI newGUI = new vehicleRateFireGUI(garages, 1);

    }
}
