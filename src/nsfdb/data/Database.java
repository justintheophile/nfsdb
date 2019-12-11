package nsfdb.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import nsfdb.data.containers.Monkey;
import nsfdb.data.containers.Scan;
import nsfdb.gui.views.FamilyTreeListView;

public abstract class Database {
	protected String serverAddress = "";
	
	public void setSeverAddress(String address) {
		this.serverAddress = address;
	}
	public abstract ArrayList<Monkey> getFamilyData(FamilyTreeListView tree, String familyID);
	public abstract ArrayList<Scan> getScanData(Monkey monkey);
	public abstract ArrayList<BufferedImage> getScanImages(String scanID);
}
