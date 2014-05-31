package fr.insalyon.smartfridge.vues;


import fr.insalyon.smartfridge.controleurs.ControleurImportRecette;
import fr.insalyon.smartfridge.utilitaires.Fenetre;

import javax.swing.*;
import java.awt.*;

public class VueImportRecette extends VueSousPanneau {
    private ControleurImportRecette controleur;

    private JList propositionsList = new JList();
    private JTextField rechercheField = new JTextField();
    private JButton rechercheButton = new JButton("Recherche", new ImageIcon(getClass().getResource("/icones/recherche.png")));
    private JButton importButton = new JButton("Importer", new ImageIcon(getClass().getResource("/icones/import.png")));

    public VueImportRecette(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurImportRecette(fenetre, this);

        rechercheButton.addActionListener(controleur);
        importButton.addActionListener(controleur);

        JScrollPane scroll = new JScrollPane(propositionsList);
        this.add(scroll, BorderLayout.CENTER);

        this.add(importButton, BorderLayout.EAST);

        JPanel recherchePanel = new JPanel(new GridLayout(1, 2));
        recherchePanel.add(rechercheField);
        recherchePanel.add(rechercheButton);
        this.add(recherchePanel, BorderLayout.SOUTH);

    }

    public JList getPropositionsList() {
        return propositionsList;
    }

    public JTextField getRechercheField() {
        return rechercheField;
    }

    public JButton getRechercheButton() {
        return rechercheButton;
    }

    public JButton getImportButton() {
        return importButton;
    }
}
