package nsfdb.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.alee.extended.label.WebStyledLabel;

public class MenuBar extends JMenuBar implements ActionListener{
	private static final long serialVersionUID = 1L;


	public MenuBar() {
		//WebStyledLabel titleLabel = new WebStyledLabel("   {NSFDB Terminal:b}");
		//add(titleLabel);

		/* File Menu */
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("Access file options.");
		add(fileMenu);

		/* Help menu */
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("View program information.");

		JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		aboutMenuItem.getAccessibleContext().setAccessibleDescription("About this program");
		helpMenu.add(aboutMenuItem);

		add(helpMenu);
	}

	
	public void actionPerformed(ActionEvent e) {
		
	}
}
