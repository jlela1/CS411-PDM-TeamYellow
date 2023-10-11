package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GarageManager {
    private JFrame mainFrame;
    private JTextField nameField;
    private JTextField capacityField;
    private DefaultListModel<String> garageListModel;
    private JList<String> garageList;
    private ArrayList<Garage> garages;
    private JButton doneButton;

    public GarageManager() {
        garages = new ArrayList<>();

        mainFrame = new JFrame("Garage Manager");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(0, 122, 255));

        JLabel titleLabel = new JLabel("Garage Manager");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        nameField = new JTextField(10);
        JLabel nameLabel = new JLabel("Garage Name:");
        nameField.setFont(new Font("Helvetica", Font.PLAIN, 16));

        nameField.setFont(new Font("Helvetica", Font.PLAIN, 16));
        JLabel capacityLabel = new JLabel("Max Capacity:");
        capacityLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        capacityField = new JTextField();
        capacityField.setFont(new Font("Helvetica", Font.PLAIN, 16));

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(capacityLabel);
        inputPanel.add(capacityField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        JButton viewButton = new JButton("View Garages");
        viewButton.setFont(new Font("Helvetica", Font.PLAIN, 16));

        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        doneButton = new JButton("Done");
        doneButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        buttonPanel.add(doneButton); // Add the Done button

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        garageListModel = new DefaultListModel<>();
        garageList = new JList<>(garageListModel);
        garageList.setFont(new Font("Helvetica", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(garageList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

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
                //spawn new simUserInput with garages
                SimulationUserInputGUI simulation = new SimulationUserInputGUI(garages);
                simulation.setVisible(true);
                System.out.println(garages.size());
            }
        });

        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(inputPanel, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);
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

        JList<String> viewGarageList = new JList<>(garageListModel);
        viewGarageList.setFont(new Font("Helvetica", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(viewGarageList);

        garageListFrame.add(scrollPane, BorderLayout.CENTER);

        garageListFrame.pack();
        garageListFrame.setVisible(true);
        garageListFrame.setLocationRelativeTo(null);
    }
}

