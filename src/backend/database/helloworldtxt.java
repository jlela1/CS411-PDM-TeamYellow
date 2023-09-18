package backend.database;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class helloworldtxt {
    public static void main(String[] args) {
        // Specify the file path where you want to write the text
        String filePath = "hello.txt";
        
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
}

