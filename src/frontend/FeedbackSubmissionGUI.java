package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FeedbackSubmissionGUI extends JFrame {
    public FeedbackSubmissionGUI() {
        setTitle("Feedback Submission");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set a solid color background
        Color backgroundColor = new Color(173, 216, 230); // Light blue color

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(backgroundColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Feedback banner
        JPanel feedbackBanner = new JPanel();
        feedbackBanner.setOpaque(false);
        JLabel bannerLabel = new JLabel("We value your feedback! Please let us know about your experience.");
        bannerLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        bannerLabel.setForeground(Color.BLACK); // Change the color as needed
        feedbackBanner.add(bannerLabel);

        // Title
        JLabel titleLabel = new JLabel("Feedback Submission");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Main panel for the content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Add the feedback banner to the main panel
        mainPanel.add(feedbackBanner, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 1, 10, 10));
        centerPanel.setOpaque(false);

        // Rating
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ratingPanel.setOpaque(false);
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
        StarRatingPanel starRatingPanel = new StarRatingPanel();
        ratingPanel.add(ratingLabel);
        ratingPanel.add(starRatingPanel);
        centerPanel.add(ratingPanel);

        // Questions and radio buttons (Yes/No)
        String[] questions = {
                "Were you able to find the location you were looking for?",
                "Did you park your car at our recommended location?",
                "Are you happy with your experience today?",
                "Would you recommend this app to others?"
        };

        for (String question : questions) {
            JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel questionLabel = new JLabel(question);
            questionLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
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
        submitButton.setFont(new Font("Monospaced", Font.PLAIN, 20));

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
                int selectedRating = starRatingPanel.getSelectedRating();
                System.out.println("Rating: " + selectedRating);

                // Show feedback submitted message
                JOptionPane.showMessageDialog(FeedbackSubmissionGUI.this, "Feedback submitted");
            }
        });
        submitPanel.add(submitButton);
        centerPanel.add(submitPanel);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setOpaque(false);

        // Add components to the main panel
        mainPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add the background panel to the frame
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
        add(backgroundPanel);

        setVisible(true);
    }

    // Custom panel for star rating
    private class StarRatingPanel extends JPanel {
        private int selectedRating = 3; // Default rating

        private List<JLabel> starLabels; // Store the star labels

        public StarRatingPanel() {
            setOpaque(false);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            starLabels = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                JLabel starLabel = createStarLabel(i);
                add(starLabel);
                starLabels.add(starLabel);
            }
        }

        public int getSelectedRating() {
            return selectedRating;
        }

        private JLabel createStarLabel(final int rating) {
            JLabel starLabel = new JLabel("\u2605"); // Unicode character for a star
            starLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));

            starLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    selectedRating = rating;
                    repaint();
                }

                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    // Change star color to yellow when hovering
                    starLabel.setForeground(Color.YELLOW);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    // Restore star color to green when not hovering
                    int starPosition = starLabels.indexOf(starLabel);
                    if (starPosition >= selectedRating) {
                        starLabel.setForeground(Color.GREEN);
                    } else {
                        starLabel.setForeground(Color.YELLOW);
                    }
                }
            });

            return starLabel;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < starLabels.size(); i++) {
                JLabel star = starLabels.get(i);
                star.setForeground((i < selectedRating) ? Color.YELLOW : Color.GREEN);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackSubmissionGUI());
    }
}
