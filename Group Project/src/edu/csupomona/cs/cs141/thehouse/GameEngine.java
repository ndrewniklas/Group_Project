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

/**
 * @author Andrew Nipp
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
	 * Instantiation for {@link Grid}
	 */
	private Grid grid;
	
	/**
	 * Instantiation for {@link Player}
	 */
	private Player plr;
	
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
     * The default constructor for GameEngine.
     * 
     * Currently it is only filling up the instances of the classes and forcing {@link #gameOver}
     * to be false.
     */
    public GameEngine(){
    	gameOver = false;
    	grid = new Grid();
    	plr = new Player();
    }
    
    // Main game methods
    /**
     * The main loop of the game, this is where all the checks and actions go.
     * The loop is finished once {@link #gameOver} is set to be true either from
     * finding the briefcase, the player dying, or the user quitting.
     */
    public void gameLoop(){
    	while(!gameOver && plr.getIsAlive()){
    		
    	}
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
     * Used to send out {@link #plrDead} which is checked within {@link #gameLoop()}.
     * @return {@link #plrDead} value
     */
    public boolean isPlrDead(){
    	return plrDead;
    }
    
    /**
     * Used to send out {@link #gameOver} which is checked within {@link #gameLoop()}.
     * @return {@link #gameOver} value
     */
    public boolean isGameOver(){
    	return gameOver;
    }
  
    /**
     * Checks to see if the {@link Player} {@link #hasAmmo}.
     * @return {@link #hasAmmo} value
     */
    public boolean plrHasAmmo(){
    	return hasAmmo;
    }    
    
    /**
     * Checks to see if the {@link Player} has found the {@link ExtraAmmo}.
     * @return {@link #hasExtraBullet} value
     */
    public boolean plrFoundExtraAmmo(){
    	return hasExtraBullet;
    }
    
    /**
     * Checks to see if the {@link Player} has found the {@link Radar}.
     * @return {@link #hasRadar} value
     */
    public boolean plrFoundRadar(){
    	return hasRadar;
    }
    
    /**
     * Checks to see if the {@link Player} has found the {@link Shield}.
     * @return {@link #hasShield} value
     */
    public boolean plrFoundShield(){
    	return hasShield;
    }

	/**
	 * Used to send to send the {@link Grid} to the other classes
	 * @return grid
	 */
	public Grid getGrid() {
		return grid;
	}
}
