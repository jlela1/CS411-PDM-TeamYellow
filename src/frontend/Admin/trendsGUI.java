package frontend.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class trendsGUI extends JFrame {
    /* private JTextField headerParkingDemandManagementTextField;
     private JPanel trendsPanel;
     private JButton button1;
     public JList garageSelection;



     public trendsGUI() {
         String gNames[]= {"Garage1","Garage2","Garage3"};
         JList garageSelection = new JList(gNames);

         */
    public trendsGUI() {
        setTitle("PDM Trends Dashboard");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 0, 0, 128));
        headerPanel.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("PDM Trends Dashboard:");

        headerLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);




        JLabel promptText = new JLabel("<html>Welcome to the Parking Demand Management Trends Dashboard<br/>Please select which garage you wish to view</html>");
        promptText.setHorizontalAlignment(JLabel.CENTER);

        add(headerPanel, BorderLayout.CENTER);
        add(promptText,BorderLayout.NORTH);

        JButton garage1 = new JButton("Garage1");
        garage1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"G1 Place Holder");

            }
        });
        headerPanel.add(garage1, BorderLayout.WEST);
        JButton garage2 = new JButton("Garage2");
        garage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"G2 Place Holder");

            }
        });
        headerPanel.add(garage2, BorderLayout.CENTER);
        JButton garage3 = new JButton("Garage3");
        garage3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"G3 Place Holder");

            }
        });
        headerPanel.add(garage3, BorderLayout.EAST);

        JLabel footerPanel = new JLabel("PDM");
        footerPanel.setHorizontalAlignment(JLabel.CENTER);
        add(footerPanel, BorderLayout.SOUTH);







    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                trendsGUI trends = new trendsGUI();

                trends.setVisible(true);
            }
        });
    }

}

