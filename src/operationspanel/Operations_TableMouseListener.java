package operationspanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class Operations_TableMouseListener extends MouseAdapter{
	private Operations_TableModel operations_TableModel;
	private JTable operations_Table;

	public Operations_TableMouseListener(Operations_TableModel operations_TableModel,JTable operations_Table) {
		this.operations_Table = operations_Table;
		this.operations_TableModel = operations_TableModel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			operations_Table.getSelectionModel().clearSelection();
		}

		if(e.getClickCount() >= 2){
			int row = operations_Table.rowAtPoint(e.getPoint());
			int col = operations_Table.columnAtPoint(e.getPoint());
			operations_TableModel.setCellEditable(true);
			operations_Table.editCellAt(row, col);
			operations_TableModel.setCellEditable(false);
		}
	}
}
