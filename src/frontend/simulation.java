package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simulation extends JFrame {

    private JLabel timeClock;
    private JButton trendsButton;
    private JPanel garageInfo;
    private JLabel garage1;
    private JLabel garage3;
    private JLabel garage2;

    public simulation() {
        trendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void simulation () {
        setTitle("PDM Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}