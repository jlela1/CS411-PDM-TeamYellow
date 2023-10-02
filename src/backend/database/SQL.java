package backend.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

class Sql {
    public static void main(String args[]) {
        String connectioString = "jdbc:sqlserver://10.0.200.9;encrypt=true; trustServerCertificate=true; database=Trends;";
        //"jdbc:sqlserver://10.0.200.45; instanceName=MSSQL; Database=Trends; encrypt=true; trustServerCertificate=true";
        String user = "sa";
        String password = "admin";
        //10.0.200.5

        try (Connection connection = DriverManager.getConnection(connectioString, user, password)) {
            System.out.println("Connection Established");
            try (BufferedReader br = new BufferedReader(new FileReader("src/trend.txt"))) {
                String line;
                //create SQL statement
                /*StringBuilder sql = new StringBuilder("CREATE TABLE Brunelle2 ");
                sql.append("(Data varchar) ");

                System.out.println("Creating table in the given database...");
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sql.toString());
                */

                while ((line = br.readLine()) != null) {
                    StringBuilder sql2 = new StringBuilder("INSERT INTO TrendData ");
                    sql2.append("(simulation_number, ");
                    sql2.append("time_,");
                    sql2.append("garage_id,");
                    sql2.append("capacity,");
                    sql2.append("current_,");
                    sql2.append("notification)");
                    sql2.append("VALUES(");
                    sql2.append(line);
                    int result = line.lastIndexOf(",");
                    //result += 1;
                    System.out.println("result is: " + result + " last character is: " + line.charAt(result) + " and length is: " + line.length());
                    if (line.length() == result + 1) {
                        sql2.append("'none')");
                    } else {
                        sql2.append(")");
                    }

                    System.out.println(sql2.toString());

                    Statement stmt2 = connection.createStatement();
                    stmt2.executeUpdate(sql2.toString());
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
}
