package nsfdb.main;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import nsfdb.gui.Window;

public class Main {
	
	public static void main(String[] args) {
		  SwingUtilities.invokeLater(new Runnable() {

		        @Override
		        public void run() {
		    		Window window  = new Window();

		        }
		    });

	}

}
