package frontend;

import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import frontend.createGraph;
import frontend.GarageManager;
import java.awt.*;



import java.time.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class trendsGUI extends JFrame implements ActionListener {


    private JButton getGraph, home;
    private String garageName, graphType;
    private JTextField userSelectionGarage;
    private LocalDate date1, date2 ;
    private DatePicker datePickerStart, datePickerEnd;
    private JPanel subPanelGrid, subPanelBox, graphPanel;
    private JFrame tGUI;

    String garageTypeSelector[] = {"Occupancy","Availability","Vehicles Per Minute"};
    static JComboBox garageTypeSelectionComboBox;
    createGraph trendsGraph;




    public trendsGUI()
    {
        tGUI = new JFrame();
        garageName = "default";
        graphType = "default";
        garageTypeSelectionComboBox = new JComboBox(garageTypeSelector);


        //Create the frame title and layout
        tGUI.setTitle("PDM Trends Dashboard");
        tGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tGUI.setLayout(new BorderLayout());
        //Header
        JPanel trendsHeader = PDMPanels.createHeader("PDM Trends Dashboard");
        tGUI.add(trendsHeader, BorderLayout.NORTH);

        //User selection

        //subPanel
        subPanelBox = new JPanel();
        subPanelGrid = new JPanel();

        subPanelBox.setLayout(new BoxLayout(subPanelBox,BoxLayout.Y_AXIS));
        //user selection for garage via text entry
        JLabel userSelectTextPrompt = new JLabel("Please type in which garage you wish you view the trends of: ",JLabel.CENTER);
        subPanelGrid.add(userSelectTextPrompt);


        userSelectionGarage = new JTextField("");
        userSelectionGarage.setColumns(20);

        subPanelGrid.add(userSelectionGarage);
        /* user garage selection via dropdown
        JComboBox<ArrayList> dropdownUserSelect = new JComboBox<>();
        ArrayList<Garage> g = ; note the ArrayList<Garage> that generates the list of garages is declared private with no getter or setter in other classes
        */

        //Date picker
        datePickerStart = new DatePicker();
        JLabel userSelectionDate1 = new JLabel("Please select the beginning date: ");
        subPanelGrid.add(userSelectionDate1);
        subPanelGrid.add(datePickerStart);
        JLabel userSelectionDate2 = new JLabel("Please select the ending date: ");
        subPanelGrid.add(userSelectionDate2);
        datePickerEnd = new DatePicker();
        subPanelGrid.add(datePickerEnd);
        //ComboBox for graphType
        JLabel userGraphTypePrompt = new JLabel("Please select which data you wish to view: ");
        subPanelGrid.add(userGraphTypePrompt);
        subPanelGrid.add(garageTypeSelectionComboBox);
        //Button to create graph
        getGraph = new JButton("Generate Trends Graph");
        getGraph.addActionListener(this);
        subPanelBox.add(subPanelGrid);
        subPanelBox.add(getGraph);










        // calls createGraph and adds it

        trendsGraph = new createGraph(garageName,1);
        graphPanel = new JPanel();

        graphPanel.add(trendsGraph.getContentPane());
        subPanelBox.add(graphPanel);
        //home button
        home = new JButton("Home");
        subPanelBox.add(home);
        //Footer
        JPanel trendsFooter = PDMPanels.createFooter();
        tGUI.add(trendsFooter,BorderLayout.SOUTH);
        tGUI.add(subPanelBox,BorderLayout.CENTER);



        tGUI.setVisible(true);
        tGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==getGraph)
        {
            graphPanel.remove(trendsGraph.getContentPane());
            tGUI.revalidate();
            tGUI.repaint();

            graphType = String.valueOf(garageTypeSelectionComboBox.getSelectedItem());
            String garageNewName = userSelectionGarage.getText();
            trendsGraph = new createGraph(garageNewName,1);
            date1 = datePickerStart.getDate();
            date2 = datePickerEnd.getDate();

            graphPanel.add(trendsGraph.getContentPane());



            tGUI.revalidate();
            tGUI.repaint();








        } else if (e.getSource()== home) //home button listener
        {

        }
    }



    public static void main(String[] args)
    {
        trendsGUI trendsGUI = new trendsGUI();
    }


}


