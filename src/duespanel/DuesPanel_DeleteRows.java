package duespanel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import toolbar.ToolBar_DuesValuesJTextFieldHandling;

public class DuesPanel_DeleteRows {

	private DuesPanel_TableModel duesPanel_TableModel;
	private JTable duesPanel_Table;
	private double oldValue;
	private ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling;
	
	public DuesPanel_DeleteRows(JTable duesPanel_Table, DuesPanel_TableModel duesPanel_TableModel, ToolBar_DuesValuesJTextFieldHandling toolBar_DuesValuesJTextFieldHandling) {
		this.duesPanel_Table = duesPanel_Table;
		this.duesPanel_TableModel = duesPanel_TableModel;
		oldValue = getOldValue();
		this.toolBar_DuesValuesJTextFieldHandling = toolBar_DuesValuesJTextFieldHandling;
	}
	
	public void delete(){
		if (duesPanel_Table.getSelectedRow() != -1 && duesPanel_Table.getSelectedRow() < duesPanel_TableModel.duesContactsList.size()) {
			int row = duesPanel_Table.getSelectedRow();
			duesPanel_TableModel.duesContactsList.remove(row);
			duesPanel_TableModel.fireTableRowsDeleted(duesPanel_Table.getSelectedRow(),duesPanel_Table.getSelectedRow());
			if (row == duesPanel_TableModel.duesContactsList.size()) {
				duesPanel_Table.getSelectionModel().setSelectionInterval(row - 1, row - 1);
			} else {
				duesPanel_Table.getSelectionModel().setSelectionInterval(row, row);
			}
			
			try {
				new Robot().keyPress(KeyEvent.VK_CANCEL);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			
			duesPanel_TableModel.saveToFile();
			toolBar_DuesValuesJTextFieldHandling.dueValueChangedWhenRowDeleted(oldValue);
		}
	}
	
	public double getOldValue() {
		int row = duesPanel_Table.getSelectedRow();
		if (row != -1) {
			int col = 1;
			return (double) duesPanel_Table.getValueAt(row, col);
		}
		return 0;
	}
}
