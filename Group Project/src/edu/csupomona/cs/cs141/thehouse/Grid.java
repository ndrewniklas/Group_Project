
package edu.csupomona.cs.cs141.thehouse;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Ben, Andrew
 *
 */
public class Grid implements Serializable{

	/**
	 * The grid is a 9x9 array of GameObject
	 */
	private GameObject[][] gog;

	private GameObject go;

	/**
	 * The grid is filled with this
	 */
//	private GameObject gameObj;

	private Room bcRoom;

	private Room[] room ;

	private ArrayList<Enemy> enemy;

	private Dice die;

	private Shield shield;

	private Radar radar;

	private ExtraAmmo extraAmmo;

	/**
	 * Checks to see if we should print the name of the briefcase 
	 */
	private boolean showBriefcase;

	private int[] briefcasePos = new int[2];

//	private int[] enemyPos = new int[2];

	private int[] shieldPos = new int[2];

	private int[] radarPos = new int[2];

	private int[] ammoPos = new int[2];

	private int numEnemies=0;

	private boolean radarVis;

	private boolean extraAmmoVis;

	private boolean shieldVis;

	private boolean enemyVis;

	/**
	 * used to test the {@ link #shootGunCheck(int, int, String)]
	 */
	private boolean enemyInstance;		//used for testing

	private boolean playerKilled;

	private boolean enemyDead;

	/**
	 * The constructor fills {@link #go} with new {@link GameObject} 
	 * Makes a new {@link Dice}
	 * Sets {@link #showBriefcase}
	 */
	public Grid() {
		gog = new GameObject[9][9];
//		gameObj = new GameObject();
		room = new Room[9];
		enemy = new ArrayList<Enemy>(6);
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
	}

	public void setUpGrid(){
		setEnemy();
		setPowerUps();
		populateGrid();
		setBriefcase();
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
		//		for(int i = 0; i < enemy.size(); ++i){
		//			enemy.get(i).printEnemyPos(i);				// for testing purpose only
		//		}
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
	 * used in testing only
	 * @return
	 */
	public int gridSize(){
		int size = 0;
		size = gog.length;
		return size;
	}

	public void rePopulateGrid(Player ply) {
		gog[ply.get_yPosition()][ply.get_xPosition()] = ply;

		if (ply.get_yPosition() == ply.getYPre() && ply.get_xPosition() == ply.getXPre()) {
			System.out.println("Invalid Command: Please try again.");
		}
		else{
			gog[ply.getYPre()][ply.getXPre()] = new GameObject();
		}
	}

	public void rePopulateGrid(int index){					//for testing only
		/*for(int i = 0; i < enemy.length; ++i){
			gog[enemy[i].getYPosition()][enemy[i].getXPosition()] = enemy[i];
			gog[enemy[i].getYPre()][enemy[i].getXPre()] = gameObj;
		}	*/
		//for(int i = 0; i < enemy.size(); ++i){
		gog[enemy.get(index).getYPosition()][enemy.get(index).getXPosition()] = enemy.get(index);
		//}
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
		try{
			for (int i = 0; i < enemy.size(); i++) {
				if (gog[yPos][xPos].compareTo(enemy.get(i)) == 0) {
					check = true;
				} else {
					check = false;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			//System.out.println("Out of the grid");
		}
		return check;
	}


	public boolean checkEnemiesStacked(){
		int x=0;
		for(int i  = 0; i < enemy.size(); ++i){
			for(int j = 0; j < enemy.size(); ++j){
				if(enemy.get(i).getYPosition() == enemy.get(i).getYPosition() && enemy.get(i).getXPosition() == enemy.get(i).getXPosition()){
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
	public int[] getBCpos() {
		return briefcasePos;
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

		for(int i = 0; i < 6; ++i){
			enemy.add(new Enemy(enemyVis));
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
				gog[spawnOne][spawnTwo] = enemy.get(i);
				enemy.get(i).setYPosition(spawnOne);
				enemy.get(i).setXPosition(spawnTwo);
				i++;
				numEnemies += 1;
			}
		}
	}

	public ArrayList<Enemy> getEnemy(){
		return enemy;
	}

	public void moveEnemy(Grid grid, Player ply) {
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).moveEnemy(grid);
			rePopulateGrid(i);
			int ypos = enemy.get(i).getYPosition();
			int xpos = enemy.get(i).getXPosition();
			checkForPlayer(ypos, xpos, ply);
			if(playerKilled){
				rePopulateGrid(i);
				break;
			}
		}
		//return playerKilled;
	}

	public boolean getPlayerKilled(){
		return playerKilled;
	}

	/**
	 * @param yPos 
	 * @param xPos 
	 * @param ply 
	 */
	public void checkForPlayer(int yPos, int xPos, Player ply) {
		playerKilled = false;

		if (ply.isHasShield()) {

		}else{
			if (yPos == ply.get_yPosition() && xPos == ply.get_xPosition()) { //up
				playerKilled = true;
				gog[ply.get_yPosition()][ply.get_xPosition()] = new GameObject();
				ply.playerDies();
			}
			if (yPos == ply.get_yPosition() && xPos == ply.get_xPosition()) { //down
				playerKilled = true;
				gog[ply.get_yPosition()][ply.get_xPosition()] = new GameObject();
				ply.playerDies();
			}
			if (yPos == ply.get_yPosition() && xPos == ply.get_xPosition()) { //left
				playerKilled = true;
				gog[ply.get_yPosition()][ply.get_xPosition()] = new GameObject();
				ply.playerDies();
			}
			if (yPos == ply.get_yPosition() && xPos == ply.get_xPosition()) { //right
				playerKilled = true;
				gog[ply.get_yPosition()][ply.get_xPosition()] = new GameObject();
				ply.playerDies();
			}
			if (checkForEnemy(8, 0)){
				shield.activateShield(ply, 1);
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
	 * This method randomly places 6 enemies.
	 */
	public void setPowerUps() {
//		boolean showPowerUps = true;
		spawnRadar();
		spawnExtraAmmo();
		spawnShield();
	}	
	public void spawnRadar(){
		int spawnOne;
		int spawnTwo;
		for(int i = 0; i < 1;){
			spawnOne = die.roll(9);			//y position
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne == 8) {
				spawnOne = die.roll(9);
			}

			spawnTwo = die.roll();			//x position
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo == 0) {
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
	public int[] getRadarPos() {
		return radarPos;
	}

	public void activateRadar() {
		radar.activateRadar(bcRoom);
	}

	public void spawnExtraAmmo(){
		int spawnOne;
		int spawnTwo;
		for(int i = 0; i < 1;){
			spawnOne = die.roll(9);			//y position
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne == 8) {
				spawnOne = die.roll(9);
			}

			spawnTwo = die.roll();			//x position
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo == 0) {
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

	public ExtraAmmo getExtraAmmo(){
		return extraAmmo;
	}
	
	public int[] getExtraAmmoPos(){
		return ammoPos;
	}

	public void spawnShield(){
		int spawnOne;
		int spawnTwo;
		for(int i = 0; i < 1;){
			spawnOne = die.roll(9);			//y position
			while (spawnOne == 1 || spawnOne == 4 || spawnOne == 7 || spawnOne == 8) {
				spawnOne = die.roll(9);
			}
			spawnTwo = die.roll();			//x position
			while (spawnTwo == 1 || spawnTwo == 4 || spawnTwo == 7 || spawnTwo == 0) {
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

	public Shield getShield(){
		return shield;
	}
	
	public int[] getShieldPos(){
		return shieldPos;
	}
	
	public void shootGunCheck(int yplr, int xplr, String dir) {
		boolean ran = false;
		switch (dir) {
		case "up":
		case "u":
		case "1":
			for (int i =+ yplr; i >= 0; i--) {
//				shootGunPrintResults(i, xplr);			// for testing					
				ran = killenemy(i,xplr, ran);
			}
			break;
		case "down":
		case "d":
		case "2":
			for (int i =+ yplr; i < gog.length; i++) {
//				shootGunPrintResults(i, xplr);			// for testing
				ran = killenemy(i,xplr, ran);
			}
			break;
		case "left":
		case "l":
		case "4":
			for (int i =+ xplr; i >= 0; i--) {
//				shootGunPrintResults(yplr, i);			// for testing
				ran = killenemy(yplr,i, ran);
			}
			break;
		case "right":
		case "r":
		case "3":
			for (int i =+ xplr; i < gog.length; i++) {
//				shootGunPrintResults(yplr, i);			// for testing
				ran = killenemy(yplr,i, ran);
			}
			break;
		}
	}
	/** 
	 * prints results of whether there is an enemy at 
	 * each position that the bullet travels over
	 * 
	 * used for testing
	 * @param y
	 * @param x
	 */
	private void shootGunPrintResults(int y, int x) {
		enemyInstance = checkForEnemy(y, x);
		System.out.print(y + " " + x);
		System.out.println(enemyInstance);
	}

	/**
	 * @param y
	 * @param x
	 * @param ran 
	 * @return 
	 */
	public boolean killenemy(int y, int x, boolean ran) {
		for (int index = 0; index < enemy.size(); index++) {
			int x1 = enemy.get(index).getXPosition();
			int y1 = enemy.get(index).getYPosition();
			if (!ran) {
				if (x1 == x && y1 == y) {
					enemy.remove(enemy.get(index));
					gog[y][x] = new GameObject();
					System.out.println("You hear a thud in the distance.");
					ran = true;
					enemyDead = true;
				}
			}
		}
		return ran;
	}
	public boolean getEnemyDeath(){
		return enemyDead;
	}

	public boolean getEnemyVis(boolean vis){
		enemyVis = vis;
		return enemyVis;		
	}

	public boolean getRadarVis(boolean vis){
		radarVis = vis;
		return radarVis;
	}

	public boolean getExtraAmmoVis(boolean vis){
		extraAmmoVis = vis;
		return extraAmmoVis;
	}

	public boolean getShieldVis(boolean vis){
		shieldVis = vis;
		return shieldVis;
	}

	public boolean getBriefcaseVis(boolean vis){
		showBriefcase = vis;
		return showBriefcase;
	}

	public void respawnRadar(){
		if(!(gog[radarPos[0]][radarPos[1]] instanceof Enemy))
			gog[radarPos[0]][radarPos[1]] = radar;
	}
	public void respawnEAmmo(){		
		if(!(gog[ammoPos[0]][ammoPos[1]] instanceof Enemy))
			gog[ammoPos[0]][ammoPos[1]] = extraAmmo;
	}
	public void respawnShield(){
		if(!(gog[shieldPos[0]][shieldPos[1]] instanceof Enemy))
			gog[shieldPos[0]][shieldPos[1]] = shield;
	}

	/**
	 * @return
	 */
	public GameObject[][] getGOG() {
		return gog;
	}

	public void overwriteGOG(GameObject[][] saveGOG){
		for(int i = 0; i < gog.length; ++i){
			for(int j = 0; j < gog[i].length; ++j){
				gog[i][j] = saveGOG[i][j];
			}
		}
	}

	/**
	 * @param lookPosX2 
	 * @param lookPosY2 
	 * @param lookPosX1 
	 * @param lookPosY1 
	 * 
	 */
	public void lookReport(int lookPosY1, int lookPosX1, int lookPosY2, int lookPosX2) {
		int count=0;
		if(getObjectAtLocation(lookPosY1, lookPosX1).getObjectName().compareTo("|E|") == 0){
			count++;
		}else if(getObjectAtLocation(lookPosY2, lookPosX2).getObjectName().compareTo("|E|") == 0){
			count++;
		}
		System.out.println("There are " + count + " enemies in front of you.");
	}
}
