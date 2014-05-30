package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.AlertePeremptionControleur;

import javax.swing.*;
import java.awt.*;

public class AlertePeremption extends SousPanneau {
    AlertePeremptionControleur controleur;

    private JList alimentsList = new JList();
    private JScrollPane scroll = new JScrollPane(alimentsList);
    private JButton enleverButton = new JButton();

    public AlertePeremption(Fenetre fenetre, int nbJours) {
        super(fenetre);
        controleur = new AlertePeremptionControleur(fenetre, this, nbJours);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.add(scroll, BorderLayout.CENTER);

        enleverButton.setText("Enlever");
        enleverButton.setIcon(new ImageIcon(getClass().getResource("/icones/suppression.png")));
        enleverButton.addActionListener(controleur);
        this.add(enleverButton, BorderLayout.EAST);

        controleur.rafraichirListe();
    }

    public JList getAlimentsList() {
        return alimentsList;
    }

    public JButton getEnleverButton() {
        return enleverButton;
    }
}
