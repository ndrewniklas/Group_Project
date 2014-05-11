/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Group Project: The House
 *
 * A turn-based text game based where the player must go through and find a briefcase without getting
 * caught by enemy spies. (Description is subject to change)
 * 
 * Team BA^2KD
 * Ben Nickerson
 * Andrew Niklas
 * Andrew Nipp
 * Kurt Newcomb
 * Dylan Nguyen
 */

package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Andrew Nipp
 * 
 *	GameEngine is where almost everything from the other classes contribute to the game
 *	The GameEngine takes user commands from {@link UserInterace}, checks with {@link Player},
 * 	and then performs the tasks. It is also where the {@link Enemy} class performs the actions 
 * 	it decides on. GameEngine updates the grid and retrieves positions from it. GameEngine
 * 	also checks to see if the {@link Player} has picked up any {@link PowerUp}s or if they have 
 * 	ammo left.
 */
public class GameEngine {
	
	
	/**
	 * Instantiation for {@link UserInterface}
	 */
	private UserInterface ui;
	/**
	 * Instantiation for {@link Grid}
	 */
	private Grid grid;
	/**
	 * Instantiation for {@link Player}
	 */
	private Player plr;
	/**
	 * Instantiation for {@link Enemy}
	 */
	private Enemy enemy;
	/**
	 * Instantiation for {@link Shield}
	 */
	private Shield shield;
	/**
	 * Instantiation for {@link Radar}
	 */
	private Radar radar;
	/**
	 * Instantiation for {@link ExtraAmmo}
	 */
	private ExtraAmmo extraAmmo;
		
	/**
	 * Saves the {@link Player}'s current position for use later
	 */
	private int[][] plrCurrentPos;
	/**
	 * Saves the {@link Player}'s last known position for use later
	 */
	private int[][] plrPrevPos;
	/**
	 * Saves the {@link Enemy}'s current position for use later
	 */
	private int[][] enemyCurrentPos;
	/**
	 * Saves the {@link Enemy}'s last known position for use later
	 */
	private int[][] enemyPrevPos;
	/**
	 * Saves the location of {@link ExtraAmmo} for use later
	 */
	private int[][] extraAmmoPos;
	/**
	 * Saves the location of {@link Radar} for use later
	 */
	private int[][] radarPos;
	/**
	 * Saves the location of {@link Shield} for use later
	 */
	private int[][] shieldPos;
	/**
	 * Saves the location of the first room
	 */
	private int[][] roomOnePos;
	/**
	 * Saves the location of the second room
	 */
	private int[][] roomTwoPos;
	/**
	 * Saves the location of the third room
	 */
	private int[][] roomThreePos;
	/**
	 * Saves the location of the fourth room
	 */
	private int[][] roomFourPos;
	/**
	 * Saves the location of the fifth room
	 */
	private int[][] roomFivePos;
	/**
	 * Saves the location of the sixth room
	 */
	private int[][] roomSixPos;
	/**
	 * Saves the location of the seventh room
	 */
	private int[][] roomSevenPos;
	/**
	 * Saves the location of the eighth room
	 */
	private int[][] roomEightPos;
	/**
	 * Saves the location of the ninth room
	 */
	private int[][] roomNinePos;
	private int plrCommand;
	private boolean plrDead;
	private boolean gameOver;
	private boolean foundBriefcase;
	private boolean hasExtraBullet;
	private boolean hasRadar;
	private boolean hasShield;
	private boolean hasAmmo;
	

    public GameEngine(){
    	ui = new UserInterface();
    	grid = new Grid();
    	plr = new Player();
    	enemy = new Enemy();
    	shield = new Shield();
    	radar = new Radar();
    	extraAmmo = new ExtraAmmo();
    }
    
    // Main game methods
    public void gameLoop(){

    }
    
    public void performPlrAction(){
    	
    }
    
    public void plrShootCMD(){
    	
    }
    
    public void plrMoveCMD(){
    	
    }
    
    public void enemyAttack(){
    	
    }
    
    public void enemyMove(){
    	
    }

    // Methods to check/send fields to other classes    
    public int getPlrCMD(){
    	return plrCommand;
    }
    

    public boolean plrFoundBriefcase(){
    	return foundBriefcase;
    }
    
    public boolean isPlrDead(){
    	return plrDead;
    }
    
    public boolean isGameOver(){
    	return gameOver;
    }
 
    public int[][] playerCurrentPosition(){
		return plrCurrentPos;
    }
    
    public int[][] playerPreviousPosition(){
    	return plrPrevPos;
    }
    
    public int[][] enemyCurrentPosition(){
    	return enemyCurrentPos;
    }
    
    public int[][] enemyPreviousPosition(){
    	return enemyPrevPos;
    }
    
    public int[][] extraAmmoPosition(){
    	return extraAmmoPos;
    }
    
    public int[][] radarPosition(){
    	return radarPos;
    }
    
    public int[][] shieldPosition(){
    	return shieldPos;
    }
    
    public int[][] roomOnePosition(){
    	return roomOnePos;
    }
    
    public int[][] roomTwoPosition(){
    	return roomTwoPos;
    }
    
    public int[][] roomThreePosition(){
    	return roomThreePos;
    }
    
    public int[][] roomFourPosition(){
    	return roomFourPos;
    }
    
    public int[][] roomFivePosition(){
    	return roomFivePos;
    }
    
    public int[][] roomSixPosition(){
    	return roomSixPos;
    }
    
    public int[][] roomSevenPosition(){
    	return roomSevenPos;
    }
    
    public int[][] roomEightPosition(){
    	return roomEightPos;
    }
    
    public int[][] roomNinePosition(){
    	return roomNinePos;
    }
    
    public boolean plrHasAmmo(){
    	return hasAmmo;
    }    
    
    public boolean plrFoundExtraAmmo(){
    	return hasExtraBullet;
    }
    
    public boolean plrFoundRadar(){
    	return hasRadar;
    }
    
    public boolean plrFoundShield(){
    	return hasShield;
    }
}
