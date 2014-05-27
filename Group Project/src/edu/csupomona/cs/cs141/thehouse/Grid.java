
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
	private GameObject[][] gog;
	
	private GameObject go;
	
	/**
	 * The grid is filled with this
	 */
	private GameObject gameObj;
	
	private Room[] room ;
	
	private Enemy[] enemy;
	
	private Dice die;
	
	private Shield shield;
	
	private Radar radar;
	
	private ExtraAmmo extraAmmo;
	
	/**
	 * Checks to see if we should print the name of the briefcase 
	 */
	private boolean showBriefcase;
	
	private int[] briefcasePos = new int[2];
	
	private int[] enemyPos = new int[2];
	
	private int[] shieldPos = new int[2];
	
	private int[] radarPos = new int[2];
	
	private int[] ammoPos = new int[2];
	
	private int numEnemies=0;

	private boolean radarVis;

	private boolean extraAmmoVis;

	private boolean shieldVis;
	
	private boolean enemyVis;
	
	/**
	 * The constructor fills {@link #go} with new {@link GameObject} 
	 * Makes a new {@link Dice}
	 * Sets {@link #showBriefcase}
	 */
	public Grid() {
		gog = new GameObject[9][9];
		gameObj = new GameObject();
		room = new Room[9];
		enemy = new Enemy[6];
		die = new Dice();
		shield = new Shield(shieldVis);
		radar = new Radar(radarVis);
		extraAmmo = new ExtraAmmo(extraAmmoVis);
		for(int i = 0; i < gog.length; ++i){
			for(int j = 0; j < gog[i].length; ++j){
				//Old way, fill with new GameObject()
				gog[i][j] = gameObj;
			}
		}
		setBriefcase();
		setEnemy();
		setPowerUps();
		populateGrid();
		//TODO CHANGE WHEN MOVE DEBUG MODE!!!!!!
		debugMode(false);
	}
	
	//TODO MOVE TO GAME ENGINE!!!!!!!!!
	public void debugMode(boolean on){
		if(on){
			radarVis = true;
			enemyVis = true;
			showBriefcase = true;
			shieldVis = true;
			extraAmmoVis = true;	
			changeAllObjectStates(true);
		}
		else{
			radarVis = false;
			enemyVis = false;
			showBriefcase = false;
			shieldVis = false;
			extraAmmoVis = false;
			changeAllObjectStates(false);
		}
		
	}
	
	public void changeAllObjectStates(boolean toggle) {
		if(toggle){
			shield.showName();
			radar.showName();
			extraAmmo.showName();
			for(int i = 0; i < enemy.length; ++i){
				enemy[i].showName();
			}
		}else{
			shield.hideName();
			radar.hideName();
			extraAmmo.hideName();
			for(int i = 0; i < enemy.length; ++i){
				enemy[i].hideName();
			}
		}	
	}
	
	public void showObjectsWithinLocation(int[] location1, int[] location2, int[] location3, int[] location4, boolean toggle){
		if(toggle){
			gog[location1[0]][location1[1]].showName();
			gog[location2[0]][location2[1]].showName();
			gog[location3[0]][location3[1]].showName();
			gog[location4[0]][location4[1]].showName();
		}else{
			gog[location1[0]][location1[1]].hideName();
			gog[location2[0]][location2[1]].hideName();
			gog[location3[0]][location3[1]].hideName();
			gog[location4[0]][location4[1]].hideName();
		}
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
	 * Populates the grid with rooms in the position at {@link #go}
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
		
		if (ply.get_yPosition() == ply.getYPre() && ply.get_xPosition() == ply.getXPre()) {
				System.out.println("Invalid Command: Please try again.");
		}
		else{
			gog[ply.getYPre()][ply.getXPre()] = gameObj;
		}
		
		for(int i = 0; i < enemy.length; ++i){
			gog[enemy[i].getYPosition()][enemy[i].getXPosition()] = enemy[i];
		}
	}

	public void rePopulateGrid(){					//for testing only
		/*for(int i = 0; i < enemy.length; ++i){
			gog[enemy[i].getYPosition()][enemy[i].getXPosition()] = enemy[i];
			gog[enemy[i].getYPre()][enemy[i].getXPre()] = gameObj;
		}	*/
		for(int i = 0; i < enemy.length; ++i){
			gog[enemy[i].getYPosition()][enemy[i].getXPosition()] = enemy[i];
		}
	}
	
	public void changeObjectIntoBlank(int posY, int posX){
		gog[posY][posX] = gameObj;
	}
	
	public GameObject getObjectAtLocation(int posY, int posX){
		go = gog[posY][posX];
		return go;
	}
	
	public boolean checkIfLocationFree(int posY, int posX){
		if(gog[posY][posX] instanceof GameObject)
			return true;
		else
			return false;
	}
	
	/**
	 * @param yPosition
	 * @param i
	 * @return
	 */
	public boolean checkForEnemy(int yPos, int xPos) {
		if(gog[yPos][xPos] instanceof Enemy){
//			if(gog[yPos][xPos] instanceof Player){
			System.out.println("false");
				return false;
//			}
		}
		else{
			System.out.println("true");
		}
		return true;
	}
	
	public boolean checkEnemiesStacked(){
		int x=0;
		for(int i  = 0; i < enemy.length; ++i){
			for(int j = 0; j < enemy.length; ++j){
				if(enemy[i].getYPosition() == enemy[j].getYPosition() && enemy[i].getXPosition() == enemy[j].getXPosition()){
					x++;
					if(x==2)
						return true;
					else {
						return false;	
					}
				}
			}
		}
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
		briefcasePos[0] = firstRandom;
		briefcasePos[1] = secondRandom;
	}
	
	/**
	 * This method randomly places 6 enemies.
	 */
	public void setEnemy() {
		int spawnOne;
		int spawnTwo;
		boolean showEnemy = true;

		for(int i = 0; i < 6; ++i){
			enemy[i] = new Enemy(enemyVis);
		}
		
		for (int i = 0; i < 6;) {
			spawnOne = die.roll(8);
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne > 6) {
				spawnOne = die.roll(9);
			}
			spawnTwo = die.roll();
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo < 2) {
				spawnTwo = die.roll(9);
			}
			
			if (checkIfLocationFree(spawnOne, spawnTwo)) {
				gog[spawnOne][spawnTwo] = enemy[i];
				enemy[i].setYPosition(spawnOne);
				enemy[i].setXPosition(spawnTwo);
				i++;
				numEnemies += 1;
			}
		}
	}
	
	public GameObject[] getEnemy(){
		return enemy;
	}
	
	public void moveEnemy(Grid grid) {
		for (int i = 0; i < enemy.length; i++) {
			enemy[i].moveEnemy(grid);
			
			enemy[i].printEnemyPos(i);		// for testing purpose only
//			boolean stacked = checkEnemiesStacked();
//			System.out.println("Stacked: " + stacked);
		}
	}

	/**
	 * @return the numEnemies
	 */
	public int getNumEnemies() {
		return numEnemies;
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
		
			if (checkIfLocationFree(spawnOne, spawnTwo)){
				gog[spawnOne][spawnTwo] = radar;
				radarPos[0] = spawnOne;
				radarPos[1] = spawnTwo;
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
		
			if (checkIfLocationFree(spawnOne, spawnTwo)){
				gog[spawnOne][spawnTwo] = extraAmmo;
				ammoPos[0] = spawnOne;
				ammoPos[1] = spawnTwo;
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
		
			if (checkIfLocationFree(spawnOne, spawnTwo)){
				gog[spawnOne][spawnTwo] = shield;
				shieldPos[0] = spawnOne;
				shieldPos[1] = spawnTwo;
				++i;
			}
		}
	}
}
