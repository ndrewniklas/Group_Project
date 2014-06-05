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

	/**
	 * This field holds the input from the user.
	 */
	private String input;

	/**
	 * This field instantiates the {@link Scanner}
	 */
	private Scanner sc = new Scanner(System.in);

	/**
	 * This method is called by {@link #main Menu()}. 
	 */
	public void rules() {
		System.out.println("BackGround");
		System.out.println();
		System.out.println("The Galactic Empire has spread its forces too thin in its war against the Alliance to Restore the Republic."
				+ " \nIn the wake of the ongoing battles, pirates make easy targets out of innocent freighters, stealing wares and selling them on the black market. "
				+ "\nMaking supply runs is now so risky a business that only the toughest are willing to make the trips.");
		System.out.println();
		System.out.println("You are one of these so-called smugglers; however, nothing could prepare you for the brilliant ambush by Pirate Captain Rodriguez. "
				+ "\nThey cut power to your ship, massacred your crew, stole your supplies, and planted a bomb in one of the ship’s many reactor cores. "
				+ "\nYou find yourself with only one bullet against a ship full of pirates and a limited amount of time before the bomb goes off.");
		System.out.println();
		System.out.println("Forget honor, bravery, or ferocity – you just want to disarm the bomb so you can get off of the ship alive! "
				+ "\nIn the words of Rodriguez, “Why are you waiting around? Get to it already! Now!”");
		System.out.println();
		System.out.println("The rules to the game are very simple:");
		System.out.println("You will be spawned in the bottom left-hand corner of your ship. "
				+ "\nThere will also be six enemies in the ship with you. \nLocated throughout the ship "
				+ "are nine reactors and in one of these reactors is a bomb. \nFind the bomb"
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
		System.out.println("Welcome to: ");
		System.out.println();
		System.out.println("     _______..______      ___       ______  _______    .______    __  .______          ___   .___________. _______     _______.");
		System.out.println("    /       ||   _  \\    /   \\     /      ||   ____|   |   _  \\  |  | |   _  \\        /   \\  |           ||   ____|   /       |");
		System.out.println("   |   (----`|  |_)  |  /  ^  \\   |  ,----'|  |__      |  |_)  | |  | |  |_)  |      /  ^  \\ `---|  |----`|  |__     |   (----`");
		System.out.println("    \\   \\    |   ___/  /  /_\\  \\  |  |     |   __|     |   ___/  |  | |      /      /  /_\\  \\    |  |     |   __|     \\   \\    ");
		System.out.println(".----)   |   |  |     /  _____  \\ |  `----.|  |____    |  |      |  | |  |\\  \\----./  _____  \\   |  |     |  |____.----)   |   ");
		System.out.println("|_______/    | _|    /__/     \\__\\ \\______||_______|   | _|      |__| | _| `._____/__/     \\__\\  |__|     |_______|_______/    ");
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1)New");
		System.out.println("2)Continue");
		System.out.println("3)Rules");
		System.out.println("4)Options");
		System.out.println("0)Exit");
		System.out.println("Please enter one of the commands or the corresponding number:");
	}

	/**
	 * This method simply prints out all potential options for the User to choose from during the game's
	 * runtime. "Debug Mode" will show all objects on the grid and give the user infinite ammo; "Save"
	 * will save the game's state. "New Game" will reset the game's state. "Mute the music" will end the 
	 * sound byte for the background music, if the music is already off it will start again.
	 */
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

	/**
	 * This method simply prints out all possible actions the User can make during runtime. Primarily,
	 * the actions that will affect the game state are "Look", "Move", and "Shoot". Selecting options will
	 * call {@link #options()}. Selecting exit will end the program.
	 */
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
	 * This method simply prints out all possible actions the User can make during runtime; specifically
	 * after they select the "Look" function. Primarily, the actions that will affect the game state are 
	 * "Move" and "Shoot". Selecting options will call {@link #options()}. Selecting exit will end the 
	 * program. 
	 */
	public void turnSelect2() {
		System.out.println("What would you like to do?");
		System.out.println("1)Move");
		System.out.println("2)Shoot");
		System.out.println("3)Options");
		System.out.println("0)Exit");
		System.out.println("Please enter one of the commands:");
	}

	/**
	 * This method will set the userChoice to whatever {@code String} is passed to it.
	 * @param choice
	 * 	- {@code String} containing some value intended for {@link #userChoice}.
	 */
	public void setChoice(String choice){
		userChoice = choice;
	}

	/**
	 * This method will pass {@link #userChoice} to whatever calls it.
	 * @return
	 */
	public String getChoice(){
		return userChoice;
	}

	/**
	 * This method simply prints out the four cardinal directions that the user can move.
	 */
	public void moveTurn() {
		System.out.println("Which direction will you move?");
		System.out.println("1)Up");
		System.out.println("2)Down");
		System.out.println("3)Right");
		System.out.println("4)Left");
		System.out.println("Please enter one of the commands, command numbers, or the letter: ");
	}

	/**
	 * This method simply prints out the four cardinal directions that the User can look.
	 */
	public void lookDirections(){
		System.out.println("Which direction will you look?");
		System.out.println("1)Up");
		System.out.println("2)Down");
		System.out.println("3)Right");
		System.out.println("4)Left");
		System.out.println("Please enter one of the commands, command numbers, or the letter: ");
	}

	/**
	 * This method prints whenever an invalid command is entered
	 */
	public void invalidCMD() {
		System.out.println("Invalid Command: Please try again");
	}


	/**
	 * This method will pass an input - saved as a {@code String} to whatever calls it, after collecting it
	 * from the user.
	 */
	public String getInput() {
		input = sc.nextLine();
		return input;
	}

	/**
	 * This method prints whenever the {@link Player} collects the {@link Radar bomb detector}
	 */
	public void radarActivated() {
		System.out.println("You've picked up the detector!");
		System.out.println("You can now see which reactor the bomb is planted in!");
	}

	/**
	 * This prints whenever the {@link Player} picks up {@link ExtraAmmo} and can actually hold it. 
	 */
	public void ammoActivated(){
		System.out.println("You have picked up ammo!");
	}

	/**
	 * This method prints whenever the {@link Player} picks up the {@link Shield}.
	 */
	public void shieldActivated() {
		System.out.println("You have picked up the shield!");
		System.out.println("You will block attacks for 5 turns");
	}
	public void getAmmoAmount(){

	}

	/**
	 * This method will print whenever the {@link Player} shoots and kills an {@link Enemy}.
	 */
	public void killEnemy() {
		System.out.println("You've killed an enemy!");
		System.out.println("But you are out of ammo...");
	}

	/**
	 * This method will print whenever the {@link Player} fires a bullet
	 */
	public void shotFired() {
		System.out.println("You fired your bullet...");

	}

	/**
	 * This method simply prints out the four cardinal directions that the {@link Player} can shoot.
	 */
	public void shootTurn() {
		System.out.println("Which direction will you fire?");
		System.out.println("1)Up");
		System.out.println("2)Down");
		System.out.println("3)Right");
		System.out.println("4)Left");
		System.out.println("Please enter one of the commands, command numbers, or the letter: ");
	}

	/**
	 * This method prints when the user tries to shoot and does not have ammo.
	 */
	public void noBullet() {
		System.out.println("You have no bullet to fire.");

	}	
	
	/**
	 * This method prints when the game ends
	 */
	public void endScreen(){
		System.out.println("--------------------------------------");
		System.out.println("Thanks for playing!");
		System.out.println("Would you like to start a new game?");
		System.out.println("Yes or No");
		System.out.println("--------------------------------------");
	}
	
	/**
	 * This method prints when the user exits
	 */
	public void exitScreen(){
		System.out.println("--------------------------------------");
		System.out.println("Thanks for playing!");
		System.out.println("--------------------------------------");
	}

	/**
	 * This method exists to force the user to hit {@code Enter} before the next line is called.
	 */
	public void pause() {
		sc.nextLine();
	}

	/**
	 * This method exists to determine whether or not a new game will be created.
	 * @return
	 * 		{@code Boolean} answer - true if the user selects yes, false if the user selects no
	 */
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
	 * This method prints when the {@link Player} is murdered by an {@link Enemy}
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
	 * This method prints when the {@link Player} finds the bomb
	 */
	public void foundBriefcase() {
		System.out.println("YOU DISARMED THE BOMB");
	}

	/**
	 * This method prints out information pertinent to the game state. Such as: how much ammo the player
	 * has and whether they have a shield activated.
	 * @param ply
	 * 	- {@link Player} object
	 * @param hasRadar 
	 * 	- {@code boolean} true when {@link Player} has radar, false otherwise.
	 * @param m 
	 * 	- {@code int} containing the amount of turns left for {@link Shield}.
	 * 
	 */
	public void printStats(Player ply, boolean hasRadar, int m) {
		System.out.println("Ammo: " + ply.getAmmo());
		System.out.println("Lives: " + ply.getNumLives());
		if(hasRadar)
			System.out.println("Detector enabled");
		else if(ply.isHasShield())
			System.out.println("Shield enabled: " + m + " turns remaining");
	}

	/**
	 * This method prints whenever the {@link Player} wins
	 */
	public void congrats() {
		System.out.println("Congratulations you disarmed the bomb and made it out alive!");
	}

	/**
	 * This method prints whenever the {@link Player} loses.
	 */
	public void missionFailed() {
		System.out.println("YOU HAVE EXPLODED!!");
		System.out.println("Maybe you can do better next time");
	}
}
