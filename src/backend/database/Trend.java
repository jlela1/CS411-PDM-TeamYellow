package backend.database;

import java.io.BufferedReader;
import java.io.FileReader;
import backend.database.parkingStructure;
/*import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;*/
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
public class Trend {

    public int counter = 0;
    public int pCounter = 0;
    public int time = 0;
    public String[] notifications = new String[400];
    public parkingStructure[] pStructure = new parkingStructure[400];

    //public parkingStructure struc[];
    /**
     * set notification message to a string
     * @param s
     */
    public void setString(String s){
        notifications[counter] = new String(s);
        counter++;

    }
    public void setParkingSturcture(String i, int tot, int curr, int ti, int nothing ){
        //sets parking structure from main
        pStructure[pCounter] = new parkingStructure(i, tot, curr, ti, nothing);
        time = ti;
        pCounter++;
    }
    public void Write() {
        // Specify the file path where you want to write the text
        String filePath = "trend.txt";
        
        try {
            //create SQL statement
            StringBuilder sql = new StringBuilder("INSERT INTO TrendData ");
            sql.append("(Time, ");
            sql.append(" garage_id, ");
            sql.append(" capacity, ");
            sql.append(" current_)) ");
            sql.append("VALUES("+ time +",");
            sql.append(pStructure[pCounter].getGarage_id()+ ",");
            sql.append(pStructure[pCounter].getCurrent_capacity()+ ",");
            //sql.append(" PRIMARY KEY ( id )))");

            String connectioString =
                    "jdbc:sqlserver://10.0.200.57;encrypt=true; trustServerCertificate=true; database=Trends;";
            //"jdbc:sqlserver://10.0.200.45; instanceName=MSSQL; Database=Trends; encrypt=true; trustServerCertificate=true";
            String user = "sa";
            String password = "admin";
//10.0.200.57 is IP, will try to connect to db
            try {

                try (Connection connection = DriverManager.getConnection(connectioString, user, password)) {
                    System.out.println("Connection Established");
                    System.out.println("Creating table in given database...");
                    Statement stmt = connection.createStatement();

                    stmt.executeUpdate(sql.toString());
                }

            } catch (SQLException e) {
                System.out.println("Error connecting to the db");
                e.printStackTrace();
            }


            // Create a FileWriter to write to the file
            FileWriter fileWriter = new FileWriter(filePath);
            
            // Create a PrintWriter to write text to the file
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int i = 0; i< notifications.length; i++){
                // Write to the file
                if(notifications[i] != null){
            printWriter.println(notifications[i]);
            }
            }
            
            
            // Close the PrintWriter and FileWriter
            printWriter.close();
            fileWriter.close();
            
           // print out each line during execution System.out.println("Trends have been written to " + filePath);
        } catch (IOException e) {
            // Handle any potential IO exceptions
            e.printStackTrace();
        }
    }
   /*  public void WriteToExcel()// throws Exception
    {
        System.out.println("Starting detailed summary report...");
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("Sample sheet");
        
        // Create a new row and add some cells
        Row row = sheet1.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("Hello");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("World!");

        try (FileOutputStream outputStream = new FileOutputStream("detailed_report.xls")) {
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("writeExcelinJava.xlsx written successfully on disk.");
    }*/

    public void readFromTxt(String fileName){

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

