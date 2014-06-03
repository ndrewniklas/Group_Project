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
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * Instantiation for {@link Grid}
	 */
	private Grid grid;
	
	/**
	 * Instantiation for {@link Player}
	 */
	private Player ply;
	
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

	private boolean playerKilled;

	private int moves; 
	
	
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
    	ply = new Player();
    	grid = new Grid();
    	ui = new UserInterface();
    	grid.setUpGrid();
    	moves = 0;
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
		grid.rePopulateGrid(ply);
		playerDefaultVision();
		ui.printGrid(grid);
		do{
			didPlayerTakeTurn = false;
			while(!didPlayerTakeTurn){			
				ui.printStats(ply);
				ui.mainGameCMD();		//print options available during each turn
				turnSelect();
				playerDefaultVision();
				if(ply.getHasBriefCase()){
					ui.foundBriefcase();
					gameOver = true;
				}
				removeDefaultVision();
				if(didPlayerLook)
					stopPlayerLook(lookDirection);
			}
			objectCheck();
			playerKilled = grid.moveEnemy(grid, ply);
			if(playerKilled){
				ui.playerDies();
				ui.pause();
				if(ply.getNumLives() >= 0){
					playerKilled = false;
				}else{
					gameOver = true;
				}
			}
			if(debugModeState){
				debugMode(true);
			}
				grid.rePopulateGrid(ply);
				playerDefaultVision();
				ui.printGrid(grid);
			moves++;
		}while(!gameOver);
		
		if(ply.getHasBriefCase()){
			ui.congrats();
		}else if(playerKilled){
			ui.missionFailed();
		}
		ui.endScreen();
	}
    
    private void objectCheck() {
    	int[] radarPos = grid.getRadarPos();
    	int[] extraAmmoPos = grid.getExtraAmmoPos();
    	int[] shieldPos = grid.getShieldPos();
    	if (ply.get_yPosition() == radarPos[0] && ply.get_xPosition() == radarPos[1] && !hasRadar) {
    		grid.activateRadar();
    		hasRadar = true;
    		ui.radarActivated();
    	}
    	if (ply.get_yPosition() == extraAmmoPos[0] && ply.get_xPosition() == extraAmmoPos[1] && !hasEAmmo) {
    		grid.getExtraAmmo().addAmmo(ply,1);
    		hasEAmmo = true;
    		ui.ammoActivated();
    	}
    	if (ply.get_yPosition() == shieldPos[0] && ply.get_xPosition() == shieldPos[1] && !hasShield) {
    		grid.getShield().activateShield(ply);
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
    	return ply;
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
    		menuSelection = sc.nextLine().toLowerCase();
    		repeat = true;
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
					fh.fileLander("load", grid, ply);
					ply = fh.loadPlayer();
					gameLoop();
					break;
					
				case "rules":
				case "r":
				case "3":
					ui.rules();
					ui.pause();
					break;
					
				case "options":
				case "o":
				case "4":
					setOption();
					repeat = true;
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

// Main game methods
    
    private void turnSelect() {
		do{
			String dir;
			userChoice = sc.nextLine().toLowerCase();
			repeat = false;
			switch(userChoice) {
				case "dev":
					sc.reset();
					System.out.println("Which dev command do you want to execute?");
					System.out.println("show, hide, check, on, off");
					String cmd = sc.nextLine();
					devMenu(cmd);
					didPlayerTakeTurn = false;
					break;
					
				case "look":
				case "l":
				case "1":
					didPlayerLook = true;
					ui.lookDirections();
					dir = sc.nextLine();
					lookDirection = dir;
					ply.playerLook(grid, dir);
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
					ui.shootTurn();
					dir = sc.nextLine();
					hasBullet  = ply.checkBulletPossession();
					if (hasBullet == true) {
						ply.useBullet();
						ui.shotFired();
						grid.shootGunCheck(ply.get_yPosition(), ply.get_xPosition(), dir);
					} else {
						ui.noBullet();
					}
					didPlayerTakeTurn = true;
					break;
					
				case "options":
				case "o":
				case "4":
					setOption();
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
				System.out.println("Select y");
				int ydir = sc.nextInt();
				System.out.println("Select x");
				int xdir = sc.nextInt();
				sc.nextLine();
				System.out.println(grid.checkForEnemy(ydir, xdir));
				break;					
			case "show":
				grid.changeAllObjectStates(true);
				break;
			case "hide":
				grid.changeAllObjectStates(false);
				break;
			case "on":
				debugMode(true);
				break;
			case "off":
				debugMode(false);	
				break;
			default:
				ui.invalidCMD();
				break;
    	}
    }

    public void movePlayerForTurn(){
		String input = sc.nextLine();
		ply.movePlayer(input);
		grid.rePopulateGrid(ply);
		didPlayerTakeTurn = true;
    }
//    public void playerLook(String dir){
//    	ply.playerLook(grid, dir);
//    }
    public void stopPlayerLook(String dir){
    	ply.stopLooking(grid, dir);
    }
    private void setOption(){
    	ui.options();
    	do {
    		pickOption = sc.nextLine().toLowerCase();
        	repeat = false;
        	switch(pickOption){
    	    	case "ai":
    	    	case "a":
    	    	case "1":
    	    		break;

    	    	case "debug":
    	    	case "d":
    	    	case "2":
    	    		setDebug();
    	    		break;
    	    		
    	    	case "save":
				case "3":
				case "s":
					fh.fileLander("Save", grid, ply);
					break;

    	    	case "exit":
    	    	case "e":
    	    	case "0":
    	    		mainMenuSelect();
    	    		break;
    	    	
    	    	default:
    	    		ui.invalidCMD();
    	    		repeat = true;
    	    		break;
        	}
    	} while(repeat == true);
    	
    }
    private void setDebug(){
		System.out.println("Please Select 'on' or 'off': ");
		debugSelect = sc.nextLine().toLowerCase();
		
		switch(debugSelect){
			case "on":
			case "1":
				debugModeState = true;
				debugMode(true);
				System.out.println("WECLOME TO DEBUG MODE");
				break;
			
			case "off":
			case "2":
				debugModeState = false;
				debugMode(false);
				System.out.println("I guess you like being blind...");
				break;
			
			default:
				ui.invalidCMD();
				break;
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
			grid.getExtraAmmo().addAmmo(ply, 9001);
		}
		else{
			grid.getRadarVis(false);
			grid.getEnemyVis(false);
			grid.getBriefcaseVis(false);
			grid.getShieldVis(false);
			grid.getExtraAmmoVis(false);	
			grid.changeAllObjectStates(false);
			grid.getExtraAmmo().addAmmo(ply, 1);
		}
	}
	
	public void playerDefaultVision(){
		switch(ply.getPlrMoveDirection()){
			case "up":
				ply.naturalVision(ply.getPlrMoveDirection(), grid);
				break;
			case "down":
				ply.naturalVision(ply.getPlrMoveDirection(), grid);
				break;
			case "right":
				ply.naturalVision(ply.getPlrMoveDirection(), grid);
				break;
			case "left":
				ply.naturalVision(ply.getPlrMoveDirection(), grid);
				break;
			default:
				ply.naturalVision("up", grid);
				break;
		}
	}
	
	public void removeDefaultVision(){
		switch(ply.getPlrMoveDirection()){
		case "up":
			ply.removeNaturalVision(ply.getPlrMoveDirection(), grid);
			break;
		case "down":
			ply.removeNaturalVision(ply.getPlrMoveDirection(), grid);
			break;
		case "right":
			ply.removeNaturalVision(ply.getPlrMoveDirection(), grid);
			break;
		case "left":
			ply.removeNaturalVision(ply.getPlrMoveDirection(), grid);
			break;
		default:
			ply.removeNaturalVision("up", grid);
			break;
		}
	}
}
