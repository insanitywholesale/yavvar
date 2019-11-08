/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapptest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author angle
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label info;
    
    @FXML
    private Slider SlBass, SlMidrange, SlTreble, SlBalance, SlVolume;
    
    @FXML
    private RadioButton presetDefault, preset1, preset2;
    
    @FXML
    private ToggleGroup presetGroup;
    
    /*@FXML
    private int SlBass;
    private int SlMidrange;
    private int SlTreble;
    private int SlBalance;
    private int SlVolume;
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupPresets();
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
        preset.bass = (int)SlBass.getValue();
        preset.midrange = (int)SlMidrange.getValue();
        preset.tremble = (int)SlTreble.getValue();
        preset.balance = (int)SlBalance.getValue();
        preset.volume = (int)SlVolume.getValue();
    }
    
    @FXML
    private void savePresetAction(ActionEvent event) {
        if(presetDefault.isSelected()) {
            storePreset(Presets[0]);
        }
        else if (preset1.isSelected()) {
            storePreset(Presets[1]);
        }
        else {
            storePreset(Presets[2]);
        }
    }
    
    @FXML
    private void showInfo(){
        String balance;
        int b = (int)SlBalance.getValue();
        if (b > 0) {
            balance = "Right" + String.valueOf(b);
        }
        else if (b < 0) {
            balance = "Left" + String.valueOf(-b);
        }
        else {
            balance = "Center";
        }
        
        info.setText("Bass:" + (int)SlBass.getValue() + "\n" +
                    "Midrange: " + (int)SlMidrange.getValue() + "\n" +
                    "Treble: " + (int)SlTreble.getValue() + "\n" +
                    "Balance: " + (int)SlBalance.getValue() + "\n" +
                    "Volume: " + (int)SlVolume.getValue()
        );
        
    }
}
