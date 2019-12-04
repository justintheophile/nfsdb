package nsfdb.gui.nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import nsfdb.gui.views.FamilyTreeView;

public class MonkeyNode extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 1L;

	private String sequenceID = "", subjectCode = "", gender = "", birthYear = "", deathYear = "", motherID = "",
			generation = "", familyID = "", siblingNum = "";

	private MonkeyNode parent;
	private FamilyTreeView tree;

	public MonkeyNode(FamilyTreeView tree, String[] data) {
		super(data == null ? "empty" : data[1]);
		this.tree = tree;
		if (data != null) {
			setSequenceID(data[0]);
			setSubjectCode(data[1]);
			setGender(data[2]);
			setBirthYear(data[3]);
			setDeathYear(data[4]);
			setMotherID(data[5]);
			setGeneration(data[6]);
			setFamilyID(data[7]);
			setSiblingNum(data[8]);
		}

	}

	public MonkeyNode(FamilyTreeView tree) {
		this(tree, null);

	}

	public String getSequenceID() {
		return sequenceID;
	}

	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;

	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
	}

	public String getMotherID() {
		return motherID;
	}

	public void setMotherID(String motherID) {
		this.motherID = motherID;
		if (!motherID.toLowerCase().equals("null")) {
			if (parent != null) {
				parent.remove(this);
			}
			parent = tree.findMonkey((int) Float.parseFloat(motherID));
			parent.add(this);
		}
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getFamilyID() {
		return familyID;
	}

	public void setFamilyID(String familyID) {
		this.familyID = familyID;
	}

	public String getSiblingNum() {
		return siblingNum;
	}

	public void setSiblingNum(String siblingNum) {
		this.siblingNum = siblingNum;
	}

}
