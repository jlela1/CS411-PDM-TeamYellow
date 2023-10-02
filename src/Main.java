
import backend.database.parkingStructure;
import backend.database.Trend;
import backend.algorithms.garageAvailabilityAlgorithm;

import java.util.LinkedList;
import test.dataGen;
import backend.database.vehicle;
import frontend.simulationGUI;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Execute sim SIM WILL LAST 6 HOURS

        //Create 2 parking structure objects
        //set size of parking structures
        //start time = 1200
        //set average parking time
        //set vehicles per minute

        //updatable variables: occupancy, prio reccomendation, time, notifications

        //input from user: hardcoded temporarily
        int sizeOfLots = 100;
        int avgParkTime = 30;
        int vehPerMin = 1;
        int prioGarage = 1;
        int secondaryGarage = 2;

        //Create parking structures
        parkingStructure struct1 = new parkingStructure("1", sizeOfLots, 0, 0, 0); //last 3 variables temp irrelevant
        parkingStructure struct2 = new parkingStructure("2", sizeOfLots, 0, 0, 0);

        parkingStructure[] structArray = new parkingStructure[3];
        structArray[1] = struct1;
        structArray[2] = struct2;

        //make 2 queues to remove vehicles from structures
        LinkedList<Integer> struct1out = new LinkedList<Integer>();
        LinkedList<Integer> struct2out = new LinkedList<Integer>();

        String notification = "";
        String notification2 = "";
        int time = 1200;

        // initialize trending object to output stats from garages
        Trend stats;
        stats = new Trend();
        String line = "";
        String line2 = "";
        Random rand = new Random(); // random number to distinguish between runs in SQL
        int upperbound = 5208;
        int random_num = rand.nextInt(upperbound);

        //create sim gui
        new simulationGUI(time, "", "");


        while (time <= 1800) { //iterate every minute until 1800
            //set variables to pass into trend.txt and SQL DB
            if (struct1.getCurrent_capacity()==90) { //if G1 is full
                if(struct2.getCurrent_capacity()==90){
                    line =(random_num +"," + time + ",'G1'," + struct1.getCurrent_capacity() + "," + struct1.getTotal_capacity() + "," + "'Both garages full!'"); // if both are full
                } 
                else if(struct2.getCurrent_capacity() !=90){
                    line =(random_num +"," + time + ",'G1'," + struct1.getCurrent_capacity() + "," + struct1.getTotal_capacity() + "," + "'Sending to Garage 2'"); // G1 Full, G2 Not Full
                }
                            }
            else{
                line =(random_num +"," + time + ",'G1'," + struct1.getCurrent_capacity() + "," + struct1.getTotal_capacity() + "," + "''"); // Neither are full
            }
            if (struct2.getCurrent_capacity()==90) { //if G2 is full
                if(struct1.getCurrent_capacity()==90){//and G1 is full
                    line2 =(random_num +"," + time + ",'G2'," + struct2.getCurrent_capacity() + "," + struct2.getTotal_capacity() + "," + "'Both garages full!'");} // if both are full
                else{
                     line2 =(random_num +"," + time + ",'G2'," + struct2.getCurrent_capacity() + "," + struct2.getTotal_capacity() + "," + "'Sending to Garage 1'"); //G2 Full, G1 Not full
                }    }
                else{
                     line2 =(random_num +"," + time + ",'G2'," + struct2.getCurrent_capacity() + "," + struct2.getTotal_capacity() + "," + "''"); //Neither are full
                }
           

            stats.setString(line);
            stats.setString(line2);
            notification = "";
            notification2 = "";

            simulationGUI.updateTimeGUI(time); //update GUI

            //remove vehicles from garage 1
            if (!(struct1out.isEmpty())) {
                while (struct1out.contains(time)) { //see if a car is leaving during current minute
                    struct1out.removeFirstOccurrence(time); //remove car from out list
                    structArray[prioGarage].setCurrent_capacity(structArray[prioGarage].getCurrent_capacity() - 1); //remove vehicle from structure1
                    simulationGUI.updateStruct1GUI(struct1.getTotal_capacity(), struct1.getCurrent_capacity()); //update GUI
                }
            }

            //remove vehicles from garage 2
            if (!(struct2out.isEmpty())) {
                while (struct2out.contains(time)) { //see if a car is leaving during current minute
                    struct2out.removeFirstOccurrence(time); //remove car from out list
                    structArray[secondaryGarage].setCurrent_capacity(structArray[secondaryGarage].getCurrent_capacity() - 1); //remove vehicle from structure1
                    simulationGUI.updateStruct2GUI(struct2.getTotal_capacity(), struct2.getCurrent_capacity()); //update GUI
                }
            }

            for (int i = 0; i < vehPerMin; i++) { //add vehicles

                //Determine which garage:
                if (!(garageAvailabilityAlgorithm.garageFull(structArray[prioGarage].getTotal_capacity(), structArray[prioGarage].getCurrent_capacity()))) { //priority garage not full

                    //add vehicle to garage
                    structArray[prioGarage].setCurrent_capacity(structArray[prioGarage].getCurrent_capacity() + 1);

                    int randParkDur = (avgParkTime - 10) + (int)(Math.random() * (((avgParkTime + 10) - (avgParkTime - 10)) + 1)); //from stackoverflow random int in range
                    vehicle newVeh = dataGen.genVehicle("vehicle id", time, randParkDur); //create new vehicle object
                    struct1out.add(newVeh.getparking_out());

                    //UPDATE GUI
                    simulationGUI.updateNotificationGUI("Sending to Garage 1");
                    simulationGUI.updateStruct1GUI(struct1.getTotal_capacity(), struct1.getCurrent_capacity());

                    //TEST
                    //System.out.println("Time: " + time + "  G1 occ: " + struct1.getCurrent_capacity() + "/" + struct1.getTotal_capacity() + "  G2 occ: " + struct2.getCurrent_capacity() + "/" + struct2.getTotal_capacity() + "  Notification: " + notification);

                } else if (!(garageAvailabilityAlgorithm.garageFull(structArray[secondaryGarage].getTotal_capacity(), structArray[secondaryGarage].getCurrent_capacity()))) { //secondary garage not full

                    //add vehicle to garage
                    structArray[secondaryGarage].setCurrent_capacity(structArray[secondaryGarage].getCurrent_capacity() + 1);

                    int randParkDur = (avgParkTime - 10) + (int)(Math.random() * (((avgParkTime + 10) - (avgParkTime - 10)) + 1)); //from stackoverflow random int in range
                    vehicle newVeh = dataGen.genVehicle("vehicle id", time, randParkDur); //create new vehicle object
                    struct2out.add(newVeh.getparking_out());

                    //UPDATE GUI
                    simulationGUI.updateStruct2GUI(struct2.getTotal_capacity(), struct2.getCurrent_capacity());
                    simulationGUI.updateNotificationGUI("Sending to Garage 2");
                    notification = "Sending to Garage 2";
                    //TEST
                    //System.out.println("Time: " + time + "  G1 occ: " + struct1.getCurrent_capacity() + "/" + struct1.getTotal_capacity() + "  G2 occ: " + struct2.getCurrent_capacity() + "/" + struct2.getTotal_capacity() + "  Notification: " + notification);
 
                } else {
                    simulationGUI.updateNotificationGUI("Both garages full!");
                    //TEST
                    //System.out.println("Time: " + time + "  G1 occ: " + struct1.getCurrent_capacity() + "/" + struct1.getTotal_capacity() + "  G2 occ: " + struct2.getCurrent_capacity() + "/" + struct2.getTotal_capacity() + "  Notification: " + notification);
                }

            }

            //iterate time
            if (time % 100 == 59) {
                time += 41; //go to next hour
            } else {
                time++;
            }


            Thread.sleep(300);
            stats.Write();
            

        }
        stats.readFromTxt("trend.txt");
        //end simulation
        //System.out.println("END SIM");

        

    }
}
