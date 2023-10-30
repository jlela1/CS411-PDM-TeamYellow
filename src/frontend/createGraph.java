package frontend;
import backend.database.trendsGarage;
import javax.swing.*;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;


import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class createGraph extends JFrame {
    public createGraph(String input, int numGar) {
        ArrayList<ArrayList<trendsGarage>> garages = new ArrayList<ArrayList<trendsGarage>>();
        trendsTest.readAndStoreToGraph(garages, numGar);

        XYDataset dataset = createDataset(input, garages.get(1)); //initialize as base case

        int inputGarageID = -1; //initialize input garage ID

        String normalizedInputString = "'" + input + "'";

        for (int i = 0; i < garages.size(); i++) {
            if (garages.get(i).get(0).getGarage_name().equals(normalizedInputString)) {
                inputGarageID = garages.get(i).get(0).getGarageID();
            }
        }

        switch (inputGarageID)
        {
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
            createGraph example = new createGraph("Constant Center South", 4);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });



    }
}