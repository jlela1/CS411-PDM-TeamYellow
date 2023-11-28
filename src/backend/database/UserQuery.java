package backend.database;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class UserQuery {
    private static final String DB_URL = "jdbc:sqlserver://10.20.30.1;Database=Trends;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "admin";

    public static void retrieveWithinDateRange(Date startDate, Date endDate) {
        // takes in start and end date for range and returns those specific rows. stored so usable after query
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectSql = "SELECT * FROM UserProfile WHERE DateAndTime BETWEEN ? AND ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                preparedStatement.setDate(1, startDate);
                preparedStatement.setDate(2, endDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String vehicleId = resultSet.getString("vehicleId");
                        Timestamp dateAndTime = resultSet.getTimestamp("DateAndTime");
                        String userFirstName = resultSet.getString("userFirstName");
                        String userLastName = resultSet.getString("userLastName");
                        String permitType = resultSet.getString("permitType");
                        String parkingCost = resultSet.getString("parkingCost");
                        String parkingMeter = resultSet.getString("parkingMeter");
                        String userRole = resultSet.getString("userRole");
                        String vehicleMake = resultSet.getString("vehicleMake");
                        String vehicleModel = resultSet.getString("vehicleModel");
                        String vehicleYear = resultSet.getString("vehicleYear");
                        String found = resultSet.getString("Found");
                        String parked = resultSet.getString("Parked");
                        String happy = resultSet.getString("Happy");
                        String recommend = resultSet.getString("Recommend");
                        int rating = resultSet.getInt("Rating");
                        String recommendedGarage = resultSet.getString("RecommendedGarage");

                        // Output columns to console
                        System.out.println("Vehicle ID: " + vehicleId);
                        System.out.println("Date and Time: " + dateAndTime);
                        System.out.println("First Name: " + userFirstName);
                        System.out.println("Last Name: " + userLastName);
                        System.out.println("Permit Type: " + permitType);
                        System.out.println("Parking Cost: " + parkingCost);
                        System.out.println("Parking Meter: " + parkingMeter);
                        System.out.println("User Role: " + userRole);
                        System.out.println("Vehicle Make: " + vehicleMake);
                        System.out.println("Vehicle Model: " + vehicleModel);
                        System.out.println("Vehicle Year: " + vehicleYear);
                        System.out.println("Found: " + found);
                        System.out.println("Parked: " + parked);
                        System.out.println("Happy: " + happy);
                        System.out.println("Recommend: " + recommend);
                        System.out.println("Rating: " + rating);
                        System.out.println("Recommended Garage: " + recommendedGarage);
                        System.out.println("-------------------"); // Separator for rows
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void retrieveWithinDateRanges(Date startDate, Date endDate) { // outputs everything to console
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectSql = "SELECT * FROM UserProfile WHERE DateAndTime BETWEEN ? AND ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                preparedStatement.setDate(1, startDate);
                preparedStatement.setDate(2, endDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            if (i > 1) {
                                System.out.print(", ");
                            }
                            String columnValue = resultSet.getString(i);
                            System.out.print(metaData.getColumnName(i) + ": " + columnValue);
                        }
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example: Provide start and end dates in the format of YYYY-MM-DD
        Date startDate = Date.valueOf("2023-01-01");
        Date endDate = Date.valueOf("2023-12-31");

        retrieveWithinDateRanges(startDate, endDate);
        retrieveWithinDateRange(startDate, endDate);

    }
}
