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
	private Sound sound;
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
	private String choice;

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
	 * Checks to see if the game is over
	 */
	private boolean gameOver;

	private boolean didPlayerTakeTurn;

	/**
	 * This field will repeat a do while loop if {@code true} in either {@link #mainMenuSelect()}
	 * or {@link #returnToMain()}.
	 */
	private boolean repeat;

	private boolean hasBullet = false;
	private boolean hasRadar = false;
	private boolean hasEAmmo = false;
	private boolean hasShield = false;

	private boolean debugModeState;

	private boolean didPlayerLook;

	private boolean playerKilled;

	private int moves;

	private int m;

	private boolean musicOn = true;

	private boolean newGameStart;



	/**
	 * The default constructor for GameEngine.
	 * 
	 * Currently it is only filling up the instances of the classes and forcing {@link #gameOver}
	 * to be false.
	 */
	public GameEngine(){
		gameOver = false;
		ui = new UserInterface();
		fh = new File_Handler();
		ply = new Player();
		grid = new Grid();
		ui = new UserInterface();
		sound = new Sound();
		grid.setUpGrid();
		moves = 0;
	}

	public void newGame(){
		sound.stopBackgroundLoop();
		sound.shootSound();
		gameOver = false;
		hasBullet = false;
		hasRadar = false;
		hasEAmmo = false;
		hasShield = false;
		debugModeState = false;
		playerKilled = false;
		ui = new UserInterface();
		fh = new File_Handler();
		grid = new Grid();
		ply = new Player();
		ui = new UserInterface();
		sound = new Sound();
		grid.setUpGrid();
		moves = 0;
		m = 0;
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
		removeDefaultVision();
		sound.backgroundMusicLoop();
		do{
			didPlayerTakeTurn = false;
			didPlayerLook = false;
			while(!didPlayerTakeTurn){	
				ui.printStats(ply, hasRadar, ply.getM());
				if(ply.isHasShield()){
					m = ply.getM();
					ply.setM(--m);
				}
				turnSelect1();
				if(ply.getHasBriefCase()){
					sound.stopBackgroundLoop();
					ui.foundBriefcase();
					gameOver = true;
				}
				if(debugModeState){
					debugMode(true);
				}
				if(didPlayerLook &&!gameOver){
					ui.printGrid(grid);
					ply.stopLooking(grid, lookDirection);
					turnSelect2();
				}
				if(debugModeState){
					debugMode(true);
				}
			}
			objectCheck();
			grid.moveEnemy(grid, ply);
			playerKilled = grid.getPlayerKilled();
			if(playerKilled){
				sound.playerDied();
				ui.printGrid(grid);
				if(ply.getNumLives() >= 0){
					playerKilled = false;
				}else{
					gameOver = true;
				}
				ui.playerDies();
				ui.pause();
			}

			grid.rePopulateGrid(ply);
			playerDefaultVision();
			ui.printGrid(grid);	
			//grid.showPlayerDefaultVision(7, 0, false);
			removeDefaultVision();

			moves++;
			if(debugModeState){
				debugMode(true);
			}
		}while(!gameOver);

		if(ply.getHasBriefCase()){
			sound.winGame();
			ui.congrats();
		}else if(playerKilled){
			ui.missionFailed();
		}
		ui.endScreen();
		newGameStart = ui.newGame();
		if(newGameStart){
			newGame();
			gameLoop();
		}else
			System.exit(0);
	}

	public boolean getNewGameStart(){
		return newGameStart;
	}

	public void objectCheck() {
		int[] radarPos = grid.getRadarPos();
		int[] extraAmmoPos = grid.getExtraAmmoPos();
		int[] shieldPos = grid.getShieldPos();
		if (ply.get_yPosition() == radarPos[0] && ply.get_xPosition() == radarPos[1] && !hasRadar) {
			grid.activateRadar();
			hasRadar = true;
			ui.radarActivated();
			sound.foundPowerup();
		}
		if (ply.get_yPosition() == extraAmmoPos[0] && ply.get_xPosition() == extraAmmoPos[1] && !hasEAmmo) {
			grid.getExtraAmmo().addAmmo(ply,1);
			hasEAmmo = true;
			ui.ammoActivated();
			sound.foundPowerup();
		}
		if (ply.get_yPosition() == shieldPos[0] && ply.get_xPosition() == shieldPos[1] && !hasShield) {
			grid.getShield().activateShield(ply, 5);
			hasShield = true;
			ui.shieldActivated();
			sound.foundPowerup();
		}
		if(!hasRadar)
			grid.respawnRadar();
		if(!hasEAmmo)
			grid.respawnEAmmo();
		if(!hasShield)
			grid.respawnShield();
		if(ply.getM() == 0){
			ply.setHasShield(false);
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
	public void mainMenuSelect() {
		do {
			ui.mainMenu();
			choice = sc.nextLine().toLowerCase();
			repeat = true;
			switch(choice) {
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

	/**
	 * first menu to print on each turn
	 */
	public void turnSelect1() {
		ui.turnSelect1();
		do{
			choice = sc.nextLine().toLowerCase();
			repeat = false;
			switch(choice) {
			case "new":
				newGame();
				gameLoop();
				break;
			case "dev":
				sc.reset();
				System.out.println("Which dev command do you want to execute?");
				System.out.println("show, hide, check, on, off");
				String cmd = sc.nextLine();
				devMenu(cmd);
				didPlayerTakeTurn = false;
				ui.printGrid(grid);
				break;

			case "mute":
				musicOn = !musicOn;
				if(musicOn)
					sound.backgroundMusicLoop();
				else
					sound.stopBackgroundLoop();
				break;

			case "look":
			case "l":
			case "1":
				didPlayerLook = true;
				ui.lookDirections();
				lookDirection = sc.nextLine();
				ply.playerLook(grid, lookDirection);
				if(grid.getEnemyFound())
					sound.foundEnemy();
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
				choice = sc.nextLine();
				hasBullet  = ply.checkBulletPossession();
				if (hasBullet == true) {
					sound.shootSound();
					ply.useBullet();
					ui.shotFired();
					grid.shootGunCheck(ply.get_yPosition(), ply.get_xPosition(), choice);
					if(grid.getEnemyDeath())
						sound.enemyDeath();
				} else {
					ui.noBullet();
				}
				didPlayerTakeTurn = true;
				break;

			case "options":
			case "o":
			case "4":
				setOption();
				ui.printGrid(grid);
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
			System.out.println();
		} while(repeat == true);
	}

	/**
	 * menu to print after player has looked
	 * 		(second half of turn)
	 */
	public void turnSelect2() {
		ui.turnSelect2();
		do{
			choice = sc.nextLine().toLowerCase();
			repeat = false;
			switch(choice) {
			case "new":
				newGame();
				gameLoop();
				break;
			case "dev":
				sc.reset();
				System.out.println("Which dev command do you want to execute?");
				System.out.println("show, hide, check, on, off");
				String cmd = sc.nextLine();
				devMenu(cmd);
				didPlayerTakeTurn = false;
				break;

			case "mute":
				musicOn = !musicOn;
				if(musicOn)
					sound.backgroundMusicLoop();
				else
					sound.stopBackgroundLoop();
				break;

			case "move":
			case "m":
			case "1":
				ui.moveTurn();
				movePlayerForTurn();
				break;

			case "shoot":
			case "s":
			case "2":
				ui.shootTurn();
				choice = sc.nextLine();
				hasBullet  = ply.checkBulletPossession();
				if (hasBullet == true) {
					ply.useBullet();
					ui.shotFired();
					grid.shootGunCheck(ply.get_yPosition(), ply.get_xPosition(), choice);
				} else {
					ui.noBullet();
				}
				didPlayerTakeTurn = true;
				break;

			case "options":
			case "o":
			case "3":
				setOption();
				ui.printGrid(grid);
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
			debugModeState = true;
			debugMode(true);
			break;
		case "off":
			debugModeState = true;
			debugMode(false);	
			break;
		default:
			ui.invalidCMD();
			break;
		}
	}

	public void setOption(){
		ui.options();
		choice = sc.nextLine().toLowerCase();
		switch(choice){
		case "debug":
		case "d":
		case "1":
			setDebug();
			break;

		case "save":
		case "2":
		case "s":
			fh.fileLander("Save", grid, ply);
			break;

		case "new":
		case "n":
		case "3":
			newGame();
			gameLoop();
			break;
			
		case "mute":
		case "m":
		case "4":
			musicOn = !musicOn;
			if(musicOn)
				sound.backgroundMusicLoop();
			else
				sound.stopBackgroundLoop();
			break;
			
		case "exit":
		case "e":
		case "0":
			mainMenuSelect();
			break;

		default:
			ui.invalidCMD();
			setOption();
			break;
		}
	}

	public void setDebug(){
		System.out.println("Please Select 'on' or 'off': ");
		choice = sc.nextLine().toLowerCase();

		switch(choice){
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

	public void movePlayerForTurn(){
		String input = sc.nextLine();
		ply.movePlayer(input);
		grid.rePopulateGrid(ply);
		didPlayerTakeTurn = true;
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

	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}
}
