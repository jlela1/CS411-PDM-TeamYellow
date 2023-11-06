package frontend;
import backend.database.trendsGarage;
import javax.swing.*;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

import java.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;


public class createGraph extends JFrame {
    private XYDataset createEmptyDataset() { // creates empty dataset in case garages is full
        final XYSeries emptySeries = new XYSeries("Empty Dataset");

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(emptySeries);

        return dataset;
    }

    public static LocalDate convertStringToLocalDate(String dateString) {
        // Remove single quotes if present
        dateString = dateString.replaceAll("'", "");

        String defaultYear = "2023"; // You can adjust the default year as needed
        String formattedDateString = defaultYear + "-" + dateString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM.dd.HHmm");

        try {
            LocalDate date = LocalDate.parse(formattedDateString, formatter);
            return date;
        } catch (Exception e) {
            // Handle parsing errors, e.g., invalid date format
            e.printStackTrace();
            return null;
        }
    }

    public static LocalDate isDateBetween(LocalDate date1, LocalDate date2, LocalDate indexDate) {
        if (date1 == null || date2 == null || indexDate == null) {
            return null; // Return null if any of the input values is null
        }

        if (!indexDate.isBefore(date1) && !indexDate.isAfter(date2)) {
            return indexDate;
        }

        //System.out.println("Not within range" + "\n"); line that would output if date was out of range
        return null;
    }
    public createGraph(String input, int numGar, LocalDate date1, LocalDate date2) {
        super("PDM Trends"); // Graph title

        // Clear existing data by initializing a new ArrayList
        ArrayList<ArrayList<trendsGarage>> garages = new ArrayList<>();

        // Populate the garages ArrayList with new data
        trendsTest.readAndStoreToGraph(garages, numGar);

        // Ensure that the garages ArrayList is cleared for cases where isDateBetween returns null
        if (date1 != null && date2 != null) {
            ArrayList<ArrayList<trendsGarage>> clearedGarages = new ArrayList<>();
            for (int i = 0; i < garages.size(); i++) {
                ArrayList<trendsGarage> garageData = garages.get(i); // Get data for a specific garage
                if (!garageData.isEmpty()) {
                    trendsGarage trendsGarage = garageData.get(0); // Assuming the data for the garage is in the first element
                    LocalDate convertedDate = convertStringToLocalDate(trendsGarage.getLong_date());

                    if (isDateBetween(date1, date2, convertedDate) != null) {
                        clearedGarages.add(garageData);
                    }
                }
            }
            garages = clearedGarages;
        }
        XYDataset dataset;
        // Now, the garages list should only contain data that falls within the specified date range.
        if (!garages.isEmpty()){
         dataset = createDataset(input, garages.get(0)); // Initialize as a base case
                                 }
        else{
            // Handle the case when garages is empty (e.g., create an empty dataset or display a message)
            dataset = createEmptyDataset();
        }
        int inputGarageID = -1; // Initialize input garage ID
        String normalizedInputString = "'" + input + "'";

        for (int i = 0; i < garages.size(); i++) {
            ArrayList<trendsGarage> garageData = garages.get(i);
            if (!garageData.isEmpty()) {
                trendsGarage trendsGarage = garageData.get(0); // Assuming the data for the garage is in the first element
                if (trendsGarage.getGarage_name().equals(normalizedInputString)) {
                    inputGarageID = trendsGarage.getGarageID();
                }
            }
        }

        switch (inputGarageID) {
            case 0:
                dataset = createDataset(input, garages.get(0));
                break;
            case 1:
                dataset = createDataset(input, garages.get(1));
                break;
            case 2:
                dataset = createDataset(input, garages.get(2));
                break;
            case 3:
                dataset = createDataset(input, garages.get(3));
                break;
            case 4:
                dataset = createDataset(input, garages.get(4));
                break;
        }

        JFreeChart xyChart = ChartFactory.createXYLineChart(
                "PDM Trends",
                "Time",
                "Used Capc.",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ChartPanel panel = new ChartPanel(xyChart);
        setContentPane(panel);
    }


    private XYDataset createDataset(String input, ArrayList<trendsGarage> garages) {
        final XYSeries singleG = new XYSeries(input);
        for(int i=0; i < garages.size(); i++){
            //TO:DO Rewrite this method to be able to use database data

            singleG.add(garages.get(i).getTime(),garages.get(i).getCurrent_capacity());
//            singleG.add(130,75);
//            singleG.add(200,1);
//            singleG.add(230,69);
        }


        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(singleG);
        return dataset;
    }

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {
            createGraph example = new createGraph("Constant Center South", 4, null, null);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });



    }
}