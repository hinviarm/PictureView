/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pictureview;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author hp
 */
public class Audio extends Thread{
     AudioInputStream audioInputStream = null;
    SourceDataLine line;
    boolean n;
   public void Musique(boolean jouer){ 
   n=jouer;
   } 
    @Override
    public void run(){
        File fichier;
        fichier = new File("C:/Users/hp/Documents/PictureView_3L/PictureView_3/Music.wav");
        try {
        AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
        } catch (UnsupportedAudioFileException e1) {
        } catch (IOException e1) {
        }
         
        try {
            audioInputStream = AudioSystem.getAudioInputStream(fichier);
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        }
         
         AudioFormat audioFormat = audioInputStream.getFormat();
         DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
          
         try {
             line = (SourceDataLine) AudioSystem.getLine(info);
                        
             } catch (LineUnavailableException e) {
               return;
             }
          
        try {
                line.open(audioFormat);
        } catch (LineUnavailableException e) {
                    return;
        }
        line.start();
       // Fenetre.begin=true;
        try {
            byte bytes[] = new byte[1024];
            int bytesRead=0;
            while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1 && n==true) {
                 line.write(bytes, 0, bytesRead);
                }
        } catch (IOException io) {
        }
        
    }
}



