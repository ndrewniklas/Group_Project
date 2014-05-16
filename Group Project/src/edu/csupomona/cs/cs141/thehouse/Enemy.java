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
	
	public Enemy(){
		setObjectName("[E]");
	}
	
	//This method will show enemy dependent on boolean value taken
	public Enemy(boolean showEnemy) {
		if(showEnemy)
			setObjectName("[E]");
		else
			setObjectName("| |");
	}
	
	
	/**
	 * {@link #moveEnemy()} This method will move the player based on user input
	 */
	public void moveEnemy(){	
		xpre = xPosition;
		ypre = yPosition;
		String cmd;
		int ranNum = die.roll(4);
		switch(ranNum){
			case 0:	
				cmd = "up";
			case 1:
				cmd = "down";
			case 2:
				cmd = "right";
			case 3:
				cmd = "left";
			default: 
				cmd = "up";
		}
        if (cmd.equals("up") && upPossible()) {
        	if (yPosition-1 >= 0 && yPosition-1 <= 8) {
				setXY(xPosition, --yPosition);
			}
        } else if (cmd.equals("down") && downPossible()) {
        	if (yPosition+1 >= 0 && yPosition+1 <= 8) {
				setXY(xPosition, ++yPosition);
			}
        } else if (cmd.equals("right") && rightPossible()) {
        	if (xPosition+1 >= 0 && xPosition+1 <= 8) {
				setXY(++xPosition, yPosition);
			}
        } else if (cmd.equals("left") && leftPossible()) {
        	if (xPosition-1 >= 0 && xPosition-1 <= 8) {
				setXY(--xPosition, yPosition);
			}
        }
    }
	
	/**
	 * @return
	 */
	public int get_yPosition() {
		return yPosition;
	}
	
	/**
	 * @return
	 */
	public int get_xPosition() {
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
}
