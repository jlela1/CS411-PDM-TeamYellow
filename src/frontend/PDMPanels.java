package frontend;

import javax.swing.*;
import java.awt.*;

public class PDMPanels {
    public static JPanel createHeader(String title) {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 241, 170, 255));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel(title);
        headingLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        headingLabel.setForeground(Color.DARK_GRAY);
        headingLabel.setBackground(Color.lightGray);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headingLabel);

        return headerPanel;
    }
    public static JPanel createUserHeader(String title) {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(113, 100, 217, 242));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel(title);
        headingLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        headingLabel.setForeground(Color.DARK_GRAY);
        headingLabel.setBackground(Color.lightGray);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headingLabel);

        return headerPanel;

    }

    public static JPanel GeneralHeader(String title) {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(98, 145, 141, 250));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel(title);
        headingLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        headingLabel.setForeground(Color.DARK_GRAY);
        headingLabel.setBackground(Color.lightGray);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headingLabel);

        return headerPanel;
    }


    public static JPanel createFooter() {
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Monospaced", Font.ITALIC, 16));
        footerLabel.setForeground(Color.black);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 241, 170, 255));
        footerPanel.add(footerLabel);

        return footerPanel;
    }

    public static JPanel createUserFooter() {
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Monospaced", Font.ITALIC, 16));
        footerLabel.setForeground(Color.black);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(149, 133, 255, 255));
        footerPanel.add(footerLabel);

        return footerPanel;
    }

    public static JPanel GeneralFooter() {
        JLabel footerLabel = new JLabel("Parking Demand Management (PDM)");
        footerLabel.setFont(new Font("Monospaced", Font.ITALIC, 16));
        footerLabel.setForeground(Color.black);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(98, 145, 141, 250));
        footerPanel.add(footerLabel);

        return footerPanel;
    }

    public static void styleButton(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setBackground(new Color(180, 209, 215, 250));
        button.setForeground(Color.BLACK);
    }
}