package logic;

import javax.swing.JTextField;

import operationspanel.Operation;
import operationspanel.Operations_TableModel;

public class StartingAndEndingCreditsHandeler {


	private JTextField toolBar_BeginningWithLabelTextField;
	private JTextField toolBar_CreditsTransferedLabelTextField;
	private JTextField toolBar_CreditsYouHaveLabelTextField;
	private Operations_TableModel operations_TableModel;

	public StartingAndEndingCreditsHandeler(JTextField toolBar_BeginningWithLabelTextField,JTextField toolBar_CreditsTransferedLabelTextField, JTextField toolBar_CreditsYouHaveLabelTextField, Operations_TableModel operations_TableModel) {

		this.toolBar_BeginningWithLabelTextField = toolBar_BeginningWithLabelTextField;
		this.toolBar_CreditsYouHaveLabelTextField = toolBar_CreditsYouHaveLabelTextField;
		this.toolBar_CreditsTransferedLabelTextField = toolBar_CreditsTransferedLabelTextField;
		this.operations_TableModel = operations_TableModel;
	}

	public void calculateCreditsYouHave(){
		toolBar_CreditsYouHaveLabelTextField.setText(null);
		if(toolBar_BeginningWithLabelTextField.getText() != null){
			if (NumbersHandling.isDouble(toolBar_BeginningWithLabelTextField.getText())) {
				double startingCreditsValue = Double.parseDouble(toolBar_BeginningWithLabelTextField.getText());
				double creditsTransfered = Double.parseDouble(toolBar_CreditsTransferedLabelTextField.getText());
				double creditsYouHave = startingCreditsValue - creditsTransfered;
				toolBar_CreditsYouHaveLabelTextField.setText(NumbersHandling.decimalFormat.format(creditsYouHave));
			} 
		} 
	}

	public void setStartingCredits(String startingCredits) {
		toolBar_CreditsYouHaveLabelTextField.setText(null);
		if (startingCredits != null) {
			if (NumbersHandling.isDouble(startingCredits)) {
				toolBar_BeginningWithLabelTextField.setText(NumbersHandling.decimalFormat.format(Double.parseDouble(startingCredits)));
				Operation.setStartingCredits(NumbersHandling.decimalFormat.format(Double.parseDouble(startingCredits)));
				operations_TableModel.saveToFile();
				calculateCreditsYouHave();
			} 
		} 
	}

}
