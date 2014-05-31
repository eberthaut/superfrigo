package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurSortieAliment;
import fr.insalyon.smartfridge.utilitaires.Changeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class VueSortieAliment extends VueSousPanneau implements Changeable {
    private ControleurSortieAliment controleur;

    private JList alimentsList = new JList();
    private JButton enleverButton = new JButton("Enlever", Raccourcis.icone("suppression"));
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // c'est le compteur de qte

    public VueSortieAliment(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurSortieAliment(fenetre, this);

        enleverButton.addActionListener(controleur);

        alimentsList.addListSelectionListener(controleur);
        JScrollPane scroll = new JScrollPane(alimentsList);
        this.add(scroll, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 3));
        JLabel spinnerLabel = new JLabel("Quantite a enlever : ");
        spinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(spinnerLabel);
        options.add(quantiteSpinner);
        options.add(enleverButton);
        this.add(options, BorderLayout.SOUTH);
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

    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }


}
