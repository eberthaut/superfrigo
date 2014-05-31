package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurEditerListeCourses;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

/** Vue d'edition de liste de courses */
public class VueEditerListeCourses extends VueSousPanneau implements Rafraichissable {
    /** Le controleur */
    private final ControleurEditerListeCourses controleur;

    /** La liste de courses */
    private final JList listeDeCourses = new JList();
    /** Bouton de changement d'habtude */
    private final JButton changerHabitudesButton = new JButton("Changer mes habitudes", Raccourcis.icone("utilisateur"));
    /** Bouton d'impression */
    private final JButton impressionButton = new JButton("Imprimer", Raccourcis.icone("imprimer"));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueEditerListeCourses(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurEditerListeCourses(fenetre, this);

        changerHabitudesButton.addActionListener(controleur);
        impressionButton.addActionListener(controleur);

        JScrollPane scroll = new JScrollPane(listeDeCourses);
        this.add(scroll, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 2));
        options.add(changerHabitudesButton);
        options.add(impressionButton);
        this.add(options, BorderLayout.SOUTH);
    }

    @Override
    public void mettreAJour() {
        controleur.mettreAJour();
    }

    /** Retourne La liste de courses
     *
     * @return La liste de courses
     */
    public JButton getChangerHabitudesButton() { return changerHabitudesButton; }

    /** Retourne Bouton de changement d'habtude
     *
     * @return Bouton de changement d'habtude
     */
    public JButton getImpressionButton() { return impressionButton; }

    /** Retourne Bouton d'impression
     *
     * @return Bouton d'impression
     */
    public JList getListeDeCourses() { return listeDeCourses; }
}





