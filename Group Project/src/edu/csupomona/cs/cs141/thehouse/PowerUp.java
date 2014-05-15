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
* @author Kurt
* This is the abstract class for the powerups in the game, which are Invincibility, Radar, and an Extra Bullet.
* 
* This class will have the behaviors for all the powerups and how they will interact with the character.
*/

public abstract class PowerUp extends GameObject {
	
	/**
	 *  {@link PowerUp} This is the boolean that tracks the state of the power up
	 */
	private boolean pwrActive;
	
	/**
	 *  {@link PowerUp} This is the string that holds the name of the powerup
	 */
	private String powerUp;
	
	/**
	 * {@link PowerUp} This method sets what type of power-up the player picks up
	 * @return {@link #powerUp}
	 */
	public String setPowerUpName(String name){
	//set the type of powerup so the program knows what  	
		name = powerUp;
		return powerUp;
	}
	
	/**
	 *  {@link PowerUp} This method checks what powerup it is. 
	 */
	public void getPowerup(){
	// checks the value of the powerup 	
	}
		
	/**
	 *  This method is the power-up.
	 */
	public PowerUp() {
		setObjectName(powerUp);
		pwrActive = false; // false means the player doesn't have a buff 
	}

}
