package Warehouse;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class WarehouseCustomerOrdersGui extends JFrame {

	// Variables to read database
	int orderNum;
	String orderNumString;
	String warehouseEmployee;
	String customerName;
	String shippingAddress;
	String GDZ;
	String status;
	int customerID;

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

	JLabel CustomerNameLabel;
	JLabel CustomerNameLabelValue;

	JLabel shippingAddressLabel;
	JLabel shippingAddressLabelValue;

	JLabel GDZlabel;
	JLabel GDZlabelValue;

	JLabel statusLabel;
	JLabel statusLabelvalue;

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
	public WarehouseCustomerOrdersGui() {

		// get initial values from database
		readDBvalues(1);
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Customer Orders");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(9, 2));
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

		customerOrderLabel = new JLabel("Customer Orders");

		//cancelButton = new JButton("Cancel");

		orderIDlabel = new JLabel("Order ID:");
		orderIDlabelvalue = new JLabel(orderNumString);

		warehouseEmployeeLabelID = new JLabel("Warehouse Employee ID:");
		warehouseEmployeeIDLabelvalue = new JLabel(warehouseEmployee);

		CustomerNameLabel = new JLabel("Customer Name:");
		CustomerNameLabelValue = new JLabel(customerName);

		shippingAddressLabel = new JLabel("Shipping Address:");
		shippingAddressLabelValue = new JLabel(shippingAddress);

		GDZlabel = new JLabel("GDZ:");
		GDZlabelValue = new JLabel(GDZ);

		statusLabel = new JLabel("Status:");
		statusLabelvalue = new JLabel(status);

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

		mainFrame.add(warehouseEmployeeLabelID);
		mainFrame.add(warehouseEmployeeIDLabelvalue);

		mainFrame.add(CustomerNameLabel);
		mainFrame.add(CustomerNameLabelValue);

		mainFrame.add(shippingAddressLabel);
		mainFrame.add(shippingAddressLabelValue);

		mainFrame.add(GDZlabel);
		mainFrame.add(GDZlabelValue);

		mainFrame.add(statusLabel);
		mainFrame.add(statusLabelvalue);

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

		//cancelButton.setActionCommand("Cancel");

		prevButton.addActionListener(new BCL());
		nextButton.addActionListener(new BCL());
		itemsBTN.addActionListener(new BCL());
		// controlPanel.add(okButton);
		// controlPanel.add(submitButton);
		// controlPanel.add(orderID);
		// controlPanel.add(orderIDvalue);

		//controlPanel.add(cancelButton);
		mainFrame.setVisible(true);
	}

	private class BCL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			String command = ae.getActionCommand();
			switch (command) {
			case "prev":
				orderNum -= 1;
				readDBvalues(orderNum);
				updateGUI();
				break;

			case "next":
				orderNum += 1;
				readDBvalues(orderNum);
				updateGUI();
				break;
			case "items":
				
				System.out.println("workin");
				WarehouseCustomerOrderLineGui sD = new WarehouseCustomerOrderLineGui(orderNum);
				MasterGuiClass.openWindow(sD);
				
				break;
			case "Cancel":
				statusssLabel.setText("Cancel not possible");
				break;
			}
		}
	}

	
	//Update the label values
	public void updateGUI(){
		orderNumString = "" + orderNum;
		orderIDlabelvalue.setText(orderNumString);

		warehouseEmployeeIDLabelvalue.setText(warehouseEmployee);

		CustomerNameLabelValue.setText(customerName);

		shippingAddressLabelValue.setText(shippingAddress);

		GDZlabelValue.setText(GDZ);

		statusLabelvalue.setText(status);
		
		mainFrame.repaint();
	}
	
	//Read the customer orders database values
	public void readDBvalues(int orderID) {
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
			
			//Make sure orderID is smaller than the maximum orderID
			String sqlMax = "SELECT MAX(orderID)"
					+ "FROM customerorder ";
			ResultSet rset = stmt.executeQuery(sqlMax);
			if (rset.next()) {
				  maxID = rset.getInt(1);
				}
			
				
				//Make sure orderID is greater than the minimum orderID
				String sqlMin = "SELECT MIN(orderID)"
						+ "FROM customerorder ";
				ResultSet rset2 = stmt.executeQuery(sqlMin);
				if (rset2.next()) {
					  minID = rset2.getInt(1);
					}
				
				//Reassign orderID if the value goes out of bounds
				if (maxID != 0 && orderID > maxID){
					orderID = minID;
				}
					if (minID != 0 && orderID < minID){
					orderID = maxID;
				}
				
				
			//Get info from customer order
			String sql2 = "SELECT orderID, customerID, WarehouseEmployeeID, shippingAddress, gdz, status "
					+ "FROM customerorder " + "WHERE orderID = " + orderID + "";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				orderNum = rs.getInt("orderID");
				warehouseEmployee = rs.getString("WarehouseEmployeeID");
				customerID = rs.getInt("customerID");
				shippingAddress = rs.getString("shippingAddress");
				GDZ = rs.getString("gdz");
				status = rs.getString("status");
				System.out.println(orderNum + warehouseEmployee
						+ shippingAddress + GDZ + status);
			}
			
			
			//Get info from customer
			String sqlCustomer = "SELECT firstName, lastName "
					+ " FROM customer "
					+ " WHERE customerID = '" + customerID + "' ";
			ResultSet rsCustomer = stmt.executeQuery(sqlCustomer);
			while (rsCustomer.next()) {
				
				customerName = rsCustomer.getString("firstName");
				customerName += " ";
				customerName += rsCustomer.getString("lastName");
				
				System.out.println("Customer name " + customerName);
			}
			
			rsCustomer.close();
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
	
	//get the j frame
	public JFrame getJframe(){
		return mainFrame;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WarehouseCustomerOrdersGui sD = new WarehouseCustomerOrdersGui();
		sD.showEvent();
		MasterGuiClass.openWindow(sD);

	}

}
