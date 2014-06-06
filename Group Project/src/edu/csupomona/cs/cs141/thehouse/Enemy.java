/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Group Project: The House
 *
 * A turn-based text game based where the player must go through and find a briefcase without getting
 * caught by enemy ninjas. (Description is subject to change)
 * 
 * Team BA^2KD
 * Ben Nickerson
 * Andrew Niklas
 * Andrew Nipp
 * Kurt Newcomb
 * Dylan Nguyen
 */

package edu.csupomona.cs.cs141.thehouse;

/**
 * This class has parameters that will define the position of the enemy pirate
 * as well as whether they are alive. Furthermore, the methods that will be contained in this class
 * will allow for the pirate to move, and to check for, kill, or be killed by the {@link Player}.
 * @author Dylan Nguyen, Andrew Nipp, Andrew Niklas, Ben Nickerson
 */

public class Enemy extends GameObject {

	/**
	 * This field instantiates {@link Dice}
	 */
	private Dice die = new Dice();
	
	/**
	 * This field represents a pair of {@code integers} that contain the location of the {@link Enemy}
	 */
	private int xPosition;
	private int yPosition;

	/**
	 * This field is part of a pair of {@code integers} that contain the next position of the {@link Player}
	 */
	private int xpre;
	private int ypre;
	
	/**
	 * This field represents the unique ID of the object, used for comparing two {@link GameObject}s
	 */
	private int objID;
	
	/**
	 * This is the string for the name of the radar.
	 */
	private final String ENEMY_NAME = "|E|";


	/**
	 * This constructor will call {@link #setObjectName(String)} with {@link #ENEMY_NAME} as the parameter.
	 * It will then call {@link #setHiddenName(String)} with the {@code String} value returned from 
	 * {@link #getBlankName()} as the parameter. Finally it calls {@link #setObjId(int)} with the
	 * {@link #objID} as the parameter.
	 */
	public Enemy(){
		setObjectName(ENEMY_NAME);
		setHiddenName(getBlankName());
		objID = setObjId(1);
	}

	//This method will show enemy dependent on boolean value taken
	/**
	 * This method determines whether or not to show the {@link Enemy} on the {@link Grid} based on a
	 * {@code boolean} value passed in as a parameter.
	 * @param showEnemy
	 * 	- {@code boolean} that will show the {@link Enemy} if {@code true}, otherwise it will hide the 
	 * 		{@link Enemy}.
	 */
	public Enemy(boolean showEnemy) {
		setRealName(ENEMY_NAME);
		objID = setObjId(1);
		if(showEnemy)
			setObjectName(ENEMY_NAME);
		else
			setHiddenName(getBlankName());
	}

	/**
	 * This method will move the enemy in a random direction based on the {@code int} returned
	 * when {@link Dice#roll(int)} is called. If the {@code int} is {@code 0}, {@code 1}, {@code 2},
	 * or {@code 3} the {@link Enemy} will move up, down, right, or left. It does this by calling 
	 * {@link #setXY(int, int)} after checking that the move will not force them off the {@link Grid} or 
	 * into another {@link Enemy} and finally it calls {@link #cleanEnemies(Grid)}. It will reroll if
	 * the {@code integer} 
	 * @param grid
	 * 	- {@link Grid}
	 */
	public void moveEnemy(Grid grid){
		int ranNum = die.roll(4);		
		xpre = xPosition;
		ypre = yPosition;
		if (ranNum == 0 && upPossible()) {
			if (yPosition - 1 >= 0 && yPosition - 1 <= 8) {
				if (!grid.checkForEnemy(yPosition - 1, xPosition)){
					setXY(xPosition, --yPosition);
					cleanEnemies(grid);
				}
			}
		} else if (ranNum == 1 && downPossible()) {
			if (yPosition + 1 >= 0 && yPosition + 1 <= 8) {
				if (!grid.checkForEnemy(yPosition+1, xPosition)){
					setXY(xPosition, ++yPosition);
					cleanEnemies(grid);
				}
			}
		} else if (ranNum == 2 && rightPossible()) {
			if (xPosition + 1 >= 0 && xPosition + 1 <= 8) {
				if (!grid.checkForEnemy(yPosition, xPosition+1)){
					setXY(++xPosition, yPosition);
					cleanEnemies(grid);
				}
			}
		} else if (ranNum == 3 && leftPossible()) {
			if (xPosition - 1 >= 0 && xPosition - 1 <= 8) {
				if (!grid.checkForEnemy(yPosition, xPosition-1)){
					setXY(--xPosition, yPosition);
					cleanEnemies(grid);
				}
			}
		} else {
			ranNum = die.roll(4);
			moveEnemy(grid);
		}
	}

	/**
	 * This method removes the symbol of the {@link Enemy} after they move to a new position by calling
	 * {@link Grid#changeObjectIntoBlank(int, int).
	 * @param grid
	 * 	- {@link Grid}
	 */
	private void cleanEnemies(Grid grid) {
		if (getYPosition() == getYPre() && getXPosition() == getXPre()) {
		}
		else{
			grid.changeObjectIntoBlank(getYPre(), getXPre());
		}
	}
	/**
	 * This method will pass {@link #yPosition} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #yPosition}
	 */
	public int getYPosition() {
		return yPosition;
	}

	/**
	 * This method will change {@link #yPosition} to whatever {@code int} is passed in as a parameter.
	 * It then returns the new value of {@link #yPosition}
	 * @param y
	 * 	- {@code int} containing the value of whatever {@link #yPosition} is intended to be
	 * @return
	 * 	- {@code int} {@link #yPosition}
	 */
	public int setYPosition(int y){
		yPosition = y;
		return yPosition;
	}

	/**
	 * This method will pass {@link #xPosition} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #xPosition}
	 */
	public int getXPosition() {
		return xPosition;
	}

	/**
	 * This method will change {@link #xPosition} to whatever {@code int} is passed in as a parameter.
	 * It then returns the new value of {@link #xPosition}
	 * @param x
	 * 	- {@code int} containing the value of whatever {@link #xPosition} is intended to be
	 * @return
	 * 	- {@code int} {@link #xPosition}
	 */public int setXPosition(int x){
		xPosition = x;
		return xPosition;
	}

	/**
	* This method will pass {@link #yPre} to whatever calls it
	* @return
	* 	- {@code int} {@link #yPre}
	*/
	public int getYPre() {
		return ypre;
	}

	/**
	 * This method will pass {@link #xPre} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #xPre}
	 */
	public int getXPre() {
		return xpre;
	}

	/**
	 * This method is used in testing to show the position the {@link Enemy}
	 * @param i
	 * 	- {@code int} containing the number of the {@link Enemy} who's position is being checked
	 */
	public void printEnemyPos(int i) {
		System.out.println("Enemy"+ i + ": " + xPosition + " " + yPosition);
	}
}
