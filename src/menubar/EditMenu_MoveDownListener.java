package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import operationspanel.Operations_TableModel;
import duespanel.DuesPanel_TableModel;
import logic.ListSwitchPositions;
import savecontactspanel.SaveContactsPanel_TableModel;

public class EditMenu_MoveDownListener implements ActionListener {

	private JTabbedPane tabbedPane;
	private JTable saveContactsPanel_Table;
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;
	private ListSwitchPositions listSwitchPositions;
	private DuesPanel_TableModel duesPanel_TableModel;
	private JTable duesPanel_Table;
	private JTable operationsPanel_Table;
	private Operations_TableModel operations_TableModel;

	public EditMenu_MoveDownListener(JTabbedPane tabbedPane, JTable saveContactsPanel_Table, SaveContactsPanel_TableModel saveContactsPanel_TableModel,
			JTable duesPanel_Table, DuesPanel_TableModel duesPanel_TableModel, JTable operationsPanel_Table, Operations_TableModel operations_TableModel) {
		this.tabbedPane = tabbedPane;
		this.saveContactsPanel_Table = saveContactsPanel_Table;
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
		this.duesPanel_Table = duesPanel_Table;
		this.duesPanel_TableModel = duesPanel_TableModel;
		this.operationsPanel_Table = operationsPanel_Table;
		this.operations_TableModel = operations_TableModel;
		listSwitchPositions = new ListSwitchPositions();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (tabbedPane.getSelectedIndex()) {
		case 0:
		{
			int row = operationsPanel_Table.getSelectedRow();
			if (row != -1 && row != (operations_TableModel.operationsList.size()-1)) {
				listSwitchPositions.switchDown(
						operations_TableModel.operationsList,
						operations_TableModel.operationsList
								.get(operationsPanel_Table.getSelectedRow()));
				operations_TableModel.refresh();
				operationsPanel_Table.getSelectionModel().setSelectionInterval(row + 1, row + 1);
			}
			
			if(row == (operations_TableModel.operationsList.size()-1)) {
				operationsPanel_Table.getSelectionModel().setSelectionInterval(row , row);
			}
			operations_TableModel.fireOperationsNumbersChangedFrom(row);
			operations_TableModel.saveToFile();
		}
			break;
			
		case 1:
		{
			int row = duesPanel_Table.getSelectedRow();
			if (row != -1 && row != (duesPanel_TableModel.duesContactsList.size()-1)) {
				listSwitchPositions.switchDown(
						duesPanel_TableModel.duesContactsList,
						duesPanel_TableModel.duesContactsList
								.get(duesPanel_Table.getSelectedRow()));
				duesPanel_TableModel.refresh();
				duesPanel_TableModel.saveToFile();
				duesPanel_Table.getSelectionModel().setSelectionInterval(row + 1, row + 1);
			}
			
			if(row == (duesPanel_TableModel.duesContactsList.size()-1)) {
				duesPanel_Table.getSelectionModel().setSelectionInterval(row , row);
			}
		}
			break;
			
		case 2:
		{
			int row = saveContactsPanel_Table.getSelectedRow();
			if (row != -1 && row != (saveContactsPanel_TableModel.savedContactsList.size()-1)) {
				listSwitchPositions.switchDown(
						saveContactsPanel_TableModel.savedContactsList,
						saveContactsPanel_TableModel.savedContactsList
								.get(saveContactsPanel_Table.getSelectedRow()));
				saveContactsPanel_TableModel.refresh();
				saveContactsPanel_TableModel.saveToFile();
				saveContactsPanel_Table.getSelectionModel().setSelectionInterval(row + 1, row + 1);
			}
			
			if(row == (saveContactsPanel_TableModel.savedContactsList.size()-1)) {
				saveContactsPanel_Table.getSelectionModel().setSelectionInterval(row , row);
			}
		}
			break;
			
		default:
			break;
		}

	}

}
