package nsfdb.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import nsfdb.gui.nodes.MonkeyNode;

public class DetailedView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static MonkeyNode selectedMonkey;
	Timer t = new Timer(50, this);

	public DetailedView() {
		t.start();
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
