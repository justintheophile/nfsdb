package nsfdb.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import nsfdb.gui.nodes.MonkeyNode;

public abstract class ScanImageLoader {
	public abstract ArrayList<BufferedImage> loadImages(MonkeyNode monkey, String scanID);
}
