package duespanel;

import java.io.Serializable;

import logic.DateFormats;

public class DueContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4839260263618299712L;
	private String name;
	private double value;
	private String relatedTo;
	private String notes;
	private String date;
	
	public DueContact(String name, double value, String relatedTo) {
		this.name = name;
		this.value = value;
		this.relatedTo = relatedTo;
		this.notes = "";
		this.date = DateFormats.getFullSimpleDateFormat();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	public String getRelatedTo() {
		return relatedTo;
	}
	
	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public String getDate() {
		return date;
	}
		
}
