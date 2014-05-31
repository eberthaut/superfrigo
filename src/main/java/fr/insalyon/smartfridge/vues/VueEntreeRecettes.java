package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurEntreeRecettes;
import fr.insalyon.smartfridge.utilitaires.VueChangeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

/** Vue pour saisir une recette */
public class VueEntreeRecettes extends VueSousPanneau implements VueChangeable {
    /** Le controleur */
    private ControleurEntreeRecettes controleur;

    /** La liste des articles */
    private JList articlesList = new JList();
    /** Le bouton pour ajouter un ingredient */
    private JButton ajouterIngredientButton = new JButton("Ajouter", Raccourcis.icone("ajout"));
    /** Le spinner pour choisir la quantite */
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
    /** Le bouton de suppression d'un ingredient */
    private JButton supprimerIngredientButton = new JButton("Supprimer", Raccourcis.icone("suppression"));
    /** La liste d'ingredients */
    private JList ingredientsList = new JList();
    /** Le bouton pour effacer */
    private JButton effacerButton = new JButton("Effacer", Raccourcis.icone("desactiver"));
    /** Le champ pour saisir le nom */
    private JTextField nomRecetteTexte = new JTextField();
    /** Le bouton de validation */
    private JButton validerButton = new JButton("Valider", Raccourcis.icone("ok"));
    /** Le spinner de choix du nombre de personnes */
    private JSpinner pourSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueEntreeRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurEntreeRecettes(fenetre, this);

        articlesList.addListSelectionListener(controleur);
        ajouterIngredientButton.addActionListener(controleur);
        ingredientsList.addListSelectionListener(controleur);
        supprimerIngredientButton.addActionListener(controleur);
        effacerButton.addActionListener(controleur);
        validerButton.addActionListener(controleur);


        JPanel principal = new JPanel(new GridLayout(1,3));
        JScrollPane articlesScroll = new JScrollPane(articlesList);
        principal.add(articlesScroll);

        JPanel milieu = new JPanel(new GridLayout(3, 1));
        milieu.add(ajouterIngredientButton);
        milieu.add(quantiteSpinner);
        milieu.add(supprimerIngredientButton);
        principal.add(milieu);

        JScrollPane ingredientsScroll = new JScrollPane(ingredientsList);
        principal.add(ingredientsScroll);
        this.add(principal, BorderLayout.CENTER);


        JPanel options = new JPanel(new GridLayout(1, 6));
        options.add(effacerButton);
        JLabel nomRecetteLabel = new JLabel("Nom de la recette :");
        nomRecetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(nomRecetteLabel);
        options.add(nomRecetteTexte);
        JLabel pourLabel = new JLabel("Pour (nb. pers.) :");
        pourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(pourLabel);
        options.add(pourSpinner);
        options.add(validerButton);
        this.add(options, BorderLayout.SOUTH);

    }

    @Override
    public void mettreAJour() {
        controleur.creerListe();
    }

    /** Retourne La liste des articles
     *
     * @return La liste des articles
     */
    public JList getArticlesList() {
        return articlesList;
    }

    /** Retourne Le bouton pour ajouter un ingredient
     *
     * @return Le bouton pour ajouter un ingredient
     */
    public JButton getAjouterIngredientButton() {
        return ajouterIngredientButton;
    }

    /** Retourne La liste d'ingredients
     *
     * @return La liste d'ingredients
     */
    public JList getIngredientsList() {return ingredientsList; }

    /** Retourne Le bouton de validation
     *
     * @return Le bouton de validation
     */
    public JButton getValiderButton() { return validerButton; }

    /** Retourne Le bouton de suppression d'un ingredient
     *
     * @return Le bouton de suppression d'un ingredient
     */
    public JButton getSuprrimerIngredientButton() { return supprimerIngredientButton; }

    /** Retourne Le bouton pour effacer
     *
     * @return Le bouton pour effacer
     */
    public JButton getEffacerButton() { return effacerButton; }

    /** Retourne Le champ pour saisir le nom
     *
     * @return Le champ pour saisir le nom
     */
    public JTextField getNomRecetteTexte() { return nomRecetteTexte; }

    /** Retourne Le spinner pour choisir la quantite
     *
     * @return Le spinner pour choisir la quantite
     */
    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }

    /** Retourne Le spinner de choix du nombre de personnes
     *
     * @return Le spinner de choix du nombre de personnes
     */
    public JSpinner getPourSpinner() {
        return pourSpinner;
    }
}

