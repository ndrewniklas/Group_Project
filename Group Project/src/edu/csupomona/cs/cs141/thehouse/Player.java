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
 * @author Dylan Nguyen
 * This {@link Player} class will contain parameters that will define the player's status, upgrade status, position, and number
 * of lives. In addition, the methods contained within the class will give the player actions (look, shoot, move, pick up items)
 * as well as reactions (death, shield, radar, new bullet).
 */
public class Player extends GameObject{

	//	private boolean isAlive = true;
//	private boolean isInvincible = false;
//	private boolean hasBullet = true;
	private boolean hasShield = false;
//	private boolean hasRadar = false;
	private boolean hasBriefCase = false;

	private int objID;

	private int ammo;

	private int xPosition;
	private int yPosition;
	private int xpre;
	private int ypre;

	private int numLives;

	private int lookPosY1, lookPosY2, lookPosX1, lookPosX2;

	private String plrMoveDir;
	
	/**
	 * turns remaining on shield use
	 */
	private int m;

	public Player(){
		super("[P]",0,8);
		setHiddenName("[P]");
		setRealName("[P]");
		setObjectName("[P]");
		xPosition = 0;
		yPosition = 8;
		ammo = 1;
		numLives = 3;
		objID = setObjId(4);
		plrMoveDir = "up";
	}

	/**
	 * {@link #movePlayer()} This method will move the player based on user input
	 */
	public void movePlayer(String input){
		String cmd = input.toLowerCase();
		xpre = xPosition;
		ypre = yPosition;

		switch(cmd){
			case "up":
			case "u":
			case "1":
				cmd = "up";
				break;
			case "down":
			case "d":
			case "2":
				cmd = "down";
				break;
			case "right":
			case "r":
			case "3":
				cmd = "right";
				break;
			case "left":
			case "l":
			case "4":
				cmd = "left";
				break;
			default:
				cmd = "up";
		}

		if (upPossible() && cmd.equals("up")) {
			if (yPosition-1 >= 0 && yPosition-1 <= 8) {
				setXY(xPosition, --yPosition);
				plrMoveDir = "up";

			}
		} else if (downPossible()&& cmd.equals("down")) {
			if (yPosition+1 >= 0 && yPosition+1 <= 8) {
				setXY(xPosition, ++yPosition);
				plrMoveDir = "down";
			}
		} else if (rightPossible() && cmd.equals("right")) {
			if (xPosition+1 >= 0 && xPosition+1 <= 8) {
				setXY(++xPosition, yPosition);
				plrMoveDir = "right";
			}
		} else if (leftPossible() && cmd.equals("left")) {
			if (xPosition-1 >= 0 && xPosition-1 <= 8) {
				setXY(--xPosition, yPosition);
				plrMoveDir = "left";
			}
		}        
	}

	public String getPlrMoveDirection(){
		return plrMoveDir;
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

	public void naturalVision(String direction, Grid grid){
		direction = direction.toLowerCase();
		switch(direction){
		case "up":
			lookUp();
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, true);
			break;	
		case "down":
			lookDown(grid);
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, true);
			break;
		case "right":
			lookRight();
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, true);
			break;	
		case "left":
			lookLeft();
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, true);
			break;	
		default:
			System.out.println("Something went wrong");
		}
	}

	public void removeNaturalVision(String direction, Grid grid){
		direction = direction.toLowerCase();
		switch(direction){
		case "up":
			lookUp();
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, false);
			break;

		case "down":
			lookDown(grid);
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, false);
			break;

		case "right":
			lookRight();
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, false);
			break;

		case "left":
			lookLeft();
			grid.showPlayerDefaultVision(lookPosY1, lookPosX1, false);
			break;

		default:
			System.out.println("Something went wrong");
		}
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
			grid.lookReport(lookPosY1, lookPosX1, lookPosY2, lookPosX2);
			break;

		case "down":
		case "d":
		case "2":
			lookDown(grid);
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			grid.lookReport(lookPosY1, lookPosX1, lookPosY2, lookPosX2);
			break;

		case "right":
		case "r":
		case "3":
			lookRight();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			grid.lookReport(lookPosY1, lookPosX1, lookPosY2, lookPosX2);
			break;

		case "left":
		case "l":
		case "4":
			lookLeft();
			grid.showObjectsWithinLocation(lookPosY1, lookPosX1, lookPosY2, lookPosX2, true);
			grid.lookReport(lookPosY1, lookPosX1, lookPosY2, lookPosX2);
			break;

		default:
			System.out.println("Something went wrong");
			break;
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
			lookDown(grid);
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

	public void lookDown(Grid grid){
		//Down one
		if(yPosition+1 >= 0 && yPosition+1 <= 8 ){
			lookPosY1 = yPosition + 1;	
			lookPosX1 = xPosition;
			checkbreifcase(lookPosY1, lookPosX1, grid);
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
		if(ammo > 0)
			return true;
		else
			return false;
	}

	/**
	 * {@link #pickUpBullet(boolean)} This method will allow the user to pick up a bullet based on the 
	 * @param hasBullet
	 */
	public void pickUpBullet(int amount) {
		ammo += amount;
	}
	
	public void forceAmmo(int ammo){
		this.ammo = ammo;
	}

	/**
	 * {@link #useBullet(boolean)} This method will will make the player use the bullet in his possession
	 * if the @param hasBullet is true
	 */
	public void useBullet() {
		--ammo;
	}

	public int getAmmo(){
		return ammo;
	}

	/**
	 * @return the hasShield
	 */
	public boolean isHasShield() {
		return hasShield;
	}

	/**
	 * @param hasShield the hasShield to set
	 */
	public void setHasShield(boolean hasShield) {
		this.hasShield = hasShield;
	}

	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * {@link #playerDies(boolean)} This method will check if the enemy has attack the player
	 * and kill the player if the parameter is true
	 * @param enemyContact Is the boolean parameter that will determine if the player dies
	 * @return 
	 * @return
	 */
	public void playerDies() {
		ypre = 8;
		xpre = 0;
		yPosition = 8;
		xPosition = 0;
		numLives--;
		plrMoveDir = "up";
	}

	private void checkbreifcase(int lookPosY1, int lookPosX1, Grid grid) {
		int[] bcPos = grid.getBCpos();
		if(lookPosY1==bcPos[0] && lookPosX1 == bcPos[1]){
			hasBriefCase = true;
		}
		else{
			hasBriefCase = false;
			if (!roomExists(lookPosX1,lookPosY1)) {
				System.out.println("room is empty");
			}
		}
	}

	public boolean getHasBriefCase() {
		return hasBriefCase;
	}
	/**
	 * This method will check if there is briefcase in front of the player
	 * @return This return value will return true if there is a briefcase in front of the player
	 */
	public void getBriefCase(boolean hasBriefCase) {
		hasBriefCase = true;
	}

	/**
	 * @return
	 */
	public int getNumLives() {
		return numLives;
	}
}
