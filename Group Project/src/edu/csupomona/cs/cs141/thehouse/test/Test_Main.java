/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse.test;
import java.util.Scanner;

import edu.csupomona.cs.cs141.thehouse.GameEngine;
import edu.csupomona.cs.cs141.thehouse.Grid;
import edu.csupomona.cs.cs141.thehouse.Player;

/**
 * @author Andrew
 *
 */
public class Test_Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		printGrid_Test1();
		//		printGrid_Test2();
		//		movementTest_Basic();
		//		playerMove_Test1();
		//		playerMoveControl();
		//		printGrid_Test3();
		//		playerMove_Test2();
		//		printGrid_Test4();		
		//		printGrid_Test5();
		//		printGrid_Test6();
		//		playerMove_Test3();
		//		printGrid_Test7();
		//		playerMove_Test4();
		//		enemyMovement_Test1();
		//		enemyMovement_Test2(100);
		//		toggleDebug_Test1();
		ui_Test1();
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
		//		grid.setBriefcase();
		grid.printGrid();		
		System.out.println("-----------------------------------------------------");
	}

	/**
	 * tested movement of player by printing out player position in the grid
	 */
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

	/**
	 * tested movement of player and printing player on grid
	 */
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

	/**
	 * tested spawning of enemies and placement on grid
	 */
	public static void printGrid_Test3(){
		System.out.println("Grid: Print Test 3");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		Grid grid = new Grid();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
	}

	/**
	 * spawning player, enemies, and power-ups and player movement
	 */
	public static void playerMove_Test2() {
		System.out.println("Player movement test 2");
		System.out.println("Player movement w/ Other Object Spawn");
		Player ply = new Player();
		Grid grid = new Grid();
		grid.rePopulateGrid(ply);
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.printGrid();

		while(true){
			for(int i:ply.getPosition()){
				System.out.print(i + " ");
			}
			System.out.println();
			Scanner sc = new Scanner(System.in);
			System.out.print("Input direction: ");
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
		System.out.println("Spawn PowerUps, Enemies, and the Briefcase room");
		Grid grid = new Grid();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
	}

	/**
	 * tests random spawning of enemies
	 */
	public static void printGrid_Test5(){
		System.out.println("Grid: Print Test 4");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		System.out.println("Spawn PowerUps, Enemies, and the Briefcase room in an infinte loop");
		while(true){
			Grid grid = new Grid();
			//			grid.setBriefcase();
			//			grid.setEnemy();
			//			grid.setPowerUps();
			grid.printGrid();
			System.out.println("Number of Enemies: " + grid.getNumEnemies());
			System.out.println("-----------------------------------------------------");
		}
	}

	/**
	 * tests enemy random movement
	 */
	public static void printGrid_Test6(){
		System.out.println("Grid: Print Test 4");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		System.out.println("Enemies will move every loop");
		Grid grid = new Grid();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
		while(true){
			grid.moveEnemy(grid, null);
			grid.printGrid();
			System.out.println("-----------------------------------------------------");
		}
	}

	/**
	 * tests enemy movement with player movement
	 */
	public static void playerMove_Test3() {
		System.out.println("Player movement test 4");
		System.out.println("Enemies will move with player");
		Player ply = new Player();
		Grid grid = new Grid();
		//		grid.setEnemy();
		grid.rePopulateGrid(ply);
		//		grid.setBriefcase();
		//		grid.setPowerUps();
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
			grid.moveEnemy(grid, null);
			grid.rePopulateGrid(ply);
			grid.printGrid();
			System.out.println("Number of Enemies: " + grid.getNumEnemies());
			System.out.println("-----------------------------------------------------");
		}
	}

	/**
	 * check spawning of power-ups and enemies don't stack and not in rooms
	 */
	public static void printGrid_Test7(){
		System.out.println("Grid: Print Test 7");
		System.out.println("B is the briefcase, R is the room, P is the player, E is the Enemy");
		System.out.println("Checks if the object is free then prints out the object at each location");
		Grid grid = new Grid();
		Player ply = new Player();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.rePopulateGrid(ply);
		for(int i = 0; i < grid.gridSize(); ++i){
			for(int j = 0; j < grid.gridSize(); ++j){
				System.out.print("Location (" + i + "," + j + "): " );
				System.out.print(grid.checkIfLocationFree(i, j));
				System.out.println(" Object Name: " + grid.getObjectAtLocation(i, j));
			}
		}
		grid.printGrid();
		System.out.println("Number of Enemies: " + grid.getNumEnemies());
		System.out.println("-----------------------------------------------------");
	}

	/**
	 * prints what object is at each location
	 */
	public static void playerMove_Test4() {
		System.out.println("Player movement test 3");
		System.out.println("Standard Player Movement w/ Other Object Spawn and Object Testing");
		System.out.println("|-----------------------------------------------------------");
		Player ply = new Player();
		Grid grid = new Grid();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.rePopulateGrid(ply);
		for(int i = 0; i < grid.gridSize(); ++i){
			for(int j = 0; j < grid.gridSize(); ++j){
				System.out.print("|Location [" + i + "," + j + "]: " );
				System.out.print(grid.checkIfLocationFree(i, j));
				System.out.println(" Object Name: " + grid.getObjectAtLocation(i, j));
			}
			System.out.println("|-----------------------------------------------------------");
		}
		grid.printGrid();
		System.out.println("|---------------------------------------------------------------");
		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.print("Input the direction: ");
			String input = sc.next();
			ply.movePlayer(input);
			grid.moveEnemy(grid, null);
			grid.rePopulateGrid(ply);
			for(int i = 0; i < grid.gridSize(); ++i){
				for(int j = 0; j < grid.gridSize(); ++j){
					System.out.print("|Location (" + i + "," + j + "): " );
					System.out.print(grid.checkIfLocationFree(i, j));
					System.out.println(" Object Name: " + grid.getObjectAtLocation(i, j));
				}
				System.out.println("|-----------------------------------------------------------");
			}
			grid.printGrid();
			System.out.println("|-----------------------------------------------------------");
		}
	}

	/**
	 * 
	 */
	private static void enemyMovement_Test1() {
		System.out.println("Enemy Movement Test 1");
		Player ply = new Player();
		Grid grid = new Grid();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.rePopulateGrid(ply);
		grid.printGrid();


		while (true) {
			Scanner sc = new Scanner(System.in);
			String input=sc.nextLine();
			ply.movePlayer(input);

			grid.moveEnemy(grid, null);
			grid.rePopulateGrid(ply);
			grid.printGrid();
		}
	}

	private static void enemyMovement_Test2(int i) {
		System.out.println("Enemy Movement Test 1");
		Player ply = new Player();
		Grid grid = new Grid();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		grid.rePopulateGrid(ply);
		grid.printGrid();


		while (i>=0) {
			grid.moveEnemy(grid, ply);
			grid.rePopulateGrid(ply);
			grid.printGrid();
			i--;
		}
	}

	/**
	 * tests function of debug mode 
	 */
	public static void toggleDebug_Test1(){
		System.out.println("Enemy Movement Test 1");
		Player ply = new Player();
		Grid grid = new Grid();
		GameEngine ge = new GameEngine();
		//		grid.setBriefcase();
		//		grid.setEnemy();
		//		grid.setPowerUps();
		//grid.rePopulateGrid();
		grid.printGrid();


		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Please Select 'on' or 'off' (all lowercase): ");
			String input = sc.nextLine();
			switch(input){
			case "on":
				ge.debugMode(true);
				System.out.println("WECLOME TO DEBUG MODE");
				break;
			case "off":
				ge.debugMode(false);
				System.out.println("I guess you like being blind...");
				break;
			default:
				System.out.println("BAD CMD");
			}
			grid.moveEnemy(grid, ply);
			//grid.rePopulateGrid();
			grid.printGrid();
		}
	}

	/**
	 * tests functionality of entire game with ui
	 */
	public static void ui_Test1(){
		GameEngine ge = new GameEngine();
		ge.startGame();		
	}
}
