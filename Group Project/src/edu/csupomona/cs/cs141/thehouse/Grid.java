
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
	
	private Room bcRoom;
	
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
	
	private boolean enemyInstance;

	private boolean enemydied;
	
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
				gog[i][j] = new GameObject();
			}
		}
		setEnemy();
		setPowerUps();
		populateGrid();
		debugMode(false);
		setBriefcase();
	}
	
	//TODO MOVE TO GAME ENGINE!!!!!!!!!
	public void debugMode(boolean on){
		if(on){
			radarVis = true;
			enemyVis = true;
			showBriefcase = true;
			shieldVis = true;
			extraAmmoVis = true;	
			showBriefcase = true;
			changeAllObjectStates(true);
		}
		else{
			radarVis = false;
			enemyVis = false;
			showBriefcase = false;
			shieldVis = false;
			extraAmmoVis = false;
			showBriefcase = false;
			changeAllObjectStates(false);
		}
		
	}
	
	public void changeAllObjectStates(boolean toggle) {
		if(toggle){
			/*shield.showName();
			radar.showName();
			extraAmmo.showName();
			for(int i = 0; i < enemy.length; ++i){
				enemy[i].showName();
			}
			gameObj.showName();*/
			for(int i = 0; i < gog.length; ++i){
				for(int j = 0; j < gog[i].length; ++j){
					gog[i][j].showName();
				}
			}
		}else{
		/*	shield.hideName();
			radar.hideName();
			extraAmmo.hideName();
			for(int i = 0; i < enemy.length; ++i){
				enemy[i].hideName();
			}
			gameObj.hideName();*/
			for(int i = 0; i < gog.length; ++i){
				for(int j = 0; j < gog[i].length; ++j){
					gog[i][j].hideName();
				}
			}
		}	
	}
	
	public void showObjectsWithinLocation(int y1, int x1, int y2, int x2, boolean toggle){
		if(toggle){
			gog[y1][x1].showName();
			gog[y2][x2].showName();
		}else{
			gog[y1][x1].hideName();
			gog[y2][x2].hideName();
		}
	}
	public void showPlayerDefaultVision(int y1, int x1, boolean toggle){
		if(toggle){
			gog[y1][x1].showName();
		}else{
			gog[y1][x1].hideName();
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
			room[i] = new Room(false);
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
			gog[ply.getYPre()][ply.getXPre()] = new GameObject();
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
		gog[posY][posX] = new GameObject();
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
		boolean check = false;
		if(gog[yPos][xPos] instanceof Enemy){
			check = true;
		}
		else{
			check = false;
		}
		return check;
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
		bcRoom = new Room(true);
		firstRandom = die.roll(8);
		while(firstRandom != 1 && firstRandom != 4 && firstRandom != 7){
			firstRandom = die.roll(8);
		}
		secondRandom = die.roll();
		while(secondRandom != 1 && secondRandom != 4 && secondRandom != 7){
			secondRandom = die.roll();
		}
		gog[firstRandom][secondRandom] = bcRoom;
		briefcasePos[0] = firstRandom;
		briefcasePos[1] = secondRandom;
	}
	
	public Room getBriefcaseRoom(){
		return bcRoom;
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
	public Radar getRadar(){
		return radar;
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
	
	public int[] getRadarPos() {
			return radarPos;
	}

	public void activateRadar() {
		radar.activateRadar(bcRoom);
	}
	
	public void shootGunCheck(int yplr, int xplr, String dir) {
		switch (dir) {
		case "up":
		case "u":
		case "1":
			for (int i =+ yplr; i >= 0; i--) {
				enemyInstance = checkForEnemy(i, xplr);
				System.out.print(i + " " + xplr);
				System.out.println(enemyInstance);
				if (enemyInstance == true) {
//					killEnemy(i, xplr);
				}
			}
			break;
			
		case "down":
		case "d":
		case "2":
			for (int i =+ yplr; i < gog.length; i++) {
				enemyInstance = checkForEnemy(i, xplr);
				System.out.print(i + " " + xplr);
				System.out.println(enemyInstance);
				if (enemyInstance == true) {
//					killEnemy(i, xplr);
				}
			}
			break;
			
		case "left":
		case "l":
		case "4":
			for (int i =+ xplr; i >= 0; i--) {
				enemyInstance = checkForEnemy(yplr, i);
				System.out.print(yplr + " " + i);
				System.out.println(enemyInstance);
				if (enemyInstance == true) {
//					killEnemy(yplr, i);
				}
			}
			break;
			
		case "right":
		case "r":
		case "3":
			for (int i =+ xplr; i < gog.length; i++) {
				System.out.print(yplr + " " + i);
				enemyInstance = checkForEnemy(yplr, i);
				System.out.println(enemyInstance);
				if (enemyInstance == true) {
//					killEnemy(yplr, i);
				}
			}
			break;
		}
	}

	private void killEnemy(int ypos, int xpos) {
		for(int i = 0; i < enemy.length; i++) {
			if (enemy[i].pos[0] == ypos && enemy[i].pos[1] == xpos) {
				enemydied = true;
				System.out.println("Enemy died");
			} else {
				System.out.println("Enemy did not die");
			}
		}
	}
}
