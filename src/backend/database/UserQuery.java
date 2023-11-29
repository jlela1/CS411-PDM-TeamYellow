package backend.database;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class UserQuery {
    private static final String DB_URL = "jdbc:sqlserver://10.20.30.1;Database=Trends;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "admin";
    private String vehicleId;
    private Timestamp dateAndTime;
    private String userFirstName;
    private String userLastName;
    private String permitType;
    private String parkingCost;
    private String parkingMeter;
    private String userRole;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
    private String found;
    private String parked;
    private String happy;
    private String recommend;
    private int rating;
    private String recommendedGarage;
    private ArrayList<UserQuery> userRecords = new ArrayList<>(); // holds all records for GUI access
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    // Last Name
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    // Permit Type
    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }

    // Parking Cost
    public String getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(String parkingCost) {
        this.parkingCost = parkingCost;
    }

    // Parking Meter
    public String getParkingMeter() {
        return parkingMeter;
    }

    public void setParkingMeter(String parkingMeter) {
        this.parkingMeter = parkingMeter;
    }

    // User Role
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    // Vehicle Make
    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    // Vehicle Model
    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    // Vehicle Year
    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    // Found
    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    // Parked
    public String getParked() {
        return parked;
    }

    public void setParked(String parked) {
        this.parked = parked;
    }

    // Happy
    public String getHappy() {
        return happy;
    }

    public void setHappy(String happy) {
        this.happy = happy;
    }

    // Recommend
    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    // Rating
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Recommended Garage
    public String getRecommendedGarage() {
        return recommendedGarage;
    }

    public void setRecommendedGarage(String recommendedGarage) {
        this.recommendedGarage = recommendedGarage;
    }
    public ArrayList<UserQuery> getUserRecords() { // returns all records
        return userRecords;
    }
    @Override
    public String toString() { // makes output pretty
        return "Vehicle ID: " + vehicleId + "\n" +
                "Date and Time: " + dateAndTime + "\n" +
                "First Name: " + userFirstName + "\n" +
                "Last Name: " + userLastName + "\n" +
                "Permit Type: " + permitType + "\n" +
                "Parking Cost: " + parkingCost + "\n" +
                "Parking Meter: " + parkingMeter + "\n" +
                "User Role: " + userRole + "\n" +
                "Vehicle Make: " + vehicleMake + "\n" +
                "Vehicle Model: " + vehicleModel + "\n" +
                "Vehicle Year: " + vehicleYear + "\n" +
                "Found: " + found + "\n" +
                "Parked: " + parked + "\n" +
                "Happy: " + happy + "\n" +
                "Recommend: " + recommend + "\n" +
                "Rating: " + rating + "\n" +
                "Recommended Garage: " + recommendedGarage + "\n";
    }
    public static List<UserQuery> retrieveWithinDateRangeAndGarage(Date startDate, Date endDate, String garage) {
        // gets records and adds them to arrayList
        List<UserQuery> records = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectSql = "SELECT * FROM UserProfile WHERE DateAndTime BETWEEN ? AND ? AND RecommendedGarage = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                preparedStatement.setDate(1, startDate);
                preparedStatement.setDate(2, endDate);
                preparedStatement.setString(3, garage);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        UserQuery user = new UserQuery();

                        user.setVehicleId(resultSet.getString("vehicleId"));
                        user.setDateAndTime(resultSet.getTimestamp("DateAndTime"));
                        user.setUserFirstName(resultSet.getString("userFirstName"));
                        user.setUserLastName(resultSet.getString("userLastName"));
                        user.setPermitType(resultSet.getString("permitType"));
                        user.setParkingCost(resultSet.getString("parkingCost"));
                        user.setParkingMeter(resultSet.getString("parkingMeter"));
                        user.setUserRole(resultSet.getString("userRole"));
                        user.setVehicleMake(resultSet.getString("vehicleMake"));
                        user.setVehicleModel(resultSet.getString("vehicleModel"));
                        user.setVehicleYear(resultSet.getString("vehicleYear"));
                        user.setFound(resultSet.getString("Found"));
                        user.setParked(resultSet.getString("Parked"));
                        user.setHappy(resultSet.getString("Happy"));
                        user.setRecommend(resultSet.getString("Recommend"));
                        user.setRating(resultSet.getInt("Rating"));
                        user.setRecommendedGarage(resultSet.getString("RecommendedGarage"));

                        records.add(user);
                        System.out.println(user.toString());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
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
        String garage = "43rd & Bluestone";
        //retrieveWithinDateRanges(startDate, endDate);
        String dateString = "2023-11-28 09:01:26.03";
        Timestamp timestamp = Timestamp.valueOf(dateString);
        retrieveWithinDateRangeAndGarage(startDate, endDate, garage);

    }
}
