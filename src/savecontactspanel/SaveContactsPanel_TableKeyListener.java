package savecontactspanel;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SaveContactsPanel_TableKeyListener extends KeyAdapter {
	
	private JTable saveContactsPanel_Table;
	private JTabbedPane tabbedPane;
	private JTextField operationsPanel_OperationTextField;
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;
	
	
	public SaveContactsPanel_TableKeyListener(JTable saveContactsPanel_Table,JTabbedPane tabbedPane,JTextField operationsPanel_OperationTextField, SaveContactsPanel_TableModel saveContactsPanel_TableModel) {
		this.operationsPanel_OperationTextField = operationsPanel_OperationTextField;
		this.saveContactsPanel_Table = saveContactsPanel_Table;
		this.tabbedPane = tabbedPane;
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() == InputEvent.CTRL_MASK ){
			int row = saveContactsPanel_Table.getSelectedRow();
			tabbedPane.setSelectedIndex(0);
			operationsPanel_OperationTextField.setText(saveContactsPanel_TableModel.savedContactsList.get(row).getNumber());
		}
		
	}
	
}
