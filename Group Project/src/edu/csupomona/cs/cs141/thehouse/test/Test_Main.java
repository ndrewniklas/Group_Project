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
//		playerMove();
//		playerMoveControl();
		printGrid_Test3();
	}
	
	public static void printGrid_Test1(){
		System.out.println("Grid: Print Test 1");
		System.out.println("R is the room");
		Grid grid = new Grid();
		grid.printGrid();
		System.out.println("-----------------------------------------------------");
	}
	
	public static void printGrid_Test2(){
		System.out.println("Grid: Print Test 2");
		System.out.println("B is the briefcase, R is the room, P is the player");
		Grid grid = new Grid();
		grid.printGrid();
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
		grid.rePopulateGrid(ply);
		grid.printGrid();
		
		while(true){
			for(int i:ply.getPosition()){
				System.out.println(i);
			}
			Scanner sc = new Scanner(System.in);
			String input=sc.next();
			ply.movePlayer(input);
			grid.rePopulateGrid(ply);
			grid.printGrid();
			
		}
	}

	public static void printGrid_Test3(){
		System.out.println("Grid: Print Test 3");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		Grid grid = new Grid();
		grid.setBriefcase();
		grid.setEnemy();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
	}
//	public static void playerMoveControl() {
//		System.out.println("Control of player movement test");
//		PlayerControl ply = new PlayerControl();
//		Grid grid = new Grid();
//		grid.printGrid();
//		
//		while(true){
//			Scanner sc = new Scanner(System.in);
//			String input=sc.next();
//			ply.movePlayerTest(input);
//			grid.rePopulateGrid(ply);
//			grid.printGrid();
//		}
//	}
}
