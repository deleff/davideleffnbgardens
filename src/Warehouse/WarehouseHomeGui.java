package Warehouse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class WarehouseHomeGui extends JFrame {

    // Variables to read database
    int userId;
    String userFullName;

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusssLabel;
    private JPanel controlPanel;

    // Labels and Buttons
    JLabel welcomeLabel;
    JButton logOutButton;
    JButton customerOrderButton;
    JButton purchaseOrderButton;
    JButton productsButton;

    // Constructor
    public WarehouseHomeGui(String name, int userId) {
    	userFullName = name;
    	this.userId = userId;
    	
    	System.out.println(userFullName + " " + userId);

        // get initial values from database
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("NB Gardens Home");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(5, 1));
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
        welcomeLabel = new JLabel("Welcome " + userFullName);
        logOutButton = new JButton("Logout");
        customerOrderButton = new JButton("Customer Orders");
        purchaseOrderButton = new JButton("Purchase Orders");
        productsButton = new JButton("Products");

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(welcomeLabel);
        mainFrame.add(logOutButton);
        mainFrame.add(customerOrderButton);
        mainFrame.add(purchaseOrderButton);
        mainFrame.add(productsButton);
        mainFrame.setVisible(true);

    }

    private void showEvent() {
        headerLabel.setText("Welcome (employee name)");
        // JButton okButton = new JButton("<== Previous Order");
        // JButton submitButton = new JButton("Next order ==>");
        // JButton cancelButton = new JButton("Cancel");
        // JLabel orderID = new JLabel("Order ID:");
        // JLabel orderIDvalue = new JLabel("value");

        //prevButton.setActionCommand("prev");
        //nextButton.setActionCommand("next");
        //cancelButton.setActionCommand("Cancel");

        //prevButton.addActionListener(new BCL());
        //nextButton.addActionListener(new BCL());
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
                    orderNum -= 1;
                    readDBvalues(orderNum);
                    updateGUI();
                    break;

                case "next":
                    orderNum += 1;
                    readDBvalues(orderNum);
                    updateGUI();
                    break;
                case "Cancel":
                    statusssLabel.setText("Cancel not possible");
                    break;
            }
        }
    }
*/
  
    public static void main(String[] args) {
        // TODO Auto-generated method stub

       // WarehouseHomeGui sD = new WarehouseHomeGui();
      //  sD.showEvent();

    }

}
