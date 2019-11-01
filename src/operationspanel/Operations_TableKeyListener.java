package operationspanel;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;

import toolbar.ToolBar_OperationsValueTextFieldHandling;

public class Operations_TableKeyListener extends KeyAdapter {
	private JTable operationsPanel_Table;
	private Operations_TableModel operations_TableModel;
	private JTextField operationsPanel_OperationTextField;
//	private JTextField operationsPanel_ValueTextField;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;
	
	public Operations_TableKeyListener(JTable operationsPanel_Table
	,JTextField operationsPanel_OperationTextField
	,Operations_TableModel operations_TableModel
	/*, JTextField operationsPanel_ValueTextField*/
	,ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling) {
		this.operationsPanel_Table = operationsPanel_Table;
		this.operations_TableModel = operations_TableModel;
		this.operationsPanel_OperationTextField = operationsPanel_OperationTextField;
//		this.operationsPanel_ValueTextField = operationsPanel_ValueTextField;
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() == InputEvent.CTRL_MASK ){
			int row = operationsPanel_Table.getSelectedRow();
			operationsPanel_OperationTextField.setText(operations_TableModel.operationsList.get(row).getOperation());
		}
		
//		if(e.getKeyCode() == KeyEvent.VK_SPACE && e.getModifiers() == InputEvent.CTRL_MASK ){
//			operationsPanel_OperationTextField.setText("");
//			operationsPanel_ValueTextField.setText("");
//			operationsPanel_OperationTextField.requestFocusInWindow();
//		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			int row = operationsPanel_Table.getSelectedRow();
			boolean accomplished = operations_TableModel.operationsList.get(row).isAccomplished();
			double operationValue = (double) operationsPanel_Table.getValueAt(row, 2);
			operations_TableModel.operationsList.get(row).setAccomplished(!accomplished);
			if (operations_TableModel.operationsList.get(row).isAccomplished() == true) {
				toolBar_OperationsValueTextFieldHandling.operationValueChangedWhenRowAdded(operationValue);
			} else {
				toolBar_OperationsValueTextFieldHandling.operationValueChangedWhenRowDeleted(operationValue);
			}
			operations_TableModel.fireOperationsNumbersChangedFrom(row);
			operations_TableModel.refresh();
			operations_TableModel.saveToFile();
			operationsPanel_Table.getSelectionModel().setSelectionInterval(row, row);
		}
		
	}
}
