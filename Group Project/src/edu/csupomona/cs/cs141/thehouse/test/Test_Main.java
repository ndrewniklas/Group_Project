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
		printGrid_Test2();
	}
	
	public static void printGrid_Test1(){
		System.out.println("Grid: Print Test 1");
		System.out.println("R is the room");
		Grid grid = new Grid();
		grid.populateGrid();
		grid.printGrid();
		System.out.println("-----------------------------------------------------");
	}
	public static void printGrid_Test2(){
		System.out.println("Grid: Print Test 2");
		System.out.println("B is the briefcase, R is the room, P is the player");
		Grid grid = new Grid();
		grid.populateGrid();
		grid.setBriefcase();
		grid.printGrid();
		
		System.out.println("-----------------------------------------------------");
	}
}
