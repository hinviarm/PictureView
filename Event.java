
package pictureview;


import effect.moreBrighter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;


public class Event implements ActionListener, Runnable {

    public enum type {

        delete, add, next, prevouis, slideShow, move
    }
    PanelMainPicture panImg;
    PanPictureEffect panEff;
    moreBrighter m = new moreBrighter(null);
    listImage lstImg;
    JFileChooser choisir = new JFileChooser(".");
    boolean start = false;
    JButton diapo = null;
           Audio son = new Audio();

    Event(PanelMainPicture img, listImage lst) {

        panImg = img;
        lstImg = lst;

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        PanPictureEffect truc;
        truc = new PanPictureEffect(lstImg, panImg, this);
        System.out.println(e.getSource().getClass().getName());
        if (e.getSource().getClass().getName().equals("javax.swing.JButton")) {
            JButton b = (JButton) e.getSource();

            System.out.println(b.getName());
            if (b.getName().equals("delete")) {
                deleteImg();
            }

            if (b.getName().equals("add")) {
                addImg();
            }
            if (b.getName().equals("next")) {
                nextImg();
            }
            if (b.getName().equals("prevouis")) {
                prevouisImg();
            }
            if (b.getName().equals("slideShow")) {
                slideShowImg();
     

            }
         
            if (b.getName().equals("stop")) {
               start = false;
               diapo.setText("diaporama");    
            }
            if (b.getName().equals("backup")) {
                backup();
            }
            if (b.getName().equals("annulerEffect")) {
                AnnulerEffect();
            }
        }
        if (e.getSource().getClass().getName().equals("javax.swing.JMenuItem")) {
            JMenuItem b = (JMenuItem) e.getSource();
            if (b.getName().equals("LoadAlbum")) {
                LoadAlbum();
            }
            if (b.getName().equals("saveAlbum")) {
                SaveAlbum();
            }
            if (b.getName().equals("optionDiapo")) {
                OptionDiapo();
            }
            if (b.getName().equals("slideShow")) {
                slideShowImg();
            }
        }
    }

    private void OptionDiapo() {
    }

    synchronized private void stop() {
        start = false;
    }

    /**
     * Pour enlever tous les effects d'une image il suffit de remettre l'image
     * selectionner dans la liste
     */
    private void AnnulerEffect() {
        panImg.setImg(lstImg.getSelectImg(), lstImg.getSelectTitle());
        panImg.repaint();
    }

    /**
     * Sauvegarde des url des image de la liste
     */
    private void SaveAlbum() {
    }

    private void LoadAlbum() {
    }

    /**
     * Sauvegarde des effets
     */
    private void backup() {
         JFileChooser choix = new JFileChooser();
	 choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         choix.setName("Choisir un dossier d'enregistrement");
         if(choix.showOpenDialog(choix) == JFileChooser.APPROVE_OPTION){
             String chemin = choix.getSelectedFile().getPath();
             if(panImg.getImg()!=null){
             File outFile = new File(chemin);
                try {
                    ImageIO.write(panImg.getImg(), "PNG", new File(outFile+"\\imagerise.jpg"));
                } catch (IOException ex) {
                    //Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
         }
         
         choix.setMultiSelectionEnabled(false);
    }

    private void deleteImg() {

        lstImg.suppr();
        panImg.setImg(lstImg.getSelectImg(), lstImg.getSelectTitle());

        lstImg.repaint();
        panImg.repaint();
    }

    private void addImg() {
        choisir.setMultiSelectionEnabled(true);
        int ok = choisir.showOpenDialog(null);

        //Si le client a bien entrer choisi 1 ou plusiuers Fichier
        if (ok == JFileChooser.APPROVE_OPTION) {
            //On récupère l'url
            File url[] = choisir.getSelectedFiles();

            //Si on a pu avoir une url 
            if (url != null) {
                int size = url.length;
                for (int i = 0; i < size; i++) {
                    System.out.println(url[i].toString());

                    //On charge l'image en memoire dans la variable img
                    panImg.chargerIMG(url[i]);
                    lstImg.addImg(panImg.getImg(), url[i]);


                    //On met à jour le panel pour afficher l'imahe
                    panImg.repaint();
                    //this.repaint();
                    lstImg.repaint();
                }
            }
        }
    }

    private void addImg(File url) {

        //Si on a pu avoir une url 
        if (url != null) {
            //On charge l'image en memoire dans la variable img
            panImg.chargerIMG(url);
            lstImg.addImg(panImg.getImg(), url);
        }
    }

    private void nextImg() {
        lstImg.next();
        panImg.setImg(lstImg.getSelectImg(), lstImg.getSelectTitle());
        lstImg.repaint();
        panImg.repaint();
    }

    private void prevouisImg() {
        lstImg.previous();
        panImg.setImg(lstImg.getSelectImg(), lstImg.getSelectTitle());
        lstImg.repaint();
        panImg.repaint();
    }

    private void slideShowImg() {
        Thread t = new Thread(this);
       // Audio son = new Audio();
        if(!"stop".equals(diapo.getText())){
               t.start();
               start=true;
               son.Musique(start);
                 son.start();
        }
        else {
            start = false;
            diapo.setText("diaporama");
            son.Musique(start);
            
          }
        }
    

    public void setDiapo(JButton diapo) {
        this.diapo = diapo;
    }

    private synchronized boolean getStart() {
         System.out.println(" passe par getstart ");  
        return start;
    }

    @Override
    public void run() {

        PanelMainPicture panFull = new PanelMainPicture();

        panFull.setBackground(Color.BLACK);
        panFull.setFull(true);

        int nb = lstImg.getSizeArray();
        lstImg.toBegin();
        if (nb > 0) {

            start = true;
            if (diapo != null) {
                diapo.setText("stop");

            }
        }

            int i=0;
            while (( i < nb )&&(start!= false)) {

                panImg.setImg(lstImg.getSelectImg(), lstImg.getSelectTitle());
                panFull.setImg(lstImg.getSelectImg(), lstImg.getSelectTitle());
                lstImg.repaint();
                panImg.repaint();
                panFull.repaint();
                try {

                        Thread.sleep(1500);
                    
                } catch (InterruptedException ex) {
                    //  Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                lstImg.nextDiapo();
              
                i++;
            }
               start = false;
               diapo.setText("diaporama");
               son.Musique(start);
            }
        }
       
      

        /* this.b_next.setEnabled(true);
         this.b_prevouis.setEnabled(true);*/
    

