package nsfdb.gui.details;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageScans extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedImage> images;
	private int currentImage;
	JLabel indicator ;
	public ImageScans() {
		setLayout(new BorderLayout());

		JPanel controls = new JPanel();
		JButton nextImage = new JButton(">");
		JButton previousImage = new JButton("<");
		indicator = new JLabel ("0/0");
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
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		currentImage %= images.size();

		
		if (images.size() > 0 && currentImage < images.size()) {
			indicator.setText((currentImage+1) + "/" + images.size());
			int w = 300;
			int h = 300;
			
			g2d.drawImage(images.get(currentImage), getWidth()/2 - w/2, 0, w, h, null);
		}
	}

	public void setImages(ArrayList<BufferedImage> images) {
		this.images = images;
		currentImage = 0;
		repaint();
	}

	public void nextImage() {
		currentImage++;
		repaint();
	}

	public void previousImage() {
		currentImage--;
		repaint();
	}
}
