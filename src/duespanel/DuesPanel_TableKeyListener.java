package duespanel;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class DuesPanel_TableKeyListener extends KeyAdapter {
	
	private JTabbedPane tabbedPane;
	private JTextField operationsPanel_OperationTextField;
	
	public DuesPanel_TableKeyListener(JTabbedPane tabbedPane,JTextField operationsPanel_OperationTextField) {
		this.operationsPanel_OperationTextField = operationsPanel_OperationTextField;
		this.tabbedPane = tabbedPane;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && e.getModifiers() == InputEvent.CTRL_MASK ){
			tabbedPane.setSelectedIndex(0);
			operationsPanel_OperationTextField.requestFocusInWindow();
		}
		
	}
	
}
