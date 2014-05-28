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
	
	private boolean didPlayerMove;
	
	/**
	 * This field will repeat a do while loop if {@code true} in either {@link #mainMenuSelect()}
	 * or {@link #returnToMain()}.
	 */
	private boolean repeat;

	private String pickOption = null;

	private String debugSelect;

	private boolean hasRadar = false;
	
	private boolean hasBullet = true;

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
    	grid = new Grid();
    	ui = new UserInterface();
    }
    
	/**
	 * 
	 */
	public void startGame() {
		mainMenuSelect();
	}
    
    /**
     * The main loop of the game, this is where all the checks and actions go.
     * The loop is finished once {@link #gameOver} is set to be true either from
     * finding the briefcase, the player dying, or the user quitting.
     */
    public void gameLoop(){
    	while(!gameOver){
    		if(plr.getHasBriefCase())
    			gameOver = true;
    		grid.rePopulateGrid(plr);
    		ui.printGrid(grid);
    		didPlayerMove = false;
    		while(!didPlayerMove){
    			ui.mainGameCMD(); //print options available during each turn
    			turnSelect();
    		}
    		objectCheck();
			grid.moveEnemy(grid);
    	}
    	

    }
    
    private void objectCheck() {
    	int [] radarPos = grid.getRadarPos();
    	if (plr.get_yPosition() == radarPos[0] && plr.get_xPosition() == radarPos[1]) {
    		grid.activateRadar();
    		ui.radarActivated();
    	}
		
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
			ui.mainMenu();
    		menuSelection = in.next();
    		menuSelection = menuSelection.toLowerCase();
    		repeat = false;
    		switch(menuSelection) {
				case "new":
				case "n":
				case "1":
					gameLoop();
					break;
					
				case "continue":
				case "c":
				case "2":
					break;
					
				case "rules":
				case "r":
				case "3":
					ui.rules();
					returnToMain();
					break;
					
				case "options":
				case "o":
				case "4":
					setOption();
					returnToMain();
					break;
					
				case "exit":
				case "e":
				case "0":
					//TODO: We need a less... destructive way to deal with this
					System.exit(0);
					break;
				
				default:
					ui.invalidCMD();
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
				case "r":
				case "0":
					// ui.mainMenu();
					mainMenuSelect();
					break;

				default:
					ui.invalidCMD();
					repeat = true;
					break;
			}
		} while (repeat == true);
	}

	// Main game methods
    
    private void turnSelect() {
		do{
			String dir;
			userChoice = in.next();
			userChoice = userChoice.toLowerCase();
			repeat = false;
			switch(userChoice) {
				case "check":
					in.reset();
					System.out.println("Select y");
					int ydir = in.nextInt();
					System.out.println("Select x");
					int xdir = in.nextInt();
					System.out.println(grid.checkForEnemy(ydir, xdir));
					break;					
				case "show":
					grid.changeAllObjectStates(true);
					ui.printGrid(grid);
					break;
				
				case "hide":
					grid.changeAllObjectStates(false);
					ui.printGrid(grid);
					break;
				
				case "look":
				case "l":
				case "1":
					ui.printGrid(grid);
					ui.lookDirections();
					in.reset();
					dir = in.next();
					playerLook(dir);
					ui.printGrid(grid);
					stopPlayerLook(dir);
					break;
				
				case "move":
				case "m":
				case "2":
					ui.printGrid(grid);
					ui.moveTurn();
					movePlayerForTurn();
					break;
				
				case "3":
				case "s":
				case "shoot":
					ui.printGrid(grid);
					ui.shootTurn();
					in.reset();
					dir = in.next();
					hasBullet  = plr.checkBulletPossession();
					if (hasBullet == true) {
						plr.useBullet();
						ui.shotFired();
						grid.shootGunCheck(plr.get_yPosition(), plr.get_xPosition(), dir);
					} else {
						ui.noBullet();
					}
					ui.printGrid(grid);
					break;
					
				case "options":
				case "o":
				case "4":
					ui.printGrid(grid);
					break;
							
				case "exit":
				case "e":
				case "0":
					System.exit(0);
					break;
			
				default:
					ui.invalidCMD();
					repeat = true;
					break;
			}
		} while(repeat == true);
	}

    public void movePlayerForTurn(){
		String input = in.next();
		plr.movePlayer(input);
    	didPlayerMove = true;
    }
    public void playerLook(String dir){
    	plr.playerLook(grid, dir); 
    }
    public void stopPlayerLook(String dir){
    	plr.stopLooking(grid, dir);
    }
    private void setOption(){
    	ui.options();
    	do {
    		pickOption = in.next();
        	pickOption = pickOption.toLowerCase();
        	switch(pickOption){
    	    	case "ai":
    	    	case "a":
    	    	case "1":
    	    		returnToMain();
    	    		break;

    	    	case "debug":
    	    	case "d":
    	    	case "2":
    	    		setDebug();
    	    		returnToMain();
    	    		break;

    	    	case "exit":
    	    	case "e":
    	    	case "0":
    	    		returnToMain();
    	    		break;
    	    	
    	    	default:
    	    		ui.invalidCMD();
        	}
    	} while(repeat == false);
    	
    }
    private void setDebug(){
		System.out.println("Please Select 'on' or 'off' (all lowercase): ");
		debugSelect = in.next();
		debugSelect = debugSelect.toLowerCase();
		
		switch(debugSelect){
			case "on":
			case "1":
				grid.debugMode(true);
				System.out.println("WECLOME TO DEBUG MODE");
				setOption();
				break;
			
			case "off":
			case "2":
				grid.debugMode(false);
				System.out.println("I guess you like being blind...");
				setOption();
				break;
			
			default:
				ui.invalidCMD();
		}    	
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
	 * @param lookPosY12
	 * @param lookPosX12
	 */
	
	
    /**
     * Used to send out {@link #foundBriefcase} which is checked within {@link #gameLoop()}.
     * @return {@link #foundBriefcase} value
     */
    public boolean plrFoundBriefcase(){
    	return foundBriefcase;
    }


}
