package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurSortieAliment;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import java.awt.*;

import javax.swing.*;

/** Vue pour enlever des aliments */
public class VueSortieAliment extends VueSousPanneau implements Rafraichissable {
    /** Le constructeur */
    private final ControleurSortieAliment controleur;

    /** La liste d'aliments */
    private final JList alimentsList = new JList();
    /** Le bouton pour enlever */
    private final JButton enleverButton = new JButton("Enlever", Raccourcis.icone("suppression"));
    /** Le spinner de quantite */
    private final JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // c'est le compteur de qte

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueSortieAliment(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurSortieAliment(this);

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
        controleur.mettreAJour();
    }

    /** Retourne La liste d'aliments
     *
     * @return La liste d'aliments
     */
    public JList getAlimentsList() {
        return alimentsList;
    }

    /** Retourne Le bouton pour enlever
     *
     * @return Le bouton pour enlever
     */
    public JButton getEnleverButton() {
        return enleverButton;
    }

    /** Retourne Le spinner de quantite
     *
     * @return Le spinner de quantite
     */
    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }


}
