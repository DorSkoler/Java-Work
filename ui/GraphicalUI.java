package ui;

import javax.swing.JOptionPane;

import interfaces.Messageable;

public class GraphicalUI implements Messageable{

	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public String getString(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	public int getInteger(String msg) {
		return Integer.parseInt(JOptionPane.showInputDialog(msg));
	}

	public String getNextLine(String msg) {
		return getString(msg);
	}
}
