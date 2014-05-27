/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Andrew
 *
 */
public class Room extends GameObject {
	private final String ROOM_NAME = "[R]";
	private final String BRIEFCASE_NAME = "[B]";
	/**
	 * Sets the name of the room
	 */
	public Room(){
		setObjectName(ROOM_NAME);
		setHiddenName(ROOM_NAME);
		setRealName(ROOM_NAME);
	}
	
	/**
	 * Sets the name of the briefcase room
	 * @param showBriefcase
	 * 			-A boolean that determines the name of the Room with the briefcase
	 */
	public Room(boolean showBriefcase){
		if(showBriefcase){
			setObjectName(BRIEFCASE_NAME);
			setRealName(BRIEFCASE_NAME);
			setHiddenName(ROOM_NAME);
		}
		else{
			setRealName(ROOM_NAME);
			setObjectName(ROOM_NAME);
			setHiddenName(ROOM_NAME);
		}
	}
}
