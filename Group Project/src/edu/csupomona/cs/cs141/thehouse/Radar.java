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
 * This class gives the Radar the ability to show where the {@link Room}
 * containing the briefcase is located on the {@link Grid}.
 * 
 * @author Kurt Newcomb, Andrew Nipp, Andrew Niklas, Ben Nickerson
 */
public class Radar extends GameObject {

	/**
	 * This is the string for the name of the radar.
	 */
	private final String RADAR = "|D|";

	/**
	 * This field represents the unique ID of the object, used for comparing two
	 * {@link GameObject}s
	 */
	private int objID;

	/**
	 * This is the constructor for the radar powerup which sets
	 * {@link #setHiddenName(String)} and {@link #setObjectName(String)} to
	 * {@link #RADAR} and {@link #getBlankName()}
	 */
	public Radar() {
		setObjectName(RADAR);
		setHiddenName(getBlankName());
	}

	/**
	 * This is the constructor for the radar powerup which sets
	 * {@link #setHiddenName(String)} and {@link #setObjectName(String)} to
	 * {@link #RADAR} and {@link #getBlankName()} but with the visibly
	 */
	public Radar(boolean vis) {
		setRealName(RADAR);
		objID = setObjId(3);
		if (vis) {
			setObjectName(RADAR);
		} else {
			setHiddenName(getBlankName());
		}
	}

	/**
	 * This is the method that activates the radar powerup by calling
	 * {@link Room#changeRoomState(boolean)} and setting it to {@code true}
	 * 
	 * @param bcRoom
	 *            The room we want to change
	 */
	public void activateRadar(Room bcRoom) {
		bcRoom.changeRoomState(true);
	}

	/**
	 * This is the method that deactivates the radar powerup by calling
	 * {@link Room#changeRoomState(boolean)} and setting it to {@code false}
	 * 
	 * @param bcRoom
	 */
	public void unactivateRadar(Room bcRoom) {
		bcRoom.changeRoomState(false);
	}

}
