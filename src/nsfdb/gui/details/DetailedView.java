package nsfdb.gui.details;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import nsfdb.gui.ImageLoader;
import nsfdb.gui.nodes.MonkeyNode;

public class DetailedView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static MonkeyNode selectedMonkey;
	
	JSplitPane splitpane;
	ImageScans imageScans;
	
	public DetailedView() {
		imageScans = new ImageScans();
		imageScans.setImages(ImageLoader.loadFolder("src/images"));
		setLayout(new BorderLayout());

		splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JPanel(), imageScans);
		splitpane.setDividerLocation(300);

		add(splitpane);
		setBackground(new Color(30,30,30,255));
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (selectedMonkey != null) {
			g2d.drawString("Selected Monkey: " + selectedMonkey.getSubjectCode(), 10, 15);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
