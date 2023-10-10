package frontend;

public class Garage {
    private String name;
    private int maxCapacity;

    public Garage(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}

