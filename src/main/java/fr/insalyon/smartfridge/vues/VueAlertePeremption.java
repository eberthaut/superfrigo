package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurAlertePeremption;
import fr.insalyon.smartfridge.utilitaires.Changeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

public class VueAlertePeremption extends VueSousPanneau implements Changeable {
    private ControleurAlertePeremption controleur;

    private JList alimentsList = new JList();
    private JButton enleverButton = new JButton("Enlever", Raccourcis.icone("suppression"));

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
        controleur.rafraichirListe();
    }

    public JList getAlimentsList() {
        return alimentsList;
    }

    public JButton getEnleverButton() {
        return enleverButton;
    }


}
