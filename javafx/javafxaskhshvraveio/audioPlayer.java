/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxaskhshvraveio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
/**
 *
 * @author angle
 */
//Only supports wav files. The more complex MediaPlayer can be used to play mp3
public class audioPlayer implements LineListener {
	private boolean playCompleted = true;
	private String audioFP;
	private Clip audioClip;
	
	public void audioPath(String audioFParg) {
		audioFP = audioFParg;
		File audioFile = new File(audioFP);
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			audioClip = (Clip)AudioSystem.getLine(info);
			
			audioClip.addLineListener(this);
			
			audioClip.open(audioStream);

		}
		catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported");
			ex.printStackTrace();
		}
		catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable");
			ex.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println("Error playing the audio file");
			ex.printStackTrace();
		}
		
	}
	
	public void start() throws InterruptedException {
		audioClip.start();
	}
	
	public void stop() {
		audioClip.stop();
	}

	public boolean getPlayCompleted() {
        return playCompleted;
    }

	public void setPlayCompleted(boolean playCompleted) {
		this.playCompleted = playCompleted;
	}
	
	@Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
		
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started");
             
        }
		else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback stopped");
        }
 
    }

}
