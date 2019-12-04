package nsfdb.data;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SQLImageLoader extends ImageLoader {
	Connection connection = null;
	Statement statement = null;

	public SQLImageLoader() {

	}

	public ArrayList<BufferedImage> loadImages(Object... param) {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

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
