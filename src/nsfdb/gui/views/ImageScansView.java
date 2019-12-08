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

import nsfdb.data.FileImageLoader;
import nsfdb.data.ScanImageLoader;
import nsfdb.data.Queries;
import nsfdb.data.SQLImageLoader;
import nsfdb.gui.nodes.MonkeyNode;

public class ImageScansView extends View {
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedImage> images;
	private int currentImage;
	JLabel indicator;
	MonkeyNode monkey;
	private ImageScansView(MonkeyNode monkey) {
		this.monkey = monkey;
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
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (images != null) {
			currentImage %= images.size();

			if (images.size() > 0 && currentImage < images.size()) {
				BufferedImage cImage = images.get(currentImage);

				indicator.setText((currentImage + 1) + "/" + images.size());
				
				int w = cImage.getWidth();
				int h = cImage.getHeight();

				
				
				g2d.drawImage(cImage, getWidth() / 2 - w / 2, 0, w, h, null);
			}
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
	
	public static ImageScansView generate(MonkeyNode monkey) {
		ImageScansView scansView = new ImageScansView(monkey);
		ScanImageLoader imageLoader = null;
		if(Queries.databaseSrc == Queries.Source.FILE) {
			imageLoader = new FileImageLoader();
		}else {
			imageLoader = new SQLImageLoader();
		}
		
		scansView.setImages(imageLoader.loadImages(monkey, ""));
		
		return scansView;
	}
	
}


class ImageGallery{
	
}