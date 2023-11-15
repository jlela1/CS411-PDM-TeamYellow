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
    private JPanel graphPanel;

    private int numGar;

    String garageTypeSelector[] = {"Occupancy","Availability","Vehicles Per Minute"};
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
        JPanel trendsHeader = PDMPanels.createHeader("Welcome to the PDM Trends Dashboard");
        trendsHeader.setFont(new Font("Monospaced",Font.BOLD,32));
        this.add(trendsHeader, BorderLayout.NORTH);


        //convert to GridBadLayout()
        JPanel trendsGComponents = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.insets = new Insets(0,0,0,0);




        //user selection for garage via dropdown
        JLabel userSelectTextPrompt = new JLabel("Please select which garage you wish you view the trends of: ",JLabel.CENTER);
        userSelectTextPrompt.setFont(new Font("Monospaced", Font.BOLD, 12));
        //add user select to gridbag

        trendsGComponents.add(userSelectTextPrompt,gbc);


        // Initialize the garageSelectorComboBox
        userSelectionGarage = new JComboBox<String>();



        // Add items to the combo box
        for (Garage garage : garages) {
            userSelectionGarage.addItem(garage.getName());
        }
        // add combobox to gridbag
        gbc.gridy= 0;
        gbc.gridx= 1;

        trendsGComponents.add(userSelectionGarage,gbc);
        //Date picker for start date
        datePickerStart = new DatePicker();
        JLabel userSelectionDate1 = new JLabel("Please select the beginning date: ");
        userSelectionDate1.setFont(new Font("Monospaced", Font.BOLD, 12));
        gbc.gridy= 0;
        gbc.gridx= 2;
        gbc.insets = new Insets(0,0,0,0);
        trendsGComponents.add(userSelectionDate1,gbc);
        gbc.gridy= 0;
        gbc.gridx= 3;
        gbc.insets = new Insets(0,0,0,0);
        trendsGComponents.add(datePickerStart,gbc);
        //Date picker for end date
        JLabel userSelectionDate2 = new JLabel("Please select the ending date: ");
        gbc.gridy= 0;
        gbc.gridx= 4;
        gbc.insets = new Insets(0,0,0,0);
        userSelectionDate2.setFont(new Font("Monospaced", Font.BOLD, 12));
        trendsGComponents.add(userSelectionDate2,gbc);
        datePickerEnd = new DatePicker();
        gbc.gridy= 0;
        gbc.gridx= 5;
        gbc.insets = new Insets(0,0,0,0);
        trendsGComponents.add(datePickerEnd,gbc);
        //ComboBox for graphType
        JLabel userGraphTypePrompt = new JLabel("Please select what kind of data you wish to view: ");
        userGraphTypePrompt.setFont(new Font("Monospaced", Font.BOLD, 12));
        trendsGComponents.add(userGraphTypePrompt);
        trendsGComponents.add(garageTypeSelectionComboBox);
        //Button to create graph
        getGraph = new JButton("Generate Graph");
        getGraph.addActionListener(this);
        getGraph.setFont(new Font("Monospaced", Font.BOLD, 12));

        getGraph.addActionListener(this);
        gbc.gridy= 1;
        gbc.gridx= 0;
        gbc.gridwidth =8;
        gbc.fill = GridBagConstraints.BOTH;
        trendsGComponents.add(getGraph,gbc);



        // calls createGraph and adds it

        trendsGraph = new createGraph(garageName, numGar, datePickerStart.getDate(), datePickerEnd.getDate(),graphType);
        graphPanel = new JPanel();

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.weighty =1.0;
        gbc.weightx=1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth =8;

        trendsGComponents.add(graphPanel,gbc);

        //home button

        home = new JButton("Home");
        home.setPreferredSize(new Dimension(800, 50));

        home.addActionListener(this);
        //add home button to gridbag

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.weighty =0.0;
        gbc.weightx=0.0;

        trendsGComponents.add(home, gbc);

        //Footer
        JPanel trendsFooter = PDMPanels.createFooter();
        this.add(trendsFooter,BorderLayout.SOUTH);
        this.add(trendsGComponents,BorderLayout.CENTER);
        //button color
        home.setBackground(Color.CYAN);
        home.setOpaque(true);
        getGraph.setBackground(Color.CYAN);
        getGraph.setOpaque(true);




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

            trendsGraph = new createGraph(garageNewName, numGar, datePickerStart.getDate(), datePickerEnd.getDate(),graphType);
            date1 = datePickerStart.getDate();
            date2 = datePickerEnd.getDate();
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

        Garage garage5 = new Garage("All Garages", 745);
        garage4.setGarageID(3);
        garage4.setAvgParkingDuration(180);
        garage4.setNumVehiclesEnteringPerMin(1);
        garage4.setAvgTimeToPark(10);
        garages.add(0, garage1);
        garages.add(1, garage2);
        garages.add(2, garage3);
        garages.add(3, garage4);
        garages.add(4,garage5);

        trendsGUI trendsGUI = new trendsGUI("43rd & Elkhorn Ave", 5, garages);
    }


}


