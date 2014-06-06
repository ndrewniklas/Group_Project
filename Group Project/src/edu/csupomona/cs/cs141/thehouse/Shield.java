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
 * This class represents the shield object, it will protect the {@link Player} from being stabbed
 * by an {@link Enemy} for a set number of turns.
 * 
 * @author Kurt Newcomb, Andrew Nipp, Andrew Niklas, Ben Nickerson
 */
public class Shield extends GameObject {

	/**
	 * This is the string for the name of the {@link Shield}.
	 */
	private final String SHIELD = "|S|";

	/**
	 * This field represents the unique ID of the object, used for comparing two {@link GameObject}s
	 */
	private int objID;

	/**
	 * Sets the name of the Shield on the grid
	 */
	public Shield() {
		setObjectName(SHIELD);
		setHiddenName(getBlankName());
	}

	/**
	 * Sets the name and visibility of the Shield
	 * 
	 * @param vis
	 *            If {@code true} then it will change the name to
	 *            {@link #SHIELD} if not then it will set it to
	 *            {@link #getBlankName()}
	 */
	public Shield(boolean vis) {
		setRealName(SHIELD);
		objID = setObjId(5);
		if (vis) {
			setObjectName(SHIELD);
		} else {
			setHiddenName(getBlankName());
		}
	}

	/**
	 * Activates the shield for the player
	 * 
	 * @param ply
	 *            Calls the {@link Player#setHasShield(boolean)} and sets it to
	 *            {@code true}, it also calls {@link Player#setM(int)} to the
	 *            amount of turns
	 */
	public void activateShield(Player ply, int m) {
		ply.setHasShield(true);
		ply.setM(m);
	}
}
