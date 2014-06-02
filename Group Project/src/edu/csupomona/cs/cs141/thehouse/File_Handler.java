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
	
	public void fileLander(String choice, Grid grid) {
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
				saveFile(grid);
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
	private void saveFile(Grid grid) throws IOException {
		fos = new FileOutputStream(SAVE_FILE);
		oos = new ObjectOutputStream(fos);
		GameObject[][] gog = grid.getGOG();
		
		for (int i = 0; i <gog.length; i++) {
			for (int j = 0; j < gog[i].length; j++) {
				oos.writeObject(grid.getObjectAtLocation(i, j));
			}
		}
	}
}
