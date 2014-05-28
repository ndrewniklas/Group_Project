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
	
	private boolean isAlive;
	private boolean isInvincible;
	private boolean hasBullet;
	private boolean hasInvincibility;
	private boolean hasRadar;
	
	private int xPosition;
	private int yPosition;
	private int xpre;
	private int ypre;
	
	private int numLives;

	private int[] firstLookPos = new int[2];
	private int[] secondLookPos = new int[2];
	private int[] thirdLookPos = new int[2];
	private int[] fourthLookPos = new int[2];
	
	public Player(){
		super("[P]",0,8);
		setHiddenName("[P]");
		setObjectName("[P]");
		xPosition = 0;
		yPosition = 8;	
		isAlive=true;
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
	
	/**
	 * {@link #playerLook()} This method will reveal two spaces in the desired direction
	 * on the grid based on user input
	 */
	public void playerLook(Grid grid, String direction) {
		direction = direction.toLowerCase();
		switch(direction){
		case "up":
			lookUp();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, true);
			break;
		case "down":
			lookDown();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, true);
			break;
		case "right":
			lookRight();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, true);
			break;
		case "left":
			lookLeft();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, true);
			break;
		default:
			System.out.println("Something went wrong");
		}
	}
	public void stopLooking(Grid grid, String direction){
		direction = direction.toLowerCase();
		switch(direction){
		case "up":
			lookUp();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, false);
			break;
		case "down":
			lookDown();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, false);
			break;
		case "right":
			lookRight();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, false);
			break;
		case "left":
			lookLeft();
			grid.showObjectsWithinLocation(firstLookPos, secondLookPos, thirdLookPos, fourthLookPos, false);
			break;
		default:
			System.out.println("Something went wrong");
		}
	}
	
	public void lookUp(){
		//Up one
		if(yPosition-1 >= 0 && yPosition-1 <= 7 ){
			firstLookPos[0] = yPosition - 1;	
			firstLookPos[1] = xPosition;
		}
		//Up two, left one
		if(yPosition - 2 >= 0 && yPosition - 2 <= 7 && xPosition - 1 >= 0 && xPosition - 1 <= 7){
			secondLookPos[0] = yPosition - 2;
			secondLookPos[1] = xPosition - 1;
		}
		//Up two
		if(yPosition - 2 >= 0 && yPosition - 2 <= 7 && xPosition >= 0 && xPosition <= 7){
			thirdLookPos[0] = yPosition - 2;
			thirdLookPos[1] = xPosition;
		}
		//Up two, right one
		if(yPosition - 2 >= 0 && yPosition - 2 <= 7 && xPosition + 1 >= 0 && xPosition + 1 <= 7){
			fourthLookPos[0] = yPosition - 2;
			fourthLookPos[1] = xPosition + 1;
		}		
	}
	public void lookDown(){
		//Down one
		if(yPosition+1 >= 0 && yPosition+1 <= 7){
			firstLookPos[0] = yPosition + 1;	
			firstLookPos[1] = xPosition;
		}
		//Down two, left one
		if(yPosition + 2 >= 0 && yPosition + 2 <= 7 && xPosition - 1 >= 0 && xPosition - 1 <= 7){
			secondLookPos[0] = yPosition + 2;
			secondLookPos[1] = xPosition - 1;
		}
		//Down two
		if(yPosition + 2 >= 0 && yPosition + 2 <= 7 && xPosition >= 0 && xPosition <= 7){
			thirdLookPos[0] = yPosition + 2;
			thirdLookPos[1] = xPosition;
		}
		//Down two, right one
		if(yPosition + 2 >= 0 && yPosition + 2 <= 7 && xPosition + 1 >= 0 && xPosition + 1 <= 7){
			fourthLookPos[0] = yPosition + 2;
			fourthLookPos[1] = xPosition + 1;
		}		
	}
	public void lookRight(){
		//Right one
		if(xPosition + 1 >= 0 && xPosition + 1 <= 7){
			firstLookPos[0] = yPosition;	
			firstLookPos[1] = xPosition + 1;
		}
		//right two, up one
		if(xPosition + 2 >= 0 && xPosition + 2 <= 7 && yPosition - 1 >= 0 && yPosition - 1 <= 7){
			secondLookPos[0] = yPosition - 1;
			secondLookPos[1] = xPosition + 2;
		}
		//right two
		if(xPosition + 2 >= 0 && xPosition + 2 <= 7 && yPosition >= 0 && yPosition <= 7){
			thirdLookPos[0] = yPosition;
			thirdLookPos[1] = xPosition + 2;
		}
		//Right two, down one
		if(xPosition + 2 >= 0 && xPosition + 2 <= 7 && yPosition + 1 >= 0 && yPosition + 1 <= 7){
			fourthLookPos[0] = yPosition + 1;
			fourthLookPos[1] = xPosition + 2;
		}		
	}
	public void lookLeft(){
		//Left one
		if(xPosition - 1 >= 0 && xPosition - 1 <= 7){
			firstLookPos[0] = yPosition;	
			firstLookPos[1] = xPosition - 1;
		}
		//left two, up one
		if(xPosition - 2 >= 0 && xPosition - 2 <= 7 && yPosition - 1 >= 0 && yPosition - 1 <= 7){
			secondLookPos[0] = yPosition - 1;
			secondLookPos[1] = xPosition - 2;
		}
		//left two
		if(xPosition - 2 >= 0 && xPosition - 2 <= 7 && yPosition >= 0 && yPosition <= 7){
			thirdLookPos[0] = yPosition;
			thirdLookPos[1] = xPosition - 2;
		}
		//Right two, down one
		if(xPosition - 2 >= 0 && xPosition - 2 <= 7 && yPosition + 1 >= 0 && yPosition + 1 <= 7){
			fourthLookPos[0] = yPosition + 1;
			fourthLookPos[1] = xPosition - 2;
		}		
	}
	
	
	/**
	 * {@link #checkBulletPossession()} This method will check if there is a bullet in the gun of the
	 * player
	 * @return Will be used to determine if {@link #pickUpBullet(boolean)} and
	 * {@link #useBullet(boolean)} can be executed or not
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
	 * {@link #useShield()} This method will toggle the {@link Shield} upgrade
	 * @return The return value will determine if the player can die or not
	 */
	public boolean useShield() {
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
	 * @return
	 */
	public boolean playerDies(boolean enemyContact) {
		return true;
	}
	
	/**
	 * This method will check if there is briefcase in front of the player
	 * @return This return value will return true if there is a briefcase in front of the player
	 */
	public boolean getBriefCase() {
		return true;
	}
	
}
