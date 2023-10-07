package frontend;

import java.sql.*;
import java.sql.SQLException;

import javax.management.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class trendsTest {

    public static void main(String args[]) {
        String connectioString = "jdbc:sqlserver://10.0.200.1;Database=Trends;encrypt=true;trustServerCertificate=true";
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

                    while (rs.next()) {
                        sim_data.add(rs.getString("simulation_number"));
                        time_data.add(rs.getString("time_"));
                        gar_data.add(rs.getString("garage_id"));
                        cap_data.add(rs.getString("capacity"));
                        cur_data.add(rs.getString("current_"));
                        not_data.add(rs.getString("notification"));
                    }

                    for (int i = 0; i < sim_data.size(); i++) {
                        System.out.println("Sim # " + sim_data.get(i) + ", Time: " + time_data.get(i) + ", Garage: " + gar_data.get(i) +
                                ", Capacity: " + cap_data.get(i) + ", Current: " + cur_data.get(i) + ", Notification: " + not_data.get(i) + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the db");
            e.printStackTrace();
        }
    }

}
