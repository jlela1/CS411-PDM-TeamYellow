package backend.database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Garage {
    private String name;
    private int garageID;
    private int maxCapacity;
    private int occupancy;
    private int numVehiclesEnteringPerMin;
    private int avgParkingDuration;
    private int avgTimeToPark;

    private double averageFeedback;

    private boolean isClosed;

    public List<vehicle> parkingVehicleList;

    public ArrayList<numVehEnteringRate> variableNumVehPerMin;

    public ArrayList<closeGarage> closeGarageList;

    //vars are temporarily public to make integration easier
    public int simNumber;
    public int time;


    public Garage(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.occupancy = 0;
        this.numVehiclesEnteringPerMin = 0;
        this.avgParkingDuration = 0;
        this.avgTimeToPark = 0;
        this.parkingVehicleList = new CopyOnWriteArrayList<>();
        this.variableNumVehPerMin = new ArrayList<numVehEnteringRate>();
        this.isClosed = false;
        this.closeGarageList = new ArrayList<closeGarage>();
        this.averageFeedback = 0.0;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupancy() {return occupancy;}

    public void setOccupancy(int newOccupancy) {occupancy = newOccupancy;}

    public int getNumVehiclesEnteringPerMin() {return numVehiclesEnteringPerMin;}
    public void setNumVehiclesEnteringPerMin(int newVehPerMin) {numVehiclesEnteringPerMin = newVehPerMin;}

    public int getAvgParkingDuration() {return avgParkingDuration;}
    public void setAvgParkingDuration(int newParkDur) {avgParkingDuration = newParkDur;}
    public int getAvgTimeToPark() {return avgTimeToPark;}
    public void setAvgTimeToPark(int newAvgTimeToPark) {avgTimeToPark = newAvgTimeToPark;}

    public boolean getIsClosed() {return isClosed;}

    public void setIsClosed(boolean newIsClosed) {isClosed = newIsClosed;}

    public int getGarageID() {return garageID;}

    public void setGarageID(int newGarageID) {garageID = newGarageID;}

    public double getAverageFeedback() {
        return averageFeedback;
    }
    public void setAverageFeedback(double averageFeedback) {
        this.averageFeedback = averageFeedback;
    }

}