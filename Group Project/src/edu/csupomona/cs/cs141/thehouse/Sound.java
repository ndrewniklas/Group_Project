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
	InputStream in;
	AudioStream as;
	String path = "src/sound/";
	public void backgroundMusic(){
		try{
		in = new FileInputStream(path + "red.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		
		}catch( IOException e){
			e.printStackTrace();
		}
	}
	public void backgroundMusicLoop(){
		try{
		in = new FileInputStream(path + "red.wav");
		as = new AudioStream(in);
		AudioData data = as.getData();
		ContinuousAudioDataStream cas = new ContinuousAudioDataStream (data);
		AudioPlayer.player.start(cas);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void playerDied(){
		try{
		in = new FileInputStream(path + "death.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			e.printStackTrace();
		}
	}
	public void shootSound(){
		try{
		in = new FileInputStream(path + "shoot.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			e.printStackTrace();
		}
	}
	public void emptySound(){
		try{
		in = new FileInputStream(path + "empty.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			e.printStackTrace();
		}
	}
	public void enemyDeath(){
		try{
		in = new FileInputStream(path + "enemydead.wav");
		as = new AudioStream(in);
		AudioPlayer.player.start(as);
		}catch( IOException e){
			e.printStackTrace();
		}
	}
}