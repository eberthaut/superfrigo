package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurAlertePeremption;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

/** Vue de l'alerte */
public class VueAlertePeremption extends VueSousPanneau implements Rafraichissable {
    /** Le controleur */
    private ControleurAlertePeremption controleur;

    /** La liste des aliments */
    private JList alimentsList = new JList();
    /** Le bouton pour les enlever */
    private JButton enleverButton = new JButton("Enlever", Raccourcis.icone("suppression"));

    /** Le Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param nbJours Le nombre de jours regles de l'alerte
     */
    public VueAlertePeremption(Fenetre fenetre, int nbJours) {
        super(fenetre);
        controleur = new ControleurAlertePeremption(this, nbJours);

        enleverButton.addActionListener(controleur);

        JScrollPane scroll = new JScrollPane(alimentsList);
        this.add(scroll, BorderLayout.CENTER);

        this.add(enleverButton, BorderLayout.EAST);
    }

    @Override
    public void mettreAJour() {
        controleur.mettreAJour();
    }

    /** Retourne La liste d'aliments
     *
     * @return La liste d'aliments
     */
    public JList getAlimentsList() {
        return alimentsList;
    }

    /** Retourne Le bouton pour enlever les aliments
     *
     * @return Le bouton pour enlever les aliments
     */
    public JButton getEnleverButton() {
        return enleverButton;
    }


}
