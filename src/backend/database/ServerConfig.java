package backend.database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JPasswordField;

public class ServerConfig extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textServer;
	private JTextField textUser;
	private JPasswordField textPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerConfig frame = new ServerConfig();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerConfig() {

		setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 16));
		setTitle("Data Server Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 266);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(2, 0, 0, 0));
		contentPane.setForeground(new Color(128, 64, 0));
		contentPane.setSize(new Dimension(2, 2));
		contentPane.setName("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblServerID = new JLabel("ServerN or IP");
		lblServerID.setFont(new Font("SimSun", Font.BOLD, 12));
		lblServerID.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerID.setBounds(35, 30, 92, 26);
		contentPane.add(lblServerID);

		textServer = new JTextField();
		textServer.setBounds(137, 30, 271, 26);
		contentPane.add(textServer);
		textServer.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("SimSun", Font.BOLD, 12));
		lblUser.setBounds(35, 76, 92, 21);
		contentPane.add(lblUser);

		textUser = new JTextField();
		textUser.setBounds(137, 70, 199, 26);
		contentPane.add(textUser);
		textUser.setColumns(10);

		JLabel lblPass = new JLabel("Pass Word");
		lblPass.setFont(new Font("SimSun", Font.BOLD, 12));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setBounds(35, 107, 92, 21);
		contentPane.add(lblPass);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerDetails sd = new ServerDetails();
				sd.setServer(textServer.getText());
				sd.setUser(textUser.getText());
				sd.setPass(textPass.getText());

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://" + sd.getServer(), sd.getUser(),
							sd.getPass());
					// PreparedStatement pst = con.prepareStatement("select * from trends");
					JOptionPane.showInternalMessageDialog(null, "Connection successfully established");
					//JOptionPane.showInternalMessageDialog(null, new DatabaseAccess());
					con.close();
				} catch (Exception ex) {
					JOptionPane.showInternalMessageDialog(null, ex.getMessage());
				}
				
			}
			
		});
		
		btnConfirm.setFont(new Font("SimSun", Font.BOLD | Font.ITALIC, 13));
		btnConfirm.setBounds(273, 180, 85, 36);
		contentPane.add(btnConfirm);
		
		textPass = new JPasswordField();
		textPass.setBounds(137, 108, 168, 20);
		contentPane.add(textPass);
	}
}

