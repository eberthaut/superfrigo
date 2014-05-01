package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ChangerHabitudeControleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class ChangerHabitude extends JPanel {
    ChangerHabitudeControleur controleur;

    private JPanel panneauOuest = new JPanel();
    private JPanel panneauSud = new JPanel();
    private JPanel panneauEst = new JPanel();
    private JList articlesList = new JList();
    private JScrollPane scrollPane = new JScrollPane(articlesList);
    private JButton changerHabitudeButton = new JButton();
    private JButton retourButton = new JButton();
    private JLabel selection = new JLabel();
    private JLabel habitude = new JLabel();
    private JTextField habitudeTextField = new JTextField();
    private FlowLayout layout = new FlowLayout();


    public ChangerHabitude(Fenetre fenetre){
        controleur = new ChangerHabitudeControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception{
        panneauOuest.setLayout(layout);
        panneauSud.setLayout(layout);
        panneauEst.setLayout(new BorderLayout());
        this.add(panneauEst, BorderLayout.EAST);
        this.add(panneauOuest, BorderLayout.WEST);
        this.add(panneauSud, BorderLayout.SOUTH);
        this.setSize(new Dimension(1000, 700));
        scrollPane.setPreferredSize(new Dimension(250,450));
        habitudeTextField.setPreferredSize(new Dimension(60, 20));
        //retourButton.setPreferredSize(new Dimension(60,30));

        changerHabitudeButton.setText("Appliquer la nouvelle habitude à cet article");
        selection.setText("Veuillez sélectionner un article");
        retourButton.setText("retour");


        changerHabitudeButton.addActionListener(controleur);
        articlesList.addListSelectionListener(controleur);
        retourButton.addActionListener(controleur);

        panneauOuest.add(scrollPane);
        panneauEst.add(habitude,BorderLayout.NORTH);
        panneauEst.add(selection, BorderLayout.WEST);
        panneauEst.add(habitudeTextField, BorderLayout.CENTER);
        panneauEst.add(changerHabitudeButton, BorderLayout.EAST);
        panneauSud.add(retourButton, BorderLayout.SOUTH);

        controleur.creerListe();

    }

    public JList getArticlesList() {return articlesList;}
    public JButton getChangerHabitudeButton() {return changerHabitudeButton;}
    public JTextField getHabitudeTextField() {return habitudeTextField;}
    public JLabel getSelection() {return selection;}
    public JLabel getHabitude() {return habitude;}
    public JButton getRetourButton() {return retourButton;}

}

