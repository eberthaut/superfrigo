package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurMenuRecettes;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

/** Vue des recettes */
public class VueMenuRecettes extends VueSousPanneau implements Rafraichissable {
    /** Le controleur */
    private final ControleurMenuRecettes controleur;

    /** La liste de recettes */
    private final JList recettesList = new JList();
    /** La liste d'ingredients */
    private final JList ingredientsList = new JList();
    /** Le bouton de changement */
    private final JButton toggleButton = new JButton("Activer", Raccourcis.icone("ok"));
    /** Le texte d'etat */
    private final JLabel etatLabel = new JLabel("...");
    /** Le bouton d'ajout */
    private final JButton ajouterButton = new JButton("Ajouter", Raccourcis.icone("ajout"));
    /** Le bouton de suppression */
    private final JButton supprimerButton = new JButton("Supprimer", Raccourcis.icone("suppression"));
    /** Le bouton d'import */
    private final JButton importButton = new JButton("Importer depuis Marmiton.org", Raccourcis.icone("internet"));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueMenuRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurMenuRecettes(fenetre, this);

        recettesList.addListSelectionListener(controleur);
        toggleButton.addActionListener(controleur);
        ajouterButton.addActionListener(controleur);
        supprimerButton.addActionListener(controleur);
        importButton.addActionListener(controleur);

        JPanel panneauCentre = new JPanel(new GridLayout(1, 2));
        JScrollPane recettesScroll = new JScrollPane(recettesList);
        panneauCentre.add(recettesScroll);
        JPanel panneauInfo = new JPanel(new GridLayout(2, 1));
        JScrollPane ingredientsScroll = new JScrollPane(ingredientsList);
        panneauInfo.add(ingredientsScroll);
        etatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauInfo.add(etatLabel);
        panneauCentre.add(panneauInfo);
        this.add(panneauCentre, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 4));
        options.add(toggleButton);
        options.add(ajouterButton);
        options.add(supprimerButton);
        options.add(importButton);
        this.add(options, BorderLayout.SOUTH);
    }

    @Override
    public void mettreAJour() {
        controleur.mettreAJour();
    }

    /** Retourne Le bouton de changement
     *
     * @return Le bouton de changement
     */
    public JButton getToggleButton() {
        return toggleButton;
    }

    /** Retourne Le bouton d'ajout
     *
     * @return Le bouton d'ajout
     */
    public JButton getAjouterButton() {
        return ajouterButton;
    }

    /** Retourne Le bouton de suppression
     *
     * @return Le bouton de suppression
     */
    public JButton getSupprimerButton() { return supprimerButton; }

    /** Retourne La liste de recettes
     *
     * @return La liste de recettes
     */
    public JList getRecettesList() {
        return recettesList;
    }

    /** Retourne La liste d'ingredients
     *
     * @return La liste d'ingredients
     */
    public JList getIngredientsList() {
        return ingredientsList;
    }

    /** Retourne Le bouton d'import
     *
     * @return Le bouton d'import
     */
    public JButton getImportButton() {
        return importButton;
    }

    /** Retourne Le texte d'etat
     *
     * @return Le texte d'etat
     */
    public JLabel getEtatLabel() {
        return etatLabel;
    }
}









