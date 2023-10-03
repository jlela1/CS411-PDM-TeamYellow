package frontend.Admin;

import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class trendsGUI extends JFrame implements ActionListener  {
    JComboBox timeSelection;
    JLabel headerLabel, footerLabel ;
    JPanel headerPanel,subPanel;

    public trendsGUI() {
        setTitle("PDM Trends Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

         headerPanel = new JPanel();
        subPanel = new JPanel();

        headerPanel.setLayout(new BorderLayout());

        headerLabel = new JLabel("Welcome to the PDM Trends Dashboard:");

        headerLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);


         footerLabel = new JLabel("PDM Trends Dashboard");
        footerLabel.setBackground(new Color(0,0,0,128));
        footerLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        footerLabel.setForeground(Color.BLUE);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        add(headerLabel,BorderLayout.NORTH);
        add(footerLabel,BorderLayout.SOUTH);


        String times[] = {"00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30","04:00","04:30","05:00","05:30",
        "06:00","06:30","07:00","07:30","08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30",
                "13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30"
        ,"20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"}; //48 elements for timeSelection





         timeSelection = new JComboBox<>(times);
        timeSelection.setBounds(50,50,90,20);


        JLabel timeText = new JLabel("Please select the time you wish to view:");
        subPanel.add(timeText);
        timeSelection.addActionListener(this);

        subPanel.add(timeSelection);

        add(subPanel,BorderLayout.CENTER);













    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==timeSelection)
        {
            System.out.println(timeSelection.getSelectedIndex());




        }

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

