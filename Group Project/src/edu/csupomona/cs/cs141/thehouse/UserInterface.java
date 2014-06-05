/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

import java.util.Scanner;

/**
 * @author Andrew
 *
 */
public class UserInterface {

	/**
	 * This field holds the choice of the user. There are 7 potential values for the user to select:
	 * left,
	 * right,
	 * up,
	 * down,
	 * shoot,
	 * save,
	 * exit
	 */
	private String userChoice;

	private String input;

	Scanner sc = new Scanner(System.in);
	/**
	 * This constructor will build the {@link UserInterface} for use with {@link GameEngine}.
	 * Then it will check after each round whether or not the game has ended and print out the appropriate
	 * results.
	 */

	/**
	 * This method is called by {@link #main Menu()}. 
	 */
	public void rules() {
		System.out.println("The rules to the game are very simple:");
		System.out.println("You will be spawned in the bottom left-hand corner of a building. "
				+ "\nThere will also be six enemies in the building with you. \nLocated throughout the building "
				+ "are nine rooms and in one of these rooms is a briefcase. \nFind the briefcase"
				+ " and win the game.");
		System.out.println();
		System.out.println("User Commands: ");
		System.out.println("You have several options available to you while the game is running.");
		System.out.println("1. Left - moves you to the left one space");
		System.out.println("2. Right - moves you to the right one space");
		System.out.println("3. Up - moves you up one space");
		System.out.println("4. Down - moves you down one space");
		System.out.println("5. Look - you can use this at the start of every round, it lets you see two"
				+ " blocks in any direction.");
		System.out.println("6. Shoot - you can use this if you have a bullet left. It will kill one enemy"
				+ " if in the path of the bullet.");
		System.out.println("7. Save - use this to save the game, and return to it later by selecting"
				+ " 'continue' in the main menu.");
		System.out.println("8. Exit - use this to quit the game, be aware that progress will not be saved.");
		System.out.println();
		System.out.println("Type \"return\" or 0 to return to the main menu:");
	}

	/**
	 * This method will print the main menu of the game to the user and give options such as, "continue"
	 * 		or "new game".
	 */
	public void mainMenu() {
		System.out.println("Welcome to The House Game!");
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1)New");
		System.out.println("2)Continue");
		System.out.println("3)Rules");
		System.out.println("4)Options");
		System.out.println("0)Exit");
		System.out.println("Please enter one of the commands or the corresponding number:");
	}

	public void options() {
		System.out.println("You can change the options here:");
		System.out.println("1) Debug Mode");
		System.out.println("2) Save Game");
		System.out.println("3) New Game");
		System.out.println("4) Mute the music");
		System.out.println("Type \"return\" or 0 to return to the main menu:");
	}

	/**
	 * This method prints the {@link Grid} in its current state. This will occur after every round. It will
	 * pull the {@link Grid} class from {@link GameEngine#getGrid()} and then call {@link Grid#getfullGrid()}
	 * @param grid 
	 */
	public void printGrid(Grid grid) {
		grid.printGrid();
	}

	public void turnSelect1() {
		System.out.println("What would you like to do?");
		System.out.println("1)Look");
		System.out.println("2)Move");
		System.out.println("3)Shoot");
		System.out.println("4)Options");
		System.out.println("0)Exit");
		System.out.println("Please enter one of the commands:");
	}

	/**
	 * 
	 */
	public void turnSelect2() {
		System.out.println("What would you like to do?");
		System.out.println("1)Move");
		System.out.println("2)Shoot");
		System.out.println("3)Options");
		System.out.println("0)Exit");
		System.out.println("Please enter one of the commands:");
	}

	public void setChoice(String choice){
		userChoice = choice;
	}

	public String getChoice(){
		return userChoice;
	}

	public void moveTurn() {
		System.out.println("Which direction will you move?");
		System.out.println("1)Up");
		System.out.println("2)Down");
		System.out.println("3)Right");
		System.out.println("4)Left");
		System.out.println("Please enter one of the commands, command numbers, or the letter: ");
	}

	public void lookDirections(){
		System.out.println("Which direction will you look?");
		System.out.println("1)Up");
		System.out.println("2)Down");
		System.out.println("3)Right");
		System.out.println("4)Left");
		System.out.println("Please enter one of the commands, command numbers, or the letter: ");
	}

	public void invalidCMD() {
		System.out.println("Invalid Command: Please try again");
	}


	/**
	 * 
	 */
	public String getInput() {
		input = sc.nextLine();
		return input;
	}

	public void radarActivated() {
		System.out.println("You've picked up the radar!");
		System.out.println("You can now see which room holds the briefcase!");
	}

	public void ammoActivated(){
		System.out.println("You have picked up ammo!");
	}

	public void shieldActivated() {
		System.out.println("You have picked up the shield!");
		System.out.println("You will block attacks for 5 turns");
	}
	public void getAmmoAmount(){

	}

	public void killEnemy() {
		System.out.println("You've killed an enemy!");
		System.out.println("But you are out of ammo...");
	}

	public void shotFired() {
		System.out.println("You fired your bullet...");

	}

	public void shootTurn() {
		System.out.println("Which direction will you fire?");
		System.out.println("1)Up");
		System.out.println("2)Down");
		System.out.println("3)Right");
		System.out.println("4)Left");
		System.out.println("Please enter one of the commands, command numbers, or the letter: ");
	}

	public void noBullet() {
		System.out.println("You have no bullet to fire.");

	}	
	public void endScreen(){
		System.out.println("--------------------------------------");
		System.out.println("Thanks for playing!");
		System.out.println("Would you like to start a new game?");
		System.out.println("Yes or No");
		System.out.println("--------------------------------------");
	}
	public void exitScreen(){
		System.out.println("--------------------------------------");
		System.out.println("Thanks for playing!");
		System.out.println("--------------------------------------");
	}

	/**
	 * 
	 */
	public void pause() {
		sc.nextLine();
	}

	public boolean newGame(){
		boolean answer = false;
		String question = sc.nextLine().toLowerCase();
		switch(question){
		case "yes":
		case "y":
			answer = true;
			break;
		case "no":
		case "n":
			answer = false;
			break;
		default:
			System.out.println("Not a valid input! Please try again!");
			newGame();
		}
		return answer;
	}

	/**
	 * 
	 */
	public void playerDies() {
		System.out.println();
		System.out.println("------------------------");
		System.out.println();
		System.out.println("You have been killed!");
		System.out.println("Respawing now....");
		System.out.println("enter to continue");
		System.out.println();
		System.out.println("------------------------");
	}

	/**
	 * 
	 */
	public void foundBriefcase() {
		System.out.println("YOU FOUND THE BRIEFCASE");
	}

	/**
	 * @param hasRadar 
	 * @param hasShield 
	 * @param m 
	 * 
	 */
	public void printStats(Player ply, boolean hasRadar, int m) {
		System.out.println("Ammo: " + ply.getAmmo());
		System.out.println("Lives: " + ply.getNumLives());
		if(hasRadar)
			System.out.println("Radar enabled");
		else if(ply.isHasShield())
			System.out.println("Shield enabled: " + m + " turns remaining");
	}

	/**
	 * 
	 */
	public void congrats() {
		System.out.println("Congratulations you found the briefcase and made it out alive!");
	}

	/**
	 * 
	 */
	public void missionFailed() {
		System.out.println("YOU HAVE FAILD ME!!!!!");
		System.out.println("Maybe you can do better next time");
	}
}
