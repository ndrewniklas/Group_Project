
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Ben, Andrew
 *
 */
public class Grid {
	
	/**
	 * This field is a {@code String[][]} representing either a block of 9 spaces in the grid,
	 * the full grid, or the temporary block that hides the briefcase.
	 */
	private String[][] roomOneBlock, roomTwoBlock, roomThreeBlock, roomFourBlock, roomFiveBlock,
					   roomSixBlock, roomSevenBlock, roomEightBlock, roomNineBlock, fullGrid,
					   tempRoomBlock;
	
	/**
	 * This field holds the briefcase, it will be the same as the {@code String[1][1]} for 
	 * {@link #tempRoomBlock}.
	 */
	private String briefcase = "[!]";
	
	/**
	 * This constructor builds the grid, one block of 9 rooms at a time.
	 */
	public Grid() {
		blockBuilder(roomOneBlock = new String[3][3]);
		blockBuilder(roomTwoBlock = new String[3][3]);
		blockBuilder(roomThreeBlock = new String [3][3]);
		blockBuilder(roomFourBlock = new String[3][3]);
		blockBuilder(roomFiveBlock = new String[3][3]);
		blockBuilder(roomSixBlock = new String[3][3]);
		blockBuilder(roomSevenBlock = new String[3][3]);
		blockBuilder(roomEightBlock = new String[3][3]);
		blockBuilder(roomNineBlock = new String[3][3]);
		setBriefcase();
	}
	
	/**
 	 * This method will give the grid in its entirety, to whatever calls it. Chiefly {@link GameEngine #setGrid(String[][])}.
	 * @return fullGrid
	 */
	public String[][] getfullGrid() {
		return fullGrid;
	}
	
	/**
	 * This method builds each individual block and then constructs {@link #fullGrid}.
	 * @param block
	 * 		- one of the 3x3 squares in the {@link #fullGrid}.
	 */
	private void blockBuilder(String[][] block) {
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block[i].length; j++) {
				block[i][j] = "[]";
			}
		}
	}
	
	/**
	 * This method randomly places the briefcase by calling {@link Dice#roll()}. It then creates a temporary
	 * {@code String[][]} that stores the briefcase. Any time a {@link Player} enters a room, it checks to
	 * see if that room corresponds to the temporary room, holding the case. If so, the player wins and the 
	 * game ends.
	 */
	private void setBriefcase() {
	}
	
}
