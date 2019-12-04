package nsfdb.data;

import java.util.ArrayList;

import nsfdb.gui.views.FamilyTreeView;

public abstract class DatabaseController {
	protected String serverAddress = "";
	
	public void setSeverAddress(String address) {
		this.serverAddress = address;
	}
	public abstract ArrayList<?> getData(Object... param);
}
