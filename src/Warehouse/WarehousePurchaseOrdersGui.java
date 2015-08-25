package Warehouse;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;

public class WarehousePurchaseOrdersGui extends JFrame {

	// Variables to read database

	ArrayList<Integer> orderIdList = new ArrayList<Integer>(7);

	int orderIdListIndex = 0;
	int orderNum;
	String orderNumString;
	int warehouseEmployee;
	String arrivalDate;
	String porousWare;
	// String GDZ;
	String status;
	// int customerID;

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusssLabel;
	private JPanel controlPanel;

	// Labels and Buttons

	JButton prevButton;
	JButton nextButton;

	JLabel customerOrderLabel;

	JButton cancelButton;

	JLabel orderIDlabel;
	JLabel orderIDlabelvalue;

	JLabel warehouseEmployeeLabelID;
	JLabel warehouseEmployeeIDLabelvalue;

	JLabel arrivalDateLabel;
	JLabel arrivalDateLabelValue;

	JLabel statusLabel;
	JLabel statusLabelvalue;

	JLabel porousWareLabel;
	JLabel porousWareLabelValue;

	// JLabel itemsLabel;
	JButton itemsBTN;

	// JLabel commitLabel;
	JButton commitBTN;

	// Variables to connect to database
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://Localhost/nbgardens";
	static final String USER = "root";
	static final String PASS = "netbuilder";

	// Constructor
	public WarehousePurchaseOrdersGui() {

		// get initial values from database
		getOrders();
		readDBvalues(orderIdList.get(orderIdListIndex));
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Purchase Orders");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(8, 2));
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
		orderNumString = "" + orderNum;

		prevButton = new JButton("<== Previous Order");
		nextButton = new JButton("Next order ==>");

		customerOrderLabel = new JLabel("Purchase Orders");

		// cancelButton = new JButton("Cancel");

		orderIDlabel = new JLabel("Order ID:");
		orderIDlabelvalue = new JLabel(orderNumString);

		warehouseEmployeeLabelID = new JLabel("Warehouse Employee ID:");
		warehouseEmployeeIDLabelvalue = new JLabel("" + warehouseEmployee);

		arrivalDateLabel = new JLabel("Arrived on:");
		arrivalDateLabelValue = new JLabel(arrivalDate);

		statusLabel = new JLabel("Status:");
		statusLabelvalue = new JLabel(status);

		porousWareLabel = new JLabel("Needs Porousware? :");
		porousWareLabelValue = new JLabel(porousWare);

		// itemsLabel = new JLabel("Items:");
		itemsBTN = new JButton("Items Button");

		// commitLabel = new JLabel("Commit");
		commitBTN = new JButton("Commit Button");

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		// mainFrame.add(controlPanel);
		mainFrame.add(customerOrderLabel);

		mainFrame.add(prevButton);
		mainFrame.add(nextButton);

		mainFrame.add(orderIDlabel);
		mainFrame.add(orderIDlabelvalue);

		mainFrame.add(arrivalDateLabel);
		mainFrame.add(arrivalDateLabelValue);

		mainFrame.add(warehouseEmployeeLabelID);
		mainFrame.add(warehouseEmployeeIDLabelvalue);

		mainFrame.add(statusLabel);
		mainFrame.add(statusLabelvalue);

		mainFrame.add(porousWareLabel);
		mainFrame.add(porousWareLabelValue);

		// mainFrame.add(itemsLabel);
		mainFrame.add(itemsBTN);

		// mainFrame.add(commitLabel);
		mainFrame.add(commitBTN);

		mainFrame.setVisible(true);

	}

	private void showEvent() {
		headerLabel.setText("Welcome (employee name)");
		// JButton okButton = new JButton("<== Previous Order");
		// JButton submitButton = new JButton("Next order ==>");
		// JButton cancelButton = new JButton("Cancel");
		// JLabel orderID = new JLabel("Order ID:");
		// JLabel orderIDvalue = new JLabel("value");

		prevButton.setActionCommand("prev");
		nextButton.setActionCommand("next");
		itemsBTN.setActionCommand("items");

		// cancelButton.setActionCommand("Cancel");

		prevButton.addActionListener(new BCL());
		nextButton.addActionListener(new BCL());
		itemsBTN.addActionListener(new BCL());
		// controlPanel.add(okButton);
		// controlPanel.add(submitButton);
		// controlPanel.add(orderID);
		// controlPanel.add(orderIDvalue);

		// controlPanel.add(cancelButton);
		mainFrame.setVisible(true);
	}

	private class BCL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			String command = ae.getActionCommand();
			switch (command) {
			case "prev":
				orderIdListIndex -= 1;
				if (orderIdListIndex < 0) {
					orderIdListIndex = orderIdList.size() - 1;
				}
				readDBvalues(orderIdList.get(orderIdListIndex));
				updateGUI();
				break;

			case "next":
				orderIdListIndex += 1;
				if (orderIdListIndex >= orderIdList.size()) {
					orderIdListIndex = 0;
				}
				readDBvalues(orderIdList.get(orderIdListIndex));
				updateGUI();
				break;
			case "items":

				System.out.println("workin");
				WarehousePurchaseOrderLineGui sD = new WarehousePurchaseOrderLineGui(
						orderNum);
				MasterGuiClass.openWindow(sD);

				break;
			case "Cancel":
				statusssLabel.setText("Cancel not possible");
				break;
			}
		} 
	}

	// Update the label values
	public void updateGUI() {

		orderIDlabelvalue.setText("" + orderNum);

		arrivalDateLabelValue.setText(arrivalDate);

		warehouseEmployeeIDLabelvalue.setText("" + warehouseEmployee);

		statusLabelvalue.setText(status);

		porousWareLabelValue.setText(porousWare);

		mainFrame.repaint();
		mainFrame.setVisible(true);
	}

	// fill the array list of order lines for this order
	public void getOrders() {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			int maxID = 0;
			int minID = 0;

			// Read from database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql3 = "SELECT orderID " + "FROM purchaseorder ";
			ResultSet rs = stmt.executeQuery(sql3);
			while (rs.next()) {
				orderIdList.add(rs.getInt("orderID"));

				System.out.println(orderIdList);
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

	// Read the customer orders database values
	public void readDBvalues(int orderID) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		
			// Read from database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			

			// Get info from customer order
			String sql2 = "SELECT orderID, needsPorous, IDacceptedBy, dateArrived, status "
					+ "FROM purchaseorder "
					+ "where orderID = '" + orderID + "' ";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				orderNum = rs.getInt("orderID");
				porousWare = rs.getString("needsPorous");
				warehouseEmployee = rs.getInt("IDacceptedBy");
				arrivalDate = rs.getString("dateArrived");
				status = rs.getString("status");
				System.out.println("ordernum " + orderNum
						+ " warehouseEmployeeNum " + warehouseEmployee
						+ " pouroused " + porousWare + " arrival date "
						+ arrivalDate + " status " + status);
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

	} // end readDBvalues()

	// get the j frame
	public JFrame getJframe() {
		return mainFrame;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WarehousePurchaseOrdersGui sD = new WarehousePurchaseOrdersGui();
		sD.showEvent();
		MasterGuiClass.openWindow(sD);

	}

}
