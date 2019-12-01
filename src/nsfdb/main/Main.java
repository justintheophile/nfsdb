package nsfdb.main;

import javax.swing.SwingUtilities;

import nsfdb.gui.GUIController;
import nsfdb.sql.SQLDatabaseController;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIController window = new GUIController();
			}
		});

	}

}
