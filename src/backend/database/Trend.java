package backend.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Trend {
    public int counter = 0;
    public int pCounter = 0;
    public int time = 0;
    public String[] notifications = new String[1000];
    String line = "";
    String line2 = "";
    String line3 = "";
    Random rand = new Random(); // random number to distinguish between runs in SQL
    int upperbound = 5208;
    int random_num = rand.nextInt(upperbound);

    public void setString(String s) {
        notifications[counter] = new String(s);
        counter++;
    }

    public void setGarage(Trend stats, int time, int garage1Occupancy, int garage1Capacity, int garage2Occupancy, int garage2Capacity, int footballStadiumOccupancy, int footballStadiumCapacity, String Clock_time){

        // initialize trending object to output stats from garages for SQL
                stats = new Trend();
                Random rand2 = new Random();
                int upper = 12;
                int rand = rand2.nextInt(upper);
                String[] Months = new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"}; 
                Random dayRand = new Random();
                int upperDay = 28;
                int RandomDay = dayRand.nextInt(upperDay);

                // Start of SQL stuff. Set variables to pass into trend.txt and SQL DB
                if (garage1Occupancy >= 90) { // if G1 is full
                    if (garage2Occupancy >= 90) { //if G2 is full
                        if(footballStadiumOccupancy >= footballStadiumCapacity){
                        line = (random_num + "," + time + ",'G1'," + garage1Occupancy + "," + garage1Capacity + "," + "'All garages full!'" + "," + "'" + Clock_time + "'" + "," + "'"  + Months[rand]+"'"+ "," + RandomDay); // if all are full

                        }
                        line = (random_num + "," + time + ",'G1'," + garage1Occupancy + "," + garage1Capacity + "," + "'Sending to Garage 3!'" + "," + "'" + Clock_time + "'" + "," + "'"  + Months[rand] +"'"+ "," + RandomDay); // if both G1/G2 are full
                    } else if (garage2Occupancy <= 90) {
                        line = (random_num + "," + time + ",'G1'," + garage1Occupancy + "," + garage1Capacity + "," + "'Sending to Garage 2'" + "," + "'" + Clock_time + "'" + "," + "'" + Months[rand] +"'" + "," + RandomDay); // G1 Full, G2 Not Full
                    }
                } else {
                    line = (random_num + "," + time + ",'G1'," + garage1Occupancy + "," + garage1Capacity + "," + "''" + "," + "'" + Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // Neither are full
                }
                if (garage2Occupancy >= garage2Capacity * .90) { // if G2 is full
                    if (garage1Occupancy >= garage1Capacity * .90) { // and G1 is full
                        if (footballStadiumOccupancy >= footballStadiumCapacity *.90) { // if G3 is full
                        line2 = (random_num + "," + time + ",'G2'," + garage2Occupancy + "," + garage2Capacity + "," + "'All garages full!'" + "," + "'" + Clock_time + "'" + "," + "'" +  Months[rand] + "'" + "," + RandomDay); // if all are full
                        }
                        line2 = (random_num + "," + time + ",'G2'," + garage2Occupancy + "," + garage2Capacity + "," + "'Sending to Garage 3!'" + "," + "'" + Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // if both are full send to G3
                    } else {
                        line2 = (random_num + "," + time + ",'G2'," + garage2Occupancy + "," + garage2Capacity + "," + "'Sending to Garage 1'," + "'"+ Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // G2 Full, G1 Not full
                    }
                    
                } else {
                    line2 = (random_num + "," + time + ",'G2'," + garage2Occupancy + "," + garage2Capacity + "," + "''" + "," + "'" + Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // Neither are full
                }
                if (footballStadiumOccupancy >= footballStadiumCapacity *.90) { // if G3 is full
                    if (garage1Occupancy >= garage1Capacity *.90) { // and G1 is full
                        if(garage2Occupancy >= garage2Capacity * .90){
                        line3 = (random_num + "," + time + ",'G3'," + footballStadiumOccupancy + "," + footballStadiumCapacity + "," + "'All garages full!'" + "," + "'" + Clock_time + "'" + "," + "'" +  Months[rand] + "'" + "," + RandomDay); // if both are full
                                                }
                      else if(garage2Occupancy <= garage2Capacity){
                        line3 = (random_num + "," + time + ",'G3'," + footballStadiumOccupancy + "," + footballStadiumCapacity + "," + "'Sending to Garage 2'" + "," + "'" + Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // G3 Full, G1 full, G2 open
                      }
                    } else {
                        line3 = (random_num + "," + time + ",'G3'," + footballStadiumOccupancy + "," + footballStadiumCapacity + "," + "'Sending to Garage 1'" + "," + "'" + Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // G2 Full, G1 Not full
                    }
                } else {
                    line3 = (random_num + "," + time + ",'G3'," + footballStadiumOccupancy + "," + footballStadiumCapacity + "," + "''" + "," + "'"+ Clock_time + "'" + "," + "'" + Months[rand] + "'" + "," + RandomDay); // Neither are full
                }
                stats.setString(line);
                stats.setString(line2);
                stats.setString(line3);

                setString(line);
                setString(line2);
                setString(line3);
                // end of SQL stuff
                //System.out.println(line + line2 + line3);
    }

    public void newGarage(){

        // function for new garage to make it scalable

    }

    public void Write() {
        String filePath = "src/trend.txt"; // create .txt file

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < notifications.length; i++) {
                if (notifications[i] != null) {
                    printWriter.println(notifications[i]);
                }
            }

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String connectionString = "jdbc:sqlserver://10.0.200.13;encrypt=true;trustServerCertificate=true;database=Trends;";
        String user = "sa";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
            System.out.println("Connection Established");
            try (BufferedReader br = new BufferedReader(new FileReader("src/trend.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    StringBuilder sql2 = new StringBuilder("INSERT INTO TrendData ");
                    sql2.append("(simulation_number, ");
                    sql2.append("time_,");
                    sql2.append("garage_id,");
                    sql2.append("capacity,");
                    sql2.append("current_ ,");
                    sql2.append("notification,");
                    sql2.append("Clock_time,");
                    sql2.append("month_)");
                    sql2.append("VALUES(");
                    sql2.append(line);
                    int result = line.lastIndexOf(",");
                    //System.out.println("result is: " + result + " last character is: " + line.charAt(result) + " and length is: " + line.length());
                    if (line.length() == result + 1) {
                        sql2.append("'none')");
                    } else {
                        sql2.append(")");
                    }
                    System.out.println(sql2.toString());

                    Statement stmt2 = connection.createStatement();
                    stmt2.executeUpdate(sql2.toString());
                }
                    System.out.println("Uploaded to DB successfully.");

            }
        } catch (SQLException | IOException e) {
            System.out.println("Error connecting to the db");
            e.printStackTrace();
        }
    }

    public void readFromTxt(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
