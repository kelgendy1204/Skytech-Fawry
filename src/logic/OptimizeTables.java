package logic;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class OptimizeTables {

	
	public void optimize(JTable table){
		table.setRowHeight(table.getRowHeight() + 10);
		table.getTableHeader().setPreferredSize(new Dimension(0, 24));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
}
