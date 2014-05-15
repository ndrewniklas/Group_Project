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

	boolean ninjaLive;
	
	int initialX;
	int initialY;
	int xPosition;
	int yPosition;
	int ninjaLife;
	int roomNumber;
	
	public Enemy(){
		setObjectName("[E]");
	}
	/**
	 * {@link #moveNinja()} This method will move the ninja
	 */
	public void moveNinja() {
		
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
