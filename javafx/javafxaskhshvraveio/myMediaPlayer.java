/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxaskhshvraveio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 *
 * @author angle
 */
public class myMediaPlayer {

    private Media media;
    private MediaPlayer player;
    private boolean playCompleted = true;
    private String mediaFile;

    public myMediaPlayer() {

    }

    public void loadMedia(String mediaFilePath) {
        mediaFile = null;
        mediaFile = new File(mediaFilePath).toURI().toString();
        media = null;
        media = new Media(mediaFile);
        System.out.println("File: " + mediaFile);
        player = null;
        player = new MediaPlayer(media);
    }

    public void startMedia() {
        playCompleted = false;
        player.play();
    }

    public void pauseMedia() {
        playCompleted = true;
        player.pause();
    }

    public void stopMedia() {
        playCompleted = true;
        player.stop();
    }

    public boolean getPlayCompleted() {
        return playCompleted;
    }

    public void setPlayCompleted(boolean playCompleted) {
        this.playCompleted = playCompleted;
    }

    public void setVolume(double vol) {
        player.setVolume(vol);
    }

    public double getVolume() {
        return player.getVolume();
    }

}
