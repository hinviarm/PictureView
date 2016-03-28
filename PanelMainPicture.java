
package pictureview;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class PanelMainPicture extends JPanel {

    /**
     * Creates new form ImagePan
     */
    BufferedImage img;
    JTabbedPane onglet;
    int index;
    boolean full = false;

    public PanelMainPicture() {
        this.setBackground(Color.DARK_GRAY);
        this.setBackground(Color.blue);
        img = null;
        onglet = null;
        index = -1;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(!full)g.drawRect(0, 0, this.getWidth(), this.getHeight());
        if (img != null) {
            //Dessin de l'image avec une taille de 200x200
            //Position à 50 sur l'axe des x et 20 sur les axe des y(hauteur)
            double coef = 1;
            if (img.getWidth() > this.getWidth()) {
                coef = ((double) this.getWidth()) / ((double) img.getWidth());

            }
            if (img.getHeight() > this.getHeight()) {
                double coef_2 = ((double) this.getHeight()) / ((double) img.getHeight());
                if (coef_2 < coef) {
                    coef = coef_2;
                }
            }
            int x = (this.getWidth() / 2) - (int) ((img.getWidth() * coef) / 2);
            int y = (this.getHeight() / 2) - (int) ((img.getHeight() * coef) / 2);
            double witdh = (img.getWidth() * coef);
            double height = (img.getHeight() * coef);
            System.out.println("(" + witdh + ";" + height + ")" + coef);
            g.drawImage(img, x, y, (int) witdh, (int) height, null);


        }
    }

    public void chargerIMG(File file) {
        try {
            img = ImageIO.read(file);
            setTitle(file.getName());
        } catch (IOException ex) {

            img = null;
        }

    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img, String title) {
        this.img = img;
        if (onglet != null) {
            onglet.setTitleAt(index, title);
        }

    }

    public void setTitle(String title) {
        if (onglet != null) {
            onglet.setTitleAt(index, title);
        }
    }

    public void setOnglet(JTabbedPane onglet, int index) {
        this.onglet = onglet;
        this.index = index;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
