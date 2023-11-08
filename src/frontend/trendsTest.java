package frontend;
import java.io.FileWriter;
import java.io.IOException;
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
        Vector<Integer> vehicles_per_minute = new Vector<>();
        Vector<Double> averageFeedback = new Vector<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 13) {

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
                    vehicles_per_minute.add(Integer.parseInt(data[11]));
                    averageFeedback.add(Double.parseDouble(data[12]));
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
            System.out.println("vehicles per minute: " + vehicles_per_minute.get(i));
            System.out.println("average feedback" + averageFeedback.get(i));
            System.out.println();
        }
    }

    public static void SQLQuery() {
        String connectioString = "jdbc:sqlserver://10.20.30.1;Database=Trends;encrypt=true;trustServerCertificate=true";
        //IMPORTANT: The IP Address periodically changes. If you get an "error connecting to db" error, let me know and i'll
        //give you the new one
        String user = "sa";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(connectioString, user, password)) {
            System.out.println("Connection Established");

            // SQL query to select all rows from the TrendData table
            String selectQuery = "SELECT * FROM TrendData";

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectQuery);

            // Create a FileWriter to write the results to a text file
            FileWriter fileWriter = new FileWriter("src/query.txt");

            // Process the query results and write them to the file
            while (resultSet.next()) {
                int simulation_number = resultSet.getInt(1);
                int time_ = resultSet.getInt(2);
                String garage_id = resultSet.getString(3);
                int capacity = resultSet.getInt(4);
                int current_ = resultSet.getInt(5);
                int graphGarageID = resultSet.getInt(11);
                String notification = resultSet.getString(6);
                String Clock_time = resultSet.getString(7);
                int month_ = resultSet.getInt(8);
                int day = resultSet.getInt(9);
                String long_date = resultSet.getString(10);
                int vehicles_per_minute = resultSet.getInt(12);
                double newColumn = resultSet.getDouble(13); // Add a new column (adjust data type as needed)

                String line = simulation_number + "," + time_ + ",'" + garage_id + "'," +
                        capacity + "," + current_ + "," + graphGarageID + ",': " + notification + "','" +
                        Clock_time + "'," + month_ + "," + day + ",'" + long_date + "'," + vehicles_per_minute + "," + newColumn;

                fileWriter.write(line + "\n");
            }

            fileWriter.close(); // Close the FileWriter

            System.out.println("SELECT query results written to query.txt.");

        } catch (SQLException | IOException e) {
            System.out.println("Error connecting to the database or writing to the file.");
            e.printStackTrace();
        }
    }

    public static void readAndStoreToGraph(ArrayList<ArrayList<trendsGarage>> p, int numGar) {
        SQLQuery();
        String fileName = "src/query.txt";

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
                if (data.length == 13) {

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
        pb.setVehiclesPerMinute(Integer.parseInt(data[11]));
        pb.setAverageFeedback((Double.parseDouble(data[12])));
        return pb;
    }

}

