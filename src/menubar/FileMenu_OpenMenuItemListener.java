package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import logic.HandlingOperationsFileName;
import logic.StartingAndEndingCreditsHandeler;
import operationspanel.Operation;
import operationspanel.Operations_TableModel;
import toolbar.ToolBar_OperationsValueTextFieldHandling;

public class FileMenu_OpenMenuItemListener implements ActionListener {
	
	private HandlingOperationsFileName handlingOperationsFile;
	private Operations_TableModel operations_TableModel;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;
	private StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler;
	private JPanel operationsPanel;

	
	public FileMenu_OpenMenuItemListener(JPanel operationsPanel, HandlingOperationsFileName handlingOperationsFile, Operations_TableModel operations_TableModel, ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling, StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler) {
		this.handlingOperationsFile = handlingOperationsFile;
		this.operations_TableModel = operations_TableModel;
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
		this.startingAndEndingCreditsHandeler = startingAndEndingCreditsHandeler;
		this.operationsPanel = operationsPanel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		handlingOperationsFile.setOperationsFileNameFromFileChooserToController();
		operations_TableModel.setController(handlingOperationsFile.controller);
		operations_TableModel.loadFromFile();
		toolBar_OperationsValueTextFieldHandling.loadOperationsValues();
        startingAndEndingCreditsHandeler.setStartingCredits(Operation.getStartingCredits());
        operationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Operations - ( " + operations_TableModel.getFileName() +" )" ));
	}

}
