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
	
	
	int[] pos = new int[2];

	/**
	 * The constructor sets the default name for {@link #objName}
	 */
	public GameObject() {
		getEmptyObjectName();
	}
	
	public GameObject(String name, int x, int y){
		
		objName = name;
		
		pos[0]=x;
		pos[1]=y;
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
		objName = "| |";
		return objName;
	}
	
	public void setXY(int x, int y) {
		pos[0]=x;
		pos[1]=y;
	}
	
	public int[] getPosition() {
		return pos;
	}
	
	public void printObject() {
		System.out.print(objName);
	}
}
