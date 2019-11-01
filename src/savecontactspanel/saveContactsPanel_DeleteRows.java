package savecontactspanel;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JTable;


public class saveContactsPanel_DeleteRows {

	private JTable saveContactsPanel_Table;
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;
	
	
	public saveContactsPanel_DeleteRows(JTable table, SaveContactsPanel_TableModel saveContactsPanel_TableModel ) {
		this.saveContactsPanel_Table = table;
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
	}
	
	public void delete(){
		if (saveContactsPanel_Table.getSelectedRow() != -1 && saveContactsPanel_Table.getSelectedRow() < saveContactsPanel_TableModel.savedContactsList.size()) {
			int row = saveContactsPanel_Table.getSelectedRow();
//			SavedContact savedContact = saveContactsPanel_TableModel.savedContactsList.get(row);
//			saveContactsPanel_TableModel.savedContactsMap.remove(savedContact.getName());
			saveContactsPanel_TableModel.savedContactsList.remove(row);
			saveContactsPanel_TableModel.fireTableRowsDeleted(saveContactsPanel_Table.getSelectedRow(),saveContactsPanel_Table.getSelectedRow());
			if (row == saveContactsPanel_TableModel.savedContactsList.size()) {
				saveContactsPanel_Table.getSelectionModel().setSelectionInterval(row - 1, row - 1 );
			} else {
				saveContactsPanel_Table.getSelectionModel().setSelectionInterval(row, row);
			}
			
			try {
				new Robot().keyPress(KeyEvent.VK_CANCEL);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			saveContactsPanel_TableModel.saveToFile();
		}
	}

}
