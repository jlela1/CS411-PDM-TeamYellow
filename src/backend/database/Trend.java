package backend.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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
    public static String removeCommasFromString(String input) {
        // Replace all commas with an empty string in the input.
        return input.replace(",", "");
    }

    public void setGarage(Trend stats, int time, int garage1Occupancy, int garage1Capacity, String Clock_time, String name, String long_date, String notification){

        // initialize trending object to output stats from garages for SQL
        stats = new Trend();
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
         DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd");

        LocalDateTime now = LocalDateTime.now();
        /*Random rand2 = new Random();
        //int upper = 12;
        //int rand = rand2.nextInt(upper);
        //String[] Months = new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        //Random dayRand = new Random();
        //int upperDay = 28;
        int RandomDay = dayRand.nextInt(upperDay);
        Old random month and day functionality */

        // Start of SQL stuff. Set variables to pass into trend.txt and SQL DB
            line = (random_num + "," + time + ",'" + name + "'" +"," + garage1Occupancy + "," + garage1Capacity + "," + "'" + removeCommasFromString(notification) + "'" + "," + "'" + Clock_time + "'" + "," + "'" + dtf.format(now) +"'" + "," + dtf2.format(now) + "," + "'" + long_date +"'" ); // output line

        stats.setString(line);

        setString(line);
        // end of SQL stuff
        //System.out.println(line);
    }

    public void newGarage(){

        // function for new garage to make it scalable

    }
    public static void appendFile(String sourceFileName, String destinationFileName) {
        try {
            // Open the source file for reading
            BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFileName));

            // Open the destination file for appending
            BufferedWriter destinationWriter = new BufferedWriter(new FileWriter(destinationFileName, true));

            String line;
            while ((line = sourceReader.readLine()) != null) {
                // Append the line to the destination file
                destinationWriter.write(line);
                destinationWriter.newLine(); // Add a newline character
            }

            // Close the readers and writers
            sourceReader.close();
            destinationWriter.close();

            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public void Write() {
        String filePath = "src/trend.txt"; // create .txt file
        String filePath2 = "src/trendy.txt"; // new file generated
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            FileWriter fileWriter1 = new FileWriter(filePath2);
            PrintWriter printWriter1 = new PrintWriter(fileWriter1);
            for (int i = 0; i < notifications.length; i++) {
                if (notifications[i] != null) {
                    //System.out.println("Writing to trend.txt: " + notifications[i]);
                    printWriter.println(notifications[i]);
                    //System.out.println("Writing to trendy.txt: " + notifications[i]);
                    printWriter1.println(notifications[i]);
                }
            }

            printWriter.close();
            fileWriter.close();
            printWriter1.close();
            fileWriter1.close();
            //appendFile(filePath, "src/newFile.txt"); //
            backend.database.sendEmail.main(new String[0]); // send email of new .txt file

        } catch (IOException e) {
            e.printStackTrace();
        }

        String connectionString = "jdbc:sqlserver://10.20.30.1;encrypt=true;trustServerCertificate=true;database=Trends;";
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
                    sql2.append("month_,");
                    sql2.append("day,");
                    sql2.append("long_date)");
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
