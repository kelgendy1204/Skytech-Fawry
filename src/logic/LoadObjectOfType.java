package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadObjectOfType<T> {
	
	private File loadFromFile;
	
	public LoadObjectOfType(File loadFromFile) {
		this.loadFromFile = loadFromFile;
	}
	
	@SuppressWarnings("unchecked")
	public T loadObject() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(loadFromFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		T loadedObject = (T) ois.readObject();
		ois.close();
		return loadedObject;
	}
}
