import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.HashMap;

public class Sound {
	
	protected static HashMap<String, Clip> soundList = new HashMap<>();
	
	public static void createSound(String name, String filepath) {
		try {
            InputStream inputStream = Sound.class.getClassLoader().getResourceAsStream(filepath);
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + filepath);
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            soundList.put(name, clip); //stores by name
        } catch (Exception e) {
            System.err.println("Failed to load sound: " + filepath);
            e.printStackTrace();
        }
	}
	public static void playSound(String name) {
		Clip clip = soundList.get(name);
		clip.setFramePosition(0);
		clip.start();
	}
	
	public static void setLooped(String name) {
		Clip clip = soundList.get(name);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void stopSound(String name) {
		Clip clip = soundList.get(name);
		clip.setFramePosition(0);
		clip.stop();
	}
	
	
	public static void setVolume(String name, double volume) {
		Clip clip = soundList.get(name);
        FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log10(volume) * 20);
        fc.setValue(Math.max(fc.getMinimum(), Math.min(dB, fc.getMaximum())));
    }
}
