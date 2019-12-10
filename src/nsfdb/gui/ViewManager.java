package nsfdb.gui;

import javax.swing.JPanel;

import nsfdb.gui.views.View;
/**
 * Boundary entity for interaction with system. responsible for constructing
 * window, menu bar, and split panes
 */
public abstract class ViewManager extends JPanel{
	public ViewManager() {
		//construct();
	}


	public abstract void construct();
	public  void setView(View v) {
		v.parent = this;
	}
}
