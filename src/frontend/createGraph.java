package frontend;
import backend.database.parkingStructure;
import javax.swing.*;
import java.sql.*;
import java.sql.SQLException;

import javax.management.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;


import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class createGraph extends JFrame {
    public createGraph(String input) {
        trendsTest.main(new String[0]);
        ArrayList<parkingStructure> garages = new ArrayList<parkingStructure>();
        parkingStructure pb = new parkingStructure();
        trendsTest.readAndStoreToGraph(garages);
//        pb.setTime(10);
//        pb.setCurrent_capacity(10);
//        pb.setGarage_id("G1");
//        garages.add(pb);
        boolean checker = garages.contains(input);
        if (checker = true) {
            XYDataset dataset = createDataset(input, garages);

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
    }
    private XYDataset createDataset(String input, ArrayList<parkingStructure> garages) {
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
            createGraph example = new createGraph("G1");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });



    }
}