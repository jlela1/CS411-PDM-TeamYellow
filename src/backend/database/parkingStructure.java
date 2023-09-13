package backend.database;

public class parkingStructure {
    private String garage_id;
    private int total_capacity;
    private int current_capacity; //cars in lot
    private int parking_availability; //spots available
    private int proximity;

      /**
     * Create new parking structure
     */
    public parkingStructure() {
        this.garage_id = "";
        this.total_capacity = 0;
        this.current_capacity = 0; 
        this.parking_availability = 0;
        this.proximity = 0;
    } /**
     * Create new parking structure with specified inputs
     */
    public parkingStructure(String garage_id, int total_capacity, int current_capacity, int parking_availability, int proximity) {
        this.garage_id = garage_id;
        this.total_capacity = total_capacity;
        this.current_capacity = current_capacity;
        this.parking_availability = parking_availability;
        this.proximity = proximity;
    }


    // Getter methods
    public String getGarage_id() {
        return garage_id;
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
    public void setGarage_id(String garage_id) {
        this.garage_id = garage_id;
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

    @Override
    public String toString() {
        return "ParkingStructure [" +
                "garage_id:'" + garage_id + '\'' +
                ", total_capacity:" + total_capacity +
                ", current_capacity:" + current_capacity +
                ", parking_availability:" + parking_availability +
                ", proximity:" + proximity +
                ']';
    }
}

