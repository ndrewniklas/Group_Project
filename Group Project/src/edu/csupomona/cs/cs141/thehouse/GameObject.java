/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

import java.io.Serializable;

/**
 * @author Andrew
 *
 */
public class GameObject implements Comparable<GameObject>, Serializable{

	/**
	 * This field holds the displayed name of the object
	 */
	private String objName;
	
	/**
	 * This field holds the actual name of the object
	 */
	private String realName;
	
	/**
	 * This field is what will be displayed when the object is hidden
	 */
	private String hiddenName;
	
	/**
	 * This field is the object ID, unique to each object type
	 */
	private int objId;
	
	/**
	 * This field represents an empty object that is hidden
	 */
	private final String EMPTY_OBJECT = "[?]";
	
	/**
	 * This field represents an empty slot that is not hidden
	 */
	private final String EMPTY_OBJECT_REAL = "| |";
	
	
	/**
	 * This field holds the coordinates of an object to make finding it easier.
	 */
	private int[] pos = new int[2];

	/**
	 * The constructor sets the default name for {@link #objName}
	 */
	public GameObject() {
		objName = EMPTY_OBJECT;
		realName = EMPTY_OBJECT_REAL;
		hiddenName = EMPTY_OBJECT;
	}
	
	/**
	 * ONLY CALLED FOR PLAYER!
	 * @param name
	 * @param x
	 * @param y
	 */
	public GameObject(String name, int x, int y){
		objName = name;
		pos[0] = x;
		pos[1] = y;
	}
	
	public String getBlankName(){
		return EMPTY_OBJECT;
	}
		
	/**
	 * Changes {@link #objName} to the argument name
	 * @param name, the name we want to set the object to
	 * @return {@link #objName}
	 */
	public void setObjectName(String name){
		objName = name;
	}
	
	public void setRealName(String name){
		realName = name;
	}
	
	/**
	 * Used to send out the name of the object
	 * @return {@link #objName}
	 */
	public String getObjectName(){
		return objName;
	}
	
	public void setXY(int x, int y) {
		pos[0] = y;
		pos[1] = x;
	}
	
	public int[] getPosition() {
		return pos;
	}
	
	public void printObject() {
		System.out.print(objName);
	}
	
	public void showName(){
		setObjectName(realName);
	}
	
	public void hideName(){
		setObjectName(hiddenName);
	}
	
	public void setHiddenName(String name){
		hiddenName = name;
	}
	
	/**
	 * @param xPosition2
	 * @param i
	 * @return
	 */
	public boolean roomExists(int xPosition2, int i) {
		if(xPosition2 == 1 ||xPosition2 == 4||xPosition2 == 7){
			if(i==1||i==4||i==7){
				return false;
			}
		}
		return true;
	}

    public boolean upPossible(){
    	if(roomExists(pos[0] - 1, pos[1]))
    		return true;
    	else
    		return false;
    }
    public boolean downPossible(){
    	if(roomExists(pos[0] + 1, pos[1]))
    		return true;
    	else
    		return false;
    }
    public boolean rightPossible(){
    	if(roomExists(pos[0], pos[1] + 1))
    		return true;
    	else
    		return false;
    }
    public boolean leftPossible(){
    	if(roomExists(pos[0], pos[1] - 1))
    		return true;
    	else
    		return false;
    }
    
    public int setObjId(int id){
    	objId = id;
    	return objId;
    }
    
    public int getObjID(){
    	return objId;
    }

	@Override
	public int compareTo(GameObject g) {
		return this.getObjID() - g.getObjID();
	}
}
