package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ChangerHabitudeControleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class ChangerHabitude extends SousPanneau {
    ChangerHabitudeControleur controleur;

    private JList articlesList = new JList();
    private JScrollPane scroll = new JScrollPane(articlesList);
    private JPanel options = new JPanel();
    private JButton changerHabitudeButton = new JButton();
    private JLabel habitude = new JLabel();
    private JSpinner habitudeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));


    public ChangerHabitude(Fenetre fenetre){
        super(fenetre);
        controleur = new ChangerHabitudeControleur(this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception{
        options.setLayout(new GridLayout(1, 3));

        this.add(scroll, BorderLayout.CENTER);

        habitude.setText("Changer l'habitude : ");
        habitude.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(habitude);
        options.add(getHabitudeSpinner());
        changerHabitudeButton.setText("Appliquer");
        changerHabitudeButton.setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
        options.add(changerHabitudeButton);
        this.add(options, BorderLayout.SOUTH);

        changerHabitudeButton.addActionListener(controleur);
        articlesList.addListSelectionListener(controleur);
        controleur.creerListe();
    }

    public JList getArticlesList() {return articlesList;}
    public JButton getChangerHabitudeButton() {return changerHabitudeButton;}
    public JLabel getHabitude() {return habitude;}
    public JSpinner getHabitudeSpinner() {
        return habitudeSpinner;
    }
}

