import javax.sound.sampled.*;

public class ThreadOutputAudio implements Runnable {
	private String folderName = "audio\\";

	@Override
	public void run() {
		while (true) {
            try {
                if (Main.getClang()) {
                    playFile(folderName + "clang.wav");
                    Main.setClang(false);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                Thread.sleep(Main.getThreadSleep());
            } catch (InterruptedException e) {
                System.out.println("PROCESS CLOCK NOT WORKING");
                e.printStackTrace();
            }
		}
	}

    /*
     * must be a .wav file
     */
	public static void playFile(String file)  {
        try {//throws UnsupportedAudioFileException, IOException, LineUnavailableException
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(file));
            clip.open(inputStream);
            clip.start(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
