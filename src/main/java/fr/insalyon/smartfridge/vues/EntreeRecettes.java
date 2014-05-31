package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeRecettesControleur;

import javax.swing.*;
import java.awt.*;


/**
 * Created by fannygallais on 20/04/2014.
 */
public class EntreeRecettes extends SousPanneau {
    private EntreeRecettesControleur controleur;

    private JPanel principal = new JPanel();
    private JPanel milieu = new JPanel();
    private JList articlesList = new JList();
    private JScrollPane articlesScroll = new JScrollPane(articlesList);
    private JButton ajouterIngredientButton = new JButton();
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
    private JButton supprimerIngredientButton = new JButton();
    private JList ingredientsList = new JList();
    private JScrollPane ingredientsScroll = new JScrollPane(ingredientsList);
    private JPanel options = new JPanel();
    private JButton effacerButton = new JButton();
    private JLabel nomRecette = new JLabel();
    private JTextField nomRecetteTexte = new JTextField();
    private JButton validerButton = new JButton();
    private JLabel pourLabel = new JLabel();
    private JSpinner pourSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

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
        ajouterIngredientButton.setText("Ajouter");
        ajouterIngredientButton.setIcon(new ImageIcon(getClass().getResource("/icones/ajout.png")));
        milieu.add(ajouterIngredientButton);
        ajouterIngredientButton.addActionListener(controleur);
        milieu.add(quantiteSpinner);
        supprimerIngredientButton.setText("Supprimer");
        supprimerIngredientButton.setIcon(new ImageIcon(getClass().getResource("/icones/suppression.png")));
        supprimerIngredientButton.addActionListener(controleur);
        milieu.add(supprimerIngredientButton);
        principal.add(milieu);
        ingredientsList.addListSelectionListener(controleur);
        principal.add(ingredientsScroll);
        this.add(principal, BorderLayout.CENTER);

        options.setLayout(new GridLayout(1, 6));
        effacerButton.setText("Effacer");
        effacerButton.setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
        effacerButton.addActionListener(controleur);
        options.add(effacerButton);
        nomRecette.setText("Nom de la recette :");
        options.add(nomRecette);
        options.add(nomRecetteTexte);
        pourLabel.setText("Pour :");
        options.add(pourLabel);
        options.add(pourSpinner);
        validerButton.setText("Valider");
        validerButton.setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
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

    public JSpinner getPourSpinner() {
        return pourSpinner;
    }
}

