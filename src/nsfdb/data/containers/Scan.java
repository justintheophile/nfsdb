package nsfdb.data.containers;

public class Scan {
	String scanID, sequenceID, title, description, date;

	public Scan(String scanID, String sequenceID, String title, String description, String date) {
		this.scanID = scanID;
		this.sequenceID = sequenceID;
		this.title = title;
		this.description = description;
		this.date = date;
	}

	public String getColumn(int index) {
		switch (index) {
		case 0:
			return scanID;
		case 1:
			return sequenceID;
		case 2:
			return title;
		case 3:
			return description;
		case 4:
			return date;
		}
		return "stat not found";
	}
}
