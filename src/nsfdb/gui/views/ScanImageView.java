package nsfdb.gui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jinteractive.main.GraphicsPanel;

import nsfdb.data.Database;
import nsfdb.data.SourceController;

public class ScanImageView extends View {
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedImage> images;
	private int currentImage;
	JLabel indicator;
	ImagePanel panel;

	public ScanImageView() {
		JPanel controls = new JPanel();
		controls.setFocusable(false);
		controls.setBackground(Color.gray);
		JButton nextImage = new JButton(">");
		JButton previousImage = new JButton("<");
		indicator = new JLabel("0/0");
		controls.add(previousImage, BorderLayout.WEST);
		controls.add(indicator, BorderLayout.CENTER);

		controls.add(nextImage, BorderLayout.EAST);

		add(controls, BorderLayout.SOUTH);

		nextImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextImage();
			}
		});

		previousImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				previousImage();
			}
		});

		panel = new ImagePanel(this);
		add(panel, BorderLayout.CENTER);
		Runnable run = new Runnable() {
			public void run() {
				panel.gameloop();
			}
		};

		new Thread(run).start();

	}

	public void tick() {
		repaint();
		currentImage %= images.size();
		if(images != null) {
			indicator.setText((currentImage + 1) + "/" + images.size());
		}
	}

	public void setImages(ArrayList<BufferedImage> images) {
		this.images = images;
		currentImage = 0;
		tick();
	}

	public void nextImage() {
		currentImage++;
		tick();
	}

	public void previousImage() {
		currentImage--;
		tick();
	}

	public BufferedImage getImage() {
		if (images != null) {
			return images.get(currentImage);
		}
		return null;
	}

	public static ScanImageView generate(String scanID) {
		ScanImageView scansView = new ScanImageView();
		Database database = SourceController.getNewDataSource();

		scansView.setImages(database.getScanImages(scanID));

		return scansView;
	}

	public void cleanup() {
		super.cleanup();
		panel.stop();
	}
} 

class ImagePanel extends GraphicsPanel {
	private static final long serialVersionUID = 1L;
	ScanImageView parent;

	public ImagePanel(ScanImageView parent) {
		super(60);
		this.parent = parent;
		drawGrid = false; 
		
	}

	public void draw(Graphics2D g2d) {
		if (parent.getImage() != null) {
			g2d.drawImage(parent.getImage(), 0, 0, null);
		}
	}

	@Override
	public void drawGui(Graphics2D arg0) {

	}

	@Override
	public void init() {

	}

	@Override
	public void update(float arg0) {

	}

}