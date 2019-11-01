package operationspanel;

import java.io.Serializable;

import logic.DateFormats;


public class Operation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6361277997053882919L;
	private String operationNumber;
	private String operation;
	private double value;
	private boolean accomplished;
	private String time;
	private String date;
	private String notes = "";
	private static String startingCredits;
	
	public Operation(String operation, double value, boolean accomplished) {
		this.operation = operation;
		this.value = value;
		this.accomplished = accomplished;
		time = DateFormats.getinnerSimpleDateFormat();
		date = DateFormats.getouterSimpleDateFormat();
	}

	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	public boolean isAccomplished() {
		return accomplished;
	}
	
	public void setAccomplished(boolean accomplished) {
		this.accomplished = accomplished;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOperationNumber() {
		return operationNumber;
	}

	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	public static String getStartingCredits() {
		return startingCredits;
	}

	public static void setStartingCredits(String startingCredits) {
		Operation.startingCredits = startingCredits;
	}
}
