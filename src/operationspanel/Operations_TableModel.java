package operationspanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controller.Controller;
import logic.NumbersHandling;
import logic.DateFormats;
import logic.HandlingOperationsFileName;
import logic.ListSwitchPositions;
import toolbar.ToolBar_OperationsValueTextFieldHandling;

@SuppressWarnings("serial")
public class Operations_TableModel extends AbstractTableModel {

	public LinkedList<Operation> operationsList;
	private String[] colNames = {"—ﬁ„ «·⁄„·Ì…", "«·⁄„·Ì…", "«·ﬁÌ„…", "⁄„·Ì… ‰«ÃÕ…", "«· «—ÌŒ", "«·Êﬁ ", "„·«ÕŸ« " };
	private LoadObjectOfType<LinkedList<Operation>> loot;
	private SaveObjectOfType<LinkedList<Operation>> soot;
	private ListSwitchPositions listSwitchPositions;
	private boolean isCellEditable;
	private final String path = "operations\\";
	private final String extension = ".ops";
	private StringBuilder dynamicFileName;
	private StringBuilder fileFullPath;
	private File dynamicOperationsFile;
	private JTable operationsPanel_Table;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;
	private Controller controller;
	private HandlingOperationsFileName handlingOperationsFileName;

	public Operations_TableModel(Controller controller, HandlingOperationsFileName handlingOperationsFileName) {
		dynamicFileName = new StringBuilder();
		fileFullPath = new StringBuilder();
		setController(controller);
		operationsList = new LinkedList<Operation>();
		isCellEditable = false;
		this.handlingOperationsFileName = handlingOperationsFileName;
	}

	public void addOperation (String operationType, double value, boolean accomplished){
		Operation operation = new Operation(operationType, value, accomplished);
		operationsList.add(operation);
		operation.setOperationNumber(manipulateOperationsNumbers(operationsList.size() - 1));
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return operationsList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return operationsList.get(row).getOperationNumber();
		case 1:
			return (String) operationsList.get(row).getOperation();
		case 2:
			return (Double) operationsList.get(row).getValue();
		case 3:
			return (boolean) operationsList.get(row).isAccomplished();
		case 4:
			return (String) operationsList.get(row).getDate();
		case 5:
			return (String) operationsList.get(row).getTime();
		case 6:
			return (String) operationsList.get(row).getNotes();
		default:
			return null;
		}
	}

	public String getColumnName(int column) {
		return colNames[column];
	}

	public void refresh(){
		this.fireTableDataChanged();
	}

	public void saveToFile() {
		try {
			dynamicOperationsFile.getParentFile().mkdir();
			soot.saveObject(operationsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile() {
		try {
			operationsList.clear();
			operationsList = loot.loadObject();
			this.refresh();
		} catch (Exception e) {
			setController(new Controller());
			this.saveToFile();
			this.refresh();
			handlingOperationsFileName.returnToDefaultController();
			handlingOperationsFileName.saveController();
			e.printStackTrace();
		}
	}

	public void setCellEditable(boolean isCellEditable) {
		this.isCellEditable = isCellEditable;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 0:
			return false;
		case 1:
			return isCellEditable;
		case 2:
			return isCellEditable;
		case 3:
			return true;
		case 4:
			return false;
		case 5:
			return false;
		case 6:
			return isCellEditable;
		default:
			return false;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		if (rowIndex != operationsList.size()) {
			Operation operation = operationsList.get(rowIndex);
			switch (columnIndex) {
			case 1:
				operation.setOperation((String) aValue);
				break;
				
			case 2:
				double oldValue = (double) operationsPanel_Table.getValueAt(rowIndex, columnIndex);
				
				try {
					double newValue = Double.parseDouble((String) aValue);
					operation.setValue(newValue);
					if (this.operationsList.get(rowIndex).isAccomplished()) {
						toolBar_OperationsValueTextFieldHandling
								.operationValueChangedWhenRowEdited(oldValue,
										newValue);
					}
				} catch (NumberFormatException e) {
				}
				break;

			case 3:
				double operationValue = (double) operationsPanel_Table.getValueAt(rowIndex, 2);
				try {
					operation.setAccomplished((boolean) aValue);
					if (operation.isAccomplished() == true) {
						toolBar_OperationsValueTextFieldHandling.operationValueChangedWhenRowAdded(operationValue);
					} else {
						toolBar_OperationsValueTextFieldHandling.operationValueChangedWhenRowDeleted(operationValue);
					}
					fireOperationsNumbersChangedFrom(rowIndex);
					this.refresh();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 6:
				operation.setNotes((String) aValue);
				break;
				
			default:
				break;
			}
		}
		this.saveToFile();

	}

	public void switchUp (Operation operation){
		listSwitchPositions.switchUp(this.operationsList, operation);
	}

	public void switchDown (Operation operation){
		listSwitchPositions.switchUp(this.operationsList, operation);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {		
		case 3:
			return Boolean.class;		
		default:
			return super.getColumnClass(columnIndex);
		}
	}
	
	public void fireOperationsNumbersChangedFrom(int rowChanged){
		for(int row = rowChanged; row < operationsList.size() ;  row++){
			if (rowChanged != -1) {
				operationsList.get(row).setOperationNumber(manipulateOperationsNumbers(row));
			}
		}
	}
	
	private String manipulateOperationsNumbers(int row) {
		switch (row) {
		case 0:
			if (operationsList.get(row).isAccomplished() == false) {
				return new String("");
			} else {
				return new String("1");
			}
			
		default:
			if(operationsList.get(row).isAccomplished() == true){
				int rowIndex = row - 1;
				while (!NumbersHandling.isInteger((String)this.getValueAt(rowIndex, 0))) {
					if(rowIndex != 0){
						rowIndex--;
					}else {
						return "1";
					}
				}
				int value = Integer.parseInt((String)this.getValueAt(rowIndex, 0)) + 1;
				return Integer.toString(value);
			}else {
				return new String("");
			}
		}
	}

	public JTable getOperationsPanel_Table() {
		return operationsPanel_Table;
	}

	public void setOperationsPanel_Table(JTable operationsPanel_Table) {
		this.operationsPanel_Table = operationsPanel_Table;
	}

	public ToolBar_OperationsValueTextFieldHandling getToolBar_OperationsValueTextFieldHandling() {
		return toolBar_OperationsValueTextFieldHandling;
	}

	public void setToolBar_OperationsValueTextFieldHandling(
			ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling) {
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
	}
	
	public void setFileName(String fileName) {
		dynamicFileName.delete(0, dynamicFileName.length());
		this.dynamicFileName.append(fileName);
	}
	
	public String getFileName(){
		return dynamicFileName.toString();
		
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
		dynamicFileName.delete(0, dynamicFileName.length());
		dynamicFileName.append(this.controller.getFileName());
		fileFullPath.delete(0, fileFullPath.length());
		fileFullPath.append(path + dynamicFileName.toString() + extension);
		dynamicOperationsFile = new File(fileFullPath.toString());
		setSootAndLoot(dynamicOperationsFile);
	}
	
	private void setSootAndLoot(File dynamicOperationsFile){
		soot = new SaveObjectOfType<LinkedList<Operation>>(dynamicOperationsFile);
		loot = new LoadObjectOfType<LinkedList<Operation>>(dynamicOperationsFile);
	}
	
	public String handleNewOperationsFileName(){
		String fileNameBeforeHandling = DateFormats.getouterSimpleDateFormat();
		StringBuilder st = new StringBuilder(fileNameBeforeHandling);
		File operationsFile = new File(path + st.toString() + extension);
		
		for(int i = 1;;i++){
			if(operationsFile.exists()){
				st = new StringBuilder(fileNameBeforeHandling);
				st.append(" - " + i);
				operationsFile = new File(path + st.toString() + extension);
			} else {
				break;
			}
		}
		return st.toString();
	}

}

class SaveObjectOfType<T> {

	private File saveInFile;

	public SaveObjectOfType(File saveInFile) {
		this.saveInFile = saveInFile;
	}

	public void saveObject(T objectToSave) throws IOException{
		FileOutputStream fos = new FileOutputStream(saveInFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(objectToSave);
		PrintStream pst = new PrintStream(oos);
		pst.append(Operation.getStartingCredits());
		oos.close();
		pst.close();
	}
}

class LoadObjectOfType<T> {

	private File loadFromFile;

	public LoadObjectOfType(File loadFromFile) {
		this.loadFromFile = loadFromFile;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public T loadObject() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(loadFromFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		T loadedObject = (T) ois.readObject();
		Operation.setStartingCredits(ois.readLine());
		ois.close();
		return loadedObject;
	}
	
}
