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
 * 
 * This class will assign {@link GameObject} to the Extra Bullet class and make it possible for 
 * the {@link Player} to get an extra bullet when the pick it up.
 * 
 * @author Kurt Newcomb, Andrew Nipp, Andrew Niklas, Ben Nickerson
 * 
 */
public class ExtraAmmo extends GameObject {

	/**
	 * This field represents the symbol for the {@link ExtraAmmo} object in the
	 * {@link Grid}.
	 */
	private final String EXTRA_AMMO = "|A|";

	/**
	 * This is the constructor defines {@link #setObjectName(String)} and
	 * {@link GameObject#hiddenName}
	 */
	public ExtraAmmo() {
		setObjectName(EXTRA_AMMO);
		setHiddenName(getBlankName());
	}

	/**
	 * This is the constructor defines {@link #setObjectName(String)} and
	 * {@link GameObject#hiddenName} but with visibility
	 * 
	 * @param vis
	 *            The {@code boolean} for toggling visibility
	 */
	public ExtraAmmo(boolean vis) {
		setRealName(EXTRA_AMMO);
		if (vis) {
			setObjectName(EXTRA_AMMO);
		} else {
			setHiddenName(getBlankName());
		}
	}

	/**
	 * This is the method that adds another bullet to the clip by calling
	 * {@link Player#pickUpBullet(int)}
	 * 
	 * @param ply
	 *            The player object we want to access
	 * @param ammount
	 *            The ammount of ammo to add
	 */
	public void addAmmo(Player ply, int amount) {
		ply.pickUpBullet(amount);
	}

}
