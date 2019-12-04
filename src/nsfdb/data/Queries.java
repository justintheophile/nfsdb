package nsfdb.data;

public class Queries {
	public static final String address = "jdbc:sqlserver://;servername=cssql\\sqldata;"
			+ "user=NsfDbDev;password=nDsBf1;" + "databaseName=NSFResearch;";
	
	public static final String excel_address = "jdbc:ucanaccess://.\\src\\test\\Database2.accdb";

	public static final String family1 = "SELECT * FROM Family22Subjects_1 \n ORDER BY SequenceId ASC";
	public static final String images = "SELECT ImageBlob FROM ImageSeries";
	
	public static enum Source{FILE, SQL};
	public static final Source databaseSrc = Source.FILE; 
}
