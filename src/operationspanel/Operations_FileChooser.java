package operationspanel;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Operations_FileChooser extends JFileChooser {
	
	private final static File operationDirectory=  new File ("operations\\");
	
	public Operations_FileChooser() {
		super(operationDirectory);
		this.setDialogTitle("Open operations");
		this.setFileFilter(new FileNameExtensionFilter("Operations files (*.ops)" , "ops"));
	}
	
}
