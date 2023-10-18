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
    public createGraph(String input, int numGar) {
        //trendsTest.main(new String[0]);
        ArrayList<ArrayList<parkingStructure>> garages = new ArrayList<ArrayList<parkingStructure>>();
        trendsTest.readAndStoreToGraph(garages, numGar);
//        pb.setTime(10);
//        pb.setCurrent_capacity(10);
//        pb.setGarage_id("G1");
//        garages.add(pb);

        XYDataset dataset = createDataset(input, garages.get(0));;

        switch (input)
        {
            case "garage1":
                dataset = createDataset(input, garages.get(0));
                break;
            case "garage2":
                dataset = createDataset(input, garages.get(1));
                break;
            case "garage3":
                dataset = createDataset(input, garages.get(2));
                break;
            case "garage4":
                dataset = createDataset(input, garages.get(3));
                break;
            case "garage5":
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
    private XYDataset createDataset(String input, ArrayList<parkingStructure> garages) {
        final XYSeries singleG = new XYSeries(input);
       // for(int i=0; i < garages.size(); i++){
            //TO:DO Rewrite this method to be able to use database data

         //   singleG.add(garages.get(i).getTime(),garages.get(i).getCurrent_capacity());
//            singleG.add(130,75);
//            singleG.add(200,1);
//            singleG.add(230,69);
        //}

        singleG.add(130,75);
       singleG.add(200,1);
        singleG.add(230,69);
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(singleG);
        return dataset;
    }

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {
            createGraph example = new createGraph("garage3", 3);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });



    }
}