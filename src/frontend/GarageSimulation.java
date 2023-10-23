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
    public GarageSimulation(SimulationGUI simulationGUI, ArrayList<Garage> garageList, int avgVehiclesParking,
                            int avgTimeToPark, int avgTimeParked, int simulationDuration, int timeValue) {

        garages = garageList;

        Thread simulationThread = new Thread(() -> {

            List<vehicle> parkingVehicleList = new CopyOnWriteArrayList<>();
            int time = timeValue;
            int endTime = time + simulationDuration;

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

                // Generate a random non-negative number with avgVehiclesParking as the median
                Random random = new Random();
                int offset = random.nextInt(11) - 5;
                int numVehiclesParking = Math.max(avgVehiclesParking + offset, 0);

                for (vehicle vehicle : parkingVehicleList) {

                    if (!vehicle.getParked()) {

                        vehicle.setParking_in(vehicle.getParking_in() - 1);

                        // Time to park!
                        if (vehicle.getParking_in() <= 0) { //if wait to park equal to 0: waiting ended

                            assignGarage(vehicle, garages); //assign vehicle to a garage

                            //update counters
                            //why double check here? if it's full why not check in first statement
                            if ((vehicle.getGarageIndex() == 0) && (garages.get(0).getOccupancy() < garages.get(0).getMaxCapacity())) { //check if assigned to garage 0 and garage 0 not full
                                garages.get(0).setOccupancy(garages.get(0).getOccupancy() + 1); //add one to occupancy

                            } else if ((vehicle.getGarageIndex() == 1) && (garages.get(1).getOccupancy() < garages.get(1).getMaxCapacity())) { //check if assigned to garage 1 and garage 1 not full
                                garages.get(1).setOccupancy(garages.get(1).getOccupancy() + 1); //add one to occupancy

                            } else if ((vehicle.getGarageIndex() == 2) && (garages.get(2).getOccupancy() < garages.get(2).getMaxCapacity())) { //check if assigned to garage 2 and garage 2 not full
                                garages.get(2).setOccupancy(garages.get(2).getOccupancy() + 1); //add one to occupancy

                            } if ((vehicle.getGarageIndex() == 3) && (garages.get(3).getOccupancy() < garages.get(3).getMaxCapacity())) { //check if assigned to garage 3 and garage 3 not full
                                garages.get(3).setOccupancy(garages.get(3).getOccupancy() + 1); //add one to occupancy

                            } if ((vehicle.getGarageIndex() == 4) && (garages.get(4).getOccupancy() < garages.get(4).getMaxCapacity())) { //check if assigned to garage 4 and garage 4 not full
                                garages.get(4).setOccupancy(garages.get(4).getOccupancy() + 1); //add one to occupancy

                            }
                        }

                    } else {

                        int newParkingOutValue = vehicle.getparking_out() - 1;
                        vehicle.setparking_out(newParkingOutValue);

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

                            parkingVehicleList.remove(vehicle);

                        }

                    }

                }

                // Add new vehicles looking for parking
                for (int i = 0; i < numVehiclesParking; i++) {

                    vehicle newVehicle = new vehicle();

                    int timeToParkOffset = random.nextInt(11) - 5;
                    int timeToPark = Math.max(avgTimeToPark + timeToParkOffset, 0);

                    int timeParkedOffset = random.nextInt(11) - 5;
                    int timeParked = Math.max(avgTimeParked + timeParkedOffset, 0);

                    newVehicle.setParking_in(timeToPark);
                    newVehicle.setparking_out(timeParked);

                    parkingVehicleList.add(newVehicle);
                }

                simulationGUI.updateSimLabels(garages, time);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            stats.Write(); // write to file and SQL DB

        });

        simulationThread.start();

    }

    private void assignGarage(vehicle vehicleToAssign, ArrayList<Garage> garages) {

        // Check if all garages are full
        int fullCounter = 0; //initialize amount of garages full
        for (int k = 0; k < garages.size(); k++) {

            if (garages.get(k).getOccupancy() >= garages.get(k).getMaxCapacity()) { //check if each garage is full
                fullCounter++; //if garage full, increment.
            }
        }
        //if all garages full, return without doing anything
        if (fullCounter >= garages.size()) {
            return;
        }


        // Generate a random integer 0-5 to pick one of the 3 garages to park

        int randomGarageInt = 0;
        //generate random int based on how many garages (1-5)
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

        // Check if the chosen garage is 90% or greater capacity
        if ((garages.get(randomGarageInt).getOccupancy() * 0.9) >= garages.get(randomGarageInt).getMaxCapacity()) {

            // Check if all garages are above 90% or greater capacity, continue chosen assignment if so.

            // Check if all garages are above 90% or greater capacity, continue chosen assignment if so.
            int almostFullCounter = 0; //initialize amount of garages 90% full
            for (int k = 0; k < garages.size(); k++) {

                if (garages.get(k).getOccupancy() >= 0.9 * garages.get(k).getMaxCapacity()) { //check if each garage is 90% full
                    almostFullCounter++; //if garage 90% full, increment.
                }
            }

            if (almostFullCounter >= garages.size()) {

                if (garages.get(randomGarageInt).getMaxCapacity() > garages.get(randomGarageInt).getOccupancy()) {

                    vehicleToAssign.setParked(true);
                    vehicleToAssign.setGarageIndex(randomGarageInt);

                } else { // Else, call assignGarage recursively to assign the vehicle to a different garage.

                    assignGarage(vehicleToAssign, garages);
                }
            }

        } else {

            vehicleToAssign.setParked(true);
            vehicleToAssign.setGarageIndex(randomGarageInt);

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

