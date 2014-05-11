/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Group Project
 *
 *   Team BAAKD
 *   Ben Nickerson
 *   Andrew Nipp
 *   Dylan Nguyen
 *   Kurt Newcomb
 *   Andrew Niklas 
 */


package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Dylan Nguyen
 *
 */
public class Player {

	boolean isAlive;
	boolean isInvincible;
	boolean isDead;
	boolean hasBullet;
	boolean hasInvincibility;
	boolean hasRadar;
	
	int initialX;
	int initialY;
	int xPosition;
	int yPosition;
	int numLives;
	
	
	/**
	 * {@link #movePlayer()} This method will move the player based on user input
	 */
	public void movePlayer() {
		
	}
	
	/**
	 * {@link #playerLook()} This method will reveal two spaces in the desired direction
	 * on the grid based on user input
	 */
	public void playerLook() {
		
	}
	
	/**
	 * {@link #lookCheck()} This method will check what is revealed after the {@link #lookCheck()}
	 * has been executed
	 */
	public void lookCheck() {
		
	}
	
	/**
	 * {@link #checkBulletPossession()} This method will check if there is a bullet in the gun of the
	 * player
	 * @return Will be used to determine if {@link #pickUpBullet(boolean)} and
	 * {@link #useBullet()} can be executed or not
	 */
	public boolean checkBulletPossession() {
		return true;
	}
	
	/**
	 * {@link #pickUpBullet(boolean)} This method will allow the user to pick up a bullet based on the 
	 * @param hasBullet
	 */
	public void pickUpBullet(boolean hasBullet) {
		
	}
	
	/**
	 * {@link #useBullet(boolean)} This method will will make the player use the bullet in his possession
	 * if the @param hasBullet is true
	 */
	public void useBullet(boolean hasBullet) {
		
	}
	
	/**
	 * {@link #checkBulletHit()} This method will check if the bullet hit the enemy ninja
	 */
	public void checkBulletHit() {
		
	}
	
	/**
	 * {@link #pickUpInvincibility()} This method will allow the user to pick up the invincibility upgrade
	 */
	public void pickUpInvincibility() {
		
	}
	
	/**
	 * {@link #useInvincibility()} This method will toggle the invincibilty upgrade
	 * @return The return value will determine if the player can die or not
	 */
	public boolean useInvincibility() {
		return true;
	}
	
	/**
	 * {@link #pickUpRadar()} This method will let the user pick up the radar upgrade
	 */
	public void pickUpRadar() {
		
	}
	
	/**
	 * {@link #useRadar()} This method will reveal the location of the briefcase
	 */
	public void useRadar() {
		
	}
	
	/**
	 * {@link #playerDies(boolean)} This method will check if the enemy has attack the player
	 * and kill the player if the parameter is true
	 * @param enemyContact Is the boolean parameter that will determine if the player dies
	 * @return The return value will determine if {@link #looseLife(boolean)} will execute
	 */
	public boolean playerDies(boolean enemyContact) {
		return true;
	}
	
	/**
	 * {@link #getBriefCase(boolean)} This method will check if there is briefcase in front of the player
	 * @return This return value will return true if there is a briefcase in front of the player
	 */
	public boolean getBriefCase() {
		return true;
	}
	
}
