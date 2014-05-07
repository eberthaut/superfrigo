package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.SortieAlimentControleur;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class SortieAliment extends SousPanneau {
    SortieAlimentControleur controleur;

    private JList alimentsList = new JList();
    private JScrollPane scroll = new JScrollPane(alimentsList);
    private JButton enleverButton = new JButton();
    private JPanel options = new JPanel();
    private JLabel spinnerLabel = new JLabel();
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // c'est le compteur de qte

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
        alimentsList.addListSelectionListener(controleur);
        this.add(scroll, BorderLayout.CENTER);

        enleverButton.setText("[Enlever]-");
        enleverButton.addActionListener(controleur);
        this.add(enleverButton, BorderLayout.EAST);

        spinnerLabel.setText("Quantite a enlever : ");
        options.add(spinnerLabel, null);
        options.add(quantiteSpinner, null);
        this.add(options, BorderLayout.SOUTH);

        controleur.rafraichirListe();
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
