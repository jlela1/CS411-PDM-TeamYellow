package frontend;
import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import backend.database.trendsGarage;
import java.util.ArrayList;

public class trendsTest {

    public static void main(String[] args) {
        readAndStoreDB(); // this will read and store the test DB values into vectors you can use in java
        Scanner myObj =  new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n Wanna query the DB? 0 if no, 1 if yes. \n");
        int answer = myObj.nextInt();  // Read user input
        if(answer == 1)  {SQLQuery();}
        myObj.close();
    }

    public static void readAndStoreDB() {
        String fileName = "src/trendy.txt";
        Vector<Integer> simulationNumbers = new Vector<>();
        Vector<Integer> times = new Vector<>();
        Vector<String> garageNames = new Vector<>();
        Vector<Integer> occupancies = new Vector<>();
        Vector<Integer> capacities = new Vector<>();
        Vector<Integer> garageIDs = new Vector<>();
        Vector<String> notifications = new Vector<>();
        Vector<String> clockTimes = new Vector<>();
        Vector<String> months = new Vector<>();
        Vector<Integer> days = new Vector<>();
        Vector<String> long_dates = new Vector<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 11) {

                    simulationNumbers.add(Integer.parseInt(data[0]));
                    times.add(Integer.parseInt(data[1]));
                    garageNames.add(data[2]);
                    occupancies.add(Integer.parseInt(data[3]));
                    capacities.add(Integer.parseInt(data[4]));
                    garageIDs.add(Integer.parseInt(data[5]));
                    notifications.add(data[6]);
                    clockTimes.add(data[7]);
                    months.add(data[8]);
                    days.add(Integer.parseInt(data[9]));
                    long_dates.add(data[10]);
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }

        // Now, you can use the vectors as needed.
        // For example, you can iterate over them and perform operations.
        for (int i = 0; i < simulationNumbers.size(); i++) {
            System.out.println("Simulation Number: " + simulationNumbers.get(i));
            System.out.println("Time: " + times.get(i));
            System.out.println("Garage ID: " + garageNames.get(i));
            System.out.println("Current Occupancy: " + occupancies.get(i));
            System.out.println("Capacity: " + capacities.get(i));
            System.out.println("Notification: " + notifications.get(i));
            System.out.println("Clock Time: " + clockTimes.get(i));
            System.out.println("Month: " + months.get(i));
            System.out.println("Day: " + days.get(i));
            System.out.println("long_date: " + long_dates.get(i));
            System.out.println();
        }
    }

    public static void SQLQuery() {
        String connectioString = "jdbc:sqlserver://10.20.30.1;Database=Trends;encrypt=true;trustServerCertificate=true";
        //IMPORTANT: The IP Address periodically changes. If you get an "error connecting to db" error, let me know and i'll
        //give you the new one
        String user = "sa";
        String password = "admin";

        try {
            try (Connection connection = DriverManager.getConnection(connectioString, user, password)) {
                System.out.println("Connection Established");
                String sql = "SELECT * FROM TrendData"; // This is the query
                /* Example queries and what they do:
                 * Select* From -> shows entire table
                 * Select* From Where 'column_name' = value or 'string_value' -> this returns a specific subset based on what
                 * you specified. EX: Select* From TrendData Where 'garage_id' = 'G1' -> returns only values for Garage 1
                 *
                 * More help:https://www.w3schools.com/sql/sql_select.asp
                 */

                try (Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                     ResultSet rs = stmt2.executeQuery(sql)) {

                    // Create Vectors to store the data from each column of the table
                    Vector<String> sim_data = new Vector<>();
                    Vector<String> time_data = new Vector<>();
                    Vector<String> gar_data = new Vector<>();
                    Vector<String> cap_data = new Vector<>();
                    Vector<String> cur_data = new Vector<>();
                    Vector<String> not_data = new Vector<>();

                    trendsGarage garages;
                    garages = new trendsGarage();
                    while (rs.next()) {
                        sim_data.add(rs.getString("simulation_number"));
                        time_data.add(rs.getString("time_"));
                        gar_data.add(rs.getString("garage_id"));
                        cap_data.add(rs.getString("capacity"));
                        cur_data.add(rs.getString("current_"));
                        not_data.add(rs.getString("notification"));
                    }

//                    for (int i = 0; i < sim_data.size(); i++) {
//                        System.out.println("Sim # " + sim_data.get(i) + ", Time: " + time_data.get(i) + ", Garage: " + gar_data.get(i) +
//                                ", Capacity: " + cap_data.get(i) + ", Current: " + cur_data.get(i) + ", Notification: " + not_data.get(i) + "\n");
//                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the db");
            e.printStackTrace();
        }
    }

    public static void readAndStoreToGraph(ArrayList<ArrayList<trendsGarage>> p, int numGar) {
        String fileName = "src/trend.txt";

        ArrayList<trendsGarage> garage1 = new ArrayList<>();
        ArrayList<trendsGarage> garage2 = new ArrayList<>();
        ArrayList<trendsGarage> garage3 = new ArrayList<>();
        ArrayList<trendsGarage> garage4 = new ArrayList<>();
        ArrayList<trendsGarage> garage5 = new ArrayList<>();

        switch(numGar) //create arrayLists for each garage and put in master arrayList
        {
            case 1:
                p.add(garage1);
                break;
            case 2:
                p.add(garage1);
                p.add(garage2);
                break;
            case 3:
                p.add(garage1);
                p.add(garage2);
                p.add(garage3);
                break;
            case 4:
                p.add(garage1);
                p.add(garage2);
                p.add(garage3);
                p.add(garage4);
                break;
            case 5:
                p.add(garage1);
                p.add(garage2);
                p.add(garage3);
                p.add(garage4);
                p.add(garage5);
                break;
        }

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int counter1 = 0;
            int counter2 = 0;
            int counter3 = 0;
            int counter4 = 0;
            int counter5 = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 11) {

                    switch(Integer.parseInt(data[5]))
                    {
                        case 0:
                            p.get(0).add(counter1, addNewParkStruct(data));
                            counter1++;
                            break;
                        case 1:
                            p.get(1).add(counter2, addNewParkStruct(data));
                            counter2++;
                            break;
                        case 2:
                            p.get(2).add(counter3, addNewParkStruct(data));
                            counter3++;
                            break;
                        case 3:
                            p.get(3).add(counter4, addNewParkStruct(data));
                            counter4++;
                            break;
                        case 4:
                            p.get(4).add(counter5, addNewParkStruct(data));
                            counter5++;
                            break;
                    }

                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }

    }

    public static trendsGarage addNewParkStruct(String[] data) {
        trendsGarage pb = new trendsGarage();
        pb.setSimulationNumber(Integer.parseInt(data[0]));
        pb.setTime(Integer.parseInt(data[1]));
        pb.setGarage_name(data[2]);
        pb.setCurrent_capacity(Integer.parseInt(data[3]));
        pb.setTotal_capacity(Integer.parseInt(data[4]));
        pb.setGarageID(Integer.parseInt(data[5]));
        pb.setNotification(data[6]);
        pb.setClock_time(data[7]);
        pb.setMonth(data[8]);
        pb.setDay(Integer.parseInt(data[9]));
        pb.setLong_date(data[10]);
        return pb;
    }

}

