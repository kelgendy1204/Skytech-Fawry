package toolbar;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JTextField;

import logic.NumbersHandling;
import duespanel.DueContact;
import duespanel.DuesPanel_TableModel;

public class ToolBar_DuesValuesJTextFieldHandling {
	private double duesValueSummation;
	private ArrayList<Double> duesValuesList;
	private DuesPanel_TableModel duesPanel_TableModel;
	private JTextField toolBar_DuesValuesTextField;
	
	public ToolBar_DuesValuesJTextFieldHandling(JTextField toolBar_DuesValuesTextField, DuesPanel_TableModel duesPanel_TableModel) {
		this.duesPanel_TableModel = duesPanel_TableModel;
		this.toolBar_DuesValuesTextField = toolBar_DuesValuesTextField;
		duesValuesList = new ArrayList<Double>();
		duesValueSummation = 0;
	}
	
	public void loadDuesValues() {
		buildValuesList(duesPanel_TableModel.duesContactsList);
		firstCalculationOfDuesValue();
		refreshDueValuesTextField();
	}
	
	private void firstCalculationOfDuesValue(){
		for (Double dueValue : duesValuesList) {
			duesValueSummation += dueValue;
		}
	}
	
	private void buildValuesList(LinkedList<DueContact> dueContactsList){
		for (DueContact dueContact : dueContactsList) {
			double dueContactValue = dueContact.getValue();
			duesValuesList.add(dueContactValue);
		}
	}
	
	public void dueValueChangedWhenRowEdited(double oldDuesValue, double newDuesValue){
		duesValueSummation = duesValueSummation - oldDuesValue;
		duesValueSummation = duesValueSummation + newDuesValue;
		refreshDueValuesTextField();
	}
	
	public void dueValueChangedWhenRowDeleted(double oldDuesValue){
		duesValueSummation = duesValueSummation - oldDuesValue;
		refreshDueValuesTextField();
	}
	
	public void dueValueChangedWhenRowAdded(double newDuesValue){
		duesValueSummation = duesValueSummation + newDuesValue;
		refreshDueValuesTextField();
	}
	
	private void refreshDueValuesTextField() {
//		toolBar_DuesValuesTextField.setText(Double.toString(duesValueSummation));
		toolBar_DuesValuesTextField.setText(NumbersHandling.decimalFormat.format(duesValueSummation));
	}
}
