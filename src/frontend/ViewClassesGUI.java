package frontend;

import backend.database.Schedule;
import backend.database.userProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;

public class ViewClassesGUI extends JFrame {

    public ViewClassesGUI(userProfile userProfile) {

        // Frame setup
        setTitle("View Classes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel();

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel header = PDMPanels.createUserHeader("View Classes");
        //header.setOpaque(false);
        //headerPanel.setPreferredSize(new Dimension(250,60));
        headerPanel.add(header, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createUserFooter();
        footerPanel.setOpaque(false);
        backgroundPanel.add(footerPanel, BorderLayout.SOUTH);

        // Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        //contentPanel.setBorder(BorderFactory.createEmptyBorder(180, 70, 150, 50));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;

        int gbcYCount = -1;

        // Populate the screen with all the elements of savedClasses ArrayList
        for (String savedClass : userProfile.getSavedClasses()) {

            gbcYCount++;
            gbc.gridy = gbcYCount;
            gbc.gridx = 0;
            JLabel classLabel = new JLabel(savedClass);
            classLabel.setFont(new Font("Monospaced",Font.BOLD,24));
            contentPanel.add(classLabel, gbc);

            gbc.gridy = gbcYCount;
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 30, 0, 0);
            JButton classRemoveButton = new JButton("Remove");

            classRemoveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    classRemoveButton.setVisible(false);
                    userProfile.removeSavedClass(savedClass);
                    //calling SQL function
                    userProfile latestUser = backend.database.userProfile.getLatestUserProfile();
                    latestUser.parseClassString(savedClass,latestUser);
                    classLabel.setText("Removed");
                }
            });
            contentPanel.add(classRemoveButton, gbc);

            gbc.insets = new Insets(0, 0, 0, 0);

        }

        gbcYCount++;
        gbc.gridy = gbcYCount;
        gbc.gridx = 0;
        JButton editProfileButton = new JButton("Edit Profile");

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                editProfileGUI editProfileGUI = new editProfileGUI(userProfile);

            }
        });
        contentPanel.add(editProfileButton, gbc);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            userProfile initialProfile = new userProfile("12345", "Carson", "Parker", "Fall Semester",
                    "0", "0", "Commuter", "Toyota", "Camry", "2018", new ArrayList<String>(), new Schedule(), new ArrayList<Integer>());
            ViewClassesGUI viewClassesGUI = new ViewClassesGUI(initialProfile);
            viewClassesGUI.setVisible(true);
        });
    }

}
