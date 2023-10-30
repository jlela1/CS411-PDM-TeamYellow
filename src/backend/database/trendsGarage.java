package backend.database;

public class trendsGarage {
    private String garage_name;

    private int garageID;
    private int total_capacity;
    private int current_capacity; //cars in lot
    private int parking_availability; //spots available
    private int proximity;
    private int time;
    private int simulation_number;
    private String notification;
    private String month;
    private int day;
    private String clock_time;
    private String long_date;

    /**
     * Create new parking structure
     */
    public trendsGarage() {
        this.garage_name = "";
        this.total_capacity = 0;
        this.current_capacity = 0;
        this.parking_availability = 0;
        this.proximity = 0;
        this.notification = "";
        this.simulation_number = 0;
        this.time = 0;
        this.month = "";
        this.day = 0;
        this.clock_time = "";
        this.long_date = "";
    } /**
     * Create new parking structure with specified inputs
     */
    public trendsGarage(String garage_name, int total_capacity, int current_capacity, String notification,
                        int simulation_number, int time, String month, int day, String clock_time, String long_date) {
        this.garage_name = garage_name;
        this.total_capacity = total_capacity;
        this.current_capacity = current_capacity;
        this.notification = notification;
        this.simulation_number = simulation_number;
        this.time = time;
        this.month = month;
        this.day = day;
        this.clock_time = clock_time;
        this.long_date = long_date;
    }


    // Getter methods
    public String getGarage_name() {
        return garage_name;
    }

    public int getTotal_capacity() {
        return total_capacity;
    }

    public int getCurrent_capacity() {
        return current_capacity;
    }

    public int getParking_availability() {
        return parking_availability;
    }

    public int getProximity() {
        return proximity;
    }

    // Setter methods
    public void setGarage_name(String garage_name) {
        this.garage_name = garage_name;
    }

    public void setTotal_capacity(int total_capacity) {
        this.total_capacity = total_capacity;
    }

    public void setCurrent_capacity(int current_capacity) {
        this.current_capacity = current_capacity;
    }

    public void setParking_availability(int parking_availability) {
        this.parking_availability = parking_availability;
    }

    public void setProximity(int proximity) {
        this.proximity = proximity;
    }

    // Getter method for simulation_number
    public int getSimulationNumber() {
        return simulation_number;
    }

    // Setter method for simulation_number
    public void setSimulationNumber(int simulation_number) {
        this.simulation_number = simulation_number;
    }

    // Getter method for notification
    public String getNotification() {
        return notification;
    }

    // Setter method for notification
    public void setNotification(String notification) {
        this.notification = notification;
    }

    // Getter method for time
    public int getTime() {
        return time;
    }

    // Setter method for time
    public void setTime(int time) {
        this.time = time;
    }

    // Getter for month
    public String getMonth() {
        return month;
    }

    // Setter for month
    public void setMonth(String month) {
        this.month = month;
    }

    // Getter for day
    public int getDay() {
        return day;
    }

    // Setter for day
    public void setDay(int day) {
        this.day = day;
    }

    // Getter for Clock_time
    public String getClockTime() {
        return clock_time;
    }

    // Setter for Clock_time
    public void setClock_time(String clockTime) {
        this.clock_time = clockTime;
    }

    // Getter for long_date
    public String getLong_date() {
        return long_date;
    }

    // Setter for long_date
    public void setLong_date(String long_date) {
        this.long_date = long_date;
    }

    public int getGarageID() {return garageID;}

    public void setGarageID(int newGarageID) {garageID = newGarageID;}
    @Override
    public String toString() {
        return "ParkingStructure [" +
                "garage_id:'" + garage_name + '\'' +
                ", total_capacity:" + total_capacity +
                ", current_capacity:" + current_capacity +
                ", parking_availability:" + parking_availability +
                ", proximity:" + proximity +
                ']';
    }
}

