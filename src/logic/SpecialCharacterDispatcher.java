package logic;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class SpecialCharacterDispatcher implements KeyEventDispatcher {

	private JComponent component;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JTable tableToSelect;
	private char specialCharacter = '`';
	private char specialCharacter2 = 'Ð';
	private char specialCharacter3 = '*';
	private JCheckBox checkboxToToggle;


	public SpecialCharacterDispatcher(JTabbedPane tabbedPane, JPanel panel, JComponent component, JTable tableToSelect, JCheckBox checkboxToToggle) {
		this.component = component;
		this.tabbedPane = tabbedPane;
		this.panel = panel;
		this.tableToSelect = tableToSelect;
		this.checkboxToToggle = checkboxToToggle;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getKeyChar() == specialCharacter || e.getKeyChar() == specialCharacter2) {
			e.consume();
		}

		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyChar() == specialCharacter || e.getKeyChar() == specialCharacter2) {
				tabbedPane.setSelectedComponent(panel);
				component.requestFocusInWindow();
				return true;
			}
		} 

		if (e.getKeyChar() == specialCharacter3) {
			e.consume();
		}

		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyChar() == specialCharacter3) {
				if(tabbedPane.getSelectedIndex() == 0){
					
					if (checkboxToToggle.isSelected()) {
						checkboxToToggle.setSelected(false);
					} else {
						checkboxToToggle.setSelected(true);					
					}
					
					return true;
				}
			}
		} 

		if (e.getKeyCode() == KeyEvent.VK_DOWN && e.isAltDown()) {
			e.consume();
		}

		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN && e.isAltDown()) {
				if(tabbedPane.getSelectedIndex() == 0){
					selectLastItemAdded();
					return true;
				}
			}
		} 

		return false;
	}

	private void selectLastItemAdded() {
		tableToSelect.requestFocus();
		tableToSelect.setRowSelectionInterval(tableToSelect.getModel().getRowCount() - 1, tableToSelect.getModel().getRowCount() - 1);
		tableToSelect.setColumnSelectionInterval(tableToSelect.getModel().getColumnCount() - 1, tableToSelect.getModel().getColumnCount() - 1);
		tableToSelect.scrollRectToVisible(tableToSelect.getCellRect(tableToSelect.getRowCount()-1, 0, true));
	}

}
