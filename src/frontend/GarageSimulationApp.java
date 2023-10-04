package frontend;
import backend.database.vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
    public class GarageSimulationApp extends JFrame {
        private JSlider capacitySlider;
        private JLabel capacityValue;
        private JSlider garage1CapacitySlider;
        private JLabel garage1CapacityValue;
        private JSlider garage2CapacitySlider;
        private JLabel garage2CapacityValue;
        private JSlider vehiclesSlider;
        private JLabel vehiclesValue;
        private JSlider parkTimeSlider;
        private JSlider timeToParkSlider;
        private JLabel timeToParkValue;
        private JLabel parkTimeValue;
        private JSlider durationSlider;
        private JLabel durationValue;
        private JButton startSimulationButton;
        private JButton seeTrendsButton;

        public GarageSimulationApp() {
            setTitle("PDM Garage Simulation"); // Updated the title
            setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximize the screen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            //setLayout(new GridBagLayout()); //I move this down to initialize header first
            //GridBagConstraints gbc = new GridBagConstraints();
            //gbc.insets = new Insets(5, 5, 5, 5);
            //gbc.fill = GridBagConstraints.HORIZONTAL;

            // Create header panel TEMPORARILY COMMENTED OUT FOR DEMO

//            JPanel headerPanel = new JPanel();
//            headerPanel.setBackground(new Color(122, 114, 114, 173));
//            headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
//
//            JLabel headingLabel = new JLabel("PDM Garage Simulation"); // Updated header label
//            headingLabel.setFont(new Font("Roboto", Font.BOLD, 32));
//            headingLabel.setForeground(Color.DARK_GRAY);
//            headingLabel.setBackground(Color.lightGray);
//            headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            headerPanel.add(headingLabel);
//
//            add(headerPanel, BorderLayout.PAGE_START);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            //gbc.fill = GridBagConstraints.HORIZONTAL;

            // Create and initialize components
            capacitySlider = createSlider(1, 500, 1, gbc);
            capacityValue = createLabel("1", gbc);
            garage1CapacitySlider = createSlider(1, 500, 1, gbc);
            garage1CapacityValue = createLabel("1", gbc);
            garage2CapacitySlider = createSlider(1, 500, 1, gbc);
            garage2CapacityValue = createLabel("1", gbc);
            vehiclesSlider = createSlider(1, 60, 1, gbc);
            vehiclesValue = createLabel("1", gbc);
            timeToParkSlider = createSlider(1, 60, 1, gbc);
            timeToParkValue = createLabel("1", gbc);
            parkTimeSlider = createSlider(1, 180, 1, gbc);
            parkTimeValue = createLabel("1", gbc);
            durationSlider = createSlider(1, 720, 1, gbc);
            durationValue = createLabel("1", gbc);
            startSimulationButton = createButton("Start Simulation", gbc);
            seeTrendsButton = createButton("See Trends", gbc);
            seeTrendsButton.setVisible(false);


            // Add event listeners
            addSliderChangeListener(capacitySlider, capacityValue);
            addSliderChangeListener(garage1CapacitySlider, garage1CapacityValue);
            addSliderChangeListener(garage2CapacitySlider, garage2CapacityValue);
            addSliderChangeListener(vehiclesSlider, vehiclesValue);
            addSliderChangeListener(timeToParkSlider, timeToParkValue);
            addSliderChangeListener(parkTimeSlider, parkTimeValue);
            addSliderChangeListener(durationSlider, durationValue);

            startSimulationButton.addActionListener(e -> startSimulation(gbc));

            seeTrendsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(() -> {
                        trendsGUI trends = new trendsGUI();
                        trends.setVisible(true);
                    });
                }
            });

/*
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTH; // Align components to the top
            contentPanel.add(capacitySlider, gbc);
*/
            // Place components in the grid
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Football Stadium Capacity:"), gbc);
            gbc.gridx = 1;
            add(capacitySlider, gbc);
            gbc.gridx = 2;
            add(capacityValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("43rd & Elkhorn Capacity:"), gbc);
            gbc.gridx = 1;
            add(garage1CapacitySlider, gbc);
            gbc.gridx = 2;
            add(garage1CapacityValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("43rd & Bluestone Capacity:"), gbc);
            gbc.gridx = 1;
            add(garage2CapacitySlider, gbc);
            gbc.gridx = 2;
            add(garage2CapacityValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("Vehicles Entering per Minute:"), gbc);
            gbc.gridx = 1;
            add(vehiclesSlider, gbc);
            gbc.gridx = 2;
            add(vehiclesValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            add(new JLabel("Average Time to Park (minutes):"), gbc);
            gbc.gridx = 1;
            add(timeToParkSlider, gbc);
            gbc.gridx = 2;
            add(timeToParkValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            add(new JLabel("Average Time Parked (minutes):"), gbc);
            gbc.gridx = 1;
            add(parkTimeSlider, gbc);
            gbc.gridx = 2;
            add(parkTimeValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            add(new JLabel("Simulation Duration (minutes):"), gbc);
            gbc.gridx = 1;
            add(durationSlider, gbc);
            gbc.gridx = 2;
            add(durationValue, gbc);

            gbc.gridwidth = 3;
            gbc.gridx = 0;
            gbc.gridy = 7;
            add(startSimulationButton, gbc);


            gbc.gridy = 12;
            add(seeTrendsButton, gbc);


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

        private JLabel createLabel(String text, GridBagConstraints gbc) {
            JLabel label = new JLabel(text);
            gbc.weightx = 0.0;
            gbc.gridx = 2;
            return label;
        }

        private JButton createButton(String text, GridBagConstraints gbc) {
            JButton button = new JButton(text);
            gbc.gridwidth = 3;
            gbc.gridx = 0;
            gbc.gridy++;
            return button;
        }



        private void addSliderChangeListener(JSlider slider, JLabel label) {
            slider.addChangeListener(e -> label.setText(String.valueOf(slider.getValue())));
        }

        // Converts time in minutes to AM/PM hourly time
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

        private void assignGarage(vehicle vehicleToAssign, int footballStadiumCapacity, int footballStadiumOccupancy,
                                  int garage1Capacity, int garage1Occupancy, int garage2Capacity, int garage2Occupancy) {

            // Check if all garages are full
            if (footballStadiumOccupancy >= footballStadiumCapacity && garage1Occupancy >= garage1Capacity
                    && garage2Occupancy >= garage2Capacity) {
                // All garages are full, return without doing anything
                return;
            }

            // Generate a random integer 0-2 to pick one of the 3 garages to park
            int randomGarageInt = ThreadLocalRandom.current().nextInt(0, 3);

            int chosenGarageCapacity = 0;
            int chosenGarageOccupancy = 0;

            if (randomGarageInt == 0) {
                chosenGarageCapacity = footballStadiumCapacity;
                chosenGarageOccupancy = footballStadiumOccupancy;
            } else if (randomGarageInt == 1) {
                chosenGarageCapacity = garage1Capacity;
                chosenGarageOccupancy = garage1Occupancy;
            } else if (randomGarageInt == 2) {
                chosenGarageCapacity = garage2Capacity;
                chosenGarageOccupancy = garage2Occupancy;
            }

            // Check if the chosen garage is 90% or greater capacity
            if (chosenGarageOccupancy * 0.9 >= chosenGarageCapacity) {

                // Check if all garages are above 90% or greater capacity, continue chosen assignment if so.
                // Else, call assignGarage recursively to assign the vehicle to a different garage.
                if (footballStadiumOccupancy >= 0.9 * footballStadiumCapacity && garage1Occupancy >= 0.9 * garage1Capacity
                    && garage2Occupancy >= 0.9 * garage2Capacity) {

                    if (chosenGarageCapacity > chosenGarageOccupancy) {

                        vehicleToAssign.setParked(true);
                        vehicleToAssign.setGarageIndex(randomGarageInt);

                    } else {

                        assignGarage(vehicleToAssign, footballStadiumCapacity, footballStadiumOccupancy,
                                    garage1Capacity, garage1Occupancy, garage2Capacity, garage2Occupancy);

                    }

                    assignGarage(vehicleToAssign, footballStadiumCapacity, footballStadiumOccupancy,
                                garage1Capacity, garage1Occupancy, garage2Capacity, garage2Occupancy);

                }

            } else {

                vehicleToAssign.setParked(true);
                vehicleToAssign.setGarageIndex(randomGarageInt);

            }

        }

        private void startSimulation(GridBagConstraints gbc) {

            Thread simulationThread = new Thread(() -> {

                int time = 420;
                int footballStadiumCapacity = capacitySlider.getValue();
                int garage1Capacity = garage1CapacitySlider.getValue();
                int garage2Capacity = garage2CapacitySlider.getValue();
                int footballStadiumOccupancy = 0;
                int garage1Occupancy = 0;
                int garage2Occupancy = 0;
                int endTime = time + durationSlider.getValue();
                int avgVehiclesParking = vehiclesSlider.getValue();
                int avgTimeToPark = timeToParkSlider.getValue();
                int avgTimeParked = parkTimeSlider.getValue();

                getContentPane().remove(capacitySlider);
                getContentPane().remove(garage1CapacitySlider);
                getContentPane().remove(garage2CapacitySlider);
                getContentPane().remove(vehiclesSlider);
                getContentPane().remove(timeToParkSlider);
                getContentPane().remove(parkTimeSlider);
                getContentPane().remove(durationSlider);
                getContentPane().remove(startSimulationButton);

                seeTrendsButton.setVisible(true);

                JLabel timeLabel = createLabel("Time: " + time, gbc);
                JLabel capacityLabel = createLabel("Football Stadium Capacity: " + "0/" + capacitySlider.getValue(), gbc);
                JLabel garage1CapacityLabel = createLabel("43rd & Elkhorn Capacity:  " + "0/" + garage1CapacitySlider.getValue(), gbc);
                JLabel garage2CapacityLabel = createLabel("43rd & Bluestone Capacity: " + "0/" + garage2CapacitySlider.getValue(), gbc);

                gbc.gridx = 0;
                gbc.gridy = 7;
                add(timeLabel, gbc);
                gbc.gridy = 8;
                add(capacityLabel, gbc);
                gbc.gridy = 9;
                add(garage1CapacityLabel, gbc);
                gbc.gridy = 10;
                add(garage2CapacityLabel, gbc);

                // Repaint the frame to update the changes
                getContentPane().validate();
                getContentPane().repaint();

                List<vehicle> parkingVehicleList = new CopyOnWriteArrayList<>();

                while (time < endTime) {

                    time += 1;

                    timeLabel.setText("Time: " + convertMinutesToAMPM(time));

                    // Generate a random non-negative number with avgVehiclesParking as the median
                    Random random = new Random();
                    int offset = random.nextInt(11) - 5;
                    int numVehiclesParking = Math.max(avgVehiclesParking + offset, 0);

                    for (vehicle vehicle : parkingVehicleList) {

                        if (!vehicle.getParked()) {

                            int newParkingInValue = vehicle.getParking_in() - 1;
                            vehicle.setParking_in(newParkingInValue);

                            // Time to park!
                            if (vehicle.getParking_in() <= 0) {

                                assignGarage(vehicle, footballStadiumCapacity, footballStadiumOccupancy,
                                        garage1Capacity, garage1Occupancy, garage2Capacity, garage2Occupancy);

                                if (vehicle.getGarageIndex() == 0) {

                                    if (footballStadiumOccupancy < footballStadiumCapacity) {
                                        footballStadiumOccupancy += 1;
                                        capacityLabel.setText("Football Stadium Capacity: " + footballStadiumOccupancy + "/" + footballStadiumCapacity);
                                    }

                                } else if (vehicle.getGarageIndex() == 1) {

                                    if (garage1Occupancy < garage1Capacity) {
                                        garage1Occupancy += 1;
                                        garage1CapacityLabel.setText("43rd & Elkhorn Capacity: " + garage1Occupancy + "/" + garage1Capacity);
                                    }

                                } else if (vehicle.getGarageIndex() == 2) {

                                    if (garage2Occupancy < garage2Capacity) {
                                        garage2Occupancy += 1;
                                        garage2CapacityLabel.setText("43rd & Bluestone Capacity: " + garage2Occupancy + "/" + garage2Capacity);
                                    }

                                }

                            }

                        } else {

                            int newParkingOutValue = vehicle.getparking_out() - 1;
                            vehicle.setparking_out(newParkingOutValue);

                            // Vehicle exits garage
                            if (vehicle.getparking_out() <= 0) {

                                if (vehicle.getGarageIndex() == 0) {

                                    if (footballStadiumOccupancy > 0) {
                                        footballStadiumOccupancy -= 1;
                                        capacityLabel.setText("Football Stadium Capacity: " + footballStadiumOccupancy + "/" + footballStadiumCapacity);
                                    }

                                } else if (vehicle.getGarageIndex() == 1) {

                                    if (garage1Occupancy > 0) {
                                        garage1Occupancy -= 1;
                                        garage1CapacityLabel.setText("43rd & Elkhorn Capacity: " + garage1Occupancy + "/" + garage1Capacity);
                                    }

                                } else if (vehicle.getGarageIndex() == 2) {

                                    if (garage2Occupancy > 0) {
                                        garage2Occupancy -= 1;
                                        garage2CapacityLabel.setText("43rd & Bluestone Capacity: " + garage2Occupancy + "/" + garage2Capacity);
                                    }

                                }

                                parkingVehicleList.remove(vehicle);

                            }

                        }

                    }

                    // Add new vehicles looking for parking
                    for (int i = 0; i < numVehiclesParking; i++) {

                        vehicle newVehicle = new vehicle();

                        int ttpOffset = random.nextInt(11) - 5;
                        int timeToPark = Math.max(avgTimeToPark + offset, 0);

                        int tpOffset = random.nextInt(11) - 5;
                        int timeParked = Math.max(avgTimeParked + offset, 0);

                        newVehicle.setParking_in(timeToPark);
                        newVehicle.setparking_out(timeParked);

                        parkingVehicleList.add(newVehicle);

                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            });

            simulationThread.start();

        }

        private void animateSlider(JSlider slider, int value) {
            int stepDuration = 50;
            int totalSteps = 20;
            int stepValue = (value - slider.getValue()) / totalSteps;

            Timer timer = new Timer(stepDuration, new ActionListener() {
                int currentStep = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentStep < totalSteps) {
                        slider.setValue(slider.getValue() + stepValue);
                        currentStep++;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });

            timer.start();
        }

    }