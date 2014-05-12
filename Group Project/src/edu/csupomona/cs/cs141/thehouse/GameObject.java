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
	
	/**
	 * The constructor sets the default name for {@link #objName}
	 */
	public GameObject() {
		objName = "[ ]";
	}
		
	/**
	 * Changes {@link #objName} to the argument name
	 * @param name, the name we want to set the object to
	 * @return {@link #objName}
	 */
	public String setObjectName(String name){
		objName = name;
		return objName;
	}
	
	/**
	 * Used to send out the name of the object
	 * @return {@link #objName}
	 */
	public String getObjectName(){
		return objName;
	}
}
