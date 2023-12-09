package frontend;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class UserAnalysisGUI extends JFrame {
    private JLabel dailyLabel, weeklyLabel, monthlyLabel, totalLabel, recommendedLabel, reviewsLabel;
    private DatePicker datePicker;

    public UserAnalysisGUI() {
        setTitle("User Analysis");
        //setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon ("resources/Background 3.png");
                g.drawImage(backgroundImage.getImage(),0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        //headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel header = PDMPanels.createHeader("PDM User Analysis");
        //header.setOpaque(false);
        //headerPanel.setPreferredSize(new Dimension(250,60));
        headerPanel.add(header, BorderLayout.NORTH);

        JPanel footerPanel = PDMPanels.createUserFooter();
        footerPanel.setOpaque(false);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);


        //getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);

        totalLabel = createLabel("Total Users: 0");
        totalLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        totalLabel.setOpaque(false);
        recommendedLabel = createLabel("Recommended Users: 0");
        recommendedLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        recommendedLabel.setOpaque(false);
        reviewsLabel = createLabel("Reviews: 0");
        reviewsLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        reviewsLabel.setOpaque(false);

        dailyLabel = createLabel("Daily Users: 0");
        dailyLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        dailyLabel.setOpaque(false);
        weeklyLabel = createLabel("Weekly Users: 0");
        weeklyLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        weeklyLabel.setOpaque(false);
        monthlyLabel = createLabel("Monthly Users: 0");
        monthlyLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        monthlyLabel.setOpaque(false);

        panel.add(totalLabel, gbc);
        panel.add(recommendedLabel, gbc);
        panel.add(reviewsLabel, gbc);

        panel.add(createSeparator(), gbc);

        panel.add(createLabel("Please Select a Date:"), gbc);
        datePicker = createDatePicker();
        panel.add(datePicker, gbc);

        panel.add(createSeparator(), gbc);

        panel.add(dailyLabel, gbc);
        panel.add(weeklyLabel, gbc);
        panel.add(monthlyLabel, gbc);

        add(panel);

        updateStatistics();

        setVisible(true);

        JButton homeButton = new JButton("Home");
        homeButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        homeButton.setBackground(new Color(190, 56, 56, 255));
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);

                dispose();
            }
        });

        panel.add(homeButton, gbc);
        mainPanel.add(panel, BorderLayout.CENTER);

    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        label.setBackground(Color.LIGHT_GRAY);
        //  label.setForeground(new Color(50, 50, 50));
        label.setOpaque(true);
        //  label.setBackground(new Color(220, 220, 220));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return label;
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        //  separator.setForeground(new Color(150, 150, 150));
        return separator;
    }

    private DatePicker createDatePicker() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
        DatePicker datePicker = new DatePicker(dateSettings);
        datePicker.setDateToToday();

        datePicker.addDateChangeListener(event -> updateStatistics());

        return datePicker;
    }

    private Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("resources/Background 11.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);
        backgroundPanel.setLayout(new BorderLayout());

        // Create a PDM footer
        JPanel footer = PDMPanels.createFooter();
        footer.setOpaque(false);
        backgroundPanel.add(footer, BorderLayout.SOUTH);

        contentPane.add(backgroundPanel, BorderLayout.CENTER);

        return contentPane;
    }

    private void updateStatistics() {
        int totalUsers = 15000;
        int recommendedUsers = 5000;
        int reviewsCount = 200;

        totalLabel.setText("Total Users: " + totalUsers);
        recommendedLabel.setText("Recommended Users: " + recommendedUsers);
        reviewsLabel.setText("Reviews: " + reviewsCount);

        int selectedWeek = datePicker.getDate().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        long seed = selectedWeek * 1000L;
        Random random = new Random(seed);

        int weeklyUsers = random.nextInt(1500);
        weeklyLabel.setText("Weekly Users: " + weeklyUsers);

        Map<DayOfWeek, Integer> dailyUsersMap = new HashMap<>();
        int remainingDailyUsers = weeklyUsers;
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            int dailyValue = remainingDailyUsers > 0 ? random.nextInt(remainingDailyUsers) : 0;
            dailyUsersMap.put(dayOfWeek, dailyValue);
            remainingDailyUsers -= dailyValue;
        }

        int dailyUsers = dailyUsersMap.get(datePicker.getDate().getDayOfWeek());
        dailyLabel.setText("Daily Users: " + dailyUsers);

        LocalDate currentDate = datePicker.getDate();
        int currentMonth = currentDate.getMonthValue();

        long monthlySeed = currentMonth * 1000L;
        Random monthlyRandom = new Random(monthlySeed);
        int monthlyUsers = monthlyRandom.nextInt(5000);
        monthlyLabel.setText("Monthly Users: " + monthlyUsers);

        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"));
        setTitle("User Analysis - " + formattedDate);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserAnalysisGUI());
    }
}
