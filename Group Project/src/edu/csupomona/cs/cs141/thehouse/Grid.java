/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;

/**
 * @author Ben, Andrew
 *
 */
public class Grid {
	
	/**
	 * This field is a {@code String[][]} representing either a block of 9 spaces in the grid,
	 * or the full grid.
	 */
	private String[][] roomOneBlock, roomTwoBlock, roomThreeBlock, roomFourBlock, roomFiveBlock,
					   roomSixBlock, roomSevenBlock, roomEightBlock, roomNineBlock, fullGrid;
	
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
	}
	
	/**
	 * This method will give the grid in its entirety, to whatever calls it. Chiefly {@link GameEngine#setGrid(String[][]).
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
	
	
}
