package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.AlertePeremptionControleur;

import javax.swing.*;
import java.awt.*;

public class AlertePeremption extends SousPanneau implements Changeable {
    private AlertePeremptionControleur controleur;

    private JList alimentsList = new JList();
    private JButton enleverButton = new JButton("Enlever", UtilitairesVues.icone("suppression"));

    public AlertePeremption(Fenetre fenetre, int nbJours) {
        super(fenetre);
        controleur = new AlertePeremptionControleur(fenetre, this, nbJours);

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
