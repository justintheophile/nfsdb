package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import com.alee.extended.label.WebStyledLabel;
import com.alee.laf.WebLookAndFeel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	Font font = new Font("Arial", Font.BOLD, 12);
	NSFMenuBar menuBar;

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
		add(menuBar = new NSFMenuBar(), BorderLayout.NORTH);
		add(constructContentArea(), BorderLayout.CENTER);
	}


	public JSplitPane constructContentArea() {
		JPanel familyData = new JPanel(); // replace with specialized class
		JPanel speciesData = new JPanel(); // replace with specialized class

		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, familyData, speciesData);
		return mainSplitPane;
	}

}
