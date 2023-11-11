package frontend;

import javax.swing.*;

import backend.database.Garage;
import backend.database.numVehEnteringRate;
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
    private JComboBox userSelectionGarage;
    private LocalDate date1, date2 ;
    private DatePicker datePickerStart, datePickerEnd;
    private JPanel subPanelGrid, subPanelBox, graphPanel;

    private int numGar;

    String garageTypeSelector[] = {"Occupancy","Availability","Vehicles Per Minute","Average Feedback"};
    static JComboBox garageTypeSelectionComboBox;
    createGraph trendsGraph;




    public trendsGUI(String garageName, int numGarages, ArrayList<Garage> garages)
    {
        numGar = numGarages;

        graphType = "default";
        garageTypeSelectionComboBox = new JComboBox(garageTypeSelector);


        //Create the frame title and layout
        this.setTitle("PDM Trends Dashboard");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        //Header
        JPanel trendsHeader = PDMPanels.createHeader("PDM Trends Dashboard");
        this.add(trendsHeader, BorderLayout.NORTH);

        //User selection

        //subPanel
        subPanelBox = new JPanel();
        subPanelGrid = new JPanel();

        subPanelBox.setLayout(new BoxLayout(subPanelBox,BoxLayout.Y_AXIS));
        //user selection for garage via text entry
        JLabel userSelectTextPrompt = new JLabel("Please type in which garage you wish you view the trends of: ",JLabel.CENTER);
        subPanelGrid.add(userSelectTextPrompt);


        // Initialize the garageSelectorComboBox
        userSelectionGarage = new JComboBox<String>();

        //userSelectionGarage.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        // Add items to the combo box
        for (Garage garage : garages) {
            userSelectionGarage.addItem(garage.getName());
        }

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
        graphType = String.valueOf(garageTypeSelectionComboBox.getSelectedItem());
        trendsGraph = new createGraph(garageName, numGar, datePickerStart.getDate(), datePickerEnd.getDate(), graphType);
        graphPanel = new JPanel();

        graphPanel.add(trendsGraph.getContentPane());
        subPanelBox.add(graphPanel);
        //home button
        home = new JButton("Home");
        home.addActionListener(this);
        subPanelBox.add(home);
        //Footer
        JPanel trendsFooter = PDMPanels.createFooter();
        this.add(trendsFooter,BorderLayout.SOUTH);
        this.add(subPanelBox,BorderLayout.CENTER);



        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==getGraph)
        {
            if (graphPanel != null) {
                graphPanel.remove(trendsGraph.getContentPane());
            }

            this.revalidate();
            this.repaint();

            graphType = String.valueOf(garageTypeSelectionComboBox.getSelectedItem());
            String garageNewName = (String) userSelectionGarage.getSelectedItem();

            date1 = datePickerStart.getDate();
            date2 = datePickerEnd.getDate();
            trendsGraph = new createGraph(garageNewName, numGar, date1, date2, graphType);

            //System.out.println(datePickerEnd.getDate() + "\n" + datePickerStart.getDate());
            graphPanel.add(trendsGraph.getContentPane());

            this.revalidate();
            this.repaint();



        } else if (e.getSource()==home) //home button listener
        {
            dispose();
            AdminHomePage adminHomePage = new AdminHomePage();
            adminHomePage.setVisible(true);
        }
    }



    public static void main(String[] args)
    {
        ArrayList<Garage> garages = new ArrayList<Garage>();

        Garage garage1 = new Garage("43rd & Elkhorn Ave", 655);
        garage1.setGarageID(0);
        garage1.setAvgParkingDuration(180);
        garage1.setNumVehiclesEnteringPerMin(1); //starts at 1 car per min at 7am
        garage1.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage1.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 3));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage1.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 5)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage1.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 7)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage1.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 5)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage1.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 3)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage1.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        Garage garage2 = new Garage("Constant Center South", 1535);
        garage2.setGarageID(1);
        garage2.setAvgParkingDuration(180);
        garage2.setNumVehiclesEnteringPerMin(1);
        garage2.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage2.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 3));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage2.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 5)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage2.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 7)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage2.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 5)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage2.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 3)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage2.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        Garage garage3 = new Garage("Constant Center North", 1045);
        garage3.setGarageID(2);
        garage3.setAvgParkingDuration(180);
        garage3.setNumVehiclesEnteringPerMin(1);
        garage3.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage3.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 3));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage3.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 5)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage3.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 7)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage3.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 5)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage3.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 3)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage3.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        Garage garage4 = new Garage("49th Street Stadium", 745);
        garage4.setGarageID(3);
        garage4.setAvgParkingDuration(180);
        garage4.setNumVehiclesEnteringPerMin(1);
        garage4.setAvgTimeToPark(10);
        //set variable vehicles per minute
        garage4.variableNumVehPerMin.add(0, new numVehEnteringRate(480, 3));//change number of vehicles entering per minute at 480 min (8am) to 3
        garage4.variableNumVehPerMin.add(1, new numVehEnteringRate(600, 5)); //change number of vehicles entering per minute at 600 min (10am) to 5
        garage4.variableNumVehPerMin.add(2, new numVehEnteringRate(720, 7)); //change number of vehicles entering per minute at 600 min (12pm) to 8
        garage4.variableNumVehPerMin.add(3, new numVehEnteringRate(840, 5)); //change number of vehicles entering per minute at 840 min (2pm) to 5
        garage4.variableNumVehPerMin.add(4, new numVehEnteringRate(960, 3)); //change number of vehicles entering per minute at 960 min (4pm) to 3
        garage4.variableNumVehPerMin.add(5, new numVehEnteringRate(1080, 1)); //change number of vehicles entering per minute at 1080 min (6pm) to 1

        garages.add(0, garage1);
        garages.add(1, garage2);
        garages.add(2, garage3);
        garages.add(3, garage4);

        trendsGUI trendsGUI = new trendsGUI("43rd & Elkhorn Ave", 4, garages);
    }


}


