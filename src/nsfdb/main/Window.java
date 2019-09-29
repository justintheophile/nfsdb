package nsfdb.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.alee.extended.label.WebStyledLabel;
import com.alee.laf.WebLookAndFeel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	Font font = new Font("Arial", Font.BOLD, 12);

	/**
	 * Boundary entity for interaction with system. responsible for constructing
	 * window, menu bar, and split panes
	 */
	public Window() {
		WebLookAndFeel.install();
		setTitle("NSF Database Terminal");
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(constructMenuBar(), BorderLayout.NORTH);
	}

	public JMenuBar constructMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		WebStyledLabel titleLabel = new WebStyledLabel("   {NSFDB Terminal:b}");
		menuBar.add(titleLabel);

		/*File Menu*/
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("Access file options.");
		menuBar.add(fileMenu);
		
		/* Help menu */
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("View program information.");
		
		JMenuItem aboutMenuItem = new JMenuItem("About",
                KeyEvent.VK_A);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		KeyEvent.VK_1, ActionEvent.ALT_MASK));
		aboutMenuItem.getAccessibleContext().setAccessibleDescription(
		"About this program");
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(helpMenu);

		return menuBar;
	}
	


}
