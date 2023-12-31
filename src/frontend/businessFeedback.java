package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import backend.database.Garage;
import backend.database.UserQuery;
import backend.database.numVehEnteringRate;

import com.github.lgooddatepicker.components.DatePicker;
import frontend.createGraph;
import frontend.GarageManager;
import java.awt.*;
import java.sql.Date;



import java.time.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class businessFeedback extends JFrame implements  ActionListener{
    UserQuery feedbackDB = new UserQuery();



    private JButton getGraph, home;
    private String garageName, graphType;
    private JComboBox userSelectionGarage;
    private LocalDate date1, date2 ;
    private DatePicker datePickerStart, datePickerEnd;
    private JPanel graphPanel, tablePanel;
    private  JScrollPane scrollTable;
    private JTable feedbackDisplay;
    private String columnNames[] = {"First Name","Last Name","Date and Time", "Rating", "Used the Recommended Location"};

    private int numGar;
    private String[][] feedbackData = {{"","","",""}};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);

    static String garageFeedback = "Average Feedback";
    createGraph feedbackGraph;

    createPieChart feedbackChart;







public businessFeedback(String garageName, int numGarages, ArrayList<Garage> garages)
{
    //Jframe frame setup




    JPanel backgroundPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon backgroundImage = new ImageIcon ("resources/Background 11.png");
            g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
        }
    };
    this.add(backgroundPanel,BorderLayout.CENTER);
    date1 =java.time.LocalDate.now();
    date2 = java.time.LocalDate.now();
    Date startD =Date.valueOf(date1);
    Date endD =Date.valueOf(date2);

    numGar = numGarages;
    graphType = garageFeedback;
    feedbackChart = new createPieChart(startD,endD,garageName);
    this.setTitle("Business Feedback Page");
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setLayout(new BorderLayout());
    this.add(backgroundPanel,BorderLayout.CENTER);
    //Header
    JPanel businessFeedbackHeader = PDMPanels.createHeader("Welcome to View Parker Feedback Page");
    businessFeedbackHeader.setOpaque(true);
    this.add(businessFeedbackHeader,BorderLayout.NORTH);
    //Footer
    JPanel businessFeedbackFooter = PDMPanels.createFooter();
    businessFeedbackFooter.setOpaque(true);
    this.add(businessFeedbackFooter,BorderLayout.SOUTH);
    //gridbag center panel
    JPanel businessFeedBackGComponents = new JPanel(new GridBagLayout());
    businessFeedBackGComponents.setOpaque(false);
    GridBagConstraints gbc = new GridBagConstraints();
    //set the start of the girdbag
    gbc.gridy= 0;
    gbc.gridx =1;
    gbc.insets = new Insets(0,100,0,0);

    //text prompt
    JLabel userSelectionPrompt = new JLabel("Please select which garage.  ");
    userSelectionPrompt.setOpaque(false);
    userSelectionPrompt.setFont(new Font("Monospaced",Font.BOLD,16));
    businessFeedBackGComponents.add(userSelectionPrompt,gbc);
    //combobox for garage selection
    userSelectionGarage = new JComboBox<String>();
    // Add items to the combo box
    for (Garage garage : garages) {
        userSelectionGarage.addItem(garage.getName());

    }
    userSelectionGarage.addItem(("All Garages"));

    gbc.gridy=1;
    gbc.gridx=1;
    userSelectionGarage.setOpaque(false);
    businessFeedBackGComponents.add(userSelectionGarage,gbc);

    //table




     feedbackDisplay= new JTable(tableModel)
     {
         public boolean editCellAt(int row, int column, java.util.EventObject e)
         {
             return false;
         }
     };







    //datepickers


    datePickerStart = new DatePicker();
    datePickerStart.setOpaque(false);
    datePickerEnd = new DatePicker();
    datePickerEnd.setOpaque(false);
    JLabel startDate = new JLabel("Please select the start date. ");
    startDate.setOpaque(false);
    JLabel endDate = new JLabel("Please select the end date. ");
    endDate.setOpaque(false);
    startDate.setFont(new Font("Monospaced",Font.BOLD,16));
    endDate.setFont(new Font("Monospaced",Font.BOLD,16));
    gbc.gridy=0;
    gbc.gridx=2;
    gbc.insets= new Insets(0,10,0,10);
    businessFeedBackGComponents.add(startDate,gbc);
    gbc.gridy = 1;
    gbc.gridx =2;

    businessFeedBackGComponents.add(datePickerStart,gbc);
    gbc.gridy = 0;
    gbc.gridx = 3;
    gbc.insets= new Insets(0,10,0,0);

    businessFeedBackGComponents.add(endDate,gbc);

    gbc.gridy = 1;
    gbc.gridx =3;
    gbc.insets= new Insets(0,0,0,0);

    businessFeedBackGComponents.add(datePickerEnd,gbc);

    //get graph button
    gbc.gridy=2;
    gbc.gridx=2;
    gbc.weightx=0.0;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.BOTH;
    getGraph = new JButton("Click Here to Create the Graph");
    getGraph.setOpaque(false);
    getGraph.setFont(new Font("Monospaced",Font.BOLD,16));
    businessFeedBackGComponents.add(getGraph,gbc);
    //add feedback reports button

    //add graph
    feedbackGraph = new createGraph(garageName, numGar, datePickerStart.getDate(),datePickerEnd.getDate(),graphType, null);

    graphPanel = new JPanel();

    graphPanel.setOpaque(false);


    gbc.gridy = 3;
    gbc.gridx =0;
    gbc.weightx =1.0;
    gbc.weighty =1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridwidth=6;
    businessFeedBackGComponents.add(graphPanel,gbc);

    //home button
    gbc.gridx =2;
    gbc.gridy = 4;
    gbc.weightx =0.0;
    gbc.weighty =0.0;
    gbc.gridwidth=1;

    home = new JButton("Home");
    home.setFont(new Font("Monospaced",Font.BOLD,16));
    businessFeedBackGComponents.add(home,gbc);
    home.setBackground(Color.CYAN);
    home.setOpaque(true);
    getGraph.setBackground(Color.CYAN);
    getGraph.setOpaque(true);

    gbc.gridy = 3;
    gbc.gridx =7;
    gbc.weightx =1.0;
    gbc.weighty =1.0;
    gbc.fill = GridBagConstraints.BOTH;
    tablePanel = new JPanel();

     scrollTable= new JScrollPane(feedbackDisplay);
     tablePanel.add(scrollTable);
     tablePanel.setVisible(false);

    businessFeedBackGComponents.add(tablePanel,gbc);
    businessFeedBackGComponents.setOpaque(false);






   backgroundPanel.add(businessFeedBackGComponents);



    //adding actionListener
    getGraph.addActionListener(this);
    home.addActionListener(this);




    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}










//action listener for buttons

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getGraph) {
            if (datePickerStart.getDate() == null || datePickerEnd.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Please Enter a Valid Date.", "Incorrect Date Format", JOptionPane.ERROR_MESSAGE);
            }
            if (datePickerStart.getDate() != null && datePickerEnd.getDate() != null) {
                if(datePickerStart.getDate().compareTo(datePickerEnd.getDate()) > 0)
                {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Date.", "Incorrect Date Format", JOptionPane.ERROR_MESSAGE);

                } else if (datePickerStart.getDate().compareTo(datePickerEnd.getDate()) <= 0) {


                if (graphPanel != null) {
                    graphPanel.remove(feedbackChart.getContentPane());
                }
                if (tablePanel != null) {
                    tablePanel.remove(scrollTable);
                }
                tablePanel.setVisible(true);
                date1 = datePickerStart.getDate();
                date2 = datePickerEnd.getDate();
                Date startDate = Date.valueOf(date1);
                Date endDate = Date.valueOf(date2);


                this.revalidate();
                this.repaint();

                //JTable


                Object[] rows = new Object[5];
                for (int i = 0; i < feedbackDB.retrieveWithinDateRangeAndGarage(startDate, endDate, userSelectionGarage.getSelectedItem().toString()).size(); i++) {


                    rows[0] = String.valueOf(feedbackDB.retrieveWithinDateRangeAndGarage(startDate, endDate, userSelectionGarage.getSelectedItem().toString()).get(i).getUserFirstName());
                    rows[1] = String.valueOf(feedbackDB.retrieveWithinDateRangeAndGarage(startDate, endDate, userSelectionGarage.getSelectedItem().toString()).get(i).getUserLastName());
                    rows[2] = String.valueOf(feedbackDB.retrieveWithinDateRangeAndGarage(startDate, endDate, userSelectionGarage.getSelectedItem().toString()).get(i).getDateAndTime());
                    rows[3] = String.valueOf(feedbackDB.retrieveWithinDateRangeAndGarage(startDate, endDate, userSelectionGarage.getSelectedItem().toString()).get(i).getRating());
                    rows[4] = String.valueOf(feedbackDB.retrieveWithinDateRangeAndGarage(startDate, endDate, userSelectionGarage.getSelectedItem().toString()).get(i).getRecommend());
                    tableModel.addRow(rows);


                }


                feedbackDisplay = new JTable(tableModel) {
                    public boolean editCellAt(int row, int column, java.util.EventObject e) {
                        return false;
                    }
                };
                scrollTable = new JScrollPane(feedbackDisplay);
                tablePanel.add(scrollTable);


                graphType = garageFeedback;
                String garageNewName = (String) userSelectionGarage.getSelectedItem();

                feedbackChart = new createPieChart(startDate, endDate, userSelectionGarage.getSelectedItem().toString());


                graphPanel.add(feedbackChart.getContentPane());


                this.revalidate();
                this.repaint();


            }
        }

    }
        else if (e.getSource() == home) //home button listener
        {
            dispose();
            AdminHomePage adminHomePage = new AdminHomePage();
            adminHomePage.setVisible(true);
        }
    }








    public static void main(String[] args)
    {
        ArrayList<Garage> garages = new ArrayList<Garage>();

        Garage garage1 = new Garage("43rd & Bluestone",655);
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

        Garage garage4 = new Garage("43rd & Elkhorn Ave", 745);
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




        businessFeedback businessFeedback = new businessFeedback("43rd & Elkhorn Ave", 4, garages);
    }
}
