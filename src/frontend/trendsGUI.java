package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class trendsGUI {
    private JTextField headerParkingDemandManagementTextField;
    private JPanel trendsPanel;
    private JButton button1;
    private JList garageSelection;



    public trendsGUI() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Place Holder");

            }
        });
    }

    public static void main(String[] args) {
        JFrame trends = new JFrame("trendsGUI");
        trends.setContentPane(new trendsGUI().trendsPanel);
        trends.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        trends.pack();
        trends.setVisible(true);


    }
}
