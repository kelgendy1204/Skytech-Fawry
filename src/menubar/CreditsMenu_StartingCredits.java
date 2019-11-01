package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logic.StartingAndEndingCreditsHandeler;

public class CreditsMenu_StartingCredits implements ActionListener {

	private StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler;
	private JFrame parentComponent;
	
	public CreditsMenu_StartingCredits(JFrame parentComponent, StartingAndEndingCreditsHandeler startingAndEndingCreditsHandeler) {
		this.startingAndEndingCreditsHandeler = startingAndEndingCreditsHandeler;
		this.parentComponent = parentComponent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String startingCreditsString = JOptionPane.showInputDialog(parentComponent, "Enter the starting credits : ","Starting Credits",  JOptionPane.PLAIN_MESSAGE);
		startingAndEndingCreditsHandeler.setStartingCredits(startingCreditsString);
		
	}

}
