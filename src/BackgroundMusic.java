/**
* Lead Author(s):
* @author Celeste Rodriguez
*
* References:
* https://stackoverflow.com/questions/16867976/how-do-you-add-music-to-a-jframe
* ChatGPT
*
* Version: 2025-05-30
* 
* Responsibilities of class: Allow the card game the ability to have music playing in the background
*/

import java.io.File;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
	// fields
	private Clip clip;
	private Random random = new Random();

	/**
	 * Purpose: Find random song and play it
	 * @param songPaths
	 */
	public void playRandomSong(List<String> songPaths) {
		if (songPaths == null || songPaths.isEmpty()) {
			System.out.println("No songs provided."); // if no songs found, return
			return;
		}
		String selectedSong = songPaths.get(random.nextInt(songPaths.size()));
		System.out.println("Now playing: " + selectedSong); // find random song and play it
		playMusic(selectedSong);
	}

	/**
	 * Purpose: Play song
	 * @param filePath
	 */
	    public void playMusic(String filePath) {
	        try {
	            File musicPath = new File(filePath);
	            if (musicPath.exists()) {
	                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
	                clip = AudioSystem.getClip();
	                clip.open(audioInput);
	                clip.loop(Clip.LOOP_CONTINUOUSLY); // loop song
	                clip.start();
	            } else {
	                System.out.println("Music file not found: " + filePath);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

}
