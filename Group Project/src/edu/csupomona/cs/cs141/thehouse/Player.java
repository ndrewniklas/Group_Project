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
 * This {@link Player} class will contain parameters that will define the player's status, upgrade status, 
 * position, and number of lives. In addition, the methods contained within the class will give the player 
 * actions (look, shoot, move, pick up items) as well as reactions (death, shield, radar, new bullet).
 */
public class Player extends GameObject{

//	private boolean isAlive = true;
//	private boolean isInvincible = false;
//	private boolean hasBullet = true;
	
	/**
	 * This field represents a {@code boolean} that is {@code true} when the {@link Player} collects the 
	 * {@link Shield} object.
	 */
	private boolean hasShield = false;
//	private boolean hasRadar = false;
	
	/**
	 * This field represents a {@code boolean} that becomes {@code true} when the {@link Player} disarms
	 * the bomb
	 */
	private boolean hasBriefCase = false;

	/**
	 * This field represents the unique ID of the object, used for comparing two {@link GameObject}s
	 */
	private int objID;

	/**
	 * This field represents an {@code int} containing the number of bullets that the {@link Player has}.
	 * This number can only be {@code 1} or {@code 0}
	 */
	private int ammo;

	/**
	 * This field is part of a pair of {@code integers} that contain the position of the 
	 * {@link Player} character.
	 */
	private int xPosition;
	private int yPosition;
	
	/**
	 * This field is part of a pair of {@code integers} that contain the next position of the {@link Player}
	 */
	private int xpre;
	private int ypre;

	/**
	 * This field represents an {@code int} containing the number of lives that the {@link Player} has left.
	 * This number decreases by {@code 1} every time the {@link Player} is killed by an {@link Enemy}
	 */
	private int numLives;

	/**
	 * This field is part of a group of {@code integers} that reveal locations on the {@link Grid} depending
	 * on the user's choice of "look" direction
	 */
	private int lookPosY1, lookPosY2, lookPosX1, lookPosX2;

	/**
	 * This field represents a {@code String} containing the direction in which the {@link Player} moved
	 */
	private String plrMoveDir;
	
	/**
	 * This field represents the turns remaining on shield use as an {@code int}
	 */
	private int m;

	/**
	 * This constructor creates the {@link Player} object by calling its super constructor 
	 * {@link GameObject#GameObject(String, int, int)}, and passing "[P]", {@code 0}, and {@code 8}
	 * as its respective parameters.
	 * It then calls {@link GameObject#setHiddenName(String)}, {@link GameObject#setRealName(String)},
	 * and {@link GameObject#setObjectName(String)} with "[P]" as the parameter for each. It does this
	 * since the {@link Player} should never be a hidden object.
	 * Finally it sets the initial conditions of {@link #xPosition}, {@link #yPosition}, {@link #ammo},
	 * {@link #numLives}, {@link #objID}, and {@link #plrMoveDir} to their necessary values.
	 */
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
	 * This method will move the player based on user input, which is passed as a {@code String} and forced
	 * to lower case. It then uses a {@code switch} statement and checks each potential input - the {@code 
	 * switch} statement serves to ensure that the {@code String} has the proper value "up", "down", "left",
	 * or "right" before continuing on an {@code if-else if} statement that checks whether the move is 
	 * possible. It then moves the {@link Player} by calling {@link #setXY(int, int)} with the appropriate
	 * parameters before finally setting {@link #plrMoveDir} to the value of the {@code String} that was
	 * passed an modified.
	 * 
	 * @param input
	 * 	- {@code String} that contains the user's input, it will be changed to the standard format of 
	 * 		{@link #plrMoveDir} before finally being saved in that field.
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

	/**
	 * This method will pass {@link #plrMoveDir} to whatever calls it
	 * @return
	 * 	- {@code String} {@code #plrMoveDir
	 */
	public String getPlrMoveDirection(){
		return plrMoveDir;
	}

	/**
	 * This method will pass {@link #yPosition} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #yPosition}
	 */
	public int get_yPosition() {
		return yPosition;
	}

	/**
	 * This method will pass {@link #XPosition} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #xPosition}
	 */
	public int get_xPosition() {
		return xPosition;
	}

	/**
	 * This method will pass {@link #yPre} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #yPre}
	 */
	public int getYPre() {
		return ypre;
	}

	/**
	 * This method will pass {@link #xPre} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #xPre}
	 */
	public int getXPre() {
		return xpre;
	}

	/**
	 * This method will reveal a location one block ahead of wherever the {@link Player} is looking. It 
	 * does this by using a {@code String} containing the direction that the {@link Player} is looking and 
	 * the {@link Grid} object and passing it to a {@code switch} statement. Based on the {@code String} it
	 * will call either {@link #lookUp()}, {@link #lookDown(Grid)}, {@link #lookRight()}, or {@link #lookLeft()};
	 * for each case it will then call {@link Grid#showPlayerDefaultVision(int, int, boolean)}, passing 
	 * one of the {@link #lookPosX1 lookPositions} in each integer and {@code true}. The {@code default} 
	 * statement should not be called, but will print an error message just in case.
	 * @param direction
	 * 	- {@code String} containing the direction that the character is looking
	 * @param grid
	 * 	- {@link Grid} object in the game state
	 */
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

	/**
	 * This method will remove a location visible from wherever the {@link Player} was looking. It 
	 * does this by using a {@code String} containing the direction that the {@link Player} is looking and 
	 * the {@link Grid} object and passing it to a {@code switch} statement. Based on the {@code String} it
	 * will call either {@link #lookUp()}, {@link #lookDown(Grid)}, {@link #lookRight()}, or {@link #lookLeft()};
	 * for each case it will then call {@link Grid#showPlayerDefaultVision(int, int, boolean)}, passing 
	 * one of the {@link #lookPosX1 lookPositions} in each integer and {@code false}. The {@code default} 
	 * statement should not be called, but will print an error message just in case.
	 * @param direction
	 * 	- {@code String} containing the direction that the character is looking
	 * @param grid
	 * 	- {@link Grid} object in the game state
	 */
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
	 * This method will reveal two spaces in the desired direction on the {@link Grid} based on user input.
	 * It does this by using a {@code String} containing the direction that the {@link Player} is looking 
	 * and the {@link Grid} object and passing it to a {@code switch} statement. Based on the {@code String} 
	 * it will call either {@link #lookUp()}, {@link #lookDown(Grid)}, {@link #lookRight()}, or 
	 * {@link #lookLeft()}; for each case it will then call 
	 * {@link Grid#showObjectsWithinLocation(int, int, int, int, boolean)} and
	 * {@link Grid#lookReport(int, int, int, int)} where the integers are all of the 
	 * {@link #lookPosX1 lookPositions}, and the {@code boolean} is {@code true}. The {@code default}
	 * condition should not be met, but will print an error message if it is.
	 * @param direction
	 * 	- {@code String} containing the direction that the character is looking
	 * @param grid
	 * 	- {@link Grid} object in the game state
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

	/**
	 * This method will set the {@link #lookPosX1 lookPositions} to their necessary values. Primarily, the 
	 * only fields that change are {@link #lookPosY1} and {@link #lookPosY2} which will be subtracted by
	 * {@code 1} and {@code 2} respectively. This only occurs after it calls {@link GameObject#roomExists(int, int)}
	 * and the value returns {@code true}.
	 */
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

	/**
	 * This method will set the {@link #lookPosX1 lookPositions} to their necessary values. Primarily, the 
	 * only fields that change are {@link #lookPosY1} and {@link #lookPosY2} which will be incremented up by
	 * {@code 1} and {@code 2} respectively. This only occurs after it calls {@link GameObject#roomExists(int, int)}
	 * and the value returns {@code true}.
	 */
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

	/**
	 * This method will set the {@link #lookPosX1 lookPositions} to their necessary values. Primarily, the 
	 * only fields that change are {@link #lookPosX1} and {@link #lookPosX2} which will be incremented up by
	 * {@code 1} and {@code 2} respectively. This only occurs after it calls {@link GameObject#roomExists(int, int)}
	 * and the value returns {@code true}.
	 */
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

	/**
	 * This method will set the {@link #lookPosX1 lookPositions} to their necessary values. Primarily, the 
	 * only fields that change are {@link #lookPosX1} and {@link #lookPosX2} which will be subtracted by
	 * {@code 1} and {@code 2} respectively. This only occurs after it calls {@link GameObject#roomExists(int, int)}
	 * and the value returns {@code true}.
	 */
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
	 * This method will check if there is a bullet in the gun of the player, basically returning {@code true}
	 * if {@link #ammo} is greater than {@code 0} and {@code false} otherwise. It will be used to determine 
	 * if {@link #pickUpBullet(boolean)} and {@link #useBullet(boolean)} can be executed or not
	 * @return
	 *	- {@code boolean} {@code true} when {@link #ammo} is greater than {@code 0}, and {@code false} otherwise
	 */
	public boolean checkBulletPossession() {
		if(ammo > 0)
			return true;
		else
			return false;
	}

	/**
	 * This method will add an {@code int}, passed as a parameter, to {@link #ammo} if it is called.
	 * @param amount
	 * 	- {@code int} containing some value to be added to {@link #ammo}.
	 */
	public void pickUpBullet(int amount) {
		ammo += amount;
	}
	
	/**
	 * This method will change {@link #ammo} to whatever {@code int} the parameter holds.
	 * @param ammo
	 * 	- {@code int} containing the value that {@link #ammo} will be set to
	 */
	public void forceAmmo(int ammo){
		this.ammo = ammo;
	}

	/**
	 * This method will decrement {@link #ammo} by {@code 1} when it is called.
	 */
	public void useBullet() {
		--ammo;
	}

	/**
	 * This method will pass {@link #ammo} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #ammo}
	 */
	public int getAmmo(){
		return ammo;
	}

	/**
	 * This method will pass {@link #hasShield} to whatever calls it
	 * @return
	 * 	- {@code boolean} {@link #hasShield}
	 */
	public boolean isHasShield() {
		return hasShield;
	}

	/**
	 * This method will set {@link #hasShield} to whatever {@code boolean} is passed in as
	 * a parameter
	 * @param hasShield 
	 * 	- {@code boolean} containing the value intended for {@link #hasShield}
	 */
	public void setHasShield(boolean hasShield) {
		this.hasShield = hasShield;
	}

	/**
	 * This method will pass {@link #m} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #m}
	 */
	public int getM() {
		return m;
	}

	/**
	 * This method will set {@link #m} to whatever {@code int} is passed in as a parameter
	 * @param m
	 * 	- {@code int} containing the value intended for {@link #m}
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * This method will check if the enemy has attack the player and kill the player by resetting their 
	 * location to the initial spawn and decrementing {@link #numLives} by {@code 1}.
	 */
	public void playerDies() {
		ypre = 8;
		xpre = 0;
		yPosition = 8;
		xPosition = 0;
		numLives--;
		plrMoveDir = "up";
	}

	/**
	 * This method checks whether or not the {@link Room} object contains the bomb. It does this by using
	 * {@link #lookPosY1} and {@link #lookPosX1} along with the {@link Grid} as parameters; then it calls
	 * {@link Grid#getBCpos()} and checks whether the {@link #lookPosX1 lookPositions} are equal to the 
	 * position of the bomb. If they are, it will return {@code true}. Otherwise, it will return {@code false}
	 * and finally call {@link #roomExists(int, int)} to determine whether there was a {@link Room} at that 
	 * location before printing the message that the reactor is clear.
	 * @param lookPosY1
	 * 	- {@code int} {@link #lookPosY1}
	 * @param lookPosX1
	 * 	- {@code int} {@link #lookPosX1}
	 * @param grid
	 * 	- {@link Grid}
	 */
	private void checkbreifcase(int lookPosY1, int lookPosX1, Grid grid) {
		int[] bcPos = grid.getBCpos();
		if(lookPosY1==bcPos[0] && lookPosX1 == bcPos[1]){
			hasBriefCase = true;
		}
		else{
			hasBriefCase = false;
			if (!roomExists(lookPosX1,lookPosY1)) {
				System.out.println("This reactor is clear!");
			}
		}
	}

	/**
	 * This method will pass {@link Player#hasBriefCase} to whatever calls it
	 * @return
	 * 	- {@code boolean} {@link Player#hasBriefCase}
	 */
	public boolean getHasBriefCase() {
		return hasBriefCase;
	}
	
	/**
	 * This method will set {@link #hasBriefCase} to {@code true} regardless of its initial value
	 * @param hasBriefCase
	 * 	- {@code boolean} which is {@code true} when the bomb is found and {@code false} otherwise
	 */
	public void getBriefCase(boolean hasBriefCase) {
		hasBriefCase = true;
	}

	/**
	 * This method will pass {@link #numLives} to whatever calls it
	 * @return
	 * 	- {@code int} {@link #numLives}
	 */
	public int getNumLives() {
		return numLives;
	}
}
