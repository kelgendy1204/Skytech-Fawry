package toolbar;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JTextField;

import logic.NumbersHandling;
import logic.StartingAndEndingCreditsHandeler;
import operationspanel.Operation;
import operationspanel.Operations_TableModel;

public class ToolBar_OperationsValueTextFieldHandling {
	private double operationsValueSummation;
	private ArrayList<Double> operationsValuesList;
	private Operations_TableModel operations_TableModel;
	private JTextField toolBar_OperationsValueTextField;
	private StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler;
	
	public ToolBar_OperationsValueTextFieldHandling(JTextField toolBar_OperationsValueTextField, Operations_TableModel operations_TableModel, StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler) {
		this.operations_TableModel = operations_TableModel;
		this.toolBar_OperationsValueTextField = toolBar_OperationsValueTextField;
		this.startingAndEndingCreditsHandeler = startingAndEndingCreditsHandeler;
		operationsValuesList = new ArrayList<Double>();
		operationsValueSummation = 0;
	}
	
	public void loadOperationsValues() {
		buildValuesList(operations_TableModel.operationsList);
		firstCalculationOfOperationsValue();
		refreshOperationsValuesTextField();
		startingAndEndingCreditsHandeler.calculateCreditsYouHave();
	}
	
	private void firstCalculationOfOperationsValue(){
		operationsValueSummation = 0;
		for (Double operationValue : operationsValuesList) {
			operationsValueSummation += operationValue;
		}
	}
	
	private void buildValuesList(LinkedList<Operation> operationsList){
		operationsValuesList.clear();
		int i = -1;
		for (Operation operation : operationsList) {
			i++;
			if (operations_TableModel.operationsList.get(i).isAccomplished()) {
				double operationValue = operation.getValue();
				operationsValuesList.add(operationValue);
			}
		}
	}
	
	public void operationValueChangedWhenRowEdited(double oldOperationValue, double newOperationValue){
		operationsValueSummation = operationsValueSummation - oldOperationValue;
		operationsValueSummation = operationsValueSummation + newOperationValue;
		refreshOperationsValuesTextField();
		startingAndEndingCreditsHandeler.calculateCreditsYouHave();
	}
	
	public void operationValueChangedWhenRowDeleted(double oldOperationValue){
		operationsValueSummation = operationsValueSummation - oldOperationValue;
		refreshOperationsValuesTextField();
		startingAndEndingCreditsHandeler.calculateCreditsYouHave();
	}
	
	public void operationValueChangedWhenRowAdded(double newOperationValue){
		operationsValueSummation = operationsValueSummation + newOperationValue;
		refreshOperationsValuesTextField();
		startingAndEndingCreditsHandeler.calculateCreditsYouHave();
	}
	
	private void refreshOperationsValuesTextField() {
		toolBar_OperationsValueTextField.setText(NumbersHandling.decimalFormat.format(operationsValueSummation));
	}
}
