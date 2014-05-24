/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Group Project: The House
 *
 * A turn-based text game based where the player must go through and find a briefcase without getting
 * caught by enemy ninjas. (Description is subject to change)
 * 
 * Team BA^2KD
 * Ben Nickerson
 * Andrew Niklas
 * Andrew Nipp
 * Kurt Newcomb
 * Dylan Nguyen
 */

package edu.csupomona.cs.cs141.thehouse;

import java.util.Scanner;

/**
 * 
 *	GameEngine is where almost everything from the other classes contribute to the game.
 *	The GameEngine takes user commands from {@link UserInterface}, checks with {@link Player},
 * 	and then performs the tasks. It is also where the {@link Enemy} class performs the actions 
 * 	it decides. GameEngine updates the {@link Grid} and retrieves positions from it. GameEngine
 * 	also checks to see if the {@link Player} has picked up any {@link PowerUp}s or if they have 
 * 	ammo left.
 */
public class GameEngine {
	
	/**
	 * Instantiation for {@link Scanner}
	 */
	private Scanner in = new Scanner(System.in);
	
	/**
	 * Instantiation for {@link Grid}
	 */
	private Grid grid;
	
	/**
	 * Instantiation for {@link Player}
	 */
	private Player plr;
	
	/**
	 * Instantiation for {@link UserInterface}
	 */
	private UserInterface ui;
	
	/**
	 * Checks to see if the game is over
	 */
	private boolean gameOver;
	
	/**
	 * Checks to see if the briefcase is found
	 */
	private boolean foundBriefcase;
	
	/** 
	 * Checks to see if the user quit 
	 */
	private boolean userQuit;
	
	/**
	 * This field holds the choice of the user. There are xx potential values for the user to select:
	 * left,
	 * right,
	 * up,
	 * down,
	 * shoot,
	 * save,
	 * exit
	 */
	private String userChoice;
	
	private String moveChoice;

	/**
	 * This field holds the choice of direction for which the user will "look" before each turn.
	 * There are four directions: 
	 * left,
	 * right,
	 * up,
	 * down
	 */
	private String lookDirection;

	/**
	 * This field holds a string set by the user. It can be any of the following:
	 * 
	 */
	private String menuSelection;

	private String input;

	private String option;

	private String cmd;
	
	/**
	 * This field will repeat a do while loop if {@code true} in either {@link #mainMenuSelect()}
	 * or {@link #returnToMain()}.
	 */
	private boolean repeat;

	/**
     * The default constructor for GameEngine.
     * 
     * Currently it is only filling up the instances of the classes and forcing {@link #gameOver}
     * to be false.
     */
    public GameEngine(){
    	gameOver = false;
    	foundBriefcase=false;
    	plr = new Player();
    	ui = new UserInterface();
    	mainMenuSelect();
    	grid = new Grid();
    	ui = new UserInterface();
    	
    }
    
    //For use with navigating the main menu
    /**
     * This method is called after {@link UserInterface#mainMenu()} prints the menu. It uses a 
     * switch statement to check each potential command, and a do-while loop to ensure that 
     * proper input is received. When {@code default} is used, the code repeats, otherwise a 
     * specific command is called:
     * Specifically, "new" starts a new game
     * 			   , "continue" continues the game from its last state
     * 			   , "rules" calls {@link UserInterface#rules()}
     * 			   , "options" calls {@link UserInterface#options()}
     * 			   , "exit" exits the program 
     */
    private void mainMenuSelect() {
		do {
    		menuSelection = in.next();
    		menuSelection = menuSelection.toLowerCase();
    		repeat = false;
    		switch(menuSelection) {
			case "new": 
				gameLoop();
				break;
			case "continue":
				break;
			case "rules":
				ui.rules();
				returnToMain();
				break;
			case "options":
				ui.options();
				returnToMain();
				break;
			case "exit":
				System.exit(0);
				break;
			default:
				ui.invalid();
				repeat = true;
				break;
			}
		} while(repeat == true);
	}

	/**
	 * This method contains a switch statement and do while loop that essentially serves to return the 
	 * user to the main menu.
	 */
	private void returnToMain() {
		do{
			menuSelection = in.next();
			menuSelection = menuSelection.toLowerCase();
			switch (menuSelection) {
			case "return":
				ui.mainMenu();
				mainMenuSelect();
				break;
			default:
				ui.invalid();
				repeat = true;
				break;
			}
		} while(repeat == true);
	}

	// Main game methods
    /**
     * The main loop of the game, this is where all the checks and actions go.
     * The loop is finished once {@link #gameOver} is set to be true either from
     * finding the briefcase, the player dying, or the user quitting.
     */
    public void gameLoop(){
    	grid.setBriefcase();
    	grid.setEnemy();
    	grid.rePopulateGrid(plr);
    	ui.printGrid(grid);
    	while(true) {
    		ui.topTurn();
    		topTurnSelect();
    		grid.rePopulateGrid(plr);
    		ui.printGrid(grid);
    	while(!gameOver){
    		grid.printGrid();
    		ui.turnMenu(); //print options available during each turn
    		option = ui.getOption(); //gets menu choice from player
    		
    		switch (option) {
				case "move":
					cmd = ui.getInput();
					plr.movePlayer(cmd);
					break;
					
				case "look":
					cmd = ui.getInput();
					plr.lookCheck();
					break;
				
				case "":
					
					break;
	
				default:
					break;
				}
	    	plr.movePlayer(input);    		
    		}
    	}
    }
    
    private void topTurnSelect() {
		do{
			userChoice = in.next();
			userChoice = userChoice.toLowerCase();
			repeat = false;
			switch(userChoice) {
			case "look":
				break;
			case "move":
				ui.moveTurn();
				moveChoice = in.next();
				plr.movePlayer(moveChoice);
				break;
			case "shoot":
				break;
			case "options":
				break;
			case "exit":
				System.exit(0);
				break;
			default:
				ui.invalid();
				repeat = true;
				break;
			}
		} while(repeat == true);
	}

	/**
     * The method that will retrieve the position of each room from {@link Grid} and save 
     * them to the corresponding local 2d String array.
     */
    public void setRoomPositions(){
    	
    }
    
    // Methods to check/send fields to other classes    
    /**
     * Used to set {@link #lookDirection} and {@link #userChoice}.
     * @param
     * 		lookDir - The direction chosen by the user to look, saved to {@link #lookDirection}
     * @param
     * 		choice - The choice of the user saved to a {@link #userChoice}. 		 
     */		
    public void setUserCMD(String lookDir, String choice){
    	
    }

    /**
     * Used to send out {@link #userQuit} which is checked by {@link #performUserAction()}.
     * @return {@link #userQuit} value
     */
    public boolean userQuitGame(){
    	return userQuit;
    }

    /**
     * Used to send out {@link #foundBriefcase} which is checked within {@link #gameLoop()}.
     * @return {@link #foundBriefcase} value
     */
    public boolean plrFoundBriefcase(){
    	return foundBriefcase;
    }

	/**
	 * 
	 */
	public void startGame() {
		ui.welcome();
		gameLoop();
	}
}
