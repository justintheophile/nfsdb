package nsfdb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import nsfdb.gui.FamilyTree;
import nsfdb.gui.nodes.MonkeyNode;

public class SQLDatabaseController extends DatabaseController {

	public SQLDatabaseController() {
		serverAddress = "jdbc:sqlserver://;servername=cssql\\sqldata;" + "user=NsfDbDev;password=nDsBf1;"
				+ "databaseName=NSFResearch;";
	}

	public void getData(FamilyTree tree) {
		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(serverAddress);

			// Create and execute an SQL statement that returns some data.
			String SQL = "SELECT * FROM dbo.Family22Subjects_1 \n" + "ORDER BY SequenceId ASC";
			stmt = con.createStatement();
			stmt.setQueryTimeout(1);
			rs = stmt.executeQuery(SQL);
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
//			System.out.println(columns);
//			for (int i = 1; i <= columns; i++) {
//				System.out.print(meta.getColumnName(i) + ", ");
//			}
//			System.out.println();

			// Iterate through the data in the result set and display it.
			String curRow = "";

			while (rs.next()) {
				String fullData = "";

				for (int i = 1; i <= columns; i++) {
					String columnData = rs.getString(i);
					fullData += columnData + (i == columns ? "" : ",");
				}
				MonkeyNode monkey = new MonkeyNode(tree, fullData.split(","));
				tree.addMonkey(monkey);
			}
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Could not connect to database.");
			tree.addMonkey(new MonkeyNode(tree, null));
		}

		finally {
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
		}

	}
}
