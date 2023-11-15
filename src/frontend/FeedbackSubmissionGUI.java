package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedbackSubmissionGUI extends JFrame{
    public FeedbackSubmissionGUI() {
        setTitle("Feedback Submission");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create a background panel with the image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("resources/color.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Title
        JLabel titleLabel = new JLabel("Feedback Submission");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Main panel for the content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 1, 10, 10));
        centerPanel.setOpaque(false);

        // Rating descriptions
        String[] ratingDescriptions = {
                "Poor", "Semi Okay", "Okay", "Pretty Good", "Excellent"
        };

        // Rating
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.setOpaque(false);
        JLabel ratingLabel = new JLabel("Rating:");
        JComboBox<String> ratingComboBox = new JComboBox<>(ratingDescriptions);
        ratingComboBox.setSelectedIndex(2); // Default selection

        // Description for the selected rating
        JLabel ratingDescriptionLabel = new JLabel("Rating Description: " + ratingDescriptions[2]);

        ratingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = ratingComboBox.getSelectedIndex();
                ratingDescriptionLabel.setText("Rating Description: " + ratingDescriptions[index]);
            }
        });

        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingComboBox);
        centerPanel.add(ratingPanel);
        centerPanel.add(ratingDescriptionLabel);

        // Questions and radio buttons (Yes/No)
        String[] questions = {
                "Were you able to find the location you were looking for?",
                "Did you park your car at our recommended location?",
                "Are you happy with your experience today?",
                "Would you recommend this app to others?"
        };

        for (String question : questions) {
            JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel questionLabel = new JLabel(question);
            JRadioButton yesButton = new JRadioButton("Yes");
            JRadioButton noButton = new JRadioButton("No");
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(yesButton);
            buttonGroup.add(noButton);
            questionPanel.add(questionLabel);
            questionPanel.add(yesButton);
            questionPanel.add(noButton);
            centerPanel.add(questionPanel);
        }

        // Submit button
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitPanel.setOpaque(false);
        JButton submitButton = new JButton("Send Feedback");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process and store the feedback data here
                for (String question : questions) {
                    System.out.print(question + ": ");
                    Component[] components = centerPanel.getComponents();
                    for (Component component : components) {
                        if (component instanceof JPanel) {
                            JPanel questionPanel = (JPanel) component;
                            if (questionPanel.getComponentCount() == 3) {
                                JLabel label = (JLabel) questionPanel.getComponent(0);
                                JRadioButton yesButton = (JRadioButton) questionPanel.getComponent(1);
                                JRadioButton noButton = (JRadioButton) questionPanel.getComponent(2);
                                String answer = (yesButton.isSelected()) ? "Yes" : "No";
                                System.out.println(answer);
                            }
                        }
                    }
                }
                int index = ratingComboBox.getSelectedIndex();
                System.out.println("Rating: " + (index + 1) + " (" + ratingDescriptions[index] + ")");

            }
        });
        submitPanel.add(submitButton);

        centerPanel.add(submitPanel);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setOpaque(false);

        // Add components to the main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the background panel to the frame
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
        add(backgroundPanel);

        setVisible(true);
    }
}
