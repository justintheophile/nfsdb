package nsfdb.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class ImageLoader {
	public abstract ArrayList<BufferedImage> loadImages(Object... param);
}
