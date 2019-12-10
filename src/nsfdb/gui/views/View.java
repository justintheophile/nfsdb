package nsfdb.gui.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nsfdb.gui.ViewManager;

public class View extends JPanel {
	private static final long serialVersionUID = 1L;
	public ViewManager parent;

	public View() {
		super(new BorderLayout());

	}
}
