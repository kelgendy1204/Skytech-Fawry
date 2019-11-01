package savecontactspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

public class SaveContactsPanel_AddContactButtonListener implements ActionListener {
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;
	private JTextField saveContactsPanel_PersonNameTextField;
	private JTextField saveContactsPanel_PersonNumberTextField;
	private JTable saveContactsPanel_Table;
	
	public SaveContactsPanel_AddContactButtonListener(JTable saveContactsPanel_Table, SaveContactsPanel_TableModel saveContactsPanel_TableModel, 
			JTextField saveContactsPanel_PersonNameTextField, JTextField saveContactsPanel_PersonNumberTextField) {
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
		this.saveContactsPanel_PersonNameTextField = saveContactsPanel_PersonNameTextField;
		this.saveContactsPanel_PersonNumberTextField = saveContactsPanel_PersonNumberTextField;
		this.saveContactsPanel_Table = saveContactsPanel_Table;
	}
	
	public void actionPerformed(ActionEvent e) {
		String name;
		String number;
		name = saveContactsPanel_PersonNameTextField.getText();
		number = saveContactsPanel_PersonNumberTextField.getText();
		saveContactsPanel_TableModel.addContact(name, number);
		saveContactsPanel_TableModel.refresh();
		saveContactsPanel_TableModel.saveToFile();
		saveContactsPanel_PersonNameTextField.setText("");
		saveContactsPanel_PersonNumberTextField.setText("");
		saveContactsPanel_Table.scrollRectToVisible(saveContactsPanel_Table.getCellRect(saveContactsPanel_Table.getRowCount()-1, 0, true));

	}

}
