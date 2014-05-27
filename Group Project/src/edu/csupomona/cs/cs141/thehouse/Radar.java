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
 * This class will assign the power-up to the Radar powerup.
 * 
 */
public class Radar extends GameObject {
	
	/**
	 * This is the string for the name of the radar.
	 */
	private final String RADAR = "|N|";
	/**
	 * This is the constructor for the radar powerup
	 */
	public Radar() {
		setObjectName(RADAR);
		setHiddenName(getBlankName());
	}
	
	public Radar(boolean vis){
		setRealName(RADAR);
		if(vis)
			setObjectName(RADAR);
		else
			setHiddenName(getBlankName());
	}
	
	/**
	 * {@link Radar} This is the method that activates the radar powerup
	 */
	public void activateRadar(){
		
	}
	
}
