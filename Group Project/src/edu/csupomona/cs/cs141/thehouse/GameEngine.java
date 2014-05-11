/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Andrew
 *
 */
public class GameEngine {
	private UserInterface ui;
	private Grid grid;
	private Player plr;
	private Enemy enemy;
	private PowerUp pwrup;
	
	private int[][] plrCurrentPos;
	private int[][] plrPrevPos;
	private int[][] enemyCurrentPos;
	private int[][] enemyPrevPos;
	private int[][] extraAmmoPos;
	private int[][] radarPos;
	private int[][] shieldPos;
	private int[][] roomOnePos;
	private int[][] roomTwoPos;
	private int[][] roomThreePos;
	private int[][] roomFourPos;
	private int[][] roomFivePos;
	private int[][] roomSixPos;
	private int[][] roomSevenPos;
	private int[][] roomEightPos;
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
