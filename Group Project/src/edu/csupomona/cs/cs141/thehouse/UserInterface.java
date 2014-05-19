/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

import java.util.Scanner;

/**
 * @author Andrew
 *
 */
public class UserInterface {
	
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

	private String input;
	
	Scanner sc = new Scanner(System.in);

	/**
	 * This constructor will build the {@link UserInterface} for use with {@link GameEngine}.
	 * Then it will check after each round whether or not the game has ended and print out the appropriate
	 * results.
	 */
	public UserInterface() {
		
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
	 * 
	 */
	public void welcome() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public void turnMenu() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return
	 */
	public String getOption() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public String getInput() {
		input = sc.nextLine();
		return input;
	}	
}
