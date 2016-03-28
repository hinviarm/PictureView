
package pictureview;

import effect.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 


public class PanPictureEffect extends JPanel implements ActionListener {

    ButtonGroup bg = new ButtonGroup(); //¨Pour avoir seulement 1 bouton radio de selectionné
    abstractEffect tab[] = new abstractEffect[10];
    JButton valider;
    Box box = Box.createVerticalBox();
    listImage listImg;
    PanelMainPicture picture;
    JButton sauvegarde;
    JButton annuler;
    public PanPictureEffect(listImage list, PanelMainPicture picture, Event e) {
        int index = 0;
        tab[index] = new BlackAndWhite_1("Binarisée");
        index++;
        tab[index] = new BlackAndWhite_2("Noir et blanc 2(avec du gris)");
        index++;
        tab[index] = new MoreDarker("Plus Lumineux");
        index++;
        tab[index] = new Inverse("Ajout d'un filtre bleu");
        index++;
        tab[0].setSelected(true);
        box.add(new JLabel("1 : choix de l'effet"), BorderLayout.NORTH);
        box.add(Box.createVerticalStrut(3));
        for (int i = 0; i < index; i++) {
            bg.add(tab[i]);
            box.add(tab[i]);

        }
        this.add(box, BorderLayout.NORTH);

      
        valider = new JButton("Valider ");
        valider.addActionListener(this);
        
        annuler = new JButton("annuler");
        annuler.setName("annulerEffect");
        annuler.addActionListener(e);
      
        sauvegarde = new JButton("Sauvegarde de l'image");
        sauvegarde.setName("backup");
        sauvegarde.addActionListener(e);

        box.add(Box.createVerticalStrut(100));
        box.add(new JLabel("2 : valider l'effet"));
        box.add(Box.createVerticalStrut(3));
        box.add(valider, BorderLayout.WEST);
        box.add(annuler, BorderLayout.WEST);
        box.add(Box.createVerticalStrut(10));
        box.add(new JLabel("3 : sauvegarde de l'image avec l'effet"));
        box.add(Box.createVerticalStrut(3));
        box.add(sauvegarde, BorderLayout.SOUTH);

        listImg = list;
        this.picture = picture;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("je passe");
        if (listImg.getSizeArray() > 0) {
            int i = 0;
            int size = tab.length;
            while ((i < size) && (!tab[i].isSelected())) {
                i++;
            }
            if (i < size) {
                picture.setImg(tab[i].actionEffect(picture.getImg()), listImg.getSelectTitle());
                listImg.repaint();
                picture.repaint();
            }
        }
    }
}
