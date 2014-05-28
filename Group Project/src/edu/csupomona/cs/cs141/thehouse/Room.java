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
	private boolean briefcaseRoom;
	private int objID;
	/**
	 * Sets the name of the room
	 */
	public Room(){
		setObjectName(ROOM_NAME);
		setHiddenName(ROOM_NAME);
		setRealName(ROOM_NAME);
		objID = setObjId(2);
	}
	
	/**
	 * Sets the name of the briefcase room
	 * @param showBriefcase
	 * 			-A boolean that determines the name of the Room with the briefcase
	 */
	public Room(boolean isBriefcase){
		if(isBriefcase){
			briefcaseRoom = true;
			setRealName(BRIEFCASE_NAME);
			setObjectName(ROOM_NAME);
			setHiddenName(ROOM_NAME);
		}
		else{
			briefcaseRoom = false;
			setRealName(ROOM_NAME);
			setObjectName(ROOM_NAME);
			setHiddenName(ROOM_NAME);
		}
		objID = setObjId(2);
	}
	public void changeRoomState(boolean change){
		if(change){
			setHiddenName(ROOM_NAME);
			setObjectName(BRIEFCASE_NAME);
			setRealName(BRIEFCASE_NAME);
		}else{
			setHiddenName(ROOM_NAME);
			setObjectName(BRIEFCASE_NAME);
			setRealName(BRIEFCASE_NAME);
		}
	}
	
	public boolean isBriefcaseRoom(){
		return briefcaseRoom;
	}

}
