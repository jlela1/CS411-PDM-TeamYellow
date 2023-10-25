package frontend;
import backend.database.Garage;
import backend.database.Trend;
import backend.database.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GarageSimulation {

    private ArrayList<Garage> garages;
    public GarageSimulation(SimulationGUI simulationGUI, ArrayList<Garage> garageList, int simulationDuration, int timeValue) {

        garages = garageList;

        Thread simulationThread = new Thread(() -> {

            int time = timeValue;
            int endTime = time + simulationDuration;
            String notificiation = "Notification: ";

            //establishing SQL trend stuff
            Trend stats;
            stats = new Trend();
            // end of SQL initialization

            while (time < endTime) {

                time += 1;

                // SQL function call NEEDS TO BE FIXED
                for (Garage garage : garages) {
                    stats.setGarage(stats, time, garage.getOccupancy(), garage.getMaxCapacity(), convertMinutesToAMPM(time), garage.getName());
                    // ...
                }

                // end of SQL stuff


                for (int i = 0; i < garages.size(); i++) { //for each garage, execute vehicle algorithms

                    int numVehiclesParking = garages.get(i).getNumVehiclesEnteringPerMin(); //get num of cars entering specific garage this minute

                    for (vehicle vehicle : garages.get(i).parkingVehicleList) { //for each vehicle in garages's specific vehicle list

                        if (!vehicle.getParked()) { //if the vehicle is not parked

                            vehicle.setParking_in(vehicle.getParking_in() - 1); //subtract 1 minute from park waiting time

                            // Time to park!
                            if (vehicle.getParking_in() <= 0) { //if wait to park equal to 0: waiting ended

                                String tempNotification = assignGarage(vehicle, garages, i); //assign vehicle to garage, output string if full
                                if (tempNotification != "") {
                                    notificiation = "Notification: " + tempNotification;
                                }

                                //update gui with notifications
                                simulationGUI.updateSimLabels(garages, time, notificiation);


                                //update counters
                                if (vehicle.getGarageIndex() == 0) { //check if assigned to garage 0
                                    garages.get(0).setOccupancy(garages.get(0).getOccupancy() + 1); //add one to occupancy

                                } else if (vehicle.getGarageIndex() == 1) { //check if assigned to garage 1 and garage 1 not full
                                    garages.get(1).setOccupancy(garages.get(1).getOccupancy() + 1); //add one to occupancy

                                } else if (vehicle.getGarageIndex() == 2) { //check if assigned to garage 2 and garage 2 not full
                                    garages.get(2).setOccupancy(garages.get(2).getOccupancy() + 1); //add one to occupancy

                                }
                                if (vehicle.getGarageIndex() == 3) { //check if assigned to garage 3 and garage 3 not full
                                    garages.get(3).setOccupancy(garages.get(3).getOccupancy() + 1); //add one to occupancy

                                }
                                if (vehicle.getGarageIndex() == 4) { //check if assigned to garage 4 and garage 4 not full
                                    garages.get(4).setOccupancy(garages.get(4).getOccupancy() + 1); //add one to occupancy

                                }
                            }

                        } else {

                            vehicle.setparking_out(vehicle.getparking_out() - 1); //decrement time parked by 1 minute

                            // Vehicle exits garage
                            if (vehicle.getparking_out() <= 0) {

                                if ((vehicle.getGarageIndex() == 0) && (garages.get(0)).getOccupancy() > 0) { //is vehicle in garage 0
                                    garages.get(0).setOccupancy(garages.get(0).getOccupancy() - 1); //decrement garage 0

                                } else if ((vehicle.getGarageIndex() == 1) && (garages.get(1)).getOccupancy() > 0) { //is vehicle in garage 1
                                    garages.get(1).setOccupancy(garages.get(1).getOccupancy() - 1); //decrement garage 1

                                } else if ((vehicle.getGarageIndex() == 2) && (garages.get(2)).getOccupancy() > 0) { //is vehicle in garage 2
                                    garages.get(2).setOccupancy(garages.get(2).getOccupancy() - 1); //decrement garage 2

                                } else if ((vehicle.getGarageIndex() == 3) && (garages.get(3)).getOccupancy() > 0) { //is vehicle in garage 3
                                    garages.get(3).setOccupancy(garages.get(3).getOccupancy() - 1); //decrement garage 3

                                } else if ((vehicle.getGarageIndex() == 4) && (garages.get(4)).getOccupancy() > 0) { //is vehicle in garage 4
                                    garages.get(4).setOccupancy(garages.get(4).getOccupancy() - 1); //decrement garage 4
                                }

                                garages.get(i).parkingVehicleList.remove(vehicle);

                            }

                        }

                    }

                    // Add new vehicles looking for parking
                    for (int j = 0; j < numVehiclesParking; j++) {

                        vehicle newVehicle = new vehicle();

                        Random random = new Random();
                        int timeToParkOffset = random.nextInt(11) - 5;
                        int timeToPark = Math.max(garages.get(i).getAvgTimeToPark() + timeToParkOffset, 0);

                        int timeParkedOffset = random.nextInt(11) - 5;
                        int timeParked = Math.max(garages.get(i).getAvgParkingDuration() + timeParkedOffset, 0);

                        newVehicle.setParking_in(timeToPark);
                        newVehicle.setparking_out(timeParked);

                        garages.get(i).parkingVehicleList.add(newVehicle);
                    }

                    simulationGUI.updateSimLabels(garages, time, notificiation);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

            stats.Write(); // write to file and SQL DB

        });

        simulationThread.start();

    }

    private String assignGarage(vehicle vehicleToAssign, ArrayList<Garage> garages, int prefGarage) {

        //logic:
        //check of all garages full
        //check if can park in preferred garage(less than 90% full): prefGarage
        //if not, assign to random garage thats less than 90% full
        //if all over 90% full:
        //check if preferred garage is less than 100% full, if so send to prefGarage
        //if not, send to random garage not 100% full

        // Check if all garages are full
        int fullCounter = 0; //initialize amount of garages full
        for (int i = 0; i < garages.size(); i++) {

            if (garages.get(i).getOccupancy() >= garages.get(i).getMaxCapacity()) { //check if each garage is full
                fullCounter++; //if garage full, increment.
            }
        }
        //if all garages full, return without doing anything
        if (fullCounter >= garages.size()) {
            return "All Garages Are Full! You cannot park at this time.";
        }

        // Check if the chosen garage is 90% or greater capacity (full/unlikely to find a spot)
        if (garages.get(prefGarage).getOccupancy() >= (garages.get(prefGarage).getMaxCapacity() * 0.9)) {

            //check if there are any garages below 90% capacity, if so, assign to a random one

            //check num of full garages
            int numFullGarages = 0; //initialize num of garages that are greater than 90% full
            for (int i = 0; i < garages.size(); i++) {
                if (garages.get(i).getOccupancy() >= (garages.get(i).getMaxCapacity() * 0.9)) {
                    numFullGarages++;
                }
            }

            //check if all garages full
            if (numFullGarages >= garages.size()) { //if all garages over 90% full

                //check if preferred garage less than 100% full
                if (garages.get(prefGarage).getMaxCapacity() > garages.get(prefGarage).getOccupancy()) {
                    vehicleToAssign.setParked(true);
                    vehicleToAssign.setGarageIndex(prefGarage);
                    return "Vehicle Assigned to Preferred Garage";
                } else { //pref garage is full, assign to another random garage less than 100% full

                    boolean successfullyAssigned = false; //temp var to support while loop to loop until random garage int matches an available garage
                    int randomGarageInt = 0;
                    while (successfullyAssigned == false) {

                        //generate random int based on how many garages (1-5)
                        randomGarageInt = 0;
                        switch (garages.size())
                        {
                            case 1:
                                randomGarageInt = ThreadLocalRandom.current().nextInt(0, 1);
                                break;
                            case 2:
                                randomGarageInt = ThreadLocalRandom.current().nextInt(0, 2);
                                break;
                            case 3:
                                randomGarageInt = ThreadLocalRandom.current().nextInt(0, 3);
                                break;
                            case 4:
                                randomGarageInt = ThreadLocalRandom.current().nextInt(0, 4);
                                break;
                            case 5:
                                randomGarageInt = ThreadLocalRandom.current().nextInt(0, 5);
                                break;
                        }

                        if (garages.get(randomGarageInt).getMaxCapacity() > garages.get(randomGarageInt).getOccupancy()) { //if the random garage is less than 100% full:

                            //assign vehicle to random garage
                            vehicleToAssign.setParked(true);
                            vehicleToAssign.setGarageIndex(randomGarageInt);

                            successfullyAssigned = true;

                        } //one of these if statements has to work, since we checked if all garages were full at the beginning. Cannot be infinite loop.
                    }

                    return "" + garages.get(prefGarage).getName() + " is full, assigning vehicle to " + garages.get(randomGarageInt).getName();

                }

            } else { //assign vehicle to a random less than 90% full garage


                boolean successfullyAssigned = false; //temp var to support while loop to loop until random garage int matches an available garage
                int randomGarageInt = 0;
                while (successfullyAssigned == false) {

                    //generate random int based on how many garages
                    randomGarageInt = 0;
                    switch (garages.size())
                    {
                        case 1:
                            randomGarageInt = ThreadLocalRandom.current().nextInt(0, 1);
                            break;
                        case 2:
                            randomGarageInt = ThreadLocalRandom.current().nextInt(0, 2);
                            break;
                        case 3:
                            randomGarageInt = ThreadLocalRandom.current().nextInt(0, 3);
                            break;
                        case 4:
                            randomGarageInt = ThreadLocalRandom.current().nextInt(0, 4);
                            break;
                        case 5:
                            randomGarageInt = ThreadLocalRandom.current().nextInt(0, 5);
                            break;
                    }

                    if ((garages.get(randomGarageInt).getMaxCapacity() * 0.9) > garages.get(randomGarageInt).getOccupancy()) { //if the random garage is less than 90% full:

                        //assign vehicle to the random garage

                        vehicleToAssign.setParked(true);
                        vehicleToAssign.setGarageIndex(randomGarageInt);

                        successfullyAssigned = true;

                    } //one of these if statements has to work, since we checked if all garages were full at the beginning. Cannot be infinite loop.
                }

                return "" + garages.get(prefGarage).getName() + " is full, assigning vehicle to " + garages.get(randomGarageInt).getName();
            }


        } else {

            vehicleToAssign.setParked(true);
            vehicleToAssign.setGarageIndex(prefGarage);

        }

        return "";
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

