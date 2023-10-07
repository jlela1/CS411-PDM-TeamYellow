package frontend;
import backend.database.Trend;
import backend.database.vehicle;
import frontend.SimulationGUI;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GarageSimulation {

    public GarageSimulation(SimulationGUI simulationGUI, int footballStadiumCapacity, int garage1Capacity, int garage2Capacity, int avgVehiclesParking,
                            int avgTimeToPark, int avgTimeParked, int simulationDuration, int timeValue) {

        Thread simulationThread = new Thread(() -> {

            List<vehicle> parkingVehicleList = new CopyOnWriteArrayList<>();
            int time = timeValue;
            int endTime = time + simulationDuration;

            int footballStadiumOccupancy = 0;
            int garage1Occupancy = 0;
            int garage2Occupancy = 0;

            //establishing SQL trend stuff
            Trend stats;
            stats = new Trend();

            while (time < endTime) {

                time += 1;

                // SQL function call
                stats.setGarage(stats, time, garage1Occupancy, garage1Capacity, garage2Occupancy, garage2Capacity, footballStadiumOccupancy, footballStadiumCapacity, convertMinutesToAMPM(time));
                // end of SQL stuff

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
                                }

                            } else if (vehicle.getGarageIndex() == 1) {

                                if (garage1Occupancy < garage1Capacity) {
                                    garage1Occupancy += 1;
                                }

                            } else if (vehicle.getGarageIndex() == 2) {

                                if (garage2Occupancy < garage2Capacity) {
                                    garage2Occupancy += 1;
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
                                }

                            } else if (vehicle.getGarageIndex() == 1) {

                                if (garage1Occupancy > 0) {
                                    garage1Occupancy -= 1;
                                }

                            } else if (vehicle.getGarageIndex() == 2) {

                                if (garage2Occupancy > 0) {
                                    garage2Occupancy -= 1;
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

                simulationGUI.updateSimLabels(footballStadiumCapacity, garage1Capacity, garage2Capacity, footballStadiumOccupancy, garage1Occupancy, garage2Occupancy, time);

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

