package nsfdb.main;

import javax.swing.SwingUtilities;

import nsfdb.gui.GUIController;
import nsfdb.sql.DatabaseController;

public class Main {
	
	public static void main(String[] args) {
		
		  SwingUtilities.invokeLater(new Runnable() {

		        @Override
		        public void run() {
		    		GUIController window  = new GUIController();

		        }
		    });

	}

}
