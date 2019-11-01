package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import operationspanel.Operations_DeleteRows;
import operationspanel.Operations_TableModel;
import duespanel.DuesPanel_DeleteRows;
import duespanel.DuesPanel_TableModel;
import savecontactspanel.SaveContactsPanel_TableModel;
import savecontactspanel.saveContactsPanel_DeleteRows;
import toolbar.ToolBar_DuesValuesJTextFieldHandling;
import toolbar.ToolBar_OperationsValueTextFieldHandling;

public class EditMenu_RemoveRowListener implements ActionListener {

	private JTabbedPane tabbedPane;
	private JTable saveContactsPanel_Table;
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;
	private DuesPanel_TableModel duesPanel_TableModel;
	private JTable duesPanel_Table;
	private ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;
	private JTable operationsPanel_Table;
	private Operations_TableModel operations_TableModel;

	public EditMenu_RemoveRowListener(JTabbedPane tabbedPane, JTable saveContactsPanel_Table, SaveContactsPanel_TableModel saveContactsPanel_TableModel,
			JTable duesPanel_Table, DuesPanel_TableModel duesPanel_TableModel, ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling,
			JTable operationsPanel_Table,Operations_TableModel operations_TableModel, ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling) {
		this.tabbedPane = tabbedPane;
		this.saveContactsPanel_Table = saveContactsPanel_Table;
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
		this.duesPanel_Table = duesPanel_Table;
		this.duesPanel_TableModel = duesPanel_TableModel;
		this.toolBar_DuesValuesJTextFieldHandling = toolBar_DuesValuesJTextFieldHandling;
		this.operationsPanel_Table = operationsPanel_Table;
		this.operations_TableModel = operations_TableModel;
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (tabbedPane.getSelectedIndex()) {
		case 0:
			new Operations_DeleteRows(operationsPanel_Table, operations_TableModel, toolBar_OperationsValueTextFieldHandling).delete();
			break;
			
		case 1:
			new DuesPanel_DeleteRows(duesPanel_Table, duesPanel_TableModel, toolBar_DuesValuesJTextFieldHandling).delete();
			break;
			
		case 2:
			new saveContactsPanel_DeleteRows(saveContactsPanel_Table, saveContactsPanel_TableModel).delete();
			break;
			
		default:
			break;
		}

	}

}
