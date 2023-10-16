package backend.algorithms;

import backend.database.parkingStructure;
import frontend.trendsTest;
import java.util.ArrayList;

public class recommendationAlgorithm {

    public static String recommendation(String closestGarage, int timeOfArrival, int numGar) {

        ArrayList<ArrayList<parkingStructure>> garages = new ArrayList<ArrayList<parkingStructure>>(); //create data storage arrayList
        String recommendedGarage = "";

        trendsTest.readAndStoreToGraph(garages, numGar); //create dataset arrayLists from stored data

        //check if closest garage is less than 90% full at time of arrival
        int closestGarageNum = 0;
        switch(closestGarage)
        {
            case "garage1":
                closestGarageNum = 0;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "garage1";
                }
                break;
            case "garage2":
                closestGarageNum = 1;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "garage1";
                }
                break;
            case "garage3":
                closestGarageNum = 2;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "garage1";
                }
                break;
            case "garage4":
                closestGarageNum = 3;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "garage1";
                }
                break;
            case "garage5":
                closestGarageNum = 4;
                if ((garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() * 0.9) >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                    recommendedGarage = "garage1";
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
                        recommendedGarage = "garage1";
                        break;
                    case 1:
                        recommendedGarage = "garage2";
                        break;
                    case 2:
                        recommendedGarage = "garage3";
                        break;
                    case 3:
                        recommendedGarage = "garage4";
                        break;
                    case 4:
                        recommendedGarage = "garage5";
                        break;

                }
            }
        }

        //if all garages are over 90% full check if the closest garage is less than 100% full
        if (recommendedGarage == "") { //is the recommendation still empty
            switch(closestGarage)
            {
                case "garage1":
                    closestGarageNum = 0;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() > garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "garage1";
                    }
                    break;
                case "garage2":
                    closestGarageNum = 1;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "garage1";
                    }
                    break;
                case "garage3":
                    closestGarageNum = 2;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "garage1";
                    }
                    break;
                case "garage4":
                    closestGarageNum = 3;
                    if (garages.get(closestGarageNum).get(timeOfArrival).getTotal_capacity() >= garages.get(closestGarageNum).get(timeOfArrival).getCurrent_capacity()) {
                        recommendedGarage = "garage1";
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
                        recommendedGarage = "garage1";
                        break;
                    case 1:
                        recommendedGarage = "garage2";
                        break;
                    case 2:
                        recommendedGarage = "garage3";
                        break;
                    case 3:
                        recommendedGarage = "garage4";
                        break;
                    case 4:
                        recommendedGarage = "garage5";
                        break;

                }
            }
        }

        return recommendedGarage;
    }

}




//identify garage of best fit (optimal garage algorithm)
//identify if optimal garage will be available at certain specified time (garage availability algorithm)
//output reccomendation
//user decides if they like it
//repeat if not
