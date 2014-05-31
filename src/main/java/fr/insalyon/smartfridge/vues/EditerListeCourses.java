package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EditerListeCoursesControleur;

import javax.swing.*;
import java.awt.*;


public class EditerListeCourses extends SousPanneau implements Changeable {
    private EditerListeCoursesControleur controleur;

    private JList listeDeCourses = new JList();
    private JScrollPane scroll = new JScrollPane(listeDeCourses);
    private JButton changerHabitudesButton = new JButton("Changer mes habitudes", UtilitairesVues.icone("utilisateur"));
    private JButton impressionButton = new JButton("Imprimer", UtilitairesVues.icone("imprimer"));

    public EditerListeCourses(Fenetre fenetre) {
        super(fenetre);
        controleur = new EditerListeCoursesControleur(fenetre, this);

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





