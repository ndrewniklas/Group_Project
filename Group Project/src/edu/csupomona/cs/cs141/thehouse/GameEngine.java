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
 *	GameEngine is where almost everything from the other classes contribute to the game.
 *	The GameEngine takes user commands from {@link UserInterface}, checks with {@link Player},
 * 	and then performs the tasks. It is also where the {@link Enemy} class performs the actions 
 * 	it decides. GameEngine updates the {@link Grid} and retrieves positions from it. GameEngine
 * 	also checks to see if the {@link Player} has picked up any {@link PowerUp}s or if they have 
 * 	ammo left.
 * 
 * @author Andrew Nipp, Andrew Niklas, Ben Nickerson, Kurt Newcomd, Dylan Nguyen
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

	/**
	 * This field represents a {@code boolean} that is {@code true} whenever the {@link Player} takes a turn
	 */
	private boolean didPlayerTakeTurn;

	/**
	 * This field will repeat a do while loop if {@code true} in either {@link #mainMenuSelect()}
	 * or {@link #returnToMain()}.
	 */
	private boolean repeat;

	/**
	 * This field represents one of the 4 {@code booleans} in charge of determining what is in the {@link 
	 * Player}'s possession.
	 */
	private boolean hasBullet = false;
	private boolean hasRadar = false;
	private boolean hasEAmmo = false;
	private boolean hasShield = false;

	/**
	 * This field represents a {@code boolean} that is {@code true} whenever debug mode is on
	 */
	private boolean debugModeState;

	/**
	 * This field represents a {@code boolean} that is {@code true} whenever the {@link Player} uses
	 * the look function
	 */
	private boolean didPlayerLook;

	/**
	 * This field represents a {@code boolean} that is {@code true} whenever the player is killed by an 
	 * {@link Enemy}
	 */
	private boolean playerKilled;

	/**
	 * This field is an {@code int} that tracks the {@link Player}'s moves
	 */
	private int moves;

	/**
	 * This field is an {@code int} that assigns a number of moves that the {@link Shield} object will be
	 * active for after being enabled.
	 */
	private int m;

	/**
	 * This field is a {@code boolean} that is true whenever the music is on
	 */
	private boolean musicOn = true;

	/**
	 * This field represents a {@code boolean} that is true whenever a new game is started
	 */
	private boolean newGameStart;



	/**
	 * The default constructor for GameEngine. It instantiates all of the other objects: {@link UserInterface},
	 * {@link File_Handler}, {@link Player}, {@link Grid}, and {@link Sound}. It then calls {@link Grid#setUpGrid()}
	 * to populate it and sets the {@link #moves} to {@code 0}.
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

	/**
	 * This constructor is used whenever the user beats the game and starts a new one. It follows the 
	 * same instantiation as the initial constructor, but also sets all {@code boolean} values to 
	 * {@code false}. 
	 */
	public void newGame(){
		sound.shootSound();
		musicOn = true;
		gameOver = false;
		hasBullet = false;
		hasRadar = false;
		hasEAmmo = false;
		hasShield = false;
		debugModeState = false;
		playerKilled = false;
		fh = new File_Handler();
		grid = new Grid();
		ply = new Player();
		grid.setUpGrid();
		moves = 0;
		m = 0;
	}

	/**
	 * This method simply calls {@link #mainMenuSelect()}, but exists to be called from {@link Main}
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

	/**
	 * This method will return the {@code boolean} {@link #newGameStart} to whatever calls it
	 * @return
	 * 	- {@code boolean} {@link #newGameStart}
	 */
	public boolean getNewGameStart(){
		return newGameStart;
	}

	/**
	 * This method checks every turn to see if there is an object wherever the {@link Player} moved.
	 * It does this by comparing the {@link Player#get_yPosition()} and {@link Player#get_xPosition()}
	 * to the location of the {@link GameObject}s. Each power up has a different set of methods that it will
	 * call when activated, but all of them call {@link Sound#foundPowerup()}.
	 */
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
				ui.exitScreen();
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
	 * This method is the top menu during the beginning of each round. It calls {@link UserInterface#turnSelect1()}
	 * to print out the menu, then asks for input. The input is then run through a {@code switch} statement.
	 * There are 8 {@code cases} for the statement:
	 * 	"new" - calls {@link #newGame()} and starts over again;
	 * 	"dev" - calls up the dev menu, which basically allows the user to enter god mode;
	 * 	"mute" - sets {@link #musicOn} to false and calls {@link Sound#stopBackgroundLoop()};
	 * 	"look" - calls up {@link UserInterface#lookDirections()} and moves the user to that menu;
	 * 	"move" - calls up {@link UserInterface#moveTurn()} and allows the user to select a direction;
	 * 	"shoot" - calls up {@link UserInterface#shootTurn()} and allows the user to select a direction;
	 * 	"options" - calls up {@link UserInterface#options()} and allows the user to make a selection before 
	 * 		returning to the game;
	 * 	"exit" - exits the game;
	 * Finally, the {@code default case} will simply restart the {@code do-while} loop and call
	 * {@link UserInterface#invalidCMD()}.
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
				ui.exitScreen();
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
	 * This method is the top menu during the beginning of each round specifically after the look function 
	 * has been used. It calls {@link UserInterface#turnSelect2()} to print out the menu, 
	 * then asks for input. The input is then run through a {@code switch} statement.
	 * There are 8 {@code cases} for the statement:
	 * 	"new" - calls {@link #newGame()} and starts over again;
	 * 	"dev" - calls up the dev menu, which basically allows the user to enter god mode;
	 * 	"mute" - sets {@link #musicOn} to false and calls {@link Sound#stopBackgroundLoop()};
	 * 	"move" - calls up {@link UserInterface#moveTurn()} and allows the user to select a direction;
	 * 	"shoot" - calls up {@link UserInterface#shootTurn()} and allows the user to select a direction;
	 * 	"options" - calls up {@link UserInterface#options()} and allows the user to make a selection before 
	 * 		returning to the game;
	 * 	"exit" - exits the game;
	 * Finally, the {@code default case} will simply restart the {@code do-while} loop and call
	 * {@link UserInterface#invalidCMD()}.
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
				ui.exitScreen();
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
	 * This method is called whenever "dev" is selected in one of the {@link #turnSelect1() turnSelects}. It 
	 * will ask for a new input and send that input to this method as a parameter. Dev mode is not very robust
	 * since it is specifically used by the developers, therefore it can crash the game if someone does not
	 * know what they are doing. There are 4 main functions:
	 * 	"check" - asks for input for the (x, y) coordinates and checks to see if an {@link Enemy} is present
	 * 	"show" - shows all objects on the {@link Grid}
	 * 	"hide" - hides all objects on the {@link Grid}
	 * "on/off" - turns dev mode (god mode) on. Shows all objects and gives infinite ammo and shield.
	 * @param cmd
	 * 	- {@code String} containing the value of one of the {@code cases} in the {@code switch} statement.
	 */
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

	/**
	 * This method deals with input received after it calls {@link UserInterface#options()}. It can be called
	 * at any point during a game and has 5 functions:
	 * 	"debug" - turns on debug mode;
	 * 	"save" - saves the game's state by calling {@link File_Handler#fileLander(String, Grid, Player)};
	 * 	"new" - starts a new game;
	 * 	"mute" mutes the music;
	 * 	"exit" - exits the game;
	 * Finally, {@code default} will call itself to essentially loop the method after printing 
	 * {@link UserInterface#invalidCMD()}
	 */
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

	/**
	 * This method is called when debug is selected from the options menus. It only has one function, 
	 * to turn debug on/off. It will print a message displaying verification of the user's selection.
	 */
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

	/**
	 * This method will move the {@link Player} by calling {@link Player#movePlayer(String)} and 
	 * {@link Grid#rePopulateGrid(Player)}. It then sets {@link #didPlayerTakeTurn} to true and moves on.
	 */
	public void movePlayerForTurn(){
		String input = sc.nextLine();
		ply.movePlayer(input);
		grid.rePopulateGrid(ply);
		didPlayerTakeTurn = true;
	}

	/**
	 * This method activates debug mode. Basically it runs through all of the visibilities in {@link Grid}
	 * and sets them to true. It then adds over 9000 to both the ammo supply and shield turns. If 
	 * {@code boolean} on is {@code false} it will do the opposite, but will not remove any bullets or shield
	 * turns. This mode is not recommended while playing the game seriously.
	 * @param on
	 * 	- {@code boolean} containing a value that will activate or deactivate debug mode.
	 */
	public void debugMode(boolean on){
		if(on){
			grid.getRadarVis(true);
			grid.getEnemyVis(true);
			grid.getBriefcaseVis(true);
			grid.getShieldVis(true);
			grid.getExtraAmmoVis(true);	
			grid.changeAllObjectStates(true);
			grid.getExtraAmmo().addAmmo(ply, 9001);
			grid.getShield().activateShield(ply, 9001);
		}
		else{
			grid.getRadarVis(false);
			grid.getEnemyVis(false);
			grid.getBriefcaseVis(false);
			grid.getShieldVis(false);
			grid.getExtraAmmoVis(false);	
			grid.changeAllObjectStates(false);
			ply.forceAmmo(1);
			grid.getShield().activateShield(ply, 5);
		}
	}

	/**
	 * This method gives the {@link Player} sight in the direction that they are looking. It is assumed
	 * that the {@link Player} is looking in the direction that they last moved or looked. Its {@code default case}
	 * assumes the {@link Player} is looking up at spawn.
	 */
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

	/**
	 * This method is needed to ensure that the square that was visible before the {@link Player} moves or\
	 * looks is set back to hidden.
	 */
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
	 * This method will pass {@link #moves} to whatever calls it
	 * @return 
	 * 	- {@code int} {@link #moves}
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * This method will set {@link #m} to whatever is passed in as an {@code int} in the parameter.
	 * @param m
	 * 	- {@code int} containing the intended value for {@link #m}
	 */
	public void setM(int m) {
		this.m = m;
	}
}
