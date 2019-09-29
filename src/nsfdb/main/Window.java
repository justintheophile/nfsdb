package nsfdb.main;

import javax.swing.JFrame;
import javax.swing.JMenu;

import com.alee.laf.WebLookAndFeel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Boundary entity for interaction with system.
	 * responsible for constructing window, menu bar, and
	 * split panes 
	 */
	public Window() {
		WebLookAndFeel.install ();
		setTitle("NSF Database Terminal");
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(constructMenuBar());
	}
	
	public JMenu constructMenuBar() {
		JMenu menu = new JMenu();
		return menu;
	}
	
	
}
