/**
 * 
 */
package edu.csupomona.cs.cs141.thehouse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
//import the sun.audio package

/**
 * @author amnipp
 *
 */
public class Sound {
	

	private InputStream in;
	
	private AudioStream as;
	
	private ContinuousAudioDataStream cas;
	
	/**
	 * Holds the path name for each sound file
	 */
	private String path = "src/sound/";
	
	/**
	 * Plays the background music once
	 */
	public void backgroundMusic(){
		try{
		in = new FileInputStream(path + "red.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		
		}catch( IOException e){
			System.out.println("Could not load " + "red.wav");
		}
	}
	
	/**
	 * Loops through the background music using {@link ContinuousAudioDataStream}
	 */
	public void backgroundMusicLoop(){
		try{
		in = new FileInputStream(path + "red.wav");
		as = new AudioStream(in);
		AudioData data = as.getData();
		cas = new ContinuousAudioDataStream (data);
		AudioPlayer.player.start(cas);
		}catch(IOException e){
			System.out.println("Could not load " + "red.wav");
		}
	}
	
	/**
	 * Plays the sound of the {@link Player} dying 
	 */
	public void playerDied(){
		try{
		in = new FileInputStream(path + "death.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			System.out.println("Could not load " + "death.wav");
		}
	}
	
	/**
	 * Plays the sound that the gun makes when it fires
	 */
	public void shootSound(){
		try{
		in = new FileInputStream(path + "shoot.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			System.out.println("Could not load " + "shoot.wav");
		}
	}
	
	/**
	 * Plays the {@link Enemy} death sound
	 */
	public void enemyDeath(){
		try{
		in = new FileInputStream(path + "enemydead.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			System.out.println("Could not load " + "enemydead.wav");
		}
	}
	
	/**
	 * Plays the sound when the {@link Player} finds the bomb
	 */
	public void winGame(){
		try{
		in = new FileInputStream(path + "wingame.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			System.out.println("Could not load " + "wingame.wav");
		}
	}
	
	/**
	 * Plays the sound when the {@link Player} sees an {@link Enemy}
	 */
	public void foundEnemy(){
		try{
		in = new FileInputStream(path + "found.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			System.out.println("Could not load " + "found.wav");
		}
	}
	
	/**
	 * Plays the sound that the {@link Player} picks up a powerup
	 */
	public void foundPowerup(){
		try{
		in = new FileInputStream(path + "item.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			System.out.println("Could not load " + "item.wav");
		}
	}
	
	/**
	 * Stops the background sound from playing
	 */
	public void stopBackgroundLoop() {
		AudioPlayer.player.stop(cas);
	}
}