package nsfdb.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class FileImageLoader extends ImageLoader {

	private static final String[] EXTENSIONS = new String[] { "gif", "png", "bmp" };
	private static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

		@Override
		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith("." + ext)) {
					return (true);
				}
			}
			return (false);
		}
	};

	/**
	 * @param 0: path (Stiring)
	 */
	public ArrayList<BufferedImage> loadImages(Object... param) {

		File dir = new File((String) param[0]);
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles(IMAGE_FILTER)) {
				BufferedImage img = null;

				try {
					img = ImageIO.read(f);

					// you probably want something more involved here
					// to display in your UI
					images.add(img);
				} catch (final IOException e) {
					// handle errors here
				}
			}
		}
		return images;
	}
}
