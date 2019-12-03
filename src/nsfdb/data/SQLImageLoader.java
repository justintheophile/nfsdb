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

	private void setup() {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://;servername=cssql\\sqldata;" + "user=NsfDbDev;password=nDsBf1;"
				+ "databaseName=NSFStudent;";

		// Declare the JDBC objects.
		// Establish the connection.
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			connection = DriverManager.getConnection(connectionUrl);

			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<BufferedImage> loadImages(Object... param) {
		setup();
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

		ResultSet rs = null;

		try {
			// String displaySQL = "select ImageBlob from ImageTest where ImgID = 1";
			String displaySQL = "select ImageBlob from ImageSeries";
			rs = statement.executeQuery(displaySQL);
			while (rs.next()) {
				BufferedImage im = ImageIO.read(rs.getBinaryStream(1));
				images.add(im);
			}

		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
				}
		}

		return images;
	}

}
