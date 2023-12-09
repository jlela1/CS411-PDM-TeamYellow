package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BugReportingGUI extends JFrame {
    private JTextField bugDescriptionField;
    private JTextField occurrenceField;
    private JTextField locationField;
    private JTextArea attachmentField;
    private JComboBox<String> bugCategoryComboBox;

    public BugReportingGUI() {
        setTitle("Bug Reporting Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 36.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel header = PDMPanels.createUserHeader("Bug Report");
        //header.setOpaque(false);
        //headerPanel.setPreferredSize(new Dimension(250,60));
        headerPanel.add(header, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createUserFooter();
        footerPanel.setOpaque(false);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        //formPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 2, 10, 2);
        gbc.anchor = GridBagConstraints.CENTER;

        bugDescriptionField = new JTextField();
        occurrenceField = new JTextField();
        locationField = new JTextField();
        attachmentField = new JTextArea();
        bugCategoryComboBox = new JComboBox<>(new String[]{"App functionality", "Payment processing", "Sign up / Login"});

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel categoryLabel = new JLabel("Bug Category (*Required): ");
        categoryLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        categoryLabel.setForeground(Color.white);
        formPanel.add(categoryLabel, gbc);



        gbc.gridx = 1;
        gbc.gridy = 0;
        bugCategoryComboBox = new JComboBox<>(new String[]{"App functionality", "Payment processing", "Sign up / Login"});
        bugCategoryComboBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
        bugCategoryComboBox.setPreferredSize(new Dimension(400, 30));
        formPanel.add(bugCategoryComboBox, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel descriptionLabel = new JLabel("Bug Description (*Required):");
        descriptionLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        descriptionLabel.setForeground(Color.white);
        formPanel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        bugDescriptionField = new JTextField(15);
        bugDescriptionField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        bugDescriptionField.setColumns(30);
        bugDescriptionField.setPreferredSize(new Dimension(bugDescriptionField.getPreferredSize().width, 60));
        formPanel.add(bugDescriptionField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel occurrenceLabel = new JLabel("Date and Time (*Required):");
        occurrenceLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        occurrenceLabel.setForeground(Color.white);
        formPanel.add(occurrenceLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        occurrenceField = new JTextField(15);
        occurrenceField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        occurrenceField.setColumns(30);
        occurrenceField.setPreferredSize(new Dimension(occurrenceField.getPreferredSize().width, 60));
        formPanel.add(occurrenceField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel locationLabel = new JLabel("Location (*Required):");
        locationLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        locationLabel.setForeground(Color.white);
        formPanel.add(locationLabel, gbc);


        gbc.gridx=1;
        gbc.gridy=3;
        locationField = new JTextField(15);
        locationField.setFont(new Font ("Monospaced", Font.PLAIN, 16));
        locationField.setColumns(30);
        locationField.setPreferredSize(new Dimension(locationField.getPreferredSize().width, 60));
        formPanel.add(locationField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel attachmentLabel = new JLabel("Please add any attachments (Optional):");
        attachmentLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        attachmentLabel.setForeground(Color.white);
        formPanel.add(attachmentLabel, gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        attachmentField = new JTextArea();
        attachmentField.setFont(new Font ("Monospaced", Font.PLAIN, 16));
        attachmentField.setColumns(30);
        attachmentField.setRows(5);
        formPanel.add(attachmentField, gbc);


        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton submitButton = new JButton ("Submit a Bug Report");
        submitButton.setBounds(15, 80, 80, 30);
        submitButton.setBackground(Color.CYAN);
        formPanel.add(submitButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;

        JButton homeButton = new JButton ("Home");
        submitButton.setBounds(15, 60, 80, 30);
        submitButton.setBackground(Color.CYAN);
        formPanel.add(homeButton, gbc);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitBugReport();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);
            }
        });


        mainPanel.add(formPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);

    }

    private void submitBugReport() {
        // Perform validation and submission logic here
        String bugDescription = bugDescriptionField.getText();
        String occurrence = occurrenceField.getText();
        String location = locationField.getText();
        String attachment = attachmentField.getText();
        String bugCategory = String.valueOf(bugCategoryComboBox.getSelectedItem());


    JOptionPane.showMessageDialog(this, "Bug report submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

    // Reset form fields
        bugDescriptionField.setText("");
        occurrenceField.setText("");
        locationField.setText("");
        attachmentField.setText("");
        bugCategoryComboBox.setSelectedIndex(0);

}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BugReportingGUI();
        });
    }
}
