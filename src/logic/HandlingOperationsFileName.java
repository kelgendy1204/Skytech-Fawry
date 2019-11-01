package logic;

import gui.MainFrame;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import operationspanel.Operations_FileChooser;
import operationspanel.Operations_TableModel;
import controller.Controller;

public class HandlingOperationsFileName{
	private Operations_FileChooser operations_FileChooser;
	private MainFrame parent;
	public Controller controller;
	private final File controllerFile = new File("controller.prf");
	private LoadObjectOfType<Controller> loot;
	private SaveObjectOfType<Controller> soot;
	private Operations_TableModel operations_TableModel;
	
	public HandlingOperationsFileName(MainFrame parent) {
		this.parent = parent;
		operations_FileChooser = new Operations_FileChooser();
		soot = new SaveObjectOfType<Controller>(controllerFile);
		loot = new LoadObjectOfType<Controller>(controllerFile);
		controller = new Controller();
	}

	public String getOperationsFileNameFromController() {
		return controller.getFileName();
	}

	public void setOperationsFileNameToController(String operationsFileName) {
		controller.setFileName(operationsFileName);
		saveController();
	}
	
	public void setOperationsFileNameFromFileChooserToController() {
		if(operations_FileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			String operationsFileName = operations_FileChooser.getSelectedFile().getName();
			if (operationsFileName.length() > 4) {
				controller.setFileName(operationsFileName.substring(0,
						operationsFileName.length() - 4));
				saveController();
			}
		};
	}
	
	public void saveController() {
		try {
			soot.saveObject(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadController(){
		try {
			controller = loot.loadObject();
		} catch (Exception e) {
			operations_TableModel.operationsList.clear();
			operations_TableModel.setController(new Controller());
			operations_TableModel.saveToFile();
			operations_TableModel.refresh();
			saveController();
			e.printStackTrace();
		}
	}
	
	public void returnToDefaultController() {
		controller.getDefaultFileName();
		saveController();
	}
	
	public void setOperations_TableModel(
			Operations_TableModel operations_TableModel) {
		this.operations_TableModel = operations_TableModel;
	}
	
}
