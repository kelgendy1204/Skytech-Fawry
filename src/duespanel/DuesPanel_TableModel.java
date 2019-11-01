package duespanel;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import toolbar.ToolBar_DuesValuesJTextFieldHandling;
import logic.ListSwitchPositions;
import logic.LoadObjectOfType;
import logic.SaveObjectOfType;

@SuppressWarnings("serial")
public class DuesPanel_TableModel extends AbstractTableModel {

	public LinkedList<DueContact> duesContactsList;
	private String[] colNames = { "«·«”„", "ﬁÌ„… «·«Ã·", "«· «—ÌŒ" , "„·«ÕŸ« ", "„·«ÕŸ«  «÷«›Ì…" };
	private LoadObjectOfType<LinkedList<DueContact>> loot;
	private SaveObjectOfType<LinkedList<DueContact>> soot;
	private final File duesContactsFile = new File("dpf\\Contacts.stf");
	private ListSwitchPositions listSwitchPositions;
	private boolean isCellEditable;
	private JTable duesPanel_Table;
	private ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling;
	
	
	public DuesPanel_TableModel() {
		duesContactsList = new LinkedList<DueContact>();
		soot = new SaveObjectOfType<LinkedList<DueContact>>(duesContactsFile);
		loot = new LoadObjectOfType<LinkedList<DueContact>>(duesContactsFile);
		isCellEditable = false;
	}

	public void addContact(String name, double number, String relatedTo){
		DueContact dueContact = new DueContact(name, number, relatedTo);
		duesContactsList.add(dueContact);
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return duesContactsList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return (String) duesContactsList.get(row).getName();
		case 1:
			return (Double) duesContactsList.get(row).getValue();
		case 2:
			return (String) duesContactsList.get(row).getDate();
		case 3:
			return (String) duesContactsList.get(row).getRelatedTo();
		case 4:
			return (String) duesContactsList.get(row).getNotes();
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
			duesContactsFile.getParentFile().mkdir();
			soot.saveObject(duesContactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile() {
		try {
			duesContactsList.clear();
			duesContactsList = loot.loadObject();
			this.refresh();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			this.saveToFile();
			e.printStackTrace();
		}
	}

	public void setCellEditable(boolean isCellEditable) {
		this.isCellEditable = isCellEditable;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		
		switch (col) {
		case 2:
			return false;
		default:
			return isCellEditable;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		if (rowIndex != duesContactsList.size()) {
			DueContact dueContact = duesContactsList.get(rowIndex);
			switch (columnIndex) {
			case 0:
				dueContact.setName((String) aValue);
				break;
				
			case 1:
				double oldValue = (double) duesPanel_Table.getValueAt(rowIndex, columnIndex);
				
				try {
					double newValue = Double.parseDouble((String) aValue);
					dueContact.setValue(newValue);
					toolBar_DuesValuesJTextFieldHandling.dueValueChangedWhenRowEdited(oldValue, newValue);
				} catch (Exception e) { 
				}
				break;
				
			case 3:
				dueContact.setRelatedTo((String) aValue);
				break;
				
			case 4:
				dueContact.setNotes((String) aValue);
				break;
				
			default:
				return;
			}
		}
		this.saveToFile();

	}

	public void switchUp (DueContact dueContact){
		listSwitchPositions.switchUp(this.duesContactsList, dueContact);
	}

	public void switchDown (DueContact dueContact){
		listSwitchPositions.switchUp(this.duesContactsList, dueContact);
	}

	public JTable getDuesPanel_Table() {
		return duesPanel_Table;
	}

	public void setDuesPanel_Table(JTable duesPanel_Table) {
		this.duesPanel_Table = duesPanel_Table;
	}

	public ToolBar_DuesValuesJTextFieldHandling getToolBar_DuesValuesJTextFieldHandling() {
		return toolBar_DuesValuesJTextFieldHandling;
	}

	public void setToolBar_DuesValuesJTextFieldHandling(
			ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling) {
		this.toolBar_DuesValuesJTextFieldHandling = toolBar_DuesValuesJTextFieldHandling;
	}

}
