/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Andrew
 *
 */
public class Room extends GameObject {
	/**
	 * Sets the name of the room
	 */
	public Room(){
		setObjectName("[R]");
	}
	
	/**
	 * Sets the name of the briefcase room
	 * @param showBriefcase
	 * 			-A boolean that determines the name of the Room with the briefcase
	 */
	public Room(boolean showBriefcase){
		if(showBriefcase)
			setObjectName("[B]");
		else
			setObjectName("[R]");
	}
}
