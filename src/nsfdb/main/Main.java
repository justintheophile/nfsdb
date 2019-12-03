package nsfdb.main;

import javax.swing.SwingUtilities;

import nsfdb.data.SQLDatabaseController;
import nsfdb.gui.GUIController;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIController window = new GUIController();
			}
		});

	}

}
