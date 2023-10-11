package frontend;

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
    public createGraph() {



        XYDataset dataset = createDataset();

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

    private XYDataset createDataset() {

        //TO:DO Rewrite this method to be able to use database data
        final XYSeries singleG = new XYSeries("G1");
        singleG.add(100,50);
        singleG.add(130,75);
        singleG.add(200,1);
        singleG.add(230,69);



        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(singleG);
        return dataset;
    }

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(() -> {
            createGraph example = new createGraph();
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });



    }
}