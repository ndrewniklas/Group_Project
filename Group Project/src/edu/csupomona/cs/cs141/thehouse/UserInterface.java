/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Andrew
 *
 */
public class UserInterface {
	
	/**
	 * This field instantiates the {@link GameEngine}
	 */
	private GameEngine game;
	/**
	 * This field holds the choice of the user. There are 7 potential values for the user to select:
	 * left,
	 * right,
	 * up,
	 * down,
	 * shoot,
	 * save,
	 * exit
	 */
	private String choice;
	
	/**
	 * This field holds the direction chosen by the user to look.
	 */
	private String lookDir;

	/**
	 * This constructor will build the {@link UserInterface} for use with {@link GameEngine}.
	 * Then it will check after each round whether or not the game has ended and print out the appropriate
	 * results.
	 */
	public UserInterface() {
		game = new GameEngine();
	}
	
	/**
	 * This method will print the main menu of the game to the user and give options such as, "continue"
	 * 		or "new game".
	 */
	public void mainMenu() {
		
	}
	 
	/**
	 * This method prints the {@link Grid} in its current state. This will occur after every round. It will
	 * pull the {@link Grid} class from {@link GameEngine#getGrid()} and then call {@link Grid#getfullGrid()}
	 */
	public void printGrid() {
		
	}
	
	/**
	 * This method will give the user several options in terms of what can be done, the options are listed
	 * under {@link #choice}.
	 */
	public void userAction() {
		game.setUserCMD(lookDir, choice);
	}
	
}
