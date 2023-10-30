package backend.algorithms;

import backend.database.trendsGarage;
import backend.database.vehicle;

import java.util.Random;

public class garageAvailabilityAlgorithm {

    public static void main(String[] args) {
        // availability assessment on a garage/lot
        boolean isAvailable = requestAvailabilityAssessment("Garage/Lot ID");

        if (isAvailable) {
            // Identify the estimated time of arrival
            String estimatedArrivalTime = getEstimatedArrivalTime();

            // Identify predicted occupancy at estimated time of arrival
            int predictedOccupancy = predictOccupancy(estimatedArrivalTime);

            // Output likelihood of parking
            outputLikelihoodOfParking(predictedOccupancy);
        } else {
            outputToUI("Parking not available");
        }
    }

    private static boolean requestAvailabilityAssessment(String garageLotID) {
        // Use the parkingStructure database to check availability
       
        Random random = new Random();
        return random.nextBoolean();
    }

    private static String getEstimatedArrivalTime() {
        // Use the Google Maps API to get the estimated time of arrival
      
        return "15 minutes";
    }

    private static int predictOccupancy(String estimatedArrivalTime) {
        // Use parking trends data from the database to predict occupancy
   
        Random random = new Random();
        return random.nextInt(101);
    }

    private static void outputLikelihoodOfParking(int predictedOccupancy) {
        if (predictedOccupancy < 90) {
            outputToUI("Likelihood of parking: High");
        } else {
            outputToUI("Likelihood of parking: Low");
        }
    }

    private static void outputToUI(String message) {
        System.out.println(message);
    }

    //is garage over 90% full?
    public static boolean garageFull(double capacity, double occupancy) {
        if ((occupancy / capacity) >= 0.9) {
            return true;
        } else {
            return false;
        }
    }


}
