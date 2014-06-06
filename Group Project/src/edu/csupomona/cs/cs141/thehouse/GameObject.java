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
	 * This field is the object ID, unique to each object type and used for comparing objects
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
	 * The constructor sets the default name for {@link #objName}, {@link #realName}, and {@link #hiddenName}.
	 */
	public GameObject() {
		objName = EMPTY_OBJECT;
		realName = EMPTY_OBJECT_REAL;
		hiddenName = EMPTY_OBJECT;
	}
	
	/**
	 * This method is only called for the {@link Player}, it instantiates the {@link Player}'s position
	 * @param name
	 * 	- {@code String} containing the displayed name "[P]" for player.
	 * @param x
	 * 	- the x-coordinate of the {@link Player} location.
	 * @param y
	 * 	- the y-coordinate of the {@link Player location.
	 */
	public GameObject(String name, int x, int y){
		objName = name;
		pos[0] = x;
		pos[1] = y;
	}
	
	/**
	 * This method will pass {@link #EMPTY_OBJECT} to whatever calls it
	 * @return
	 * 	{@code String} {@link #EMPTY_OBJECT}
	 */
	public String getBlankName(){
		return EMPTY_OBJECT;
	}
		
	/**
	 * This method changes {@link #objName} to the name given by the object type. For instance, 
	 * {@link Radar} will pass "[D]" as the name {@code parameter}.
	 * @param name 
	 * 	- {@code String} containing the symbol we want to set the object to
	 */
	public void setObjectName(String name){
		objName = name;
	}
	
	/**
	 * This method changes {@link #realName} to the name given by the object type. For instance, 
	 * {@link Radar} will pass "[D]" as the name {@code parameter}.
	 * @param name 
	 * 	- {@code String} containing the symbol we want to set the object to
	 */
	public void setRealName(String name){
		realName = name;
	}
	
	/**
	 * This method will pass {@Link #objName} to whatever calls it
	 * @return 
	 * 	{@code String} {@link #objName}
	 */
	public String getObjectName(){
		return objName;
	}
	
	/**
	 * This method changes the coordinates of the {@link GameObject} to whatever the 
	 * {@code integers} passed to it declare.
	 * @param x
	 * 	- {@code int} containing the new x-coordinate for the {@link GameObject}
	 * @param y
	 * 	- {@code int} containing the new y-coordinate for the {@link GameObject}
	 */
	public void setXY(int x, int y) {
		pos[0] = y;
		pos[1] = x;
	}
	
	/**
	 * This method will pass {@link #pos} to whatever calls it. 
	 * @return
	 * 	- {@code int[]} {@link #pos}
	 */
	public int[] getPosition() {
		return pos;
	}
	
	/**
	 * This method will print whatever {@link #objName} contains to the command prompt
	 */
	public void printObject() {
		System.out.print(objName);
	}
	
	/**
	 * This method will call {@link #setObjectName(String)} while passing {@link #realName} as
	 * the parameter
	 */
	public void showName(){
		setObjectName(realName);
	}
	
	/**
	 * This method will call {@link #setObjectName(String)} while passing {@link #hiddenName} as
	 * the parameter
	 */
	public void hideName(){
		setObjectName(hiddenName);
	}
	
	/**
	 * This method will set {@link #hiddenName} to whatever {@code String} is passed to it
	 * @param name
	 * 	- {@code String} usually containing "[?]" 
	 */
	public void setHiddenName(String name){
		hiddenName = name;
	}
	
	/**
	 * This method checks to see if there is a room at the given location passed to it. It does this by
	 * using xposition2 and i as the position of the checked object, then using an if statement will 
	 * determine if that position is occupied by a {@link Room} object. If it is, it returns {@code false},
	 * if it is not, then it returns {@code true}.
	 * @param xPosition2
	 * 	- {@code int} containing the x-coordinate of the position being checked
	 * @param i
	 * 	- {@code int} containing the y-coordinate of the position being checked
	 * @return
	 * 	- {@code boolean} that is {@code false} when a {@link Room} exists, and {@code true} otherwise
	 */
	public boolean roomExists(int xPosition2, int i) {
		if(xPosition2 == 1 ||xPosition2 == 4||xPosition2 == 7){
			if(i==1||i==4||i==7){
				return false;
			}
		}
		return true;
	}

    /**
     * This method checks to ensure that there is no {@link Room} in the way of the {@link Player} when they 
     * attempt to move north. It does this by calling {@link #roomExists(int, int)} passing {@link #pos pos[0]}
     * {@code - 1} and {@link #pos pos[1]} to it. Subtracting {@code 1} from the position of the object's 
     * x-position will move it up one space. It returns a boolean that is {@code true} when 
     * {@link #roomExists(int, int)} is false, and visa versa.
     * @return
     * 	- {@code boolean} that is {@code true} when {@link #roomExists(int, int)} is false, and visa versa.
     */
    public boolean upPossible(){
    	if(roomExists(pos[0] - 1, pos[1]))
    		return true;
    	else
    		return false;
    }
    
    /**
     * This method checks to ensure that there is no {@link Room} in the way of the {@link Player} when they 
     * attempt to move south. It does this by calling {@link #roomExists(int, int)} passing {@link #pos pos[0]}
     * {@code + 1} and {@link #pos pos[1]} to it. Adding {@code 1} to the position of the object's 
     * x-position will move it down one space. It returns a boolean that is {@code true} when 
     * {@link #roomExists(int, int)} is false, and visa versa.
     * @return
     * 	- {@code boolean} that is {@code true} when {@link #roomExists(int, int)} is false, and visa versa.
     */public boolean downPossible(){
    	if(roomExists(pos[0] + 1, pos[1]))
    		return true;
    	else
    		return false;
    }
     
     /**
      * This method checks to ensure that there is no {@link Room} in the way of the {@link Player} when they 
      * attempt to move east. It does this by calling {@link #roomExists(int, int)} passing {@link #pos pos[0]}
      * and {@link #pos pos[1]}{@code + 1}  to it. Adding {@code 1} to the position of the object's 
      * y-position will move it right one space. It returns a boolean that is {@code true} when 
      * {@link #roomExists(int, int)} is false, and visa versa.
      * @return
      * 	- {@code boolean} that is {@code true} when {@link #roomExists(int, int)} is false, and visa versa.
      */ 
    public boolean rightPossible(){
    	if(roomExists(pos[0], pos[1] + 1))
    		return true;
    	else
    		return false;
    }
    
    /**
     * This method checks to ensure that there is no {@link Room} in the way of the {@link Player} when they 
     * attempt to move west. It does this by calling {@link #roomExists(int, int)} passing {@link #pos pos[0]}
     * and {@link #pos pos[1]}{@code - 1}  to it. Subtracting {@code 1} from the position of the object's 
     * y-position will move it left one space. It returns a boolean that is {@code true} when 
     * {@link #roomExists(int, int)} is false, and visa versa.
     * @return
     * 	- {@code boolean} that is {@code true} when {@link #roomExists(int, int)} is false, and visa versa.
     */
    public boolean leftPossible(){
    	if(roomExists(pos[0], pos[1] - 1))
    		return true;
    	else
    		return false;
    }
    
    /**
     * This method will set the {@link #objId} to the {@code int} passed to it and then returns that ID to 
     * whatever called it.
     * @param id
     * 	- {@code int} containing the ID for use with {@link #compareTo(GameObject)}
     * @return
     * 	- {@code int} {@link #objId}
     */
    public int setObjId(int id){
    	objId = id;
    	return objId;
    }
    
    /**
     * This method will pass the {@link #objId} to whatever calls it
     * @return
     * 	- {@code int} {@link #objId}
     */
    public int getObjID(){
    	return objId;
    }

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(GameObject g) {
		return this.getObjID() - g.getObjID();
	}
}
