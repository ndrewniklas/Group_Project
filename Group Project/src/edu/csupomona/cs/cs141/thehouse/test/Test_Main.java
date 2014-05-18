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
//		playerMove_Test1();
//		playerMoveControl();
		printGrid_Test3();
		
//		playerMove_Test2();
		printGrid_Test4();
		
		//printGrid_Test5 is an infinite loop used to test to see if anything spawns outside the boundaries 
//		printGrid_Test5();
		//playerMove_Test3() should spawn the powerups and enemies
		playerMove_Test3();
		//printGrid_Test6 is an infinite loop which should move the enemies
//		printGrid_Test6();
		//Enemy movement
//		playerMove_Test4();
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
	
	public static void playerMove_Test1() {
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
	
	public static void playerMove_Test2() {
		System.out.println("Player movement test 2");
		Player ply = new Player();
		Grid grid = new Grid();
		grid.rePopulateGrid(ply);
		grid.setBriefcase();
		grid.setEnemy();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
		
		while(true){
			for(int i:ply.getPosition()){
				System.out.println(i);
			}
			Scanner sc = new Scanner(System.in);
			String input=sc.next();
			ply.movePlayer(input);
			grid.rePopulateGrid(ply);
			grid.printGrid();
			System.out.println("Number of Enemies: " + grid.getNumEnemies());
			System.out.println("-----------------------------------------------------");
		}
	}
	public static void printGrid_Test4(){
		System.out.println("Grid: Print Test 4");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		Grid grid = new Grid();
		grid.setBriefcase();
		grid.setEnemy();
		grid.setPowerUps();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
	}
	public static void printGrid_Test5(){
		System.out.println("Grid: Print Test 4");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		while(true){
			Grid grid = new Grid();
			grid.setBriefcase();
			grid.setEnemy();
			grid.setPowerUps();
			grid.printGrid();
			System.out.println("Number of Enemies: " + grid.getNumEnemies());
			System.out.println("-----------------------------------------------------");
		}
	}
	public static void playerMove_Test3() {
		System.out.println("Player movement test 3");
		Player ply = new Player();
		Grid grid = new Grid();
		grid.rePopulateGrid(ply);
		grid.setBriefcase();
		grid.setEnemy();
		grid.setPowerUps();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
		
		while(true){
			for(int i:ply.getPosition()){
				System.out.println(i);
			}
			Scanner sc = new Scanner(System.in);
			String input=sc.next();
			ply.movePlayer(input);
			grid.rePopulateGrid(ply);
			grid.printGrid();
			System.out.println("Number of Enemies: " + grid.getNumEnemies());
			System.out.println("-----------------------------------------------------");
		}
	}
	public static void printGrid_Test6(){
		System.out.println("Grid: Print Test 4");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		Grid grid = new Grid();
		grid.setBriefcase();
		grid.setEnemy();
		grid.setPowerUps();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
		while(true){
			grid.moveEnemyOnGrid();
			grid.printGrid();
			System.out.println("-----------------------------------------------------");
		}
	}
	public static void playerMove_Test4() {
		System.out.println("Player movement test 4");
		Player ply = new Player();
		Grid grid = new Grid();
		grid.setEnemy();
		grid.rePopulateGrid(ply);
		grid.setBriefcase();
		grid.setPowerUps();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
		
		while(true){
			for(int i:ply.getPosition()){
				System.out.println(i);
			}
			Scanner sc = new Scanner(System.in);
			String input=sc.next();
			ply.movePlayer(input);
			grid.moveEnemyOnGrid();
			grid.rePopulateGrid(ply);
			grid.printGrid();
			System.out.println("Number of Enemies: " + grid.getNumEnemies());
			System.out.println("-----------------------------------------------------");
		}
	}
}
