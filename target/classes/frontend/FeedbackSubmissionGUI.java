package frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FeedbackSubmissionGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Feedback Submission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("YOUR FEEDBACK");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(11, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Rating descriptions
        Map<Integer, String> ratingDescriptions = new HashMap<>();
        ratingDescriptions.put(1, "Poor");
        ratingDescriptions.put(2, "Semi Okay");
        ratingDescriptions.put(3, "Okay");
        ratingDescriptions.put(4, "Pretty Good");
        ratingDescriptions.put(5, "Excellent");

        // Rating
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel ratingLabel = new JLabel("Rating (1-5):");
        JComboBox<Integer> ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        // Description for the selected rating
        JLabel ratingDescriptionLabel = new JLabel("Rating Description: " + ratingDescriptions.get(1));

        ratingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = (int) ratingComboBox.getSelectedItem();
                String ratingDescription = ratingDescriptions.get(rating);
                ratingDescriptionLabel.setText("Rating Description: " + ratingDescription);
            }
        });

        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingComboBox);
        centerPanel.add(ratingPanel);
        centerPanel.add(ratingDescriptionLabel);

        // Questions and input fields
        String[] questions = {
                "Were you able to find the location you were looking for?",
                "Did you park your car at our recommended location?",
                "Are you happy with your experience today?",
                "Would you recommend this app to others"
        };

        Map<String, JTextField> answerFields = new HashMap<>();

        for (String question : questions) {
            JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel questionLabel = new JLabel(question);
            JTextField answerField = new JTextField(40);
            answerFields.put(question, answerField);
            questionPanel.add(questionLabel);
            questionPanel.add(answerField);
            centerPanel.add(questionPanel);
        }

        // Submit button
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Send Feedback");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process and store the feedback data here
                for (String question : questions) {
                    String answer = answerFields.get(question).getText();
                    System.out.println(question + ": " + answer);
                }
                int rating = (int) ratingComboBox.getSelectedItem();
                String ratingDescription = ratingDescriptions.get(rating);
                System.out.println("Rating: " + rating + " (" + ratingDescription + ")");
                JOptionPane.showMessageDialog(frame, "Feedback submitted!");
            }
        });
        submitPanel.add(submitButton);

        centerPanel.add(submitPanel);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
