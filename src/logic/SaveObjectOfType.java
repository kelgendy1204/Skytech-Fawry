package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveObjectOfType<T> {
	
	private File saveInFile;
	
	public SaveObjectOfType(File saveInFile) {
		this.saveInFile = saveInFile;
	}
	
	public void saveObject(T objectToSave) throws IOException{
		FileOutputStream fos = new FileOutputStream(saveInFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(objectToSave);
		oos.close();
	}
}
