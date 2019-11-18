/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxaskhshvraveio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author angle
 */
public class myFileChooser {

    private Desktop desktop = Desktop.getDesktop();
    private FileChooser fileChooser;
    private File file;

    public myFileChooser() {
    }

    public void openMyFileChooser(Stage stage) {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio Files", "*.ogg", "*.wav", "*.mp3", "*.aac"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        file = fileChooser.showOpenDialog(stage);
        System.out.println("filechooser file: " + file.toURI().toString());
    }

    public File getSelectedFile() {
        return file;
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                myFileChooser.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }

}
