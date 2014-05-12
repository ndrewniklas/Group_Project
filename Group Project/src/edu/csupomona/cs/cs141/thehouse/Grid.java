
package edu.csupomona.cs.cs141.thehouse;

import java.util.*;
/**
 * @author Ben, Andrew
 *
 */
public class Grid {
	
	/**
	 * The grid is a 9x9 array of GameObject
	 */
	private GameObject[][] go = new GameObject[9][9];	
	
	private Dice die;
	/**
	 * This field holds the briefcase, it will be the same as the {@code String[1][1]} for 
	 * {@link #tempRoomBlock}.
	 */
	private String briefcase = "[B]";
	
	/**
	 * Checks to see if we should print the name of the briefcase 
	 */
	private boolean showBriefcase;
	
	/**
	 * The constructor fills {@link #go} with new {@link GameObject} 
	 * Makes a new {@link Dice}
	 * Sets {@link #showBriefcase}
	 */
	public Grid() {
		die = new Dice();
		showBriefcase = true;
		for(int i = 0; i < go.length; ++i){
			for(int j = 0; j < go[i].length; ++j){
				go[i][j] = new GameObject();
			}
		}
	}
	
	/**
	 * Prints out the name of the object at that location
	 */
	public void printGrid(){
		for(int i = 0; i < go.length; ++i){
			for(int j = 0; j < go[i].length; ++j){
				System.out.print(go[i][j].getObjectName());
			}
			System.out.println();
		}
	}
	
	/**
	 * Sets the name of each room at that position in {@link #go}
	 */
	public void createRooms(){
		go[1][1].setObjectName("[R]");
		go[4][1].setObjectName("[R]");
		go[7][1].setObjectName("[R]");
		go[1][4].setObjectName("[R]");
		go[4][4].setObjectName("[R]");
		go[7][4].setObjectName("[R]");
		go[1][7].setObjectName("[R]");
		go[4][7].setObjectName("[R]");
		go[7][7].setObjectName("[R]");
	}
	
	/**
	 * If {@link #showBriefcase} is {@code true} then show the {@link #briefcase} name
	 * If it is {@code false} then show the default room name
	 * @return
	 * 		-The value of the if-then statement, either {@link #briefcase} or {@code "[R]"}
	 */
	public String createBriefcase(){
		if(showBriefcase)
			return briefcase;
		else
			return "[R]";
	}
	
	/**
	 * This method randomly places the briefcase by calling {@link Dice#roll()}. It then creates a temporary
	 * {@code String[][]} that stores the briefcase. Any time a {@link Player} enters a room, it checks to
	 * see if that room corresponds to the temporary room, holding the case. If so, the player wins and the 
	 * game ends.
	 * 
	 * AMN: Creates two local ints. Uses {@link Dice#roll(int)} to randomly choose 1, 4, or 7 (which are the
	 * rooms locations). Then it will set {@link GameObject#setObjectName(String)} to {@link #createBriefcase()}
	 */
	public void setBriefcase() {
		int firstRandom, secondRandom;
		firstRandom = die.roll(8);
		while(firstRandom != 1 && firstRandom != 4 && firstRandom != 7){
			firstRandom = die.roll(8);
		}
		secondRandom = die.roll(8);
		while(secondRandom != 1 && secondRandom != 4 && secondRandom != 7){
			secondRandom = die.roll(8);
		}
		go[firstRandom][secondRandom].setObjectName(createBriefcase());
	}
	
}
