package Warehouse;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class NbGardensLoginGui extends JFrame {

	// Variables to read database
	String employeeID;

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusssLabel;
	private JPanel controlPanel;

	// Labels and Buttons
	JLabel enterIDLabel;
	JTextField enterIDTextfield;
	JButton submitButton;

	// Variables to connect to database
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://Localhost/nbgardens";
	static final String USER = "root";
	static final String PASS = "netbuilder";

	// Strings to read the database
	String name;
	String department;

	// Constructor
	public NbGardensLoginGui() {

		// get initial values from database
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("NB Gardens Login");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 2));
		headerLabel = new JLabel("", JLabel.CENTER);
		statusssLabel = new JLabel("", JLabel.CENTER);
		statusssLabel.setSize(350, 100);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		// Labels and Buttons
		enterIDLabel = new JLabel("Welcome, please enter your employee ID: ");
		enterIDTextfield = new JTextField();
		submitButton = new JButton("Login");

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(enterIDLabel);
		mainFrame.add(enterIDTextfield);
		mainFrame.add(submitButton);
	}

	private void showEvent() {
		headerLabel.setText("Welcome (employee name)");
		submitButton.setActionCommand("submit");

		submitButton.addActionListener(new BCL());
		

		// controlPanel.add(cancelButton);
		mainFrame.setVisible(true);
	}

	private class BCL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			String command = ae.getActionCommand();
			switch (command) {
			case "submit":
				readDBvalues();
				if (department.toLowerCase().trim().equals("warehouse")){
					WarehouseHomeGui sD = new WarehouseHomeGui(name, Integer.parseInt(enterIDTextfield.getText()) );
					MasterGuiClass.openWindow(sD);				}
				break;
			}
		}
	}

	// Read the customer orders database values
	public void readDBvalues() {

		Connection conn = null;
		Statement stmt = null;

		try { // Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Read from database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			// Get info from orderID
			String sql2 = "SELECT firstName, lastName, department "
					+ " FROM employee " + "WHERE employeeID = "
					+ enterIDTextfield.getText() + "";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				name = rs.getString("firstName");
				name += " ";
				name += rs.getString("lastName");
				department = rs.getString("department");
				System.out.println("name " + name + ", department "
						+ department);
			}
			rs.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");

	} // end getOrderLines()

	// Get the j frame
	public JFrame getJframe() {
		return mainFrame;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NbGardensLoginGui sD = new NbGardensLoginGui();
		sD.showEvent();

	}

}
