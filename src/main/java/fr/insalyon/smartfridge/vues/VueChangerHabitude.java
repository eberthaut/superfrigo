package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurChangerHabitude;
import fr.insalyon.smartfridge.utilitaires.Changeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

public class VueChangerHabitude extends VueSousPanneau implements Changeable {
    private ControleurChangerHabitude controleur;

    private JList articlesList = new JList();
    private JButton changerHabitudeButton = new JButton("Appliquer", Raccourcis.icone("ok"));
    private JLabel habitudeEtat = new JLabel("Veuillez sélectionner un article");
    private JSpinner habitudeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));


    public VueChangerHabitude(Fenetre fenetre){
        super(fenetre);
        controleur = new ControleurChangerHabitude(this);

        changerHabitudeButton.addActionListener(controleur);
        articlesList.addListSelectionListener(controleur);

        JScrollPane scroll = new JScrollPane(articlesList);
        this.add(scroll, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 3));
        options.add(habitudeEtat);
        options.add(getHabitudeSpinner());
        options.add(changerHabitudeButton);
        this.add(options, BorderLayout.SOUTH);
    }

    @Override
    public void mettreAJour() {
        controleur.creerListe();
    }

    public JList getArticlesList() {return articlesList;}
    public JButton getChangerHabitudeButton() {return changerHabitudeButton;}
    public JLabel getHabitudeEtat() {return habitudeEtat;}
    public JSpinner getHabitudeSpinner() {
        return habitudeSpinner;
    }
}

