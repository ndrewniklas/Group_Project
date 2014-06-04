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
 * This class will assign the power-up to the invincibility power-up.
 * 
 */
public class Shield extends GameObject {


	private final String SHIELD = "|S|";

	private int objID;
	/**
	 * This is the constructor for the shield power-up
	 */
	public Shield() {
		setObjectName(SHIELD);
		setHiddenName(getBlankName());
	}

	public Shield(boolean vis){
		setRealName(SHIELD);
		objID = setObjId(5);
		if(vis)
			setObjectName(SHIELD);
		else
			setHiddenName(getBlankName());		
	}
}
