package nsfdb.gui;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import nsfdb.gui.views.FamilyTreeListView;
import nsfdb.gui.views.MonkeyDataView;
import nsfdb.gui.views.ScanImageView;
import nsfdb.gui.views.ScansView;
import nsfdb.gui.views.View;
import nsfdb.gui.views.interactive.InteractiveFamilyTreeView;

public class SplitPaneViewManager extends ViewManager{
	private static final long serialVersionUID = 1L;
	View familyTree; 
	View monkeyData; 
	View imageScans; 
	View scans;
	JSplitPane detailSplitPane, mainSplitPane, scanSplitPane;
	JTabbedPane tabbedPane;
	public SplitPaneViewManager() {
		setLayout(new BorderLayout());
		detailSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		scanSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);


		mainSplitPane.setDividerLocation(500);

		monkeyData = new View(); 

		tabbedPane = new JTabbedPane();
		
		mainSplitPane.setLeftComponent(tabbedPane);
		mainSplitPane.setRightComponent(detailSplitPane);
		detailSplitPane.setBottomComponent(scanSplitPane);
		add(mainSplitPane, BorderLayout.CENTER);

		
		
		setView(FamilyTreeListView.generate());

	}
	
	

	public void setView(View v) {
		super.setView(v);
		if(v instanceof FamilyTreeListView) {
			if(familyTree != null) {
				familyTree.cleanup();
			}
			familyTree = v;
			tabbedPane.removeAll();
			tabbedPane.addTab("Map", ((FamilyTreeListView) familyTree).getInteractiveMap());
			tabbedPane.addTab("List", familyTree);

			
		}else if(v instanceof MonkeyDataView) {
			if(monkeyData != null) {
				monkeyData.cleanup();
			}
			monkeyData = v;
			detailSplitPane.setTopComponent(monkeyData);

		}else if(v instanceof ScanImageView) {
			if(imageScans != null) {
				imageScans.cleanup();
			}
			imageScans = v;
			scanSplitPane.setRightComponent(imageScans);
		}else if(v instanceof ScansView) {
			if(scans != null) {
				scans.cleanup();
			}
			scans = v;
			scanSplitPane.setLeftComponent(scans);
		}else {
			// error
		}
		
		detailSplitPane.setDividerLocation(300);
		construct();
repaint();
	}
	
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
