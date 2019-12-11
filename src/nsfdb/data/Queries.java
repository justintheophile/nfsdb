package nsfdb.data;

public class Queries {
	public static final String sql_monkeys_url = "jdbc:sqlserver://;servername=cssql\\sqldata;"
			+ "user=NsfDbDev;password=nDsBf1;" + "databaseName=NSFResearch;";

	public static final String local_monkeys_url = "jdbc:ucanaccess://.\\src\\local\\local_database.accdb";

	private static final String select_family = "SELECT * FROM Family22Subjects_1 WHERE FamilyID=&v0 ORDER BY SequenceId ASC";
	private static final String select_images = "SELECT ImageBlob FROM ImageSeries WHERE ScanID=&v0";
	private static final String select_scans = "SELECT * FROM Scans WHERE SequenceID=&v0";



	/**
	 * Used to insert data into queries for specific situations. replacement taags
	 * in form of '&v#' where '#' is an integer
	 * 
	 * @param template the query template
	 * @param args     data to be inserted into the query
	 * @return processed query string
	 */
	private static String createQuery(String template, String... args) {
		String query = template;
		for (int i = 0; i < args.length; i++) {
			query = query.replace("&v" + i, args[i]);
		}
		return query;
	}

	public static String getFamilyQuery(String familyID) {
		return createQuery(select_family, familyID);
	}
	
	public static String getScanImageQuery(String scanID) {
		return createQuery(select_images, scanID);
	}
	
	public static String getScansQuery(String sequenceID) {
		return createQuery(select_scans, sequenceID);
	}
}
