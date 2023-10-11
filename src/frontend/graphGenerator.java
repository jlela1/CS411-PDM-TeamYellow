package frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;

import javax.management.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jfree.chart.*; 
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class graphGenerator extends JFrame  {

    JTextField userSelection = new JTextField(20);


    JFreeChart xyChart;
    public graphGenerator()
    {
        ArrayList<String> garageList = new ArrayList<String>();
        garageList.add("G1");
        garageList.add("G2");
        garageList.add("G3");

         String garageSelected =JOptionPane.showInputDialog("Which garage's trends do you wish to view?:");

        //Frame creation
        JFrame graphFrame = new JFrame();
        graphFrame.setSize(1000,1000);
        graphFrame.setLayout(new BorderLayout());
        //Header
        JPanel Pheader= new JPanel();
        Pheader.setBackground(Color.GRAY);
        Pheader.setLayout(new BorderLayout());
        JLabel Lheader = new JLabel("PDM Trends Graph");
        Lheader.setFont(new Font("Roboto", Font.BOLD, 32));
        Lheader.setForeground(Color.WHITE);
        Lheader.setHorizontalAlignment(JLabel.CENTER);
        Pheader.add(Lheader, BorderLayout.NORTH);
        Pheader.setVisible(true);
        add(Pheader,BorderLayout.NORTH);
        //User Selection Textfield
        //JTextField userSelection = new JTextField(20);


        //User Selection ActionListener
        /*userSelection.setVisible(true);
        userSelection.addActionListener(this);
        Pheader.add(userSelection,BorderLayout.SOUTH);*/
        //checking if selected garage exists
        boolean checker =garageList.contains(garageSelected);
        if(checker==true) {
            XYDataset dataset = createDataset(garageSelected);
            xyChart = ChartFactory.createXYLineChart(
                    "PDM Trends",
                    "Time",
                    "Used Capc.",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }
        else {
            JOptionPane.showMessageDialog(null,"Garage Not Found");
        }
        ChartPanel panel = new ChartPanel(xyChart);
        panel.setVisible(true);
        add(panel);

        //packing
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private XYDataset createDataset(String e) {

        //TO:DO Rewrite this method to be able to use database data
        if (e.equals("G1")) {
            final XYSeries singleG = new XYSeries("G1");
            singleG.add(230, 69);
            singleG.add(130, 75);
            singleG.add(200, 1);
            singleG.add(100, 50);



            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(singleG);
            return dataset;
        }

        else if (e.equals("G2") ) {
            final XYSeries singleG = new XYSeries("G2");
            singleG.add(100, 150);
            singleG.add(130, 59);
            singleG.add(200, 112);
            singleG.add(230, 87);


            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(singleG);
            return dataset;
        } else if (e.equals("G3"))

        {
            final XYSeries singleG = new XYSeries("G3");
            singleG.add(100, 250);
            singleG.add(130, 59);
            singleG.add(200, 112);
            singleG.add(230, 87);


            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(singleG);
            return dataset;


        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            graphGenerator graphGenerator = new graphGenerator();

        });




    }


}