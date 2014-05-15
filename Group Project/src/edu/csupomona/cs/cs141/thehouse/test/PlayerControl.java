
package edu.csupomona.cs.cs141.thehouse.test;

import java.util.Scanner;

import edu.csupomona.cs.cs141.thehouse.GameObject;


public class PlayerControl extends GameObject{
	
	private int xPosition;
	private int yPosition;
	private int xpre;
	private int ypre;

	public PlayerControl(){
		super("[P]",0,8);
		
		xPosition=0;
		yPosition=8;
	}
	
	/**
	 * {@link #movePlayer()} This method will move the player based on user input
	 */
	public void movePlayer(String input) {
		
		String cmd=input.toLowerCase();
		xpre=xPosition;
		ypre=yPosition;
		switch (cmd) {
		case "up":
			setXY(xPosition, --yPosition);
			break;
		case "down":
			setXY(xPosition, ++yPosition);
			break;
		case "left":
			setXY(--xPosition, yPosition);
			break;
		case "right":
			setXY(++xPosition, yPosition);
			break;
		default:
			System.out.println("Invalid command: Try again");
			break;
		}
	}
	
	public void movePlayerTest(String input){
        boolean upPossible = roomExists(xPosition, yPosition - 1);
        boolean downPossible = roomExists(xPosition, yPosition + 1);
        boolean rightPossible = roomExists(xPosition + 1, yPosition);
        boolean leftPossible = roomExists(xPosition - 1, yPosition);
        System.out.print("Where would you like to go :");
        if (upPossible) {
            System.out.print(" up");
        }
        if (rightPossible) {
            System.out.print(" right");
        }
        if (downPossible) {
            System.out.print(" down");
        }
        if (leftPossible) {
            System.out.print(" left");
        }
        System.out.print(" ? ");
       
        if (input.equals("up") && upPossible) {
            yPosition--;
        } else if (input.equals("down") && downPossible) {
            yPosition++;
        } else if (input.equals("right") && rightPossible) {
            xPosition++;
        } else if (input.equals("left") && leftPossible) {
            xPosition--;
        }
    }
	
	/**
	 * @param xPosition2
	 * @param i
	 * @return
	 */
	private boolean roomExists(int xPosition2, int i) {
		if(xPosition2 == 1 ||xPosition2 == 4||xPosition2 == 7){
			if(i==1||i==4||i==7){
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @return
	 */
	public int get_yPosition() {
		return yPosition;
	}
	
	/**
	 * @return
	 */
	public int get_xPosition() {
		return xPosition;
	}
	
	/**
	 * @return
	 */
	public int getYPre() {
		return ypre;
	}
	
	/**
	 * @return
	 */
	public int getXPre() {
		return xpre;
	}
		
	/**
	 * This method will check if there is briefcase in front of the player
	 * @return This return value will return true if there is a briefcase in front of the player
	 */
	public boolean getBriefCase() {
		return true;
	}
	
}
