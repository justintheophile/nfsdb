package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.alee.laf.WebLookAndFeel;

import nsfdb.gui.views.DetailedView;
import nsfdb.gui.views.FamilyTreeView;
import nsfdb.gui.views.ImageScansView;
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
	public abstract void setView(View v);
}
