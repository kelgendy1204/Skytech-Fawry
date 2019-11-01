package savecontactspanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;

public class SaveContacts_TextFiledsKeyListener extends KeyAdapter {

	
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;
	private JTextField saveContactsPanel_PersonNameTextField; 
	private JTextField saveContactsPanel_PersonNumberTextField;
	private JTable saveContactsPanel_Table;
	
	public SaveContacts_TextFiledsKeyListener(JTable saveContactsPanel_Table, JTextField saveContactsPanel_PersonNameTextField,JTextField saveContactsPanel_PersonNumberTextField, SaveContactsPanel_TableModel saveContactsPanel_TableModel) {
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
		this.saveContactsPanel_PersonNameTextField = saveContactsPanel_PersonNameTextField;
		this.saveContactsPanel_PersonNumberTextField = saveContactsPanel_PersonNumberTextField;
		this.saveContactsPanel_Table = saveContactsPanel_Table;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		String name;
		String number;
		name = saveContactsPanel_PersonNameTextField.getText();
		number = saveContactsPanel_PersonNumberTextField.getText();
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			saveContactsPanel_TableModel.addContact(name, number);
			saveContactsPanel_TableModel.refresh();
			saveContactsPanel_TableModel.saveToFile();
			saveContactsPanel_PersonNameTextField.setText("");
			saveContactsPanel_PersonNumberTextField.setText("");
			saveContactsPanel_Table.scrollRectToVisible(saveContactsPanel_Table.getCellRect(saveContactsPanel_Table.getRowCount()-1, 0, true));
		}
	}

}
