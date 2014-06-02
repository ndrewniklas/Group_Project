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
	
	private final String SAVE_FILE = "save.objects";
	
	private final String SAVE_GRID_CLASS = "save.grid";
	
	private FileInputStream fis;
	
	private ObjectInputStream ois;
	
	private FileOutputStream fos;
	
	private ObjectOutputStream oos;
	
	private FileOutputStream fosGrid;
	
	private ObjectOutputStream oosGrid;
	
	private FileInputStream fisGrid;
	
	private ObjectInputStream oisGrid;
	
	public void fileLander(String choice, Grid grid) {
				
		switch (choice) {
		case "Open":
			try {
				openFile(grid);
			} catch (IOException | ClassNotFoundException e) {
				
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

	public void openFile(Grid grid) throws IOException, ClassNotFoundException {
		fis = new FileInputStream(SAVE_FILE);
		ois = new ObjectInputStream(fis);
		grid.overwriteGOG((GameObject[][])ois.readObject());
	}
	
	public Grid openGrid() throws IOException, ClassNotFoundException{
		fisGrid = new FileInputStream(SAVE_GRID_CLASS);
		oisGrid = new ObjectInputStream(fis);
		return (Grid)oisGrid.readObject();
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void saveFile(Grid grid) throws IOException {
		fos = new FileOutputStream(SAVE_FILE);
		oos = new ObjectOutputStream(fos);
		fosGrid = new FileOutputStream(SAVE_GRID_CLASS);
		oosGrid = new ObjectOutputStream(fos);
		GameObject[][] gog = grid.getGOG();
		oos.writeObject(gog);
		oosGrid.writeObject(grid);
	}
}
