package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurEditerListeCourses;
import fr.insalyon.smartfridge.utilitaires.Changeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;


public class VueEditerListeCourses extends VueSousPanneau implements Changeable {
    private ControleurEditerListeCourses controleur;

    private JList listeDeCourses = new JList();
    private JScrollPane scroll = new JScrollPane(listeDeCourses);
    private JButton changerHabitudesButton = new JButton("Changer mes habitudes", Raccourcis.icone("utilisateur"));
    private JButton impressionButton = new JButton("Imprimer", Raccourcis.icone("imprimer"));

    public VueEditerListeCourses(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurEditerListeCourses(fenetre, this);

        changerHabitudesButton.addActionListener(controleur);
        impressionButton.addActionListener(controleur);

        this.add(scroll, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 2));
        options.add(changerHabitudesButton);
        options.add(impressionButton);
        this.add(options, BorderLayout.SOUTH);
    }

    @Override
    public void mettreAJour() {
        controleur.creerListe();
    }

    public JButton getChangerHabitudesButton() { return changerHabitudesButton; }
    public JButton getImpressionButton() { return impressionButton; }
    public JList getListeDeCourses() { return listeDeCourses; }
}





