package controller;

import java.io.Serializable;

public class Controller implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8844043160047292023L;

	private StringBuilder fileName;
	
	public Controller() {
		fileName = new StringBuilder("Default");
	}
	
	public String getFileName() {
		return fileName.toString();
	}
	
	public String getDefaultFileName() {
		fileName.delete(0, fileName.length());
		fileName.append("Default");
		return fileName.toString();
	}

	public void setFileName(String fileName) {
		this.fileName.delete(0, this.fileName.length());
		this.fileName.append(fileName);
	}
	
	
}
