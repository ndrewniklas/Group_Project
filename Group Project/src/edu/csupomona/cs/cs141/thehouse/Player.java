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
 * @author Dylan Nguyen
 * This {@link Player} class will contain parameters that will define the player's status, upgrade status, position, and number
 * of lives. In addition, the methods contained within the class will give the player actions (look, shoot, move, pick up items)
 * as well as reactions (death, shield, radar, new bullet).
 */
public class Player extends GameObject{
	
	private boolean isAlive = true;
	private boolean isInvincible = false;
	private boolean hasBullet = true;
	private boolean hasShield = false;
	private boolean hasRadar = false;
	private boolean hasBriefCase = false;
	
	private int ammo;
	
	private int xPosition;
	private int yPosition;
	private int xpre;
	private int ypre;
	
	private int numLives;

	private int lookPosY1, lookPosY2, lookPosX1, lookPosX2;
	
	public Player(){
		super("[P]",0,8);
		setHiddenName("[P]");
		setRealName("[P]");
		setObjectName("[P]");
		xPosition = 0;
		yPosition = 8;	
		isAlive=true;
		ammo = 1;
	}
	
	/**
	 * {@link #movePlayer()} This method will move the player based on user input
	 */
	public void movePlayer(String input){
		
/*        boolean upPossible = roomExists(xPosition, yPosition - 1);
        boolean downPossible = roomExists(xPosition, yPosition + 1);
        boolean rightPossible = roomExists(xPosition + 1, yPosition);
        boolean leftPossible = roomExists(xPosition - 1, yPosition);*/
        
//        System.out.println("Where would you like to go :");
//        if (upPossible) {
//            System.out.print(" up");
//        }
//        if (rightPossible) {
//            System.out.print(" right");
//        }
//        if (downPossible) {
//            System.out.print(" down");
//        }
//        if (leftPossible) {
//            System.out.print(" left");
//        }
//        System.out.print(" ? \n");
  
        String cmd = input.toLowerCase();
		xpre = xPosition;
		ypre = yPosition;
        if (cmd.equals("up") || cmd.equals("u") || cmd.equals("1") && upPossible()) {
        	if (yPosition-1 >= 0 && yPosition-1 <= 8) {
				setXY(xPosition, --yPosition);
			}
        } else if (cmd.equals("down") || cmd.equals("d") || cmd.equals("2")&& downPossible()) {
        	if (yPosition+1 >= 0 && yPosition+1 <= 8) {
				setXY(xPosition, ++yPosition);
			}
        } else if (cmd.equals("right") || cmd.equals("r") || cmd.equals("3") && rightPossible()) {
        	if (xPosition+1 >= 0 && xPosition+1 <= 8) {
				setXY(++xPosition, yPosition);
			}
        } else if (cmd.equals("left") || cmd.equals("l") || cmd.equals("4")&& leftPossible()) {
        	if (xPosition-1 >= 0 && xPosition-1 <= 8) {
				setXY(--xPosition, yPosition);
			}
        }        
    }
	
	/**
	 * @return
	 */
	public int get_yPosition() {
		return yPosition;
	}
	
	/**
	 * @return
	 */
	public int get_xPosition() {
		return xPosition;
	}
	
	/**
	 * @return
	 */
	public int getYPre() {
		return ypre;
	}
	
	/**
	 * @return
	 */
	public int getXPre() {
		return xpre;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void playerDefaultVision(Grid grid){
		if(yPosition+1 >= 0 && yPosition+1 <= 8 )
			grid.showPlayerDefaultVision(yPosition + 1, xPosition, true);
		if(yPosition-1 >= 0 && yPosition-1 <= 8 )
			grid.showPlayerDefaultVision(yPosition - 1, xPosition, true);
		if(xPosition - 1 >= 0 && xPosition - 1 <= 8 )
			grid.showPlayerDefaultVision(yPosition, xPosition - 1, true);
		if(xPosition+1 >= 0 && xPosition+1 <= 8 )
			grid.showPlayerDefaultVision(yPosition, xPosition + 1, true);
	}
	public void clearDefaultVision(Grid grid){
		if(yPosition+1 >= 0 && yPosition+1 <= 8 )
			grid.showPlayerDefaultVision(yPosition + 1, xPosition, false);
		if(yPosition-1 >= 0 && yPosition-1 <= 8 )
			grid.showPlayerDefaultVision(yPosition - 1, xPosition, false);
		if(xPosition - 1 >= 0 && xPosition - 1 <= 8 )
			grid.showPlayerDefaultVision(yPosition, xPosition - 1, false);
		if(xPosition+1 >= 0 && xPosition+1 <= 8 )
			grid.showPlayerDefaultVision(yPosition, xPosition + 1, false);
	}
	/**
	 * {@link #playerLook()} This method will reveal two spaces in the desired direction
	 * on the grid based on user input
	 */
	public void playerLook(Grid grid, String direction) {
		direction = direction.toLowerCase();
		switch(direction){
		case "up":
		case "u":
		case "1":
			lookUp();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			break;
			
		case "down":
		case "d":
		case "2":
			lookDown();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			break;
			
		case "right":
		case "r":
		case "3":
			lookRight();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			break;
			
		case "left":
		case "l":
		case "4":
			lookLeft();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			break;
			
		default:
			System.out.println("Something went wrong");
		}
	}
	public void stopLooking(Grid grid, String direction){
		direction = direction.toLowerCase();
		switch(direction){
		case "up":
		case "u":
		case "1":
			lookUp();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, false);
			break;
			
		case "down":
		case "d":
		case "2":
			lookDown();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, false);
			break;
			
		case "right":
		case "r":
		case "3":
			lookRight();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, false);
			break;
			
		case "left":
		case "l":
		case "4":
			lookLeft();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, false);
			break;
			
		default:
			System.out.println("Something went wrong");
		}
	}
	
	public void lookUp(){
		//Up one
		if(yPosition-1 >= 0 && yPosition-1 <= 8 && roomExists(yPosition-1,xPosition)){
			lookPosY1 = yPosition - 1;	
			lookPosX1 = xPosition;
		}else{
			lookPosY1 = yPosition;	
			lookPosX1 = xPosition;
		}
		//Up two
		if(yPosition - 2 >= 0 && yPosition - 2 <= 8 && roomExists(yPosition-2,xPosition)&& roomExists(yPosition-1,xPosition)){
			lookPosY2 = yPosition - 2;
			lookPosX2 = xPosition;
		}else{
			lookPosY2 = yPosition;
			lookPosX2 = xPosition;
		}
	}
	public void lookDown(){
		//Down one
		if(yPosition+1 >= 0 && yPosition+1 <= 8 ){
			lookPosY1 = yPosition + 1;	
			lookPosX1 = xPosition;
		}else{
			lookPosY1 = yPosition;	
			lookPosX1 = xPosition;
		}
		//Down two
		if(yPosition + 2 >= 0 && yPosition + 2 <= 8 && xPosition >= 0 && xPosition <= 8 && roomExists(yPosition+2,xPosition) && roomExists(yPosition+1,xPosition)){
			lookPosY2 = yPosition + 2;
			lookPosX2 = xPosition;
		}else{
			lookPosY2 = yPosition ;
			lookPosX2 = xPosition;
		}
	
	}
	public void lookRight(){
		//Right one
		if(xPosition + 1 >= 0 && xPosition + 1 <= 8 && roomExists(yPosition,xPosition + 1)){
			lookPosY1 = yPosition;	
			lookPosX1 = xPosition + 1;
		}else{
			lookPosY1 = yPosition;	
			lookPosX1 = xPosition;
		}
		//right two
		if(xPosition + 2 >= 0 && xPosition + 2 <= 8 && yPosition >= 0 && yPosition <= 8 && roomExists(yPosition,xPosition + 2)&& roomExists(yPosition,xPosition + 1)){
			lookPosY2 = yPosition;
			lookPosX2 = xPosition + 2;
		}else{
			lookPosY2 = yPosition;
			lookPosX2 = xPosition;
		}
	}
	public void lookLeft(){
		//Left one
		if(xPosition - 1 >= 0 && xPosition - 1 <= 8 && roomExists(yPosition,xPosition-1)){
			lookPosY1 = yPosition;	
			lookPosX1 = xPosition - 1;
		}else{
			lookPosY1 = yPosition;	
			lookPosX1 = xPosition;
		}
		//left two
		if(xPosition - 2 >= 0 && xPosition - 2 <= 8 && yPosition >= 0 && yPosition <= 8&& roomExists(yPosition,xPosition-2)&& roomExists(yPosition,xPosition-1)){
			lookPosY2 = yPosition;
			lookPosX2 = xPosition - 2;
		}else{
			lookPosY2 = yPosition;
			lookPosX2 = xPosition;
		}
	}
	
	
	/**
	 * {@link #checkBulletPossession()} This method will check if there is a bullet in the gun of the
	 * player
	 * @return Will be used to determine if {@link #pickUpBullet(boolean)} and
	 * {@link #useBullet(boolean)} can be executed or not
	 */
	public boolean checkBulletPossession() {
		if(ammo > 1)
			return hasBullet;
		else
			return false;
	}
	
	/**
	 * {@link #pickUpBullet(boolean)} This method will allow the user to pick up a bullet based on the 
	 * @param hasBullet
	 */
	public void pickUpBullet() {
		++ammo;
	}
		
	/**
	 * {@link #checkBulletHit()} This method will check if the bullet hit the enemy ninja
	 */
	public void checkBulletHit() {
		
	}
	
	/**
	 * {@link #pickUpInvincibility()} This method will allow the user to pick up the invincibility upgrade
	 * @return 
	 */
	public void pickUpInvincibility(boolean hasShield) {
		hasShield = true;
	}
	
	/**
	 * {@link #useShield()} This method will toggle the {@link Shield} upgrade
	 * @return The return value will determine if the player can die or not
	 */
	public void useShield(boolean hasShield) {
		
		hasShield = false;
	}
	
	/**
	 * {@link #pickUpRadar()} This method will let the user pick up the radar upgrade
	 */
	public void pickUpRadar(boolean hasRadar) {
		hasRadar = true;
	}
	
	/**
	 * {@link #useRadar()} This method will reveal the location of the briefcase
	 */
	public void useRadar(boolean hasRadar) {
		hasRadar = false;
	}
	
	/**
	 * {@link #playerDies(boolean)} This method will check if the enemy has attack the player
	 * and kill the player if the parameter is true
	 * @param enemyContact Is the boolean parameter that will determine if the player dies
	 * @return 
	 * @return
	 */
	public void playerDies(boolean enemyContact) {
		isAlive = false;
	}
	
	/**
	 * This method will check if there is briefcase in front of the player
	 * @return This return value will return true if there is a briefcase in front of the player
	 */
	public void getBriefCase(boolean hasBriefCase) {
		hasBriefCase = true;
	}
	
}
