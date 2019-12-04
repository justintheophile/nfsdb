package nsfdb.data;

public class Queries {
	public static final String address = "jdbc:sqlserver://;servername=cssql\\sqldata;" + "user=NsfDbDev;password=nDsBf1;"
			+ "databaseName=NSFResearch;";
	
	public static final String family1 = "SELECT * FROM dbo.Family22Subjects_1 \n ORDER BY SequenceId ASC";
	public static final String images = "SELECT ImageBlob FROM ImageSeries";
}
