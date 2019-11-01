package duespanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class DuesPanel_TableMouseListener extends MouseAdapter {

	private DuesPanel_TableModel duesPanel_TableModel;
	private JTable duesPanel_Table;

	public DuesPanel_TableMouseListener(DuesPanel_TableModel duesPanel_TableModel,JTable duesPanel_Table) {
		this.duesPanel_Table = duesPanel_Table;
		this.duesPanel_TableModel = duesPanel_TableModel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			duesPanel_Table.getSelectionModel().clearSelection();
		}

		if(e.getClickCount() >= 2){
			int row = duesPanel_Table.rowAtPoint(e.getPoint());
			int col = duesPanel_Table.columnAtPoint(e.getPoint());
			duesPanel_TableModel.setCellEditable(true);
			duesPanel_Table.editCellAt(row, col);
			duesPanel_TableModel.setCellEditable(false);
		}
	}
	

}
