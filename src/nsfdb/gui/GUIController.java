package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.alee.laf.WebLookAndFeel;

import nsfdb.gui.details.DetailedView;
/**
 * Boundary entity for interaction with system. responsible for constructing
 * window, menu bar, and split panes
 */
public class GUIController extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final Font font = new Font("Courier", Font.BOLD, 12);
	private  NSFMenuBar menuBar;
	public static FamilyTree familyTree = new FamilyTree();
	public static DetailedView detailedView = new DetailedView();
	public GUIController() {
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
		JPanel familyData = familyTree.getPanel();
		JPanel speciesData = detailedView; 
		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, familyData, speciesData);
		mainSplitPane.setPreferredSize(new Dimension(1280, 720));
		mainSplitPane.setDividerLocation(300);

		return mainSplitPane;
	}

}
