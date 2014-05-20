/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Andrew
 *
 */
public class GameObject {

	/**
	 * The name of the object
	 */
	private String objName;
	
	private final String EMPTY_OBJECT = "| |";
	
	int[] pos = new int[2];

	/**
	 * The constructor sets the default name for {@link #objName}
	 */
	public GameObject() {
		getEmptyObjectName();
	}
	
	public GameObject(String name, int x, int y){
		
		objName = name;
		
		pos[0] = x;
		pos[1] = y;
	}
		
	/**
	 * Changes {@link #objName} to the argument name
	 * @param name, the name we want to set the object to
	 * @return {@link #objName}
	 */
	public void setObjectName(String name){
		objName = name;
	}
	
	/**
	 * Used to send out the name of the object
	 * @return {@link #objName}
	 */
	public String getObjectName(){
		return objName;
	}
	
	public String getEmptyObjectName(){
		objName = EMPTY_OBJECT;
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
	
	/**
	 * @param xPosition2
	 * @param i
	 * @return
	 */
	private boolean roomExists(int xPosition2, int i) {
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
}
