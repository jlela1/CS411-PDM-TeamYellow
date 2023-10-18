package frontend;

import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import frontend.createGraph;
import java.awt.*;
import com.github.lgooddatepicker.components.DatePicker;


import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;


public class trendsGUI extends JFrame {







    public trendsGUI()
    {
        //Create the frame title and layout
        setTitle("PDM Trends Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        //Header
        JPanel trendsHeader = PDMPanels.createHeader("PDM Trends Dashboard");
        add(trendsHeader, BorderLayout.NORTH);

        //User selection

        //subFrame
        JPanel subPanel = new JPanel();
        //user selection for garage
        JTextField userSelectionGarage = new JTextField("Which Garage?: ",20);
        subPanel.add(userSelectionGarage);
        //Date picker
        DatePicker datePickerStart = new DatePicker();
        subPanel.add(datePickerStart);
        DatePicker datePickerEnd = new DatePicker();
        subPanel.add(datePickerEnd);









        // calls createGraph and adds it

        createGraph trendsGraph = new createGraph("garage1",1);
        subPanel.add(trendsGraph.getContentPane(),BorderLayout.SOUTH);

        //Footer
        JPanel trendsFooter = PDMPanels.createFooter();
        add(trendsFooter,BorderLayout.SOUTH);
        add(subPanel,BorderLayout.CENTER);



        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    public static void main(String[] args)
    {
        trendsGUI trendsGUI = new trendsGUI();
    }


}


