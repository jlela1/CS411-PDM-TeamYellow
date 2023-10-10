package frontend;

public class Garage {
    private String name;
    private int maxCapacity;

    private int occupancy;

    public Garage(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.occupancy = 0;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupancy() {return occupancy;}

    public void setOccupancy(int newOccupancy) {occupancy = newOccupancy;}
}

