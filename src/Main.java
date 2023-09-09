
import frontend.HelloWorldFrontend;
import backend.database.parkingStructure;
import backend.database.testTable;
public class Main {
    public static void main(String[] args) {

        //Execute backend to frontend data transfer/output
        HelloWorldFrontend helloOutput = new HelloWorldFrontend();
        helloOutput.outputHelloWorld();

        //Create test instance of database:
        testTable table = new testTable(123, "John", "Doe", "test", 1, "test", true);
        System.out.println("Table Test: \n" + table.user_firstname + " " + table.user_lastname + " " + table.vehicle_id); // needs work

        parkingStructure parking_lot = new parkingStructure("49th St. Stadium Garage", 600, 450, 450, 0);
        System.out.println(parking_lot.toString());

    }
}
