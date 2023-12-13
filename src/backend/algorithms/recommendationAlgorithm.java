package backend.algorithms;

import backend.database.recommendation;
import backend.database.trendsGarage;
import frontend.trendsTest;
import java.util.ArrayList;

public class recommendationAlgorithm {

    public static recommendation recommendation(String closestGarage, int timeOfArrival, int numGar) {

        ArrayList<ArrayList<trendsGarage>> garages = new ArrayList<ArrayList<trendsGarage>>(); //create data storage arrayList
        String recommendedGarage = "";

        trendsTest.readAndStoreToGraph(garages, numGar, null); //create dataset arrayLists from stored data

        //check if closest garage is less than 90% full at time of arrival
        int closestGarageNum = 0;
        switch(closestGarage)
        {
            case "43rd & Elkhorn Ave":
                closestGarageNum = 0;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "'43rd & Elkhorn Ave'";
                }
                break;
            case "Constant Center South":
                closestGarageNum = 1;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "'Constant Center South'";
                }
                break;
            case "Constant Center North":
                closestGarageNum = 2;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "'Constant Center North'";
                }
                break;
            case "49th Street Stadium":
                closestGarageNum = 3;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "'49th Street Stadium'";
                }
                break;

        }

        //if closest garage is over 90% full, check if other garages are available

        if (recommendedGarage == "") { //is the recommendation still empty

            //temp var
            int newGarNum = -1;

            for (int i = 0; i < numGar; i++) {
                if ((garages.get(i).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(i).get(timeOfArrival).getCurrent_capacity()) {
                    newGarNum = i;
                }
            }

            //check if any garages are below 90% capacity
            if (newGarNum > -1) {
                switch (newGarNum) {
                    case 0:
                        recommendedGarage = "'43rd & Elkhorn Ave'";
                        break;
                    case 1:
                        recommendedGarage = "'Constant Center South'";
                        break;
                    case 2:
                        recommendedGarage = "'Constant Center North'";
                        break;
                    case 3:
                        recommendedGarage = "'49th Street Stadium'";
                        break;

                }
            }
        }

        //if all garages are over 90% full check if the closest garage is less than 100% full
        if (recommendedGarage == "") { //is the recommendation still empty
            switch(closestGarage)
            {
                case "43rd & Elkhorn Ave":
                    closestGarageNum = 0;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() > garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "'43rd & Elkhorn Ave'";
                    }
                    break;
                case "Constant Center South":
                    closestGarageNum = 1;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "'Constant Center South'";
                    }
                    break;
                case "Constant Center North":
                    closestGarageNum = 2;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "'Constant Center North'";
                    }
                    break;
                case "49th Street Stadium":
                    closestGarageNum = 3;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "'49th Street Stadium'";
                    }
                    break;
                case "garage5":
                    closestGarageNum = 4;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "garage1";
                    }
                    break;
            }
        }

        //Check if any garages are below 100% capacity
        if (recommendedGarage == "") { //is the recommendation still empty

            //temp var
            int newGarNum = -1;

            for (int i = 0; i < numGar; i++) {
                if (garages.get(i).get(timeOfArrival).getTotal_capacity() > garages.get(i).get(timeOfArrival).getCurrent_capacity()) {
                    newGarNum = i;
                }
            }

            //check if any garages are below 90% capacity
            if (newGarNum > -1) {
                switch (newGarNum) {
                    case 0:
                        recommendedGarage = "'43rd & Elkhorn Ave'";
                        break;
                    case 1:
                        recommendedGarage = "'Constant Center South'";
                        break;
                    case 2:
                        recommendedGarage = "'Constant Center North'";
                        break;
                    case 3:
                        recommendedGarage = "'49th Street Stadium'";
                        break;

                }
            }
        }

        int selectedGarageNumID = -1;

        for (int i = 0; i < garages.size(); i++) {
            if (recommendedGarage.equals(garages.get(i).get(0).getGarage_name())) {
                selectedGarageNumID = i;
            }
        }

        //get expected occupancy on arrival
        double arrivalOccupancy = garages.get(selectedGarageNumID).get(timeOfArrival).getCurrent_capacity();
        double arrivalCapacity = garages.get(selectedGarageNumID).get(timeOfArrival).getTotal_capacity();
        double expectedOccupancyPercent = (arrivalOccupancy /arrivalCapacity) * 100.00;
        String expectedOccupancy = (int)expectedOccupancyPercent + "%";

        recommendation newRec = new recommendation(recommendedGarage, expectedOccupancy);

        return newRec;
    }

}




//identify garage of best fit (optimal garage algorithm)
//identify if optimal garage will be available at certain specified time (garage availability algorithm)
//output reccomendation
//user decides if they like it
//repeat if not
