package Warehouse;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class WarehouseProducts extends JFrame {

    // Variables to read database
    int productID;
    int maxProductID;
    String productName;
    int totalQty;
    int availableQty;
    int reservedQty;
    int availablePorous;
    int reservedPorous;

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusssLabel;
    private JPanel controlPanel;

    // Labels and Buttons
    JButton prevButton;
    JButton nextButton;

    JLabel productsLabel;

    JLabel productIDlabel;

    JLabel productIDlabelValue;

    JLabel productNameLabel;
    JLabel productNameLabelValue;

    JLabel totalQtyLabel;
    JLabel totalQtyLabelValue;

    JLabel availableQtyLabel;
    JLabel availableQtyLabelValue;

    JLabel reservedQtyLabel;
    JLabel reservedQtyLabelValue;

    JLabel availablePorousLabel;
    JLabel availablePorousLabelValue;

    JLabel reservedPorousLabel;
    JLabel reservedPorousLabelValue;

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
    JButton backBTN;

    // Variables to connect to database
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://Localhost/nbgardens";
    static final String USER = "root";
    static final String PASS = "netbuilder";

    // Constructor
    public WarehouseProducts() {

        // get initial values from database
        readDBvalues(1);
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Products");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(10, 2));
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
        prevButton = new JButton("<== Previous Product");
        nextButton = new JButton("Next Product ==>");

        productsLabel = new JLabel("Products");

        //cancelButton = new JButton("Cancel");
        productIDlabel = new JLabel("Product ID:");
        productIDlabelValue = new JLabel("" + productID);

        productNameLabel = new JLabel("Name:");
        productNameLabelValue = new JLabel(productName);

        totalQtyLabel = new JLabel("Total Quantity:");
        totalQtyLabelValue = new JLabel("" + totalQty);

        availableQtyLabel = new JLabel("Available Regular Quantity:");
        availableQtyLabelValue = new JLabel("" + availableQty);

        reservedQtyLabel = new JLabel("Reserved Regular Quantity:");
        reservedQtyLabelValue = new JLabel("" + reservedQty);

        availablePorousLabel = new JLabel("Available Porousware Quantity:");
        availablePorousLabelValue = new JLabel("" + availablePorous);

        reservedPorousLabel = new JLabel("Reserved Porousware Quantity:");
        reservedPorousLabelValue = new JLabel("" + reservedPorous);

        // commitLabel = new JLabel("Commit");
        backBTN = new JButton("Back");

        // itemsLabel = new JLabel("Items:");
        itemsBTN = new JButton("Shelves Button");

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        // mainFrame.add(controlPanel);
        mainFrame.add(productsLabel);

        mainFrame.add(prevButton);
        mainFrame.add(nextButton);

        mainFrame.add(productIDlabel);
        mainFrame.add(productIDlabelValue);

        mainFrame.add(productNameLabel);
        mainFrame.add(productNameLabelValue);

        mainFrame.add(totalQtyLabel);
        mainFrame.add(totalQtyLabelValue);

        mainFrame.add(availableQtyLabel);
        mainFrame.add(availableQtyLabelValue);

        mainFrame.add(reservedQtyLabel);
        mainFrame.add(reservedQtyLabelValue);

        mainFrame.add(availablePorousLabel);
        mainFrame.add(availablePorousLabelValue);

        mainFrame.add(reservedPorousLabel);
        mainFrame.add(reservedPorousLabelValue);

        // mainFrame.add(itemsLabel);
        mainFrame.add(backBTN);

        // mainFrame.add(commitLabel);
        mainFrame.add(itemsBTN);

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
        //cancelButton.setActionCommand("Cancel");

        prevButton.addActionListener(new BCL());
        nextButton.addActionListener(new BCL());
		//cancelButton.addActionListener(new BCL());
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
                    productID -= 1;
                    if (productID <= 0) {
                        productID = maxProductID;
                    }
                    readDBvalues(productID);
                    updateGUI();
                    break;

                case "next":
                    productID += 1;
                    if (productID > maxProductID) {
                        productID = 1;
                    }
                    readDBvalues(productID);
                    updateGUI();
                    break;
                case "Cancel":
                    statusssLabel.setText("Cancel not possible");
                    break;
            }
        }
    }

    //Update the label values
    public void updateGUI() {
        productIDlabelValue.setText("" + productID);

        productNameLabelValue.setText(productName);

        totalQtyLabelValue.setText("" + totalQty);

        availableQtyLabelValue.setText("" + availableQty);

        reservedQtyLabelValue.setText("" + reservedQty);

        availablePorousLabelValue.setText("" + availablePorous);
        
        reservedPorousLabelValue.setText("" + reservedPorous);
        
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
            if (maxID != 0 && orderID > maxID) {
                orderID = minID;
            }
            if (minID != 0 && orderID < minID) {
                orderID = maxID;
            }
/*
            String sql2 = "SELECT orderID, WarehouseEmployeeID, shippingAddress, gdz, status "
                    + "FROM customerorder " + "WHERE orderID = " + orderID + "";
            ResultSet rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                orderNum = rs.getInt("orderID");
                warehouseEmployee = rs.getString("WarehouseEmployeeID");
                // customerName = rs.getString("GET THE CUSTOMER NAME");
                shippingAddress = rs.getString("shippingAddress");
                GDZ = rs.getString("gdz");
                status = rs.getString("status");
                System.out.println(orderNum + warehouseEmployee
                        + shippingAddress + GDZ + status);
            }
            rs.close();
*/
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");

    } // end readDBvalues()

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        WarehouseProducts sD = new WarehouseProducts();
        sD.showEvent();

    }

}
