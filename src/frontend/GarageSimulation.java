package frontend;
import backend.database.Garage;
import backend.database.MilitaryTimeConverter;
import backend.database.Trend;
import backend.database.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class GarageSimulation {

    private ArrayList<Garage> garages;
    private int currentPreset;
    public GarageSimulation(SimulationGUI simulationGUI, ArrayList<Garage> garageList, int simulationDuration, int timeValue, int presetType) {

        //preset type: 0 = none, 1 = normalday, 2 = fire in elkhorn, 3 = football

        currentPreset = presetType;

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
                // call func to get current military time
                MilitaryTimeConverter converter = new MilitaryTimeConverter();

                for (Garage garage : garages) {
                    stats.setGarage(stats, time, garage.getOccupancy(), garage.getMaxCapacity(), convertMinutesToAMPM(time), garage.getName(), converter.getMilitaryTime(), notificiation, garage.getGarageID(), garage.getNumVehiclesEnteringPerMin());
                    // ...
                }

                // end of SQL stuff

                //for each garage
                for (int i = 0; i < garages.size(); i++) { //for each garage, execute vehicle algorithms

                    //update num vehicles parking per minute
                    for (int j = 0; j < garages.get(i).variableNumVehPerMin.size(); j++) { //iterate over number of vehicle rate changes in stored arrayList
                        if (garages.get(i).variableNumVehPerMin.get(j).getTime() == time) { //if the time set in one of the arrayList values is equal to the current time (time to change vehicle rates)
                            garages.get(i).setNumVehiclesEnteringPerMin(garages.get(i).variableNumVehPerMin.get(j).getRate()); //set current num of vehicle rate to specified one stored in array
                        }
                    }

                    //update garage closed/open
                    for (int j = 0; j < garages.get(i).closeGarageList.size(); j++) { //iterate over number of changes in closing/opening
                        if (garages.get(i).closeGarageList.get(j).getTime() == time) { //if the time set in one of the arrayList values is equal to the current time (time to change garage closed status)
                            garages.get(i).setIsClosed(garages.get(i).closeGarageList.get(j).getClosed());

                            //send emergency notification
                            if(garages.get(i).closeGarageList.get(j).getClosed() == true) {
                                notificiation = "Notification: " + garages.get(i).getName() + " is now closed!";
                            } else {
                                notificiation = "Notification: " + garages.get(i).getName() + " is now open!";
                            }
                            //push new notification to GUI
                            simulationGUI.updateSimLabels(garages, time, notificiation);

                        }
                    }



                    int numVehiclesParking = garages.get(i).getNumVehiclesEnteringPerMin(); //get num of cars entering specific garage this minute

                    for (vehicle vehicle : garages.get(i).parkingVehicleList) { //for each vehicle in garages's specific vehicle list

                        int numOfCarsParkingThisTick = 0; //track number of cars parking this tick for average feedback calculation
                        int totalFeedbackRatingCount = 0; //total feedback from all cars parking this tick for average feedback calculation

                        if (!vehicle.getParked()) { //if the vehicle is not parked

                            vehicle.setParking_in(vehicle.getParking_in() - 1); //subtract 1 minute from park waiting time

                            // Time to park!
                            if (vehicle.getParking_in() <= 0) { //if wait to park equal to 0: waiting ended

                                String tempNotification = assignGarage(vehicle, garages, i); //assign vehicle to garage, output string if full
                                if (tempNotification != "") {
                                    notificiation = "Notification: " + tempNotification;
                                }

                                //update feedback for vehicles parking this minute
                                numOfCarsParkingThisTick++;
                                totalFeedbackRatingCount += vehicle.getFeedbackRating();

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
                            if ((vehicle.getparking_out() <= 0) && (!(garages.get(vehicle.getGarageIndex()).getIsClosed()))) { //if the time parked has decreased to 0 and the garage is not closed:

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

                        //compile and store feedback
                        double averageFeedbackRating = totalFeedbackRatingCount / numOfCarsParkingThisTick;
                        garages.get(i).setAverageFeedback(averageFeedbackRating);
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

                        //set if vehicle is football authorized based on originated garage
                        if (garages.get(i).getName().equals("49th Street Stadium") || garages.get(i).getName().equals("43rd & Elkhorn Ave")) {
                            newVehicle.setFootballAuthorized(true);
                        }

                        garages.get(i).parkingVehicleList.add(newVehicle);
                    }

                    simulationGUI.updateSimLabels(garages, time, notificiation);

                    try {
                        Thread.sleep(10);
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
        //check if can park in preferred garage(less than 90% full) and not closed: prefGarage
        //if not, assign to random garage thats less than 90% full and not closed
        //if all over 90% full:
        //check if preferred garage is less than 100% full and not closed, if so send to prefGarage
        //if not, send to random garage not 100% full and not closed

        // Check if all garages are full or closed
        int fullCounter = 0; //initialize amount of garages full
        for (int i = 0; i < garages.size(); i++) {

            if ((garages.get(i).getOccupancy() >= garages.get(i).getMaxCapacity()) || (garages.get(i).getIsClosed())) { //check if each garage is full or closed
                fullCounter++; //if garage full, increment.
            }
        }
        //if all garages full, return without doing anything
        if (fullCounter >= garages.size()) {

            //feedback section
            int randomFeedbackInt = ThreadLocalRandom.current().nextInt(0, 2); //random feedback value from 0-1
            vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback of garages are all full


            return "All Garages Are Full or Closed! You cannot park at this time.";
        }

        // Check if the chosen garage is 90% or greater capacity or closed (full/unlikely to find a spot or closed)
        if ((garages.get(prefGarage).getOccupancy() >= (garages.get(prefGarage).getMaxCapacity() * 0.9)) || (garages.get(prefGarage).getIsClosed())) {

            //check if there are any garages below 90% capacity and are not closed, if so, assign to a random one

            //check num of full or closed garages
            int numFullGarages = 0; //initialize num of garages that are greater than 90% full or closed
            for (int i = 0; i < garages.size(); i++) {
                if ((garages.get(i).getOccupancy() >= (garages.get(i).getMaxCapacity() * 0.9)) || (garages.get(i).getIsClosed())) {
                    numFullGarages++;
                }
            }

            //check if all garages full or closed
            if (numFullGarages >= garages.size()) { //if all garages over 90% full or closed

                //check if preferred garage less than 100% full and not closed
                if ((garages.get(prefGarage).getMaxCapacity() > garages.get(prefGarage).getOccupancy()) && (!(garages.get(prefGarage).getIsClosed()))) {

                    //check if football preset is activated, garage assigned is a football garage, and if the vehicle is football authorized:
                    if((currentPreset == 3) && (garages.get(prefGarage).getName().equals("49th Street Stadium") || garages.get(prefGarage).getName().equals("43rd & Elkhorn Ave")) && (vehicleToAssign.getFootballAuthorized())) {
                        vehicleToAssign.setParked(true);
                        vehicleToAssign.setGarageIndex(prefGarage);

                        //feedback for getting preferred garage
                        int randomFeedbackInt = ThreadLocalRandom.current().nextInt(4, 6); //random feedback value from 4-5
                        vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                        return "Vehicle Assigned to Preferred Garage";
                    } else if((currentPreset == 3) && (!(garages.get(prefGarage).getName().equals("49th Street Stadium") || garages.get(prefGarage).getName().equals("43rd & Elkhorn Ave")))) {
                        vehicleToAssign.setParked(true);
                        vehicleToAssign.setGarageIndex(prefGarage);

                        //feedback for getting preferred garage
                        int randomFeedbackInt = ThreadLocalRandom.current().nextInt(4, 6); //random feedback value from 4-5
                        vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                        return "Vehicle Assigned to Preferred Garage";
                    } else {
                        vehicleToAssign.setParked(true);
                        vehicleToAssign.setGarageIndex(prefGarage);

                        //feedback for getting preferred garage
                        int randomFeedbackInt = ThreadLocalRandom.current().nextInt(4, 6); //random feedback value from 4-5
                        vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                        return "Vehicle Assigned to Preferred Garage";
                    }

                } else { //pref garage is full or closed, assign to another random garage less than 100% full

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

                        if ((garages.get(randomGarageInt).getMaxCapacity() > garages.get(randomGarageInt).getOccupancy()) && (!(garages.get(randomGarageInt).getIsClosed()))) { //if the random garage is less than 100% full AND not closed:

                            //check if football preset is activated, garage assigned is a football garage, and if the vehicle is football authorized:
                            if((currentPreset == 3) && (garages.get(randomGarageInt).getName().equals("49th Street Stadium") || garages.get(randomGarageInt).getName().equals("43rd & Elkhorn Ave")) && (vehicleToAssign.getFootballAuthorized())) {
                                //assign vehicle to random garage
                                vehicleToAssign.setParked(true);
                                vehicleToAssign.setGarageIndex(randomGarageInt);

                                //feedback for getting random non preferred garage
                                int randomFeedbackInt = ThreadLocalRandom.current().nextInt(2, 4); //random feedback value from 4-5
                                vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                                successfullyAssigned = true;
                            } else if((currentPreset == 3) && (!(garages.get(randomGarageInt).getName().equals("49th Street Stadium") || garages.get(randomGarageInt).getName().equals("43rd & Elkhorn Ave")))) {
                                vehicleToAssign.setParked(true);
                                vehicleToAssign.setGarageIndex(randomGarageInt);

                                //feedback for getting random non preferred garage
                                int randomFeedbackInt = ThreadLocalRandom.current().nextInt(2, 4); //random feedback value from 4-5
                                vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                                successfullyAssigned = true;
                            } else {
                                vehicleToAssign.setParked(true);
                                vehicleToAssign.setGarageIndex(randomGarageInt);

                                //feedback for getting random non preferred garage
                                int randomFeedbackInt = ThreadLocalRandom.current().nextInt(2, 4); //random feedback value from 4-5
                                vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                                successfullyAssigned = true;
                            }



                        } //one of these if statements has to work, since we checked if all garages were full at the beginning. Cannot be infinite loop.
                    }

                    return "" + garages.get(prefGarage).getName() + " is full, assigning vehicle to " + garages.get(randomGarageInt).getName();

                }

            } else { //assign vehicle to a random less than 90% full garage thats not closed


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

                    if (((garages.get(randomGarageInt).getMaxCapacity() * 0.9) > garages.get(randomGarageInt).getOccupancy()) && (!(garages.get(randomGarageInt).getIsClosed()))) { //if the random garage is less than 90% full and not closed:

                        //assign vehicle to the random garage

                        //check if football preset is activated, garage assigned is a football garage, and if the vehicle is football authorized:
                        if((currentPreset == 3) && (garages.get(randomGarageInt).getName().equals("49th Street Stadium") || garages.get(randomGarageInt).getName().equals("43rd & Elkhorn Ave")) && (vehicleToAssign.getFootballAuthorized())) {
                            //assign vehicle to random garage
                            vehicleToAssign.setParked(true);
                            vehicleToAssign.setGarageIndex(randomGarageInt);

                            //feedback for getting random non preferred garage
                            int randomFeedbackInt = ThreadLocalRandom.current().nextInt(2, 4); //random feedback value from 4-5
                            vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                            successfullyAssigned = true;
                        } else if((currentPreset == 3) && (!(garages.get(randomGarageInt).getName().equals("49th Street Stadium") || garages.get(randomGarageInt).getName().equals("43rd & Elkhorn Ave")))) {
                            vehicleToAssign.setParked(true);
                            vehicleToAssign.setGarageIndex(randomGarageInt);

                            //feedback for getting random non preferred garage
                            int randomFeedbackInt = ThreadLocalRandom.current().nextInt(2, 4); //random feedback value from 4-5
                            vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                            successfullyAssigned = true;
                        } else {
                            vehicleToAssign.setParked(true);
                            vehicleToAssign.setGarageIndex(randomGarageInt);

                            //feedback for getting random non preferred garage
                            int randomFeedbackInt = ThreadLocalRandom.current().nextInt(2, 4); //random feedback value from 4-5
                            vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                            successfullyAssigned = true;
                        }

                    } //one of these if statements has to work, since we checked if all garages were full at the beginning. Cannot be infinite loop.
                }

                return "" + garages.get(prefGarage).getName() + " is full, assigning vehicle to " + garages.get(randomGarageInt).getName();
            }


        } else {

            //check if football preset is activated, garage assigned is a football garage, and if the vehicle is football authorized:
            if((currentPreset == 3) && (garages.get(prefGarage).getName().equals("49th Street Stadium") || garages.get(prefGarage).getName().equals("43rd & Elkhorn Ave")) && (vehicleToAssign.getFootballAuthorized())) {
                vehicleToAssign.setParked(true);
                vehicleToAssign.setGarageIndex(prefGarage);

                //feedback
                int randomFeedbackInt = ThreadLocalRandom.current().nextInt(4, 6); //random feedback value from 4-5
                vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                return "Vehicle Assigned to Preferred Garage";
            } else if((currentPreset == 3) && (!(garages.get(prefGarage).getName().equals("49th Street Stadium") || garages.get(prefGarage).getName().equals("43rd & Elkhorn Ave")))) {
                vehicleToAssign.setParked(true);
                vehicleToAssign.setGarageIndex(prefGarage);

                //feedback
                int randomFeedbackInt = ThreadLocalRandom.current().nextInt(4, 6); //random feedback value from 4-5
                vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                return "Vehicle Assigned to Preferred Garage";
            } else {
                vehicleToAssign.setParked(true);
                vehicleToAssign.setGarageIndex(prefGarage);

                //feedback
                int randomFeedbackInt = ThreadLocalRandom.current().nextInt(4, 6); //random feedback value from 4-5
                vehicleToAssign.setFeedbackRating(randomFeedbackInt); //set feedback

                return "Vehicle Assigned to Preferred Garage";
            }

        }
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

