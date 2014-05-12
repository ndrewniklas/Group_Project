/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse.test;
import edu.csupomona.cs.cs141.thehouse.*;
/**
 * @author Andrew
 *
 */
public class Test_Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printGrid_Test1();
	}
	
	public static void printGrid_Test1(){
		Grid grid = new Grid();
		grid.createRooms();
		grid.printGrid();
	}
}
