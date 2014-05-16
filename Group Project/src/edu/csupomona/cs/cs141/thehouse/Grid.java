
package edu.csupomona.cs.cs141.thehouse;

import java.util.*;
/**
 * @author Ben, Andrew
 *
 */
public class Grid {
	
	/**
	 * The grid is a 9x9 array of GameObject
	 */
	private GameObject[][] gog = new GameObject[9][9];	
	
	/**
	 * The grid is filled with this
	 */
	private GameObject gameObj = new GameObject();
	
	private Room room = new Room();
	
	private Enemy enemy;
	
	private Dice die;
	/**
	 * This field holds the briefcase, it will be the same as the {@code String[1][1]} for 
	 * {@link #tempRoomBlock}.
	 */
	private String briefcase = "[B]";
	
	/**
	 * Checks to see if we should print the name of the briefcase 
	 */
	private boolean showBriefcase;

	private int BCy;
	private int BCx;
	
	//enemy position y
	private int EPy;
	private int EPx;

	private int radY;
	private int radX;
	private int shieldY;
	private int shieldX;
	private int ammoY;
	private int ammoX;
	
	private int numEnemies=0;
	
	/**
	 * The constructor fills {@link #go} with new {@link GameObject} 
	 * Makes a new {@link Dice}
	 * Sets {@link #showBriefcase}
	 */
	public Grid() {
		die = new Dice();
		showBriefcase = true;
		for(int i = 0; i < gog.length; ++i){
			for(int j = 0; j < gog[i].length; ++j){
				//Old way, fill with new GameObject()
				gog[i][j] = new GameObject();
			}
		}
		populateGrid();
	}
	
	/**
	 * Prints out the name of the object at that location
	 */
	public void printGrid(){
		for(int i = 0; i < gog.length; ++i){
			for(int j = 0; j < gog[i].length; ++j){
				gog[i][j].printObject();
			}
			System.out.println();
		}
	}
	
	/**
	 * Populates the grid with rooms, player, enemy, and powerups in the position at {@link #go}
	 */
	public void populateGrid(){
		gog[1][1] = new Room();
		gog[4][1] = new Room();
		gog[7][1] = new Room();
		gog[1][4] = new Room();
		gog[4][4] = new Room();
		gog[7][4] = new Room();
		gog[1][7] = new Room();
		gog[4][7] = new Room();
		gog[7][7] = new Room();
	}
	
	public void rePopulateGrid(Player ply) {
		gog[ply.get_yPosition()][ply.get_xPosition()] = ply;
		gog[ply.getYPre()][ply.getXPre()] = ply;
		if (ply.get_yPosition() == ply.getYPre() && ply.get_xPosition() == ply.getXPre()) {
				System.out.println("Invalid Command: Please try again.");
		}
		else{
			gog[ply.getYPre()][ply.getXPre()] = new GameObject();
		}
		
	}
	
	/**
	 * This method randomly places the briefcase by calling {@link Dice#roll()}. It then creates a temporary
	 * {@code String[][]} that stores the briefcase. Any time a {@link Player} enters a room, it checks to
	 * see if that room corresponds to the temporary room, holding the case. If so, the player wins and the 
	 * game ends.
	 * 
	 * AMN: Creates two local ints. Uses {@link Dice#roll(int)} to randomly choose 1, 4, or 7 (which are the
	 * rooms locations). Then it will create a new {@link Room(boolean)} which the value of {@link #showBriefcase}
	 */
	public void setBriefcase() {
		int firstRandom, secondRandom;
		firstRandom = die.roll(8);
		while(firstRandom != 1 && firstRandom != 4 && firstRandom != 7){
			firstRandom = die.roll(8);
		}
		secondRandom = die.roll();
		while(secondRandom != 1 && secondRandom != 4 && secondRandom != 7){
			secondRandom = die.roll();
		}
		gog[firstRandom][secondRandom] = new Room(showBriefcase);
		BCy=firstRandom;
		BCx=secondRandom;
	}
	
	/**
	 * This method randomly places 6 enemies.
	 */
	public void setEnemy() {
		int spawnOne;
		int spawnTwo;
		boolean showEnemy = true;

		for (int i = 0; i < 6;) {
			spawnOne = die.roll(8);
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne < 0) {
				spawnOne = die.roll(9);
			}
			
			spawnTwo = die.roll();
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo > 8) {
				spawnTwo = die.roll(9);
			}
			
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName()) 
				&& gog[spawnOne][spawnTwo].getObjectName().compareTo(room.getObjectName())!= 1) {
				gog[spawnOne][spawnTwo] = new Enemy();
				EPx = spawnOne;
				EPy = spawnTwo;
				i++;
				setNumEnemies(getNumEnemies() + 1);
			}
		}
	}

	/**
	 * @return the numEnemies
	 */
	public int getNumEnemies() {
		return numEnemies;
	}

	/**
	 * @param numEnemies the numEnemies to set
	 */
	public void setNumEnemies(int numEnemies) {
		this.numEnemies = numEnemies;
	}
	
	/**
	 * This method randomly places 6 enemies.
	 */
	public void setPowerUps() {
		boolean showPowerUps = true;
		spawnRadar();
		spawnExtraAmmo();
		spawnShield();
	}	
	public void spawnRadar(){
		int spawnOne;
		int spawnTwo;
		for(int i = 0; i < 1;){
			spawnOne = die.roll(9);
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne < 0) {
				spawnOne = die.roll(9);
			}
		
			spawnTwo = die.roll();
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo > 8) {
				spawnTwo = die.roll(9);
			}
		
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName()) 
					&& gog[spawnOne][spawnTwo].getObjectName().compareTo(room.getObjectName())!= 1) {
				gog[spawnOne][spawnTwo] = new Radar();
				radX = spawnOne;
				radY = spawnTwo;
				++i;
			}
		}
	}
	public void spawnExtraAmmo(){
		int spawnOne;
		int spawnTwo;
		for(int i = 0; i < 1;){
			spawnOne = die.roll(9);
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne < 0) {
				spawnOne = die.roll(9);
			}
		
			spawnTwo = die.roll();
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo > 8) {
				spawnTwo = die.roll(9);
			}
		
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName()) 
					&& gog[spawnOne][spawnTwo].getObjectName().compareTo(room.getObjectName())!= 1) {
				gog[spawnOne][spawnTwo] = new ExtraAmmo();
				ammoX = spawnOne;
				ammoY = spawnTwo;
				++i;
			}
		}
	}
	public void spawnShield(){
		int spawnOne;
		int spawnTwo;
		for(int i = 0; i < 1;){
			spawnOne = die.roll(9);
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne < 0) {
				spawnOne = die.roll(9);
			}
		
			spawnTwo = die.roll();
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo > 8) {
				spawnTwo = die.roll(9);
			}
		
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName()) 
					&& gog[spawnOne][spawnTwo].getObjectName().compareTo(room.getObjectName())!= 1) {
				gog[spawnOne][spawnTwo] = new Shield();
				shieldX = spawnOne;
				shieldY = spawnTwo;
				++i;
			}
		}
	}
}
