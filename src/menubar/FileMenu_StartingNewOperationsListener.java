package menubar;

import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.HandlingOperationsFileName;
import logic.StartingAndEndingCreditsHandeler;
import operationspanel.Operations_TableModel;
import toolbar.ToolBar_OperationsValueTextFieldHandling;

public class FileMenu_StartingNewOperationsListener implements ActionListener {

	private HandlingOperationsFileName handlingOperationsFile;
	private MainFrame parent;
	private StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler;
	private Operations_TableModel operations_TableModel;
	private JPanel operationsPanel;
	private JTextField toolBar_BeginningWithLabelTextField;
	private ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling;



	public FileMenu_StartingNewOperationsListener(ToolBar_OperationsValueTextFieldHandling toolBar_OperationsValueTextFieldHandling, JTextField toolBar_BeginningWithLabelTextField, StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler,
			HandlingOperationsFileName handlingOperationsFile, MainFrame parent,
			Operations_TableModel operations_TableModel, JPanel operationsPanel) {
		this.startingAndEndingCreditsHandeler = startingAndEndingCreditsHandeler;
		this.handlingOperationsFile = handlingOperationsFile;
		this.parent = parent;
		this.operations_TableModel = operations_TableModel;
		this.operationsPanel = operationsPanel;
		this.toolBar_BeginningWithLabelTextField = toolBar_BeginningWithLabelTextField;
		this.toolBar_OperationsValueTextFieldHandling = toolBar_OperationsValueTextFieldHandling;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(parent, "Are you sure you want to start new operations file?", "New operations file", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION){
			String fileName = operations_TableModel.handleNewOperationsFileName();
			handlingOperationsFile.setOperationsFileNameToController(fileName);
			operations_TableModel.setController(handlingOperationsFile.controller);
			operations_TableModel.operationsList.clear();
			operations_TableModel.saveToFile();
			operations_TableModel.refresh();
			toolBar_BeginningWithLabelTextField.setText(null);
			toolBar_OperationsValueTextFieldHandling.loadOperationsValues();
			String startingCredits = JOptionPane.showInputDialog(parent, "Remove the previous operations and enter the starting credits : ", "Starting Credits", JOptionPane.PLAIN_MESSAGE );
			startingAndEndingCreditsHandeler.setStartingCredits(startingCredits);
			operationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Operations - ( " + operations_TableModel.getFileName() +" )" ));
		}
	}

}
