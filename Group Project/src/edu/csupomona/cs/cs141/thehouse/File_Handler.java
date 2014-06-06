/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Group Project
 *
 * The game takes place inside a building which will be represented as a grid of 81
 * squares, every square representing a possible position for different entities in
 * the game (player's avatar, enemies, power-ups), with the exception of 9 special
 * squares which represent rooms, and are equally distributed on the grid. The player
 * can perform special functions each turn and each turn the enemies move randomly.
 * The game ends if an enemy kills the player or the player finds the briefcase located
 * in one of the special rooms.
 *
 * Team BA^2KD
 * 	- Ben Nickerson
 * 	- Andrew Nipp
 * 	- Andrew Niklas
 * 	- Kurt Newcomb
 * 	- Dylan Nguyen
 */
package edu.csupomona.cs.cs141.thehouse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class deals with all interactions between the game and saving/loading
 * functions. Primarily the only objects that are saved are the {@link Grid} and
 * {@link Player}, this is also true for the objects that are loaded.
 * 
 * @author Andrew Niklas, Andrew Nipp, Ben Nickerson
 * 
 */
public class File_Handler {

	/**
	 * This field represents a {@link Grid} object in its state from the time
	 * that "save" is selected by the user in the {@link GameEngine#setOption}
	 * method.
	 */
	private Grid savedGrid;

	/**
	 * This field represents the name of the save file for the
	 * {@link GameObject}s
	 */
	private final String SAVE_FILE = "save.obj";

	/**
	 * This field represents the name of the file for the {@link Grid} object
	 */
	private final String SAVE_GRID_CLASS = "save.grid";

	/**
	 * This field represents the name of the save file for the {link Player}
	 * object.
	 */
	private final String SAVE_PLAYER = "save.plr";

	/**
	 * This field instantiates the {@link FileInputStream}
	 */
	private FileInputStream fis;

	/**
	 * This field instantiates the {@link ObjectInputStream}
	 */
	private ObjectInputStream ois;

	/**
	 * This field instantiates the {@link FileOutputStream}
	 */
	private FileOutputStream fos;

	/**
	 * This field instantiates the {@link ObjectOutputStream}
	 */
	private ObjectOutputStream oos;

	/**
	 * This method is called when either the user saves or loads a game. It does
	 * this by collecting a {@code String} with the value of "save" or "load".
	 * "Save" saves the {@link Grid} and {@link Player} objects by passing them
	 * to {@link #saveFile(Grid, Player)}. "Load" loads the {@link Grid} by
	 * calling {@link #openFile(Grid)}.
	 * 
	 * @param choice
	 *            {@code String} with value of "save" or "load"
	 * @param grid
	 *            {@link Grid} {@link #savedGrid} that will be saved or
	 *            overwritten
	 * @param player
	 *            {@link Player} in its state from the point that the game is
	 *            saved
	 */
	public void fileLander(String choice, Grid grid, Player player) {
		choice = choice.toLowerCase();
		switch (choice) {
		case "load":
			try {

				openFile(grid);

				System.out.println("Loaded the game!");
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Something went wrong");
				// e.printStackTrace();
				noFileError();
			}
			break;
		case "save":
			saveFile(grid, player);
			System.out.println("Saved the game!");
			break;

		default:
			break;
		}
	}

	/**
	 * This method serves to overwrite the Game Object Grid by calling
	 * {@link Grid#overwriteGOG(GameObject[][])} where the {@link GameObject
	 * [][]} is the first object read by {@link #ois}.
	 * 
	 * @param grid
	 *            {@link Grid} object that will be overwritten.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void openFile(Grid grid) throws IOException, ClassNotFoundException {
		fis = new FileInputStream(SAVE_FILE);
		ois = new ObjectInputStream(fis);
		grid.overwriteGOG((GameObject[][]) ois.readObject());
	}

	/**
	 * This method returns the {@link Grid} object to be used when the game is
	 * continued. It does this by reading the {@link Grid} with {@link #ois} and
	 * saving it to {@link #savedGrid}.
	 * 
	 * @return {@link Grid} {@link #savedGrid}
	 */
	public Grid openGrid() {
		savedGrid = new Grid();
		try {
			fis = new FileInputStream(SAVE_GRID_CLASS);
			ois = new ObjectInputStream(fis);
			savedGrid = (Grid) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Could not load the grid");
		}
		return savedGrid;
	}

	/**
	 * This method returns the {@link Player} object to be used when the game is
	 * continued. It does this by reading the {@link Player} with {@link #ois}
	 * and saving it to {@code savedPLayer}
	 * 
	 * @return {@link Player} {@code savedPlayer} - object containing the
	 *         {@link Player} object from the save file.
	 */
	public Player loadPlayer() {
		Player savedPlayer = new Player();
		try {
			fis = new FileInputStream(SAVE_PLAYER);
			ois = new ObjectInputStream(fis);
			savedPlayer = (Player) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Could not load player");
		}
		return savedPlayer;
	}

	/**
	 * This method saves {@link Grid#gog}, {@link Grid}, and {@link Player} into
	 * three separate files named "save.obj", "save.grid", and "save.plr"
	 * respectively. It uses {@link #oos} to perform these functions.
	 * 
	 */
	public void saveFile(Grid grid, Player plr) {
		try {
			fos = new FileOutputStream(SAVE_FILE);
			oos = new ObjectOutputStream(fos);
			GameObject[][] gog = grid.getGOG();
			oos.writeObject(gog);
			oos.close();
			fos = new FileOutputStream(SAVE_GRID_CLASS);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(grid);
			fos.close();
			fos = new FileOutputStream(SAVE_PLAYER);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(plr);
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void noFileError() {
		System.out.println("Save files do not exist.");
		System.out.println("Starting a new game.");
	}

}
