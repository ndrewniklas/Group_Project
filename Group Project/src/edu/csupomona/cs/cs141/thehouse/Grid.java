
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Ben, Andrew
 *
 */
public class Grid {
	
	/**
	 * The grid is a 9x9 array of GameObject
	 */
	private GameObject[][] go = new GameObject[9][9];	
	
	/**
	 * This field holds the briefcase, it will be the same as the {@code String[1][1]} for 
	 * {@link #tempRoomBlock}.
	 */
	private String briefcase = "[!]";
	
	/**
	 * The constructor fills {@link #go} with new {@link GameObject} 
	 */
	public Grid() {
		for(int i = 0; i < go.length; ++i){
			for(int j = 0; j < go[i].length; ++j){
				go[i][j] = new GameObject();
			}
		}
	}
	
	/**
	 * Prints out the name of the object at that location
	 */
	public void printGrid(){
		for(int i = 0; i < go.length; ++i){
			for(int j = 0; j < go[i].length; ++j){
				System.out.print(go[i][j].getObjectName());
			}
			System.out.println();
		}
	}
	
	/**
	 * Sets the name of each room at that position in {@link #go}
	 */
	public void createRooms(){
		go[1][1].setObjectName("[R]");
		go[4][1].setObjectName("[R]");
		go[7][1].setObjectName("[R]");
		go[1][4].setObjectName("[R]");
		go[4][4].setObjectName("[R]");
		go[7][4].setObjectName("[R]");
		go[1][7].setObjectName("[R]");
		go[4][7].setObjectName("[R]");
		go[7][7].setObjectName("[R]");
	}
	
	/**
	 * This method randomly places the briefcase by calling {@link Dice#roll()}. It then creates a temporary
	 * {@code String[][]} that stores the briefcase. Any time a {@link Player} enters a room, it checks to
	 * see if that room corresponds to the temporary room, holding the case. If so, the player wins and the 
	 * game ends.
	 */
	private void setBriefcase() {
	}
	
}
