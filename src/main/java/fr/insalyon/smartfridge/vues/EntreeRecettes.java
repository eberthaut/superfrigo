package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.controleurs.EntreeRecettesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import javax.swing.*;
import java.awt.*;


/**
 * Created by fannygallais on 20/04/2014.
 */
public class EntreeRecettes extends JPanel {
    EntreeRecettesControleur controleur;

    private FlowLayout layout = new FlowLayout();
    private BorderLayout layout2 = new BorderLayout();
    private JPanel panneauListe = new JPanel();
    private JPanel panneauBouton1 = new JPanel();
    private JPanel panneauBouton2 = new JPanel();
    private JList articlesList = new JList();
    private JButton ajouterIngredientButton = new JButton();
    private JButton retourButton = new JButton();
    private JList ingredientsList = new JList();
    private JLabel nomRecette = new JLabel();
    private JTextField nomRecetteTexte = new JTextField();
    private JButton validerButton = new JButton();
    private JScrollPane scrollPane = new JScrollPane(articlesList);
    private JLabel avancementAjout = new JLabel();
    private JButton supprimerIngredientButton = new JButton();
    private JButton effacerButton = new JButton();

    public EntreeRecettes(Fenetre fenetre) {
        controleur = new EntreeRecettesControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout2);
        panneauListe.setLayout(layout);
        panneauBouton1.setLayout(layout);
        this.add(panneauBouton1, BorderLayout.SOUTH);
        this.add(panneauListe, BorderLayout.CENTER);
        this.add(panneauBouton2, BorderLayout.NORTH);
        this.setSize(new Dimension(506, 372));

        articlesList.setPreferredSize(new Dimension(200, 400));
        articlesList.setMaximumSize(new Dimension(200, 1000));
        articlesList.setMinimumSize(new Dimension(200, 0));
        articlesList.setSize(new Dimension(200, 100));
        scrollPane.setPreferredSize(new Dimension(250,450));
        ingredientsList.setPreferredSize(new Dimension(200, 400));
        nomRecetteTexte.setPreferredSize(new Dimension(100, 20));

        ajouterIngredientButton.setText("Ajouter à la liste d'ingrédients");
        supprimerIngredientButton.setText("Supprimer l'ingrédient de la liste");
        retourButton.setText("Retour");
        nomRecette.setText("Nom de la recette :");
        validerButton.setText("Valider");
        avancementAjout.setText("Recette en cours d'ajout... Cliquez sur valider pour ajouter la recette!");
        effacerButton.setText("Tout effacer");

        validerButton.addActionListener(controleur);
        supprimerIngredientButton.addActionListener(controleur);
        ajouterIngredientButton.addActionListener(controleur);
        retourButton.addActionListener(controleur);
        effacerButton.addActionListener(controleur);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panneauListe.add(scrollPane);
        panneauListe.add(ingredientsList);
        panneauBouton2.add(nomRecette);
        panneauBouton2.add(nomRecetteTexte);
        panneauBouton2.add(ajouterIngredientButton);
        panneauBouton2.add(supprimerIngredientButton);
        panneauBouton2.add(effacerButton);
        panneauBouton1.add(retourButton, null);
        panneauBouton1.add(validerButton);
        panneauBouton1.add(avancementAjout);

        controleur.creerListe();


    }

    public JList getArticlesList() {
        return articlesList;
    }

    public JButton getAjouterIngredientButton() {
        return ajouterIngredientButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public JList getIngredientsList() {return ingredientsList; }

    public JButton getValiderButton() { return validerButton; }

    public JButton getSuprrimerIngredientButton() { return supprimerIngredientButton; }

    public JButton getEffacerButton() { return effacerButton; }

    public JTextField getNomRecetteTexte() { return nomRecetteTexte; }

    public void setAvancementAjout(String s) {avancementAjout.setText(s);}
}

