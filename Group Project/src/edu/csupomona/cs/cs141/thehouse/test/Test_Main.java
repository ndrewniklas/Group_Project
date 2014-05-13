/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse.test;
import java.util.Scanner;

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
//		movementTest_Basic();
		playerMove();
	}
	
	public static void printGrid_Test1(){
		System.out.println("Grid: Print Test 1");
		System.out.println("R is the room");
		Grid grid = new Grid();
		System.out.println("-----------------------------------------------------");
	}
	
	public static void printGrid_Test2(){
		System.out.println("Grid: Print Test 2");
		System.out.println("B is the briefcase, R is the room, P is the player");
		Grid grid = new Grid();
		grid.setBriefcase();
		
		System.out.println("-----------------------------------------------------");
	}
	
	public static void movementTest_Basic() {
		System.out.println("Player movement test 1");
		Player ply = new Player();
		
		while (true) {
			for(int i:ply.getPosition()){
				System.out.println(i);
			}
			Scanner sc = new Scanner(System.in);
			String input = sc.next();
			ply.movePlayer(input);
			
		}
	}
	
	public static void playerMove() {
		System.out.println("Player movement test 2");
		Player ply = new Player();
		Grid grid = new Grid();
		
		while(true){
			Scanner sc = new Scanner(System.in);
			String input=sc.next();
			ply.movePlayer(input);
			grid.printGrid();
			
		}
	}
}
