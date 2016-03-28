package pictureview;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

/**
 * Fenetre principale
 * 
 */
public class MainFrame extends JFrame {

    listImage listImg;
    PanelMainPicture imgPan;
    JButton next;//1
    JButton add;//2
    JButton prevouis;//3
    JButton diaporama;//4
    JButton suppr;//5
    JButton activerPoss;//6
    Box box_bouton;
    PanPictureEffect effect;
    Box box = Box.createVerticalBox();
    Event e;
    int ecart_verticale = 50;//px
   

    public MainFrame() {

        //Init panel avec l'image sélectionner et les image reduite
        imgPan = new PanelMainPicture();
        listImg = new listImage(imgPan);
        
        //init Event et bouton
        e = new Event(imgPan, listImg);
        initBouton(e);

        //Ajout du menu
        AddMenu();

        //Panel avec les effect
        effect = new PanPictureEffect(listImg, imgPan, e);

        //Init la Fenetre Principal
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double h = tailleEcran.getHeight() * 0.75;
        double w = tailleEcran.getWidth() * 0.75;

        //On modifie la taille de la fenetre, titre,et on parametre la fermeture du programme
        setSize((int) w, (int) h);

        //Permet de savoir si il y a bien eu compilation et connaitre l'avance du projet
        setTitle("Hinvi et Abdellahi");
       
        //Pour quitter en fermant la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Pour changer la disposition des panels
        setGridBagLayout();
        this.add(box);

        //On affiche la fenetre
        setVisible(true);
    }

    private void setGridBagLayout() {
        /**
         * Creation du Gestion De conteneur
         */
        GridBagLayout Disposition = new GridBagLayout();

        //Changement de contenair de la fenetre
        this.getContentPane().setLayout(Disposition);


        //creation des contrainte
        GridBagConstraints c = new GridBagConstraints();

        //ajustement de l'espace en largeur et hauteur
        c.fill = GridBagConstraints.BOTH;

        /**
         * *******************************************************
         * MISE en place de l'interface *************************
         * *******************************************************
         */
        // Image principal
        JTabbedPane onglet = new JTabbedPane();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 8;
        c.gridwidth = 8;
        c.weightx = 160;
        c.weighty = 160;

        onglet.add("image", imgPan);
        imgPan.setOnglet(onglet, 0);
        this.getContentPane().add(onglet, c);

        //Effect
        onglet = new JTabbedPane();
        c.gridx = 8;
        c.gridy = 0;
        c.gridheight = 8;
        c.gridwidth = 2;
        c.weightx = 10;
        c.weighty = 10;
        onglet.add("Effet", effect);
        this.getContentPane().add(onglet, c);

        // liste des images
        c.gridx = 0;
        c.gridy = 8;
        c.gridheight = 2;
        c.gridwidth = 11;
        c.weightx = 150; //poid pour la largeur
        c.weighty = 150;

        this.getContentPane().add(addLstImg(), c);

        //Liste des bouton
        addButton();

        c.gridx = 0;
        c.gridy = 10;
        c.gridheight = 1;
        c.gridwidth = 11;
        c.weightx = 20; //poid pour la largeur
        c.weighty = 20;

        this.getContentPane().add(box_bouton, c);


    }

    private Box addLstImg() {
        Box tmp = Box.createVerticalBox();
        tmp.add(Box.createVerticalStrut(5));

        Box tmp_hori = Box.createHorizontalBox();

        tmp_hori.add(Box.createHorizontalStrut(10));
        tmp_hori.add(listImg);

        tmp.add(tmp_hori);

        return tmp;
    }
    /**
     * Initialisation des bouton et ajout des gestions evenements
     * @param ev pour savoir sur quel bouton on clique
     */
    private void initBouton(Event ev) {
        CreateButton();
        setTitleButton();
        add.addActionListener(ev);
        add.setName(Event.type.add.toString());

        diaporama.addActionListener(ev);
        diaporama.setName(Event.type.slideShow.toString());
        e.setDiapo(diaporama);

        next.addActionListener(ev);
        next.setName(Event.type.next.toString());

        prevouis.addActionListener(ev);
        prevouis.setName(Event.type.prevouis.toString());

        suppr.addActionListener(ev);
        suppr.setName(Event.type.delete.toString());

    }
    /**
     * Creation de bouton 
     */

    private void CreateButton() {
        //Cration des boutons
        next = new JButton();
        prevouis = new JButton();
        diaporama = new JButton();
        suppr = new JButton();
        add = new JButton();
    
    }
    /**
     * Changement des titres des bouton
     */
    private void setTitleButton() {
        //On change les titres des boutons
        next.setText("suivant");
        prevouis.setText("précédent");
        diaporama.setText("diaporama");
        suppr.setText("supprimer");
        add.setText("ajouter photo");
    
    }
    /**
     * ajout du bouton dans le Panel des boutons
     */
    private void addButton() {
        int espace = 30;
        box_bouton = Box.createHorizontalBox();
        box_bouton.add(Box.createHorizontalStrut(350));
        box_bouton.add(next);
        box_bouton.add(Box.createHorizontalStrut(espace));
        box_bouton.add(prevouis);
        box_bouton.add(Box.createHorizontalStrut(espace));
        box_bouton.add(diaporama);
        box_bouton.add(Box.createHorizontalStrut(espace));
        box_bouton.add(suppr);
        box_bouton.add(Box.createHorizontalStrut(espace));
        box_bouton.add(add);
      

    }
    /**
     * ajout du menu pour l'ecran principal
     */
    private void AddMenu() {
        //creation du menu
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
    }
}
