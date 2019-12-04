package nsfdb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nsfdb.gui.nodes.MonkeyNode;
import nsfdb.gui.views.FamilyTreeView;

public class SQLDatabaseController extends DatabaseController {

	public SQLDatabaseController() {

	}

	public static ResultSet queryDatabase(String serverAddress, String query) {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
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
		return rs;
	}

	public ArrayList<MonkeyNode> getData(Object... params) {
		// Declare the JDBC objects.

		ResultSet rs = queryDatabase(Queries.address, Queries.family1);
		ArrayList<MonkeyNode> monkeys = new ArrayList<MonkeyNode>();

		if (rs != null) {
			FamilyTreeView tree = (FamilyTreeView) params[0];

			ResultSetMetaData meta = null;
			int columns;
			try {
				meta = rs.getMetaData();
				columns = meta.getColumnCount();

				while (rs.next()) {
					String fullData = "";

					for (int i = 1; i <= columns; i++) {
						String columnData = rs.getString(i);
						fullData += columnData + (i == columns ? "" : ",");
					}
					MonkeyNode monkey = new MonkeyNode(tree, fullData.split(","));
					monkeys.add(monkey);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return monkeys;

	}
}
