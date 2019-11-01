package duespanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import toolbar.ToolBar_DuesValuesJTextFieldHandling;

public class DuesPanel_AddContactButtonListener implements ActionListener {

	private DuesPanel_TableModel duesPanel_TableModel;
	private JTextField duesPanel_PersonNameTextField;
	private JTextField duesPanel_ValueTextField;
	private JTextField duesPanel_RelatedToTextField;
	private JTable duesPanel_Table;
	private ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling;
	
	public DuesPanel_AddContactButtonListener(DuesPanel_TableModel duesPanel_TableModel, 
			JTextField duesPanel_PersonNameTextField, JTextField duesPanel_ValueTextField, JTextField duesPanel_RelatedToTextField,
			ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling, JTable duesPanel_Table) {
		this.duesPanel_TableModel = duesPanel_TableModel;
		this.duesPanel_PersonNameTextField = duesPanel_PersonNameTextField;
		this.duesPanel_ValueTextField = duesPanel_ValueTextField;
		this.duesPanel_RelatedToTextField = duesPanel_RelatedToTextField;
		this.duesPanel_Table = duesPanel_Table;
		this.toolBar_DuesValuesJTextFieldHandling = toolBar_DuesValuesJTextFieldHandling;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name;
		double number;
		String relatedTo;
		name = duesPanel_PersonNameTextField.getText();
		try {
			number = Double.parseDouble(duesPanel_ValueTextField.getText());
		} catch (NumberFormatException e1) {
			number = 0;
		}
		relatedTo = duesPanel_RelatedToTextField.getText();
		duesPanel_TableModel.addContact(name, number, relatedTo);
		duesPanel_TableModel.refresh();
		duesPanel_TableModel.saveToFile();
		handlingDueValuesTextField();
		duesPanel_PersonNameTextField.setText("");
		duesPanel_ValueTextField.setText("");
		duesPanel_RelatedToTextField.setText("");
		duesPanel_Table.scrollRectToVisible(duesPanel_Table.getCellRect(duesPanel_Table.getRowCount()-1, 0, true));
	}

	private void handlingDueValuesTextField() {
		int row = duesPanel_TableModel.duesContactsList.size() - 1;
		int col = 1;
		double value = (double) duesPanel_Table.getValueAt(row, col);
		toolBar_DuesValuesJTextFieldHandling.dueValueChangedWhenRowAdded(value);
	}

}
