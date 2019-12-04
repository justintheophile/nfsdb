package nsfdb.gui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import nsfdb.data.FileImageLoader;
import nsfdb.data.ImageLoader;
import nsfdb.data.SQLImageLoader;
import nsfdb.gui.nodes.MonkeyNode;

public class DetailedView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private  MonkeyNode selectedMonkey;
	
	JSplitPane splitpane;
	ImageScans imageScans;
	ImageLoader loader;
	public DetailedView() {
		loader = new FileImageLoader();
		
		imageScans = new ImageScans();
		imageScans.setImages(loader.loadImages("src/images"));
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
	
	public void setSelectedMonkey(MonkeyNode monkey) {
		selectedMonkey = monkey;
		repaint();
	}

}
