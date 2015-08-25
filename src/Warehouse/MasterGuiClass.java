package Warehouse;

import java.util.Stack;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MasterGuiClass {

	private static Stack<JFrame> guiStack = new Stack<JFrame>();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void openWindow(JFrame newWindow) {
		
		if (guiStack.size() != 0){
		guiStack.peek().setVisible(false);
		}
		guiStack.push(newWindow);
		
		System.out.println(guiStack.size());
	}
	
	public static void closeWindow() {
		guiStack.pop().setVisible(false);
		guiStack.peek().setVisible(true);

	}

}
