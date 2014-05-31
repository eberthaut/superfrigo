package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ChangerHabitudeControleur;

import javax.swing.*;
import java.awt.*;

public class ChangerHabitude extends SousPanneau implements Changeable {
    private ChangerHabitudeControleur controleur;

    private JList articlesList = new JList();
    private JButton changerHabitudeButton = new JButton("Appliquer", UtilitairesVues.icone("ok"));
    private JLabel habitudeEtat = new JLabel("Veuillez sélectionner un article");
    private JSpinner habitudeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));


    public ChangerHabitude(Fenetre fenetre){
        super(fenetre);
        controleur = new ChangerHabitudeControleur(this);

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

