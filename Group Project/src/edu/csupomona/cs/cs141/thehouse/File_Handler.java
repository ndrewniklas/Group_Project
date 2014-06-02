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
	
	private Grid savedGrid;
	
	private final String SAVE_FILE = "save.obj";
	
	private final String SAVE_GRID_CLASS = "save.grid";
	
	private final String SAVE_PLAYER = "save.plr";
	
	private FileInputStream fis;
	
	private ObjectInputStream ois;
	
	private FileOutputStream fos;
	
	private ObjectOutputStream oos;
	
	private FileOutputStream fosGrid;
	
	private ObjectOutputStream oosGrid;
	
	private FileInputStream fisGrid;
	
	private ObjectInputStream oisGrid;
	
	private FileOutputStream fosPlr;
	
	private ObjectOutputStream oosPlr;
	
	private FileInputStream fisPlr;
	
	private ObjectInputStream oisPlr;
	
	public void fileLander(String choice, Grid grid, Player player) {
		choice = choice.toLowerCase();
		switch (choice) {
		case "load":
			try {
				openFile(grid);
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
			}
			break;
			
		case "save":
			saveFile(grid, player);
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
	
	public Grid openGrid(){
		try{
			fisGrid = new FileInputStream(SAVE_GRID_CLASS);
			oisGrid = new ObjectInputStream(fisGrid);
			savedGrid = (Grid)oisGrid.readObject();		
		}catch(ClassNotFoundException | IOException e){
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
		return savedGrid;
	}
	
	public Player loadPlayer(){
		Player savedPlayer = null;
		try{
			fisPlr = new FileInputStream(SAVE_PLAYER);
			oisPlr = new ObjectInputStream(fisPlr);
			savedPlayer = (Player)oisPlr.readObject();		
		}catch(ClassNotFoundException | IOException e){
			System.out.println("Something went wrong loading the player!");
			e.printStackTrace();
		}
		return savedPlayer;
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void saveFile(Grid grid, Player plr){
		try{
			fos = new FileOutputStream(SAVE_FILE);
			oos = new ObjectOutputStream(fos);
			GameObject[][] gog = grid.getGOG();
			oos.writeObject(gog);
			oos.close();
			fosGrid = new FileOutputStream(SAVE_GRID_CLASS);
			oosGrid = new ObjectOutputStream(fosGrid);
			oosGrid.writeObject(grid);
			fosGrid.close();
			fosPlr = new FileOutputStream(SAVE_PLAYER);
			oosPlr = new ObjectOutputStream(fosPlr);
			oosPlr.writeObject(plr);
			fosPlr.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
