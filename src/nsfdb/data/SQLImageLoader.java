package nsfdb.data;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import nsfdb.gui.nodes.MonkeyNode;

public class SQLImageLoader extends ScanImageLoader {
	Connection connection = null;
	Statement statement = null;

	public SQLImageLoader() {

	}

	public ArrayList<BufferedImage> loadImages(MonkeyNode monkey, String scanID) {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

		String query = "SELECT ImageBlob FROM ImageSeries WHERE ScanID="+scanID+";";

		ResultSet rs = SQLDatabase.queryDatabase(Queries.address, Queries.images);

		try {
			while (rs.next()) {
				BufferedImage im = ImageIO.read(rs.getBinaryStream(1));
				images.add(im);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return images;
	}

	
}
