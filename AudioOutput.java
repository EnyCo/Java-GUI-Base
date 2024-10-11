import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioOutput implements Runnable {
	private static String folderName = "audio/";
	
	@Override
	public void run() {
		while (true) {
            //playFile("audio.wav");
		}
	}

    /*
     * must be a .wav file
     */
	public static void playFile(String file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }
}
