package fr.insalyon.smartfridge.vues;


import fr.insalyon.smartfridge.controleurs.ControleurImportRecette;
import fr.insalyon.smartfridge.utilitaires.Fenetre;

import javax.swing.*;
import java.awt.*;

/** Vue ppour importer une recette */
public class VueImportRecette extends VueSousPanneau {
    /** La liste des resultats de recherche */
    private final JList propositionsList = new JList();
    /** Le champ de recherche */
    private final JTextField rechercheField = new JTextField();
    /** Le bouton de recherche */
    private final JButton rechercheButton = new JButton("Recherche", new ImageIcon(getClass().getResource("/icones/recherche.png")));
    /** Le bouton d'import */
    private final JButton importButton = new JButton("Importer", new ImageIcon(getClass().getResource("/icones/import.png")));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueImportRecette(Fenetre fenetre) {
        super(fenetre);
        ControleurImportRecette controleur = new ControleurImportRecette(fenetre, this);

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

    /** Retourne La liste des resultats de recherche
     *
     * @return La liste des resultats de recherche
     */
    public JList getPropositionsList() {
        return propositionsList;
    }

    /** Retourne Le champ de recherche
     *
     * @return Le champ de recherche
     */
    public JTextField getRechercheField() {
        return rechercheField;
    }

    /** Retourne Le bouton de recherche
     *
     * @return Le bouton de recherche
     */
    public JButton getRechercheButton() {
        return rechercheButton;
    }

    /** Retourne Le bouton d'import
     *
     * @return Le bouton d'import
     */
    public JButton getImportButton() {
        return importButton;
    }
}
