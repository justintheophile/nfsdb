package nsfdb.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import net.ucanaccess.complex.Attachment;
import nsfdb.data.containers.Monkey;
import nsfdb.data.containers.Scan;
import nsfdb.gui.views.FamilyTreeView;

public class LocalDatabase extends Database {
	private static ResultSet rs = null;
	private static Connection con = null;
	private static Statement stmt = null;

	public static ResultSet queryDatabase(String serverAddress, String query) {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (Exception e) {
			}
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {
			}
		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(serverAddress);

			// Create and execute an SQL statement that returns some data.
			String SQL = query;
			stmt = con.createStatement();
			stmt.setQueryTimeout(1);
			rs = stmt.executeQuery(SQL);
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ArrayList<Monkey> getFamilyData(FamilyTreeView tree, String familyID) {
		// Declare the JDBC objects.

		ResultSet rs = queryDatabase(Queries.local_monkeys_url, Queries.getFamilyQuery(familyID));
		ArrayList<Monkey> monkeys = new ArrayList<Monkey>();

		if (rs != null) {
			ResultSetMetaData meta = null;
			int columns;
			try {
				meta = rs.getMetaData();
				columns = meta.getColumnCount();

				while (rs.next()) {
					String fullData = rs.getString("SequenceID") + "," + rs.getString("SubjectCode") + ","
							+ rs.getString("Gender") + "," + rs.getString("BirthYear") + "," + rs.getString("DeathYear")
							+ "," + rs.getString("MotherID") + "," + rs.getString("Generation") + ","
							+ rs.getString("FamilyID") + "," + rs.getString("SiblingNum") + ",";

					Monkey monkey = new Monkey(tree, fullData.split(","));
					tree.addMonkey(monkey);
					monkeys.add(monkey);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return monkeys;

	}

	@Override
	public ArrayList<Scan> getScanData(Monkey monkey) {
		ArrayList<Scan> scans = new ArrayList<Scan>();
		ResultSet rs = LocalDatabase.queryDatabase(Queries.local_monkeys_url,
				Queries.getScansQuery(monkey.getSequenceID()));

		if (rs != null) {
			ResultSetMetaData meta = null;
			int columns;
			try {
				meta = rs.getMetaData();
				columns = meta.getColumnCount();

				while (rs.next()) {

					Scan scan = new Scan(rs.getString("ScanID"), rs.getString("SequenceID"), rs.getString("Title"),
							rs.getString("Description"), rs.getString("Date"));
					scans.add(scan);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return scans;
	}

	@Override
	public ArrayList<BufferedImage> getScanImages(String scanID) {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		ResultSet rs = LocalDatabase.queryDatabase(Queries.local_monkeys_url, Queries.getScanImageQuery(scanID));
		try {
			rs.next();

			Attachment[] atts = (Attachment[]) rs.getObject("ImageBlob");
			for (Attachment att : atts) {
				ByteArrayInputStream bis = new ByteArrayInputStream(att.getData());
				BufferedImage im = ImageIO.read(bis);
				images.add(im);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return images;
	}

}
