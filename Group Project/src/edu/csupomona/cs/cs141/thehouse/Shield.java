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
 * This class will assign the power-up to the invincibility powerup.
 * 
 */
public class Shield extends GameObject {

	
	/**
	 *  This is the int that holds the shield value.
	 */
	private int Shield;
	
	private final String SHIELD = "|S|";
	
	/**
	 * This is the constructor for the shield powerup
	 */
	public Shield() {
		// Invincibility, just felt like making it shield so it is quicker to type
		setObjectName(SHIELD);
		setHiddenName(getBlankName());
	}
	
	public Shield(boolean vis){
		setRealName(SHIELD);
		if(vis)
			setObjectName(SHIELD);
		else
			setHiddenName(getBlankName());		
	}
	
	/**
	 * {@link Player} This is the method that gives invincibility
	 */
	public void blockAttack(){
		
	}
}
