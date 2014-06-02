/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Andrew
 *
 */
public class File_Handler {
	
	private final String SAVE_FILE = "saveData.house";
	
	private FileInputStream fis;
	
	private ObjectInputStream ois;
	
	private FileOutputStream fos;
	
	private ObjectOutputStream oos;
	
	public void fileLander(String choice) {
		String cmd = choice;
		
		switch (choice) {
		case "Open":
			try {
				openFile();
			} catch (IOException e) {
				
			}
			break;
			
		case "Save":
			try {
				saveFile();
			} catch (IOException e) {
				
			}
			break;

		default:
			break;
		}
	}

	public void openFile() throws IOException {
		fis = new FileInputStream(SAVE_FILE);
		ois = new ObjectInputStream(fis);
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	private void saveFile() throws IOException {
		fos = new FileOutputStream(SAVE_FILE);
	}
}
