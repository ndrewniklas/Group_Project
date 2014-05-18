
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
	
	private GameObject go;
	/**
	 * The grid is filled with this
	 */
	private GameObject gameObj = new GameObject();
	
	private Room[] room = new Room[9];
	
	private Enemy[] enemy = new Enemy[6];
	
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
	private int[] enemyPos = new int[2];

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
				gog[i][j] = gameObj;
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
	
	public int gridSize(){
		int size = 0;
		size = gog.length;
		return size;
	}
	
	/**
	 * Populates the grid with rooms, player, enemy, and powerups in the position at {@link #go}
	 */
	public void populateGrid(){
		for(int i = 0; i < 9; ++i){
			room[i] = new Room();
		}
		gog[1][1] = room[0];
		gog[4][1] = room[1];
		gog[7][1] = room[2];
		gog[1][4] = room[3];
		gog[4][4] = room[4];
		gog[7][4] = room[5];
		gog[1][7] = room[6];
		gog[4][7] = room[7];
		gog[7][7] = room[8];
	}
	
	public void rePopulateGrid(Player ply) {
		gog[ply.get_yPosition()][ply.get_xPosition()] = ply;
		gog[ply.getYPre()][ply.getXPre()] = ply;
		if (ply.get_yPosition() == ply.getYPre() && ply.get_xPosition() == ply.getXPre()) {
				System.out.println("Invalid Command: Please try again.");
		}
		else{
			gog[ply.getYPre()][ply.getXPre()] = gameObj;
		}
	}
	
	public GameObject getObjectAtLocation(int posY, int posX){
		go = gog[posY][posX];
		return go;
	}
	
	public boolean checkIfLocationFree(int posY, int posX){
		if(getObjectAtLocation(posY, posX).equals(gameObj))
			return true;
		else
			return false;
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
		BCy = firstRandom;
		BCx = secondRandom;
	}
	
	/**
	 * This method randomly places 6 enemies.
	 */
	public void setEnemy() {
		int spawnOne;
		int spawnTwo;
		boolean showEnemy = true;

		for(int i = 0; i < 6; ++i){
			enemy[i] = new Enemy();
		}
		
		for (int i = 0; i < 6;) {
			spawnOne = die.roll(8);
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne < 1) {
				spawnOne = die.roll(9);
			}
			spawnTwo = die.roll();
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo > 7) {
				spawnTwo = die.roll(9);
			}
			
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName())) {
				gog[spawnOne][spawnTwo] = enemy[i];
				enemyPos[0] = spawnTwo;
				enemyPos[1] = spawnOne;
				i++;
				setNumEnemies(getNumEnemies() + 1);
			}
		}
	}

	public int[] getEnemyPosition(){
		return enemyPos;
	}
	
	public void moveEnemyOnGrid(){
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
		
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName())){
				gog[spawnOne][spawnTwo] = new Radar();
				radX = spawnTwo;
				radY = spawnOne;
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
		
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName())){
				gog[spawnOne][spawnTwo] = new ExtraAmmo();
				ammoX = spawnTwo;
				ammoY = spawnOne;
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
		
			if (gog[spawnOne][spawnTwo].getObjectName().equals(gameObj.getEmptyObjectName())){
				gog[spawnOne][spawnTwo] = new Shield();
				shieldX = spawnTwo;
				shieldY = spawnOne;
				++i;
			}
		}
	}
}
