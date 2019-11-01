package savecontactspanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class SaveContactsPanel_TableMouseListener extends MouseAdapter {

	private JTable saveContactsPanel_Table;
	private SaveContactsPanel_TableModel saveContactsPanel_TableModel;

	public SaveContactsPanel_TableMouseListener(JTable saveContactsPanel_Table, SaveContactsPanel_TableModel saveContactsPanel_TableModel) {
		this.saveContactsPanel_Table = saveContactsPanel_Table;
		this.saveContactsPanel_TableModel = saveContactsPanel_TableModel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			saveContactsPanel_Table.getSelectionModel().clearSelection();
		}
		
		if(e.getClickCount() >= 2){
			int row = saveContactsPanel_Table.rowAtPoint(e.getPoint());
			int col = saveContactsPanel_Table.columnAtPoint(e.getPoint());
			saveContactsPanel_TableModel.setCellEditable(true);
			saveContactsPanel_Table.editCellAt(row, col);
			saveContactsPanel_TableModel.setCellEditable(false);
		}
	}
}
