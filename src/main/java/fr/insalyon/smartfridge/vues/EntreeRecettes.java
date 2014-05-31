package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeRecettesControleur;

import javax.swing.*;
import java.awt.*;

public class EntreeRecettes extends SousPanneau implements Changeable {
    private EntreeRecettesControleur controleur;

    private JList articlesList = new JList();
    private JButton ajouterIngredientButton = new JButton("Ajouter", UtilitairesVues.icone("ajout"));
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
    private JButton supprimerIngredientButton = new JButton("Supprimer", UtilitairesVues.icone("suppression"));
    private JList ingredientsList = new JList();
    private JButton effacerButton = new JButton("Effacer", UtilitairesVues.icone("desactiver"));
    private JTextField nomRecetteTexte = new JTextField();
    private JButton validerButton = new JButton("Valider", UtilitairesVues.icone("ok"));
    private JSpinner pourSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

    public EntreeRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new EntreeRecettesControleur(fenetre, this);

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

    public JList getArticlesList() {
        return articlesList;
    }

    public JButton getAjouterIngredientButton() {
        return ajouterIngredientButton;
    }

    public JList getIngredientsList() {return ingredientsList; }

    public JButton getValiderButton() { return validerButton; }

    public JButton getSuprrimerIngredientButton() { return supprimerIngredientButton; }

    public JButton getEffacerButton() { return effacerButton; }

    public JTextField getNomRecetteTexte() { return nomRecetteTexte; }

    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }

    public JSpinner getPourSpinner() {
        return pourSpinner;
    }
}

