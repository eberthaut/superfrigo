package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.SortieAlimentControleur;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class SortieAliment extends SousPanneau {
    SortieAlimentControleur controleur;

    private JPanel principal = new JPanel();
    private JList alimentsList = new JList();
    private JButton enleverButton = new JButton();
    private JPanel options = new JPanel();
    private JLabel spinnerLabel = new JLabel();
    private JSpinner quantiteSpinner = new JSpinner(); // c'est le compteur de qte

    public SortieAliment(Fenetre fenetre) {
        super(fenetre);
        controleur = new SortieAlimentControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        principal.setLayout(new BorderLayout());

        alimentsList.addListSelectionListener(controleur);
        principal.add(alimentsList, BorderLayout.CENTER);

        enleverButton.setText("[Enlever]-");
        enleverButton.addActionListener(controleur);
        principal.add(enleverButton, BorderLayout.EAST);

        spinnerLabel.setText("Quantite a enlever : ");
        options.add(spinnerLabel, null);
        quantiteSpinner.setValue(1);
        options.add(quantiteSpinner, null);
        principal.add(options, BorderLayout.SOUTH);

        controleur.rafraichirListe();

        this.add(principal, BorderLayout.CENTER);
    }

    public JList getAlimentsList() {
        return alimentsList;
    }

    public JButton getEnleverButton() {
        return enleverButton;
    }

    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }
}
