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

import java.util.Random;

/**
 * @author Ben
 *
 */
public class Dice {

	/**
	 * This field instantiates {@link Random}.
	 */
	private Random dice = new Random();
	
	/**
	 * This method rolls two die and returns their total.
	 * @param
	 * 		dieNumber - used to specify the number of sides
	 * @return
	 * 		dieTotal - a random number between 0 and dieNumber saved as an {@code int}.
	 */
	public int roll(int dieNumber) {
		int dieTotal = dice.nextInt(dieNumber);
		return dieTotal;
	}
}
