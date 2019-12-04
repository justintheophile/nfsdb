package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;

import com.alee.laf.WebLookAndFeel;

public class Window extends JFrame {
	private static final Font font = new Font("Courier", Font.BOLD, 12);

	public Window() {
		WebLookAndFeel.install();

		ViewManager viewManager = new ViewManager();
		
		WebLookAndFeel.install();
		setTitle("NSF Database Terminal");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(new MenuBar(), BorderLayout.NORTH);
		add(viewManager.constructContentArea(), BorderLayout.CENTER);
		repaint();
	}
}
