package backend.database;
import backend.database.Schedule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.sql.Timestamp;
import java.sql.ResultSet;


public class userProfile implements Cloneable{
    private String vehicleId;
    private String userFirstName;
    private String userLastName;
    private String permitType;
    private String parkingCost;
    private String parkingMeter;
    private String userRole;
    private Schedule schedule;
    private Timestamp dateAndTime;
    private ArrayList<Integer> dailyStartTimes;
    private String clockStartTime;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
    private ArrayList<String> savedClasses;
    private static final String DB_URL = "jdbc:sqlserver://10.20.30.1;Database=Trends;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "admin";

    // Constructor
    public userProfile(String vehicleId, String userFirstName, String userLastName, String permitType, String parkingCost, String parkingMeter,
                       String userRole, String vehicleMake, String vehicleModel, String vehicleYear, List<String> savedClasses, Schedule schedule, ArrayList<Integer> dailyStartTimes) {
        this.vehicleId = vehicleId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.permitType = permitType;
        this.parkingCost = parkingCost;
        this.parkingMeter = parkingMeter;
        this.userRole = userRole;
        this.clockStartTime = clockStartTime;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.savedClasses = new ArrayList<String>();
        this.dailyStartTimes = new ArrayList<Integer>(7);
        // Set each daily start time to 7AM by default
        for (int i = 0; i < 7; i++) {
            this.dailyStartTimes.add(420);
        }
        this.dateAndTime = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    // Getter and Setter methods for vehicleId
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    // Getter and Setter methods for userFirstName
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    // Getter and Setter methods for userLastName
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    // Getter and Setter methods for permitType
    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }

    // Getter and Setter methods for parkingCost
    public String getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(String parkingCost) {
        this.parkingCost = parkingCost;
    }

    // Getter and Setter methods for parkingMeter
    public String getParkingMeter() {
        return parkingMeter;
    }

    public void setParkingMeter(String parkingMeter) {
        this.parkingMeter = parkingMeter;
    }

    // Getter and Setter methods for userRole
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    // Getter and Setter methods for vehicleMake
    public String getVehicleMake() { return vehicleMake; }
    public void setVehicleMake(String vehicleMake) { this.vehicleMake = vehicleMake; }

    // Getter and Setter methods for vehicleModel
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    // Getter and Setter methods for vehicleYear
    public String getVehicleYear() { return vehicleYear; }
    public void setVehicleYear(String vehicleYear) { this.vehicleYear = vehicleYear; }
    public ArrayList<String> getSavedClasses() { return savedClasses; }
    public void addSavedClass(String classToSave) { savedClasses.add(classToSave); }
    public void removeSavedClass(String classToRemove) {

        Iterator<String> iterator = savedClasses.iterator();

        while (iterator.hasNext()) {
            String currentString = iterator.next();
            if (currentString.equals(classToRemove)) {
                iterator.remove();
            }
        }

    }
    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
    }
    public Schedule getSchedule(){
        return this.schedule;
    }

    // Takes a day index 0-6 (Monday == 0, Tuesday == 1, etc.) and sets its value equal to the time parameter
    public void setDailyStartTime(int dayToSet, int time) { this.dailyStartTimes.set(dayToSet, time); }

    // Takes a day index 0-6 (Monday == 0, Tuesday == 1, etc.) and returns the start time associated with that day.
    public int getDailyStartTime(int dayToGet) {
        return this.dailyStartTimes.get(dayToGet);
    }
    public ArrayList<Integer> getDailyStartTimes() { // returns all daily start times
        return new ArrayList<>(this.dailyStartTimes);
    }

    public String getClockStartTime() {
        return clockStartTime;
    }

    public void setClockStartTime(String clockStartTime) {
        this.clockStartTime = clockStartTime;
    }

    // You can also add any additional methods or functionality here as needed
    public static void insertUserProfile(userProfile user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertSql = "INSERT INTO UserProfile (vehicleId, userFirstName, userLastName, permitType, parkingCost, parkingMeter, userRole, DateAndTime, vehicleMake, vehicleModel, vehicleYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setString(1, user.getVehicleId());
                preparedStatement.setString(2, user.getUserFirstName());
                preparedStatement.setString(3, user.getUserLastName());
                preparedStatement.setString(4, user.getPermitType());
                preparedStatement.setString(5, user.getParkingCost());
                preparedStatement.setString(6, user.getParkingMeter());
                preparedStatement.setString(7, user.getUserRole());
                preparedStatement.setTimestamp(8, user.getDateAndTime());
                preparedStatement.setString(9, user.getVehicleMake());
                preparedStatement.setString(10, user.getVehicleModel());
                preparedStatement.setString(11, user.getVehicleYear());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDefaultDailyStartTimes(String vehicleId) {
        // Check if the vehicleId already exists in the UserProfile table
        if (!vehicleIdExists(vehicleId)) {
            // If not, insert the user profile first
            userProfile user = new userProfile(vehicleId, "John", "Doe", "Regular", "10.00", "Meter123", "Driver", "Toyota", "Camry", "2014",null,null, null);
            insertUserProfile(user);
        }

        // Now insert default start times
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            for (int i = 0; i < 7; i++) {
                String dayOfTheWeek = getDayOfWeekFromIndex(i);
                pushDailyStartTime(connection, vehicleId, 420, dayOfTheWeek);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean vehicleIdExists(String vehicleId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT COUNT(*) FROM UserProfile WHERE vehicleId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, vehicleId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return false in case of any exceptions or if the query fails
        return false;
    }
    public static void insertOrUpdateDailyStartTimes(String vehicleId, ArrayList<Integer> dailyStartTimes, String... daysOfWeek) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            int loopLimit = Math.min(7, dailyStartTimes.size()); // Ensure loop limit doesn't exceed the size of dailyStartTimes
            for (int i = 0; i < loopLimit; i++) {
                String dayOfTheWeek = getDayOfWeekFromIndex(i);

                // Check if the dayOfTheWeek is among the selected days
                if (containsDayOfWeek(daysOfWeek, dayOfTheWeek)) {
                    // Update the existing entry for the selected day
                    updateDailyStartTime(connection, vehicleId, dailyStartTimes.get(i), dayOfTheWeek);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String getDayOfWeekFromIndex(int dayIndex) {
        switch (dayIndex) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
            default:
                return "Monday"; // Default to Monday if an invalid dayIndex is provided
        }
    }

    private static boolean containsDayOfWeek(String[] daysOfWeek, String day) {
        for (String dayOfWeek : daysOfWeek) {
            if (dayOfWeek.equals(day)) {
                return true;
            }
        }
        return false;
    }


    private static void updateDailyStartTime(Connection connection, String vehicleId, int startTime, String dayOfTheWeek) {
        // Check if the record already exists for the specified vehicleId and dayOfTheWeek
        boolean recordExists = recordExists(connection, vehicleId, dayOfTheWeek);

        if (recordExists) {
            // Update the existing entry for the selected day
            String updateSql = "UPDATE DailyStartTimes SET dailyStartTime = ? WHERE vehicleId = ? AND DayOfTheWeek = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
                preparedStatement.setInt(1, startTime);
                preparedStatement.setString(2, vehicleId);
                preparedStatement.setString(3, dayOfTheWeek);
                preparedStatement.executeUpdate();

                System.out.println("Updated DailyStartTime for vehicleId: " + vehicleId + ", day: " + dayOfTheWeek);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Insert a new record for the specified vehicleId and dayOfTheWeek
            pushDailyStartTime(connection, vehicleId, startTime, dayOfTheWeek);
        }
    }

    private static boolean recordExists(Connection connection, String vehicleId, String dayOfTheWeek) {
        String query = "SELECT COUNT(*) FROM DailyStartTimes WHERE vehicleId = ? AND DayOfTheWeek = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vehicleId);
            preparedStatement.setString(2, dayOfTheWeek);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void pushDailyStartTime(Connection connection, String vehicleId, int startTime, String dayOfTheWeek) {
        String insertSql = "INSERT INTO DailyStartTimes (vehicleId, dailyStartTime, DayOfTheWeek) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, vehicleId);
            preparedStatement.setInt(2, startTime);
            preparedStatement.setString(3, dayOfTheWeek);
            preparedStatement.executeUpdate();

            System.out.println("Inserted DailyStartTime for vehicleId: " + vehicleId + ", day: " + dayOfTheWeek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";

    public static String generateRandomVehicleId() { //gives random vehicle id for each profile
        StringBuilder vehicleIdBuilder = new StringBuilder();

        // Add three random letters
        for (int i = 0; i < 3; i++) {
            char randomLetter = getRandomChar(LETTERS);
            vehicleIdBuilder.append(randomLetter);
        }

        // Add three random numbers
        for (int i = 0; i < 3; i++) {
            char randomDigit = getRandomChar(DIGITS);
            vehicleIdBuilder.append(randomDigit);
        }

        return vehicleIdBuilder.toString();
    }

    private static char getRandomChar(String source) { // gets a random char for vehicle id
        Random random = new Random();
        int randomIndex = random.nextInt(source.length());
        return source.charAt(randomIndex);
    }

    public static userProfile getLatestUserProfile() {
        userProfile latestUser = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectSql = "SELECT TOP 1 * FROM UserProfile ORDER BY DateAndTime DESC";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        latestUser = new userProfile(
                                resultSet.getString("vehicleId"),
                                resultSet.getString("userFirstName"),
                                resultSet.getString("userLastName"),
                                resultSet.getString("permitType"),
                                resultSet.getString("parkingCost"),
                                resultSet.getString("parkingMeter"),
                                resultSet.getString("userRole"),

                                resultSet.getString("vehicleMake"),  // schedule - you may need to fetch this from the database too
                                resultSet.getString("vehicleModel"),   // dailyStartTimes - you may need to fetch this from the database too
                                resultSet.getString("vehicleYear"),
                                null,
                                null,
                                null


                        );
                        latestUser.setDateAndTime(resultSet.getTimestamp("DateAndTime"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latestUser;
    }

    public userProfile clone() {
        try {
            return (userProfile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateProfileInDatabase(userProfile user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateSql = "UPDATE UserProfile SET userFirstName=?, userLastName=?, userRole=?, permitType=?, vehicleMake=?, vehicleModel=?, vehicleYear=? WHERE vehicleId=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
                preparedStatement.setString(1, user.getUserFirstName());
                preparedStatement.setString(2, user.getUserLastName());
                preparedStatement.setString(3, user.getUserRole());
                preparedStatement.setString(4, user.getPermitType());
                preparedStatement.setString(5, user.getVehicleMake());
                preparedStatement.setString(6, user.getVehicleModel());
                preparedStatement.setString(7, user.getVehicleYear());
                preparedStatement.setString(8, user.getVehicleId());

                preparedStatement.executeUpdate();
                System.out.println("Profile updated in the database successfully.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateFeedback(String found, String parked, String happy, String recommend, int rating, String vehicleId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateSql = "UPDATE UserProfile SET Found = ?, Parked = ?, Happy = ?, Recommend = ?, Rating = ? WHERE vehicleId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
                preparedStatement.setString(1, found);
                preparedStatement.setString(2, parked);
                preparedStatement.setString(3, happy);
                preparedStatement.setString(4, recommend);
                preparedStatement.setInt(5, rating);
                preparedStatement.setString(6, vehicleId);

                preparedStatement.executeUpdate();
                System.out.println("Feedback updated in the database successfully.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     // parses through data from editProfile for classes
        public static void parseClassString(String classToSave, userProfile user) {
            // Splitting the classToSave string
            String[] parts = classToSave.split(" - |\\s+|:");


            String className = parts[0]; // Extract the class name
            String day = parts[1]; // Extract the day
            String startTime = parts[2] + ":" + parts[3]; // Extract the start time
            //push to DB
            userProfile latestUser = getLatestUserProfile();
            if (user == null){
                insertClassAndTime(latestUser.getVehicleId(), className, day, startTime);
        }
            else {deleteClassAndTime(user.getVehicleId(), className,day,startTime);}
            // Print the parsed values (you can use these values in your SQL query)
//            System.out.println("Class Name: " + className);
//            System.out.println("Day: " + day);
//            System.out.println("Start Time: " + startTime);
        }

    public static void insertClassAndTime(String vehicleId, String className, String day, String startTime) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertSql = "INSERT INTO DailyStartTimes (vehicleId, DayOfTheWeek, Class, startTime) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setString(1, vehicleId);
                preparedStatement.setString(2, day);
                preparedStatement.setString(3, className);
                preparedStatement.setString(4, startTime);

                int rowsInserted = preparedStatement.executeUpdate();
                System.out.println(rowsInserted + " row(s) inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteClassAndTime(String vehicleId, String className, String day, String startTime) { // deletes from DB
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String deleteSql = "DELETE FROM DailyStartTimes WHERE vehicleId = ? AND DayOfTheWeek = ? AND Class = ? AND startTime = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
                preparedStatement.setString(1, vehicleId);
                preparedStatement.setString(2, day);
                preparedStatement.setString(3, className);
                preparedStatement.setString(4, startTime);

                int rowsDeleted = preparedStatement.executeUpdate();
                System.out.println(rowsDeleted + " row(s) deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        userProfile user = new userProfile(generateRandomVehicleId(), "John", "Doe", "Regular", "10.00", "Meter123", "Driver", "Camry", "Toyota","2014",null,null,null);
        insertUserProfile(user);

        // Call insertDefaultDailyStartTimes to insert default values
        insertDefaultDailyStartTimes(user.getVehicleId());

        // Populate the updatedDaysOfWeek array with default days of the week
        String[] updatedDaysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        ArrayList<Integer> dailyStartTimes = new ArrayList<>();
        dailyStartTimes.add(900); // Example: 9:00 AM
        dailyStartTimes.add(1200); // Example: 12:00 PM
        dailyStartTimes.add(1500); // Example: 3:00 PM

        // Call insertOrUpdateDailyStartTimes with updatedDaysOfWeek array
        insertOrUpdateDailyStartTimes(user.getVehicleId(), dailyStartTimes, updatedDaysOfWeek);

        userProfile latestUser = getLatestUserProfile();
        if (latestUser != null) {
            System.out.println("Latest User Profile:");
            System.out.println("Vehicle ID: " + latestUser.getVehicleId());
            System.out.println("Date and Time: " + latestUser.getDateAndTime());
        }
    }
}

