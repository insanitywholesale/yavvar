/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxaskhshvraveio;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
//import javafx.beans.Observable;

/**
 *
 * @author angle
 */
public class FXMLDocumentController implements Initializable {

    //FXML variables
    @FXML
    private Label info, playingInfo;

    @FXML
    private Slider SlBass, SlMidrange, SlTreble, SlBalance, SlVolume;

    @FXML
    private Button storeButton, stopButton, startButton, resetButton;

    @FXML
    private RadioButton presetDefault, preset1, preset2;

    @FXML
    private ToggleGroup presetGroup;
    
    @FXML
    private Menu fileMenu, helpMenu;
    
    @FXML
    private MenuItem openFileMenuItem, helpAboutMenuItem;

    //non-FXML variables
    
    //
        
    //old way of playing audio (only supports wav)
    audioPlayer player;
    String playerInfo = "Stopped";

    //new way of playing audio (supports mp3 too)
    myMediaPlayer mPlayer;
    String mPlayerInfo = "Stopped";
    
    myFileChooser mFC = new myFileChooser();
    
    //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Audio files", "*.mp3", "*.wav");
    //fileChooser.getExtensionFilters(extFilter).add(extFilter);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupPresets();
        balanceLabel();
        //audioStuff();
        mediaStuff();
        showInfo();
    }

    Preset Presets[] = new Preset[3];

    void setupPresets() {
        Presets[0] = new Preset(0, 0, 0, 0, 0);
        Presets[1] = new Preset(-1, -1, 9, 0, 4);
        Presets[2] = new Preset(2, 4, -2, 4, 2);
    }

    @FXML
    void loadPresetDefault() {
        SlBass.setValue(Presets[0].bass);
        SlMidrange.setValue(Presets[0].midrange);
        SlTreble.setValue(Presets[0].tremble);
        SlBalance.setValue(Presets[0].balance);
        SlVolume.setValue(Presets[0].volume);
        presetDefault.setSelected(true);
        showInfo();
    }

    @FXML
    void loadPreset1() {
        SlBass.setValue(Presets[1].bass);
        SlMidrange.setValue(Presets[1].midrange);
        SlTreble.setValue(Presets[1].tremble);
        SlBalance.setValue(Presets[1].balance);
        SlVolume.setValue(Presets[1].volume);
        showInfo();
    }

    @FXML
    void loadPreset2() {
        SlBass.setValue(Presets[2].bass);
        SlMidrange.setValue(Presets[2].midrange);
        SlTreble.setValue(Presets[2].tremble);
        SlBalance.setValue(Presets[2].balance);
        SlVolume.setValue(Presets[2].volume);
        showInfo();
    }

    @FXML
    public void storePreset(Preset preset) {
        preset.bass = (int) SlBass.getValue();
        preset.midrange = (int) SlMidrange.getValue();
        preset.tremble = (int) SlTreble.getValue();
        preset.balance = (int) SlBalance.getValue();
        preset.volume = (int) SlVolume.getValue();
    }

    @FXML
    private void savePresetAction(ActionEvent event) {
        if (presetDefault.isSelected()) {
            storePreset(Presets[0]);
        } else if (preset1.isSelected()) {
            storePreset(Presets[1]);
        } else if (preset2.isSelected()) {
            storePreset(Presets[2]);
        } else {
            System.out.println("Error: No preset button is selected");
        }
    }

    @FXML
    private void balanceLabel() {
        SlBalance.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n > 0) {
                    return "Right";
                }
                if (n < 0) {
                    return "Left";
                }
                if (n == 0) {
                    return "Center";
                }
                return "Center";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Left":
                        return -5.0;
                    case "Right":
                        return 5.0;

                    default:
                        return 0.0;
                }
            }
        });
    }

    @FXML
    private void showInfo() {
        String balance;
        int b = (int) SlBalance.getValue();
        if (b > 0) {
            //The commented out parts cause it to appear as: Left3 or Right2 for example
            balance = "Right"; // + String.valueOf(b);
        } else if (b < 0) {
            balance = "Left"; // + String.valueOf(-b);
        } else {
            balance = "Center";
        }

        //audioStatus();
        mediaStatus();
        mPlayer.setVolume(SlVolume.getValue()/100);
        
        info.setText("Bass:" + (int) SlBass.getValue() + "\n"
                + "Midrange: " + (int) SlMidrange.getValue() + "\n"
                + "Treble: " + (int) SlTreble.getValue() + "\n"
                + "Balance: " + balance + "\n"
                + "Volume: " + (int) SlVolume.getValue()
        );

    }

    //Audio player with start and stop functionality
    //reference: https://stackoverflow.com/questions/5833553/how-to-stop-a-music-clip
    // audio player using LineListener
    private void audioStuff() {
        player = new audioPlayer();
        String audioFP = "./audio.wav";
        player.audioPath(audioFP);
    }

    @FXML
    private void audioStart() throws InterruptedException {
        player.setPlayCompleted(false);
        audioStatus();
        player.start();
    }

    @FXML
    private void audioStop() {
        player.setPlayCompleted(true);
        audioStatus();
        player.stop();
    }

    @FXML
    private void audioReset() {
        audioStop();
        audioStuff();
        setupPresets();
        loadPresetDefault();
        showInfo();
    }

    private void audioStatus() {
        if (player.getPlayCompleted()) {
            playerInfo = "Stopped";
        } else {
            playerInfo = "Playing";
        }
        playingInfo.setText(playerInfo);
    }

    //audio player using MediaPlayer
    private void mediaStuff() {
        mPlayer = null;
        mPlayer = new myMediaPlayer();
        mPlayer.loadMedia("/home/angle/Downloads/shortmix.wav");
    }
    
    private void mediaStuff(File mediaFile) {
        mPlayer = null;
        mPlayer = new myMediaPlayer();
        String mediaFS = mediaFile.toURI().toString();
        String mediaFileString = mediaFS.substring(5);
        System.out.println("mediaFileString: " + mediaFileString);
        mPlayer.loadMedia(mediaFileString);
    }

    @FXML
    private void mediaStart() {
        mPlayer.setPlayCompleted(false);
        mediaStatus();
        mPlayer.startMedia();
    }

    @FXML
    private void mediaPause() {
        mPlayer.setPlayCompleted(true);
        mPlayer.pauseMedia();
        mediaStatus();
    }

    @FXML
    private void mediaStop() {
        mPlayer.setPlayCompleted(true);
        mPlayer.stopMedia();
        mediaStatus();
    }

    @FXML
    private void mediaReset() {
        mediaStop();
        mediaStuff();
        setupPresets();
        loadPresetDefault();
        showInfo();
    }

    private void mediaStatus() {
        if (mPlayer.getPlayCompleted()) {
            mPlayerInfo = "Stopped";
        } else {
            mPlayerInfo = "Playing";
        }

        playingInfo.setText(mPlayerInfo);
    }
    
    @FXML
    private void openAudioFile() {
        Stage myStage = (Stage)info.getScene().getWindow();
        mFC.openMyFileChooser(myStage);
        System.out.println("selectedFile: " + mFC.getSelectedFile());
        mediaStuff(mFC.getSelectedFile());
    }

}
