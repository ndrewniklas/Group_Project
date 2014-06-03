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
	 * Instantiation for {@link File_Handler}
	 */
	private File_Handler fh;
	
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
	
	private boolean didPlayerTakeTurn;
	
	/**
	 * This field will repeat a do while loop if {@code true} in either {@link #mainMenuSelect()}
	 * or {@link #returnToMain()}.
	 */
	private boolean repeat;

	private String pickOption = null;

	private String debugSelect;
	
	private boolean hasBullet = false;

	private boolean hasRadar = false;
	private boolean hasEAmmo = false;
	private boolean hasShield = false;

	private boolean debugModeState;

	private boolean didPlayerLook; 
	
	
	/**
     * The default constructor for GameEngine.
     * 
     * Currently it is only filling up the instances of the classes and forcing {@link #gameOver}
     * to be false.
     */
    public GameEngine(){
    	gameOver = false;
    	foundBriefcase=false;
    	ui = new UserInterface();
    	fh = new File_Handler();
    	plr = new Player();
    	grid = new Grid();
    	ui = new UserInterface();
    	grid.setUpGrid();
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
    	grid.rePopulateGrid(plr);
		playerDefaultVision();
		ui.printGrid(grid);
    	while(!gameOver){
    		//ui.printGrid(grid);
    		didPlayerTakeTurn = false;
    		while(!didPlayerTakeTurn){
        		grid.rePopulateGrid(plr);
    			ui.mainGameCMD(); 		//print options available during each turn
    			System.out.println("Ammo Left: " + plr.ammoAmount());
    			turnSelect();
        		playerDefaultVision();
    			ui.printGrid(grid);
        		if(plr.getHasBriefCase())
        			gameOver = true;
        		removeDefaultVision();
        		if(didPlayerLook)
        			stopPlayerLook(lookDirection);
    		}
    		objectCheck();
			grid.moveEnemy(grid);
			if(debugModeState)
				debugMode(true);
    	}
    	ui.endScreen();
    }
    
    private void objectCheck() {
    	int[] radarPos = grid.getRadarPos();
    	int[] extraAmmoPos = grid.getExtraAmmoPos();
    	int[] shieldPos = grid.getShieldPos();
    	if (plr.get_yPosition() == radarPos[0] && plr.get_xPosition() == radarPos[1] && !hasRadar) {
    		grid.activateRadar();
    		hasRadar = true;
    		ui.radarActivated();
    	}
    	if (plr.get_yPosition() == extraAmmoPos[0] && plr.get_xPosition() == extraAmmoPos[1] && !hasEAmmo) {
    		grid.getExtraAmmo().addAmmo(plr,1);
    		hasEAmmo = true;
    		ui.ammoActivated();
    	}
    	if (plr.get_yPosition() == shieldPos[0] && plr.get_xPosition() == shieldPos[1] && !hasShield) {
    		grid.getShield().activateShield(plr);
    		hasShield = true;
    		ui.shieldActivated();
    	}
    	if(!hasRadar)
    		grid.respawnRadar();
    	if(!hasEAmmo)
    		grid.respawnEAmmo();
    	if(!hasShield)
    		grid.respawnShield();
    }

    public Player getPlayer(){
    	return plr;
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
    		menuSelection = in.next().toLowerCase();
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
					grid = fh.openGrid();
					fh.fileLander("load", grid, plr);
					plr = fh.loadPlayer();
					gameLoop();
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
					ui.endScreen();
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
				case "dev":
					in.reset();
					System.out.println("Which dev command do you want to execute?");
					System.out.println("show, hide, check, on, off");
					String cmd = in.next();
					devMenu(cmd);
					break;
					
				case "look":
				case "l":
				case "1":
					didPlayerLook = true;
					ui.lookDirections();
					dir = in.next();
					lookDirection = dir;
					playerLook(dir);
					//ui.printGrid(grid);
					//stopPlayerLook(dir);
					didPlayerTakeTurn = true;
					break;
				
				case "move":
				case "m":
				case "2":
					ui.moveTurn();
					movePlayerForTurn();
					break;
				
				case "3":
				case "s":
				case "shoot":
					//ui.printGrid(grid);
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
					//ui.printGrid(grid);
					didPlayerTakeTurn = true;
					break;
					
				case "options":
				case "o":
				case "4":
					setOption();
					//ui.printGrid(grid);
					break;
				case "exit":
				case "e":
				case "0":
					ui.endScreen();
					System.exit(0);
					break;
			
				default:
					ui.invalidCMD();
					repeat = true;
					break;
			}
		} while(repeat == true);
	}
    
    public void devMenu(String cmd){
    	switch(cmd){
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
			case "on":
				debugMode(true);
				ui.printGrid(grid);
				break;
			case "off":
				debugMode(false);	
				ui.printGrid(grid);
				break;
			default:
				ui.invalidCMD();
    	}
    }

    public void movePlayerForTurn(){
		String input = in.next();
		plr.movePlayer(input);
		grid.rePopulateGrid(plr);
	//	grid.printGrid();
		didPlayerTakeTurn = true;
    }
    public void playerLook(String dir){
    	plr.playerLook(grid, dir); 
    	//grid.printGrid();
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
    	    		
    	    	case "save":
				case "5":
					fh.fileLander("Save", grid, plr);
					break;

    	    	case "exit":
    	    	case "e":
    	    	case "0":
    	    		mainMenuSelect();
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
				debugModeState = true;
				debugMode(true);
				System.out.println("WECLOME TO DEBUG MODE");
				//setOption();
				break;
			
			case "off":
			case "2":
				debugModeState = false;
				debugMode(false);
				System.out.println("I guess you like being blind...");
				//setOption();
				break;
			
			default:
				ui.invalidCMD();
		}    	
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

	public void debugMode(boolean on){
		if(on){
			grid.getRadarVis(true);
			grid.getEnemyVis(true);
			grid.getBriefcaseVis(true);
			grid.getShieldVis(true);
			grid.getExtraAmmoVis(true);	
			grid.changeAllObjectStates(true);
			grid.getExtraAmmo().addAmmo(plr, 9001);
		}
		else{
			grid.getRadarVis(false);
			grid.getEnemyVis(false);
			grid.getBriefcaseVis(false);
			grid.getShieldVis(false);
			grid.getExtraAmmoVis(false);	
			grid.changeAllObjectStates(false);
			grid.getExtraAmmo().addAmmo(plr, 1);
		}
	}
	
	public void playerDefaultVision(){
		switch(plr.getPlrMoveDirection()){
			case "up":
				plr.naturalVision(plr.getPlrMoveDirection(), grid);
				break;
			case "down":
				plr.naturalVision(plr.getPlrMoveDirection(), grid);
				break;
			case "right":
				plr.naturalVision(plr.getPlrMoveDirection(), grid);
				break;
			case "left":
				plr.naturalVision(plr.getPlrMoveDirection(), grid);
				break;
			default:
				plr.naturalVision("up", grid);
		}
	}
	public void removeDefaultVision(){
		switch(plr.getPlrMoveDirection()){
		case "up":
			plr.removeNaturalVision(plr.getPlrMoveDirection(), grid);
			break;
		case "down":
			plr.removeNaturalVision(plr.getPlrMoveDirection(), grid);
			break;
		case "right":
			plr.removeNaturalVision(plr.getPlrMoveDirection(), grid);
			break;
		case "left":
			plr.removeNaturalVision(plr.getPlrMoveDirection(), grid);
			break;
		default:
			plr.removeNaturalVision("up", grid);
	}
	}
}
