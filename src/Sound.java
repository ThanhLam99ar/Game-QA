
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

public class Sound implements LineListener {
		private AudioInputStream inputStream;
		private AudioFormat format;
		private Clip clip;
		private boolean isCompleted;
		
		private DataLine.Info setInfo(File file) {
			try {
				inputStream = AudioSystem.getAudioInputStream(file);
				format = inputStream.getFormat();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("set info error: "+ e.getMessage());
			}
			return new DataLine.Info(Clip.class, format);
		}
		public void setFile(String fileLocation) {
			File file = new File(fileLocation);
			try {
				DataLine.Info info = setInfo(file);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(inputStream);
				clip.addLineListener(this);
			}catch(IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		public void play() {
			try {
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
				do {
					Thread.sleep(25);
				}while(!isCompleted);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				clip.close();
			}
		}
		@Override
		public void update(LineEvent event) {
			LineEvent.Type type = event.getType();
			if(type == LineEvent.Type.STOP)
				isCompleted = true;
			
		}
	}
class SoundThread implements Runnable {

	String fileLocation;
	Sound player;
	long delay;
	SoundThread(String location ){
		this.fileLocation = location;
	}
	SoundThread(String location, long d){
		this.fileLocation = location;
		this.delay = d;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(delay);
		}catch (Exception e) {
			e.printStackTrace();
		}
		player = new Sound();
		player.setFile(fileLocation);
		player.play();
		
	}
	
}
