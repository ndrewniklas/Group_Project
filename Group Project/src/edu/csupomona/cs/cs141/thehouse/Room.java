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
	 * This field represents a {@code constant} {@code String} containing "[R]", the symbol for a 
	 * general {@link Room} object
	 */
	private final String ROOM_NAME = "[R]";
	
	/**
	 * This field represents a {@code constant} {@code String} containing "[B]", the symbol for a 
	 * bomb.
	 */
	private final String BRIEFCASE_NAME = "[B]";

	/**
	 * This field represents a {@code boolean} that is {@code true} when the {@link Room} contains the
	 * bomb and {@code false} otherwise.
	 */
	private boolean briefcaseRoom;
	
	/**
	 * This field represents the unique ID of the object, used for comparing two {@link GameObject}s
	 */
	private int objID;

	/**
	 * Sets up the standard room name and the {@link #setObjId(int)}
	 */
	public Room() {
		setObjectName(ROOM_NAME);
		setHiddenName(ROOM_NAME);
		setRealName(ROOM_NAME);
		objID = setObjId(2);
	}

	/**
	 * Checks to see if the room we are accessing is the briefcase room, if it
	 * is then it will set {@link #briefcaseRoom} to {@code true}, and set the
	 * {@link #setRealName(String)} to {@link #BRIEFCASE_NAME}. If not then it
	 * will set the room name to {@link #ROOM_NAME}
	 * 
	 * @param isBriefcase
	 *            -A boolean that determines the name of the Room with the
	 *            briefcase
	 */
	public Room(boolean isBriefcase) {
		if (isBriefcase) {
			briefcaseRoom = true;
			setRealName(BRIEFCASE_NAME);
			setObjectName(ROOM_NAME);
			setHiddenName(ROOM_NAME);
		} else {
			briefcaseRoom = false;
			setRealName(ROOM_NAME);
			setObjectName(ROOM_NAME);
			setHiddenName(ROOM_NAME);
		}
		objID = setObjId(2);
	}

	/**
	 * Changes the name of the briefcase room to show {@link #BRIEFCASE_NAME}
	 * 
	 * @param change
	 *            The {@code boolean} for changing the name of the room
	 */
	public void changeRoomState(boolean change) {
		if (change) {
			setHiddenName(ROOM_NAME);
			setObjectName(BRIEFCASE_NAME);
			setRealName(BRIEFCASE_NAME);
		} else {
			setHiddenName(ROOM_NAME);
			setObjectName(BRIEFCASE_NAME);
			setRealName(BRIEFCASE_NAME);
		}
	}

	/**
	 * Used to check if the room is a briefcase room
	 * 
	 * @return The {@code boolean} {@link #BRIEFCASE_NAME}
	 */
	public boolean isBriefcaseRoom() {
		return briefcaseRoom;
	}

}
