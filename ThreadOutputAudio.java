import java.io.File;
import javax.sound.sampled.*;

public class ThreadOutputAudio implements Runnable {
	private String folderName = "audio\\";
    private static boolean playClang = false;
	
    public boolean getClang() {
        return playClang;
    }
    public static void setClang(boolean p) {
        System.out.println(p);
        playClang = p;
    }

	@Override
	public void run() {
		while (true) {
            if (getClang()) {
                System.out.println("PLAYING AUDIO");
                playFile(folderName + "clang.wav");
                playClang = false;
            }
		}
	}

    /*
     * must be a .wav file
     */
	public static void playFile(String file)  {
        try {//throws UnsupportedAudioFileException, IOException, LineUnavailableException
            File audioFile = new File(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
            audioClip.close();  
            audioStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        /*try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }*/
    }
}
