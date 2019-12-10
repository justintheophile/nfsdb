package nsfdb.gui;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import nsfdb.gui.views.FamilyTreeView;
import nsfdb.gui.views.MonkeyDataView;
import nsfdb.gui.views.ScanImageView;
import nsfdb.gui.views.ScansView;
import nsfdb.gui.views.View;

public class SplitPaneViewManager extends ViewManager{
	private static final long serialVersionUID = 1L;
	View familyTree; 
	View monkeyData; 
	View imageScans; 
	View scans;
	JSplitPane detailSplitPane, mainSplitPane, scanSplitPane;
	
	public SplitPaneViewManager() {
		setLayout(new BorderLayout());
		detailSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		scanSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		//detailSplitPane.setDividerLocation(300);
		detailSplitPane.setEnabled(false);

		mainSplitPane.setDividerLocation(250);

		setView(FamilyTreeView.generate());
		monkeyData = new View(); //MonkeyDataView.generate(); 
		
		//setView(ImageScansView.generate(null));

		//detailSplitPane.setTopComponent(monkeyData);
		//detailSplitPane.setBottomComponent(imageScans);
		mainSplitPane.setLeftComponent(familyTree);
		mainSplitPane.setRightComponent(detailSplitPane);
		detailSplitPane.setBottomComponent(scanSplitPane);
		add(mainSplitPane, BorderLayout.CENTER);

	}
	
	

	public void setView(View v) {
		super.setView(v);
		if(v instanceof FamilyTreeView) {
			familyTree = v;
			mainSplitPane.setLeftComponent(familyTree);
		}else if(v instanceof MonkeyDataView) {
			monkeyData = v;
			detailSplitPane.setTopComponent(monkeyData);

		}else if(v instanceof ScanImageView) {
			imageScans = v;
			scanSplitPane.setRightComponent(imageScans);
		}else if(v instanceof ScansView) {
			scans = v;
			scanSplitPane.setLeftComponent(scans);
		}else {
			// error
		}
		if(monkeyData != null) {
			//detailSplitPane.setDividerLocation(monkeyData.getHeight());

		}
		detailSplitPane.setDividerLocation(300);
		construct();
	}



	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	

}
