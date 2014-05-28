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
 * This class will assign the power-up to the Extra Bullet powerup.
 * 
 */
public class ExtraAmmo extends GameObject {

	/**
	 * This is the string for holding the amount of bullets.
	 */
	private String ExtraAmmo;
	
	private final String EXTRA_AMMO= "|A|";
	
	/**
	 * This is the method that gives powerup its type.  
	 */
	public ExtraAmmo() {
		// TODO Auto-generated constructor stub
		setObjectName(EXTRA_AMMO);
		setHiddenName(getBlankName());		
	}
	public ExtraAmmo(boolean vis){
		setRealName(EXTRA_AMMO);
		if(vis)
			setObjectName(EXTRA_AMMO);
		else
			setHiddenName(getBlankName());
	}
	 
	/**
	 * This is the method that adds another bullet to the clip. 
	 */
	public void addAmmo(Player ply, int amount){
		ply.pickUpBullet(amount);
	}

}
