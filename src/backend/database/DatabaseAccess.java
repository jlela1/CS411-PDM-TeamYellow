package backend.database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.*;

import java.util.Vector;
import java.awt.event.ActionEvent;

public class DatabaseAccess extends JFrame {

	private static final long serialVersionUID = 1L;
	// private static final String DefaultTableModel = null;
	private JPanel contentPane;
	private JTextField textSimulation;
	private JTextField textTime;
	private JTextField textGarageId;
	private JTextField textCapacity;
	private JTextField textCurrent;
	private JTextField textNotification;
	private JTextField textClockTime;
	private JTextField textMonth;
	private JTable table;
	private JTextField textDay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseAccess frame = new DatabaseAccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/*
	 * Create the frame.
	 */
	public DatabaseAccess() {

		setForeground(new Color(192, 192, 192));
		setFont(new Font("Corbel", Font.BOLD, 18));
		setTitle("DATABASE CONNECTION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 128, 0), 4, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		panel.setBounds(731, 10, 186, 289);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAddnew = new JButton("Add New");
		btnAddnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// load the driver
					Class.forName("com.mysql.cj.jdbc.Driver");

					// Establish the connection between java application with MySQL database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trends", " ",
							" ");

					String query = "insert into trenddata (simulationnumber, time, garageid,"
							+ "capacity, current, notification, clocktime, month, day)values(?,?,?,?,?,?,?,?,?)";

					PreparedStatement stm = con.prepareStatement(query);

					stm.setString(1, textSimulation.getText());
					stm.setString(2, textTime.getText());
					stm.setString(3, textGarageId.getText());
					stm.setString(4, textCapacity.getText());
					stm.setString(5, textCurrent.getText());
					stm.setString(6, textNotification.getText());
					stm.setString(7, textClockTime.getText());
					stm.setString(8, textMonth.getText());
					stm.setString(9, textDay.getText());

					int rows = stm.executeUpdate();
					if (rows > 0) {
						System.out.println("A row has been inserted");
					}
					// close connection
					stm.close();
					con.close();
				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(DatabaseAccess.class.getName())
							.log(java.util.logging.Level.SEVERE, null, ex);
				} catch (SQLException ex) {
					java.util.logging.Logger.getLogger(DatabaseAccess.class.getName())
							.log(java.util.logging.Level.SEVERE, null, ex);
				}
			}

			
		});

		btnAddnew.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAddnew.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		btnAddnew.setBounds(36, 10, 108, 36);
		panel.add(btnAddnew);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUpdate.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		btnUpdate.setBounds(36, 56, 108, 36);
		panel.add(btnUpdate);

		JButton btnShowdata = new JButton("Show Data");
		btnShowdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// ServerConfig server = new ServerConfig();

					// load the driver
					Class.forName("com.mysql.cj.jdbc.Driver");

					// Establish the connection between java application with MySQL database
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trends", " ",
							" ");

					// Create statement object
					// Statement stm =con.createStatement();

					String query = "select * from trenddata";

					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery(query);

					System.out.println(
							"simunb" + " | " + "time" + " | " + "garageId" + " |" + "capacity" + " | " + "current"
									+ " | " + "notification" + " | " + "clocktime" + " | " + "month " + " | " + "day");
					System.out.println(
							"---------------------------------------------------------------------------------------------");
					
					while (rs.next()) {

						String simulationnumber = rs.getString(1);
						String time = rs.getString(2);
						String garageid = rs.getString(3);
						String capacity = rs.getString(4);
						String current = rs.getString(5);
						String notification = rs.getString(6);
						String clocktime = rs.getString(7);
						String month = rs.getString(8);
						String day = rs.getString(9);

						System.out.println(simulationnumber + " | " + time + " | " + garageid + " | " + capacity + " | "
								+ current + " | " + notification + " | " + clocktime + " | " + month + " | " + day);
						
					}
					stm.close();
					con.close();
				} catch (ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
					// java.util.logging.Logger.getLogger(DatabaseAccess.class.getName())
					// .log(java.util.logging.Level.SEVERE, null, ex);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnShowdata.setVerticalAlignment(SwingConstants.BOTTOM);
		btnShowdata.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnShowdata.setBounds(36, 102, 108, 36);
		panel.add(btnShowdata);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSimulation.setText("");
				textTime.setText("");
				textGarageId.setText("");
				textCapacity.setText("");
				textCurrent.setText("");
				textNotification.setText("");
				textClockTime.setText("");
				textMonth.setText("");
				textDay.setText("");

			}
		});
		btnReset.setVerticalAlignment(SwingConstants.BOTTOM);
		btnReset.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		btnReset.setBounds(36, 148, 108, 36);
		panel.add(btnReset);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDelete.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		btnDelete.setBounds(36, 194, 108, 36);
		panel.add(btnDelete);

		JButton btnExit = new JButton("Exit");
		btnExit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnExit.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		btnExit.setBounds(36, 240, 108, 36);
		panel.add(btnExit);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		panel_1.setBounds(10, 10, 699, 312);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSimulation = new JLabel("simulationnumber");
		lblSimulation.setFont(new Font("SimSun", Font.BOLD, 13));
		lblSimulation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulation.setBounds(27, 17, 139, 21);
		panel_1.add(lblSimulation);

		JLabel lblTime = new JLabel("time");
		lblTime.setFont(new Font("SimSun", Font.BOLD, 13));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(27, 48, 139, 21);
		panel_1.add(lblTime);

		JLabel lblGarageid = new JLabel("garageid");
		lblGarageid.setFont(new Font("SimSun", Font.BOLD, 13));
		lblGarageid.setHorizontalAlignment(SwingConstants.CENTER);
		lblGarageid.setBounds(27, 79, 139, 21);
		panel_1.add(lblGarageid);

		JLabel lblCapacity = new JLabel("capacity");
		lblCapacity.setFont(new Font("SimSun", Font.BOLD, 13));
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacity.setBounds(27, 110, 139, 21);
		panel_1.add(lblCapacity);

		JLabel lblCurrent = new JLabel("current");
		lblCurrent.setFont(new Font("SimSun", Font.BOLD, 13));
		lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrent.setBounds(27, 141, 139, 21);
		panel_1.add(lblCurrent);

		JLabel lblNotification = new JLabel("notification");
		lblNotification.setFont(new Font("SimSun", Font.BOLD, 13));
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setBounds(27, 172, 139, 21);
		panel_1.add(lblNotification);

		JLabel lblClocktime = new JLabel("clocktime");
		lblClocktime.setFont(new Font("SimSun", Font.BOLD, 13));
		lblClocktime.setHorizontalAlignment(SwingConstants.CENTER);
		lblClocktime.setBounds(27, 203, 133, 21);
		panel_1.add(lblClocktime);

		JLabel lblMonth = new JLabel("month");
		lblMonth.setFont(new Font("SimSun", Font.BOLD, 14));
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setBounds(27, 234, 139, 21);
		panel_1.add(lblMonth);

		textSimulation = new JTextField();
		textSimulation.setBounds(247, 14, 365, 27);
		panel_1.add(textSimulation);
		textSimulation.setColumns(10);

		textTime = new JTextField();
		textTime.setColumns(10);
		textTime.setBounds(247, 45, 365, 27);
		panel_1.add(textTime);

		textGarageId = new JTextField();
		textGarageId.setColumns(10);
		textGarageId.setBounds(247, 76, 365, 27);
		panel_1.add(textGarageId);

		textCapacity = new JTextField();
		textCapacity.setColumns(10);
		textCapacity.setBounds(247, 107, 365, 27);
		panel_1.add(textCapacity);

		textCurrent = new JTextField();
		textCurrent.setColumns(10);
		textCurrent.setBounds(247, 138, 365, 27);
		panel_1.add(textCurrent);

		textNotification = new JTextField();
		textNotification.setColumns(10);
		textNotification.setBounds(247, 169, 365, 27);
		panel_1.add(textNotification);

		textClockTime = new JTextField();
		textClockTime.setColumns(10);
		textClockTime.setBounds(247, 200, 365, 27);
		panel_1.add(textClockTime);

		textMonth = new JTextField();
		textMonth.setColumns(10);
		textMonth.setBounds(247, 229, 365, 31);
		panel_1.add(textMonth);

		table = new JTable();
		table.setBounds(0, 326, 907, 230);
		panel_1.add(table);

		JLabel lblDay = new JLabel("day");
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setFont(new Font("SimSun", Font.BOLD, 14));
		lblDay.setBounds(27, 275, 139, 27);
		panel_1.add(lblDay);

		textDay = new JTextField();
		textDay.setText("");
		textDay.setBounds(247, 270, 365, 32);
		panel_1.add(textDay);
		textDay.setColumns(10);
	}
}

