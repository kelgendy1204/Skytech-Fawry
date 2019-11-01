package operationspanel;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import toolbar.ToolBar_OperationsValueTextFieldHandling;

public class Operations_TextFieldKeyListener extends KeyAdapter {
	private Operations_TableModel operations_TableModel; 
	private JTextField operationsPanel_OperationTextField; 
	private JTextField operationsPanel_ValueTextField; 
	private JCheckBox operationsPanel_AccomplisedCheckBox;
	private JTable operationsPanel_Table;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;
	
	public Operations_TextFieldKeyListener(Operations_TableModel operations_TableModel 
	,JTextField operationsPanel_OperationTextField 
	,JTextField operationsPanel_ValueTextField 
	,JCheckBox operationsPanel_AccomplisedCheckBox
	,JTable operationsPanel_Table
	,ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling) {
		this.operations_TableModel = operations_TableModel; 
		this.operationsPanel_OperationTextField = operationsPanel_OperationTextField; 
		this.operationsPanel_ValueTextField = operationsPanel_ValueTextField; 
		this.operationsPanel_AccomplisedCheckBox = operationsPanel_AccomplisedCheckBox ;
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
		this.operationsPanel_Table = operationsPanel_Table;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		String operation;
		double value;
		boolean isAccomplished;
		operation = operationsPanel_OperationTextField.getText();
		try {
			value = Double.parseDouble(operationsPanel_ValueTextField.getText());
		} catch (NumberFormatException e1) {
			value = 0;
		}
		isAccomplished = operationsPanel_AccomplisedCheckBox.isSelected();
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() != InputEvent.CTRL_MASK){
			operations_TableModel.addOperation(operation, value, isAccomplished);
			operations_TableModel.refresh();
			operations_TableModel.saveToFile();
			handlingOperationsValuesTextField();
			operationsPanel_Table.scrollRectToVisible(operationsPanel_Table.getCellRect(operationsPanel_Table.getRowCount()-1, 0, true));
			operationsPanel_OperationTextField.setText("");
			operationsPanel_ValueTextField.setText("");
			operationsPanel_AccomplisedCheckBox.setSelected(false);
			operationsPanel_OperationTextField.requestFocusInWindow();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() == InputEvent.CTRL_MASK){
			operations_TableModel.addOperation(operation, value, isAccomplished);
			operations_TableModel.refresh();
			operations_TableModel.saveToFile();
			handlingOperationsValuesTextField();
			operationsPanel_Table.scrollRectToVisible(operationsPanel_Table.getCellRect(operationsPanel_Table.getRowCount()-1, 0, true));
			operationsPanel_OperationTextField.requestFocusInWindow();
		}
	}
	
	private void handlingOperationsValuesTextField() {
		int row = operations_TableModel.operationsList.size() - 1;
		int col = 2;
		double value = (double) operationsPanel_Table.getValueAt(row, col);
		if (operations_TableModel.operationsList.get(row).isAccomplished()) {
			toolBar_OperationsValueTextFieldHandling
					.operationValueChangedWhenRowAdded(value);
		}
	}
}
