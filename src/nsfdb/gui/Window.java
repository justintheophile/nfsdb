package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.alee.laf.WebLookAndFeel;
/**
 * Boundary entity for interaction with system. responsible for constructing
 * window, menu bar, and split panes
 */
public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Font font = new Font("Arial", Font.BOLD, 12);
	NSFMenuBar menuBar;

	public Window() {
		WebLookAndFeel.install();
		setTitle("NSF Database Terminal");
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(menuBar = new NSFMenuBar(), BorderLayout.NORTH);
		add(constructContentArea(), BorderLayout.CENTER);
		repaint();
	}


	public JSplitPane constructContentArea() {
		JPanel familyData = new FamilyTree().getPanel();
		JPanel speciesData = new JPanel(); // replace with specialized class
		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, familyData, speciesData);
		mainSplitPane.setPreferredSize(new Dimension(1280, 720));
		mainSplitPane.setDividerLocation(300);

		return mainSplitPane;
	}

}
