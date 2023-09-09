
import frontend.HelloWorldFrontend;
import backend.database.testTable;
public class Main {
    public static void main(String[] args) {

        //Execute backend to frontend data transfer/output
        HelloWorldFrontend helloOutput = new HelloWorldFrontend();
        helloOutput.outputHelloWorld();

        //Create test instance of database:
        testTable table = new testTable(123, "John", "Doe", "test", 1, "test", true);
        System.out.println("Table Test: \n" + table.user_firstname + " " + table.user_lastname + " " + table.vehicle_id); // needs work


    }
}
