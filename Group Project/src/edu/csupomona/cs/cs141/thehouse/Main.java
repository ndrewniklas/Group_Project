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
 * This class gives the program a starting point. Its only method is {@link #main(String[])}.
 * @author Ben Nickerson, Andrew Nipp, Andrew Niklas
 * 
 */
public class Main {

	/**
	 * The main class which creates {@link GameEngine} and calls
	 * {@link GameEngine#startGame()}
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.startGame();
	}

}
