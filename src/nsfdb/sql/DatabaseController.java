package nsfdb.sql;

import nsfdb.gui.FamilyTree;

public abstract class DatabaseController {
	protected String serverAddress = "";
	
	public void setSeverAddress(String address) {
		this.serverAddress = address;
	}
	public abstract void getData(FamilyTree tree);
}
