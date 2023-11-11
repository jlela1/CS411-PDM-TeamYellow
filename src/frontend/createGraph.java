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
    private XYDataset createEmptyDataset() {
        final XYSeries emptySeries = new XYSeries("Empty Dataset");
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(emptySeries);
        return dataset;
    }

    public static LocalDate convertStringToLocalDate(String dateString) {
        dateString = dateString.replaceAll("'", "");
        String defaultYear = "2023";
        String formattedDateString = defaultYear + "-" + dateString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM.dd.HHmm");

        try {
            return LocalDate.parse(formattedDateString, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LocalDate isDateBetween(LocalDate date1, LocalDate date2, LocalDate indexDate) {
        if (date1 == null || date2 == null || indexDate == null) {
            return null;
        }

        if (!indexDate.isBefore(date1) && !indexDate.isAfter(date2)) {
            System.out.println(indexDate + " is within range");
            return indexDate;
        }

        System.out.println(indexDate + " Not within range" + "\n");
        return null;
    }

    public createGraph(String input, int numGar, LocalDate date1, LocalDate date2, String graphType) {
        super("PDM Trends");

        ArrayList<ArrayList<trendsGarage>> garages = new ArrayList<>();
        trendsTest.readAndStoreToGraph(garages, numGar); // call the function to populate array list

        if (date1 != null && date2 != null) { // function to filter out unneeded data from outside of date range
            ArrayList<ArrayList<trendsGarage>> clearedGarages = new ArrayList<>();

            for (ArrayList<trendsGarage> garageData : garages) {
                ArrayList<trendsGarage> filteredData = new ArrayList<>();

                for (trendsGarage trendsGarage : garageData) {
                    LocalDate convertedDate = convertStringToLocalDate(trendsGarage.getLong_date());

                    if (isDateBetween(date1, date2, convertedDate) != null) {
                        filteredData.add(trendsGarage);
                    }
                }

                if (!filteredData.isEmpty()) {
                    clearedGarages.add(filteredData);
                }
            }
            garages = clearedGarages;
        }

        XYDataset dataset = createEmptyDataset();

        for (int i = 0; i < garages.size(); i++) {
            switch (i) {
                case 0:
                    dataset = createDataset(input, garages.get(0), graphType);
                    break;
                case 1:
                    dataset = createDataset(input, garages.get(1), graphType);
                    break;
                case 2:
                    dataset = createDataset(input, garages.get(2), graphType);
                    break;
                case 3:
                    dataset = createDataset(input, garages.get(3), graphType);
                    break;
                case 4:
                    dataset = createDataset(input, garages.get(4), graphType);
                    break;
            }
        }



    String title;
        switch (graphType) {
            case "Occupancy":
                title = input + " Occupancy Trend";
                break;
            case "Availability":
                title = input + " Availability Trend";
                break;
            case "Vehicles Per Minute":
                title = input + " Vehicles Per Minute Trend";
                break;
            case "Average Feedback":
                title = input + " Average Feedback Trend";
                break;
            default:
                title = input + " Trend";
                break;
        }

        JFreeChart xyChart = ChartFactory.createXYLineChart(
                title,
                "Time",
                "Used Capc.",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ChartPanel panel = new ChartPanel(xyChart);
        setContentPane(panel);
    }

    private XYDataset createDataset(String input, ArrayList<trendsGarage> garage, String graphType) {
        final XYSeries singleG = new XYSeries(input);
        for (trendsGarage trendsGarage : garage) {
            switch (graphType) {
                case "Occupancy": //occupancy graph
                    singleG.add(trendsGarage.getTime(), trendsGarage.getCurrent_capacity());
                    break;
                case "Availability": //availability graph
                    int availability = trendsGarage.getTotal_capacity() - trendsGarage.getCurrent_capacity();
                    singleG.add(trendsGarage.getTime(), availability);
                    break;
                case "Vehicles Per Minute": //cars entering per minute graph
                    singleG.add(trendsGarage.getTime(), trendsGarage.getVehiclesPerMinute());
                    break;
                case "Average Feedback": //feedback graph
                    singleG.add(trendsGarage.getTime(), trendsGarage.getAverageFeedback());
                    break;
            }
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(singleG);
        return dataset;
    }


    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {
            createGraph example = new createGraph("Constant Center South", 4, null, null, "Occupancy");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });



    }
}