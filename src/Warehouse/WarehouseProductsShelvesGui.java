package Warehouse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class WarehouseProductsShelvesGui extends JFrame {

    // Variables to read database
    int productID;
    String productName;
    int totalQty;

    //s stands for shelf
    String s1Porous;
    String s1Row;
    String s1Column;
    String s1AvailableQty;
    String s1ReservedQty;

    String s2Porous;
    String s2Row;
    String s2Column;
    String s2AvailableQty;
    String s2ReservedQty;

    String s3Porous;
    String s3Row;
    String s3Column;
    String s3AvailableQty;
    String s3ReservedQty;

    String s4Porous;
    String s4Row;
    String s4Column;
    String s4AvailableQty;
    String s4ReservedQty;

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusssLabel;
    private JPanel controlPanel;

    // Labels and Buttons
    JLabel porousWareLabel;
    JLabel shelfRowLabel;
    JLabel shelfColumnLabel;
    JLabel availableQtyLabel;
    JLabel reservedQtyLabel;

    JLabel totalQtyLabelValue;

    JLabel productsLabel;

    JLabel productIDlabel;

    JLabel productIDlabelValue;

    JLabel productNameLabel;
    JLabel productNameLabelValue;

    JLabel s1PorousValue;
    JLabel s1RowValue;
    JLabel s1ColumnValue;
    JLabel s1AvailableQtyValue;
    JLabel s1ReservedQtyValue;

    JLabel s2PorousValue;
    JLabel s2RowValue;
    JLabel s2ColumnValue;
    JLabel s2AvailableQtyValue;
    JLabel s2ReservedQtyValue;

    JLabel s3PorousValue;
    JLabel s3RowValue;
    JLabel s3ColumnValue;
    JLabel s3AvailableQtyValue;
    JLabel s3ReservedQtyValue;

    JLabel s4PorousValue;
    JLabel s4RowValue;
    JLabel s4ColumnValue;
    JLabel s4AvailableQtyValue;
    JLabel s4ReservedQtyValue;

   
    // JLabel back button;
    JButton backBTN;

    // Variables to connect to database
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://Localhost/nbgardens";
    static final String USER = "root";
    static final String PASS = "netbuilder";

    // Constructor
    public WarehouseProductsShelvesGui() {

        // get initial values from database
//        readDBvalues(1);
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Shelves");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(7, 5));
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
        //cancelButton = new JButton("Cancel");
        productIDlabel = new JLabel("Product ID:");
        productIDlabelValue = new JLabel("" + productID);

        productNameLabel = new JLabel("Name:");
        productNameLabelValue = new JLabel(productName);
        totalQtyLabelValue = new JLabel("Total " + totalQty);
        porousWareLabel = new JLabel("Porousware");
        shelfRowLabel = new JLabel("Row");
        shelfColumnLabel = new JLabel("Column");
        availableQtyLabel = new JLabel("Available Quantity");
        reservedQtyLabel = new JLabel("Reserved Quantity");

        s1PorousValue = new JLabel(s1Porous);
        s1RowValue = new JLabel(s1Row);
        s1ColumnValue = new JLabel(s1Column);
        s1AvailableQtyValue = new JLabel(s1AvailableQty);
        s1ReservedQtyValue = new JLabel(s1ReservedQty);

        s2PorousValue = new JLabel(s2Porous);
        s2RowValue = new JLabel(s2Row);
        s2ColumnValue = new JLabel(s2Column);
        s2AvailableQtyValue = new JLabel(s2AvailableQty);
        s2ReservedQtyValue = new JLabel(s2ReservedQty);

        s3PorousValue = new JLabel(s3Porous);
        s3RowValue = new JLabel(s3Row);
        s3ColumnValue = new JLabel(s3Column);
        s3AvailableQtyValue = new JLabel(s3AvailableQty);
        s3ReservedQtyValue = new JLabel(s3ReservedQty);

        s4PorousValue = new JLabel(s4Porous);
        s4RowValue = new JLabel(s4Row);
        s4ColumnValue = new JLabel(s4Column);
        s4AvailableQtyValue = new JLabel(s4AvailableQty);
        s4ReservedQtyValue = new JLabel(s4ReservedQty);

        // commitLabel = new JLabel("Commit");
        backBTN = new JButton("Back");



        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
   //     mainFrame.add(headerLabel);
        // mainFrame.add(controlPanel);
  //      mainFrame.add(productsLabel);

        mainFrame.add(productIDlabel);
        mainFrame.add(productIDlabelValue);
        mainFrame.add(productNameLabel);
        mainFrame.add(productNameLabelValue);
        mainFrame.add(totalQtyLabelValue);

        mainFrame.add(porousWareLabel);
        mainFrame.add(shelfRowLabel);
        mainFrame.add(shelfColumnLabel);
        mainFrame.add(availableQtyLabel);
        mainFrame.add(reservedQtyLabel);

        mainFrame.add(s1PorousValue);
        mainFrame.add(s1RowValue);
        mainFrame.add(s1ColumnValue);
        mainFrame.add(s1AvailableQtyValue);
        mainFrame.add(s1ReservedQtyValue);

        mainFrame.add(s2PorousValue);
        mainFrame.add(s2RowValue);
        mainFrame.add(s2ColumnValue);
        mainFrame.add(s2AvailableQtyValue);
        mainFrame.add(s2ReservedQtyValue);

        mainFrame.add(s3PorousValue);
        mainFrame.add(s3RowValue);
        mainFrame.add(s3ColumnValue);
        mainFrame.add(s3AvailableQtyValue);
        mainFrame.add(s3ReservedQtyValue);

        mainFrame.add(s4PorousValue);
        mainFrame.add(s4RowValue);
        mainFrame.add(s4ColumnValue);
        mainFrame.add(s4AvailableQtyValue);
        mainFrame.add(s4ReservedQtyValue);


        mainFrame.add(backBTN);

        mainFrame.setVisible(true);

    }

    private void showEvent() {
        headerLabel.setText("Welcome (employee name)");
        // JButton okButton = new JButton("<== Previous Order");
        // JButton submitButton = new JButton("Next order ==>");
        // JButton cancelButton = new JButton("Cancel");
        // JLabel orderID = new JLabel("Order ID:");
        // JLabel orderIDvalue = new JLabel("value");

//        prevButton.setActionCommand("prev");
  //      nextButton.setActionCommand("next");
        //cancelButton.setActionCommand("Cancel");

    //    prevButton.addActionListener(new BCL());
      //  nextButton.addActionListener(new BCL());
		//cancelButton.addActionListener(new BCL());
        // controlPanel.add(okButton);
        // controlPanel.add(submitButton);
        // controlPanel.add(orderID);
        // controlPanel.add(orderIDvalue);

        //controlPanel.add(cancelButton);
        mainFrame.setVisible(true);
    }
/*
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
*/
    
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

        WarehouseProductsShelvesGui sD = new WarehouseProductsShelvesGui();
        sD.showEvent();

    } 

}
