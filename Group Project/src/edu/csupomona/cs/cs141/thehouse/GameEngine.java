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
 *	The GameEngine takes user commands from {@link UserInterace}, checks with {@link Player},
 * 	and then performs the tasks. It is also where the {@link Enemy} class performs the actions 
 * 	it decides. GameEngine updates the {@link Grid} and retrieves positions from it. GameEngine
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
	/**
	 * Saves the user's command number from {@link UserInterface}
	 */
	private int userCommand;
	/**
	 * Checks to see if the player is dead
	 */
	private boolean plrDead;
	/**
	 * Checks to see if the game is over
	 */
	private boolean gameOver;
	/**
	 * Checks to see if the briefcase is found
	 */
	private boolean foundBriefcase;
	/** 
	 * Checks to see if the user quit from within {@link UserInterface}
	 */
	private boolean userQuit;
	/**
	 * Checks to see if the {@link Player} has found the {@link ExtraBullet}
	 */
	private boolean hasExtraBullet;
	/**
	 * Checks to see if the {@link Player} has found the {@link Radar}
	 */
	private boolean hasRadar;
	/**
	 * Checks to see if the {@link Player} has found the {@link Shield}
	 */
	private boolean hasShield;
	/**
	 * Checks to see if the {@link Player} has ammo left
	 */
	private boolean hasAmmo;
	

    /**
     * The default constructor for GameEngine.
     * 
     * Currently it is only filling up the instances of the classes and forcing {@link #gameOver}
     * to be false.
     */
    public GameEngine(){
    	gameOver = false;
    	ui = new UserInterface();
    	grid = new Grid();
    	plr = new Player();
    	enemy = new Enemy();
    	shield = new Shield();
    	radar = new Radar();
    	extraAmmo = new ExtraAmmo();
    }
    
    // Main game methods
    /**
     * The main loop of the game, this is where all the checks and actions go.
     * The loop is finished once {@link #gameOver} is set to be true either from
     * finding the briefcase, the player dying, or the user quitting.
     */
    public void gameLoop(){
    	while(!gameOver){
    		
    	}
    }
    
    /**
     * The method that will take {@link #userCommand} and perform actions based around it.
     * For example, if the user chooses to move it will call the {@link #plrMoveCMD()} method.
     */
    public void performUserAction(){
    	
    }
    
    /**
     * The method that will interpret the command integer from {@link UserInterface}.
     * Then it will check to see if the {@link Player} has any bullets left.
     * Finally it will fire the gun.
     */
    public void plrShootCMD(){
    	
    }
    
    /**
     * The method that will interpret the command integer from {@link UserInterace}.
     * It will then move the player in the direction specified.
     */
    public void plrMoveCMD(){
    	
    }
    
    /**
     * The method that will have the {@link Enemy} attack the {@link Player}.
     */
    public void enemyAttack(){
    	
    }
    
    /**
     * The method that will have the {@link Enemy} move.
     */
    public void enemyMove(){
    	
    }
    
    /**
     * The method that sets {@link #plrPrevPos} to the last known position of the {@link Player}.
     */
    public void getPlrPreviousPosition(){
    	
    }
    
    /**
     * The method that sets {@link #plrCurrentPos} to the current position of the {@link Player}.
     */
    public void getPlrCurrentPosition(){
    	
    }
    
    /**
     * The method that sets {@link #enemyPrevPos} to the last known position of the {@link Enemy}.
     */
    public void getEnemyPreviousPosition(){
    	
    }
    
    /**
     * The method that sets {@link #enemyCurrentPos} to the current position of the {@link Enemy}.
     */
    public void getEnemyCurrentPosition(){
    	
    }
    
    /**
     * The method that will retrieve the position of each room from {@link Grid} and save 
     * them to the corresponding 2d int array.
     */
    public void setRoomPositions(){
    	
    }
    
    // Methods to check/send fields to other classes    
    /**
     * Used to send out {@link #userCommand} which is set by {@link #performUserAction()}.
     * @return {@link #userCommand} value
     */
    public int getUserCMD(){
    	return userCommand;
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
     * Used to send out {@link #plrCurrentPos} which is set within {@link #getPlrCurrentPosition()}.
     * @return {@link #plrCurrentPos} value
     */
    public int[][] playerCurrentPosition(){
		return plrCurrentPos;
    }
    
    /**
     * Used to send out {@link #plrPrevPos} which is set within {@link #getPlrPreviousPosition()}.
     * @return {@link #plrPrevPos} value
     */
    public int[][] playerPreviousPosition(){
    	return plrPrevPos;
    }
    
    /**
     * Used to send out {@link #enemyCurrentPos} which is set with {@link #getEnemyCurrentPosition()}.
     * @return {@link #enemyCurrentPos} value
     */
    public int[][] enemyCurrentPosition(){
    	return enemyCurrentPos;
    }
    
    /**
     * Used to send out {@link #enemyPrevPos} which is set with {@link #getEnemyPreviousPosition()}.
     * @return {@link #enemyPrevPos} value
     */
    public int[][] enemyPreviousPosition(){
    	return enemyPrevPos;
    }
    
    /**
     * Used to send out and set {@link #extraAmmoPos}.
     * @return {@link #extraAmmoPos}
     */
    public int[][] extraAmmoPosition(){
    	return extraAmmoPos;
    }
    
    /**
     * Used to send out and set {@link #radarPos}.
     * @return {@link #radarPos}
     */
    public int[][] radarPosition(){
    	return radarPos;
    }
    
    /**
     * Used to send out and set {@link #shieldPos}.
     * @return {@link #shieldPos}
     */
    public int[][] shieldPosition(){
    	return shieldPos;
    }
   
    /**
     * Checks to see which room position is asked for then returns that room's position.
     * @param roomNum is to check which room we want.
     * @return The corresponding room number.
     */
    public int[][] roomPositions(int roomNum){
    	if(roomNum == 1)
    		return roomOnePos;
    	else if(roomNum == 2)
    		return roomTwoPos;
    	else if(roomNum == 3)
    		return roomThreePos;
    	else if(roomNum == 4)
    		return roomFourPos;
    	else if(roomNum == 5)
    		return roomFivePos;
    	else if(roomNum == 6)
    		return roomSixPos;
    	else if(roomNum == 7)
    		return roomSevenPos;
    	else if(roomNum == 8)
    		return roomEightPos;
    	else if(roomNum == 9)
    		return roomNinePos;
    	else
    		return null;
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
}
