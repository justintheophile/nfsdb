package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.alee.laf.WebLookAndFeel;

import nsfdb.gui.views.DetailedView;
import nsfdb.gui.views.FamilyTreeView;
/**
 * Boundary entity for interaction with system. responsible for constructing
 * window, menu bar, and split panes
 */
public class ViewManager {
	public static FamilyTreeView familyTree = new FamilyTreeView();
	public static DetailedView detailedView = new DetailedView();
	
	public ViewManager() {
	}


	public JSplitPane constructContentArea() {
		JPanel familyData = FamilyTreeView.generate("SELECT * FROM dbo.Family22Subjects_1 \n ORDER BY SequenceId ASC");
		JPanel speciesData = detailedView; 
		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, familyData, speciesData);
		mainSplitPane.setPreferredSize(new Dimension(1280, 720));
		mainSplitPane.setDividerLocation(300);

		return mainSplitPane;
	}

}
