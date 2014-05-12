package edu.csupomona.cs.cs141.thehouse;
/**
* @author Kurt
* This is the abstract class for the powerups in the game, which are Invincibility, Radar, and an Extra Bullet.
* 
* This class will have the behaviors for all the powerups and how they will interact with the character.
*/

public abstract class PowerUp {
	
	/**
	 *  @link #PowerUp This is the boolean that tracks the state of the power up
	 */
	public boolean pwrActive;
	
	/**
	 *  @link #PowerUp This is the string that holds the name of the powerup
	 */
	public String PowerUp;
	
	
	/**
	 * @link #Powerup This method sets what type of pwoer-up the player picks up
	 */
	public String setPowerUp(){
	//set the type of powerup so the program knows what  	
		return PowerUp;
	}
	
	/**
	 *  @link #PowerUp This method checks what powerup it is. 
	 */
	public void getPowerup(){
	// checks the value of the powerup 	
	}
		
	/**
	 *  This method is the power-up.
	 */
	public void PowerUp() {
		// TODO Auto-generated constructor stub
		boolean pwrActive = false; // false means the player doesn't have a buff 
		String PowerUp;
	}

}
