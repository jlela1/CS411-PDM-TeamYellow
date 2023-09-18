package backend.database;

import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import backend.database.parkingStructure;

public class Trend {

    public int counter = 0;
    public String[] notifications;
    //public parkingStructure struc[];
    /**
     * set notification message to a string
     * @param s
     */
    public void setString(String s){
        notifications[counter] = s;
        counter++;

    }
    public static    void main(String[] args) {
        // Specify the file path where you want to write the text
        String filePath = "trend.txt";
        
        try {
            // Create a FileWriter to write to the file
            FileWriter fileWriter = new FileWriter(filePath);
            
            // Create a PrintWriter to write text to the file
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            // Write "Hello, World!" to the file
            printWriter.println("Hello, World!");
            
            // Close the PrintWriter and FileWriter
            printWriter.close();
            fileWriter.close();
            
            System.out.println("Hello, World! has been written to " + filePath);
        } catch (IOException e) {
            // Handle any potential IO exceptions
            e.printStackTrace();
        }
    }
    public void WriteToExcel()// throws Exception
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
    }

    public void writeToTxt(){


        
    }

}