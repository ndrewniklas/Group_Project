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
 * @author Dylan Nguyen
 * The {@link Enemy} class has parameters that will define the position of the enemy ninja
 * as well as status of life. Furthermore, the methods that will be contained in this class
 * will allow for the ninja to move, check for the player, kill the player, and die by the player.
 */

public class Enemy extends GameObject {

	private boolean enemyDead;
	private Dice die = new Dice();
	private int initialX;
	private int initialY;
	private int xPosition;
	private int yPosition;
	private int xpre;
	private int ypre;
	
	private final String ENEMY_NAME = "|E|";
	
	
	private int[] newPos = new int[2];
	
	public Enemy(){
		setObjectName(ENEMY_NAME);
	}
	
	//This method will show enemy dependent on boolean value taken
	public Enemy(boolean showEnemy) {
		if(showEnemy)
			setObjectName(ENEMY_NAME);
		else
			setObjectName("| |");
	}
	
	/**
	 * {@link #moveEnemy()} This method will move the enemy
	 */
	public void moveEnemy(Grid grid){
		
			int ranNum = die.roll(4);		
			xpre = xPosition;
			ypre = yPosition;
			if (ranNum == 0 && upPossible()) {
				if (yPosition - 1 >= 0 && yPosition - 1 <= 8) {
					if (grid.checkIfLocationFree(yPosition-1, xPosition)){
						setXY(xPosition, --yPosition);
						cleanEnemies(grid);
					}
				}
			} else if (ranNum == 1 && downPossible()) {
				if (yPosition + 1 >= 0 && yPosition + 1 <= 8) {
					if (grid.checkIfLocationFree(yPosition+1, xPosition)){
						setXY(xPosition, ++yPosition);
						cleanEnemies(grid);
					}
				}
			} else if (ranNum == 2 && rightPossible()) {
				if (xPosition + 1 >= 0 && xPosition + 1 <= 8) {
					if (grid.checkIfLocationFree(yPosition, xPosition+1)){
						setXY(++xPosition, yPosition);
						cleanEnemies(grid);
					}
				}
			} else if (ranNum == 3 && leftPossible()) {
				if (xPosition - 1 >= 0 && xPosition - 1 <= 8) {
					if (grid.checkIfLocationFree(yPosition, xPosition-2)){
						setXY(--xPosition, yPosition);
						cleanEnemies(grid);
					}
				}
			} else {
				ranNum = die.roll(4);
				moveEnemy(grid);
			}
	}
	
	private void cleanEnemies(Grid grid) {
		if (getYPosition() == getYPre() && getXPosition() == getXPre()) {
		}
		else{
			grid.changeObjectIntoBlank(getYPre(), getXPre());
		}
	}
	/**
	 * @return
	 */
	public int getYPosition() {
		return yPosition;
	}
	
	public int setYPosition(int y){
		yPosition = y;
		return yPosition;
	}
	
	/**
	 * @return
	 */
	public int getXPosition() {
		return xPosition;
	}
	
	public int setXPosition(int x){
		xPosition = x;
		return xPosition;
	}
	
	/**
	 * @return
	 */
	public int getYPre() {
		return ypre;
	}
	
	/**
	 * @return
	 */
	public int getXPre() {
		return xpre;
	}
	
	/**
	 * {@link #checkForPlayer()} This method will let the enemy check if the player is
	 * in the adjacent space
	 */
	public void checkForPlayer() {
		
	}
	
	/**
	 * {@link #killPlayer()} This method will kill the player
	 */
	public void killPlayer() {
		
	}
	
	/**
	 * {@link #ninjaDies()} This method will kill the ninja if the player fires a bullet
	 */
	public void ninjaDies() {
		
	}
	
	public void printEnemyPos(int i) {
		System.out.println("Enemy"+ i + ": " + xPosition + " " + yPosition);
	}
}
