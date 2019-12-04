package nsfdb.main;

import javax.swing.SwingUtilities;

import nsfdb.gui.Window;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Window window = new Window();
			}
		});

	}

}
