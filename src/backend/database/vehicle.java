package backend.database;

public class vehicle {
    /**this class is for the vehicles and their components
     * 
     */
	private String vehicle_id;
	private int parking_in;
	private int parking_out;
	
	// default constructor
	public vehicle() {
		vehicle_id = "";
		parking_in = 0;
		parking_out = 0;
	}
	
	//constructor
	public vehicle(String theVehicle_id, int theParking_in,int theParking_out) {
		vehicle_id = theVehicle_id;
		parking_in = theParking_in;
		parking_out = theParking_out;
		
	}
	
	// return vehicle_id
	public String getVehicle_id() {
		return vehicle_id;
	}
	
	// return parking_in
		public int getParking_in() {
			return parking_in;
		}
		
	// return vehicle_id
		public int getparking_out() {
			return parking_out;
		}
	//set vehicle_id
	public void setVehicle_id(String theVehicle_id) {
		vehicle_id = theVehicle_id;
	}
	
	public void setParking_in(int theParking_in) {
		parking_in = theParking_in;
	}
	
	public void setparking_out(int theParking_out) {
		parking_out = theParking_out;;
	}
	
	@Override
	public String toString() {
		return "The Vehicle with ID number" + vehicle_id + 
				"enter the parking lot at " + parking_in +
				"and departed at" + parking_out + ".";
	}
	
}


