package operationspanel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import toolbar.ToolBar_OperationsValueTextFieldHandling;

public class Operations_DeleteRows {
	private JTable operationsPanel_Table;
	private Operations_TableModel operations_TableModel;
	private double oldValue;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;
	
	public Operations_DeleteRows(JTable operationsPanel_Table, Operations_TableModel operations_TableModel, ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling ) {
		this.operationsPanel_Table = operationsPanel_Table;
		this.operations_TableModel = operations_TableModel;
		oldValue = getOldValue();
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
	}
	
	public void delete(){
		if (operationsPanel_Table.getSelectedRow() != -1 && operationsPanel_Table.getSelectedRow() < operations_TableModel.operationsList.size()) {
			int row = operationsPanel_Table.getSelectedRow();
			if (operations_TableModel.operationsList.get(row).isAccomplished()) {
				toolBar_OperationsValueTextFieldHandling
						.operationValueChangedWhenRowDeleted(oldValue);
			}
			operations_TableModel.operationsList.remove(row);
			operations_TableModel.fireTableRowsDeleted(operationsPanel_Table.getSelectedRow(),operationsPanel_Table.getSelectedRow());
			if (row == operations_TableModel.operationsList.size()) {
				operationsPanel_Table.getSelectionModel().setSelectionInterval(row - 1, row - 1 );
			} else {
				operationsPanel_Table.getSelectionModel().setSelectionInterval(row, row);
			}
			
			try {
				new Robot().keyPress(KeyEvent.VK_CANCEL);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			
			operations_TableModel.fireOperationsNumbersChangedFrom(row);
			operations_TableModel.saveToFile();
		}
	}
	
	public double getOldValue() {
		int row = operationsPanel_Table.getSelectedRow();
		if (row != -1) {
			int col = 2;
			return (double) operationsPanel_Table.getValueAt(row, col);
		}
		return 0;
	}
}
