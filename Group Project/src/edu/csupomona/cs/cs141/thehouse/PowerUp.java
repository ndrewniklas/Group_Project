package edu.csupomona.cs.cs141.thehouse;
/**
* @author Kurt
* This is the abstract class for the powerups in the game, which are Invincibility, Radar, and an Extra Bullet.
* 
* This class will have the behaviors for all the powerups and how they will interact with the character.
*/
public abstract class PowerUp {

	public boolean pwrActive;
	public String PowerUp;
	
	
	public String setPowerUp(){
	//set the type of powerup so the program knows what  	
		return PowerUp;
	}
	
	public void getPowerup(){
	// checks the value of the powerup 	
		
	}
		
	public PowerUp() {
		// TODO Auto-generated constructor stub
		boolean pwrActive = false; // false means the player doesn't have a buff 
		String PowerUp;
	}

}
