package savecontactspanel;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import logic.ListSwitchPositions;
import logic.LoadObjectOfType;
import logic.SaveObjectOfType;

@SuppressWarnings("serial")
public class SaveContactsPanel_TableModel extends AbstractTableModel {

	public LinkedList<SavedContact> savedContactsList;
//	public HashMap<String , String> savedContactsMap;
	private String[] colNames = { "«·«”„", "«·—ﬁ„", "„·«ÕŸ« " };
	private LoadObjectOfType<LinkedList<SavedContact>> loot;
	private SaveObjectOfType<LinkedList<SavedContact>> soot;
	private final File savedContactsFile = new File("scf\\Contacts.stf");
	private ListSwitchPositions listSwitchPositions;
	private boolean isCellEditable;

	public SaveContactsPanel_TableModel() {
		savedContactsList = new LinkedList<SavedContact>();
//		savedContactsMap = new HashMap<String, String>();
		soot = new SaveObjectOfType<LinkedList<SavedContact>>(savedContactsFile);
		loot = new LoadObjectOfType<LinkedList<SavedContact>>(savedContactsFile);
		isCellEditable = false;
	}

	public void addContact(String name, String number){
		SavedContact savedContact = new SavedContact(name, number);
		savedContactsList.add(savedContact);
//		addToMap(savedContact);
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return savedContactsList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return (String) savedContactsList.get(row).getName();
		case 1:
			return (String) savedContactsList.get(row).getNumber();
		case 2:
			return (String) savedContactsList.get(row).getNotes();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void refresh(){
		this.fireTableDataChanged();
	}

	public void saveToFile() {
		try {
			savedContactsFile.getParentFile().mkdir();
			soot.saveObject(savedContactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile() {
		try {
			savedContactsList.clear();
			savedContactsList = loot.loadObject();
//			savedContactsMap.clear();
//			savedContactsMap = makeMap(savedContactsList);
			this.refresh();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			this.saveToFile();
			e.printStackTrace();
		}
	}

//	private void addToMap(SavedContact savedContact){
//		savedContactsMap.put(savedContact.getName(), savedContact.getNumber());
//	}

//	private HashMap<String,String> makeMap (LinkedList<SavedContact> savedContacts) {
//		HashMap<String,String> contactsMap = new HashMap<String,String>();
//		for (SavedContact savedContact : savedContacts) {
//			contactsMap.put(savedContact.getName(), savedContact.getNumber());			
//		}
//		return contactsMap;
//	}
	
	public void setCellEditable(boolean isCellEditable) {
		this.isCellEditable = isCellEditable;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return isCellEditable;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		if (rowIndex != savedContactsList.size()) {
			SavedContact savedContact = savedContactsList.get(rowIndex);
			switch (columnIndex) {
			case 0:
				savedContact.setName((String) aValue);
				break;
			case 1:
				savedContact.setNumber((String) aValue);
				break;
			case 2:
				savedContact.setNotes((String) aValue);
				break;
			default:
				return;
			}
		}
		this.saveToFile();
			
	}
	
	public void switchUp (SavedContact savedContact){
		listSwitchPositions.switchUp(this.savedContactsList, savedContact);
	}
	
	public void switchDown (SavedContact savedContact){
		listSwitchPositions.switchUp(this.savedContactsList, savedContact);
	}

}
