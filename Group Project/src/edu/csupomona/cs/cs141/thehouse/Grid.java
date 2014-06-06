
package edu.csupomona.cs.cs141.thehouse;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class deals with all {@link GameObject}s on the 9x9 grid that the game takes place on. It helps
 * {@linl GameEngine} to move objects around and also re-populates itself after every turn. While {@link 
 * GameEngine} runs the game, the actual actions take place on the grid itself.
 * @author Ben Nickerson, Andrew Nipp, Andrew Niklas
 *
 */
public class Grid implements Serializable{

	/**
	 * The grid is a 9x9 array of GameObject
	 */
	private GameObject[][] gog;

	/**
	 * Instantiation for {@link GameObject}
	 */
	private GameObject go;

	/**
	 * This field holds the {@link Room} containing a bomb
	 */
	private Room bcRoom;

	/**
	 * This field holds the {@link Room} objects in an {@code array}
	 */
	private Room[] room ;

	/**
	 * This field creates an {@link ArrayList} of {@link Enemy Enemies}
	 */
	private ArrayList<Enemy> enemy;

	/**
	 * This field instantiates {@link Dice}
	 */
	private Dice die;

	/**
	 * This field instantiates {@link Shield}
	 */
	private Shield shield;

	/**
	 * This field instantiates {@link Radar}
	 */
	private Radar radar;

	/**
	 * This field instantiates {@link ExtraAmmo}
	 */
	private ExtraAmmo extraAmmo;

	/**
	 * Checks to see if we should print the name of the briefcase 
	 */
	private boolean showBriefcase;

	/**
	 * This field represents an {@link int[]} containing the coordinates of the bomb
	 */
	private int[] briefcasePos = new int[2];

//	private int[] enemyPos = new int[2];

	/**
	 * This field represents an {@link int[]} containing the coordinates of the {@link Shield}
	 */
	private int[] shieldPos = new int[2];

	/**
	 * This field represents an {@link int[]} containing the coordinates of the {@link Radar}
	 */
	private int[] radarPos = new int[2];

	/**
	 * This field represents an {@link int[]} containing the coordinates of the {@link ExtraAmmo}
	 */
	private int[] ammoPos = new int[2];

	/**
	 * This field represents the number of {@link Enemy Enemies} as an {@code int}
	 */
	private int numEnemies=0;

	/**
	 * This field represents a {@code boolean} that is {@code true} when the location of the {@link Radar}
	 * can be seen, and {@code false} otherwise
	 */
	private boolean radarVis;

	/**
	 * This field represents a {@code boolean} that is {@code true} when the location of the {@link ExtraAmmo}
	 * can be seen, and {@code false} otherwise
	 */
	private boolean extraAmmoVis;

	/**
	 * This field represents a {@code boolean} that is {@code true} when the location of the {@link Shield}
	 * can be seen, and {@code false} otherwise
	 */
	private boolean shieldVis;

	/**
	 * This field represents a {@code boolean} that is {@code true} when the location of an {@link Enemy}
	 * can be seen, and {@code false} otherwise
	 */
	private boolean enemyVis;

	/**
	 * used to test the {@link #shootGunCheck(int, int, String)}
	 */
	private boolean enemyInstance;		//used for testing

	/**
	 * This field represents a {@code boolean} that is {@code true} when the {@link Player} is killed, and
	 * {@code false} otherwise
	 */
	private boolean playerKilled;

	/**
	 * This field represents a {@code boolean} that is {@code true} when an {@link Enemy} is killed, and
	 * {@code false} otherwise
	 */
	private boolean enemyDead;

	/**
	 * This field represents a {@code boolean} that is {@code true} when an {@link Enemy} is found, and
	 * {@code false} otherwise
	 */
	private boolean enemyFound;

	/**
	 * The constructor fills {@link #go} with new {@link GameObject}, 
	 * Makes a new {@link Dice},
	 * Sets {@link #showBriefcase}.
	 * Instantiates {@link #enemy} with 6 {@link Enemy} Objects, 
	 * {@link #shield} by calling {@link Shield#Shield(boolean)},
	 * {@link #radar} by calling {@link Radar#Radar(boolean)},
	 * {@link #extraAmmo} by calling {@link ExtraAmmo#ExtraAmmo(boolean)},
	 * and fills {@link #gog} with {@link GameObject}s.
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

	/**
	 * This method essentially populates the {@link Grid} for a new game. It calls {@link #setEnemy()},
	 * {@link #setPowerUps()}, {@link #populateGrid()}, and {@link #setBriefcase()} in that order.
	 */
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
	 * Prints out the {@link Grid}
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

	/**
	 * This method will change all object states to visible/invisible depending on the value of the 
	 * parameter. All it has to do is go through every square in {@link #gog} and call {@link GameObject#showName()}
	 * or {@link GameObject#hideName()}.
	 * @param toggle
	 * 	- {@code boolean} containing {@code true/false}. {@code True} when all objects are intended to be 
	 * 		shown. {@code False} when all objects are intended to be hidden.
	 */
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

	/**
	 * This method will show the objects in a specific location so long as the {@code boolean} parameter
	 * is {@code true}; otherwise it serves to hide the object at that specific location. Primarily used
	 * for the look function
	 * @param y1
	 * 	- {@code int} containing the initial y coordinate
	 * @param x1
	 * 	- {@code int} containing the initial x coordinate
	 * @param y2
	 * 	- {@code int} containing the secondary y coordinate
	 * @param x2
	 * 	- {@code int} containing the secondary x coordinate
	 * @param toggle
	 * 	- {@code boolean} when {@code true} will show the objects in that location, when {@code false} will
	 * 		hide them.
	 */
	public void showObjectsWithinLocation(int y1, int x1, int y2, int x2, boolean toggle){
		if(toggle){
			gog[y1][x1].showName();
			gog[y2][x2].showName();
		}else{
			gog[y1][x1].hideName();
			gog[y2][x2].hideName();
		}
	}
	
	/**
	 * This method is used to make the {@link Player}'s default visibility work. It will use the coordinates
	 * of the direction that the player is looking and call {@link GameObject#showName()} or 
	 * {@link GameObject#hideName()} depending on whether the {@code boolean} parameter is {@code true}
	 * or {@code false} respectively.
	 * @param y1
	 * 	- {@code int} containing the y coordinate of the direction the {@link Player} is looking}
	 * @param x1
	 * 	- {@code int} containing the x coordinate of the direction the {@link Player} is looking}
	 * @param toggle
	 * 	- {@code boolean} when {@code true} will show the objects in that location, when {@code false} will
	 * 		hide them.
	 */
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

	/**
	 * This method will replace the [P] tile to the new position on the {@link Grid} as long as the 
	 * {@link Player} is able to move to that location
	 * @param ply
	 * 	- {@code Player} {@link Player}
	 */
	public void rePopulateGrid(Player ply) {
		gog[ply.get_yPosition()][ply.get_xPosition()] = ply;

		if (ply.get_yPosition() == ply.getYPre() && ply.get_xPosition() == ply.getXPre()) {
			System.out.println("Invalid Command: Please try again.");
		}
		else{
			gog[ply.getYPre()][ply.getXPre()] = new GameObject();
		}
	}

	/**
	 * This method will repopulate the {@link Grid} with all of the {@link Enemy} objects. It is used for
	 * testing purposes only.
	 * @param index
	 */
	public void rePopulateGrid(int index){					//for testing only
		/*for(int i = 0; i < enemy.length; ++i){
			gog[enemy[i].getYPosition()][enemy[i].getXPosition()] = enemy[i];
			gog[enemy[i].getYPre()][enemy[i].getXPre()] = gameObj;
		}	*/
		//for(int i = 0; i < enemy.size(); ++i){
		gog[enemy.get(index).getYPosition()][enemy.get(index).getXPosition()] = enemy.get(index);
		//}
	}

	/**
	 * This method will cause the object at the given location to be replaced with a generic empty 
	 * {@link GameObject}.
	 * @param posY
	 * 	- {@code int} containing the y coordinate of the object
	 * @param posX
	 * 	- {@code int} containing the x coordinate of the object
	 */
	public void changeObjectIntoBlank(int posY, int posX){
		gog[posY][posX] = new GameObject();
	}

	/**
	 * This method will find an object at a specified location and pass that to whatever calls it. It does 
	 * this by setting {@link #go} to the location of {@link #gog}
	 * @param posY
	 * 	- {@code int} containing the y coordinate of the object
	 * @param posX
	 * 	- {@code int} containing the x coordinate of the object
	 * @return
	 * 	- {@link GameObject} {@link #go}
	 */
	public GameObject getObjectAtLocation(int posY, int posX){
		go = gog[posY][posX];
		return go;
	}

	/**
	 * This method is responsible for checking if a location is free. If there is no {@link GameObject} at 
	 * the location, it returns {@code false}; otherwise it returns {@code true}.
	 * @param posY
	 * 	- {@code int} containing the y coordinate of the object
	 * @param posX
	 * 	- {@code int} containing the x coordinate of the object
	 * @return
	 * 	- {@code boolean} that is {@code true} is the location is taken, and {@code false} otherwise
	 */
	public boolean checkIfLocationFree(int posY, int posX){
		if(gog[posY][posX] instanceof GameObject)
			return true;
		else
			return false;
	}

	/**
	 * This method will check for an {@link Enemy} in the specified location and return a {@code boolean}.
	 * @param yPos
	 * 	- {@code int} containing the y coordinate of the object 
	 * @param xPos
	 * 	- {@code int} containing the y coordinate of the object
	 * @return
	 * 	- {@code boolean} that is {@code true} when there is an {@link Enemy} and {@code false} otherwise
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


	/**
	 * This method will check to ensure that the {@link Enemy} objects are not stacked by comparing them.
	 * @return
	 * 	- {@code boolean} that is {@code true} when {@link Enemy} objects are in the same square and {@code false
	 * 		otherwise.
	 */
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
	
	/**
	 * This method will give the location of the bomb as an {@code int[]} to whatever calls it
	 * @return
	 * 	- {@code int[]} {@link #briefcasePos}
	 */
	public int[] getBCpos() {
		return briefcasePos;
	}
	
	/**
	 * This method will give the {@link Room} object containing the bomb to whatever calls it
	 * @return
	 * 	- {@link Room} containing bomb
	 */
	public Room getBriefcaseRoom(){
		return bcRoom;
	}

	/**
	 * This method randomly places 6 enemies on the {@link Grid} at least two squares away from the 
	 * {@link Player}
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

	/**
	 * This method will return an {@link ArrayList} of {@link Enemy} objects to whatever calls it
	 * @return
	 * 	- {@link ArrayList} of {@link Enemy Enemies}
	 */
	public ArrayList<Enemy> getEnemy(){
		return enemy;
	}

	/**
	 * This method will move the {@link Enemy} object on the {@link Grid} while also checking to see if 
	 * a {@link Player} needs to be stabbed.
	 * @param grid
	 * 	- {@link Grid}
	 * @param ply
	 * 	- {@link Player}
	 */
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

	/**
	 * This method returns the {@link #playerKilled} {@code boolean} to whatever calls it
	 * @return
	 * 	- {@code boolean} {@link #playerKilled}
	 */
	public boolean getPlayerKilled(){
		return playerKilled;
	}

	/**
	 * This method is called whenever the {@link Enemy Enemies} move. It checks to see if a {@link Player}
	 * has a {@link Shield} activated before killing them for being within striking distance.
	 * @param yPos 
	 * 	- {@code int} containing the y coordinate of the object
	 * @param xPos 
	 *	- {@code int} containing the X coordinate of the object
	 * @param ply 
	 * 	- {@link Player}
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
	 * This method will pass {@link #numEnemies} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #numEnemies}
	 */
	public int getNumEnemies() {
		return numEnemies;
	}

	/**
	 * This method calls the methods that spawn each individual power up.
	 */
	public void setPowerUps() {
//		boolean showPowerUps = true;
		spawnRadar();
		spawnExtraAmmo();
		spawnShield();
	}	
	
	/**
	 * This method randomly places the {@link Radar} at least 2 squares away from the {@link Player}
	 */
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
	
	/**
	 * This method will pass the {@link Radar} to whatever calls it
	 * @return
	 * 	- {@link Radar}
	 */
	public Radar getRadar(){
		return radar;
	}
	
	/**
	 * This method will pass the location of the {@link Radar} as an {@code int[]} to whatever calls it
	 * @return
	 * 	- {@code int[]} {@link #radarPos}
	 */
	public int[] getRadarPos() {
		return radarPos;
	}

	/**
	 * This method simply calls {@link Radar#activateRadar(Room)} and passes along the {@link #bcRoom}
	 */
	public void activateRadar() {
		radar.activateRadar(bcRoom);
	}

	/**
	 * This method randomly places the {@link ExtraAmmo} at least 2 squares away from the {@link Player}
	 */
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

	/**
	 * This method will return the {@link ExtraAmmo} object to whatever calls it
	 * @return
	 * 	- {@link ExtraAmmo}
	 */
	public ExtraAmmo getExtraAmmo(){
		return extraAmmo;
	}
	
	/**
	 * This method will return the location of the {@link ExtraAmmo} object as an {@code int[]}
	 * @return
	 * 	- {@code int[]} {@link #ammoPos}
	 */
	public int[] getExtraAmmoPos(){
		return ammoPos;
	}

	/**
	 * This method randomly places the {@link Shield} at least 2 squares away from the {@link Player}
	 */
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

	/**
	 * This method will return the {@link Shield} object to whatever calls it
	 * @return
	 * 	- {@link Shield}
	 */
	public Shield getShield(){
		return shield;
	}
	
	/**
	 * This method will return the location of the {@link Shield} object as an {@code int[]}
	 * @return
	 * 	- {@code int[]} {@link #shieldPos}
	 */
	public int[] getShieldPos(){
		return shieldPos;
	}
	
	/**
	 * This method will check to see if any {@link Enemy} objects are in the way of the bullet when the 
	 * {@link Player} shoots
	 * @param yplr
	 * 	- {@code int} containing the y coordinate of the {@link Player}
	 * @param xplr
	 * 	- {@code int} containing the x coordinate of the {@link Player}
	 * @param dir
	 * 	- {@code String} containing a cardinal direction desiginating where the bullet will travel.
	 */
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
	 * 	- {@code int} containing the y coordinate
	 * @param x
	 * 	- {@code int} containing the x coordinate
	 */
	private void shootGunPrintResults(int y, int x) {
		enemyInstance = checkForEnemy(y, x);
		System.out.print(y + " " + x);
		System.out.println(enemyInstance);
	}

	/**
	 * This method will kill an {@link Enemy} when it is hit by a bullet
	 * @param y
	 * 	- {@code int} containing the y coordinate to be checked by the {@link Enemy}'s
	 * @param x
	 * 	- {@code int} containing the x coordinate to be checked by the {@link Enemy}'s
	 * @param ran 
	 * 	- {@code boolean} that will runs the location check only if it is {@code false}
	 * @return 
	 * 	- {@code boolean} that was passed in as a parameter
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
				else
					enemyDead = false;
			}
		}
		return ran;
	}
	
	/**
	 * This method will pass {@link #enemyDead} to whatever calls it
	 * @return
	 * 	- {@code boolean} {@link #enemyDead}
	 */
	public boolean getEnemyDeath(){
		return enemyDead;
	}

	/**
	 * This method will set {@link #enemyVis} to whatever the parameter is and return it to whatever calls it
	 * @param vis
	 * 	- {@code boolean} containing the value intended for {@link #enemyVis}
	 * @return
	 * 	- {@code boolean} {@link #enemyVis}
	 */
	public boolean getEnemyVis(boolean vis){
		enemyVis = vis;
		return enemyVis;		
	}

	/**
	 * This method will set {@link #radarVis} to whatever the parameter is and return it to whatever calls it
	 * @param vis
	 * 	- {@code boolean} containing the value intended for {@link #radarVis}
	 * @return
	 * 	- {@code boolean} {@link #radarVis}
	 */
	public boolean getRadarVis(boolean vis){
		radarVis = vis;
		return radarVis;
	}

	/**
	 * This method will set {@link #extraAmmoVis} to whatever the parameter is and return it to whatever calls it
	 * @param vis
	 * 	- {@code boolean} containing the value intended for {@link #extraAmmoVis}
	 * @return
	 * 	- {@code boolean} {@link #extraAmmoVis}
	 */
	public boolean getExtraAmmoVis(boolean vis){
		extraAmmoVis = vis;
		return extraAmmoVis;
	}

	/**
	 * This method will set {@link #shieldVis} to whatever the parameter is and return it to whatever calls it
	 * @param vis
	 * 	- {@code boolean} containing the value intended for {@link #shieldVis}
	 * @return
	 * 	- {@code boolean} {@link #shieldVis}
	 */
	public boolean getShieldVis(boolean vis){
		shieldVis = vis;
		return shieldVis;
	}

	/**
	 * This method will set {@link #showBriefcase} to whatever the parameter is and return it to whatever calls it
	 * @param vis
	 * 	- {@code boolean} containing the value intended for {@link #showBriefcase}
	 * @return
	 * 	- {@code boolean} {@link #showBriefcase}
	 */
	public boolean getBriefcaseVis(boolean vis){
		showBriefcase = vis;
		return showBriefcase;
	}

	/**
	 * This method will respawn the {@link Radar} at its location if an {@link Enemy} walks over it
	 */
	public void respawnRadar(){
		if(!(gog[radarPos[0]][radarPos[1]] instanceof Enemy))
			gog[radarPos[0]][radarPos[1]] = radar;
	}
	
	/**
	 * This method will respawn the {@link ExtraAmmo} at its location if an {@link Enemy} walks over it
	 */
	public void respawnEAmmo(){		
		if(!(gog[ammoPos[0]][ammoPos[1]] instanceof Enemy))
			gog[ammoPos[0]][ammoPos[1]] = extraAmmo;
	}
	
	/**
	 * This method will respawn the {@link Shield} at its location if an {@link Enemy} walks over it
	 */
	public void respawnShield(){
		if(!(gog[shieldPos[0]][shieldPos[1]] instanceof Enemy))
			gog[shieldPos[0]][shieldPos[1]] = shield;
	}

	/**
	 * This method will pass {@link #gog} to whatever calls it
	 * @return
	 * 	- {@code GameObject[][]} {@link #gog}
	 */
	public GameObject[][] getGOG() {
		return gog;
	}

	/**
	 * This method will overwrite the initial {@link #gog} with one taken from a save state
	 * @param saveGOG
	 * 	- {@code GameObject[][]} taken from a save file
	 */
	public void overwriteGOG(GameObject[][] saveGOG){
		for(int i = 0; i < gog.length; ++i){
			for(int j = 0; j < gog[i].length; ++j){
				gog[i][j] = saveGOG[i][j];
			}
		}
	}

	/**
	 * This method will find the amount of {@link Enemy Enemies} that are in front of the {@link Player} 
	 * when they use the look function.
	 * @param lookPosX2 
	 * 	- {@code int} the second x coordinate
	 * @param lookPosY2 
	 * 	- {@code int} the second y coordinate
	 * @param lookPosX1 
	 * 	- {@code int} the first x coordinate
	 * @param lookPosY1 
	 * 	- {@code int} the first y coordinate
	 * 
	 */
	public void lookReport(int lookPosY1, int lookPosX1, int lookPosY2, int lookPosX2) {
		int count=0;
		if(getObjectAtLocation(lookPosY1, lookPosX1).getObjectName().compareTo("|E|") == 0){
			count++;
			enemyFound = true;
		}else if(getObjectAtLocation(lookPosY2, lookPosX2).getObjectName().compareTo("|E|") == 0){
			count++;
			enemyFound = true;
		}
		else{
			enemyFound = false;
		}
		System.out.println("There are " + count + " enemies in front of you.");
	}
	
	/**
	 * This method will return {@link #enemyFound} to whatever calls it
	 * @return
	 * 	- {@code boolean} {@link #enemyFound}
	 */
	public boolean getEnemyFound(){
		return enemyFound;
	}
}
