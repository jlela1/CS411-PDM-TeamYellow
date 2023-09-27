package backend.database;

import java.sql.*;
class Sql{

    public static void main(String args[]){    
        String connectioString =
        "jdbc:sqlserver://10.0.200.57;encrypt=true; trustServerCertificate=true; database=Trends;";
        //"jdbc:sqlserver://10.0.200.45; instanceName=MSSQL; Database=Trends; encrypt=true; trustServerCertificate=true";
        String user = "sa";
        String password = "admin";
//10.0.200.45
        try {

                try(Connection connection = DriverManager.getConnection(connectioString, user, password))
                {
                        System.out.println("Connection Established");

                        //create SQL statement
            StringBuilder sql = new StringBuilder("CREATE TABLE TrendData ");
            sql.append("(Time INTEGER, ");
            sql.append(" G1_cap VARCHAR(255), ");
            sql.append(" G2_cap VARCHAR(255), ");
            sql.append(" G3_cap VARCHAR(255)) ");
            System.out.println("Creating table in given database...");
                Statement stmt = connection.createStatement();
                        //System.out.println(sql.toString());
                    stmt.executeUpdate(sql.toString());
                }

        }
        catch (SQLException e){
                System.out.println("Error connecting to the db");
                e.printStackTrace();
        }

        }
    }

