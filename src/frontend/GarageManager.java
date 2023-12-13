package frontend;

import backend.database.Garage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GarageManager {
    private JFrame mainFrame;
    private JTextField nameField;
    private JTextField capacityField;
    private DefaultListModel<String> garageListModel;
    private JList<String> garageList;
    private ArrayList<Garage> garages;
    private JButton doneButton;

    public GarageManager(ArrayList<Garage> garagesList, int presetType) {

        this.garages = garagesList;

        mainFrame = new JFrame("Garage Manager");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1500, 1000));

        mainFrame.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 10.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        mainFrame.setContentPane(backgroundPanel);


        // Create a header panel
        JPanel headerPanel = PDMPanels.createHeader("Garage Manager");


        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridLayout(5, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));

        nameField = new JTextField(15);
        nameField.setBackground(Color.LIGHT_GRAY);
        JLabel nameLabel = new JLabel("Garage Name:");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        nameField.setFont(new Font("Monospaced", Font.PLAIN, 16));

        capacityField = new JTextField(15);
        capacityField.setBackground(Color.LIGHT_GRAY);
        JLabel capacityLabel = new JLabel("Max Capacity:");
        capacityLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        capacityField.setFont(new Font("Monospaced", Font.PLAIN, 16));

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(capacityLabel);
        inputPanel.add(capacityField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 250 ));

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        saveButton.setBackground(Color.YELLOW);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        deleteButton.setBackground(Color.red);

        JButton viewButton = new JButton("View Garages");
        viewButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        viewButton.setBackground(Color.PINK);

        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        doneButton = new JButton("Done");
        doneButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        doneButton.setBackground(Color.green);
        buttonPanel.add(doneButton); // Add the Done button

        JPanel listPanel = new JPanel();
        listPanel.setOpaque(false);
        listPanel.setLayout(new BorderLayout());
        listPanel.setPreferredSize(new Dimension(350, 100));


        garageListModel = new DefaultListModel<>();
        garageList = new JList<>(garageListModel);
        garageList.setFont(new Font("Monospaced", Font.BOLD, 16));
        garageList.setForeground(Color.BLACK);
        garageList.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(garageList);
        scrollPane.setOpaque(false);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        updateGarageList();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String capacityText = capacityField.getText().trim();

                if (name.isEmpty() || capacityText.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Please fill in both Garage Name and Max Capacity.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int capacity = Integer.parseInt(capacityText);
                Garage garage = new Garage(name, capacity);
                garages.add(garage);
                garages.get(garages.size() - 1).setGarageID(garages.size() - 1); //set garageID based on location in garages array to call instead of calling name
                updateGarageList();
                nameField.setText("");
                capacityField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = garageList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    garages.remove(selectedIndex);
                    updateGarageList();
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGarageList();
            }
        });

        // ActionListener for the Done button
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presetType == 2) { //if fire preset, bring up fire rate GUI
                    vehicleRateFireGUI newFireRateGUI = new vehicleRateFireGUI(garages, presetType);
                    mainFrame.dispose();
                } else {
                    vehicleRateGUI newRateGUI = new vehicleRateGUI(garages, presetType);
                    mainFrame.dispose();
                }

            }
        });

        JPanel footerPanel = PDMPanels.createFooter();
        mainFrame.add(footerPanel, BorderLayout.SOUTH);

        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(inputPanel, BorderLayout.WEST);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.add(listPanel, BorderLayout.EAST);

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    private void updateGarageList() {
        garageListModel.clear();
        for (Garage garage : garages) {
            garageListModel.addElement("Name: " + garage.getName() + ", Max Capacity: " + garage.getMaxCapacity());
        }
    }

    private void showGarageList() {
        updateGarageList();

        JFrame garageListFrame = new JFrame("Garage List");
        garageListFrame.setLayout(new BorderLayout());
        garageListFrame.setPreferredSize(new Dimension(500, 400));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 11.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        JPanel headingPanel = PDMPanels.createHeader("Saved Garages for Simulation");

        JList<String> viewGarageList = new JList<>(garageListModel);
        viewGarageList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(viewGarageList);
        scrollPane.setOpaque(false);

        garageListFrame.add(scrollPane, BorderLayout.CENTER);

        garageListFrame.add(headingPanel, BorderLayout.NORTH);
        garageListFrame.pack();
        garageListFrame.setVisible(true);
        garageListFrame.setLocationRelativeTo(null);
    }
    /*
    public static void main(String[] args) {
        // Create an ArrayList to hold Garage objects
        ArrayList<Garage> garages = new ArrayList<>();

        // Create an instance of GarageManager
        // You may pass the ArrayList and any other required parameters to the constructor
        GarageManager garageManager = new GarageManager(garages, 0);
    }
     */
}
