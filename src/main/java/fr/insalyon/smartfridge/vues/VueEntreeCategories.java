package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurEntreeCategories;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import java.awt.*;

import javax.swing.*;

/** La vue pour choisir un Type pour ajouter un Article */
public class VueEntreeCategories extends VueSousPanneau implements Rafraichissable {
    /** Le controleur */
    private ControleurEntreeCategories controleur;

    /** La liste de types */
    private JList typesList = new JList();
    /** Le bouton de choix */
    private JButton choixButton = new JButton("Choisir", Raccourcis.icone("suivant"));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueEntreeCategories(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurEntreeCategories(fenetre, this);

        choixButton.addActionListener(controleur);

        JScrollPane scroll = new JScrollPane(typesList);
        this.add(scroll, BorderLayout.CENTER);

        this.add(choixButton, BorderLayout.EAST);
    }

    @Override
    public void mettreAJour() {
        controleur.mettreAJour();
    }

    /** Retourne La liste des types
     *
     * @return La liste des types
     */
    public JList getTypesList() {
        return typesList;
    }

    /** Retourne Le bouton de choix
     *
     * @return Le bouton de choix
     */
    public JButton getChoixButton() {
        return choixButton;
    }
}
