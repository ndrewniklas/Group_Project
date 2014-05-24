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
		mainMenu();
	}
	
	/**
	 * This method will print the main menu of the game to the user and give options such as, "continue"
	 * 		or "new game".
	 */
	public void mainMenu() {
		System.out.println("Welcome to the House Game!");
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("New");
		System.out.println("Continue");
		System.out.println("Rules");
		System.out.println("Options");
		System.out.println("Exit");
		System.out.println("Please enter one of the commands:");
	}
	
	/**
	 * This method is called by {@link #main Menu()}. 
	 */
	public void rules() {
		System.out.println("The rules to the game are very simple:");
		System.out.println("You will be spawned in the bottom left-hand corner of a building. There will "
						 + "also be six enemies in the building with you. \nLocated throughout the building "
						 + "are nine rooms and in one of these rooms is a briefcase. \nFind the briefcase"
						 + "and win the game.");
		System.out.println();
		System.out.println("User Commands: ");
		System.out.println("You have several options available to you while the game is running.");
		System.out.println("1. Left - moves you to the left one space");
		System.out.println("2. Right - moves you to the right one space");
		System.out.println("3. Up - moves you up one space");
		System.out.println("4. Down - moves you down one space");
		System.out.println("5. Look - you can use this at the start of every round, it lets you see two"
						          + " blocks in any direction.");
		System.out.println("6. Shoot - you can use this if you have a bullet left. It will kill one enemy"
								   + " if in the path of the bullet.");
		System.out.println("7. Save - use this to save the game, and return to it later by selecting"
				                  + " 'continue' in the main menu.");
		System.out.println("8. Exit - use this to quit the game, be aware that progress will not be saved.");
		System.out.println("Type \"return\" to return to the main menu:");
	}
	
	public void options() {
		System.out.println("You can change the options here:");
		System.out.println("1. AI - ");
		System.out.println("2. ");
	}
	 
	/**
	 * This method prints the {@link Grid} in its current state. This will occur after every round. It will
	 * pull the {@link Grid} class from {@link GameEngine#getGrid()} and then call {@link Grid#getfullGrid()}
	 * @param grid 
	 */
	public void printGrid(Grid grid) {
		grid.printGrid();
	}

	public void topTurn() {
		System.out.println("What would you like to do?");
		System.out.println("Look");
		System.out.println("Move");
		System.out.println("Shoot");
		System.out.println("Options");
		System.out.println("Exit");
		System.out.println("Please enter one of the commands:");

	public void welcome() {
		// TODO Auto-generated method stub
		
	}

	public void moveTurn() {
		System.out.println("Which direction will you move?");
		System.out.println("Up");
		System.out.println("Down");
		System.out.println("Left");
		System.out.println("Right");
		System.out.println("Please enter one of the commands:");
	}

	public void invalid() {
		System.out.println("Invalid Command: Try Again");
		
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
