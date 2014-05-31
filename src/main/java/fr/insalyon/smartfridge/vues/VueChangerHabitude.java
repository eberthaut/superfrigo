package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurChangerHabitude;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

/** Vue pour changer les habitudes utilisateur */
public class VueChangerHabitude extends VueSousPanneau implements Rafraichissable {
    /** Le controleur */
    private ControleurChangerHabitude controleur;

    /** La liste d'articles */
    private JList articlesList = new JList();
    /** Le bouton pour changer les habitudes */
    private JButton changerHabitudeButton = new JButton("Appliquer", Raccourcis.icone("ok"));
    /** L'affichage de l'etat des habitudes */
    private JLabel habitudeEtat = new JLabel("Veuillez sélectionner un article");
    /** Le spinner de selection de l'habitude */
    private JSpinner habitudeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueChangerHabitude(Fenetre fenetre){
        super(fenetre);
        controleur = new ControleurChangerHabitude(this);

        changerHabitudeButton.addActionListener(controleur);
        articlesList.addListSelectionListener(controleur);

        JScrollPane scroll = new JScrollPane(articlesList);
        this.add(scroll, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 3));
        options.add(habitudeEtat);
        options.add(getHabitudeSpinner());
        options.add(changerHabitudeButton);
        this.add(options, BorderLayout.SOUTH);
    }

    @Override
    public void mettreAJour() {
        controleur.mettreAJour();
    }

    /** Retourne La liste des Articles
     *
     * @return La liste des Articles
     */
    public JList getArticlesList() {return articlesList;}

    /** Retourne Le bouton pour changer l'habitude
     *
     * @return Le bouton pour changer l'habitude
     */
    public JButton getChangerHabitudeButton() {return changerHabitudeButton;}

    /** Retourne Le texte d'etat
     *
     * @return Le texte d'etat
     */
    public JLabel getHabitudeEtat() {return habitudeEtat;}

    /** Retourne Le spinner de selection
     *
     * @return Le spinner de selection
     */
    public JSpinner getHabitudeSpinner() {
        return habitudeSpinner;
    }
}

