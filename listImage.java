
package pictureview;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * Classe qui gère et affiche la liste des images
 */
public class listImage extends JPanel implements MouseListener {

    ArrayList<BufferedImage> lstMiniature;
    ArrayList<File> lstNom;
    int index = 0;
    int index_lect = -1;
    int nbMiniature = 5;
    int ecart = 25;
    int height = 75;
    int witdh = 75;
    PanelMainPicture mainPicture;
    int debut = 0;

    /**
     *
     * @param mainPicture pour changer l'image afficher sur le panel Principal
     */
    public listImage(PanelMainPicture mainPicture) {
        lstMiniature = new ArrayList<BufferedImage>();
        lstNom = new ArrayList<File>();
        addMouseListener(this);
        this.mainPicture = mainPicture;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);


        debut = 0;
        nbMiniature = (this.getWidth() / (witdh + ecart));

        if (index >= nbMiniature) {
            debut = (index - nbMiniature) + 1;

        }
        if (index >= (nbMiniature - 1)) {
            debut++;
        }
        int nbImg = lstMiniature.size();
        System.out.println(nbMiniature + ";" + debut + ";" + index);
        for (int i = debut; i < (nbMiniature + debut); i++) {
            if (i == index) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }

            g.drawRect((i - debut) * (witdh + ecart), 0, witdh + 1, height + 1);
            if (i < lstMiniature.size()) {
                g.drawImage(lstMiniature.get(i), 1 + (i - debut) * (witdh + ecart), 1, witdh - 1, height, null);
                g.drawString((i + 1) + "/" + nbImg, (witdh / 2) + (i - debut) * (witdh + ecart), height + 20);
            }
        }
    }

    /**
     * pour enlever une image de la liste
     */
    public void suppr() {
        int size = lstMiniature.size();
        if (size > 0) {
            lstMiniature.remove(index);
            lstNom.remove(index);
            if ((size - 1) == index) {
                index--;
            }
            if (index < 0) {
                index = 0;
            }
        }
    }
    /**
     * 
     * @param img image à rajouter
     * @param title pour avoir le titre pour l'image principal
     */
    public void addImg(BufferedImage img, File title) {
        lstMiniature.add(img);
        lstNom.add(title);
        index = lstMiniature.size() - 1;
    }

    public String getSelectTitle() {
        if (lstMiniature.size() < 1) {
            return "";
        }
        return lstNom.get(index).getName();
    }
    /**
     * Fonction pour récuper l'url d'un fichier, utilisé pour la création d'album
     * @param indexTitle index dans la liste d'image
     * @return l'url du l'image en fonction de l'index
     */
    public File getUrl(int indexTitle) {
        return lstNom.get(indexTitle);
    }

    public BufferedImage getSelectImg() {
        if (lstMiniature.size() < 1) {
            return null;
        }
        return lstMiniature.get(index);
    }

    public void previous() {
        if (index > 0) {
            index--;
        }
        else{
            index = lstMiniature.size() - 1 ;
        }
    }

    public void next() {
        if (index < lstMiniature.size() - 1) {
            index++;
        }
        else{
            index = 0;
        }
    }
    public void nextDiapo() {
        if (index < lstMiniature.size() - 1) {
            index++;
        }
    }

    public int getSizeArray() {
        return lstMiniature.size();
    }

    public void toBegin() {
        index = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        for (int i = 0; i < nbMiniature; i++) {

            Rectangle r = new Rectangle(i * (witdh + ecart) - (ecart / 2), 0, witdh + ecart, height + 20);
            if (r.contains(x, y)) {
                if ((i + debut) < getSizeArray()) {
                    index = (i + debut);
                    this.repaint();
                    mainPicture.setImg(getSelectImg(), getSelectTitle());
                    mainPicture.repaint();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
