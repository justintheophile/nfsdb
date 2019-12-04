package nsfdb.gui;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import nsfdb.gui.views.FamilyTreeView;
import nsfdb.gui.views.ImageScansView;
import nsfdb.gui.views.MonkeyDataView;
import nsfdb.gui.views.View;

public class SplitPaneViewManager extends ViewManager{
	private static final long serialVersionUID = 1L;
	View familyTree; 
	View monkeyData; 
	View imageScans; 
	JSplitPane detailSplitPane, mainSplitPane;
	
	public SplitPaneViewManager() {
		setLayout(new BorderLayout());
		detailSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		detailSplitPane.setDividerLocation(300);
		mainSplitPane.setDividerLocation(300);

		familyTree = new View();//FamilyTreeView.generate();
		monkeyData = new View(); //MonkeyDataView.generate(); 
		imageScans = new ImageScansView();
		
		detailSplitPane.setTopComponent(monkeyData);
		detailSplitPane.setBottomComponent(imageScans);
		mainSplitPane.setLeftComponent(familyTree);
		mainSplitPane.setRightComponent(detailSplitPane);

		add(mainSplitPane, BorderLayout.CENTER);

	}
	
	

	public void setView(View v) {
		if(v instanceof FamilyTreeView) {
			familyTree = v;
			mainSplitPane.setLeftComponent(familyTree);
		}else if(v instanceof MonkeyDataView) {
			monkeyData = v;
			detailSplitPane.setTopComponent(monkeyData);

		}else if(v instanceof ImageScansView) {
			imageScans = v;
			detailSplitPane.setBottomComponent(imageScans);

		}else {
			// error
		}
		construct();
	}
	

}
