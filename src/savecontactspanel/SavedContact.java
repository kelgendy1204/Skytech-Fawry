package savecontactspanel;

import java.io.Serializable;

public class SavedContact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1949766815451782192L;
	private String name;
	private String number;
	private String notes = "";
	
	public SavedContact(String name,String number) {
		this.name = name;
		this.number = number;
	}
	
	public String getName(){
		return name;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
