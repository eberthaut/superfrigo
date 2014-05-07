package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.controleurs.EntreeRecettesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import javax.swing.*;
import java.awt.*;


/**
 * Created by fannygallais on 20/04/2014.
 */
public class EntreeRecettes extends SousPanneau {
    EntreeRecettesControleur controleur;

    private JPanel principal = new JPanel();
    private JPanel milieu = new JPanel();
    private JList articlesList = new JList();
    private JScrollPane articlesScroll = new JScrollPane(articlesList);
    private JButton ajouterIngredientButton = new JButton();
    private JSpinner quantiteSpinner = new JSpinner();
    private JButton supprimerIngredientButton = new JButton();
    private JList ingredientsList = new JList();
    private JScrollPane ingredientsScroll = new JScrollPane(ingredientsList);
    private JPanel options = new JPanel();
    private JButton effacerButton = new JButton();
    private JLabel nomRecette = new JLabel();
    private JTextField nomRecetteTexte = new JTextField();
    private JButton validerButton = new JButton();

    public EntreeRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new EntreeRecettesControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        principal.setLayout(new GridLayout(1,3));
        articlesList.addListSelectionListener(controleur);
        principal.add(articlesScroll);
        milieu.setLayout(new GridLayout(3, 1));
        ajouterIngredientButton.setText("[Ajouter]>");
        milieu.add(ajouterIngredientButton);
        ajouterIngredientButton.addActionListener(controleur);
        quantiteSpinner.setValue(0);
        milieu.add(quantiteSpinner);
        supprimerIngredientButton.setText("<[Supprimer]");
        supprimerIngredientButton.addActionListener(controleur);
        milieu.add(supprimerIngredientButton);
        principal.add(milieu);
        ingredientsList.addListSelectionListener(controleur);
        principal.add(ingredientsScroll);
        this.add(principal, BorderLayout.CENTER);

        options.setLayout(new GridLayout(1, 4));
        effacerButton.setText("[Effacer]-");
        effacerButton.addActionListener(controleur);
        options.add(effacerButton);
        nomRecette.setText("Nom de la recette :");
        options.add(nomRecette);
        options.add(nomRecetteTexte);
        validerButton.setText("[Valider]+");
        validerButton.addActionListener(controleur);
        options.add(validerButton);
        this.add(options, BorderLayout.SOUTH);

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
}

