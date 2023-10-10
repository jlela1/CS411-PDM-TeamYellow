package backend.database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
public class pushTestDB { //this will populate your local SQL DB with the test values
    public static void main(String[] args) {
        String connectionString = "jdbc:sqlserver://192.168.0.170;encrypt=true;trustServerCertificate=true;database=Trends;";
        /*replace the 192.168.0.170 with your IPv4 address. You can get this by opening a terminal and running "ipconfig"
        scroll down until you see the "Wireless LAN adapter Wi-Fi:". Use the IPv4 address from there. You will also need to
        enable SQL Server Authentication and enable the sa account https://www.fosstechnix.com/how-to-enable-sa-account-in-sql-server/
        make sure the sa password is "admin"
        /*/
        String user = "sa";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
            System.out.println("Connection Established");
            try (BufferedReader br = new BufferedReader(new FileReader("src/testDatabaseMaster.txt"))) {
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
                    sql2.append("day)");
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
}
