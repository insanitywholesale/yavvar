/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inhereplayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * @author angle
 */
public class FXMLDocumentController implements Initializable {

    //FXML variables
    @FXML
    private Label info, playingInfo, bassLabel, midrangeLabel, trebleLabel, balanceLabel, volumeLabel;

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
	
	@FXML
    private CheckMenuItem darkmodeMenuItem;
	
	@FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane anchorPane;
	
	@FXML
    private VBox vbox;

	
    //non-FXML variables
    private MediaPlayer mPlayer;
    private boolean playerStopped = true;
    private String mPlayerInfo = "Stopped";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupPresets();
        balanceLabel();
        loadPresetDefault();
        showInfo();
        playingInfo.setText("No audio loaded");
    }

    Preset Presets[] = new Preset[3];

    private void setupPresets() {
        Presets[0] = new Preset(0, 0, 0, 0, 9);
        Presets[1] = new Preset(-1, -1, 9, 0, 4);
        Presets[2] = new Preset(2, 4, -2, 4, 2);
    }

    @FXML
    private void loadPresetDefault() {
        SlBass.setValue(Presets[0].bass);
        SlMidrange.setValue(Presets[0].midrange);
        SlTreble.setValue(Presets[0].tremble);
        SlBalance.setValue(Presets[0].balance);
        SlVolume.setValue(Presets[0].volume);
        showInfo();
    }

    @FXML
    private void loadPreset1() {
        SlBass.setValue(Presets[1].bass);
        SlMidrange.setValue(Presets[1].midrange);
        SlTreble.setValue(Presets[1].tremble);
        SlBalance.setValue(Presets[1].balance);
        SlVolume.setValue(Presets[1].volume);
        showInfo();
    }

    @FXML
    private void loadPreset2() {
        SlBass.setValue(Presets[2].bass);
        SlMidrange.setValue(Presets[2].midrange);
        SlTreble.setValue(Presets[2].tremble);
        SlBalance.setValue(Presets[2].balance);
        SlVolume.setValue(Presets[2].volume);
        showInfo();
    }

    @FXML
    private void storePreset(Preset preset) {
        preset.bass = (int) SlBass.getValue();
        preset.midrange = (int) SlMidrange.getValue();
        preset.tremble = (int) SlTreble.getValue();
        preset.balance = (int) SlBalance.getValue();
        preset.volume = (int) SlVolume.getValue();
    }

    @FXML
    private void savePreset() {
        if (presetDefault.isSelected()) {
            storePreset(Presets[0]);
        }
        else if (preset1.isSelected()) {
            storePreset(Presets[1]);
        }
        else if (preset2.isSelected()) {
            storePreset(Presets[2]);
        }
        else {
            System.out.println("Error: No preset button is selected");
        }
    }

    @FXML
    private void balanceLabel() {
        SlBalance.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n > 0) {
                    return "R";
                }
                if (n < 0) {
                    return "L";
                }
                if (n == 0) {
                    return "Center";
                }
                return "Center";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "L":
                        return -5.0;
                    case "R":
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
            balance = "Right";
        } else if (b < 0) {
            balance = "Left";
        } else {
            balance = "Center";
        }

        if (mPlayer != null) {
            mediaStatus();
            mPlayer.setVolume(SlVolume.getValue() / 100);
        }

        info.setText("Bass:" + (int) SlBass.getValue() + "\n"
            + "Midrange: " + (int) SlMidrange.getValue() + "\n"
            + "Treble: " + (int) SlTreble.getValue() + "\n"
            + "Balance: " + balance + "\n"
            + "Volume: " + (int) SlVolume.getValue()
        );

    }
    
    @FXML
    private void mediaStart() {
		if (mPlayer != null) {
			playerStopped = false;
			mPlayer.play();
			mediaStatus();
		}
    }

    @FXML
    private void mediaPause() {
		if (mPlayer != null) {
			playerStopped = true;
			mPlayer.pause();
			mediaStatus();
		}
    }

    @FXML
    private void mediaStop() {
		if (mPlayer != null) {
			playerStopped = true;
			mPlayer.stop();
			mediaStatus();
		}
    }

    @FXML
    private void mediaReset() {
        mPlayer = null;
        setupPresets();
        showInfo();
		playingInfo.setText("No audio loaded");
    }

    private void mediaStatus() {
        if (playerStopped) {
            mPlayerInfo = "Stopped";
        } else {
            mPlayerInfo = "Playing";
        }
        playingInfo.setText(mPlayerInfo);
    }

    @FXML
    private void openAudioFile() {
        if (mPlayer != null) {
            mediaPause();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio Files", "*.ogg", "*.wav", "*.mp3", "*.aac")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
		System.out.println("selectedFile: " + selectedFile);
        Media media = new Media(selectedFile.toURI().toString());
		System.out.println("mediaString: " + selectedFile.toURI().toString());
        mPlayer = new MediaPlayer(media);
        playingInfo.setText("Stopped");
    }
	
	@FXML
	private void setInitialTheme() {
		vbox.getScene().getStylesheets().add("/css/lightmodeStyle.css");
	}

	@FXML
	private void changeTheme() {
		Scene scene = vbox.getScene();
		
		if (darkmodeMenuItem.isSelected()) {
			scene.getStylesheets().removeAll("/css/lightmodeStyle.css");
			scene.getStylesheets().add("/css/darkmodeStyle.css");
		}
		else {
			scene.getStylesheets().removeAll("/css/darkmodeStyle.css");
			scene.getStylesheets().add("/css/lightmodeStyle.css");
		}
	}
}
