package moteurJeu;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

		private Clip clip;
		
		public Audio(String son) {
			try {
				AudioInputStream audio= AudioSystem.getAudioInputStream(getClass().getResource(son));
				clip = AudioSystem.getClip();
				clip.open(audio);
			}catch(Exception e) {}
		}
		
		public Clip getClip() {
			return clip;
		}
		public void play() {
			if(clip != null) {
				clip.start();
			}
		}
		public void stop() {
			clip.stop();
		}
		public  static void playSound(String son) {
			Audio a = new Audio(son);
			a.play();
		}
}
