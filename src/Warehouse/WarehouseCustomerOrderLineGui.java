/** Edit 
 * the
 *  SQL
 *   to
 *    be
 *     Purchase
 *      Orders **/

package Warehouse;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;

public class WarehouseCustomerOrderLineGui extends JFrame {

	// Variables to read database

	ArrayList<Integer> orderLineList = new ArrayList<Integer>(7);

	int orderNum;
	String orderNumString;
	int lineID;
	String lineIDString;
	int productNum;
	String productNumString;
	int totalProducts;
	String totalProductsString;
	int productID;
	String productIDString;
	String productName;
	String porousWare;
	int shelfID;
	String shelfIDString;
	String shelfRowString;
	String shelfColumnString;
	String picked;
	String notes;

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusssLabel;
	private JPanel controlPanel;

	// Labels and Buttons
	JLabel statusLabel;
	JLabel statusLabelvalue;

	JButton prevButton;
	JButton nextButton;

	JLabel customerOrderLabel;

	JButton cancelButton;

	JLabel orderIDlabel;
	JLabel orderIDlabelvalue;

	JLabel itemNumLabel;
	JLabel itemNumLabelvalue;

	JLabel CustomerNameLabel;
	JLabel CustomerNameLabelValue;

	JLabel pickedLabel;
	JLabel pickedLabelValue;

	JLabel productIDlabel;
	JLabel productIDlabelValue;

	JLabel productNameLabel;
	JLabel productNameLabelvalue;

	JLabel shelfIDlabel;
	JLabel shelfIDlabelValue;

	JLabel shelfRowLabel;
	JLabel shelfRowLabelvalue;

	JLabel shelfColumnLabel;
	JLabel shelfColumnLabelvalue;

	JLabel porousWareLabel;
	JLabel porousWareLabelvalue;

	// JButton back to orders;
	JButton backBTN;

	// JButton commit button;
	JButton commitBTN;

	// JButton undo changes (before commit) button;
	JButton undoBTN;

	// Variables to connect to database
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://Localhost/nbgardens";
	static final String USER = "root";
	static final String PASS = "netbuilder";

	// Constructor
	public WarehouseCustomerOrderLineGui(int orderNumID) {

		// get initial values from database
		getOrderLines(orderNumID);
		productNum = 1;
		readDBvalues(orderLineList.get(productNum - 1));
		prepareGUI();
		showEvent();

	}

	private void prepareGUI() {
		mainFrame = new JFrame("Customer Order Line");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(12, 2));
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

		prevButton = new JButton("<== Previous Product");
		nextButton = new JButton("Next Product ==>");

		customerOrderLabel = new JLabel("Customer Order Line");

		// cancelButton = new JButton("Cancel");

		statusLabel = new JLabel("Status:");
		// statusLabelvalue = new JLabel(status);

		orderIDlabel = new JLabel("Order ID:");
		orderIDlabelvalue = new JLabel("" + orderNum);

		itemNumLabel = new JLabel("Order product number:");
		itemNumLabelvalue = new JLabel(productNum + " / "
				+ orderLineList.size());

		pickedLabel = new JLabel("Picked?: ");
		pickedLabelValue = new JLabel(picked);

		productIDlabel = new JLabel("Product ID:");
		productIDlabelValue = new JLabel("" + productID);

		productNameLabel = new JLabel("Product Name:");
		productNameLabelvalue = new JLabel(productName);

		shelfIDlabel = new JLabel("Shelf ID:");
		shelfIDlabelValue = new JLabel("" + shelfID);

		shelfRowLabel = new JLabel("Shelf Row:");
		shelfRowLabelvalue = new JLabel(shelfRowString);

		shelfColumnLabel = new JLabel("Shelf Column:");
		shelfColumnLabelvalue = new JLabel(shelfColumnString);

		porousWareLabel = new JLabel("Porousware:");
		porousWareLabelvalue = new JLabel(porousWare);

		// itemsLabel = new JLabel("Items:");
		backBTN = new JButton("Back To Orders");

		// commitLabel = new JLabel("Commit");
		commitBTN = new JButton("Commit Button");

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		// mainFrame.add(controlPanel);
		// mainFrame.add(statusLabel);
		mainFrame.add(customerOrderLabel);

		mainFrame.add(prevButton);
		mainFrame.add(nextButton);

		mainFrame.add(orderIDlabel);
		mainFrame.add(orderIDlabelvalue);

		mainFrame.add(itemNumLabel);
		mainFrame.add(itemNumLabelvalue);

		mainFrame.add(pickedLabel);
		mainFrame.add(pickedLabelValue);

		mainFrame.add(productIDlabel);
		mainFrame.add(productIDlabelValue);

		mainFrame.add(productNameLabel);
		mainFrame.add(productNameLabelvalue);

		mainFrame.add(shelfIDlabel);
		mainFrame.add(shelfIDlabelValue);

		mainFrame.add(shelfRowLabel);
		mainFrame.add(shelfRowLabelvalue);

		mainFrame.add(shelfColumnLabel);
		mainFrame.add(shelfColumnLabelvalue);

		mainFrame.add(porousWareLabel);
		mainFrame.add(porousWareLabelvalue);

		// mainFrame.add(itemsLabel);
		mainFrame.add(backBTN);

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
		backBTN.setActionCommand("back");
		// cancelButton.setActionCommand("Cancel");

		prevButton.addActionListener(new BCL());
		nextButton.addActionListener(new BCL());
		backBTN.addActionListener(new BCL());

		// cancelButton.addActionListener(new BCL());
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
				productNum -= 1;
				if (productNum <= 0) {
					productNum = orderLineList.size();
				}
				readDBvalues(orderLineList.get(productNum - 1));
				updateGUI();
				break;

			case "next":
				productNum += 1;
				if (productNum > orderLineList.size()) {
					productNum = 1;
				}
				readDBvalues(orderLineList.get(productNum - 1));
				updateGUI();
				break;
				
			case "back":
				System.out.println("workin");
				MasterGuiClass.closeWindow();

				break;
			case "Cancel":
				statusssLabel.setText("Cancel not possible");
				break;
			}
		}
	}

	// Update the label values

	public void updateGUI() {
		orderNumString = "" + orderNum;
		orderIDlabelvalue.setText(orderNumString);

		itemNumLabelvalue.setText(productNum + " / " + orderLineList.size());

		pickedLabelValue.setText(picked);

		productIDlabelValue.setText("" + productID);

		productNameLabelvalue.setText(productName);

		shelfIDlabelValue.setText("" + shelfID);

		shelfColumnLabelvalue.setText(shelfColumnString);

		shelfRowLabelvalue.setText(shelfRowString);

		porousWareLabelvalue.setText(porousWare);
		
		

		// statusLabelvalue.setText(status);

		mainFrame.repaint();
	}

	// Read the customer orders database values
	public void readDBvalues(int lineID) {

		Connection conn = null;
		Statement stmt = null;

		try { // Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			int minID = orderLineList.get(0);
			int maxID = orderLineList.get(orderLineList.size() - 1);

			System.out.println("min ID: " + minID);
			System.out.println("max ID: " + maxID);

			// Read from database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			// Reassign orderID if the value goes out of bounds
			if (maxID != 0 && lineID > maxID) {
				lineID = minID;
			}
			if (minID != 0 && lineID < minID) {
				lineID = maxID;
			}

			// Get info from orderID
			String sql2 = "SELECT IDorder, IDproduct, porousWare, IDshelf, picked, notes "
					+ " FROM customerorderline "
					+ "WHERE lineID = "
					+ lineID
					+ "";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				orderNum = rs.getInt("IDorder");
				orderNum = rs.getInt("IDorder");
				productID = rs.getInt("IDproduct");
				shelfID = rs.getInt("IDshelf");
				porousWare = rs.getString("porousWare");
				picked = rs.getString("picked");
				notes = rs.getString("notes");
				System.out.println("Line ID " + lineID + ", order num "
						+ orderNum + " productID " + productID + " shelfID "
						+ shelfID + " picked " + picked + " notes " + notes);
			}

			// Get name from product
			String sqlProduct = "SELECT name " + " FROM product "
					+ " WHERE productID =  '" + productID + "' ";
			ResultSet rsProduct = stmt.executeQuery(sqlProduct);
			while (rsProduct.next()) {
				productName = rsProduct.getString("name");
				System.out.println("product name " + productName);
			}

			// Get info from shelf
			String sqlShelf = "SELECT shelfRow, shelfColumn " + " FROM shelf "
					+ " WHERE shelfID = '" + shelfID + "' ";
			ResultSet rsShelf = stmt.executeQuery(sqlShelf);
			while (rsShelf.next()) {

				shelfRowString = rsShelf.getString("shelfRow");
				shelfColumnString = rsShelf.getString("shelfColumn");

				System.out.println("shelf " + shelfRowString
						+ shelfColumnString);
			}

			rs.close();
			rsProduct.close();
			rsShelf.close();

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

	// fill the array list of order lines for this order
	public void getOrderLines(int orderID) {
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

			String sql3 = "SELECT lineID " + "FROM customerorderline "
					+ "WHERE IDorder = " + orderID + "";
			ResultSet rs = stmt.executeQuery(sql3);
			while (rs.next()) {
				orderLineList.add(rs.getInt("lineID"));

				System.out.println(orderLineList);
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

		//WarehouseCustomerOrderLineGui sD = new WarehouseCustomerOrderLineGui();
		//sD.showEvent();
		// sD.getOrderLines(1);
		// sD.readDBvalues(3);
		// sD.updateGUI();

	}

}